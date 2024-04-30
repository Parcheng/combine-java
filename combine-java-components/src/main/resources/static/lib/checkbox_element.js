$combineWebUI.element.register("CHECKBOX", (function () {
    const domFns = $combineWebUI.dom;
    const dataFns = $combineWebUI.data;
    const configFns = $combineWebUI.config;

    function init(config, parentData) {
        return config;
    }

    function buildControls(config, buildData) {
        const settings = config.settings;

        let layoutConfig;
        const layout = settings.layout ? settings.layout.toUpperCase() : null;
        switch (layout) {
            case "INLINE":
                layoutConfig = config.inline;
                break;
            case "MULTILINE":
            default:
                layoutConfig = config.multiline;
                break;
        }

        const isDisabled = settings.disabled && settings.disabled == true;
        return buildCheckbox(config, layoutConfig, isDisabled, settings, buildData);
    }


    function buildCheckbox(config, layoutConfig, isDisabled, settings, buildData) {
        const body = [];
        if (!settings.option) {
            return body;
        }

        if (isDisabled) {
            layoutConfig = configFns.initElement(layoutConfig, config.disabled, buildData);
        }

        const key = dataFns.parseVariableText(settings.key, buildData);
        let value = dataFns.parseVariable(settings.value, buildData);
        value = value ? (value instanceof Array ? value : [value]) : [];

        const optionTextField = settings.option.text;
        const optionValueField = settings.option.value;

        let optionData = dataFns.parseVariable(settings.option.data, buildData);
        optionData = optionData instanceof Array ? optionData : [optionData];

        for (let i = 0; i < optionData.length; i++) {
            const currOptionData = optionData[i];
            if (!currOptionData) {
                continue;
            }

            const optionText = dataFns.parseVariableText(optionTextField, currOptionData);
            const optionValue = dataFns.parseVariableText(optionValueField, currOptionData);

            const inputDom = domFns.build(config.option, null);
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
        build: function build(config, data) {
            config = init(config, data);
            const buttons = buildControls(config, data);
            const externalDom = domFns.build(config.external, buttons);
            return externalDom
        },
        refresh: function refresh(id, config, parentData) {
            config = init(config, parentData);
            let dom = document.getElementById(id);
            domFns.setBody(dom, buildControls(config, parentData));
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