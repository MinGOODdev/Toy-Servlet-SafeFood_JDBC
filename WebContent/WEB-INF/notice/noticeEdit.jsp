<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>공지 수정</title>
    <meta charset="UTF-8">

    <jsp:include page="../partial/config.jsp"></jsp:include>
</head>
<body>
<div class="container div-margin-bottom">
    <jsp:include page="../partial/nav.jsp"></jsp:include>

    <div class="table-responsive">
        <h3>공지 수정</h3>
        <form action="${pageContext.request.contextPath}/main.do" method="post">
            <input type="hidden" name="action" value="editNotice">
            <input type="hidden" name="id" value="${notice.id}">
            <input type="hidden" name="writer" value="${sessionScope.userId}">

            <table class="table table-hover table-bordered">
                <thead class="thead-light">
                <tr>
                    <th class="notice-width">제목</th>
                    <th><input class="form-control" type="text" name="title" value="${notice.title}" style="width: 100%"></th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td class="notice-width">내용</td>
                    <td><textarea class="form-control" name="contents" style="width: 100%; height: 600px">${notice.contents}</textarea>
                    </td>
                </tr>
                </tbody>
            </table>
            
            <div style="width: 100%; text-align:center"><button class="btn btn-primary" type="submit">수정</button></div>
        </form>
    </div>
</div>
</body>
</html>