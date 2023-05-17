const insertBtn = document.querySelector("#insertBtn");

if(insertBtn != null) {
    // 글쓰기 버튼 클릭시
    insertBtn.addEventListener("click", () => {
        // JS BOM 객체 중 location 이용
        // location.href = "주소" 
        // => 해당 주소 요청(GET 방식)

        location.href = `/board2/${location.pathname.split('/')[2]}/insert`;
    });
}

const boardSearch = document.querySelector('#boardSearch');
const searchKey = document.querySelector('#searchKey');
const searchQuery = document.querySelector('#searchQuery');
const options = document.querySelectorAll('#searchKey > option');

// 검색창에 이전 검색 기록 남겨놓기
(()=>{
    const params = new URL(location.href).searchParams;

    const key = params.get("key");
    const query = params.get("query");

    if(key != null) { // 검색을 했을 때
        searchQuery.value = query; // 검색어를 화면에 출력
        
        // option 태그를 하나씩 순차접근해서 value가 key랑 같으면 selected 속성을 추가
        for(let op of options) {
            if(op.value == key) {
                op.selected = true;
            }
        }
    }
})();

// 검색어 입력 없이 제출된 경우
boardSearch.addEventListener('submit', e => {
    if(searchQuery.value.trim().length == 0) { // 검색어 미입력
        e.preventDefault(); // form 기본 이벤트 제거

        // location.pathname : 쿼리스트링을 제외한 실제 주소
        location.href = location.pathname; // 해당 게시판 1페이지로 이동
    }
});