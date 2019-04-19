<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>공지 조회</title>
    <meta charset="UTF-8">

    <jsp:include page="../partial/config.jsp"></jsp:include>
</head>
<body>
<div class="container div-margin-bottom">
    <jsp:include page="../partial/nav.jsp"></jsp:include>

    <div class="table-responsive">
        <h3>공지 사항</h3>
        <table class="table table-hover table-bordered">
            <c:if test="${not empty noticeDetail}">
                <thead class="thead-light">
                <tr>
                    <th class="notice-width">제목</th>
                    <th>${noticeDetail.title}</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td class="notice-width">내용</td>
                    <td>${noticeDetail.contents}</td>
                </tr>
                </tbody>
            </c:if>
        </table>
        <div style="width: 100%; text-align:center">
            <input type="button" value="삭제"
                   onclick="location.href='${pageContext.request.contextPath}/main.do?action=deleteNotice&id=${noticeDetail.id}'"
                   class="btn btn-danger">
        </div>
        <h3 style="text-align: right">
            <a href="${pageContext.request.contextPath}/main.do?action=getEdit&id=${noticeDetail.id}" class="btn btn-primary">글 수정</a>
        </h3>
    </div>
</div>
</body>
</html>