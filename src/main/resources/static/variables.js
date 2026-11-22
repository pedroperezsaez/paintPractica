export const canvas = document.getElementById("canvas");
export const ctx = canvas.getContext("2d");
export const figuras = [];
export const formulario = document.getElementById("form");

export const estado = {
  relleno: false,
  creando: false,
  figuraSeleccionada: null,
  tipoFigura: "",
  figuraEnCreacion: null,
  color: "red"
};
export const historial = {
  deshechas: [],
  rehechas: [],
  entradas: []
};


const datosCanvas = document.getElementById("canvas-datos");
export let idCanvas = "0";

if (datosCanvas !== null) {
  idCanvas = datosCanvas.getAttribute("data-id");
}