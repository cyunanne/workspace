const table = document.querySelector(".table-body > table");
const rowClass = ["col-no", "col-name", "col-size", "col-unit", "col-count", "col-unitcost", "col-totalcost", "col-etc"];
const rowType = ["text", "text", "text", "text", "text", "text", "text", "text"];

for(let i=0; i<3; i++) {
    addRow();
}

function addRow(prevSibling) {
    const row = document.createElement("tr");
    const rownum = document.createElement("input");
    const rownumValue = table.children.length + 1;
    rownum.setAttribute("type", "hidden");
    rownum.setAttribute("value", rownumValue);
    rownum.setAttribute("name", "rownum");
    row.append(rownum);

    for(let j=0; j<9; j++) {
        const cell = document.createElement("td");
        const input = document.createElement("input");
        cell.classList.add(rowClass[j]);
        input.setAttribute("type", rowType[j]);
        if( rowClass[j] == "col-totalcost" ) { 
            input.disabled = true;
        }
        cell.append(input); 
        row.append(cell);
    }

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

// function removeLastRow() {
//     table.lastElementChild.remove();
// }

table.addEventListener("input", () => {
    // const rowCount = table.children.length;
    const lastRow = table.lastElementChild.children;
    
    // tr 첫 번째 요소인 hidden rownum 제외
    for(let j=1; j<=rowClass.length; j++) {
        if(lastRow[j].firstElementChild.value.length > 0) {
            addRow();
            return;
        }
    }

    // for(let i=0; i<rowCount; i++) {
    //     const lastRow = table.lastElementChild.children;
    //     const secondLastRow = table.lastElementChild.previousSibling.children;

    //     for(let j=1; j<rowClass.length; j++) {
    //         if(lastRow[j].firstElementChild.value.length > 0) {
    //             addRow();
    //             return;
    //         }
    //     }
        
    //     for(let j=1; j<rowClass.length; j++) {
    //         if(secondLastRow[j].firstElementChild.value.length > 0) {
    //             return;
    //         }
    //     }

    //     if( table.children.length > 3 ) 
    //         removeLastRow();
    // }
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