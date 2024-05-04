$combineWebUI.element.register("CONTENT", (function () {
    const domFns = $combineWebUI.dom;
    const dataFns = $combineWebUI.data;
    const elementFns = $combineWebUI.element;

    const data = {};

    function init(config, parentData) {
        return config;
    }

    function buildContent(config, settings, buildData) {
        const body = [];
        if (!settings) {
            return body;
        }

        if (settings.leftImg) {
            const imgLeftDom = domFns.build(config.leftImg, null);
            const leftImg = dataFns.parseVariable(settings.leftImg, buildData);
            imgLeftDom.setAttribute("src", leftImg);
            body.push(domFns.build(config.left, imgLeftDom));
        }

        const contentBody = [];
        const title = dataFns.parseVariable(settings.title, buildData);
        contentBody.push(domFns.build(config.bodyTitle, title));

        if (settings.text) {
            let text = dataFns.parseVariable(settings.text, buildData);
            if (text) {
                text = text instanceof Array ? text : [text];
                for (let i = 0; i < text.length; i++) {
                    const textLines = text[i] instanceof Array ? text[i] : [text[i]];
                    for (let j = 0; j < textLines.length; j++) {
                        const textLine = textLines[j];
                        contentBody.push(domFns.build(config.bodyContent, textLine));
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
                    contentBody.push(domFns.build(config.bodyChildren, buildContent(config, settings, childrenItem)));
                }
            }
        }

        body.push(domFns.build(config.body, contentBody));

        if (settings.rightImg) {
            const imgRightDom = domFns.build(config.rightImg, null);
            const rightImg = dataFns.parseVariable(settings.rightImg, buildData);
            imgRightDom.setAttribute("src", rightImg);
            body.push(domFns.build(config.right, imgRightDom));
        }

        return body;
    }

    return {
        build: function build(config, data) {
            config = init(config, data);
            return domFns.build(config.external, buildContent(config, config.settings, data));
        },
        refresh: function refresh(id, config, parentData) {
            config = init(config, parentData);
            let externalDom = document.getElementById(id);
            if (externalDom) {
                domFns.setBody(externalDom, buildContent(config, config.settings, parentData));
            }
        },
        getData: function getData(id) {
            return data[id];
        }
    }
})());