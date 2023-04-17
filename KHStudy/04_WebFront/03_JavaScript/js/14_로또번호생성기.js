const balls = document.querySelector("#container").children;
document.getElementById("createBtn").addEventListener("click", () => {
    const numbers = [];
    while(numbers.length < 6) {
        const num = Math.floor(Math.random() * 45) + 1;
        if( numbers.includes(num) ) continue;
        numbers.push(num);
    }

    numbers.sort((a, b) => a - b);
    for( let i=0; i<6; i++ ) {
        balls[i].innerText = numbers[i];
    }
});