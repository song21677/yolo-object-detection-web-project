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
    </style>

    <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
    <script>
        $(document).ready(function () {

            $("button").on("click", function (e) {
                let form = $("form");
                let url = $("form").attr("action");
                let method = $("form").attr("method");
                let formData = new FormData(form[0]);

                $.ajax({
                    url: url,
                    method: method,
                    data: formData,
                    success: function (data) {
                        console.log(data);
                    },
                    error: function (request, status, error) {
                        alert("실패 code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
                    }
                })
            });
        });
    </script>
</head>
<body>
    <h1>로그인 페이지</h1>
    <form action="/login" method="post">
        <div>
            <label for="loginId">아이디: </label>
            <input type="text" id="loginId" name="loginId">
        </div>

        <div>
            <label for="password">비밀번호: </label>
            <input type="password" id="password" name="password">
        </div>

        <button>로그인</button>
    </form>
</body>
</html>