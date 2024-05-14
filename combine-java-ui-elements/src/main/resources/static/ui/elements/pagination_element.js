$combineWebUI.element.register("PAGINATION", (function () {
    const domFns = $combineWebUI.dom;
    const dataFns = $combineWebUI.data;
    const elementFns = $combineWebUI.element;
    const triggerFns = $combineWebUI.trigger;

    const data = {};

    function init(instance, parentData) {
        return instance;
    }

    function buildPage(instance, settings, buildData) {
        const body = [];

        const currPage = Number(dataFns.parseVariable(settings.currPage, buildData));
        const maxPage = Number(dataFns.parseVariable(settings.maxPage, buildData));
        data[instance.id] = { currPage: currPage, maxPage: maxPage };

        const maxLength = settings.maxLength ? settings.maxLength : 10;
        if (isNaN(maxPage) || isNaN(currPage)) {
            return domFns.build(instance.template.pagination, body);
        }

        const currData = data[instance.id] = {};
        currData.currPage = currPage;
        currData.maxPage = maxPage;

        if (currPage <= 1) {
            body.push(buildItem(instance.id, instance.template.itemDisabled, instance.template.itemContentFirst, instance.template.itemContentFirst.text));
        } else {
            body.push(buildItem(instance.id, instance.template.item, instance.template.itemContentFirst, instance.template.itemContentFirst.text, settings.triggers, buildData, true, currPage - 1));
        }

        const startIndex = (maxLength / 2) < currPage ? parseInt(currPage - maxLength / 2) : 0;
        for (let i = startIndex; i < currPage - 1; i++) {
            body.push(buildItem(instance.id, instance.template.item, instance.template.itemContentNum, i + 1, settings.triggers, buildData, true, i + 1));
        }

        const itemContentNumDom = buildItem(instance.id, instance.template.itemActive, instance.template.itemContentNum, currPage);
        body.push(itemContentNumDom);

        let endIndex = currPage + (maxLength - (currPage - startIndex));
        endIndex = endIndex > maxPage ? maxPage : endIndex;
        for (let i = currPage; i < endIndex; i++) {
            body.push(buildItem(instance.id, instance.template.item, instance.template.itemContentNum, i + 1, settings.triggers, buildData, true, i + 1));
        }

        // if (maxPage >= 2) {
        //     body.push(buildItem(instance.id, instance.template.item, instance.template.itemContentSkip, domFns.build(instance.template.itemContentSkipInput, null)));
        // }

        if (currPage >= maxPage) {
            body.push(buildItem(instance.id, instance.template.itemDisabled, instance.template.itemContentEnd, instance.template.itemContentEnd.text));
        } else {
            body.push(buildItem(instance.id, instance.template.item, instance.template.itemContentEnd, instance.template.itemContentEnd.text, settings.triggers, buildData, true, currPage + 1));
        }

        return domFns.build(instance.template.pagination, body);
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
        build: function build(instance, data) {
            instance = init(instance, data);
            return domFns.build(instance.template.external, buildPage(instance, instance.template.settings, data));
        },
        refresh: function refresh(id, instance, parentData) {
            instance = init(instance, parentData);
            let externalDom = document.getElementById(id);
            if (externalDom) {
                domFns.setBody(externalDom, buildPage(instance, instance.template.settings, parentData));
            }
        },
        getData: function getData(id) {
            return data[id];
        },
        call: {
            checked: function (instance, pageNum) {
                let externalDom = document.getElementById(instance.id);
                if (externalDom) {
                    data[instance.id].currPage = pageNum;
                    domFns.setBody(externalDom, buildPage(instance, {
                        currPage: pageNum,
                        maxPage: data[instance.id].maxPage,
                        maxLength: instance.maxLength,
                        triggers: instance.triggers
                    }));
                }
            }
        }
    }
})());