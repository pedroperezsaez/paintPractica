const canvas = document.getElementById("canvas");
const ctx = canvas.getContext("2d");

const figuras = [];
let relleno = false;
let creando = false;
let figuraSeleccionada = null;
let tipoFigura = "";
let inicioX = 0;
let inicioY = 0;
let finX = 0;
let finY = 0;
let tamanoInicial = 100;
let figuraEnCreacion = null;

const formulario = document.getElementById("form");

function mostrarOpciones() {
  relleno = false;

  formulario.innerHTML = `
    <div>
      <input type="checkbox" id="relleno">
      <label for="relleno">Con relleno</label>
    </div>
    <div>
      <input type="number" id="tamano" placeholder="Tamaño">
    </div>
  `;

  creando = true;

  const casilla = document.getElementById("relleno");

  casilla.addEventListener("change", function() {
    if (casilla.checked) {
      relleno = true;
    } else {
      relleno = false;
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
      tipoFigura = boton.getAttribute("data-tipo");
      mostrarOpciones();
    });
  }
}

function ajustarFigurarArrastrando(figura,tamanoPorDefecto,esFinal){
    let tamano=Math.max(Math.abs(finX-inicioX), Math.abs(finY-inicioY))
    if (esFinal && tamano < 1) {
  tamano = tamanoPorDefecto;
}
      if (figura.tipo === "Cuadrado") {
    figura.x = Math.min(inicioX, finX);
    figura.y = Math.min(inicioY, finY);
    figura.grandaria = tamano;
  } else if (figura.tipo === "Circulo") {
    figura.x = (inicioX + finX) / 2;
    figura.y = (inicioY + finY) / 2;
    figura.radios = tamano / 2;
  } else if (figura.tipo === "Triangulo") {
    figura.x = (inicioX + finX) / 2;
    figura.y = (inicioY + finY) / 2;
    figura.x1 = figura.x;
    figura.y1 = figura.y - tamano / 2;
    figura.x2 = figura.x + tamano / 2;
    figura.y2 = figura.y + tamano / 2;
    figura.x3 = figura.x - tamano / 2;
    figura.y3 = figura.y + tamano / 2;
  }
   else if (figura.tipo === "Estrella") {
    figura.x = (inicioX + finX) / 2;
    figura.y = (inicioY + finY) / 2;
    figura.grandaria = tamano;
}

}

canvas.addEventListener("mousedown", function(event) {
  if (creando == false) {
    return;
  }
  console.log("TIPO:", tipoFigura, "CREANDO:", creando);
  inicioX = event.offsetX;
  inicioY = event.offsetY;
  finX = inicioX;
  finY = inicioY;
  tamanoInicial = Number(document.getElementById("tamano").value) || 100;

  figuraEnCreacion = {
    tipo: tipoFigura,
    x: inicioX,
    y: inicioY,
    color: "red",
    tieneRelleno: relleno
  };

  if (tipoFigura === "Trazado") {
    figuraEnCreacion.posicionPuntos = [{ x: inicioX, y: inicioY }];
    figuraEnCreacion.tieneRelleno = false;
  }

  figuras.push(figuraEnCreacion);
  ajustarFigurarArrastrando(figuraEnCreacion, tamanoInicial, false);
  dibujar();
});

canvas.addEventListener("mousemove", function(event) {
  if (figuraEnCreacion == null) {
    return;
  }

  finX = event.offsetX;
  finY = event.offsetY;

  if (figuraEnCreacion.tipo === "Trazado") {
    figuraEnCreacion.posicionPuntos.push({ x: finX, y: finY });
  } else {
    ajustarFigurarArrastrando(figuraEnCreacion, tamanoInicial, false);
  }

  dibujar();
});

window.addEventListener("mouseup", function() {
  if (figuraEnCreacion == null) {
    return;
  }

  if (figuraEnCreacion.tipo !== "Trazado") {
    ajustarFigurarArrastrando(figuraEnCreacion, tamanoInicial, true);
  }

  figuraEnCreacion = null;
  creando = false;
  formulario.innerHTML = "";
  dibujar();
});

   function crearContorno(figura) {
  const contorno = new Path2D();

  if (figura.tipo == "Cuadrado") {
    contorno.rect(figura.x, figura.y, figura.grandaria, figura.grandaria);
  } else if (figura.tipo == "Circulo") {
    contorno.arc(figura.x, figura.y, figura.radios, 0, 2 * Math.PI);
  } else if (figura.tipo == "Triangulo") {
    contorno.moveTo(figura.x1, figura.y1);
    contorno.lineTo(figura.x2, figura.y2);
    contorno.lineTo(figura.x3, figura.y3);
    contorno.closePath();
  } else if (figura.tipo == "Estrella") {
    const radio = figura.grandaria / 2;
    const radioInterior = radio / 2;

    for (let i = 0; i < 10; i++) {
      const angulo = (Math.PI / 5) * i - Math.PI / 2;

      let distancia;
      if (i % 2 == 0) {
        distancia = radio;
      } else {
        distancia = radioInterior;
      }

      const xPunto = figura.x + Math.cos(angulo) * distancia;
      const yPunto = figura.y + Math.sin(angulo) * distancia;

      if (i === 0) {
        contorno.moveTo(xPunto, yPunto);
      } else {
        contorno.lineTo(xPunto, yPunto);
      }
    }
    contorno.closePath();
  } else if (figura.tipo == "Trazado") {
    for (let i = 0; i < figura.posicionPuntos.length; i++) {
      const punto = figura.posicionPuntos[i];

      if (i === 0) {
        contorno.moveTo(punto.x, punto.y);
      } else {
        contorno.lineTo(punto.x, punto.y);
      }
    }
  }

  return contorno;
}

