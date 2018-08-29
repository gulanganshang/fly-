<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta charset="utf-8">
  <title>基于 layui 的极简社区页面模版</title>
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <meta name="keywords" content="fly,layui,前端社区">
  <meta name="description" content="Fly社区是模块化前端UI框架Layui的官网社区，致力于为web开发提供强劲动力">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/res/layui/css/layui.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/res/css/global.css">
  <script type="text/javascript" src="${pageContext.request.contextPath}/res/js/jquery-2.1.4.min.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/res/js/template-web.js"></script>
  <script type="text/javascript">
  $(function(){
	  $.ajax({
		  url:'${pageContext.request.contextPath}/article/remark',
		  type:'post',
		  dataType:'json',
		  success:function(data){
			  var json ={'remark_map':data};
			  var html = template('host',json);
	  		  $("#hostnav").html(html);
		  },async:true
	  })
	  $.ajax({
		  url:'${pageContext.request.contextPath}/article/host',
		  type:'post',
		  dataType:'json',
		  success:function(data){
			  var json ={'host_map':data};
			  var html1 = template('hostremark',json);
			  var html2 = template('hostvisit',json);
	  		  $("#hovisit").html(html1);
	  		  $("#horemark").html(html2);
		  },async:true
	  })
  }) 
  
  </script>
