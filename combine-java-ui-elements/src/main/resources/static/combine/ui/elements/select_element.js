$combineWebUI.element.register("SYSTEM.SELECT", (function () {
    const domFns = $combineWebUI.dom;
    const dataFns = $combineWebUI.data;
    const elementFns = $combineWebUI.element;

    function init(instance, parentData) {
        return instance;
    }

    function buildControls(instance, buildData) {
        return buildSelect(instance.template, instance, buildData);
    }

    function buildSelect(template, instance, buildData) {
        const selectBody = [];

        const key = dataFns.parseVariableText(instance.key, buildData);
        const value = dataFns.parseVariable(instance.defaultValue, buildData);
        const optionTextField = instance.option.text;
        const optionValueField = instance.option.value;

        let optionData = dataFns.parseVariable(instance.option.data, buildData);
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

            const optionDom = domFns.build(template.selectOptionItem, domFns.build(template.selectOptionItemText, optionText));
            optionDom.setAttribute("value", optionValue);
            domFns.appendProtity(optionDom, "onclick", elementFns.buildCallFnCode(instance.id, "checked", i));
            selectOptionBody.push(optionDom);

            if (optionValue == value) {
                checkedText = optionText;
            }
        }

        if (!checkedText) {
            checkedText = dataFns.parseVariable(instance.text ? instance.text : instance.defaultText, buildData);
        }
        const selectValueDom = domFns.build(template.selectValue, buildSelectValueText(template, checkedText));
        if (value) {
            selectValueDom.setAttribute("value", value);
        }
        if (key) {
            selectValueDom.name = key;
        }

        selectBody.push(selectValueDom);
        selectBody.push(domFns.build(template.selectOptions, selectOptionBody));
        return domFns.build(template.select, selectBody);
    }

    function buildSelectValueText(template, text) {
        const flagDom = domFns.build(template.selectOptionFlag, template.selectOptionFlag.text);
        return [text, " ", flagDom];
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
                const valueDom = externalDom.children[0].children[0];
                return valueDom.getAttribute("value");
            }
            return null;
        },
        call: {
            checked: function (instance, checkIndex) {
                let externalDom = document.getElementById(instance.id);
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
                    domFns.setBody(valueDom, buildSelectValueText(instance.template, text));
                }
            }
        }
    }
})());