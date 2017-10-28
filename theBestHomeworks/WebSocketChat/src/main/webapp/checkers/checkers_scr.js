var myColor = "black";
function makeFieldAndCheckers() {
    var field = document.getElementById("field");
    let myOpponentColor = myColor == "black" ? "white" : "black";
    for (let i = 0; i < 8; i++) {
        for (let j = 0; j < 8; j++) {
            let childField = document.createElement("div");
            childField.style.backgroundColor = ((j - i) % 2 == 0) ? "white" : "black";
            childField.dataset.id = i * 8 + j;
            childField.classList.add("childField");
            if ((j - i + 1) % 2 == 0) {
                let checker = document.createElement("img");
                checker.classList.add("checker");
                checker.id = i * 8 + j;
                if (i * 8 + j < 24) {
                    checker.setAttribute("src", myOpponentColor + ".png");
                    checker.dataset.checkerColor = myOpponentColor;
                    childField.appendChild(checker);
                }
                if (i * 8 + j > 39) {
                    checker.setAttribute("src", myColor + ".png");
                    checker.dataset.checkerColor = myColor;
                    childField.appendChild(checker);
                }
                if (checker.dataset.checkerColor == myColor) {
                    checker.setAttribute("draggable", "true");
                }
                else {
                    checker.setAttribute("draggable", "false");
                }
            }
            field.appendChild(childField);
        }
    }
}

var turn = 0;

function whoseTurn() { // true - white, false - black
    return turn % 2 == 0 ? true : false;
}

function allowedMoves(checker) {
    var checkerColor = checker.dataset.checkerColor;
    var forwardRM = 0, forwardLM = 0;
    var checkerPosition = parseInt(checker.parentNode.dataset.id);
    forwardRM = checkerPosition - 7;
    forwardLM = checkerPosition - 9;
    for (let field of document.querySelectorAll(".childField")) {
        if (field.dataset.id == forwardRM || field.dataset.id == forwardLM) {
            if (field.style.backgroundColor != "white" && !field.hasChildNodes()) {
                let greenField = document.createElement("div");
                greenField.classList.add("rightMove");
                field.appendChild(greenField);
            }
        }
    }
}

function allowedBeats(checker) {
    var checkerColor = checker.dataset.checkerColor;
    let checkerPosition = parseInt(checker.parentNode.dataset.id);
    let enemies = [checkerPosition + 7,
        checkerPosition + 9, //Checker moves forward and backward
        checkerPosition - 9,
        checkerPosition - 7];
    let beats = [checkerPosition + 14,
        checkerPosition + 18,
        checkerPosition - 18,
        checkerPosition - 14];
    for (let i in enemies) {
        for (let field of document.querySelectorAll(".childField")) {
            if (field.dataset.id == enemies[i]) {
                if (field.hasChildNodes()
                    && field.firstChild.dataset.checkerColor != checkerColor) {
                    let finalPos = document.querySelector("div[data-id=\"" + beats[i] + "\"]");
                    if (finalPos != null) {
                        if (!finalPos.hasChildNodes() && finalPos.style.backgroundColor != "white") {
                            let redField = document.createElement("div");
                            redField.classList.add("beatMove");
                            finalPos.appendChild(redField);
                        }
                    }
                }
            }
        }
    }
}
//document.addEventListener("DOMContentLoaded", makeFieldAndCheckers);
//makeFieldAndCheckers();
document.addEventListener("dragstart", function (e) {
    let t = whoseTurn() ? "white" : "black";
    if (e.target.dataset.checkerColor == t) {
        e.dataTransfer.setData("imageId", e.target.id);
        allowedBeats(e.target);
        if (document.querySelectorAll(".beatMove").length == 0) {
            allowedMoves(e.target);
        }
    }
}, false);
document.addEventListener("dragover", function (e) {
    e.preventDefault();
}, false);
var beatedCheckers = [];
document.addEventListener("drop", function (e) {
    e.preventDefault();
    var id = e.dataTransfer.getData("imageId");
    if (e.target.classList.contains("rightMove")) {
        e.target.parentNode.appendChild(document.getElementById(id));
        turn++;
        let JSONMessage = {
            "sender": window.parent.myUsername,
            "recipient": window.parent.sendToUser,
            "checker": (63 - id).toString(),
            "field": (63 - e.target.parentNode.dataset.id).toString()
        };
        try {
            window.parent.webSocket.send(JSON.stringify(JSONMessage));
        }
        catch (exp) {
            console.log(exp);
        }
    }
    if (e.target.classList.contains("beatMove")) {
        let fieldId = document.getElementById(id).parentNode.dataset.id; // the checker field's id
        let f = e.target.parentNode.dataset.id;
        let beatenId = (parseInt(fieldId) + parseInt(f)) / 2;
        let beatenCheckerId = document.querySelector("div[data-id=\"" + beatenId + "\"]").firstChild.id;
        document.querySelector("div[data-id=\"" + beatenId + "\"]").firstChild.remove();
        e.target.parentNode.appendChild(document.getElementById(id));
        beatedCheckers.push(63 - beatenCheckerId);
        for (let redField of document.querySelectorAll(".beatMove")) {
            redField.remove();
        }
        allowedBeats(document.getElementById(id));
        if (document.querySelectorAll(".beatMove").length != 0) {
            for (let redField of document.querySelectorAll(".beatMove")) {
                redField.remove();
            }
            var event = new Event("dragstart");
            document.getElementById(id).dispatchEvent(event);
        }
        else {
            turn++;
            let JSONMessage = {
                "sender": window.parent.myUsername,
                "recipient": window.parent.sendToUser,
                "beats": beatedCheckers,
                "checker": (63 - id).toString(),
                "field": (63 - f).toString()
            };
            try {
                window.parent.webSocket.send(JSON.stringify(JSONMessage));
            }
            catch (exp) {
                console.log(exp);
            }
            beatedCheckers = [];
        }
    }
    for (let redField of document.querySelectorAll(".beatMove")) {
        redField.remove();
    }
    for (let greenField of document.querySelectorAll(".rightMove")) {
        greenField.remove();
    }
}, false);