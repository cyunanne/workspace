// input 이벤트 : 입력과 관련된 모든 행동
// input, textarea 등 입력 가능한 요소에 값이 입력되면 인식
// 단점: 언어떤 키가 눌렸는지는 알 수 없다. (keyDown, keyUp 이벤트에서 확인 가능)
document.getElementById('content').addEventListener('input', e => {
    const length = e.target.value.length;
    const count =  document.getElementById('count');

    if( length > 100) {
        count.classList.add('error');
    } else {
        count.classList.remove('error');
    }

    // 요소.classList.toggle('클래스명');
    // 요소에 클래스가 없으면 추가 (true 반환)
    // 요소에 클래스가 있으면 제거 (false 반환)

    count.innerText = length;
});