
// 수정 마우스오버 시 연필 모양 이미지 출력
const pencilImageList = document.querySelectorAll('.mod > img');
for(let img of pencilImageList) {
    img.style.display = 'none';
    img.nextElementSibling.addEventListener('mouseover', () => {
        img.style.display = 'inline-block';
    });
    img.nextElementSibling.addEventListener('mouseout', () => {
        img.style.display = 'none';
    });
}

const modTextList = document.getElementsByClassName('mod-text');
const saveTextList = document.getElementsByClassName('save-text');
const infoPrintList = document.querySelectorAll('.row > .info-print');
const infoInputList = document.querySelectorAll('.row > .info-input');

// 수정 클릭 시 해당 요소 input으로 변경
for(let i=0; i<modTextList.length; i++) {
    modTextList[i].addEventListener('click', () => {
        modTextList[i].style.display = 'none';
        saveTextList[i].style.display = 'inline-block';
        infoPrintList[i].style.display = 'none';
        infoInputList[i].style.display = 'inline-block';
    });
}

// 저장 클릭 시 해당 input요소 숨기고 제출
for(let i=0; i<saveTextList.length; i++) {
    saveTextList[i].style.display = 'none';
    saveTextList[i].addEventListener('click', () => {
        modTextList[i].style.display = 'inline-block';
        saveTextList[i].style.display = 'none';

        infoPrintList[i].style.display = 'inline-block';
        infoInputList[i].style.display = 'none';

        document.getElementById('hiddenSubmitBtn').click();
    });
}

/* Toggle */ 
const toggles = document.querySelectorAll('.toggle');
for(let t of toggles) {
    t.addEventListener('click', () => {
        t.classList.toggle('selected');
    });
}

/* Modal */
// 모달창 취소 버튼 기능 넣기
const cancelBtnList = document.getElementsByClassName('cancelBtn');
for(let btn of cancelBtnList) {
    btn.addEventListener('click', () => {
        modal.style.display = "none";
    });
}