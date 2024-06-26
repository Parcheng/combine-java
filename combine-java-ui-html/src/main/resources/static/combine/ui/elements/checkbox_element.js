$combine.element.register("SYSTEM.CHECKBOX", (function () {
    const domFns = $combine.dom;
    const dataFns = $combine.data;
    const configFns = $combine.config;

    function init(instance, parentData) {
        return instance;
    }

    function buildControls(instance, buildData) {
        let layoutConfig;
        const layout = instance.layout ? instance.layout.toUpperCase() : null;
        switch (layout) {
            case "INLINE":
                layoutConfig = configFns.initElement({}, instance.template.inline);
                break;
            case "MULTILINE":
            default:
                layoutConfig = configFns.initElement({}, instance.template.multiline);
                break;
        }

        const isDisabled = instance.disabled && instance.disabled === true;
        if (isDisabled) {
            layoutConfig = configFns.initElement(layoutConfig, instance.template.disabled, buildData);
        }

        return buildCheckbox(instance, layoutConfig, isDisabled, buildData);
    }


    function buildCheckbox(instance, layoutConfig, isDisabled, buildData) {
        const body = [];
        if (!instance.option) {
            return body;
        }

        const key = dataFns.parseVariableText(instance.key, buildData);
        let value = dataFns.parseVariable(instance.value, buildData);
        value = value ? (value instanceof Array ? value : [value]) : [];

        const optionTextField = instance.option.text;
        const optionValueField = instance.option.value;

        let optionData = dataFns.parseVariable(instance.option.data, buildData);
        optionData = optionData instanceof Array ? optionData : [optionData];

        for (let i = 0; i < optionData.length; i++) {
            const currOptionData = optionData[i];
            if (!currOptionData) {
                continue;
            }

            const optionText = dataFns.parseVariableText(optionTextField, currOptionData);
            const optionValue = dataFns.parseVariableText(optionValueField, currOptionData);

            const inputDom = domFns.build(instance.template.option, null);
            const checkboxItemDom = domFns.build(layoutConfig, optionText ? [inputDom, optionText] : inputDom);
            if (key) {
                inputDom.name = key;
            }
            if (optionValue) {
                inputDom.value = optionValue;
            }
            if (value.indexOf(optionValue) >= 0) {
                inputDom.checked = true;
            }

            body.push(checkboxItemDom);
        }

        return body;
    }

    return {
        build: function build(instance, data) {
            instance = init(instance, data);
            const buttons = buildControls(instance, data);
            const externalDom = domFns.build(instance.template.external, buttons);
            return externalDom
        },
        refresh: function refresh(id, instance, parentData) {
            instance = init(instance, parentData);
            let dom = document.getElementById(id);
            domFns.setBody(dom, buildControls(instance, parentData));
        },
        getData: function getData(id) {
            let externalDom = document.getElementById(id);
            if (externalDom && externalDom.children) {
                const resultData = [];
                for (let i = 0; i < externalDom.children.length; i++) {
                    const checkboxDom = externalDom.children[i].children[0];
                    if (checkboxDom.checked) {
                        resultData.push(checkboxDom.value);
                    }
                }
                return resultData;

            }
            return null;
        }
    }
})());