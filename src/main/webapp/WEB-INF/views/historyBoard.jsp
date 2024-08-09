<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">

    <style>
        table {
            border: darkseagreen solid 2px;
        }
    </style>
    <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/handlebars@latest/dist/handlebars.js"></script>
    <script>
        function addEventToHistoryBoard() {
            $(".row2").on("click", function (e) {
                // console.log(e);
                // console.log($(e.target).closest("tr").attr("id"));
                let id = $(e.target).closest("tr").attr("id");
                $(location).attr("href", "/history-detail?id=" + id);
            })
        }

        $(document).ready(function () {
            const testTemplate = Handlebars.compile($("#test-template").html());

            $.ajax({
                url: "/history",
                method: "get",
                success: function (data) {
                    console.log(data);
                    const html = testTemplate({list: data});
                    $("#contents").html(html);
                    addEventToHistoryBoard();
                },
                error: function (request, status, error) {
                    console.log("실패")
                }
            })
        })
    </script>
</head>
<body>
<table id="contents" class="table">
</table>

<%@include file="historyBoard.hbs"%>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>