import { canvas, figuras, idCanvas, estado, historial } from './variables.js';

const clave = 'paint-' + idCanvas;

export function guardar() {
  const datos = {
    figuras: figuras,
    ancho: canvas.width,
    alto: canvas.height,
    color: estado.color,
    relleno: estado.relleno,
    tipoFigura: estado.tipoFigura,
    deshacer: historial.deshacer,
    rehacer: historial.rehacer,
    entradas: historial.entradas,
  };

  localStorage.setItem(clave, JSON.stringify(datos));
}

export function cargar() {
  const texto = localStorage.getItem(clave);

  if (texto === null) {
    return false;
  }

  const datos = JSON.parse(texto);
  figuras.length = 0;

  for (const figura of datos.figuras) {
    figuras.push(figura);
  }

  canvas.width = datos.ancho || 800;
  canvas.height = datos.alto || 800;
  estado.color = datos.color || 'red';
  estado.relleno = datos.relleno || false;
  estado.tipoFigura = datos.tipoFigura || '';

  historial.deshacer = datos.deshacer || [];
  historial.rehacer = datos.rehacer || [];
  historial.entradas = datos.entradas || [];

  return true;
}
