export const canvas = document.getElementById("canvas");
export const ctx = canvas.getContext("2d");
export const figuras = [];
export const formulario = document.getElementById("form");

export const estado = {
  relleno: false,
  creando: false,
  figuraSeleccionada: null,
  tipoFigura: "",
  figuraEnCreacion: null
};