const boardLike = document.getElementById('boardLike');

boardLike.addEventListener('click', e => {

    // 로그인 여부 검사
    if(loginMemberNo == "") {
        alert('로그인 후 이용해주세요');
        return;
    }

    // 기존에 좋아요 X(빈 하트, 0), O(꽉찬 하트, 1)
    let check;
    // contains("클래스명") : 클래스가 있으면 true, 없으면 false 반환
    if(e.target.classList.contains("fa-regular")) { // 좋아요 X(빈 하트, 0)
        check = 0;
    } else { // 좋아요 O(꽉찬 하트, 1)
        check = 1;
    }

    // ajax로 서버로 제출할 파라미터를 모아둔 JS 객체
    const data = {"boardNo" : boardNo, "memberNo" : loginMemberNo, "check" : check};

    // ajax 코드 작성
    fetch("/board/like", {
        method : "POST",
        headers : {"Content-Type" : "application/json"},
        body : JSON.stringify(data)
    })
    .then(response => response.text()) // 응답 객체를 필요한 형태로 파싱
    .then(count => { // 파싱된 데이터를 받아서 처리하는 코드 작성
        // console.log("count : " + count);

        if(count == -1) { // INSERT, DELETE 실패 시
            console.log("좋아요 처리 실패");
            return;
        }

        // toggle() : 클래스가 있으면 없애고, 없으면 추가
        e.target.classList.toggle("fa-regular");
        e.target.classList.toggle("fa-solid");

        // 현재 게시글의 좋아요 수 갱신
        e.target.nextElementSibling.innerText = count;
    })
    .catch(err => {
        console.log("예외 발생");
        console.log(err);
    }) // 예외 발생 시 처리하는 부분
});

// 게시글 수정 버튼 클릭 시
document.getElementById("updateBtn").addEventListener('click', e => {
    location.href = location.pathname.replace('board', 'board2')
                    + '/update' + location.search;
    // => /board2/{boardCode}/{boardNo}/update?cp={cp} (GET)
});

// 게시글 삭제 버튼이 클릭 되었을 때
document.getElementById('deleteBtn').addEventListener('click', () => {
    if(confirm('정말 삭제 하시겠습니까?')) {
        location.href = location.pathname.replace('board', 'board2') + '/delete';
        // => /board2/1/2006/delete (GET)

        // 삭제서비스 호출 성공시 redirect:/board/{boardCode}
        // + RedirectAttributes 이용 "삭제 되었습니다" alert 출력

        // 삭제서비스 호출 실패시 redirct:/board/{boardCode}/{boardNo}
        // + RedirectAttributes 이용 "삭제 실패" alert 출력
    }
});