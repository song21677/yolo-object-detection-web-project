<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style>
        input {
            display: none;
        }

        #load {
            width: 100%;
            height: 100%;
            top: 0;
            left: 0;
            position: fixed;
            display: block;
            opacity: 0.8;
            background: white;
            z-index: 99;
            text-align: center;
        }

        #load > img {
            position: absolute;
            top: 50%;
            left: 50%;
            z-index: 100;
        }

        #load > p {
            position: absolute;
            top: 40%;
            left: 48%;
            z-index: 100;
        }
    </style>

    <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
    <script>

        $(document).ready(function () {

            $("input").css("opacity", 0);
            $("input").on("change", updateImageDisplay);

            $("#load").css("display", "none");

            addEventToUploadButton();
        });

        function addEventToUploadButton() {
            $("button").on("click", function (e) {

                e.preventDefault();

                $(document).css("overflow", "hidden");
                $("#load").css("display", "block");

                let form = $("form");
                let url = form.attr("action");
                let method = form.attr("method");
                let formData = new FormData(form[0]);

                let entries = formData.entries();
                for (const pair of entries) {
                    console.log(pair[0]+ ', ' + pair[1]);
                }

                $.ajax({
                    url: url,
                    method: method,
                    data: formData,
                    success: function (data) {
                        alert("성공");
                        $(document).css("overflow", "auto");
                        $("#load").css("display", "none");
                        console.log(data);

                        loadImage(data)
                    },
                    error: function (request, status, error) {
                        alert("실패 code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
                    },
                    cache: false,
                    contentType: false,
                    processData: false
                })
            });
        }
        function loadImage(id) {

            let originalImage = $("<img>").attr('src', '/images/original/' + id);
            $(".result").append(originalImage);
            let analyzedImage = $("<img>").attr('src', '/images/analysis/' + id);
            $(".result").append(analyzedImage);
        }

        function updateImageDisplay() {
            while ($(".preview").firstChild) {
                $(".preview").removeChild($(".preview").firstChild);
            }

            let curFile = $("input").prop("files");
            console.log(curFile);
            if (curFile.length == 0) {
                let p = $("<p>").text("선택된 파일이 없습니다.");
                $(".preview").append(p);
            } else {
                let selectedFile = $("<p>").text(curFile[0].name);
                $(".preview").append(selectedFile);
            }
        }
    </script>
</head>
<body>
    <div id="load">
        <img src="/images/basic/ghost.gif" alt="loading"/>
        <p>이미지 분석 중입니다 ...</p>
    </div>

    <form action="/analysis" method="post" enctype="multipart/form-data">
        <div>
            <div class="d-grid gap-2 d-md-block">
                <label for="image">
                    <button type="button" class="btn btn-light">파일 선택(PNG, JPG)</button>
                </label>
            </div>
            <!-- multiple 파일 여러 개 한 번에 선택할 수 있는 옵션-->
            <!-- accept는 파일을 검증하진 않음 -->
            <input type="file"
                   id="image"
                   name="image"
                   accept=".jpg, .jpeg, .png"/>

            <div class="preview">
            </div>
            <button type="button" class="btn btn-info">업로드 버튼</button>
        </div>
    </form>
    <div class="result">
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>