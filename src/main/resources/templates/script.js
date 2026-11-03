const canvas = document.getElementById("canvas");
const ctx = canvas.getContext("2d");


const circle = new Path2D();
circle.arc(200, 150, 50, 0, 2 * Math.PI);
ctx.fillStyle = "red";
ctx.fill(circle);

canvas.addEventListener("mousemove", (event) => {

  const isPointInPath = ctx.isPointInPath(circle, event.offsetX, event.offsetY);
  ctx.fillStyle = isPointInPath ? "green" : "red";

  
  ctx.clearRect(0, 0, canvas.width, canvas.height);
  ctx.fill(circle);
});