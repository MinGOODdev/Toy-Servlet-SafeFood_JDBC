function validate() {
    if (document.getElementById("id").value == "") {
        alert("아이디를 입력하세요.");
        document.getElementById("id").focus();
        return false;
    }
    if (document.getElementById("pw").value == "") {
        alert("비밀번호를 입력하세요.");
        document.getElementById("pw").focus();
        return false;
    }
    return true;
}