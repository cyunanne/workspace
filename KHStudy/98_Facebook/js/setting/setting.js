const pencilImages = document.querySelectorAll('.row > .mod > img');
for(let img of pencilImages) {
    img.style.display = 'none';
    img.nextElementSibling.addEventListener('mouseover', () => {
        img.style.display = 'inline';
    });
    img.nextElementSibling.addEventListener('mouseout', () => {
        img.style.display = 'none';
    });
}