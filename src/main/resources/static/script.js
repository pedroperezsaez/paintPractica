const cuadrado = {
    tamaño: 100,
    color: "red",
    posicionX: 200,
    posicionY: 150,
    relleno: true
};
let cuadrados=[];

const canvas = document.getElementById("canvas");
const ctx = canvas.getContext("2d");
let cuadradoSeleccionado=null;


canvas.addEventListener("click",(event)=>{
  if(cuadradoSeleccionado !=null){
    editarEliminarFigura()
  }
  for(let i=0; i<cuadrados.length;i++){
    const cuadrado=cuadrados[i];
    const figura=new Path2D();
    figura.rect(
      cuadrado.posicionX,
      cuadrado.posicionY,
      cuadrado.tamaño,
      cuadrado.tamaño
    )
    const encima=ctx.isPointInPath(
      figura,
      event.offsetX,
      event.offsetY
    )
     if (encima) {
            cuadradoSeleccionado=i;
        }
  }
})
function  editarEliminarFigura(){
  const cuadrado=cuadrados[cuadradoSeleccionado]
  form.innerHTML=`
        <div>
            <input type="checkbox" id="checkLabel" ${cuadrado.relleno ? "checked" : ""}>
            <label for="checkLabel">Con relleno</label>
        </div>
        <div>
            <label for="sizesInputSmall">Tamaño</label>
            <input type="number" id="sizesInputSmall" value="${cuadrado.tamaño}">
        </div>

        <button type="button" id="modificarCuadrado">Modificar</button>
        <button type="button" id="eliminarCuadrado">Eliminar</button>
    `

    document.getElementById("modificarCuadrado").addEventListener("click",()=>{
      cuadrados[cuadradoSeleccionado].relleno= document.getElementById("checkLabel").checked
      cuadrados[cuadradoSeleccionado].tamaño = document.getElementById("sizesInputSmall").value
      dibujarCuadrados()
    })

    document.getElementById("eliminarCuadrado").addEventListener("click",()=>{
      cuadrados.splice(cuadradoSeleccionado,1)
      form.innerHTML=""
      cuadradoSeleccionado=null;
      dibujarCuadrados();
    })
}



const botonCuadrado = document.getElementById("boton-cuadrado");
const form = document.getElementById("form");
const tituloFigura = document.getElementById("titulo-figura");

let grandariaCuadrado = 0;
let tieneRellenoCuadrado = false;
let cuadradoActivo = false;

botonCuadrado.addEventListener("click", () => {

    form.innerHTML = `
        <div class="form-field">
            <input type="checkbox" id="checkLabel" class="check">
            <label for="checkLabel">Con relleno</label>
        </div>
        <div>
            <label for="sizesInputSmall">Escribe el tamaño</label>
            <input type="number" id="sizesInputSmall" class="form-control form-control-sm" placeholder="Tamaño">
        </div>
        <button type="button" id="crearCuadrado">Crear cuadrado</button>
    `;

    tituloFigura.innerHTML = "Cuadrado";

    const crearCuadrado = document.getElementById("crearCuadrado");

    crearCuadrado.addEventListener("click", () => {
        cuadrado.relleno = document.getElementById("checkLabel").checked;
    cuadrado.tamaño = Number(document.getElementById("sizesInputSmall").value);
    cuadradoActivo = true;
    });
});

canvas.addEventListener("click", (event) => {

   if (cuadradoActivo) {

        cuadrado.posicionX = event.offsetX;
        cuadrado.posicionY = event.offsetY;

        if (cuadrado.relleno) {
            ctx.fillStyle = cuadrado.color;

            ctx.fillRect(
                cuadrado.posicionX,
                cuadrado.posicionY,
                cuadrado.tamaño,
                cuadrado.tamaño
            );
        }

        ctx.strokeStyle = cuadrado.color;

        ctx.strokeRect(
            cuadrado.posicionX,
            cuadrado.posicionY,
            cuadrado.tamaño,
            cuadrado.tamaño
        );
        cuadrados.push({
    tamaño: cuadrado.tamaño,
    color: cuadrado.color,
    posicionX: cuadrado.posicionX,
    posicionY: cuadrado.posicionY,
    relleno: cuadrado.relleno
});
  dibujarCuadrados();

        cuadradoActivo = false;
        
    }
});



function dibujarCuadrados() {
    ctx.clearRect(0, 0, canvas.width, canvas.height);
    for (let i = 0; i < cuadrados.length; i++) {
        const cuadrado = cuadrados[i];
        ctx.fillStyle = cuadrado.color;
        ctx.strokeStyle = cuadrado.color;

        if (cuadrado.relleno) {
            ctx.fillRect(
                cuadrado.posicionX,
                cuadrado.posicionY,
                cuadrado.tamaño,
                cuadrado.tamaño
            );
        }

        ctx.strokeRect(
            cuadrado.posicionX,
            cuadrado.posicionY,
            cuadrado.tamaño,
            cuadrado.tamaño
        );
    }


}

const figurasJson=JSON.parse(document.getElementById("datosFiguras").textContent)
