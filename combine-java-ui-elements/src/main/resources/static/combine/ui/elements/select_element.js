$combine.element.register("SYSTEM.SELECT", (function () {
    const domFns = $combine.dom;
    const dataFns = $combine.data;

    function init(instance, parentData) {
        return instance;
    }

    function buildControls(instance, buildData) {
        return buildSelect(instance.template, instance, buildData);
    }

    function buildSelect(template, instance, buildData) {
        const key = dataFns.parseVariableText(instance.key, buildData);
        const value = dataFns.parseVariable(instance.value, buildData);
        const optionTextField = instance.option.text;
        const optionValueField = instance.option.value;

        let optionData = dataFns.parseVariable(instance.option.data, buildData);
        optionData = optionData instanceof Array ? optionData : [optionData];

        const selectOptionBody = [];

        if (instance.defaultText || instance.defaultValue) {
            const optionDom = domFns.build(template.option, instance.defaultText ? instance.defaultText : "");
            optionDom.setAttribute("value", instance.defaultValue ? instance.defaultValue : "");
            optionDom.setAttribute("hidden", true);
            if (!value) {
                optionDom.selected = true;
            }
            selectOptionBody.push(optionDom);
        }

        for (let i = 0; i < optionData.length; i++) {
            const currOptionData = optionData[i];
            if (!currOptionData) {
                continue;
            }

            const optionText = dataFns.parseVariableText(optionTextField, currOptionData);
            const optionValue = dataFns.parseVariableText(optionValueField, currOptionData);

            const optionDom = domFns.build(template.option, optionText);
            optionDom.setAttribute("value", optionValue);
            if (value && value == optionValue) {
                optionDom.selected = true;
            }

            selectOptionBody.push(optionDom);
        }

        const selectDom = domFns.build(template.select, selectOptionBody);
        if (key) {
            selectDom.name = key;
        }

        return selectDom
    }

    return {
        build: function build(instance, data) {
            instance = init(instance, data);
            return domFns.build(instance.template.external, buildControls(instance, data));
        },
        refresh: function refresh(id, instance, parentData) {
            instance = init(instance, parentData);
            let dom = document.getElementById(id);
            domFns.setBody(dom, buildControls(instance, parentData));
        },
        getData: function getData(id) {
            let externalDom = document.getElementById(id);
            if (externalDom) {
                const selectDom = externalDom.children[0];
                return selectDom.options[selectDom.selectedIndex].value;
            }
            return null;
        }
    }
})());