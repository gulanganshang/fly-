<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>用户主页</title>
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <meta name="keywords" content="fly,layui,前端社区">
  <meta name="description" content="Fly社区是模块化前端UI框架Layui的官网社区，致力于为web开发提供强劲动力">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/res/layui/css/layui.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/res/css/global.css">
   <script type="text/javascript" src="${pageContext.request.contextPath}/res/js/jquery-2.1.4.min.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/res/js/template-web.js"></script>
  <script type="text/javascript">
  </script>
</head>
<body style="margin-top: 65px;">

<div class="header">
  <div class="main">
    <a class="logo" href="${pageContext.request.contextPath}/article/loadindex" title="Fly">Fly社区</a>
    <div class="nav">
      <a class="nav-this" href="index.html">
        <i class="iconfont icon-wenda"></i>问答
      </a>
      <a href="http://www.layui.com/" target="_blank">
        <i class="iconfont icon-ui"></i>框架
      </a>
    </div>
     <div class="nav-user">
      <!-- 未登入状态 -->
      <c:if test="${login_user == null}">
      <a class="unlogin" href="user/login.html"><i class="iconfont icon-touxiang"></i></a>
      <span><a href="${pageContext.request.contextPath}/jsp/user/login.jsp">登入</a><a href="${pageContext.request.contextPath}/jsp/user/reg.jsp">注册</a></span>
      <p class="out-login">
        <a href="" onclick="layer.msg('正在通过QQ登入', {icon:16, shade: 0.1, time:0})" class="iconfont icon-qq" title="QQ登入"></a>
        <a href="" onclick="layer.msg('正在通过微博登入', {icon:16, shade: 0.1, time:0})" class="iconfont icon-weibo" title="微博登入"></a>
      </p>   
      </c:if>
      <!-- 登入后的状态 -->
     
    <c:if test="${login_user != null}">
      <a class="avatar" href="${pageContext.request.contextPath}/jsp/user/index.jsp">
        <img src="${pageContext.request.contextPath}/headphoto/${login_user.head_url}">
        <cite>${login_user.nickname}</cite>
        <i>VIP2</i>
      </a>
      <div class="nav">
        <a href="${pageContext.request.contextPath}/jsp/user/set.jsp"><i class="iconfont icon-shezhi"></i>设置</a>
        <a href="${pageContext.request.contextPath}/user/logout"><i class="iconfont icon-tuichu" style="top: 0; font-size: 22px;"></i>退了</a>
      </div> 
     </c:if>
    </div>
  </div>
</div>

<div class="fly-home" style="background-image: url();">
  <img src="${pageContext.request.contextPath}/headphoto/${login_user.head_url}" alt="头像">
  <h1>
    ${login_user.nickname}
    <i class="iconfont icon-nan"></i> 
    <!-- <i class="iconfont icon-nv"></i> -->
    
    <!-- <span style="color:#c00;">（超级码农）</span>
    <span style="color:#5FB878;">（活雷锋）</span>
    <span>（该号已被封）</span> -->
  </h1>
  <p class="fly-home-info">
    <i class="iconfont icon-zuichun" title="飞吻"></i><span style="color: #FF7200;">67206飞吻</span>
    <i class="iconfont icon-shijian"></i><span>2015-06-17 加入</span>
    <i class="iconfont icon-chengshi"></i><span>来自杭州</span>
  </p>
  <p class="fly-home-sign">（人生仿若一场修行）</p>
</div>

<div class="main fly-home-main">
  <div class="layui-inline fly-home-jie">
    <div class="fly-panel">
      <h3 class="fly-panel-title">贤心 最近的提问</h3>
      <ul class="jie-row">
        <li>
          <span class="fly-jing">精</span>
          <a href="/jie/{{item.id}}.html" class="jie-title">使用 layui 秒搭后台大布局（基本结构）</a>
          <i>1天前</i>
          <em>1136阅/27答</em>
        </li>
        <li>
          <a href="/jie/{{item.id}}.html" class="jie-title">使用 layui 秒搭后台大布局（基本结构）</a>
          <i>1天前</i>
          <em>1136阅/27答</em>
        </li>
        <li>
          <a href="/jie/{{item.id}}.html" class="jie-title">使用 layui 秒搭后台大布局（基本结构）</a>
          <i>1天前</i>
          <em>1136阅/27答</em>
        </li>
        <li>
          <a href="/jie/{{item.id}}.html" class="jie-title">使用 layui 秒搭后台大布局（基本结构）</a>
          <i>1天前</i>
          <em>1136阅/27答</em>
        </li>
        <li>
          <a href="/jie/{{item.id}}.html" class="jie-title">使用 layui 秒搭后台大布局（基本结构）</a>
          <i>1天前</i>
          <em>1136阅/27答</em>
        </li>
        <li>
          <a href="/jie/{{item.id}}.html" class="jie-title">使用 layui 秒搭后台大布局（基本结构）</a>
          <i>1天前</i>
          <em>1136阅/27答</em>
        </li>
        <li>
          <a href="/jie/{{item.id}}.html" class="jie-title">使用 layui 秒搭后台大布局（基本结构）</a>
          <i>1天前</i>
          <em>1136阅/27答</em>
        </li>
      </ul>
      <!-- <div class="fly-none" style="min-height: 50px; padding:30px 0; height:auto;"><i style="font-size:14px;">没有发表任何求解</i></div> -->
    </div>
  </div>
  
  <div class="layui-inline fly-home-da">
    <div class="fly-panel">
      <h3 class="fly-panel-title">贤心 最近的回答</h3>
      <ul class="home-jieda">
        <li>
          <p>
          <span>1分钟前</span>
          在<a href="" target="_blank">tips能同时渲染多个吗?</a>中回答：
          </p>
          <div class="home-dacontent">
            尝试给layer.photos加上这个属性试试：
<pre>
full: true         
</pre>
            文档没有提及
          </div>
        </li>
        <li>
          <p>
          <span>5分钟前</span>
          在<a href="" target="_blank">在Fly社区用的是什么系统啊?</a>中回答：
          </p>
          <div class="home-dacontent">
            Fly社区采用的是NodeJS。分享出来的只是前端模版
          </div>
        </li>
      </ul>
      <!-- <div class="fly-none" style="min-height: 50px; padding:30px 0; height:auto;"><span>没有回答任何问题</span></div> -->
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