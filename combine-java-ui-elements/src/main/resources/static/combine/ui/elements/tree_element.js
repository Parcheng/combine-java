$combineWebUI.element.register("SYSTEM.TREE", (function () {
    const domFns = $combineWebUI.dom;
    const triggerFns = $combineWebUI.trigger;
    const elementFns = $combineWebUI.element;

    const data = {};

    function init(template, parentData) {
        return template;
    }

    function buildTree(template, settings, buildData, level, checkPath, treeData) {
        const body = [];
        if (!buildData) {
            return domFns.build(template.tree, body);
        }

        if (!level || level == 0) {
            level = 0;
            treeData = data[instance.id] = [];
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

            const textDom = domFns.build(template.itemText, text ? text : "未知");
            domFns.appendProtity(textDom, "onclick", elementFns.buildCallFnCode(instance.id, "checked", currCheckPath));
            if (settings.triggers) {
                triggerFns.build(settings.triggers, textDom, currData);
                if (isChecked) {
                    triggerFns.trigger(settings.triggers, textDom);
                }
            }
            itemBody.push(textDom);

            if (children) {
                const itemTreeDom = domFns.build(template.itemTree, buildTree(template, settings, children, level + 1, currCheckPath, currTreeData.children));
                if (!isChecked) {
                    domFns.hide(itemTreeDom);
                }
                itemBody.push(itemTreeDom);
            }

            const itemConfig = template.levelItems && template.levelItems[level] ? template.levelItems[level] : template.item;
            body.push(domFns.build(isChecked ? template.itemAtive : itemConfig, itemBody));
        }

        return domFns.build(template.tree, body);
    }

    function checked(template, ulDom, dataArr, checkPath, level) {
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

                    const newItemDom = domFns.build(template.itemAtive);
                    domFns.appendBody(newItemDom, itemDom.children[0]);
                    domFns.appendBody(newItemDom, itemChildrenDom);
                    domFns.replace(itemDom, newItemDom);
                }
            } else {
                if (isChecked) {
                    currData.checked = false;
                    domFns.hide(itemChildrenDom);

                    const newItemDom = domFns.build(template.levelItems && template.levelItems[level] ? template.levelItems[level] : template.item);
                    domFns.appendBody(newItemDom, itemDom.children[0]);
                    domFns.appendBody(newItemDom, itemChildrenDom);
                    domFns.replace(itemDom, newItemDom);
                }
            }


            if (currData.children && currData.children.length > 0 && itemChildrenDom) {
                checked(template, itemChildrenDom.children[0], currData.children, checkPath, level + 1);
            }
        }
    }

    return {
        build: function build(instance, parentData) {
            instance = init(instance, parentData);
            return domFns.build(instance.template.external, buildTree(instance.template, instance, parentData));
        },
        refresh: function refresh(id, instance, parentData) {
            instance = init(instance, parentData);
            let externalDom = document.getElementById(id);
            if (externalDom) {
                domFns.setBody(externalDom, buildTree(instance.template, instance, parentData));
            }
        },
        getData: function getData(id) {
            return data[id];
        },
        call: {
            checked: function (instance, checkPath) {
                const treeData = data[instance.id];
                const externalDom = document.getElementById(instance.id);
                if (externalDom && treeData) {
                    checked(instance.template, externalDom.children[0], treeData, checkPath, 0);
                }
            }
        }
    }
})());