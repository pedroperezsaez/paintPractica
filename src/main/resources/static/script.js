const canvas = document.getElementById("canvas");
const ctx = canvas.getContext("2d");

const figuras = [];
let relleno = false;
let creando = false;
let figuraSeleccionada = null;
let tipoFigura = "";

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
  document.getElementById("boton-estrella")
];

for (const boton of botones) {
  boton.addEventListener("click", function() {
    tipoFigura = boton.getAttribute("data-tipo");
    mostrarOpciones();
  });
}
canvas.addEventListener("click",(event)=>{
    if(creando == false){
        return;
    }
    const tamano= Number(document.getElementById("tamano").value)
   const x= event.offsetX;
   const y= event.offsetY;
   let figura;
   if(tipoFigura=="Cuadrado"){
   figura={ tipo: "Cuadrado",
      x: x,
      y: y,
      grandaria: tamano,
      color: "red",
      tieneRelleno: relleno
   };
}else if(tipoFigura=="Triangulo"){
     figura = {
      tipo: "Triangulo",
      x: x,
      y: y,
      x1: x,
      y1: y - tamano / 2,
      x2: x + tamano / 2,
      y2: y + tamano / 2,
      x3: x - tamano / 2,
      y3: y + tamano / 2,
      color: "red",
      tieneRelleno: relleno
    };
} else if (tipoFigura === "Circulo") {
    figura = {
      tipo: "Circulo",
      x: x,
      y: y,
      radios: tamano / 2,
      color: "red",
      tieneRelleno: relleno
    };
}else {
    figura = {
      tipo: "Estrella",
      x: x,
      y: y,
      grandaria: tamano,
      color: "red",
      tieneRelleno: relleno
    };
}
figuras.push(figura)
dibujar()
creando=false;
formulario.innerHTML="";

   } )


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

if (botonBorrarFigura !== null) {
  botonBorrarFigura.addEventListener("click", function() {
    if (figuraSeleccionada === null) {
      return;
    }

    const posicion = figuras.indexOf(figuraSeleccionada);
    figuras.splice(posicion, 1);

    figuraSeleccionada = null;
    dibujar();
  });
}
