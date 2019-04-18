<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>비밀번호 찾기</title>

    <jsp:include page="WEB-INF/partial/config.jsp"></jsp:include>
    <script src="${pageContext.request.contextPath}/res/js/findpw.js"></script>
</head>
<body>
<div class="container text-center login-margin-top">
    <div>
        <h1>비밀번호 찾기</h1>
    </div>
    <hr>

    <form action="${pageContext.request.contextPath}/main.do" method="post">
        <input type="hidden" name="action" value="findPw">

        <div class="form-group">
            <input class="form-control margin-auto login-input-width"
                   type="text" id="id" name="id"
                   placeholder="ID를 입력하세요.">

            <c:if test="${errorMessages.idError != null}">
                <span class="error">${errorMessages.idError}</span>
            </c:if>
        </div>

        <div class="form-group">
            <input class="form-control margin-auto login-input-width"
                   type="text" id="name" name="name"
                   placeholder="이름을 입력하세요.">

            <c:if test="${errorMessages.nameError != null}">
                <span class="error">${errorMessages.nameError}</span>
            </c:if>
        </div>
        <hr>
        <div>
			<button class="btn btn-primary" type="submit" onclick="validate();">비밀번호 찾기</button>
        </div>
    </form>
</div>

<jsp:include page="WEB-INF/partial/footer.jsp"></jsp:include>
</body>
</html>