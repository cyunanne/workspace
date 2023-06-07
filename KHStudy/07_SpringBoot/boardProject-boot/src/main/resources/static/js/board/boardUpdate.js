// 미리보기 관련 요소 모두 얻어오기
const preview = document.getElementsByClassName('preview'); // img 5개
const inputImage = document.getElementsByClassName('inputImage'); // file 5개
const deleteImage = document.getElementsByClassName('delete-image'); // x버튼 5개

// 게시글 수정 시 삭제된 이미지의 순서를 기록할 Set 객체 생성
// x버튼 클릭 시 순서를 한 번만 저장하는 용도
const deleteSet = new Set(); // 순서x, 중복x

// => 위에 얻어온 요소들의 개수가 같음 = 인덱스가 일치함
for(let i=0; i<inputImage.length; i++) {
    inputImage[i].addEventListener('change', e => {
        // 파일이 선택되거나, 선택 후 취소 되었을 때

        const file = e.target.files[0]; // 선택된 파일의 데이터
        
        if(file != undefined) { // 파일이 선택되었을 때
            const reader = new FileReader(); // 파일을 읽는 객체
            reader.readAsDataURL(file); // 지정된 파일을 읽은 후 result 변수에 URL 형식으로 저장
            reader.onload = e => { // 파일을 다 읽은 후 수행
                preview[i].setAttribute("src", e.target.result);
                deleteSet.delete(i); // 이미지가 성공적으로 읽어지면 deleteSet에서 삭제
            };
        } else { // 선택 후 취소되었을 때
            // 선택된 파일 없음 -> 미리보기 삭제
            preview[i].removeAttribute("src");
        }
    });

    // 미리보기 삭제 버튼(x버튼)
    deleteImage[i].addEventListener('click', e => {

        // 미리보기 이미지가 있을 경우
        if(preview[i].getAttribute("src") != null) {

            // 미리보기 삭제
            preview[i].removeAttribute("src");
            // input type="file"의 value는 ""(빈칸)만 대입 가능
            inputImage[i].value = ""; 
            // deteteSet에 삭제된 이미지 순서(i) 추가
            deleteSet.add(i);
        }
    });
}

// 게시글 등록 시 제목, 내용 작성 여부 검사
const boardUpdateFrm = document.getElementById('boardUpdateFrm');
const boardTitle = document.querySelector('[name="boardTitle"]');
const boardContent = document.querySelector('[name="boardContent"]');

boardUpdateFrm.addEventListener('submit', e => {
    if(boardTitle.value.trim().length == 0) {
        alert("제목을 입력해주세요");
        boardTitle.value = "";
        boardTitle.focus();
        e.preventDefault();
        return;
    }

    if(boardContent.value.trim().length == 0) {
        alert("내용을 입력해주세요");
        boardContent.value = "";
        boardContent.focus();
        e.preventDefault();
        return;
    }

    // deleteSet에 저장된 값을 "1,2,3" 형태로 변형해서 input type="hidden" 태그에 저장
    // Array.from(deleteSet) : Set -> Array로 변경
    // JS 배열은 string에 대입되거나 출력될 때 "요소,요소,요소" 형태의 문자열을 반환한다.
    document.querySelector('[name="deleteList"]').value = Array.from(deleteSet);
});