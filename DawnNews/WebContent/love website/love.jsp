<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="css/style.css">
  
  <title>Love</title>
  <!--样式css 修饰 衣服 化妆品-->
	<style type="text/css">
		*{
			margin:0;
			padding:0;			
			}
		body{
			background:#000;
		}
		/*自适应居中*/
		#heart-3d{
			position:absolute;
			left:0;
			top:0;
			right:0;
			bottom:0;
			margin:auto;
			width:300px;
			height:660px;
			transform-style:preserve-3d;/*设置3D环境*/
			animation:rot 15s linear infinite;/*css3自定义动画：动画名称、动画时间、匀速运动 无限重复*/
		}
		@keyframes rot{/*css3关键帧实现转动*/
			/*从0转到360度		0加不加无所谓*/
			/*from{
				transform:rotateY(0deg) rotateX(0deg);
			}*/
			to{
				transform:rotateY(360deg) rotateX(360deg);
			}
		}
		.heart{
			position:absolute;
			left:0;
			top:0;
			width:300px;
			height:500px;
			border:1px solid red;
			border-width:1px 1px 0 0;
			border-radius:50% 50% 0/40% 50% 0;/*水平边 左上、右上/左下、右下*/
			/*transform:rotateZ(45deg);/*旋转45度*/
		}

		/*旋转*//*
		.heart:nth-child(1){
			transform:rotateY(10deg) rotateZ(45deg) translateX(30);/*旋转10度
		}
		.heart:nth-child(2){
			transform:rotateY(40deg) rotateZ(45deg) translateX(30);/*旋转40度
		}
		.heart:nth-child(3){
			transform:rotateY(100deg) rotateZ(45deg) translateX(30);/*旋转100度
		}*/

		/*立方体*/
		.cube{
		position:absolute;
		left:0;
		top:0;
		right:0;
		bottom:0;
		margin:auto;
		width:100px;
		height:200px;
		/*margin:300px auto;*/
		transform-style:preserve-3d;
		transform:translateZ(50px);
		
		}
		/*.cube:hover{
			animation:rot 5s linear;
		}
		@keyframes rot{
			to{
				transform:translateZ(-100px) rotateY(360deg);
			}
		}*/
		.cube div{
			position:absolute;
			left:0;
			top:0;
			width:100px;
			height:100px;
			/*border:2px solid red;*/
		}
		/*上*/
		.cube div:nth-child(1){
			left:0;
			top:-100px;
			transform-origin:bottom;/*围绕底线(转动轴)转动*/
			transform:translateZ(0px) rotateX(90deg);/*转动90度*/
		}
		/*下*/
		.cube div:nth-child(2){
			left:0;
			top:100px;
			transform-origin:top;/*围绕上线(转动轴)转动*/
			transform:translateZ(0px) rotateX(-90deg);/*转动90度*/
		}
		/*左*/
		.cube div:nth-child(3){
			left:-100px;
			top:0;
			transform-origin:right;/*围绕右线(转动轴)转动*/
			transform:translateZ(0px) rotateY(-90deg);/*转动90度*/
		}
		/*右*/
		.cube div:nth-child(4){
			left:100px;
			top:0;
			transform-origin:left;/*围绕左线(转动轴)转动*/
			transform:translateZ(0px) rotateY(90deg);/*转动90度*/
		}
		/*前*/
		.cube div:nth-child(5){
			transform:translateZ(0px);/*移位*/
		}
		/*后*/
		.cube div:nth-child(6){
			transform:translateZ(-100px);/*移位*/
		}
	</style>
 </head>
 <body>
 
	<!--自动播放背景音乐，不显示-->
	<audio src="music/1.mp3" autoplay></audio>

	<canvas id="canvas">Canvas is not supported by your browser.</canvas>
    <script src="js/index.js"></script>

	<div id="heart-3d">
		<!--<div class="heart"></div>
		<div class="heart"></div>
		<div class="heart"></div>-->
		<div class="cube">
			<div><img src="images/1.jpg" width="100" height="100" alt=""/></div>
			<div><img src="images/2.jpg" width="100" height="100" alt=""/></div>
			<div><img src="images/3.jpg" width="100" height="100" alt=""/></div>
			<div><img src="images/4.jpg" width="100" height="100" alt=""/></div>
			<div><img src="images/5.jpg" width="100" height="100" alt=""/></div>
			<div><img src="images/6.jpg" width="100" height="100" alt=""/></div>
		</div>
	</div>
	<script type="text/javascript">
		var heart_3d = document.getElementById("heart-3d");
		for(var i=0;i<36;i++)
		{
			var oDiv = document.createElement("div");
			oDiv.className = "heart";
			oDiv.style.transform = "rotateY("+10*i+"deg) rotateZ(45deg) translateX(100px)";/*旋转*/
			heart_3d.appendChild(oDiv);
		}
	</script>
 </body>
</html>