function dibujar() {
  ctx.clearRect(0, 0, canvas.width, canvas.height);

  for (const figura of figuras) {
    const contorno = crearContorno(figura);

    ctx.fillStyle = figura.color;
    ctx.strokeStyle = figura.color;

    if (figura.tieneRelleno) {
      ctx.fill(contorno);
    } else {
      ctx.stroke(contorno);
    }

    if (figura === figuraSeleccionada) {
      ctx.strokeStyle = "blue";
      ctx.lineWidth = 3;
      ctx.stroke(contorno);
      ctx.lineWidth = 1;
    }
  }
}

 const datosFiguras = document.getElementById("datosFiguras");
const textoFiguras = datosFiguras.textContent.trim();

if (textoFiguras !== "") {
const figurasGuardadas = JSON.parse(textoFiguras);

  for (const figura of figurasGuardadas) {
    figuras.push(figura);
  }
}

dibujar();
   





canvas.addEventListener("click", function(event) {
  if (creando === true) {
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
  figuraSeleccionada = encontrado;
  dibujar();
});

const botonBorrarFigura = document.getElementById("boton-borrar-figura");

if (botonBorrarFigura != null) {
  botonBorrarFigura.addEventListener("click", function() {
    if (figuraSeleccionada == null) {
      return;
    }

    const posicion = figuras.indexOf(figuraSeleccionada);
    figuras.splice(posicion, 1);

    figuraSeleccionada = null;
    dibujar();
  });
}
const botonEditarFigura = document.getElementById("boton-editar-figura");
const formularioEditar = document.getElementById("form-editar");

if (botonEditarFigura != null) {
  botonEditarFigura.addEventListener("click", function() {
    if (figuraSeleccionada == null) {
      return;
    }

    creando = false;
    formulario.innerHTML = "";

    let tamanoActual = 100;
    if (figuraSeleccionada.tipo == "Cuadrado") {
      tamanoActual = figuraSeleccionada.grandaria;
    } else if (figuraSeleccionada.tipo == "Circulo") {
      tamanoActual = figuraSeleccionada.radios * 2;
    } else if (figuraSeleccionada.tipo == "Triangulo") {
      tamanoActual = figuraSeleccionada.y2 - figuraSeleccionada.y1;
    } else if (figuraSeleccionada.tipo == "Estrella") {
      tamanoActual = figuraSeleccionada.grandaria;
    }

    formularioEditar.innerHTML = `
      <div>
        <input type="number" id="tamano-editar" value="${tamanoActual}">
      </div>
      <div>
        <input type="checkbox" id="relleno-editar">
        <label for="relleno-editar">Con relleno</label>
      </div>
      <button type="button" id="boton-aplicar">Aplicar</button>
    `;

    document.getElementById("relleno-editar").checked = figuraSeleccionada.tieneRelleno;

    document.getElementById("boton-aplicar").addEventListener("click", function() {
      const tamano = Number(document.getElementById("tamano-editar").value) || 100;
      const conRelleno = document.getElementById("relleno-editar").checked;

      if (figuraSeleccionada.tipo == "Cuadrado") {
        figuraSeleccionada.grandaria = tamano;
      } else if (figuraSeleccionada.tipo == "Circulo") {
        figuraSeleccionada.radios = tamano / 2;
      } else if (figuraSeleccionada.tipo == "Triangulo") {
        figuraSeleccionada.x1 = figuraSeleccionada.x;
        figuraSeleccionada.y1 = figuraSeleccionada.y - tamano / 2;
        figuraSeleccionada.x2 = figuraSeleccionada.x + tamano / 2;
        figuraSeleccionada.y2 = figuraSeleccionada.y + tamano / 2;
        figuraSeleccionada.x3 = figuraSeleccionada.x - tamano / 2;
        figuraSeleccionada.y3 = figuraSeleccionada.y + tamano / 2;
      } else if (figuraSeleccionada.tipo == "Estrella") {
        figuraSeleccionada.grandaria = tamano;
      }

      figuraSeleccionada.tieneRelleno = conRelleno;
      formularioEditar.innerHTML = "";
      dibujar();
    });
  });
}
const botonGuardar = document.getElementById("form-guardar");

if (botonGuardar !== null) {
  botonGuardar.addEventListener("submit", function() {
    const datosCanvas = document.getElementById("canvas-datos");
    const canvasId = datosCanvas.getAttribute("data-id");

    for (const figura of figuras) {
      figura.canvasId = Number(canvasId);
    }

    document.getElementById("figurasGuardar").value = JSON.stringify(figuras);
  });
}