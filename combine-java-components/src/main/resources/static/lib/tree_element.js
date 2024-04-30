$combineWebUI.element.register("TREE", (function () {
    const domFns = $combineWebUI.dom;
    const triggerFns = $combineWebUI.trigger;
    const elementFns = $combineWebUI.element;

    const data = {};

    function init(config, parentData) {
        return config;
    }

    function buildTree(config, settings, buildData, level, checkPath, treeData) {
        const body = [];
        if (!buildData) {
            return domFns.build(config.tree, body);
        }

        if (!level || level == 0) {
            level = 0;
            treeData = data[config.id] = [];
        }

        const checkFirst = settings.checkFirst === true;
        buildData = buildData instanceof Array ? buildData : [buildData];
        for (let i = 0; i < buildData.length; i++) {
            const currData = buildData[i];

            let text, children, checked;
            if (currData instanceof Array) {
                children = currData;
            } else if (currData instanceof Object) {
                text = currData[settings.textField];
                children = currData[settings.childrenField];
                checked = settings.checkedField && currData[settings.checkedField] && currData[settings.checkedField] == true;
            } else {
                text = currData;
            }

            const itemBody = [];
            const isChecked = checked || (i == 0 && checkFirst) ? true : false;
            const currTreeData = { text: text, checked: isChecked, children: [] };
            const currCheckPath = !checkPath || level == 0 ? [] : checkPath.concat();
            treeData.push(currTreeData);
            currCheckPath.push(i);

            const textDom = domFns.build(config.itemText, text ? text : "未知");
            domFns.appendProtity(textDom, "onclick", elementFns.buildCallFnCode(config.id, "checked", currCheckPath));
            if (settings.triggers) {
                triggerFns.build(settings.triggers, textDom, currData);
                if (isChecked) {
                    triggerFns.trigger(settings.triggers, textDom);
                }
            }
            itemBody.push(textDom);

            if (children) {
                const itemTreeDom = domFns.build(config.itemTree, buildTree(config, settings, children, level + 1, currCheckPath, currTreeData.children));
                if (!isChecked) {
                    domFns.hide(itemTreeDom);
                }
                itemBody.push(itemTreeDom);
            }

            const itemConfig = config.levelItems && config.levelItems[level] ? config.levelItems[level] : config.item;
            body.push(domFns.build(isChecked ? config.itemAtive : itemConfig, itemBody));
        }

        return domFns.build(config.tree, body);
    }

    function checked(config, ulDom, dataArr, checkPath, level) {
        const itemDoms = ulDom ? ulDom.children : null;
        if (!itemDoms) {
            return;
        }

        const checkIndex = checkPath.length > level ? checkPath[level] : -1;
        const length = itemDoms.length < dataArr.length ? itemDoms.length : dataArr.length;
        for (let i = 0; i < length; i++) {
            const currData = dataArr[i];
            const itemDom = itemDoms[i];

            const isChecked = currData.checked ? true : false;
            const itemChildrenDom = itemDom.children.length > 1 ? itemDom.children[1] : null;
            if (checkIndex == i) {
                if (!isChecked) {
                    currData.checked = true;
                    domFns.show(itemChildrenDom);

                    const newItemDom = domFns.build(config.itemAtive);
                    domFns.appendBody(newItemDom, itemDom.children[0]);
                    domFns.appendBody(newItemDom, itemChildrenDom);
                    domFns.replace(itemDom, newItemDom);
                }
            } else {
                if (isChecked) {
                    currData.checked = false;
                    domFns.hide(itemChildrenDom);

                    const newItemDom = domFns.build(config.levelItems && config.levelItems[level] ? config.levelItems[level] : config.item);
                    domFns.appendBody(newItemDom, itemDom.children[0]);
                    domFns.appendBody(newItemDom, itemChildrenDom);
                    domFns.replace(itemDom, newItemDom);
                }
            }


            if (currData.children && currData.children.length > 0 && itemChildrenDom) {
                checked(config, itemChildrenDom.children[0], currData.children, checkPath, level + 1);
            }
        }
    }

    return {
        build: function build(config, parentData) {
            config = init(config, parentData);
            return domFns.build(config.external, buildTree(config, config.settings, parentData));
        },
        refresh: function refresh(id, config, parentData) {
            config = init(config, parentData);
            let externalDom = document.getElementById(id);
            if (externalDom) {
                domFns.setBody(externalDom, buildTree(config, config.settings, parentData));
            }
        },
        getData: function getData(id) {
            return data[id];
        },
        call: {
            checked: function (config, checkPath) {
                const treeData = data[config.id];
                const externalDom = document.getElementById(config.id);
                if (externalDom && treeData) {
                    checked(config, externalDom.children[0], treeData, checkPath, 0);
                }
            }
        }
    }
})());