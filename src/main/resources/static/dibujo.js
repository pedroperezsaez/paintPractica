import { canvas, ctx, figuras, formulario, estado } from "./variables.js";
import { crearContorno, dibujar, actualizarLista, ponerNombre, cambiarTamano } from "./figuras.js";

function mostrarOpciones() {
  estado.relleno = false;

  formulario.innerHTML = `
    <div>
      <input type="checkbox" id="relleno">
      <label for="relleno">Con relleno</label>
    </div>
    <div>
      <select id="color">
        <option value="red">Rojo</option>
        <option value="lime">Verde</option>
        <option value="yellow">Amarillo</option>
        <option value="dodgerblue">Azul</option>
      </select>
    </div>
  `;

  estado.creando = true;

  const casilla = document.getElementById("relleno");

  casilla.addEventListener("change", function() {
    if (casilla.checked) {
      estado.relleno = true;
    } else {
      estado.relleno = false;
    }
  });
}

const botones = [
  document.getElementById("boton-cuadrado"),
  document.getElementById("boton-triangulo"),
  document.getElementById("boton-circulo"),
  document.getElementById("boton-estrella"),
  document.getElementById("boton-trazado")
];

for (const boton of botones) {
  if (boton !== null) {
    boton.addEventListener("click", function() {
      estado.tipoFigura = boton.getAttribute("data-tipo");
      mostrarOpciones();
    });
  }
}

canvas.addEventListener("mousedown", function(event) {
  if (estado.creando == false) {
    return;
  }

  const x = event.offsetX;
  const y = event.offsetY;
  const tamano  = 100;;

  const figura = {
    tipo: estado.tipoFigura,
    x: x,
    y: y,
    color: document.getElementById("color").value,
    tieneRelleno: estado.relleno
  };

  ponerNombre(figura);

  if (estado.tipoFigura == "Trazado") {
    figura.posicionPuntos = [{ x: x, y: y }];
    figura.tieneRelleno = false;
    estado.figuraEnCreacion = figura;
  } else {
    cambiarTamano(figura, tamano);
    estado.figuraSeleccionada = figura;
    estado.creando = false;
    formulario.innerHTML = "";
  }

  figuras.push(figura);
  actualizarLista();
  dibujar();
});

canvas.addEventListener("mousemove", function(event) {
  if (estado.figuraEnCreacion == null) {
    return;
  }

  estado.figuraEnCreacion.posicionPuntos.push({ x: event.offsetX, y: event.offsetY });
  dibujar();
});

window.addEventListener("mouseup", function() {
  if (estado.figuraEnCreacion == null) {
    return;
  }

  estado.figuraEnCreacion = null;
  estado.creando = false;
  formulario.innerHTML = "";
  dibujar();
});

canvas.addEventListener("wheel", function(event) {
  if (estado.figuraSeleccionada == null) {
    return;
  }
  event.preventDefault();

  let tamano = 100;
  if (estado.figuraSeleccionada.tipo == "Cuadrado") {
    tamano = estado.figuraSeleccionada.grandaria;
  } else if (estado.figuraSeleccionada.tipo == "Circulo") {
    tamano = estado.figuraSeleccionada.radios * 2;
  } else if (estado.figuraSeleccionada.tipo == "Triangulo") {
    tamano = estado.figuraSeleccionada.y2 - estado.figuraSeleccionada.y1;
  } else if (estado.figuraSeleccionada.tipo == "Estrella") {
    tamano = estado.figuraSeleccionada.grandaria;
  }

  if (event.deltaY < 0) {
    tamano = tamano + 10;
  } else {
    tamano = tamano - 10;
  }

  if (tamano < 10) {
    tamano = 10;
  }

  cambiarTamano(estado.figuraSeleccionada, tamano);
  dibujar();
});

canvas.addEventListener("click", function(event) {
  if (estado.creando === true) {
    return;
  }

  ctx.lineWidth = 8;
  let encontrado = null;

  for (const figura of figuras) {
    const contorno = crearContorno(figura);

    let dentro;
    if (figura.tieneRelleno) {
      dentro = ctx.isPointInPath(contorno, event.offsetX, event.offsetY);
    } else {
      dentro = ctx.isPointInStroke(contorno, event.offsetX, event.offsetY);
    }

    if (dentro) {
      encontrado = figura;
    }
  }

  ctx.lineWidth = 1;

  estado.figuraSeleccionada = encontrado;
  actualizarLista();
  dibujar();
});