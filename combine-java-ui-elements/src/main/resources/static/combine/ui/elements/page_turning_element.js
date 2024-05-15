$combineWebUI.element.register("SYSTEM.PAGE_TURNING", (function () {
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
        if (isNaN(maxPage) || isNaN(currPage)) {
            return domFns.build(instance.template.pageTurning, body);
        }

        const currData = data[instance.id] = {};
        currData.currPage = currPage;
        currData.maxPage = maxPage;

        if (currPage <= 1) {
            body.push(buildItem(instance.id, instance.template.lastDisabled, instance.template.lastContent, instance.template.lastContent.text));
        } else {
            body.push(buildItem(instance.id, instance.template.last, instance.template.lastContent, instance.template.lastContent.text, settings.triggers, buildData, true, currPage - 1));
        }

        if (currPage >= maxPage) {
            body.push(buildItem(instance.id, instance.template.nextDisabled, instance.template.nextContent, instance.template.nextContent.text));
        } else {
            body.push(buildItem(instance.id, instance.template.next, instance.template.nextContent, instance.template.nextContent.text, settings.triggers, buildData, true, currPage + 1));
        }

        return domFns.build(instance.template.pageTurning, body);
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
                        triggers: instance.triggers
                    }));
                }
            }
        }
    }
})());