<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
</head>
<body>
<!-- 메뉴바 -->
<nav class="navbar sticky-top navbar-expand-lg navbar-light">
    <img src="${pageContext.request.contextPath}/res/img/ssafy.png" alt="SSAFY" style="width: 70px;">
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/main.do?action=foodList">Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/main.do?action=mypage">MyPage</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/main.do?action=logout">Logout</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/main.do?action=noticeList">공지사항</a>
            </li>

            <li class="nav-item dropdown active">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                   data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Menu
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
<%--                    <a class="dropdown-item" href="${pageContext.request.contextPath}/main.do?action=getRegister">도서등록</a>--%>
                    <a class="dropdown-item" href="${pageContext.request.contextPath}/main.do?action=foodList">식품목록</a>
                    <a class="dropdown-item" href="${pageContext.request.contextPath}/main.do?action=orderList">섭취목록</a>
                    <a class="dropdown-item" href="${pageContext.request.contextPath}/main.do?action=userList">회원명단</a>
                </div>
            </li>
        </ul>

        <div class="float-right div-margin-top">
            <h4>${sessionScope.userId} 님 "Hello, SSAFY!"</h4>
        </div>
    </div>
</nav>
<!-- ./ 메뉴바 -->
<hr>
</body>
</html>


