$combineWebUI.element.register("RADIO", (function () {
    const domFns = $combineWebUI.dom;
    const dataFns = $combineWebUI.data;
    const configFns = $combineWebUI.config;

    function init(instance, parentData) {
        return instance;
    }

    function buildControls(instance, buildData) {
        let layoutConfig;
        const layout = instance.layout ? instance.layout.toUpperCase() : null;
        switch (layout) {
            case "INLINE":
                layoutConfig = instance.template.inline;
                break;
            case "MULTILINE":
            default:
                layoutConfig = instance.template.multiline;
                break;
        }

        const isDisabled = instance.disabled && instance.disabled == true;
        return buildRadio(instance, layoutConfig, isDisabled, settings, buildData);
    }

    function buildRadio(instance, layoutConfig, isDisabled, settings, buildData) {
        const body = [];
        if (!instance.option) {
            return body;
        }

        if (isDisabled) {
            layoutConfig = configFns.initElement(layoutConfig, instance.template.disabled, buildData);
        }

        const key = dataFns.parseVariableText(instance.key, buildData);
        const value = dataFns.parseVariable(instance.value, buildData);
        const optionTextField = instance.option.text;
        const optionValueField = instance.option.value;

        let optionData = dataFns.parseVariable(instance.option.data, buildData);
        optionData = optionData instanceof Array ? optionData : [optionData];
        for (let i = 0; i < optionData.length; i++) {
            const currOptionData = optionData[i];
            if (!currOptionData) {
                continue;
            }

            const currOptionText = dataFns.parseVariableText(optionTextField, currOptionData);
            const currOptionValue = dataFns.parseVariableText(optionValueField, currOptionData);

            const inputDom = domFns.build(instance.template.option, null);
            const radioItemDom = domFns.build(layoutConfig, currOptionText ? [inputDom, currOptionText] : inputDom);
            if (key) {
                inputDom.name = key;
            }
            if (currOptionValue) {
                inputDom.value = currOptionValue;
            }
            if (value == currOptionValue) {
                inputDom.checked = true;
            }

            body.push(radioItemDom);
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
            if (externalDom) {
                const valueDom = externalDom.children[0];
                if (valueDom.children) {
                    for (let i = 0; i < valueDom.children.length; i++) {
                        const radioDom = valueDom.children[i];
                        if (radioDom.checked) {
                            return radioDom.value;
                        }
                    }
                }
            }
            return null;
        }
    }
})());