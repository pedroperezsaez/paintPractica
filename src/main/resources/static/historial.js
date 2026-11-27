import { figuras, estado, historial } from './variables.js';
import { dibujar, actualizarLista } from './figuras.js';
import { guardar } from './local.js';

function copiarFigura(figura) {
  return JSON.parse(JSON.stringify(figura));
}

function copiarFiguras(lista) {
  return JSON.parse(JSON.stringify(lista));
}

function fechaActual() {
  return new Date().toLocaleString('es-ES');
}

export function pintarHistorial() {
  const divHistorial = document.getElementById('historial');

  let html = '<h4>Historial</h4>';
  html +=
    '<p>' +
    historial.deshacer.length +
    '/' +
    historial.entradas.length +
    '</p>';
  html += '<ul class="list-unstyled">';

  const posicionActual = historial.entradas.length - historial.deshacer.length;

  let actual = posicionActual;
  if (actual >= historial.entradas.length) {
    actual = historial.entradas.length - 1;
  }

  for (let i = 0; i < historial.entradas.length; i++) {
    const entrada = historial.entradas[i];

    let clase = '';
    if (i === actual) {
      clase = 'bg-black text-white';
    } else if (i < posicionActual) {
      clase = 'text-primary';
    } else {
      clase = 'text-secondary';
    }

    html +=
      '<li class="' +
      clase +
      '">' +
      entrada.fecha +
      ' - ' +
      entrada.texto +
      '</li>';
  }

  html += '</ul>';
  divHistorial.innerHTML = html;
}

function anadirEntrada(texto) {
  const fecha = fechaActual();
  historial.rehacer.length = 0;
  historial.entradas.push({ texto: texto, fecha: fecha });
  return fecha;
}

export function historialCrear(posicion, figura, texto) {
  const fecha = anadirEntrada(texto);
  historial.deshacer.push({
    tipo: 'crear',
    posicion: posicion,
    figura: copiarFigura(figura),
    texto: texto,
    fecha: fecha,
  });
  guardar();
  pintarHistorial();
}

export function historialBorrar(posicion, figura, texto) {
  const fecha = anadirEntrada(texto);
  historial.deshacer.push({
    tipo: 'borrar',
    posicion: posicion,
    figura: copiarFigura(figura),
    texto: texto,
    fecha: fecha,
  });
  guardar();
  pintarHistorial();
}

export function historialCambiar(posicion, figuraAntes, texto) {
  const fecha = anadirEntrada(texto);
  historial.deshacer.push({
    tipo: 'cambiar',
    posicion: posicion,
    figuraAntes: copiarFigura(figuraAntes),
    figuraDespues: null,
    texto: texto,
    fecha: fecha,
  });
  guardar();
  pintarHistorial();
}

export function historialBorrarTodo(figurasAntes, texto) {
  const fecha = anadirEntrada(texto);
  historial.deshacer.push({
    tipo: 'borrarTodo',
    figurasAntes: copiarFiguras(figurasAntes),
    figurasDespues: null,
    texto: texto,
    fecha: fecha,
  });
  guardar();
  pintarHistorial();
}

export function deshacer() {
  if (historial.deshacer.length === 0) {
    return;
  }

  const entrada = historial.deshacer.pop();

  if (entrada.tipo === 'crear') {
    const figuraQuitada = figuras.splice(entrada.posicion, 1)[0];
    entrada.figura = copiarFigura(figuraQuitada);
    historial.rehacer.push(entrada);
  }

  if (entrada.tipo === 'borrar') {
    figuras.splice(entrada.posicion, 0, copiarFigura(entrada.figura));
    historial.rehacer.push(entrada);
  }

  if (entrada.tipo === 'cambiar') {
    entrada.figuraDespues = copiarFigura(figuras[entrada.posicion]);
    figuras[entrada.posicion] = copiarFigura(entrada.figuraAntes);
    historial.rehacer.push(entrada);
  }

  if (entrada.tipo === 'borrarTodo') {
    entrada.figurasDespues = copiarFiguras(figuras);
    figuras.length = 0;
    for (const figura of entrada.figurasAntes) {
      figuras.push(copiarFigura(figura));
    }
    historial.rehacer.push(entrada);
  }

  estado.figuraSeleccionada = null;
  actualizarLista();
  dibujar();
  guardar();
  pintarHistorial();
}

export function rehacer() {
  if (historial.rehacer.length === 0) {
    return;
  }

  const entrada = historial.rehacer.pop();

  if (entrada.tipo === 'crear') {
    figuras.splice(entrada.posicion, 0, copiarFigura(entrada.figura));
    historial.deshacer.push(entrada);
  }

  if (entrada.tipo === 'borrar') {
    const figuraQuitada = figuras.splice(entrada.posicion, 1)[0];
    entrada.figura = copiarFigura(figuraQuitada);
    historial.deshacer.push(entrada);
  }

  if (entrada.tipo === 'cambiar') {
    entrada.figuraAntes = copiarFigura(figuras[entrada.posicion]);
    figuras[entrada.posicion] = copiarFigura(entrada.figuraDespues);
    historial.deshacer.push(entrada);
  }

  if (entrada.tipo === 'borrarTodo') {
    entrada.figurasAntes = copiarFiguras(figuras);
    figuras.length = 0;
    for (const figura of entrada.figurasDespues) {
      figuras.push(copiarFigura(figura));
    }
    historial.deshacer.push(entrada);
  }

  estado.figuraSeleccionada = null;
  actualizarLista();
  dibujar();
  guardar();
  pintarHistorial();
}

window.addEventListener('keydown', function (event) {
  if (event.ctrlKey === false) {
    return;
  }

  if (event.key === 'z') {
    deshacer();
  } else if (event.key === 'y') {
    rehacer();
  }
});
