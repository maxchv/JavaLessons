var webSocket;
var myUsername = null;
var sendToUser = "All Users"; // Чтоб обратиться к переменным из iframe
$(function () {
    var usersOnline = ["All Users"];
    var myDialogues = {"All Users": []};
    $(window).load(connectWS);
    $(window).unload(disconWS);
    function addNewUser(username) {
        let li = $("#usersList").find("li:first").clone(true);
        li.html("");
        li.append('<span class="dialogName">' + username + '</span>' +
            '<span class="badge" style="display: none;"></span>');
        li.removeClass("li-active");
        li.appendTo("#usersList");
        myDialogues[username] = [];
        myDialogues[username].unreaded = 0;
        usersOnline.push(username);
    }

    function deleteUser(username) {
        for (let i in usersOnline) {
            if (usersOnline[i] == username) {
                usersOnline.splice(i, 1)
            }
        }
        $("#usersList li .dialogName").each(function (id) {
            if ($(this).text() == username) {
                $(this).parent().remove();
            }
        })
        if (sendToUser == username) {
            $("#usersList li:last").click();
        }
        delete myDialogues[username];
    }

    $(window).resize(function () {
        $(".pre-scrollable").css("min-height", $(window).height() * 0.6 + "px");
    });

    $("#checkersOpen").click(function () {
        $(this).css("display", "none");
        $("#checkersClose").css("display", "block");
        let iframe = document.getElementsByTagName('iframe')[0];
        $("#checkersWell").css("display", "block");
        let iframeDoc = iframe.contentWindow.document;
        if(randomInteger(1, 10) % 2 == 0) {
            iframe.contentWindow.myColor = "white";
        }
        else{
            iframe.contentWindow.myColor = "black";
        }
        iframe.contentWindow.makeFieldAndCheckers();
        let JSONMessage = {
            "recipient": sendToUser,
            "checker": "none",
            "color": iframe.contentWindow.myColor == "white" ? "black" : "white"
        };
        webSocket.send(JSON.stringify(JSONMessage));
    });
    $("#checkersClose").click(function () {
       $("#checkersClose").css("display", "none");
       $("#checkersOpen").css("display", "block");
       $("#checkersWell").css("display", "none");
        let iframe = document.getElementsByTagName('iframe')[0];
        iframe.src = iframe.src;
        let JSONMessage = {
            "recipient": sendToUser,
            "checker": "close",
        };
        webSocket.send(JSON.stringify(JSONMessage));
    });
    function connectWS() {
        $(".pre-scrollable").css("min-height", $(window).height() * 0.6 + "px"); // My fix)
        var wsUri = "ws://" + document.location.host + document.location.pathname + "chat";
        webSocket = new WebSocket(wsUri);
        $(webSocket).on("open", loginUser);
        $(webSocket).on("message", function (e) {
            var jsonData = e.originalEvent.data;
            let JSONObject = JSON.parse(jsonData);
            if (JSONObject.deleteUsername != null) {
                deleteUser(JSONObject.deleteUsername);
            }
            else if (JSONObject.users != null) {
                for (let username of JSONObject.users) {
                    if (!usersOnline.includes(username) && myUsername != username) {
                        addNewUser(username);
                    }
                }
            }
            else if (JSONObject.checker != null) {
                let iframe = document.getElementsByTagName('iframe')[0];
                let iframeDoc = iframe.contentWindow.document;
                if(JSONObject.checker == "close"){
                    iframe.src = iframe.src;
                    $("#checkersClose").css("display", "none");
                    $("#checkersOpen").css("display", "block");
                    $("#checkersWell").css("display", "none");
                    return;
                }
                if(JSONObject.checker == "none"){
                    iframe.contentWindow.myColor = JSONObject.color;
                    iframe.contentWindow.makeFieldAndCheckers();
                    $("#checkersWell").css("display", "block");
                    $("#checkersOpen").css("display", "none");
                    $("#checkersClose").css("display", "block");
                    return;
                }
                let divID = JSONObject.field;
                let checkerID = JSONObject.checker;
                if (JSONObject.beats == null) {
                    iframeDoc.querySelector("div[data-id=\"" + divID + "\"]")
                        .appendChild(iframeDoc.getElementById(checkerID));
                }
                else {
                    for(let b of JSONObject.beats){
                        iframeDoc.querySelector("img[id=\"" + b + "\"]").remove();
                    }
                    iframeDoc.querySelector("div[data-id=\"" + divID + "\"]")
                        .appendChild(iframeDoc.getElementById(checkerID));
                }
                iframe.contentWindow.turn++;
            }
            else if (JSONObject.message != null) {
                let dialog = JSONObject.recipient == null ? "All Users" : JSONObject.sender;
                myDialogues[dialog].push(
                    {
                        "sender": JSONObject.sender,
                        "message": JSONObject.message,
                    });
                showMessage(JSONObject.sender, JSONObject.message, dialog);
            }
        });

        $(webSocket).on("error", function (err) {
            console.dir(err);
        });

        $(webSocket).on("close", function () {
        });
    }

    function disconWS() {
        webSocket.close();
    }

    $("#submit").click(function () {
        $("#message").focus();
        sendMessage();
    });

    $(document).keydown(function (event) {
        if (event.keyCode == 13) {
            sendMessage();
        }
    });

    function showMessage(sender, message, dialog) {
        if ($("#usersList .li-active .dialogName").text() != dialog) {
            $("#usersList .list-group-item .dialogName").each(function () {
                if ($(this).text() == dialog) {
                    myDialogues[dialog].unreaded += 1;
                    $(this).parent().find(".badge")
                        .text(myDialogues[dialog].unreaded)
                        .css("display", "inline");
                }
            });
            return;
        }
        else {
            $("#usersList .li-active .badge").css("display", "none");
            myDialogues[dialog].unreaded = 0;
            let elemMsg = '<div class="panel panel-default col-xs-11 col-sm-9 col-md-9 col-lg-9">' +
                '<div class="panel-body"><strong>' + sender + '</strong><br/>' + message + "</div></div>";
            $("#out").append(elemMsg);
            $("#out").parent().scrollTop($("#out").parent().scrollTop() + 100);
        }
    }

    function sendMessage() {
        if (webSocket) {
            let msg = $("#message").val();
            if (msg !== "") {
                $("#message").val("");
                let sender = myUsername || "Anonymous";
                let recipient = sendToUser;
                let JsonMsg;
                if (recipient == "All Users") {
                    myDialogues["All Users"].push(
                        {
                            "sender": "Me",
                            "message": msg
                        });
                    JsonMsg = {"sender": sender, "message": msg};
                }
                else {
                    myDialogues[recipient].push(
                        {
                            "sender": "Me",
                            "message": msg
                        });
                    JsonMsg = {"recipient": recipient, "sender": sender, "message": msg};
                }
                let elemMsg = '<div class="panel myMessage panel-default col-xs-offset-1 col-md-offset-3 col-sm-offset-3 col-lg-offset-3 col-xs-11 col-sm-9 col-md-9 col-lg-9">' +
                    '<div class="panel-body"><strong>Me</strong><br/>' + JsonMsg.message + "</div></div>";
                $("#out")
                    .append(elemMsg)
                    .parent().scrollTop($("#out").parent().scrollTop() + 100);
                let wsMsg = JSON.stringify(JsonMsg);
                webSocket.send(wsMsg);
            }
        }
    }

    $("#usersList").find("li").click(function () {
        $("#usersList").find("li").removeClass("li-active");
        $(this).addClass("li-active");
        sendToUser = $(this).find(".dialogName").text();
        if(sendToUser != "All Users"){
            $("#checkersOpen").css("display", "block");
        }
        else{
            $("#checkersOpen").css("display", "none");
        }
        $("#out").html("");
        for (let i = 0; i < myDialogues[sendToUser].length; i++) {
            if (myDialogues[sendToUser][i].sender == "Me") {
                let elemMsg = '<div class="panel myMessage panel-default col-xs-offset-1 col-md-offset-3 col-sm-offset-3 col-lg-offset-3 col-xs-11 col-sm-9 col-md-9 col-lg-9">' +
                    '<div class="panel-body"><strong>Me</strong><br/>' + myDialogues[sendToUser][i].message + "</div></div>";
                $("#out")
                    .append(elemMsg)
                    .parent().scrollTop($("#out").parent().scrollTop() + 100);
            } else {
                showMessage(myDialogues[sendToUser][i].sender, myDialogues[sendToUser][i].message, sendToUser);
            }
        }
    });

    $("#buttonLogin").click(function () {
        let username = $("#username").val();
        if ($("#rememberMe").prop('checked')) {
            localStorage.setItem("username", username);
        }
        $("#modalUsername").modal("toggle");
        loginSuccessful(username);
    });

    $("[data-dismiss]").click(function () {
        $("#loginBtn").css("display", "inline-block");
    });

    $("#loginBtn").click(loginUser);

    $("#logoutBtn").click(logoutUser);

    function loginSuccessful(username) {
        myUsername = username;
        $("#greeting").text("Hi, " + username);
        $("#loginBtn").css("display", "none");
        $("#logoutBtn").css("display", "inline-block");
        let jsonUser = JSON.stringify({"username": myUsername});
        myDialogues["All Users"].unreaded = 0;
        webSocket.send(jsonUser);
    }

    function loginUser() {
        let username = localStorage.getItem("username");
        if (username == null) {
            $("#modalUsername").modal("show");
        }
        else {
            loginSuccessful(username);
        }
    }

    function logoutUser() {
        let jsonUser = JSON.stringify({"deleteUsername": localStorage.getItem("username")});
        localStorage.removeItem("username");
        webSocket.send(jsonUser);
        location.reload();
    }

    function randomInteger(min, max) {
        var rand = min - 0.5 + Math.random() * (max - min + 1)
        rand = Math.round(rand);
        return rand;
    }
});