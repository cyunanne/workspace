const loginFrm = document.getElementById('loginFrm');
const memberEmail = document.querySelector('#loginFrm input[name="memberEmail"]');
const memberPw = document.querySelector('#loginFrm input[name="memberPw"]');


if(loginFrm != null) {
    // 로그인 시도
    loginFrm.addEventListener('submit', e => {
        // alert('login 시도');

        // form 태그 기본 이벤트 제거
        // e.preventDefault();

        // 이메일이 입력되지 않은 경우
        // 문자열.trim() : 문자열 앞뒤 공백 제거
        if(memberEmail.value.trim().length == 0) {
            alert('이메일을 입력해 주세요');

            memberEmail.value = ""; // 잘못 입력된 값 제거
            memberEmail.focus(); // 이메일 input 태그에 초점을 맞춤

            e.preventDefault(); // 제출 못하게하기
            return;
        }

        // 비밀번호가 입력되지 않은 경우
        if(memberPw.value.trim().length == 0) {
            alert('비밀번호를 입력해 주세요');

            memberPw.value = ""; // 잘못 입력된 값 제거
            memberPw.focus(); // 이메일 input 태그에 초점을 맞춤

            e.preventDefault(); // 제출 못하게하기
            return;
        }
    });
}

// 비동기로 이메일이 일치하는 회원의 닉네임 조회
function selectNickname(email) {

    // 지정된 주소로 GET방식 비동기 요청(ajax)
    // 전달하고자 하는 파라미터를 주소 뒤 쿼리스트링으로 추가
    fetch("/selectNickname?email=" + email) 
    .then(response => response.text()) // 요청에 대한 응답 객체(response)를 필요한 형태로 파싱
    .then(nickname => console.log(nickname)) // 첫 번째 then에서 파싱한 데이터를 이용한 동작 작성
    .catch(e => console.log(e)); // 예외 발생 시 처리할 내용을 작성
}

// 닉네임이 일치하는 회원의 전화번호 조회
const inputNickname = document.getElementById('inputNickname');
const btn1 = document.getElementById('btn1');
const result1 = document.getElementById('result1');

btn1.addEventListener('click', () => {
    // fetch() API를 이용해서 ajax(비동기통신)
    // fetch("요청주소") // GET방식 요청 (파라미터를 쿼리스트링으로 추가)
    // fetch("요청주소", {전달매개변수}) // POST방식 요청
    fetch("/selectMemberTel?nickname=" + inputNickname.value)
    .then(/*응답객체*/resp => /*데이터파싱*/resp.text()) // 익명함수
    // resp.text() : 응답객체 내용을 문자열로 변환하여 반환
    .then(/*파싱된데이터*/tel => {
        /*비동기 요청 후 수행할 코드*/
        result1.innerText = tel; // 조회 결과를 result1에 출력
    })
    .catch(e => console.log(e)); // 에러 발생 시 콘솔에 출력
});