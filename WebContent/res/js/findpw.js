function validate() {
    if (document.getElementById("id").value == "") {
        alert("아이디를 입력하세요.");
        document.getElementById("id").focus();
        return false;
    }
    if (document.getElementById("name").value == "") {
        alert("이름을 입력하세요.");
        document.getElementById("name").focus();
        return false;
    }
    return true;
}