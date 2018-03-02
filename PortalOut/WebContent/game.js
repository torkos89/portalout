"use strict"
const VERSION = "A";
const CVS = document.getElementById("game");
const CTX = CVS.getContext("2d");
const SCORE = document.getElementById("score");
const X = CVS.width/10;
const Y = CVS.height/20;
var health;
var color = ["","lightgreen","red"]
var level = 0;
var stage = [
	[   //   level 0
		 [1,1,1,1,1,1,1,1,1,1] 
		,[1,1,1,1,1,1,1,1,1,1]
		,[1,1,1,1,1,1,1,1,1,1]
		,[1,1,1,1,1,1,1,1,1,1]
		,[1,1,1,1,1,1,1,1,1,1]
	]
];
/*
var ball = {
 x: 300,
 y: 500,
 vx: 1,
 vy: 1
}
*/
var mouse = {
  x: 0,
  y: 0
}



var webby = new WebSocket("ws://"+location.hostname+(location.port=="80"? "" : (":"+location.port))+"/PortalOut/serverA");
webby.onmessage = function(inc){
	console.log(inc.data) // TODO: .data
	let data = JSON.parse(inc.data)
	console.log(data)
	if(data.id){
		
	}
	else{
		drawLoop(data)
	}
		
}

function mousePos(event){
	//console.log(event);
	mouse.x = event.clientX
	mouse.y = event.clientY
	webby.send(event.clientX+","+ event.clientY)
}
CVS.addEventListener("click",mousePos)

// CTX.fillRect(60,60,120,120);  / reference /
function drawStage(bricks){
  CTX.fillStyle = "darkgray";
	CTX.fillRect(0,0,600,600);
 //console.log(X,Y,stage[level])
	for(let y = 0; y<stage[level].length; y++){
		for(let x = 0; x<stage[level][y].length; x++){  // load
			let col = stage[level][y][x];
			if(col!=0){
			  CTX.fillStyle = color[col];
			  CTX.fillRect(X*x+1,Y*y+1,X-1,Y-1);			
		 }	
		}
	}	
	
}
//drawStage();

function drawBall(ball){
		CTX.fillStyle = "red";
		CTX.beginPath();
		CTX.arc(ball.x,ball.y,10,0,2*Math.PI); //TODO: move up
		CTX.fill();
}

//drawBall();

function drawPortal(portals){
	if(portals.length==0) return;
  
	for(let i=0;i<portals.length;i++){
		if(!portals[i].type) return;
		CTX.fillStyle = portals[i].type.split("-")[1];
		if(portals[i].x==5&&portals[i].y!=595 || portals[i].x==595) CTX.fillRect(portals[i].x-6,portals[i].y-25,12,50);
		if(portals[i].y==595) CTX.fillRect(portals[i].x-25,portals[i].y-6,50,12);
	}
}

function drawLoop(data){
	//updateBall();
	drawStage(data.bricks);
	drawBall(data.ball);
	drawPortal(data.portals);
  //requestAnimationFrame(drawLoop);
}
//drawLoop();
