<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>

    <style>
        body {
            text-align: center;
        }

        header {

        }

        a {

        }
    </style>

    <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
    <script>

        $(document).ready(function() {
            $.ajax({
                url: "/login-check",
                method: "get",
                data_type: "json",
                success: function (data) {
                    console.log(data);
                    console.log(data.length);
                    console.log(typeof data.length);
                    if (data === "anonymous") {
                        $("#member-menu").append("<a href=\"/login-page\">로그인</a>");
                    } else {
                        $("#member-menu").append("<a href=\"/logout\">로그아웃</a>");
                    }
                    // console.log(data.length <= 20);
                    // if (4 <= data.length <= 20) {
                    //     $("#member-menu").append("<a href=\"/logout\">로그아웃</a>");
                    // } else if (data.length > 20) {
                    //     $("#member-menu").append("<a href=\"/login-page\">로그인</a>");
                    // }
                }
            });
        });
    </script>
</head>
<body>
    <h1>YoloV8 이미지 분석 시스템</h1>
    <header>
        <div id="member-menu">
<%--            <a href="/login-page">로그인</a>--%>
<%--            <a href="/logout">로그아웃</a>--%>
        </div>
        <div>
            <a href="/upload-form">이미지 분석</a>
            <a href="/history-board">분석 결과</a>
        </div>
    </header>
</body>
</html>