// 계산 버튼 클릭 시 input 태그에 작성된 값을 모두 읽어와 합한 결과를 alert로 출력
document.getElementById("calc").addEventListener("click", () => {
    const numbers = document.getElementsByClassName("in");
    let result = 0;
    for(n of numbers) {
        result += Number(n.value);
    }
    alert(result);
});

// 요소 만들기 : document.createElement("태그명")

// 요소의 클래스 목록 확인하기 : 요소.classList
// 요소에 class 추가 : 요소.classList.add("클래스명")
// 요소에 class 제거 : 요소.classList.remove("클래스명")

// 요소에 속성, 값 추가 : 요소.setAttribute("속성명", 값)
// 요소에 속성 제거 : 요소.removeAttribute("속성명")

// 부모요소.append(자식요소) : 부모 요소의 마지막 자식으로 자식 요소를 추가(덧붙이기) 
// 부모요소.prepend(자식요소) : 부모 요소의 첫번째 자식으로 자식 요소를 추가(덧붙이기) 
// A.after(B) : A의 다음 요소로 B를 추가
// A.before(B) : A의 이전 요소로 B를 추가

document.getElementById("add").addEventListener("click", () => {
    // div 요소 만들기
    const div = document.createElement("div");
    // child.className = "row";
    div.classList.add("row"); // div에 row 클래스 추가
    
    // span 요소 만들기
    const span = document.createElement("span");
    span.classList.add("remove-row"); // span에 remove-row 클래스 추가
    span.innerHTML = "&times;"; // span에 &times; 내용 추가(innerHTML 사용)
    // 만들어진 span 요소에 이벤트리스너 추가
    span.addEventListener("click", e => {
        e.target.parentElement.remove();
    });

    // input 요소 만들기
    const input = document.createElement("input");
    // input.type = "number";
    // input.className = "in";
    input.classList.add("in"); // input에 in 클래스 추가
    input.setAttribute("type", "number") // input에 type="number" 속성 추가
    
    div.append(input); // div의 자식으로 input 추가
    div.append(span); // div의 자식으로 span 추가
    document.querySelector(".container").append(div);
});

// 삭제 버튼 동작 테스트

// class="remove-row"인 요소 중 첫 번째 요소
document.querySelector(".remove-row").addEventListener("click", e => {
    // 이벤트가 발생한 요소 (클릭된 버튼)
    console.log(e.target); 
    // 이벤트가 발생한 요소의 부모요소 (row)
    console.log(e.target.parentElement); 
    // 이벤트가 발생한 요소의 부모요소를 제거(remove())
    e.target.parentElement.remove();
});