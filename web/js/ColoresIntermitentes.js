var pause = 30;
var step = 1;
var r = 255;
var g = 0;
var b = 0;
var estadoOcultar = true;


function bodyEvent(event) {
    if (event.key === "o" || event.key === "O") {
        ocultar(estadoOcultar);
        estadoOcultar = !estadoOcultar;
    }
}

function ocultar() {
    if (oculto) {
        document.getElementById("rgbProgress").style.visibility = 'hidden';
    } else {
        document.getElementById("rgbProgress").style.visibility = 'visible';
    }
}
(function () {
    gen();
})();
// https://stackoverflow.com/questions/951021/what-is-the-javascript-version-of-sleep
function sleep(ms) {
    return new Promise(resolve => setTimeout(resolve, ms));
}
async function gen() {
    /*while (true) {
     randomColor();
     await sleep(1000);
     }*/
    while (true) {
        // sube el verde
        for (g = 0; g <= 255; g = g + step) {
            printRgb();
            document.body.style.backgroundColor = "rgb(" + r + "," + g + "," + b + ")";
            await sleep(pause);
        }
        g = g - step;
        // baja el rojo
        for (r = 255; r >= 0; r = r - step) {
            printRgb();
            document.body.style.backgroundColor = "rgb(" + r + "," + g + "," + b + ")";
            await sleep(pause);
        }
        r = r + step;
        // sube el azul
        for (b = 0; b <= 255; b = b + step) {
            printRgb();
            document.body.style.backgroundColor = "rgb(" + r + "," + g + "," + b + ")";
            await sleep(pause);
        }
        b = b - step;
        // baja el verde
        for (g = 255; g >= 0; g = g - step) {
            printRgb();
            document.body.style.backgroundColor = "rgb(" + r + "," + g + "," + b + ")";
            await sleep(pause);
        }
        g = g + step;
        // sube el rojo
        for (r = 0; r <= 255; r = r + step) {
            printRgb();
            document.body.style.backgroundColor = "rgb(" + r + "," + g + "," + b + ")";
            await sleep(pause);
        }
        r = r - step;
        // baja el azul
        for (b = 255; b >= 0; b = b - step) {
            printRgb();
            document.body.style.backgroundColor = "rgb(" + r + "," + g + "," + b + ")";
            await sleep(pause);
        }
        b = b + step;
    }
}
function printRgb() {
    document.getElementById("r").innerHTML = "Rojo: " + r;
    document.getElementById("g").innerHTML = "Verde: " + g;
    document.getElementById("b").innerHTML = "Azul: " + b;
    document.getElementById("rp").value = r;
    document.getElementById("gp").value = g;
    document.getElementById("bp").value = b;
}
