
    var atuokey = !1;
    moveElement = function (elementID, final_x, final_y, interval) {
        if (!document.getElementById) return !1;
        if (!document.getElementById(elementID)) return !1;
        var elem = document.getElementById(elementID);
        elem.movement && clearTimeout(elem.movement), elem.style.left || (elem.style.left = "0px"), elem.style.top || (elem.style.top = "0px");
        var xpos = parseInt(elem.style.left), ypos = parseInt(elem.style.top);
        if (xpos == final_x && ypos == final_y) return !0;
        if (xpos < final_x) {
            xpos += Math.ceil((final_x - xpos) / 10)
        }
        if (xpos > final_x) {
            xpos -= Math.ceil((xpos - final_x) / 10)
        }
        if (ypos < final_y) {
            ypos += Math.ceil((final_y - ypos) / 10)
        }
        if (ypos > final_y) {
            ypos -= Math.ceil((ypos - final_y) / 10)
        }
        elem.style.left = xpos + "px", elem.style.top = ypos + "px";
        var repeat = "moveElement('" + elementID + "'," + final_x + "," + final_y + "," + interval + ")";
        elem.movement = setTimeout(repeat, interval)
    }, setInterval(autoiFocus, 3e3);
    devaddLoadEvent(iFocusChange);
    function MoveQuery(id) {
        return document.getElementById(id)
    }
    function classNormal(iFocusBtnID) {
        for (var iFocusBtns = MoveQuery(iFocusBtnID).getElementsByTagName("li"), i = 0; i < iFocusBtns.length; i++) iFocusBtns[i].className = "normal"
    }

    function classCurrent(iFocusBtnID, n) {
        MoveQuery(iFocusBtnID).getElementsByTagName("li")[n].className = "current"
    }
    function iFocusChange() {
        if (!MoveQuery("ifocus")) return !1;
        MoveQuery("ifocus").onmouseover = function () {
            atuokey = !0
        }, MoveQuery("ifocus").onmouseout = function () {
            atuokey = !1
        };
        var iFocusBtns = MoveQuery("ifocus_btn").getElementsByTagName("li"), listLength = iFocusBtns.length;
        iFocusBtns[0].onmouseover = function () {
            moveElement("ifocus_piclist", 0, 0, 5), classNormal("ifocus_btn"), classCurrent("ifocus_btn", 0)
        }, listLength >= 2 && (iFocusBtns[1].onmouseover = function () {
            moveElement("ifocus_piclist", 0, -271, 5), classNormal("ifocus_btn"), classCurrent("ifocus_btn", 1)
        }), listLength >= 3 && (iFocusBtns[2].onmouseover = function () {
            moveElement("ifocus_piclist", 0, -542, 5), classNormal("ifocus_btn"), classCurrent("ifocus_btn", 2)
        }), listLength >= 4 && (iFocusBtns[3].onmouseover = function () {
            moveElement("ifocus_piclist", 0, -813, 5), classNormal("ifocus_btn"), classCurrent("ifocus_btn", 3)
        })
    }

    function devaddLoadEvent(func) {
        var oldonload = window.onload;
        "function" != typeof window.onload ? window.onload = func : window.onload = function () {
            oldonload(), func()
        }
    }
    function autoiFocus() {
        if (!MoveQuery("ifocus")) return !1;
        if (atuokey) return !1;
        for (var focusBtnList = MoveQuery("ifocus_btn").getElementsByTagName("li"), listLength = focusBtnList.length, currentNum = 0, i = 0; i < listLength; i++)
            "current" == focusBtnList[i].className && (currentNum = i);
        "0" == currentNum && 1 != listLength && (moveElement("ifocus_piclist", 0, -271, 5),
            classNormal("ifocus_btn"), classCurrent("ifocus_btn", 1)),
        1 == currentNum && 2 != listLength && (moveElement("ifocus_piclist", 0, -542, 5),
            classNormal("ifocus_btn"), classCurrent("ifocus_btn", 2)),
        2 == currentNum && 3 != listLength && (moveElement("ifocus_piclist", 0, -813, 5),
            classNormal("ifocus_btn"), classCurrent("ifocus_btn", 3)),
        3 == currentNum && (moveElement("ifocus_piclist", 0, 0, 5),
            classNormal("ifocus_btn"), classCurrent("ifocus_btn", 0)),
        1 == currentNum && 2 == listLength && (moveElement("ifocus_piclist", 0, 0, 5),
            classNormal("ifocus_btn"), classCurrent("ifocus_btn", 0)),
        2 == currentNum && 3 == listLength && (moveElement("ifocus_piclist", 0, 0, 5),
            classNormal("ifocus_btn"), classCurrent("ifocus_btn", 0))
    }



