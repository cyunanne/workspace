/* Modal */
const modal = document.querySelector('.modal');
const modalBody = document.querySelector('.modal-body');

const accountDisableBtn = document.getElementById('accountDisableBtn');
const disableContinueBtn = document.getElementById('disableContinueBtn');

const modalContent1 = document.getElementById('modalContent1');
const modalContent2 = document.getElementById('modalContent2');
const modalBottom1 = document.getElementById('modalBottom1');
const modalBottom2 = document.getElementById('modalBottom2');

// 모달창 띄우기
accountDisableBtn.addEventListener('click', () => {
    modal.style.display = 'block';
    modalContent1.style.display = 'block';
    modalBottom1.style.display = 'block';
    modalContent2.style.display = 'none';
    modalBottom2.style.display = 'none';
});

// 모달창 외부 클릭 시 창 닫힘
modal.addEventListener("click", (e) => {
    // 팝업 내부 클릭 시 동작 안함
    if( e.clientX > modalBody.getBoundingClientRect().left 
        && e.clientX < modalBody.getBoundingClientRect().right
        && e.clientY > modalBody.getBoundingClientRect().top 
        && e.clientY < modalBody.getBoundingClientRect().bottom ) {
            return;
    }

    // 팝업 외부 클릭 시 팝업 닫힘
    modal.style.display = "none";
});

// 두 번째 모달 창으로 이동
disableContinueBtn.addEventListener('click', () => {
    modalContent1.style.display = 'none';
    modalBottom1.style.display = 'none';
    modalContent2.style.display = 'block';
    modalBottom2.style.display = 'block';
});

