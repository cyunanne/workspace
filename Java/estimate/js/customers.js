document.getElementById('modifyBtn').addEventListener('click', e => {
    const modifyBtn = e.target;
    modifyBtn.style.display = 'none';
    modifyBtn.nextElementSibling.style.display = 'block';

    const inputList = document.querySelectorAll('.info1.data > li > input');
    for(let i=0; i<inputList.length; i++) {
        if(i==5 || i==11) continue; // 최근거래내역, 명함이미지 경로는 수정 불가
        inputList[i].disabled = false;
        inputList[i].style.border = "1px solid #ccc";
    }
});

// 수정완료 버튼 클릭
document.getElementById('modCompleteBtn').addEventListener('click', e => {
    const modifyBtn = e.target;
    modifyBtn.style.display = 'none';
    modifyBtn.previousElementSibling.style.display = 'block';
    
    const inputList = document.querySelectorAll('.info1.data > li > input');
    for(let input of inputList) {
        input.disabled = true;
        input.style.border = "none";
    }
});

document.querySelector('.info1.data > li > input').addEventListener('input', e => {
    document.querySelector(".customer-name > span.title").innerText = e.target.value;
});

const emptyContent = document.getElementById('emptyContent');
const content = document.getElementById('content');
content.style.display = "none";

// 검색결과 목록 추가
for(let i=0; i<10; i++) {
    const searchResult = document.getElementById('searchResult');
    let item = document.createElement('div');
    item.classList.add('searchResultItem');

    item.addEventListener('click', () => {
        emptyContent.style.display = "none";
        content.style.display = "grid";
    });

    searchResult.append(item);
}

// 검색결과 예시 아이템에 이벤트 추가 (나중에 삭제)
document.querySelector('.searchResultItem').addEventListener('click', () => {
    emptyContent.style.display = "none";
    content.style.display = "grid";
});

// 거래처 삭제 버튼 클릭
document.getElementById('deleteBtn').addEventListener('click', e => {
    const check = confirm("정말로 삭제하시겠습니까?\n삭제 후에는 복구할 수 없습니다.");
    if(check) { 
        alert("삭제되었습니다.");
        // 상세정보 페이지 초기화
        emptyContent.style.display = "flex";
        content.style.display = "none";
        // #검색 결과에서 삭제
    }
    else alert("취소되었습니다.");
});