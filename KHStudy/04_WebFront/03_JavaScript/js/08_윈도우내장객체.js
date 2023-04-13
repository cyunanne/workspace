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