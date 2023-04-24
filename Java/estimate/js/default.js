// 엔터 누를 때 폼 제출 방지
const formList = document.getElementsByTagName('form');
for(let form of formList) {
    form.addEventListener("keydown", (e) => {
        if(e.keyCode == 13) {
            e.preventDefault();
        }
    });
};