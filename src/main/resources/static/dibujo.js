import { canvas, ctx, figuras, formulario, estado } from './variables.js';
import { guardar } from "./local.js";
import { registrarCambio } from "./historial.js";
import {
  crearContorno,
  dibujar,
  actualizarLista,
  ponerNombre,
  cambiarTamano,
  clickFigura
} from './figuras.js';

function mostrarOpciones() {
 

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
  document.getElementById('color').value = estado.color;
  document.getElementById('relleno').checked = estado.relleno;

  estado.creando = true;

  const casilla = document.getElementById('relleno');

  casilla.addEventListener('change', function () {
    if (casilla.checked) {
      estado.relleno = true;
    } else {
      estado.relleno = false;
    }
    guardar();
  });

  document.getElementById('color').addEventListener('change', function () {
    estado.color = document.getElementById('color').value;
    guardar();
  });
}
const botones = [
  document.getElementById('boton-cuadrado'),
  document.getElementById('boton-triangulo'),
  document.getElementById('boton-circulo'),
  document.getElementById('boton-estrella'),
  document.getElementById('boton-trazado'),
];

for (const boton of botones) {
  if (boton !== null) {
    boton.addEventListener('click', function () {
      estado.tipoFigura = boton.getAttribute('data-tipo');
      mostrarOpciones();
    });
  }
}

canvas.addEventListener('mousedown', function (event) {
  if (estado.creando == false) {
    return;
  }

    const posicionClick = clickFigura(event);
  const x = posicionClick.x;
  const y = posicionClick.y;
  const tamano = 100;

  const figura = {
    tipo: estado.tipoFigura,
    x: x,
    y: y,
    color: document.getElementById('color').value,
    tieneRelleno: estado.relleno,
  };

  ponerNombre(figura);

  if (estado.tipoFigura == 'Trazado') {
    figura.posicionPuntos = [{ x: x, y: y }];
    figura.tieneRelleno = false;
    estado.figuraEnCreacion = figura;
  } else {
    cambiarTamano(figura, tamano);
    estado.figuraSeleccionada = figura;
    estado.creando = false;
    formulario.innerHTML = '';
  }
      registrarCambio("Crear " + figura.nombre);
    figuras.push(figura);
  actualizarLista();
  dibujar();
  guardar();
});

canvas.addEventListener('mousemove', function (event) {
  if (estado.figuraEnCreacion == null) {
    return;
  }

   const posicionClick = clickFigura(event);
  estado.figuraEnCreacion.posicionPuntos.push({
    x: posicionClick.x,
    y: posicionClick.y,
  });
  dibujar();
});

window.addEventListener('mouseup', function () {
  if (estado.figuraEnCreacion == null) {
    return;
  }

   estado.figuraEnCreacion = null;
  estado.creando = false;
  formulario.innerHTML = "";
  dibujar();
  guardar();
});

canvas.addEventListener('wheel', function (event) {
  if (estado.figuraSeleccionada == null) {
    return;
  }
  event.preventDefault();

  let tamano = 100;
  if (estado.figuraSeleccionada.tipo == 'Cuadrado') {
    tamano = estado.figuraSeleccionada.grandaria;
  } else if (estado.figuraSeleccionada.tipo == 'Circulo') {
    tamano = estado.figuraSeleccionada.radios * 2;
  } else if (estado.figuraSeleccionada.tipo == 'Triangulo') {
    tamano = estado.figuraSeleccionada.y2 - estado.figuraSeleccionada.y1;
  } else if (estado.figuraSeleccionada.tipo == 'Estrella') {
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
    registrarCambio("Redimensionar " + estado.figuraSeleccionada.nombre);
    cambiarTamano(estado.figuraSeleccionada, tamano);
  dibujar();
  guardar();
});

canvas.addEventListener('click', function (event) {
  if (estado.creando === true) {
    return;
  }

  const posicionClick = clickFigura(event);

  ctx.lineWidth = 8;
  let encontrado = null;

  for (const figura of figuras) {
    const contorno = crearContorno(figura);

    let dentro;
    if (figura.tieneRelleno) {
      dentro = ctx.isPointInPath(contorno, posicionClick.x, posicionClick.y);
    } else {
      dentro = ctx.isPointInStroke(contorno, posicionClick.x, posicionClick.y);
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
