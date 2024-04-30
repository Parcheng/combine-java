$combineWebUI.element.register("PAGINATION", (function () {
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
        data[config.id] = { currPage: currPage, maxPage: maxPage };

        const maxLength = settings.maxLength ? settings.maxLength : 10;
        if (isNaN(maxPage) || isNaN(currPage)) {
            return domFns.build(config.pagination, body);
        }

        const currData = data[config.id] = {};
        currData.currPage = currPage;
        currData.maxPage = maxPage;

        if (currPage <= 1) {
            body.push(buildItem(config.id, config.itemDisabled, config.itemContentFirst, config.itemContentFirst.text));
        } else {
            body.push(buildItem(config.id, config.item, config.itemContentFirst, config.itemContentFirst.text, settings.triggers, buildData, true, currPage - 1));
        }

        const startIndex = (maxLength / 2) < currPage ? parseInt(currPage - maxLength / 2) : 0;
        for (let i = startIndex; i < currPage - 1; i++) {
            body.push(buildItem(config.id, config.item, config.itemContentNum, i + 1, settings.triggers, buildData, true, i + 1));
        }

        const itemContentNumDom = buildItem(config.id, config.itemActive, config.itemContentNum, currPage);
        body.push(itemContentNumDom);

        let endIndex = currPage + (maxLength - (currPage - startIndex));
        endIndex = endIndex > maxPage ? maxPage : endIndex;
        for (let i = currPage; i < endIndex; i++) {
            body.push(buildItem(config.id, config.item, config.itemContentNum, i + 1, settings.triggers, buildData, true, i + 1));
        }

        // if (maxPage >= 2) {
        //     body.push(buildItem(config.id, config.item, config.itemContentSkip, domFns.build(config.itemContentSkipInput, null)));
        // }

        if (currPage >= maxPage) {
            body.push(buildItem(config.id, config.itemDisabled, config.itemContentEnd, config.itemContentEnd.text));
        } else {
            body.push(buildItem(config.id, config.item, config.itemContentEnd, config.itemContentEnd.text, settings.triggers, buildData, true, currPage + 1));
        }

        return domFns.build(config.pagination, body);
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
                        maxLength: config.settings.maxLength,
                        triggers: config.settings.triggers
                    }));
                }
            }
        }
    }
})());