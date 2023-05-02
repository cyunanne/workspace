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

        // fetch("/setting/changeInfo", )
        // document.getElementById('hiddenSubmitBtn').click();
    });
}

// 비밀번호 수정 클릭
const passwdRow = document.querySelector('.row.passwd');
const passwdChangeRow = document.querySelector('.row.mod.passwd');
const passwdChangeBtn = document.querySelector('.row.passwd .mod-button');
const passwdCloseBtn = document.querySelector('.row.passwd .mod-button.close');
passwdChangeBtn.addEventListener('click', () => {
    passwdRow.classList.add('hidden');
    passwdChangeRow.classList.remove('hidden');
});
passwdCloseBtn.addEventListener('click', () => {
    passwdRow.classList.remove('hidden');
    passwdChangeRow.classList.add('hidden');
});