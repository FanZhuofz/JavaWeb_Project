﻿<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<!--网页文档三要素-->
  <title>Love</title>
  <meta name="Generator" content="EditPlus®">
  <meta name="Author" content="">
  <meta name="Keywords" content="">
  <meta name="Description" content="">
  
  
  <link rel="stylesheet" href="css/love.css">
 </head>
 <body>
	<!--盒子模型标签div 宽度 高度 位置 边框(css样式)-->
	<div id="heart"><!--id="自定义的名称" 命名规范(见名知意：用有语义的英文单词)-->
	</div>
	<!--背景音乐-->
	<audio src="music/爱情-马建南.mp3" autoplay></audio>
	<script src="js/jquery-1.2.6.min.js"></script>
	<script src="js/snowfall.jquery.js"></script>
	<script>
		$(document).snowfall({
			flakeCount:50	//爱心的数量
		});
	</script>
 </body>
</html>