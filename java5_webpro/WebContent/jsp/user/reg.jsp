<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>注册</title>
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <meta name="keywords" content="fly,layui,前端社区">
  <meta name="description" content="Fly社区是模块化前端UI框架Layui的官网社区，致力于为web开发提供强劲动力">
  <link rel="stylesheet" href="${ pageContext.request.contextPath }/res/layui/css/layui.css">
  <link rel="stylesheet" href="${ pageContext.request.contextPath }/res/css/global.css">
  <script type="text/javascript" src="${ pageContext.request.contextPath }/res/js/jquery-2.1.4.min.js"></script>
  <script type="text/javascript">
  		$(function(){

  			// ajax发送请求查询邮箱是否合法
  			$('#L_email').blur(function(){
  				if($('#L_email').val() != null && $('#L_email').val() != '' && $('#L_email').val().indexOf('@') !=-1 ){
  					$.ajax({
  	  					url:'${ pageContext.request.contextPath }/user/reg',
  	  					type:'get',
  	  					data:{'email':$('#L_email').val()},
  	  					success: function(data){
  	  						if(data == 0){
  	  							$('#regmsg').html('该邮箱地址可以注册').css('color','green');
  	  						}
  	  						if(data == 1){
  	  							$('#regmsg').html('该邮箱地址已被注册').css('color','red');
  	  						}
  	  					},
  	  					async: true
  	  				})
  				}
  			});

  			// 注册验证
  			$('#regform').submit(function(){
  				$('#passmsg').html('');
  				if($('#L_repass').val() != $('#L_pass').val()){
  					$('#passmsg').html('两次密码不一致');
  				}
  				if($('#regmsg').html() == '该邮箱地址已被注册' || $('#passmsg').html() == '两次密码不一致'){
  					return false;
  				}
  			})
  		})
  </script>
</head>
<body>

<div class="header">
  <div class="main">
    <a class="logo" href="${ pageContext.request.contextPath }/jsp/index.jsp" title="Fly">Fly社区</a>
    <div class="nav">
      <a class="nav-this" href="index.html">
        <i class="iconfont icon-wenda"></i>问答
      </a>
      <a href="http://www.layui.com/" target="_blank">
        <i class="iconfont icon-ui"></i>框架
      </a>
    </div>
    
   <div class="nav-user">
    <c:if test="${ login_user == null }">
     	<!-- 未登入状态 -->
      <a class="unlogin" href="user/login.html"><i class="iconfont icon-touxiang"></i></a>
      <span><a href="${ pageContext.request.contextPath }/jsp/user/login.jsp">登入</a><a href="${ pageContext.request.contextPath }/jsp/user/reg.jsp">注册</a></span>
      <p class="out-login">
        <a href="" onclick="layer.msg('正在通过QQ登入', {icon:16, shade: 0.1, time:0})" class="iconfont icon-qq" title="QQ登入"></a>
        <a href="" onclick="layer.msg('正在通过微博登入', {icon:16, shade: 0.1, time:0})" class="iconfont icon-weibo" title="微博登入"></a>
      </p>   
    </c:if>
     <c:if test="${ login_user != null }">
      <!-- 登入后的状态 -->
      <a class="avatar" href="user/index.html">
        <img src="${ pageContext.request.contextPath }/upload/${login_user.head_url}">
        <cite>${ login_user.nickname }</cite>
        <i>VIP2</i>
      </a>
      <div class="nav">
        <a href="/user/set/"><i class="iconfont icon-shezhi"></i>设置</a>
        <a href="/user/logout/"><i class="iconfont icon-tuichu" style="top: 0; font-size: 22px;"></i>退了</a>
      </div>
      </c:if>
    </div>
  </div>
</div>

<div class="main layui-clear">

  <div class="fly-panel fly-panel-user" pad20>
    <div class="layui-tab layui-tab-brief">
      <ul class="layui-tab-title">
        <li><a href="${ pageContext.request.contextPath }/jsp/user/login.jsp">登入</a></li>
        <li class="layui-this">注册</li>
      </ul>
      <div class="layui-form layui-tab-content" id="LAY_ucm" style="padding: 20px 0;">
        <div class="layui-tab-item layui-show">
          <div class="layui-form layui-form-pane">
          <!-- 注册表单 -->
            <form id="regform" method="post" action="${ pageContext.request.contextPath }/user/reg">
              <div class="layui-form-item">
                <label for="L_email" class="layui-form-label">邮箱</label>
                <div class="layui-input-inline">
                  <input type="email" id="L_email" name="email" required lay-verify="email" autocomplete="off" class="layui-input">
                </div>
                <div id="regmsg" class="layui-form-mid layui-word-aux">将会成为您唯一的登入名</div>
              </div>
              <div class="layui-form-item">
                <label for="L_username" class="layui-form-label">昵称</label>
                <div class="layui-input-inline">
                  <input type="text" id="L_username" name="nickname" required lay-verify="required" autocomplete="off" class="layui-input">
                </div>
              </div>
              <div class="layui-form-item">
                <label for="L_pass" class="layui-form-label">密码</label>
                <div class="layui-input-inline">
                  <input type="password" id="L_pass" name="pass" required lay-verify="required" autocomplete="off" class="layui-input">
                </div>
                <div class="layui-form-mid layui-word-aux">6到16个字符</div>
              </div>
              <div class="layui-form-item">
                <label for="L_repass" class="layui-form-label">确认密码</label>
                <div class="layui-input-inline">
                  <input type="password" id="L_repass" name="repass" required lay-verify="required" autocomplete="off" class="layui-input">
                </div>
                <div id="passmsg" class="layui-form-mid layui-word-aux" style="color:red"></div>
              </div>
              <div class="layui-form-item">
                <button class="layui-btn">立即注册</button>
              </div>
              <div class="layui-form-item fly-form-app">
                <span>或者直接使用社交账号快捷注册</span>
                <a href="http://fly.layui.com:8098/app/qq" onclick="layer.msg('正在通过QQ登入', {icon:16, shade: 0.1, time:0})" class="iconfont icon-qq" title="QQ登入"></a>
                <a href="http://fly.layui.com:8098/app/weibo/" onclick="layer.msg('正在通过微博登入', {icon:16, shade: 0.1, time:0})" class="iconfont icon-weibo" title="微博登入"></a>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>

</div>

<div class="footer">
  <p><a href="http://fly.layui.com/">Fly社区</a> 2017 &copy; <a href="http://www.layui.com/">layui.com</a></p>
  <p>
    <a href="http://fly.layui.com/auth/get" target="_blank">产品授权</a>
    <a href="http://fly.layui.com/jie/8157.html" target="_blank">获取Fly社区模版</a>
    <a href="http://fly.layui.com/jie/2461.html" target="_blank">微信公众号</a>
  </p>
</div>
</body>
</html>