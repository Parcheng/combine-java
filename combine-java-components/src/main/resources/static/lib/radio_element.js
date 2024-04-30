$combineWebUI.element.register("RADIO", (function () {
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
        return buildRadio(config, layoutConfig, isDisabled, settings, buildData);
    }

    function buildRadio(config, layoutConfig, isDisabled, settings, buildData) {
        const body = [];
        if (!settings.option) {
            return body;
        }

        if (isDisabled) {
            layoutConfig = configFns.initElement(layoutConfig, config.disabled, buildData);
        }

        const key = dataFns.parseVariableText(settings.key, buildData);
        const value = dataFns.parseVariable(settings.value, buildData);
        const optionTextField = settings.option.text;
        const optionValueField = settings.option.value;

        let optionData = dataFns.parseVariable(settings.option.data, buildData);
        optionData = optionData instanceof Array ? optionData : [optionData];
        for (let i = 0; i < optionData.length; i++) {
            const currOptionData = optionData[i];
            if (!currOptionData) {
                continue;
            }

            const currOptionText = dataFns.parseVariableText(optionTextField, currOptionData);
            const currOptionValue = dataFns.parseVariableText(optionValueField, currOptionData);

            const inputDom = domFns.build(config.option, null);
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