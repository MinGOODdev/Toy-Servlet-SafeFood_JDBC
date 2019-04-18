function validate() {
    if (document.getElementById("isbn").value == "") {
        alert("도서번호를 입력하세요");
        document.getElementById("isbn").focus();
        return false;
    }
    if (document.getElementById("title").value == "") {
        alert("도서 제목을 입력하세요");
        document.getElementById("title").focus();
        return false;
    }
    if (document.getElementById("author").value == "") {
        alert("저자를 입력하세요");
        document.getElementById("author").focus();
        return false;
    }
    return true;
}