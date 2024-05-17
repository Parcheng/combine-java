$combineWebUI.element.register("SYSTEM.TABLE", (function () {
    const domFns = $combineWebUI.dom;
    const instanceFns = $combineWebUI.instance;
    const elementFns = $combineWebUI.element;

    const data = {};

    function init(instance, parentData) {
        return instance;
    }

    function buildHeadContent(instance) {
        const row = instance.template.headRow;
        const col = instance.template.headCol;
        const checkbox = instance.template.checkbox;
        const headNames = instance.headNames;
        const hasChecked = instance.hasChecked;

        let body = [];
        if (!headNames || headNames.length === 0) {
            return body;
        }
        if (hasChecked) {
            const checkboxDom = domFns.build(checkbox, null);
            domFns.appendProtity(checkboxDom, "onclick", elementFns.buildCallFnCode(instance.id, "checkAll"));
            body.push(domFns.build(col, checkboxDom));
        }
        for (let i = 0; i < headNames.length; i++) {
            body.push(domFns.build(col, headNames[i]));
        }
        return domFns.build(row, body);
    }

    function buildBodyContent(instance, buildData) {
        const id = instance.id;
        const row = instance.template.row;
        const col = instance.template.col;
        const checkbox = instance.template.checkbox;
        const rowOpts = instance.rowOpts;
        const fieldNames = instance.fieldNames;
        const dataFieldNames = instance.dataFieldNames;
        const minLength = instance.minLength ? instance.minLength : 10;
        const hasChecked = instance.hasChecked;
        const hasIndex = instance.hasIndex;

        let body = [];
        if (buildData instanceof Array) {
        } else if (buildData && typeof buildData === "object") {
            buildData = [buildData];
        } else {
            data[id] = [];
            return body;
        }

        data[id] = [];
        for (let d = 0; d < buildData.length; d++) {
            let rowBody = [];
            let rowData = {};
            const currData = buildData[d];

            if (dataFieldNames && dataFieldNames.length > 0) {
                for (let i = 0; i < dataFieldNames.length; i++) {
                    const fieldName = dataFieldNames[i];
                    rowData[fieldName] = currData[fieldName];
                }
            }

            if (hasChecked) {
                rowBody.push(domFns.build(col, domFns.build(checkbox, null)));
            }
            if (hasIndex) {
                rowBody.push(domFns.build(col, d + 1));
            }
            if (currData instanceof Array) {
                for (let i = 0; i < currData.length; i++) {
                    rowData["$" + i] = currData[i];
                    rowBody.push(domFns.build(col, currData[i]));
                }
            } else if (currData && typeof currData === "object" && fieldNames?.length > 0) {
                for (let i = 0; i < fieldNames.length; i++) {
                    rowData[fieldNames[i]] = currData[fieldNames[i]];
                    rowBody.push(domFns.build(col, currData[fieldNames[i]]));
                }
            }
            if (rowOpts) {
                rowBody.push(domFns.build(col, buildOpts(rowOpts, rowData)));
            }
            data[id].push(rowData);

            body.push(domFns.build(row, rowBody));
        }

        // 补空行
        if (buildData.length < minLength) {
            const supLength = minLength - buildData.length;
            for (let d = 0; d < supLength; d++) {
                const supRowBody = [];
                if (hasChecked) {
                    supRowBody.push(domFns.build(col, "\u00A0"));
                }
                if (hasIndex) {
                    supRowBody.push(domFns.build(col, "\u00A0"));
                }
                for (let i = 0; i < fieldNames.length; i++) {
                    supRowBody.push(domFns.build(col, "\u00A0"));
                }
                if (rowOpts) {
                    supRowBody.push(domFns.build(col, "\u00A0"));
                }
                body.push(domFns.build(row, supRowBody));
            }
        }

        return body;
    }

    function buildOpts(rowOpts, rowData) {
        const result = instanceFns.build(rowOpts, rowData);
        if (result.success) {
            return result.data;
        }
        return "";
    }

    return {
        build: function (instance, data) {
            instance = init(instance, data);
            const headBody = buildHeadContent(instance);
            const head = domFns.build(instance.template.head, headBody)
            const bodyBody = buildBodyContent(instance, data);
            const body = domFns.build(instance.template.body, bodyBody);
            const table = domFns.build(instance.template.table, [head, body]);
            return domFns.build(instance.template.external, table);
        },
        refresh: function (id, instance, parentData) {
            instance = init(instance, parentData);
            let externalDom = document.getElementById(id);
            if (externalDom) {
                const bodyBody = buildBodyContent(instance, parentData);
                domFns.setBody(externalDom.children[0].children[1], bodyBody);
            }
        },
        getData: function (id, instance) {
            const elementData = data[id]
            if (instance.hasChecked) {
                const result = [];
                const externalDom = document.getElementById(instance.id);
                if (externalDom) {
                    const rowDoms = externalDom.children[0].children[1].children;
                    for (let i = 0; i < rowDoms.length; i++) {
                        if (elementData.length <= i) {
                            break;
                        }

                        const rowDom = rowDoms[i];
                        if (rowDom.children.length > 0) {
                            const colDoms = rowDom.children[0].children;
                            if (colDoms.length > 0) {
                                if (colDoms[0].checked) {
                                    result.push(elementData[i]);
                                }
                            }
                        }
                    }
                }

                return result;
            }

            return elementData;
        },
        call: {
            checkAll: function (instance) {
                const externalDom = document.getElementById(instance.id);
                if (externalDom) {
                    let isChecked;
                    const headerRowDom = externalDom.children[0].children[0].children[0];
                    if (headerRowDom.children.length > 0) {
                        const headerColDom = headerRowDom.children[0];
                        if (headerColDom.children.length > 0) {
                            isChecked = headerColDom.children[0].checked;
                        }
                    }

                    const rowDoms = externalDom.children[0].children[1].children;
                    for (let i = 0; i < rowDoms.length; i++) {
                        const rowDom = rowDoms[i];
                        if (rowDom.children.length > 0) {
                            const colDoms = rowDom.children[0].children;
                            if (colDoms.length > 0) {
                                colDoms[0].checked = isChecked ? true : false;
                            }
                        }
                    }
                }
            }
        }
    }
})());