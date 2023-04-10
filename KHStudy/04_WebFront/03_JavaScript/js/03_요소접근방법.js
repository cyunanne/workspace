// id 로 접근하기
function accessId() { // 함수 선언
    // 함수 정의

    // id 가 div1 인 요소의 배경색을 얻어와
    // 배경색이 red 이면 yellow 로 변경
    // 배경색이 red 가 아니면 red 로 변경

    // 1) id 가 div1 인 요소를 얻어와 변수 div1 에 저장
    var div1 = document.getElementById("div1");

    // 2) div1 의 배경색을 변수 bgColor 에 저장
    /* js 에서 css 관련 속성을 다룰 때에는 style 을 이용함 */
    var bgColor = div1.style.backgroundColor;

    // 3) 배경색이 red 일 때, 아닐 때에 대한 코드 작성
    if (bgColor == "red") { /* js 는 문자열 비교시에도 비교연산자 사용 */
        div1.style.backgroundColor = "yellow";
    } else {
        div1.style.backgroundColor = "red";
    }
}

// class 로 접근하기
function accessClass() {
    
    // class 가 같은 요소를 모두 찾아 배열로 반환
    var div2Arr = document.getElementsByClassName("div2");

    // 배열.length : 배열 길이
    // 배열[i] : i번 인덱스 요소
    for (var i=0; i<div2Arr.length; i++) {
        div2Arr[i].style.backgroundColor = "rgb(20, 150, " + (i*100) + ")";
    }
}

// name 으로 접근하기
function accessName() {

    // 1) name 이 hobby 인 요소를 모두 얻어와 hobbyList 에 저장
    var hobbyList = document.getElementsByName("hobby");

    // 2) 체크된 체크박스의 value 를 저장할 변수
    //    + 개수를 세기 위한 변수 선언
    var result = "";
    var count = 0;

    // 3) hobbyList 요소 반복 접근
    for (var i=0; i<hobbyList.length; i++) {
        /******** radio / checkbox 전용 속성 : checked ********/
        // 해당 요소가 체크되어 있으면 true, 아니면 false
        if (hobbyList[i].checked) {
            result += hobbyList[i].value + " ";
            count++;
        }
    }

    // 4) #div3 에 내용 출력
    document.getElementById("div3").innerText = result;
}

// tag 로 접근하기
function accessTagName() {

    var arr = document.getElementsByTagName("li");

    // 각 li 요소에 작성된 색으로 글자색 변경
    for (var i=0; i<arr.length; i++) {
        arr[i].style.color = arr[i].innerText;
    }
}

// CSS 선택자로 접근하기
function cssTest() {

    // querySelector("CSS 선택자") : 요소 1개 선택 (여러 요소가 존재하면 첫 번째 요소 선택)
    document.querySelector("#cssDiv").style.border = "3px solid red"

    // 여러 요소 선택(첫 번째 요소만 선택되는지 확인)
    document.querySelector("#cssDiv > div").style.fontSize = "30px";

    // qeurySelectorAll("CSS 선택자") : 일치하는 모든 요소 선택(배열 반환)
    var arr = document.querySelectorAll("#cssDiv > div");

    // 선택된 모든 요소의 배경색 변경
    for(var i=0; i<arr.length; i++) {
        arr[i].style.backgroundColor = "orange";
    }
}

// 입력 버튼을 눌렀을 때 입력한 내용을 채팅창에 출력하기
function readValue() {
    
    // 채팅 출력에 사용되는 변수 선언
    var container = document.getElementById("chattingContainer");
    var input = document.querySelector("#chattingInput");

    // input 의 값이 비어있지 않다면
    // 문자열.trim() : 문자열 양쪽 공백 제거
    if (input.value.trim().length > 0) {
       // input 에 입력된 값을 읽어와 container 에 알맞은 형태로 누적
        container.innerHTML += "<p><span>" + input.value + "</span></p>";

        // 채팅 내용 출력 후 컨테이너의 스크롤을 맨 밑으로 이동
        // 요소.scrollTop        : 요소 내부 현재 스크롤 위치를 반환
        // 요소.scrollTop = 위치 : 스크롤을 지정된 위치로 이동
        // 요소.scrollHeight     : 스크롤 전체 높이를 반환
        container.scrollTop = container.scrollHeight;
    }

    // 입력된 input 의 값을 지우기
    input.value = ""; // 빈 문자열을 대입

    // 입력 후 다시 input 에 포커스 맞추기
    input.focus();
}

// input 태그에 엔터가 눌러졌을 때
function inputEnter() {

    // console.log(window.event.key); // 현재 눌러진 키를 반환

    if(window.event.key == "Enter") readValue();
}