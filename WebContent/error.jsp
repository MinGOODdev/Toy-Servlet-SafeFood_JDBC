<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error Page</title>

    <jsp:include page="./WEB-INF/partial/config.jsp"></jsp:include>
</head>
<body>
<div class="container text-center" style="margin-top: 10%;">
    <div>
        <img src="${pageContext.request.contextPath}/res/img/ssafy.png" alt="SSAFY" style="width: 200px;">
    </div>
    <hr>

    <div class="jumbotron">
        <h1 class="display-4">삐빅! 처리 중 문제가 발생하였습니다.</h1>
        <p class="lead">${errorMsg}</p>
        <a class="btn btn-warning btn-lg" href="${pageContext.request.contextPath}/main.do?action=foodList"
           role="button">돌아가세요</a>
    </div>
</div>
</body>
</html>
