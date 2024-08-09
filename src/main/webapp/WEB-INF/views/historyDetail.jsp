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
        .wrap {
            max-width: 1280px;
            margin: 0 auto;
        }

        .wrap .images {
            display: block;
            font-size: 0;
            margin: 0 -5px;
        }

        .imageDetail {
            display: inline-block;
            width: 50%;
            padding: 0 5px;
            box-sizing: border-box;
        }

        .imageDetail > img {
            width: 80%;
            height: 50%;
        }

        th, td {
            border: cornflowerblue 2px solid;
        }
    </style>

    <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/handlebars@latest/dist/handlebars.js"></script>
    <script>

        $(document).ready(function () {
            const historyDetailTemplate = Handlebars.compile($("#history-detail").html());

            let query = $(location).attr("search");
            console.log(query);
            let param = new URLSearchParams(query);

            console.log(param);
            console.log(param.get("id"));
            let id = param.get("id");

            $.ajax({
                url: "/history?id=" + id,
                method: "get",
                success: function (data) {
                    console.log(data);
                    let detail = data[0];
                    $("#original").attr("src", "/images/original/" + detail.storedName);
                    $("#analysis").attr("src", "/images/analysis/" + detail.storedName);
                    let html = historyDetailTemplate({detail: detail});
                    $("body").html(html);

                // <table>
                //     <tr>
                //     <th rowspan="2">결과</th>
                //         <td>사람</td>
                //         </tr>
                //         <tr>
                //         <td>0.93</td>
                //         </tr>
                //         </table>

                    // for (let i = 0; i < analysisResults.length; i++) {
                    //     let tr = $("<tr>");
                    //     $("<th>").attr("rowspan", )
                    //     $("<td>").text(analysisResults[i].cls);
                    //     $("<td>").text(analysisResults[i].confidence);
                    //     $("<td>").text(analysisResults[i].x);
                    //     $("<td>").text(analysisResults[i].y);
                    //     $("<td>").text(analysisResults[i].w);
                    //     $("<td>").text(analysisResults[i].h);
                    // }
                }
            })

        })
    </script>
</head>
<body>
<%@include file="historyDetail.hbs"%>
<%--<div class="wrap">--%>
<%--    <div class="images">--%>
<%--        <div class="imageDetail">--%>
<%--            <img id="original">--%>
<%--            <table>--%>
<%--                <tr>--%>
<%--                    <th>파일 이름</th>--%>
<%--                    <td>dd</td>--%>
<%--                </tr>--%>
<%--            </table>--%>
<%--        </div>--%>
<%--        <div class="imageDetail">--%>
<%--            <img id="analysis">--%>
<%--            <div id="contents"></div>--%>

<%--            <%@include file="historyDetail.hbs"%>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</div>--%>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>