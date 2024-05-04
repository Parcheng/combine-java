$combineWebUI.element.register("TAB", (function () {
    const tabIdName = "tabId";
    const domFns = $combineWebUI.dom;
    const instanceFns = $combineWebUI.instance;
    const elementFns = $combineWebUI.element;
    const toolFns = $combineWebUI.tools;
    const configFns = $combineWebUI.config;

    const data = {};

    function init(config, parentData) {
        return config;
    }

    function buildTab(config, buildData) {
        const settings = config.settings;
        if (!settings.items) {
            return [];
        }

        const titleBody = [];
        const contentBody = [];

        const elementData = data[config.id] = {};
        for (let i = 0; i < settings.items.length; i++) {
            const curr = settings.items[i];
            curr.id = curr.id ? curr.id : toolFns.generateUUID();

            if (curr.checked) {
                curr.show = true;
            }
            if (curr.show || curr.checked) {
                titleBody.push(buildTitle(config, curr));
                const contentDom = buildContent(config, curr, buildData);
                if (!curr.checked) {
                    domFns.hide(contentDom);
                }
                contentBody.push(contentDom);
            }

            elementData[curr.id] = toolFns.copy(curr);
        }

        const tabDom = domFns.build(config.tab, titleBody);
        const contentDom = domFns.build(config.content, contentBody);
        return [tabDom, contentDom];
    }

    function buildTitle(config, itemSettings) {
        const idParam = itemSettings.id;

        const titleTextDom = domFns.build(config.titleText, itemSettings.title ? itemSettings.title : config.titleText.text);
        domFns.appendProtity(titleTextDom, "onclick", elementFns.buildCallFnCode(config.id, "checked", idParam));
        let closeDom = null;
        if (itemSettings.hasClose) {
            closeDom = domFns.build(config.titleClose, config.titleClose.text)
            domFns.appendProtity(closeDom, "onclick", elementFns.buildCallFnCode(config.id, "close", idParam));
        }

        const titleDom = domFns.build(config.title, closeDom ? [titleTextDom, closeDom] : titleTextDom);
        const itemDom = domFns.build(itemSettings.checked ? config.itemActive : config.item, titleDom);
        itemDom.setAttribute(tabIdName, idParam);
        return itemDom;
    }

    function buildContent(config, itemSettings, buildData) {
        return configFns.buildSubElement(itemSettings.body, config.content, buildData);
    }

    function checked(config, externalDom, checkId) {
        const currData = data[config.id];
        if (!externalDom || !config || !checkId || !currData) {
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
        for (let i = 0; i < config.settings.items.length; i++) {
            const curr = currData[config.settings.items[i].id];
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
                    domFns.replace(titleDom, buildTitle(config, curr));
                }
                if (contentDom) {
                    domFns.hide(contentDom);
                }
            }
        }

        if (showItemConfig) {
            showItemConfig.checked = true;
            if (showTitleDom && showContentDom) {
                domFns.replace(showTitleDom, buildTitle(config, showItemConfig));
                domFns.show(showContentDom);
            } else {
                showItemConfig.show = true;
                domFns.appendBody(titleExternalDom, buildTitle(config, showItemConfig));
                domFns.appendBody(contentExternalDom, buildContent(config, showItemConfig));
            }
        }
    }

    function close(config, externalDom, colseDomId) {
        const currData = data[config.id];
        if (!externalDom || !config || !colseDomId || !currData) {
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
        const items = config.settings.items;
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
            domFns.replace(titleDoms[0], buildTitle(config, showData));
            domFns.show(contentDoms[0]);
        }
    }


    return {
        build: function build(config, data) {
            config = init(config, data);
            return domFns.build(config.external, buildTab(config, data));
        },
        refresh: function refresh(id, config, parentData) {
            config = init(config, parentData);
            let externalDom = document.getElementById(id);
            if (externalDom) {
                domFns.setBody(externalDom, buildTab(config, parentData));
            }
        },
        getData: function getData(id) {
            return null;
        },
        call: {
            checked: function (config, checkId) {
                const externalDom = document.getElementById(config.id);
                checked(config, externalDom, checkId);
            },
            open: function (config, checkId) {
                const externalDom = document.getElementById(config.id);
                checked(config, externalDom, checkId);
            },
            close: function (config, closeDomId) {
                const externalDom = document.getElementById(config.id);
                close(config, externalDom, closeDomId);
            }
        }
    }
})());