
function changeFontSize(size) {
    document.getElementById("titulo").style.fontSize = size + "px";
}
var r, g, b;

function randomColor() {
    r = Math.floor((Math.random() * 255) + 0);
    g = Math.floor((Math.random() * 255) + 0);
    b = Math.floor((Math.random() * 255) + 0);
}
function setColor() {
    randomColor();
    document.body.style.backgroundColor = "rgb(" + r + "," + g + "," + b + ")";
    randomColor();
    document.getElementById("titulo").style.color = "rgb(" + r + "," + g + "," + b + ")";
    randomColor();
    document.getElementById("btnRandom").style.backgroundColor = "rgb(" + r + "," + g + "," + b + ")";
    randomColor();
    document.getElementById("btnRandom").style.color = "rgb(" + r + "," + g + "," + b + ")";
    randomColor();
    document.getElementById("tam").style.backgroundColor = "rgb(" + r + "," + g + "," + b + ")";
    randomColor();
    document.getElementById("tam").style.color = "rgb(" + r + "," + g + "," + b + ")";
}

