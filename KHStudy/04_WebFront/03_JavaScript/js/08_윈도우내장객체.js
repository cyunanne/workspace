// window.setTimeout(함수, 지연시간(ms));
document.getElementById("testBtn").addEventListener("click", () => {
    console.log('0초');
    window.setTimeout(() => {
        console.log('1초');
    }, 1000);
    window.setTimeout(() => {
        console.log('2초');
    }, 2000);

    // setTimeout()같은 시간 관련 함수들은
    // 여러 번 작성된 경우 앞의 코드가 실행되기를 기다리지 않음
    // => 비동기로 동작
});

// window.setInterval(함수, 지연시간(ms));
const loadingTime = document.getElementById("loadingTime");
window.setInterval(() => {
    loadingTime.innerText = Number(loadingTime.innerText) + 1;
}, 1000);

// 시계 만들기
const clock = document.getElementById("clock");

// 현재 시간을 #clock에 출력
function currentTime() {
    const now = new Date(); // 현재 시간을 저장한 Date 객체 생성
    
    // 현재 시/분/초를 변수에 저장
    let hour = now.getHours();
    let minute = now.getMinutes();
    let second = now.getSeconds();

    // 시/분/초가 10미만(두자리가 아닐 경우)일 경우 앞에 0 추가
    if(hour < 10) hour = "0" + hour;
    if(minute < 10) minute = "0" + minute;;
    if(second < 10) second = "0" + second;

    // 백틱(`)을 이용한 문자열 조합
    clock.innerText = `${hour} : ${minute} : ${second}`;
}
// 처음에 화면에 시간이 표시되도록 currentTime() 호출
currentTime();
// setInteval을 사용하여 currentTime() 함수를 호출
let time = window.setInterval(currentTime, 1000);

// window.clearInterval(setInterval이 저장된 함수);
const stop = document.getElementById("stop").addEventListener("click", () => {
    clearInterval(time);
});

// 팝업창 열기
const openPopup1 = document.getElementById('openPopup1');
const openPopup2 = document.getElementById('openPopup2');
const openPopup3 = document.getElementById('openPopup3');

openPopup1.addEventListener("click", () => {
    // widnow.open(url);
    // js 경로 X -> 브라우저에 보이는 주소
    window.open("07_함수.html"); // 새 탭에서 열기
});

openPopup2.addEventListener("click", () => {
    // 새 창에서 최소한의 팝업창 요건을 가진 상태로 열기
    // window.open("url", "_blank", "popup");
    window.open("07_함수.html", "_blank", "popup");
});

openPopup3.addEventListener("click", () => {
    // 새 창의 크기를 너비 400px, 높이 600px로 지정
    // 열리는 위치는 위에서 100px, 왼쪽에서 100px 떨어진 위치
    // window.open("url", "_blank", "popup");
    window.open("07_함수.html", "_blank", "width=400, height=600, top=100, left=100"); // 옵션 기본단위 : px
});

// 팝업창으로 값 전달
document.getElementById("sendPopup").addEventListener("click", () => {
    window.open("09_팝업.html", "popupWindow", "width=500, height=300");
});

