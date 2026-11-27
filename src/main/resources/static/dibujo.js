import { canvas, ctx, figuras, formulario, estado } from './variables.js';
import { guardar } from './local.js';
import { historialCrear, historialCambiar } from './historial.js';
import {
  crearContorno,
  dibujar,
  actualizarLista,
  ponerNombre,
  cambiarTamano,
  clickFigura,
} from './figuras.js';

function buscarFigura(posicion) {
  ctx.lineWidth = 8;
  let encontrado = null;

  for (const figura of figuras) {
    const contorno = crearContorno(figura);

    let dentro;
    if (figura.tieneRelleno) {
      dentro = ctx.isPointInPath(contorno, posicion.x, posicion.y);
    } else {
      dentro = ctx.isPointInStroke(contorno, posicion.x, posicion.y);
    }

    if (dentro) {
      encontrado = figura;
    }
  }

  ctx.lineWidth = 1;
  return encontrado;
}

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

  const selectorColor = document.getElementById('color');
  selectorColor.addEventListener('change', function () {
    estado.color = selectorColor.value;
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
  const posicionClick = clickFigura(event);

  if (estado.creando == true) {
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

    historialCrear(figuras.length, figura, 'Crear ' + figura.nombre);
    figuras.push(figura);

    actualizarLista();
    dibujar();
    guardar();
    return;
  }

  const figura = buscarFigura(posicionClick);

  if (figura !== null) {
    estado.figuraMoviendose = figura;
    estado.ultimoPunto = posicionClick;
    estado.yaRegistrado = false;
    estado.figuraSeleccionada = figura;
    actualizarLista();
    dibujar();
  }
});

canvas.addEventListener('mousemove', function (event) {
  const posicionClick = clickFigura(event);

  if (estado.figuraEnCreacion != null) {
    estado.figuraEnCreacion.posicionPuntos.push({
      x: posicionClick.x,
      y: posicionClick.y,
    });
    dibujar();
    return;
  }

  if (estado.figuraMoviendose == null) {
    return;
  }

  const figura = estado.figuraMoviendose;
  const movimientoX = posicionClick.x - estado.ultimoPunto.x;
  const movimientoY = posicionClick.y - estado.ultimoPunto.y;

  if (movimientoX === 0 && movimientoY === 0) {
    return;
  }

  if (estado.yaRegistrado === false) {
    historialCambiar(figuras.indexOf(figura), figura, 'Mover ' + figura.nombre);
    estado.yaRegistrado = true;
  }

  figura.x = figura.x + movimientoX;
  figura.y = figura.y + movimientoY;

  if (figura.tipo == 'Triangulo') {
    const tamano = figura.y2 - figura.y1;
    cambiarTamano(figura, tamano);
  }

  if (figura.tipo == 'Trazado') {
    for (const punto of figura.posicionPuntos) {
      punto.x = punto.x + movimientoX;
      punto.y = punto.y + movimientoY;
    }
  }

  estado.ultimoPunto = posicionClick;
  dibujar();
});

window.addEventListener('mouseup', function () {
  if (estado.figuraEnCreacion != null) {
    estado.figuraEnCreacion = null;
    estado.creando = false;
    formulario.innerHTML = '';
    dibujar();
    guardar();
    return;
  }

  if (estado.figuraMoviendose != null) {
    if (estado.yaRegistrado === true) {
      guardar();
    }
    estado.figuraMoviendose = null;
    estado.ultimoPunto = null;
    estado.yaRegistrado = false;
  }
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
  historialCambiar(
    figuras.indexOf(estado.figuraSeleccionada),
    estado.figuraSeleccionada,
    'Redimensionar ' + estado.figuraSeleccionada.nombre,
  );
  cambiarTamano(estado.figuraSeleccionada, tamano);
  dibujar();
  guardar();
});

canvas.addEventListener('click', function (event) {
  if (estado.creando === true) {
    return;
  }

  const posicionClick = clickFigura(event);
  estado.figuraSeleccionada = buscarFigura(posicionClick);
  actualizarLista();
  dibujar();
});
