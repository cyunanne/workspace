// 거래처 정보 수정 버튼 클릭
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
    document.getElementsByTagName('textarea')[0].disabled = false;
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
    document.getElementsByTagName('textarea')[0].disabled = true;
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

// #최근 견적 내역 목록 클릭 시 해당 견적서 상세 페이지로 이동
const recentEstimateList = document.querySelectorAll("table tr:not(tr:first-child)");
for(let estimate of recentEstimateList) {
    if(estimate.children[0].innerText != "") {
        estimate.addEventListener('click', () => {
            window.location.href = "index.html";
        });
    }
}

// 신규 거래처 추가 버튼 클릭 시 추가를 위한 팝업
document.querySelector('#emptyContent > button').addEventListener('click', () => {
    // let options = "toolbar=no, scrollbars=no, resizable=no, status=no, menubar=no, width=600, top=0,left=0";
    let options = "width=600, height=800 top=100, left=100";
    window.open('customers-new-popup.html', '_blank', options);
});