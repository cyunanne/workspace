const table = document.querySelector(".table-body > table");
const rowClass = ["col-no", "col-name", "col-size", "col-unit", "col-count", "col-unitcost", "col-totalcost", "col-etc"];
const rowType = ["text", "text", "text", "text", "text", "text", "text", "text"];

for(let i=0; i<3; i++) {
    addRow();
}

function addRow(prevSibling) {
    const row = document.createElement("tr");
    // const row = table.addRow();
    const rownum = document.createElement("input");
    const rownumValue = table.children.length + 1;
    rownum.setAttribute("type", "hidden");
    rownum.setAttribute("value", rownumValue);
    rownum.setAttribute("name", "rownum");
    row.append(rownum);

    for(let j=0; j<9; j++) {
        const cell = row.insertCell(j);
        const input = document.createElement("input");
        cell.classList.add(rowClass[j]);
        input.setAttribute("type", rowType[j]);
        if( rowClass[j] == "col-totalcost" ) { 
            input.disabled = true;
        }
        cell.append(input); 
    }
    console.log("1");

    row.children[5].addEventListener("input", (e) => {
        const cur = Number(e.target.value.replaceAll(',', ''));
        if (cur == 0 || isNaN(cur)) e.target.value = null;
        else e.target.value = cur.toLocaleString();
        changeCost(e.target.parentElement.parentElement.firstElementChild.value - 1);
    });  
    row.children[6].addEventListener("input", (e) => {
        const cur = Number(e.target.value.replaceAll(',', ''));
        if (cur == 0 || isNaN(cur)) e.target.value = null;
        else e.target.value = cur.toLocaleString();
        changeCost(e.target.parentElement.parentElement.firstElementChild.value - 1);
    });
    row.children[8].addEventListener("keyup", (e) => {
        if(e.keyCode == 13 && e.target.value == '+') {
            addRow(e.target.parentElement.parentElement);
        } else if(e.keyCode == 13 && e.target.value == '-') {
            removeRow(e.target.parentElement.parentElement);
        }
    });

    if(prevSibling == null) table.append(row);
    else prevSibling.after(row);
}

function removeRow(prevSibling) {
    if(prevSibling.nextElementSibling != null)
        prevSibling.nextElementSibling.remove();
}

table.addEventListener("input", () => {
    const lastRow = table.lastElementChild.children;
    
    // tr 첫 번째 요소인 hidden rownum 제외
    for(let j=1; j<=rowClass.length; j++) {
        if(lastRow[j].firstElementChild.value.length > 0) {
            addRow();
            return;
        }
    }
});

function changeCost(i) {
    const countList = document.querySelectorAll("[class $= 'count'] > input");
    const unitcostList = document.querySelectorAll("[class $= 'unitcost'] > input");
    const totalcostList = document.querySelectorAll("[class $= 'totalcost'] > input");

    const count = Number(countList[i].value.replaceAll(',', ''));
    const unitcost = Number(unitcostList[i].value.replaceAll(',', ''));
    const totalcost = (count * unitcost).toLocaleString();
    totalcostList[i].value = totalcost == 0 ? null : totalcost;

    let sum = 0;
    for(let j=0; j<totalcostList.length; j++) {
        sum += Number(totalcostList[j].value.replaceAll(',', ''));
    }
    document.getElementById("estimateCost").value = "￦ " + sum.toLocaleString();
}

document.getElementById("tempSaveBtn").addEventListener("click", (e) => {
    document.getElementById("tempSavePopup").style.display = "block";
});

document.querySelector("#tempSavePopup > .cover").addEventListener("click", (e) => {
    const popup = document.querySelector("#tempSavePopup > .cover > .popup");

    // 팝업 내부 클릭 시 동작 안함
    if( e.clientX > popup.getBoundingClientRect().left 
        && e.clientX < popup.getBoundingClientRect().right
        && e.clientY > popup.getBoundingClientRect().top 
        && e.clientY < popup.getBoundingClientRect().bottom ) {
            return;
    }

    // 팝업 외부 클릭 시 팝업 닫힘
    document.getElementById("tempSavePopup").style.display = "none";
});

// 팝업 라인 생성
for(let i=0; i<10; i++) {
    const popup = document.querySelector("#tempSavePopup >.cover >.popup > .temp-save-main");
    const row = document.createElement("div");
    row.classList.add("temp-save-row");
    for(let j=0; j<6; j++) {
        const col = document.createElement("div");
        col.classList.add("temp-save-col");
        row.append(col);
    }
    const img = document.createElement("img");
    img.setAttribute("src", "../images/delete-button.png");
    img.setAttribute("alt", "delete");
    img.addEventListener("click", (e) => {
        const result = confirm("정말로 삭제하시겠습니까? 복구할 수 없습니다.");
        if(result == true) {
            for(let div of e.target.parentElement.parentElement.children) {
                if( div != e.target.parentElement ) div.innerText = "";
                else e.target.style.display = "none";
            }
        }
    });
    row.lastElementChild.append(img);
    popup.append(row);
}

document.getElementById('loadBtn').addEventListener("click", (e) => {
    e.target.previousElementSibling.click();
});