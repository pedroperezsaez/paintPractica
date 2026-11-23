import { figuras, formulario, estado } from './variables.js';
import { dibujar, actualizarLista, cambiarTamano } from './figuras.js';
import { guardar, cargar } from "./local.js";
import { registrarCambio, pintarHistorial } from "./historial.js";

const datosFiguras = document.getElementById('datosFiguras');

if (cargar() === false) {
  const textoFiguras = datosFiguras.textContent.trim();

  if (textoFiguras !== '') {
    const figurasGuardadas = JSON.parse(textoFiguras);

    for (const figura of figurasGuardadas) {
      figuras.push(figura);
    }
  }
}

actualizarLista();
dibujar();
pintarHistorial();

const botonBorrarFigura = document.getElementById('boton-borrar-figura');

if (botonBorrarFigura != null) {
  botonBorrarFigura.addEventListener('click', function () {
    if (estado.figuraSeleccionada == null) {
      return;
    }
    registrarCambio("Borrar " + estado.figuraSeleccionada.nombre);
    const posicion = figuras.indexOf(estado.figuraSeleccionada);
    figuras.splice(posicion, 1);

    estado.figuraSeleccionada = null;
    actualizarLista();
    guardar()
    dibujar();
  });
}

const botonBorrarTodo = document.getElementById('boton-borrar-todo');

if (botonBorrarTodo != null) {
  botonBorrarTodo.addEventListener('click', function () {
    registrarCambio("Borrar todo");
    figuras.length = 0;
    estado.figuraSeleccionada = null;
    actualizarLista();
    guardar();
    dibujar();
  });
}

const botonEditarFigura = document.getElementById('boton-editar-figura');
const formularioEditar = document.getElementById('form-editar');

if (botonEditarFigura != null) {
  botonEditarFigura.addEventListener('click', function () {
    if (estado.figuraSeleccionada == null) {
      return;
    }

    estado.creando = false;
    formulario.innerHTML = '';

    let tamanoActual = 100;
    if (estado.figuraSeleccionada.tipo == 'Cuadrado') {
      tamanoActual = estado.figuraSeleccionada.grandaria;
    } else if (estado.figuraSeleccionada.tipo == 'Circulo') {
      tamanoActual = estado.figuraSeleccionada.radios * 2;
    } else if (estado.figuraSeleccionada.tipo == 'Triangulo') {
      tamanoActual =
        estado.figuraSeleccionada.y2 - estado.figuraSeleccionada.y1;
    } else if (estado.figuraSeleccionada.tipo == 'Estrella') {
      tamanoActual = estado.figuraSeleccionada.grandaria;
    }

    formularioEditar.innerHTML = `
      <div class="mb-3">
        <label for="tamano-editar" class="form-label">Tamaño</label>
        <input type="number" class="form-control"
               id="tamano-editar" value="${tamanoActual}">
      </div>

      <div class="form-check mb-3">
        <input class="form-check-input" type="checkbox" id="relleno-editar">
        <label class="form-check-label" for="relleno-editar">Con relleno</label>
      </div>

      <div class="mb-3">
        <label for="color-editar" class="form-label">Color</label>
        <select class="form-select" id="color-editar">
          <option value="red">Rojo</option>
          <option value="lime">Verde</option>
          <option value="yellow">Amarillo</option>
          <option value="dodgerblue">Azul</option>
        </select>
      </div>

      <button type="button" id="boton-aplicar" class="btn btn-primary w-100">
        Aplicar
      </button>
    `;

    document.getElementById('relleno-editar').checked =
      estado.figuraSeleccionada.tieneRelleno;
    document.getElementById('color-editar').value =
      estado.figuraSeleccionada.color;

    document
      .getElementById('boton-aplicar')
      .addEventListener('click', function () {
        const tamano =
          Number(document.getElementById('tamano-editar').value) || 100;
                  registrarCambio("Editar " + estado.figuraSeleccionada.nombre);
        const conRelleno = document.getElementById('relleno-editar').checked;

        cambiarTamano(estado.figuraSeleccionada, tamano);
        estado.figuraSeleccionada.tieneRelleno = conRelleno;
        estado.figuraSeleccionada.color =
          document.getElementById('color-editar').value;
          guardar()
        formularioEditar.innerHTML = '';
        dibujar();
      });
  });
}

const botonGuardar = document.getElementById('form-guardar');

if (botonGuardar !== null) {
  botonGuardar.addEventListener('submit', function () {
    const datosCanvas = document.getElementById('canvas-datos');
    const canvasId = datosCanvas.getAttribute('data-id');

    for (const figura of figuras) {
      figura.canvasId = Number(canvasId);
    }

    document.getElementById('figurasGuardar').value = JSON.stringify(figuras);
    document.getElementById('numObjetos').value = figuras.length;
  });
}

const botonMisCanvas = document.getElementById('boton-mis-canvas');
const botonOtrosCanvas = document.getElementById('boton-otros-canvas');

if (botonMisCanvas !== null && botonOtrosCanvas !== null) {
  botonMisCanvas.addEventListener('click', function () {
    document.getElementById('lista-mis-canvas').style.display = 'block';
    document.getElementById('lista-otros-canvas').style.display = 'none';
    botonMisCanvas.classList.add('activa');
    botonOtrosCanvas.classList.remove('activa');
  });

    botonOtrosCanvas.addEventListener('click', function () {
      document.getElementById('lista-otros-canvas').style.display = 'block';
      document.getElementById('lista-mis-canvas').style.display = 'none';
      botonOtrosCanvas.classList.add('activa');
      botonMisCanvas.classList.remove('activa');
    });
  }


const enlacesCanvas = document.querySelectorAll(
  '#lista-mis-canvas span.nombre-canvas',
);
let contador = 1;

for (const nombre of enlacesCanvas) {
  if (nombre.textContent.trim() === 'Nuevo canvas') {
    nombre.textContent = 'Canvas ' + contador;
    contador++;
  }
}
