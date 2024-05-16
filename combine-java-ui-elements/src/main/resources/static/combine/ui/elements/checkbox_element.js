$combineWebUI.element.register("SYSTEM.CHECKBOX", (function () {
    const domFns = $combineWebUI.dom;
    const dataFns = $combineWebUI.data;
    const configFns = $combineWebUI.config;

    function init(instance, parentData) {
        return instance;
    }

    function buildControls(instance, buildData) {
        const templateConfig = instance.template;

        let layoutConfig;
        const layout = instance.layout ? instance.layout.toUpperCase() : null;
        switch (layout) {
            case "INLINE":
                layoutConfig = templateConfig.inline;
                break;
            case "MULTILINE":
            default:
                layoutConfig = templateConfig.multiline;
                break;
        }

        const isDisabled = instance.disabled && instance.disabled == true;
        return buildCheckbox(templateConfig, layoutConfig, isDisabled, instance, buildData);
    }


    function buildCheckbox(templateConfig, layoutConfig, isDisabled, instance, buildData) {
        const body = [];
        if (!instance.option) {
            return body;
        }

        if (isDisabled) {
            layoutConfig = configFns.initElement(layoutConfig, templateConfig.disabled, buildData);
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

            const inputDom = domFns.build(templateConfig.option, null);
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