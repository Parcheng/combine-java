$combine.element.register("SYSTEM.CONTENT", (function () {
    const domFns = $combine.dom;
    const dataFns = $combine.data;

    const data = {};

    function init(instance, parentData) {
        return instance;
    }

    function buildContent(templateConfig, settings, buildData) {
        const body = [];
        if (!settings) {
            return body;
        }

        if (settings.leftImg) {
            const imgLeftDom = domFns.build(templateConfig.leftImg, null);
            const leftImg = dataFns.parseVariable(settings.leftImg, buildData);
            imgLeftDom.setAttribute("src", leftImg);
            body.push(domFns.build(templateConfig.left, imgLeftDom));
        }

        const contentBody = [];
        const title = dataFns.parseVariable(settings.title, buildData);
        contentBody.push(domFns.build(templateConfig.bodyTitle, title));

        if (settings.text) {
            let text = dataFns.parseVariable(settings.text, buildData);
            if (text) {
                text = text instanceof Array ? text : [text];
                for (let i = 0; i < text.length; i++) {
                    const textLines = text[i] instanceof Array ? text[i] : [text[i]];
                    for (let j = 0; j < textLines.length; j++) {
                        const textLine = textLines[j];
                        contentBody.push(domFns.build(templateConfig.bodyContent, textLine));
                    }
                }
            }
        }

        if (settings.children) {
            let children = dataFns.parseVariable(settings.children, buildData);
            if (children) {
                children = children instanceof Array ? children : [children];
                for (let i = 0; i < children.length; i++) {
                    const childrenItem = children[i];
                    contentBody.push(domFns.build(templateConfig.bodyChildren, buildContent(templateConfig, settings, childrenItem)));
                }
            }
        }

        body.push(domFns.build(templateConfig.body, contentBody));

        if (settings.rightImg) {
            const imgRightDom = domFns.build(templateConfig.rightImg, null);
            const rightImg = dataFns.parseVariable(settings.rightImg, buildData);
            imgRightDom.setAttribute("src", rightImg);
            body.push(domFns.build(templateConfig.right, imgRightDom));
        }

        return body;
    }

    return {
        build: function build(instance, data) {
            instance = init(instance, data);
            return domFns.build(instance.template.external, buildContent(instance.template, instance, data));
        },
        refresh: function refresh(id, instance, parentData) {
            instance = init(instance, parentData);
            let externalDom = document.getElementById(id);
            if (externalDom) {
                domFns.setBody(externalDom, buildContent(instance.template, instance, parentData));
            }
        },
        getData: function getData(id) {
            return data[id];
        }
    }
})());