<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>

    <style>
        table {
            border: darkseagreen solid 2px;
        }
    </style>
    <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/handlebars@latest/dist/handlebars.js"></script>
    <script>
        $(document).ready(function () {
            const testTemplate = Handlebars.compile($("#test-template").html());

            $.ajax({
                url: "/history",
                method: "get",
                success: function (data) {
                    console.log(data);

                    const html = testTemplate({list: data});
                    console.log("html", html);
                    $("#contents").html(html);

                    // for (let i = 0; i<data.length; i++) {
                    //     const tr = $("<tr>");
                    //     $("table").append(tr);
                    //     const row_num = tr.append($("<td>").text(i));
                    //     //row_num.append($("<a>").val('src', data[i]))
                    //     tr.append($("<td>").text(data[i].uploadedName));
                    //     tr.append($("<td>").text(data[i].owner));
                    //     tr.append($("<td>").text(data[i].requestAt));
                    // }

                },
                error: function (request, status, error) {
                    console.log("실패")
                }
            })
        })
    </script>
</head>
<body>
<table id="contents">
</table>

<%@include file="historyBoard.hbs"%>

</body>
</html>