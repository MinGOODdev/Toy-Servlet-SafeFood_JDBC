<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>로그인</title>

    <jsp:include page="WEB-INF/partial/config.jsp"></jsp:include>
    <script src="${pageContext.request.contextPath}/res/js/login.js"></script>
</head>
<body>
<div class="container text-center login-margin-top">
    <div>
        <img src="${pageContext.request.contextPath}/res/img/ssafy.png" alt="SSAFY" style="width: 150px;">
    </div>
    <hr>

    <form action="${pageContext.request.contextPath}/main.do" method="post">
        <input type="hidden" name="action" value="login">

        <div class="form-group">
            <input class="form-control margin-auto login-input-width"
                   type="text" id="id" name="id"
                   value="<c:if test="${cookie.userId.value != null}">${cookie.userId.value}</c:if>"
                   placeholder="ID를 입력하세요.">

            <c:if test="${errorMessages.idError != null}">
                <span class="error">${errorMessages.idError}</span>
            </c:if>
        </div>

        <div class="form-group">
            <input class="form-control margin-auto login-input-width"
                   type="password" id="pw" name="pw"
                   placeholder="Password를 입력하세요.">

            <c:if test="${errorMessages.pwError != null}">
                <span class="error">${errorMessages.pwError}</span>
            </c:if>
        </div>

        <div class="form-group form-check">
            <input type="checkbox" class="form-check-input" id="idSave" name="idSave">
            <label class="form-check-label" for="idSave">Remember ID</label>
        </div>
        <hr>

        <div>
            <button class="btn btn-link" type="submit" onclick="validate();">로그인</button>
            <a class="ssafy" href="${pageContext.request.contextPath}/signUp.jsp">
                <button class="btn btn-link" type="button">회원가입</button>
            </a>
            <a class="ssafy" href="${pageContext.request.contextPath}/findPw.jsp">
                <button class="btn btn-link" type="button">비밀번호 찾기</button>
            </a>
            <a class="ssafy" href="${pageContext.request.contextPath}/main.do?action=userList">
                <button class="btn btn-link" type="button">회원명단</button>
            </a>
        </div>
    </form>
</div>

<jsp:include page="WEB-INF/partial/footer.jsp"></jsp:include>
</body>
</html>