var str = "JS코드를 함수 내부가 아닌 JS파일 또는 script 태그에 바로 작성하면\
        HTML 렌더링 시 바로 수행된다."
console.log(str); // 자동완성 : clg

// 변수 선언 위치에 따른 구분
var num1 = 1; // 전역변수
num2 = 2;     // 전역변수
console.log("(전)num1 : " + num1);
console.log("(전)num2 : " + num2);

function testFn() {
    var num3 = 3; // function 지역변수
    num4 = 4;     // 전역변수

    console.log("함수 내부에서 선언된 (함)num3 : " + num3);
    console.log("함수 내부에서 선언된 (전)num4 : " + num4);

    if(1 ==1) {
        var num5 = 5; // function 지역변수
        num6 = 6;     // 전역변수
    }

    console.log("if문 종료 후 (if)num5 : " + num5);
    console.log("if문 종료 후 (if)num6 : " + num6);
}

testFn(); // 함수 호출

// num3 is not defined(존재하지 않거나 접근 불가능)
// console.log("함수 밖에서 (함)num3 : " + num3);

// js 코드 중간에 에러가 발생하면 같은 파일/함수 내 에러 이후 부분 코드가 해석되지 않는다.
console.log("함수 밖에서 (전)num4 : " + num4);

// console.log("함수 밖에서 (if)num5 : " + num5);
console.log("함수 밖에서 (if)num6 : " + num6);

// var 변수명 중복 확인
var testValue = 10;
console.log(testValue);
var testValue = 20;
console.log(testValue);

// let/const 확인
let let1 = 10;
// let let1 = 10; // 변수명 중복 X

function testFn2() {
    let let1 = 20;
    let let2 = 200;

    if(1==1) {
        let let1 = 30;
        let let3 = 300;
    }

    // console.log("if문 내부 let3 : " + let3); // let3 is not defined
}

testFn2();
// console.log("함수 내부에 선언한 let2 : " + let2); // let2 is not defined

// const 는 let 이랑 똑같은데 상수인 점만 다름
const temp = 999;
// temp = 1000; // signment to constant variable.

// var 의 호이스팅(hoisting) :
// 변수가 선언되기 전에 사용 가능하게 하는 기술
// error 가 발생하지 않고 undifined 로 출력됨
console.log(test);
var test = 1;
console.log(test);

// 자료형 확인
function typeTest() {
    const box = document.getElementById("typeBox");

    // undefined
    let temp; // 선언 후 값 초기화 X -> undefined
    box.innerHTML = "temp : " + typeof temp;

    // string : '', "" 둘 다 String 이다
    const name = "홍길동";
    box.innerHTML += "<br>name : " + name + " / " + typeof name;

    const gender = 'M';
    box.innerHTML += "<br>gender : " + gender + " / " + typeof gender;

    const phone = '010-1111-1111';
    box.innerHTML += "<br>phone : " + phone + " / " + typeof phone;

    // number
    const age = 20;
    const height = 166.3;
    box.innerHTML += "<br>age : " + age + " / " + typeof age;           // 정수
    box.innerHTML += "<br>height : " + height + " / " + typeof height;  // 실수

    // boolean
    box.innerHTML += "<br>true : " + true + " / " + typeof true;
    box.innerHTML += "<br>false : " + false + " / " + typeof false;

    // object-------------------------------------------------------------------------- //
    // js 의 배열, 초기화에 [] 사용
    const arr = [1, 2, 3];
    box.innerHTML += "<br>array : " + arr + " / " + typeof arr;
    
    // java의 객체 : class에 작성된 내용대로 메모리(heap)에 할당된 것
    // js의 객체 : {Key : Value} Map 형식, **Key는 무조건 String으로 고정**
    const user = {"id" : "user01", 'pw' : 'pass01', address : '서울시 중구' }
    box.innerHTML += "<br>user : " + user + " / " + typeof user;
    // 콘솔로 객체 출력 시 브라우저가 파악하기 쉽게 바꿔서 출력
    console.log(user);
    // 객체 value 출력방법 (1)
    box.innerHTML += "<br>user.id : " + user.id;
    box.innerHTML += "<br>user.pw : " + user.pw;
    box.innerHTML += "<br>user.address : " + user.address;
    // 객체 value 출력방법 (2)
    box.innerHTML += "<br>user['id'] : " + user['id'];
    box.innerHTML += "<br>user['pw'] : " + user['pw'];
    box.innerHTML += "<br>user['address'] : " + user['address'];
    // ------------------------------------------------------------------------------------ //

    // function(함수도 자료형이다~!!!)
    // 이렇게 선언된 함수는 이 라인이 실행되기 전까지 사용할 수 없다
    const sumFn = function(n1, n2){ return n1 + n2; };
    // 함수명만 작성 : 함수에 작성된 코드가 그대로 출력된다.
    box.innerHTML += "<br>sumFn : " + sumFn + " / " + typeof sumFn;
    // 함수명() 작성 : 함수 실행(함수 호출)
    box.innerHTML += "<br>sumFn(1, 2) : " + sumFn(1, 2);
    // doubleFn 함수 호출
    box.innerHTML += "<br>doubleFn(sumFn) : " + doubleFn(sumFn);
}

// 전달받은 함수의 결과를 2배로 반환하는 함수
function doubleFn(fn) {
    return fn(1, 2) * 2;
}