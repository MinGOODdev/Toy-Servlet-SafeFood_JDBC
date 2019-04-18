<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>도서 등록</title>

    <jsp:include page="../partial/config.jsp"></jsp:include>
    <script src="${pageContext.request.contextPath}/res/js/register.js"></script>
</head>
<body>
<div class="container div margin-bottom">
    <jsp:include page="../partial/nav.jsp"></jsp:include>

    <h1>도서 등록</h1>
    <p class="error">* 표시는 필수 입력 항목입니다.</p>
    <hr>

    <form action="${pageContext.request.contextPath}/main.do" method="post">
        <input type="hidden" name="action" value="register">

        <div class="form-row div-margin-bottom">
            <div class="form-group col-md-4">
                <span class="error">* </span><label for="isbn"> 도서번호</label>
                <input type="text" class="form-control" id="isbn" name="isbn"
                       placeholder="ISBN">
            </div>
            <div class="form-group col-md-4">
                <span class="error">* </span><label for="title"> 도서명</label>
                <input type="title" class="form-control" id="title" name="title"
                       placeholder="TITLE">
            </div>
            <div class="form-group col-md-4">
                <span class="error">* </span><label for="author"> 저자</label>
                <input type="author" class="form-control" id="author" name="author"
                       placeholder="AUTHOR">
            </div>
        </div>

        <div class="form-row div-margin-bottom">
            <div class="form-group col-md-4">
                <label for="nation">출판국가</label>
                <select id="nation" name="nation" class="form-control">
                    <option value="국내도서">국내도서</option>
                    <option value="외국도서">외국도서</option>
                </select>
            </div>
            <div class="form-group col-md-4">
                <label for="catalogue">도서종류</label>
                <select id="catalogue" name="catalogue" class="form-control">
                    <c:forEach items="${catalogues}" var="c">
                        <option value="${c}">${c}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group col-md-4">
                <label for="publisher">출판사</label>
                <select id="publisher" name="publisher" class="form-control">
                    <c:forEach items="${publishers}" var="p">
                        <option value="${p}">${p}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div class="form-row div-margin-bottom">
            <div class="form-group col-md-5">
                <label for="publishDate">출판일</label>
                <input type="date" class="form-control" id="publishDate" name="publishDate">
            </div>
            <div class="form-group col-md-3">
                <label for="price">가격</label>
                <input type="text" class="form-control" id="price" name="price" value="0">
            </div>
            <div class="form-group col-md-4">
                <label for="currency">단위</label>
                <select id="currency" name="currency" class="form-control">
                    <option value="원">원</option>
                    <option value="달러">달러</option>
                    <option value="위안">위안</option>
                    <option value="프랑">프랑</option>
                </select>
            </div>
        </div>

        <div class="form-group div-margin-bottom">
            <label for="description">설명</label>
            <textarea rows="4" type="text" class="form-control" id="description" placeholder="설명"></textarea>
        </div>

        <div style="margin-bottom: 30px;">
            <button class="btn btn-primary" type="submit" onclick="return validate();">등록</button>
            <a href="${pageContext.request.contextPath}/main.do?action=bookList">
                <button type="button" class="btn btn-link">목록으로 돌아가기</button>
            </a>
        </div>
    </form>
</div>
</body>
</html>
