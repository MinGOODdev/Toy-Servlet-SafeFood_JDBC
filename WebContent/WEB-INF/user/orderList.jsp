<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>섭취목록</title>
    <meta charset="UTF-8">

    <jsp:include page="../partial/config.jsp"></jsp:include>
</head>
<body>
<div class="container div-margin-bottom">
    <jsp:include page="../partial/nav.jsp"></jsp:include>

    <div class="table-responsive">
        <h3>섭취 목록</h3>
        <table class="table table-hover table-bordered">
            <thead class="thead-light">
            <tr>
                <th>#</th>
                <th>식품명</th>
                <th>제조사</th>
                <th>1회 제공량</th>
                <th>칼로리</th>
                <th>수량</th>
                <th>-</th>
            </tr>
            </thead>
            <tbody>
            <c:if test="${not empty purchaseList}">
                <c:forEach items="${purchaseList}" var="f" varStatus="status">
                    <tr>
                        <td>${status.count}</td>
                        <td>${f.food.name}</td>
                        <td>${f.food.maker}</td>
                        <td>${f.food.supportpereat} g</td>
                        <td>${f.food.calory} kcal</td>
                        <td>${f.count}</td>
                        <td>
                            <form action="${pageContext.request.contextPath}/main.do" method="post">
                                <input type="hidden" name="action" value="deletePurchase">
                                <input type="hidden" name="code" value="${f.food.code}">
                                <button class="btn-sm btn-danger">삭제</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </c:if>
            </tbody>
        </table>

        <h3>알러지를 조심하세요.</h3>
        <table class="table table-hover table-bordered">
            <c:forEach items="${purchaseList}" var="p">
                <tr>
                    <c:forEach items="${p.allergyList}" var="a">
                        <td style="color: red">${a.name}</td>
                    </c:forEach>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>