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
document.querySelector('#button1').addEventListener('click', () => {
    alert('화살표 함수의 기본 형태');
});


// 2. 매개 변수 1개
// 익명함수 : function(e) {}
// 화살표 함수 : (e) => {} 또는 e => {} (소괄호 생략 가능)
document.querySelector('#button2').addEventListener('click', function(e) {
    e.target.style.backgroundColor = 'red';
});
document.querySelector('#button2').addEventListener('click', (e) => {
    e.target.style.backgroundColor = 'blue';
});
document.querySelector('#button2').addEventListener('click', e => {
    e.target.style.backgroundColor = 'green';
});

// 3. 매개변수가 2개 이상이거나 0개이면 소괄호 생략 불가능
// 인수 식이 필요합니다.ts(1135)
// document.querySelector('#button2').addEventListener('click', => {
//     e.target.style.backgroundColor = 'green';
// });
// document.querySelector('#button2').addEventListener('click', a, b => {
//     e.target.style.backgroundColor = 'green';
// });

// 4. {} return 생략
document.querySelector('#button4').addEventListener('click', () => {
    // 익명 함수
    printConsole( function(num) { return num * 10; } );
    // 화살표 함수
    printConsole( (num) => { return num * 100; } );
    // 매개변수 1개 -> () 생략
    printConsole( num => { return num * 1000; } );
    // 함수 정의 부분에 return 만 작성 -> {}, return 생략
    printConsole( num => num * 2 );

    // 5. 함수 정의 부분에 return 구문만 있지만 반환 자료형이 object 인 경우
    const temp = {price:100, name:'사탕'} // JS 객체(Object  타입)
    console.log(temp);
    console.log(typeof temp);
    // object를 변수에 저장해서 return 하는 건 가능
    // printConsole( (num) => { return temp; } );
    printConsole(num => temp);
    // return, {} 생략 상태에서 object를 직접 작성하면 오류 발생
    // -> js 객체의 {}를 함수 정의 부분의 {}로 인식해서 구문 오류 발생  
    // printConsole(num => {price:100, name:'사탕'});
});

function printConsole(fn) {
    //매개변수로 함수를 받아와 함수의 수행 결과를 콘솔에 출력
    console.log('-------------------');
    console.log(fn(2));
    console.log('-------------------');
}

// 화살표 함수 this 문제점
const button6 = document.querySelector('#button6');

// 클릭된 요소의 배경색을 검정색으로 변경(익명)
button6.addEventListener('click', function(e) {
    e.target.style.backgroundColor ='black';
});

// 클릭된 요소의 글자색을 흰색으로 변경(화살표)
button6.addEventListener('click', e => {
    e.target.style.color = 'white';
});

// 클릭된 요소의 폰트 크기를 25px로 증가(익명, e 사용x, this 사용o)
button6.addEventListener('click', function() {
    // 이벤트핸들러로 지정된 익명 함수의 this == 이벤트가 발생한 요소
    this.style.fontSize = '25px';
});

// 클릭된 요소의 테두리를 3px 실선 pink로 변경
button6.addEventListener('click', function() {
    this.style.border = '3px solid pink';
});

// caught TypeError: Cannot set properties of undefined (setting 'border')
button6.addEventListener('click', () => {
    console.log(this);
    // 화살표 함수에 this를 작성하면 이벤트가 발생한 요소가 아닌
    // 창 자체를 나타내는 window 객체가 반환된다
    this.style.border = '3px solid pink';
});