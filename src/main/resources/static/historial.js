import { figuras, estado, historial } from "./variables.js";
import { dibujar, actualizarLista } from "./figuras.js";
import { guardar } from "./local.js";

function fechaActual() {
  return new Date().toLocaleString("es-ES");
}

function restaurar(textoFiguras) {
  const copia = JSON.parse(textoFiguras);
  figuras.length = 0;

  for (const figura of copia) {
    figuras.push(figura);
  }

  estado.figuraSeleccionada = null;
  actualizarLista();
  dibujar();
}

export function pintarHistorial() {
  const panel = document.getElementById("historial");
  const posicion = historial.entradas.length - historial.deshechas.length;

  let actual = posicion;
  if (actual >= historial.entradas.length) {
    actual = historial.entradas.length - 1;
  }

  let html = "<h4>Historial " + posicion + "/" + historial.entradas.length + "</h4><ul>";

  for (let i = 0; i < historial.entradas.length; i++) {
    const entrada = historial.entradas[i];
    let clase = "";

    if (i === actual) {
      clase = "bg-black text-white";
    } else if (i < posicion) {
      clase = "text-primary";
    } else {
      clase = "text-secondary";
    }

    html += '<li class="' + clase + '">' + entrada.fecha + " - " + entrada.texto + "</li>";
  }

  html += "</ul>";
  panel.innerHTML = html;
}
export function registrarCambio(texto) {
  const fecha = fechaActual();

  historial.deshechas.push({
    figuras: JSON.stringify(figuras),
    texto: texto,
    fecha: fecha
  });

  historial.rehechas.length = 0;
  historial.entradas.push({ texto: texto, fecha: fecha });

  guardar();
  pintarHistorial();
}
export function deshacer() {
  if (historial.deshechas.length === 0) {
    return;
  }

  const entrada = historial.deshechas.pop();

  historial.rehechas.push({
    figuras: JSON.stringify(figuras),
    texto: entrada.texto,
    fecha: entrada.fecha
  });

  restaurar(entrada.figuras);
  guardar();
  pintarHistorial();
}

export function rehacer() {
  if (historial.rehechas.length === 0) {
    return;
  }

  const entrada = historial.rehechas.pop();

  historial.deshechas.push({
    figuras: JSON.stringify(figuras),
    texto: entrada.texto,
    fecha: entrada.fecha
  });

  restaurar(entrada.figuras);
  guardar();
  pintarHistorial();
}

window.addEventListener("keydown", function (event) {
  if (event.ctrlKey === false) {
    return;
  }

  if (event.key === "z") {
    
      deshacer();
    }else if(event.key==="y"){
        rehacer()
    }
  
});