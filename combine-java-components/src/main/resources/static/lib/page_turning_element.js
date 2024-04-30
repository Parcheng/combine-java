$combineWebUI.element.register("PAGE_TURNING", (function () {
    const domFns = $combineWebUI.dom;
    const dataFns = $combineWebUI.data;
    const elementFns = $combineWebUI.element;
    const triggerFns = $combineWebUI.trigger;

    const data = {};

    function init(config, parentData) {
        return config;
    }

    function buildPage(config, settings, buildData) {
        const body = [];

        const currPage = Number(dataFns.parseVariable(settings.currPage, buildData));
        const maxPage = Number(dataFns.parseVariable(settings.maxPage, buildData));
        if (isNaN(maxPage) || isNaN(currPage)) {
            return domFns.build(config.pageTurning, body);
        }

        const currData = data[config.id] = {};
        currData.currPage = currPage;
        currData.maxPage = maxPage;

        if (currPage <= 1) {
            body.push(buildItem(config.id, config.lastDisabled, config.lastContent, config.lastContent.text));
        } else {
            body.push(buildItem(config.id, config.last, config.lastContent, config.lastContent.text, settings.triggers, buildData, true, currPage - 1));
        }

        if (currPage >= maxPage) {
            body.push(buildItem(config.id, config.nextDisabled, config.nextContent, config.nextContent.text));
        } else {
            body.push(buildItem(config.id, config.next, config.nextContent, config.nextContent.text, settings.triggers, buildData, true, currPage + 1));
        }

        return domFns.build(config.pageTurning, body);
    }

    function buildItem(id, item, content, body, triggers, buildData, hasChecked, targetPage) {
        const itemDom = domFns.build(item, domFns.build(content, body));
        if (hasChecked && hasChecked == true) {
            domFns.appendProtity(itemDom, "onclick", elementFns.buildCallFnCode(id, "checked", targetPage));
        }
        if (triggers) {
            triggerFns.build(triggers, itemDom, buildData);
        }
        return itemDom;
    }

    return {
        build: function build(config, data) {
            config = init(config, data);
            return domFns.build(config.external, buildPage(config, config.settings, data));
        },
        refresh: function refresh(id, config, parentData) {
            config = init(config, parentData);
            let externalDom = document.getElementById(id);
            if (externalDom) {
                domFns.setBody(externalDom, buildPage(config, config.settings, parentData));
            }
        },
        getData: function getData(id) {
            return data[id];
        },
        call: {
            checked: function (config, pageNum) {
                let externalDom = document.getElementById(config.id);
                if (externalDom) {
                    data[config.id].currPage = pageNum;
                    domFns.setBody(externalDom, buildPage(config, {
                        currPage: pageNum,
                        maxPage: data[config.id].maxPage,
                        triggers: config.settings.triggers
                    }));
                }
            }
        }
    }
})());