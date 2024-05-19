$combine.element.register("SYSTEM.BUTTON", (function () {
    const domFns = $combine.dom;
    const dataFns = $combine.data;
    const configFns = $combine.config;
    const triggerFns = $combine.trigger;

    function init(instance, parentData) {
        return instance;
    }

    function buildButtons(instance, buildData) {
        let body = [];
        if (!instance.items || instance.items.length === 0) {
            return body;
        }

        for (let i = 0; i < instance.items.length; i++) {
            const item = instance.items[i];

            const itemTemp = instance.template[item.type];
            if (itemTemp) {
                const text = dataFns.parseVariable(item.text, buildData);
                const itemConfig = configFns.initElement(instance.template.button, itemTemp, buildData);
                const buttonDom = domFns.build(itemConfig, text);
                if (item.triggers) {
                    triggerFns.build(item.triggers, buttonDom, buildData);
                }
                body.push(buttonDom);
            }
        }

        return body;
    }

    return {
        build: function build(instance, data) {
            instance = init(instance, data);
            const buttons = buildButtons(instance, data);
            const externalDom = domFns.build(instance.template.external, buttons);
            return externalDom
        },
        refresh: function refresh(id, instance, parentData) {
            instance = init(instance, parentData);
            let dom = document.getElementById(id);
            domFns.setBody(dom, buildButtons(instance, parentData));
        },
        getData: function getData(id) {
            return null;
        }
    }
})());