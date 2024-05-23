$combine.element.register("SYSTEM.WINDOW", (function () {
    const windowsDomId = "$combine-web-windows";
    const domFns = $combine.dom;
    const instanceFns = $combine.instance;
    const dataFns = $combine.data;
    const elementFns = $combine.element;
    const configFns = $combine.config;
    const triggerFns = $combine.trigger;

    const data = {};

    function init(instance, parentData) {
        return instance;
    }

    function buildHead(instance, buildData) {
        let body = [];

        const title = dataFns.parseVariable(instance.title, buildData);
        body.push(domFns.build(instance.template.headTitle, title ? title : instance.template.headTitle.text));

        const colseDom = domFns.build(instance.template.headClose, instance.template.headClose.text)
        domFns.appendProtity(colseDom, "onclick", elementFns.buildCallFnCode(instance.id, "close", null));
        if (instance.closeTriggers) {
            triggerFns.build(instance.closeTriggers, colseDom, buildData);
        }

        body.push(colseDom);

        return domFns.build(instance.template.head, body);
    }

    function buildContent(instance, buildData) {
        return configFns.buildSubElement(instance.body, instance.template.body, buildData);
    }

    function refreshBody(id, instance, data) {
        instance = init(instance, data);

        if (instance.body?.elements) {
            for (let i = 0; i < instance.body.elements?.length; i++) {
                instanceFns.refresh(instance.body.elements[i], data);``
            }
        } else if (instance.body?.text) {
            let externalDom = document.getElementById(id);
            if (externalDom) {
                const text = dataFns.parseVariableText(instance.body.text, data);
                domFns.setBody(externalDom.children[0].children[1], text);
            }
        }
    }

    function initWindowsDom() {
        let windowsDom = document.getElementById(windowsDomId);
        if (!windowsDom) {
            windowsDom = document.createElement("div");
            windowsDom.setAttribute("id", windowsDomId);
            document.body.appendChild(windowsDom);
        }
        return windowsDom;
    }

    return {
        build: function (instance, data) {
            instance = init(instance, data);
            const windowsDom = initWindowsDom();

            const headBody = buildHead(instance, data);
            const contentBody = buildContent(instance, data);
            const windowBody = domFns.build(instance.template.window, [headBody, contentBody]);
            windowBody.style.width = instance.size + "px";

            const externalDom = domFns.build(instance.template.external, windowBody);
            if (instance.show && instance.show === false) {
                externalDom.style.display = "none";
            }

            windowsDom.appendChild(externalDom);
            return null;
        },
        refresh: function (id, instance, parentData) {
            refreshBody(id, instance, parentData);
        },
        getData: function (id) {
            return null;
        },
        call: {
            open: function (instance, data) {
                if (data) {
                    refreshBody(instance.id, instance, data);
                }
                domFns.show(instance.id);
            },
            close: function (instance, data) {
                if (data) {
                    refreshBody(instance.id, instance, data);
                }
                domFns.hide(instance.id);
            }
        }
    }
})());