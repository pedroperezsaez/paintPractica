import { canvas, ctx, figuras, estado } from './variables.js';
export function clickFigura(event) {
  const escalaX = canvas.width / canvas.clientWidth;
  const escalaY = canvas.height / canvas.clientHeight;

  const punto = {
    x: event.offsetX * escalaX,
    y: event.offsetY * escalaY
  };

  return punto;
}

export function ponerNombre(figura) {
  figura.nombre = figura.tipo + ' ' + (figuras.length + 1);
}

export function cambiarTamano(figura, tamano) {
  if (figura.tipo == 'Cuadrado') {
    figura.grandaria = tamano;
  } else if (figura.tipo == 'Circulo') {
    figura.radios = tamano / 2;
  } else if (figura.tipo == 'Triangulo') {
    figura.x1 = figura.x;
    figura.y1 = figura.y - tamano / 2;
    figura.x2 = figura.x + tamano / 2;
    figura.y2 = figura.y + tamano / 2;
    figura.x3 = figura.x - tamano / 2;
    figura.y3 = figura.y + tamano / 2;
  } else if (figura.tipo == 'Estrella') {
    figura.grandaria = tamano;
  }
}

export function crearContorno(figura) {
  const contorno = new Path2D();

  if (figura.tipo == 'Cuadrado') {
    contorno.rect(
      figura.x - figura.grandaria / 2,
      figura.y - figura.grandaria / 2,
      figura.grandaria,
      figura.grandaria,
    );
  } else if (figura.tipo == 'Circulo') {
    contorno.arc(figura.x, figura.y, figura.radios, 0, 2 * Math.PI);
  } else if (figura.tipo == 'Triangulo') {
    contorno.moveTo(figura.x1, figura.y1);
    contorno.lineTo(figura.x2, figura.y2);
    contorno.lineTo(figura.x3, figura.y3);
    contorno.closePath();
  } else if (figura.tipo == 'Estrella') {
    const radio = figura.grandaria / 2;
    const radioInterior = radio / 2;

    for (let i = 0; i < 14; i++) {
      const angulo = (Math.PI / 7) * i - Math.PI / 2;

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
  } else if (figura.tipo == 'Trazado') {
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

export function dibujar() {
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

    if (figura === estado.figuraSeleccionada) {
      ctx.strokeStyle = 'blue';
      ctx.lineWidth = 3;
      ctx.stroke(contorno);
      ctx.lineWidth = 1;
    }
  }
}

export function actualizarLista() {
  const lista = document.getElementById('lista-figuras');
  lista.innerHTML = '';

  for (let i = 0; i < figuras.length; i++) {
    const item = document.createElement('li');
   
     item.textContent = i + 1 + '. ' + figuras[i].nombre;

    if (figuras[i] === estado.figuraSeleccionada) {
      item.style.fontWeight = 'bold';
    }

    lista.appendChild(item);
  }
}
