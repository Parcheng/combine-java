


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
        var overallNavDom = document.getElementById("overall-nav");
        var flowConfigNavDom = document.getElementById("flow-config-nav");
        var editorNavDom = document.getElementById("editor-nav");

        homeNavDom.onclick = function() {
            domTools.switchDisplay(homeDom, true);
            domTools.switchDisplay(overallDom, false);
            domTools.switchDisplay(flowConfigDom, false);
            domTools.switchDisplay(editorDom, false);

            homeNavDom.className = "active";
            overallNavDom.className = "";
            flowConfigNavDom.className = "";
            editorNavDom.className = "";
        }
        
        overallNavDom.onclick = function() {
            domTools.switchDisplay(homeDom, false);
            domTools.switchDisplay(overallDom, true);
            domTools.switchDisplay(flowConfigDom, false);
            domTools.switchDisplay(editorDom, false);

            homeNavDom.className = "";
            overallNavDom.className = "active";
            flowConfigNavDom.className = "";
            editorNavDom.className = "";
        }
        
        flowConfigNavDom.onclick = function() {
            domTools.switchDisplay(homeDom, false);
            domTools.switchDisplay(overallDom, false);
            domTools.switchDisplay(flowConfigDom, true);
            domTools.switchDisplay(editorDom, false);

            homeNavDom.className = "";
            overallNavDom.className = "";
            flowConfigNavDom.className = "active";
            editorNavDom.className = "";
        }

        editorNavDom.onclick = function() {
            domTools.switchDisplay(homeDom, false);
            domTools.switchDisplay(overallDom, false);
            domTools.switchDisplay(flowConfigDom, false);
            domTools.switchDisplay(editorDom, true);

            homeNavDom.className = "";
            overallNavDom.className = "";
            flowConfigNavDom.className = "";
            editorNavDom.className = "active";
        }

        homeNavDom.dispatchEvent(new Event("click"));
    }
}