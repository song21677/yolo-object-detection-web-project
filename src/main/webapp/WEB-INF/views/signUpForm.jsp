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

                e.preventDefault();

                let form = $("form");
                let url = form.attr("action");
                let method = form.attr("method");
                let formData = form.serialize();


                $.ajax({
                    url: url,
                    method: method,
                    data: formData,
                    success: function (data) {
                        alert("회원가입 되었습니다!");
                        window.location.replace("/login-page");
                    },
                    error: function (request, status, error) {
                        alert("실패 code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
                    }
                })
            })
        });
    </script>

</head>
<body>
<h1>회원가입 페이지</h1>
<form action="/sign-up" method="post">
    <div>
        <label for="loginId">아이디: </label>
        <input type="text" id="loginId" name="loginId">
    </div>

    <div>
        <label for="password">비밀번호: </label>
        <input type="password" id="password" name="password">
    </div>

    <button>회원가입</button>
</form>
</body>
</html>