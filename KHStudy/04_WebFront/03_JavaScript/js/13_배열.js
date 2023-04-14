// 배열 생성 확인
document.getElementById("btn1").addEventListener("click", () => {
    const arr1 = new Array();
    const arr2 = new Array(3);
    const arr3 = [];
    const arr4 = ['사과', '딸기', '바나나'];

    console.log(arr1);
    console.log(arr2);
    console.log(arr3);
    console.log(arr4);

    // 자료형 제한 없음 + 길이 제한 없음확인
    // - 배열에 지정된 인덱스가 없으면 자동으로 추가
    // - 중간에 인덱스를 건너 뛰면 건너 뛴 자리를 비워듬
    arr1[0] = '가나다'; // string
    arr1[2] = 100;      // number
    arr1[4] = true;     // boolean
    arr1[5] = {id:'user01', pw:'pass01'};
    console.log(arr1);
});

document.getElementById('btn2').addEventListener('click', () => {
    // 빈 배열 생성
    const arr = new Array(); // = [];

    // push() : 배열의 마지막 자식 요소로 추가
    arr.push('김밥');
    arr.push('삼겹살');
    arr.push('갈비');
    arr.push('냉면');
    console.log(arr); // 자식요소 추가 확인

    // unshift() : 배열의 첫번째 자식 요소로 추가
    arr.unshift('샌드위치');
    arr.unshift('햄버거');
    console.log(arr); // 자식요소 추가 확인

    // pop() : 배열의 마지막 요소 꺼내기
    console.log(arr.pop()); // 꺼낸 요소 확인
    console.log(arr);       // 남은 배열 확인

    // shift() : 배열의 첫번째 요소 꺼내기
    console.log(arr.shift()); // 꺼낸 요소 확인
    console.log(arr);         // 남은 배열 확인

    // sort() : 정렬
    console.log(arr.sort());

    const arr2 = [5, 2, 3, 4, 10];
    console.log(arr2.sort()); // 숫자 배열 정렬 시도 => 실패
    console.log(arr2.sort(function(a, b) { return a-b })); // 숫자 오름차순 정렬
    console.log(arr2.sort(function(a, b) { return b-a })); // 숫자 내림차순 정렬

    // indexOf('값') : 값이 배열에 있으면 인덱스 번호, 없으면 -1 반환
    console.log(arr.indexOf('갈비'));   // 0
    console.log(arr.indexOf('꽃등심')); // -1

    // toString(), join()
    console.log(arr.toString());
    console.log(arr.join('/'));

    // forEach()
    arr.forEach( function(value) {
        console.log("값 : " + value);
    });
    arr.forEach( value => {
        console.log("값2 : " + value);
    });
});