$combineWebUI.element.register("SELECT", (function () {
    const domFns = $combineWebUI.dom;
    const dataFns = $combineWebUI.data;
    const elementFns = $combineWebUI.element;

    function init(config, parentData) {
        return config;
    }

    function buildControls(config, buildData) {
        const settings = config.settings;
        return buildSelect(config, settings, buildData);
    }

    function buildSelect(config, settings, buildData) {
        const selectBody = [];

        const key = dataFns.parseVariableText(settings.key, buildData);
        const value = dataFns.parseVariable(settings.defaultValue, buildData);
        const optionTextField = settings.option.text;
        const optionValueField = settings.option.value;

        let optionData = dataFns.parseVariable(settings.option.data, buildData);
        optionData = optionData instanceof Array ? optionData : [optionData];

        let checkedText;
        const selectOptionBody = [];

        for (let i = 0; i < optionData.length; i++) {
            const currOptionData = optionData[i];
            if (!currOptionData) {
                continue;
            }

            const optionText = dataFns.parseVariableText(optionTextField, currOptionData);
            const optionValue = dataFns.parseVariableText(optionValueField, currOptionData);

            const optionDom = domFns.build(config.selectOptionItem, domFns.build(config.selectOptionItemText, optionText));
            optionDom.setAttribute("value", optionValue);
            domFns.appendProtity(optionDom, "onclick", elementFns.buildCallFnCode(config.id, "checked", i));
            selectOptionBody.push(optionDom);

            if (optionValue == value) {
                checkedText = optionText;
            }
        }

        if (!checkedText) {
            checkedText = dataFns.parseVariable(settings.text ? settings.text : settings.defaultText, buildData);
        }
        const selectValueDom = domFns.build(config.selectValue, buildSelectValueText(config, checkedText));
        if (value) {
            selectValueDom.setAttribute("value", value);
        }
        if (key) {
            selectValueDom.name = key;
        }

        selectBody.push(selectValueDom);
        selectBody.push(domFns.build(config.selectOptions, selectOptionBody));
        return domFns.build(config.select, selectBody);
    }

    function buildSelectValueText(config, text) {
        const flagDom = domFns.build(config.selectOptionFlag, config.selectOptionFlag.text);
        return [text, " ", flagDom];
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
                const valueDom = externalDom.children[0].children[0];
                return valueDom.getAttribute("value");
            }
            return null;
        },
        call: {
            checked: function (config, checkIndex) {
                let externalDom = document.getElementById(config.id);
                if (externalDom) {
                    const valueDom = externalDom.children[0].children[0];
                    const optionsDom = externalDom.children[0].children[1];

                    let value = "", text = "     ";
                    if (optionsDom.children.length > checkIndex) {
                        const optionDom = optionsDom.children[checkIndex];
                        value = optionDom.getAttribute("value");
                        text = optionDom.children[0].textContent;
                    }
                    valueDom.setAttribute("value", value);
                    domFns.setBody(valueDom, buildSelectValueText(config, text));
                }
            }
        }
    }
})());