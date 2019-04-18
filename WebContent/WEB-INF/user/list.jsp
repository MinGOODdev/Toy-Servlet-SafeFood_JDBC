<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>회원명단</title>

    <jsp:include page="../partial/config.jsp"></jsp:include>
</head>
<body>
<div class="container div-margin-bottom">
    <jsp:include page="../partial/nav.jsp"></jsp:include>

    <div class="table-responsive">
        <h3>회원 명단</h3>
        <table class="table table-hover table-bordered">
            <thead class="thead-light">
            <tr>
                <th>#</th>
                <th>ID</th>
                <th>NAME</th>
                <th>-</th>
            </tr>
            </thead>
            <tbody>
            <c:if test="${not empty users}">
                <c:forEach items="${users}" var="u" varStatus="status">
                    <tr>
                        <td>${status.count}</td>
                        <td>${u.userId}</td>
                        <td>${u.name}</td>
                        <td><a href="${pageContext.request.contextPath}/main.do?action=userDelete&id=${u.userId}">삭제</a>
                        </td>
                    </tr>
                </c:forEach>
            </c:if>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>