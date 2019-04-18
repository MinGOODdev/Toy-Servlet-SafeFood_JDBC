<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>비밀번호 찾았음</title>

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
        <input type="hidden" name="action" value="findpw">
		<h3>당신의 비밀번호는 ${pw} 입니다</h3>
        <div>
			<button class="btn btn-primary" type="submit" onclick="validate();">로그인</button>
        </div>
    </form>
</div>

<jsp:include page="WEB-INF/partial/footer.jsp"></jsp:include>
</body>
</html>