$combine.element.register("SYSTEM.BANNER", (function () {
    const domFns = $combine.dom;
    const dataFns = $combine.data;
    const elementFns = $combine.element;

    const data = {};

    function init(instance, parentData) {
        return instance;
    }

    function build(instance, buildData) {
        let body = [];

        if (instance.hasClose === true) {
            const closeDom = domFns.build(instance.template.close, instance.template.close.text);
            domFns.appendProtity(closeDom, "onclick", elementFns.buildCallFnCode(instance.id, "close", null));
            body.push(closeDom);
        }

        const content = dataFns.parseVariable(instance.content, buildData);
        if (instance.bannerType === "img") {
            const imgDom = domFns.build(instance.template.img, null);
            imgDom.src = content;
            body.push(imgDom);
        } else {
            body.push(domFns.build(instance.template.text, content));
        }
        data[instance.id] = content;

        return body;
    }

    return {
        build: function (instance, data) {
            instance = init(instance, data);
            return domFns.build(instance.template.external, build(instance, data));
        },
        refresh: function (id, instance, parentData) {
            instance = init(instance, parentData);
            let externalDom = document.getElementById(id);
            if (externalDom) {
                domFns.setBody(externalDom, build(instance, parentData));
            }
        },
        getData: function (id) {
            return data[id];
        },
        call: {
            show: function (instance) {
                const externalDom = document.getElementById(instance.id);
                if (externalDom) {
                    externalDom.style.removeProperty("display");
                }
            },
            close: function (instance) {
                const externalDom = document.getElementById(instance.id);
                if (externalDom) {
                    externalDom.style.display = "none"
                }
            }
        }
    }
})());