// innerText 로 내용 읽어오기
function getBoxText() {
    // HTML 문서 내에서 id 속성값이 test1 인 요소를 얻어와 text1 변수에 대입
    // var(variable : 변수)
    // var test1 : 변수 test1 을 선언 (자료형 선언 X)
    // -> js 는 대입되는 값에 따라 변수 자료형이 정해진다
    var test1 = document.getElementById('test1');

    // test1에 저장된 요소의 내용을 얻어와 콘솔창에 출력
    console.log(test1.innerText); //콘솔 창 출력
}

// innerText 로 내용 변경하기
function setBoxText() {
    var test1 = document.getElementById('test1');

    // test1 에 저장된 요소의 내용(content)를 변경
    test1.innerText = '<b>innerText로<br>변경된 내용입니다</b>';
}

// innerHTML 로 읽어오기
function getBoxElement() {
    // 문서 내에서 id 가 test2 인 요소를 얻어와 temp 변수에 저장
    var temp = document.getElementById('test2')

    // temp 요소에 작성된 태그, 속성, 내용을 얻어와 console 에 출력
    console.log(temp.innerHTML);

    /* (참고) 감싸고 있는 요소 전체 얻어오기 */
    console.log(temp.outerHTML);
}

// innerHTML 로 변경하기
function setBoxElement() {
    var temp = document.getElementById('test2')
    temp.innerHTML = '<b>innerHTML로<br>변경된 내용입니다</b>';
}

// innerHTML 응용
function add() {
    // 1) id 가 area1 인 요소를 얻어와 변수에 저장
    var a = document.getElementById('area1');
    
    // 2) #area1 요소의 이전 내용에 새로운 내용(요소)을 추가
    //  -> 이전 내용 + 새로운 내용(누적)
    a.innerHTML += '<div class="box2"></div>';
}

// confirm 함수
function fnConfirm() {
    var check = confirm('배경을 분홍색으로 바꾸시겠습니까?');

    if(check) { // 확인 클릭 시
        document.querySelector('body').style.backgroundColor = 'pink';
    } else { // 츼소 클릭 시
        document.querySelector('body').style.backgroundColor = 'white';
    }
}

// prompt 함수
function fnPrompt() {
    var input = prompt('이름을 입력해주세요');
    var result;
    if(input == null) {
        result = '취소되었습니다';
    } else {
        result = input + "님 환영합니다";
    }
    alert(result);
}