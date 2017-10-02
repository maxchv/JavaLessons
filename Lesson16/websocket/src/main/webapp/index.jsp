<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="Page Description">
    <meta name="author" content="max">
    <title>Page Title</title>

    <!-- Bootstrap -->
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>

<nav class="navbar navbar-inverse" role="navigation">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="#">Chat</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse navbar-ex1-collapse">
        <ul class="nav navbar-nav">
            <li class="active"><a href="#">Link</a></li>
            <li><a href="#">Link</a></li>
        </ul>
    </div><!-- /.navbar-collapse -->
</nav>

<div class="container">
    <div class="row">
        <div class="col-xs-10 col-xs-offset-1">
            <div role="form">
                <legend>Simple chat</legend>

                <div class="form-group">
                    <label for="text"></label>
                    <input type="text" class="form-control" name="text" id="text" placeholder="Input...">
                </div>

                <button type="submit" class="btn btn-primary">Submit</button>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-xs-10 col-xs-offset-1">
            <pre><code id="out"></code></pre>
        </div>
    </div>
</div>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<!-- Latest compiled and minified JS -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<script>
    $(function () {
        var webSocket = new WebSocket("ws://localhost:8082/chat");

        $(webSocket).on('open', function () {
            $("#out").append("Connected successfully");
            console.log("Connected");
        });

        $(webSocket).on('error', function (event) {
            $("#out").append("Error with code: " + evetn.code);
            console.log("Error");
        });

        $(webSocket).on('message', function (event) {
            $("#out").append("Got message: " + event.originalEvent.data);
            console.dir(event);
            console.log("Got message");
        });

        $(webSocket).on('close', function () {
            $("#out").append("Close: ");
        });

        $(":submit").click(() => {
            let msg = $("#text").val();
            webSocket.send(msg);
        });
    });
</script>
</body>
</html>