</head>
<body>
<div class="header">
  <div class="main">
    <a class="logo" href="${pageContext.request.contextPath}/article/loadindex" title="Fly">Fly社区</a>
    <div class="nav">
      <a class="nav-this" href="${pageContext.request.contextPath}/jsp/index.jsp">
        <i class="iconfont icon-wenda"></i>问答
      </a>
      <a href="http://www.layui.com/" target="_blank">
        <i class="iconfont icon-ui"></i>框架
      </a>
    </div>
    
    <div class="nav-user">
      <!-- 未登入状态 -->
      <c:if test="${login_user == null}">
      <a class="unlogin" href="${pageContext.request.contextPath}/jsp/user/login.jsp"><i class="iconfont icon-touxiang"></i></a>
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
<div class="main layui-clear">
  <div class="wrap">
    <div class="content">
      <div class="fly-tab fly-tab-index">
        <span>
          <a href="jie/index.html">全部</a>
          <a href="jie/index.html">未结帖</a>
          <a href="jie/index.html">已采纳</a>
          <a href="jie/index.html">精帖</a>
          <a href="user/index.html">我的帖</a>
        </span>
        <form action="http://cn.bing.com/search" class="fly-search">
          <i class="iconfont icon-sousuo"></i>
          <input class="layui-input" autocomplete="off" placeholder="搜索内容，回车跳转" type="text" name="q">
        </form>
        <a href="${pageContext.request.contextPath}/jie/goadd" class="layui-btn jie-add">发布问题</a>
      </div>
      
      
      <ul class="fly-list fly-list-top">
       <c:if test="${toplist!=null}">
      <c:forEach items="${toplist}" var="top">
        <li class="fly-list-li">
          <a href="${pageContext.request.contextPath}/jsp/user/home.jsp" class="fly-list-avatar">
            <img src="${pageContext.request.contextPath}/headphoto/${top.head_url}" alt="">
          </a>
          <h2 class="fly-tip">
            <a href="${pageContext.request.contextPath}/article/detail/${top.id}">${top.title}</a>
            <c:if test="${top.istop==1}"><span class="fly-tip-stick">置顶</span></c:if>
            <c:if test="${top.isgood==1}"><span class="fly-tip-jing">精帖</span></c:if>
          </h2>
          <p>
            <span><a href="${pageContext.request.contextPath}/jsp/user/home.jsp">${top.nickname}</a></span>
            <span><fmt:formatDate pattern="yyyy-MM-dd" value="${top.releasetime}"/>  </span>
            <span>${top.typename}</span>
            <span class="fly-list-hint"> 
              <i class="iconfont" title="回答">&#xe60c;</i> ${top.remarknum}
              <i class="iconfont" title="人气">&#xe60b;</i> ${top.visitnum}
            </span>
          </p>
        </li>
        </c:forEach>
        </c:if>
      </ul>
      <!-- <script type="text/html" id="botten">
        {{each indexlist as value i}}
         <li class="fly-list-li">
          <a href="${pageContext.request.contextPath}/jsp/${pageContext.request.contextPath}/jsp/user/home.jsp" class="fly-list-avatar">
            <img src="${pageContext.request.contextPath}/headphoto/{{value.head_url}}" alt="">
          </a>
          <h2 class="fly-tip">
            <a href="jie/detail.html">{{value.title}}</a>
          </h2>
          <p>
            <span><a href="${pageContext.request.contextPath}/jsp/user/home.jsp">{{value.nickname}}</a></span>
            <span>1小时前</span>
            <span>{{value.typename}}</span>
            <span class="fly-list-hint"> 
              <i class="iconfont" title="回答">&#xe60c;</i> 8
              <i class="iconfont" title="人气">&#xe60b;</i> 106
            </span>
          </p>
        </li>
       {{/each}}
      </script>   -->
      <ul class="fly-list" id="artbotten">
      <c:if test="${indexlist!=null}">
      <c:forEach items="${indexlist}" var="art">
      <li class="fly-list-li">
          <a href="${pageContext.request.contextPath}/jsp/${pageContext.request.contextPath}/jsp/user/home.jsp" class="fly-list-avatar">
            <img src="${pageContext.request.contextPath}/headphoto/${art.head_url}" alt="">
          </a>
          <h2 class="fly-tip">
            <a href="${pageContext.request.contextPath}/article/detail/${art.id}">${art.title}</a>
          </h2>
          <p>
            <span><a href="${pageContext.request.contextPath}/jsp/user/home.jsp">${art.nickname}</a></span>
            <span><fmt:formatDate pattern="yyyy-MM-dd" value="${art.releasetime}"/></span>
            <span>${art.typename}</span>
            <span class="fly-list-hint"> 
              <i class="iconfont" title="回答">&#xe60c;</i>${art.remarknum}
              <i class="iconfont" title="人气">&#xe60b;</i>${art.visitnum}
            </span>
          </p>
        </li>
        </c:forEach>
        </c:if>
      </ul>
      
      <div style="text-align: center">
        <div class="laypage-main">
          <a href="${pageContext.request.contextPath}/jsp/jie/index.jsp" class="laypage-next">更多求解</a>
        </div>
      </div>
      
    </div>
  </div>
  <script type="text/html" id="host">
     {{each remark_map as value i}}
        <dd>
          <a href="${pageContext.request.contextPath}/jsp/user/home.jsp">
            <img src="${pageContext.request.contextPath}/headphoto/{{value.head_url}}">
            <cite>{{value.nickname}}</cite>
            <i>{{value.countnum}}</i>
          </a>
        </dd>
    {{/each}}
  </script>
  <div class="edge">
    <div class="fly-panel leifeng-rank"> 
      <h3 class="fly-panel-title">近一月回答榜 - TOP 12</h3>
      <dl id="hostnav">
        
        
      </dl>
      <script type="text/html" id="hostremark">
     <dt class="fly-panel-title" >近期热议</dt>
     
     {{each host_map as value i}}      
      <dd>
        <a href="${pageContext.request.contextPath}/article/detail?artid={{value.id}}">{{value.title}}</a>
        <span><i class="iconfont">&#xe60c;</i>{{value.remarknum}}</span>
      </dd>
     {{/each}}
      </script>
      <script type="text/html" id="hostvisit">
      <dt class="fly-panel-title" >最近热帖</dt>
      {{each host_map as value i}}
       <dd>
        <a href="${pageContext.request.contextPath}/article/detail?artid={{value.id}}">{{value.title}}</a>
        <span><i class="iconfont">&#xe60b;</i> {{value.visitnum}}</span>
       </dd>
      {{/each}}
      </script>
    </div>
    
    <dl class="fly-panel fly-list-one" id="hovisit"> 
      
    </dl>
    
    <dl class="fly-panel fly-list-one" id="horemark"> 
      

    </dl>
    
    <div class="fly-panel fly-link"> 
      <h3 class="fly-panel-title">友情链接</h3>
      <dl>
        <dd>
          <a href="http://www.layui.com/" target="_blank">layui</a>
        </dd>
        <dd>
          <a href="http://layim.layui.com/" target="_blank">LayIM</a>
        </dd>
        <dd>
          <a href="http://layer.layui.com/" target="_blank">layer</a>
        </dd>
      </dl>
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