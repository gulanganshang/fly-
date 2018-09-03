<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta charset="utf-8">
  <title>基于Layui的轻量级问答社区页面模版</title>
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <meta name="keywords" content="fly,layui,前端社区">
  <meta name="description" content="Fly社区是模块化前端UI框架Layui的官网社区，致力于为web开发提供强劲动力">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/res/layui/css/layui.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/res/css/global.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/res/css/mricode.pagination.css">
  <script type="text/javascript" src="${pageContext.request.contextPath}/res/js/template-web.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/res/js/jquery-2.1.4.min.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/res/js/mricode.pagination.js"></script>
  <script type="text/javascript">
  $(function(){
		$("#page").pagination({
		    pageSize: 10,
		    prevBtnText:'上一页',
		    nextBtnText:'下一页',
		    showFirstLastBtn:false,
		    pageIndex:0,
		    remote: {
		        url: '${pageContext.request.contextPath }/article/more',
		        method:'get',
		        success: function (data) {
		            // data为ajax返回数据
		            console.log(data);
		            var html = template('pageinfo',data);
		             $('#content').html(html);
		        },
		        totalName:'total'
		    }
		});
	})
  
  </script>
</head>
<body>

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
      <a class="avatar" href="user/index.jsp">
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
    <div class="content" style="margin-right:0">
      <div class="fly-tab">
        <span>
          <a href="" class="tab-this">全部</a>
          <a href="">未结帖</a>
          <a href="">已采纳</a>
          <a href="">精帖</a>
          <a href="../user/index.html">我的帖</a>
        </span>
        <form action="http://cn.bing.com/search" class="fly-search">
          <i class="iconfont icon-sousuo"></i>
          <input class="layui-input" autocomplete="off" placeholder="搜索内容，回车跳转" type="text" name="q">
        </form>
        <a href="${pageContext.request.contextPath}/jsp/jie/add.jsp" class="layui-btn jie-add">发布问题</a>
      </div>
      
    <script type="text/html" id="pageinfo">
         {{each list as value i}}
         <li class="fly-list-li">
          <a href="${pageContext.request.contextPath}/jsp/user/home.jsp" class="fly-list-avatar">
            <img src="${pageContext.request.contextPath}/headphoto/{{value.head_url}}" alt="">
          </a>
          <h2 class="fly-tip">
            <a href="${pageContext.request.contextPath}/article/detail?artid={{value.id}}">{{value.title}}</a>
            {{if value.istop==1}}
            <span class="fly-tip-stick">置顶</span>
            {{/if}}
            {{if value.isgood==1}}
            <span class="fly-tip-jing">精帖</span>
             {{/if}} 
          </h2>
          <p>
            <span><a href="${pageContext.request.contextPath}/jsp/user/home.jsp">{{value.nickname}}</a></span>
            <span>{{value.releasetime}}</span>
            <span>{{value.typename}}</span>
            <span class="fly-list-hint"> 
              <i class="iconfont" title="回答">&#xe60c;</i> {{value.remarknum}}
              <i class="iconfont" title="人气">&#xe60b;</i> {{value.visitnum}}
            </span>
          </p>
        </li>
       {{/each}}
    </script>
      <ul class="fly-list" id="content">
      </ul>
      
      <!-- <div class="fly-none">并无相关数据</div> -->
    
      <div style="text-align: center">
        <div class="m-pagination" id="page"></div>
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