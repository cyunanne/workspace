const table = document.querySelector(".table-body > table");
const rowClass = ["col-no", "col-name", "col-size", "col-unit", "col-count", "col-unitcost", "col-totalcost", "col-etc"];
const rowType = ["text", "text", "text", "text", "number", "number", "number", "text"];

for(let i=0; i<3; i++) {
    addRow();
}

function addRow() {
    const row = document.createElement("tr");
    for(let j=0; j<8; j++) {
        const cell = document.createElement("td");
        const input = document.createElement("input");
        cell.classList.add(rowClass[j]);
        input.setAttribute("type", rowType[j]);
        cell.append(input);
        row.append(cell);
    }
    table.append(row);
}

function removeLastRow() {
    table.lastElementChild.remove();
}

table.addEventListener("keyup", () => {
    const lastRow = table.lastElementChild.children;

    let check = false;
    for(let col of lastRow) {
        if(col.firstElementChild.value.length > 0) {
            check = true;
        }
    }

    if(check) addRow();
    if(!check && table.children.length > 3) removeLastRow();
});