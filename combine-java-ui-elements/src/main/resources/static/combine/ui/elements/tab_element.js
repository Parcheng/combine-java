$combine.element.register("SYSTEM.TAB", (function () {
    const tabIdName = "tabId";
    const domFns = $combine.dom;
    const elementFns = $combine.element;
    const toolFns = $combine.tools;
    const configFns = $combine.config;

    const data = {};

    function init(instance, parentData) {
        return instance;
    }

    function buildTab(instance, buildData) {
        if (!instance.items) {
            return [];
        }

        const titleBody = [];
        const contentBody = [];

        const elementData = data[instance.id] = {};
        for (let i = 0; i < instance.items.length; i++) {
            const curr = instance.items[i];
            curr.id = curr.id ? curr.id : toolFns.generateUUID();

            if (curr.checked) {
                curr.show = true;
            }
            if (curr.show || curr.checked) {
                titleBody.push(buildTitle(instance, curr));
                const contentDom = buildContent(instance, curr, buildData);
                if (!curr.checked) {
                    domFns.hide(contentDom);
                }
                contentBody.push(contentDom);
            }

            elementData[curr.id] = toolFns.copy(curr);
        }

        const tabDom = domFns.build(instance.template.tab, titleBody);
        const contentDom = domFns.build(instance.template.content, contentBody);
        return [tabDom, contentDom];
    }

    function buildTitle(instance, itemSettings) {
        const idParam = itemSettings.id;

        const titleTextDom = domFns.build(instance.template.titleText, itemSettings.title ? itemSettings.title : instance.template.titleText.text);
        domFns.appendProtity(titleTextDom, "onclick", elementFns.buildCallFnCode(instance.id, "checked", idParam));
        let closeDom = null;
        if (itemSettings.hasClose) {
            closeDom = domFns.build(instance.template.titleClose, instance.template.titleClose.text)
            domFns.appendProtity(closeDom, "onclick", elementFns.buildCallFnCode(instance.id, "close", idParam));
        }

        const titleDom = domFns.build(instance.template.title, closeDom ? [titleTextDom, closeDom] : titleTextDom);
        const itemDom = domFns.build(itemSettings.checked ? instance.template.itemActive : instance.template.item, titleDom);
        itemDom.setAttribute(tabIdName, idParam);
        return itemDom;
    }

    function buildContent(instance, itemSettings, buildData) {
        return configFns.buildSubElement(itemSettings.body, instance.template.content, buildData);
    }

    function checked(instance, externalDom, checkId) {
        const currData = data[instance.id];
        if (!externalDom || !instance || !checkId || !currData) {
            return;
        }

        const titleExternalDom = externalDom.children[0];
        const contentExternalDom = externalDom.children[1];
        const titleDoms = titleExternalDom.children;
        const contentDoms = contentExternalDom.children;
        if (titleDoms.length == 0 || contentDoms.length == 0) {
            return;
        }
        const titleDomMap = {}, contentDomMap = {};
        for (let i = 0; i < titleDoms.length; i++) {
            const titleDom = titleDoms[i];
            const contentDom = contentDoms[i];
            const domTabId = titleDom.getAttribute(tabIdName);
            titleDomMap[domTabId] = titleDom;
            contentDomMap[domTabId] = contentDom;
        }

        let childrenIndex = -1;
        let showTitleDom, showContentDom, showItemConfig;
        for (let i = 0; i < instance.items.length; i++) {
            const curr = currData[instance.items[i].id];
            if (!curr) {
                continue;
            }

            let titleDom, contentDom;
            if (curr.show || curr.checked) {
                titleDom = titleDomMap[curr.id];
                contentDom = contentDomMap[curr.id];
            }

            if (checkId == curr.id) {
                showTitleDom = titleDom;
                showContentDom = contentDom;
                showItemConfig = curr;
            } else {
                if (titleDom && curr.checked) {
                    curr.checked = false;
                    domFns.replace(titleDom, buildTitle(instance, curr));
                }
                if (contentDom) {
                    domFns.hide(contentDom);
                }
            }
        }

        if (showItemConfig) {
            showItemConfig.checked = true;
            if (showTitleDom && showContentDom) {
                domFns.replace(showTitleDom, buildTitle(instance, showItemConfig));
                domFns.show(showContentDom);
            } else {
                showItemConfig.show = true;
                domFns.appendBody(titleExternalDom, buildTitle(instance, showItemConfig));
                domFns.appendBody(contentExternalDom, buildContent(instance, showItemConfig));
            }
        }
    }

    function close(instance, externalDom, colseDomId) {
        const currData = data[instance.id];
        if (!externalDom || !instance || !colseDomId || !currData) {
            return;
        }

        const titleDoms = externalDom.children[0].children;
        const contentDoms = externalDom.children[1].children;
        if (titleDoms.length == 0 || contentDoms.length == 0) {
            return;
        }
        const titleDomMap = {}, contentDomMap = {};
        for (let i = 0; i < titleDoms.length; i++) {
            const titleDom = titleDoms[i];
            const contentDom = contentDoms[i];
            const domTabId = titleDom.getAttribute(tabIdName);
            titleDomMap[domTabId] = titleDom;
            contentDomMap[domTabId] = contentDom;
        }

        let showFirstDom = false, showData;
        const items = instance.items;
        for (let i = 0; i < items.length; i++) {
            const curr = currData[items[i].id];

            let titleDom, contentDom;
            if (curr.show || curr.checked) {
                titleDom = titleDomMap[curr.id];
                contentDom = contentDomMap[curr.id];
            }

            let currIsRemoeve = false;
            if (colseDomId == curr.id) {
                curr.show = false;
                currIsRemoeve = true;
                if (titleDom && contentDom) {
                    titleDom.remove();
                    contentDom.remove();
                }
                if (curr.checked) {
                    curr.checked = false;
                    showFirstDom = true;
                }
            }

            if (!showData && curr.show) {
                showData = curr;
                showData.checked = true;
            }

            if (currIsRemoeve && showData) {
                break;
            }
        }

        if (showFirstDom && showData) {
            domFns.replace(titleDoms[0], buildTitle(instance, showData));
            domFns.show(contentDoms[0]);
        }
    }


    return {
        build: function build(instance, data) {
            instance = init(instance, data);
            return domFns.build(instance.template.external, buildTab(instance, data));
        },
        refresh: function refresh(id, instance, parentData) {
            instance = init(instance, parentData);
            let externalDom = document.getElementById(id);
            if (externalDom) {
                domFns.setBody(externalDom, buildTab(instance, parentData));
            }
        },
        getData: function getData(id) {
            return null;
        },
        call: {
            checked: function (instance, checkId) {
                const externalDom = document.getElementById(instance.id);
                checked(instance, externalDom, checkId);
            },
            open: function (instance, checkId) {
                const externalDom = document.getElementById(instance.id);
                checked(instance, externalDom, checkId);
            },
            close: function (instance, closeDomId) {
                const externalDom = document.getElementById(instance.id);
                close(instance, externalDom, closeDomId);
            }
        }
    }
})());