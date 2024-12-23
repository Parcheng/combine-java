


window.onload = function() {
    loadFns.loadData();
    loadFns.loadOverall();
    initFns.bindNavEvent();
};

const initFns = {
    bindNavEvent: function() {
        var homeDom = document.getElementById("home");
        var overallDom = document.getElementById("overall");
        var flowConfigDom = document.getElementById("flow-config");
        var editorDom = document.getElementById("editor");

        var homeNavDom = document.getElementById("home-nav");
        homeNavDom.onclick = function() {
            domTools.switchDisplay(homeDom, true);
            domTools.switchDisplay(overallDom, false);
            domTools.switchDisplay(flowConfigDom, false);
            domTools.switchDisplay(editorDom, false);
        }
    
        var overallNavDom = document.getElementById("overall-nav");
        overallNavDom.onclick = function() {
            domTools.switchDisplay(homeDom, false);
            domTools.switchDisplay(overallDom, true);
            domTools.switchDisplay(flowConfigDom, false);
            domTools.switchDisplay(editorDom, false);
        }

        var flowConfigNavDom = document.getElementById("flow-config-nav");
        flowConfigNavDom.onclick = function() {
            domTools.switchDisplay(homeDom, false);
            domTools.switchDisplay(overallDom, false);
            domTools.switchDisplay(flowConfigDom, true);
            domTools.switchDisplay(editorDom, false);
        }

        var editorNavDom = document.getElementById("editor-nav");
        editorNavDom.onclick = function() {
            domTools.switchDisplay(homeDom, false);
            domTools.switchDisplay(overallDom, false);
            domTools.switchDisplay(flowConfigDom, false);
            domTools.switchDisplay(editorDom, true);
        }
    }
}