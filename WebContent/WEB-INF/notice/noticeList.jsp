<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>공지사항</title>
    <meta charset="UTF-8">

    <jsp:include page="../partial/config.jsp"></jsp:include>
</head>
<body>
<div class="container div-margin-bottom">
    <jsp:include page="../partial/nav.jsp"></jsp:include>

    <div class="table-responsive">
        <h3>공지사항</h3>
        <table class="table table-hover table-bordered">
            <thead class="thead-light">
            <tr>
                <th class="notice-width">#</th>
                <th>제목</th>
                <th>글쓴이</th>
                <th>작성시간</th>
                <th>수정시간</th>
            </tr>
            </thead>
            <tbody>
            <c:if test="${not empty noticeList}">
                <c:forEach items="${noticeList}" var="n" varStatus="status">
                    <tr>
                        <td>${n.id}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/main.do?action=noticeDetail&id=${n.id}">${n.title}</a>
                        </td>
                        <td>${n.writer}</td>
                        <td>${n.createdAt}</td>
                        <td>${n.updatedAt}</td>
                    </tr>
                </c:forEach>
            </c:if>
            </tbody>
        </table>
        <h3 style="text-align: right">
            <a href="${pageContext.request.contextPath}/main.do?action=getWrite" class="btn btn-primary">글 쓰기</a>
        </h3>
    </div>
</div>
</body>
</html>