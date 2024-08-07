<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>

    <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
    <script>

        $(document).ready(function () {

            $("input").css("opacity", 0);
            $("input").on("change", updateImageDisplay);

            $("button").on("click", function (e) {

                e.preventDefault();

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
        });


        function loadImage(id) {

            let originalImage = $("<img>").attr('src', '/images/original/' + id);
            $(".result").append(originalImage);
            let analyzedImage = $("<img>").attr('src', '/images/analysis/' + id);
            $(".result").append(analyzedImage);
        }

        function updateImageDisplay() {
            let curFile = $("input").prop("files");
            console.log(curFile);
            if (curFile.length == 0) {

            } else {
                let selectedFile = $("<p>").text(curFile[0].name);
                $(".preview").append(selectedFile);
            }
        }
    </script>
</head>
<body>
    <form action="/analysis" method="post" enctype="multipart/form-data">
        <div>
            <label for="image">파일 선택(PNG, JPG)</label>
            <!-- multiple 파일 여러 개 한 번에 선택할 수 있는 옵션-->
            <!-- accept는 파일을 검증하진 않음 -->
            <input type="file"
                   id="image"
                   name="image"
                   accept=".jpg, .jpeg, .png"/>
            <div class="preview">
                <p>선택된 파일이 없습니다.</p>
            </div>
            <button>업로드 버튼</button>
        </div>
    </form>
    <div class="result">
    </div>
</body>
</html>