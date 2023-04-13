// 기본 함수
function clickCount(btn) {
    // 클릭할 대 마다 1씩 증가
    // 단, 20을 초과하면 다시 0으로 초기화
    const cur = Number(btn.innerText);
    btn.innerText = cur < 20 ? cur + 1 : 0; 
}

// 익명 함수
const target2 = document.getElementById('target2');
const btn2 = document.getElementById('btn2');
const colorList = ['red', 'orange', 'yellow', 'green', 'blue']; // Array
let index = -1;
btn2.addEventListener('click', function() {
    target2.style.color = colorList[++index % colorList.length];
});

///////////////////////////////////////////////////////////////////////////////////////

// 즉시 실행 함수

// 1. 속도적 우위

// 일반함수 : 선언 -> 정의 -> 실행 (4행)
function testFn() {
    console.log('일반 함수');
}
testFn();

// 즉시실행함수 (3행 ~> 행 수가 짧아서 조금 더 빠름)
(function(){
    console.log('즉시 실행 함수');
})();

// 2. 변수명 중복 회피
const str = '전역 변수';
(function(){
    const str = '지역 변수';
    console.log(str);
})();
console.log(str);

////////////////////////////////////////////////////////////////////////////////////////

// 화살표 함수

// 1. 기본 형태 : () => {}
// 익명함수 : function(){}
// 화살표 함수 : () => {}