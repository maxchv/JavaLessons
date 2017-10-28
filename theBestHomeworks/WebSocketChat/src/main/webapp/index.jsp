<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="WebSocketChat">
    <meta name="author" content="StanIsLove">
    <title>Welcome to chat</title>

    <!-- Bootstrap -->
    <!-- Latest compiled and minified CSS -->

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>

    <![endif]-->
    <style>
        .row {
            margin-bottom: 5px !important;
        }

        .myMessage {
            background-color: rgba(70, 176, 244, 0.44);
        }

        .modal-header, h4, .close {
            background-color: #3262b8;
            color: white !important;
            text-align: center;
            font-size: 30px;
        }

        .modal-footer {
            background-color: #f9f9f9;
        }

        .modal {
            text-align: center;
        }

        @media screen and (min-width: 320px) {
            .modal:before {
                display: inline-block;
                vertical-align: middle;
                content: " ";
                height: 100%;
            }
        }

        .modal-dialog {
            display: inline-block;
            text-align: left;
            vertical-align: middle;
        }

        #usersList li:hover {
            cursor: pointer;
        }

        .li-active {
            background-color: rgba(179, 189, 255, 0.76);
            font-weight: bold !important;
        }

        ::-moz-focus-outer, ::-moz-focus-inner {
            border: 0;
            padding: 0;
        }

        .pre-scrollable {
            overflow-y: auto !important;
        }

        #checkersFrame {
            width: 100%;
            height: 75vmin !important;
            border: none !important;
            border-style: none !important;
            border-width: 0 !important;
            overflow: hidden;
        }

        #checkersWell {
            display: none;
            height: 79vmin !important;
        }
    </style>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="col-xs-6 col-sm-2 col-md-2 col-lg-2">
            <h2 id="greeting"></h2>
        </div>
        <div class="col-xs-offset-2 col-xs-3 col-sm-offset-1 col-sm-1 col-md-offset-1 col-md-1 col-lg-offset-1 col-lg-1">
            <div class="form-group">
                <button type="button" class="btn btn-info" id="loginBtn" style="display: none;">Login</button>
            </div>
            <div class="form-group">
                <button type="button" class="btn btn-danger" id="logoutBtn" style="display: none;"><span
                        class="glyphicon glyphicon-off"></span>Log Out
                </button>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-xs-12 col-sm-3 col-md-3 col-lg-3">
            <div class="well">
                <h3>Users Online</h3>
                <ul class="list-group" id="usersList">
                    <li class="list-group-item li-active"><span class="dialogName">All Users</span><span class="badge"
                                                                                                         style="display: none;"></span>
                    </li>
                </ul>
            </div>
        </div>
        <div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
            <div class="well well-sm chat-well">
                <div class="row">
                    <div class="col-xs-offset-1 col-md-offset-1 col-sm-offset-1 col-lg-offset-1 col-xs-10 col-sm-10 col-md-10 col-lg-10 pre-scrollable">
                        <div class="panel-group" id="out">
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                        <div class="form-group">
                            <div class="input-group">
                                <input type="text" class="form-control" id="message" placeholder="Input message...">
                                <div class="input-group-btn">
                                    <button class="btn btn-default" type="button" id="submit">
                                        <span>Send</span>
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class=" hidden-xs hidden-sm col-md-offset-3 col-lg-offset-3 col-md-6 col-lg-6">
            <div class="row">
                <div class="form-group">
                    <div class=" hidden-xs hidden-sm col-md-offset-5 col-lg-offset-5 col-md-1 col-lg-1">
                        <button type="submit" class="btn btn-success btn-lg" id="checkersOpen" style="display: none;">Play checkers</button>
                    </div>
                </div>
            </div>
            <div class="well" id="checkersWell">
                <div class="col-md-12 col-lg-12">
                    <iframe class="embed-responsive-item"
                            src="checkers/checkers.html" id="checkersFrame"
                            allowfullscreen></iframe>
                </div>
            </div>
            <div class="form-group">
                <div class=" hidden-xs hidden-sm col-md-offset-5 col-lg-offset-5 col-md-1 col-lg-1">
                    <button type="submit" class="btn btn-danger btn-lg" id="checkersClose" style="display: none;">Close</button>
                </div>
            </div>
        </div>
    </div>
    <!-- Modal Input Nickname-->
    <div class="modal fade" id="modalUsername" role="dialog">
        <div class="modal-dialog">
            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header" style="padding:35px 50px;">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4>Welcome to chat</h4>
                </div>
                <div class="modal-body" style="padding:40px 50px;">
                    <form role="form">
                        <div class="form-group">
                            <label for="username"><span class="glyphicon glyphicon-user"></span> Username</label>
                            <input type="text" class="form-control" id="username" placeholder="Enter your Nickname">
                        </div>
                        <div class="checkbox">
                            <label><input type="checkbox" id="rememberMe" value="" checked>Remember me</label>
                        </div>
                        <button type="button" id="buttonLogin" class="btn btn-info btn-block">Submit</button>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" id="cancelLogin" class="btn btn-danger btn-default pull-left"
                            data-dismiss="modal"><span
                            class="glyphicon glyphicon-remove"></span> Cancel
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<!-- Latest compiled and minified JS -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="js/page.js"></script>
</body>
</html>
