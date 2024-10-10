$combine.element.register("SYSTEM.TEXTAREA", (function () {
    const domFns = $combine.dom;
    const dataFns = $combine.data;

    function init(instance, parentData) {
        return instance;
    }

    function buildControls(instance, buildData) {
        return buildTextarea(instance.template, instance, buildData);
    }

    function buildTextarea(template, item, buildData) {
        const key = dataFns.parseVariableText(item.key, buildData);
        const value = dataFns.parseVariable(item.value, buildData);
        const textDom = domFns.build(template.textarea, value);
        if (key) {
            textDom.name = key;
        }
        return textDom;
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
                return valueDom.value;
            }
            return null;
        }
    }
})());