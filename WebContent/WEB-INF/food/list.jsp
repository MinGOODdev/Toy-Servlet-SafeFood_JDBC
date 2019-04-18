<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>식품 목록</title>

    <jsp:include page="../partial/config.jsp"></jsp:include>
</head>
<body>
<div class="container div-margin-bottom">
    <jsp:include page="../partial/nav.jsp"></jsp:include>
	<form class="form-inline my-2 my-lg-0" action="${pageContext.request.contextPath}/main.do">
	    <input type="hidden" name="action" value="search">
	
		<div style="width: 100%; text-align:center">
		    <select name="sb" class="form-control mr-sm-2">
		        <option value="all">전체</option>
		        <option value="code">식품코드</option>
		        <option value="name">식품명</option>
		        <option value="maker">제조사</option>
		        <option value="material">원재료</option>
		    </select>
		    <input class="form-control mr-sm-2" type="search" name="st" placeholder="Search" aria-label="Search">
		
		    <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
		</div>
	</form>
	<hr>
    <div class="row text-center">
        <c:choose>
            <c:when test="${not empty foods}">
                <c:forEach items="${foods}" var="f">
                    <div class="col-sm">
                        <div class="card custom-card">
                            <a href="${pageContext.request.contextPath}/main.do?action=view&code=${f.code}"
                               class="product-font-color">
                                <img src="${pageContext.request.contextPath}/res/${f.img}" alt="FOOD"
                                     class="card-img-top" style="width: 70% !important;">
                                <div class="card-body">
                                    <h4 class="card-title">${f.maker} / ${f.name}</h4>
                                    <h5>1회 제공량: ${f.supportpereat} g</h5>
                                    <h5>칼로리: ${f.calory} kcal</h5>
                                </div>
                            </a>
                        </div>
                    </div>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <h1>등록된 식품이 없습니다.</h1>
            </c:otherwise>
        </c:choose>
    </div>
</div>
</body>
</html>
