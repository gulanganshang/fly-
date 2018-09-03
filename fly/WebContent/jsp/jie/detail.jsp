<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta charset="utf-8">
  <title></title>
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
      $.ajax({
			  url:'${pageContext.request.contextPath}/article/reply?artid=${artid}',
			  type:'get',
			  dataType: 'json',
			  success:function(data){
				  var json ={'reply':data};
				  //console.log(json);
				  var html = template('allreplymodel',json);
		  		  $("#jieda").html(html);
		  		  
			  },async:true
	   });
	  $('#replybtn').click(function(){
		  //发送ajax请求
		  //DOM操作
		  $.ajax({
			  url:'${pageContext.request.contextPath}/article/reply',
			  type:'post',
			  data:{
				  'userid':'${login_user.id}',
				  'artid':'${artid}',
				  'content':$('#L_content').val()
			  },
			  sussess:function(data){
				  
			  }
			  
		  });
		  $("L_content").html("");
		  var json={
			  'head_url':'${login_user.head_url}',
			  'nickname':'${login_user.nickname}',
			  'replytime':'刚刚',
			  'replycontent':$('#L_content').val(),
			  'goodnum':0
	  };
	  var html=template('replymodel',json);
	  $('#jieda').append(html);
	  });
	  
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
      <a class="avatar" href="${pageContext.request.contextPath}/jsp/user/index.jsp">
        <img src="${pageContext.request.contextPath}/headphoto/${login_user.head_url}">
        <cite>${login_user.nickname}</cite>
        <i>VIP2</i>
      </a>
      <div class="nav">
        <a href="${pageContext.request.contextPath}/user/set"><i class="iconfont icon-shezhi"></i>设置</a>
        <a href="${pageContext.request.contextPath}/user/logout"><i class="iconfont icon-tuichu" style="top: 0; font-size: 22px;"></i>退了</a>
      </div> 
     </c:if>
    </div>
  </div>
</div>


<div class="main layui-clear">
  <div class="wrap">
    <div class="content detail">
      <div class="fly-panel detail-box">
        <h1>${art_map.title}</h1>
        <div class="fly-tip fly-detail-hint" data-id="{{rows.id}}">
        <c:if test="${art_map.istop==1}">
          <span class="fly-tip-stick">置顶帖</span></c:if>
           <c:if test="${art_map.isgood==1}">
          <span class="fly-tip-jing">精帖</span></c:if>
          
          <span>未结贴</span>
          <!-- <span class="fly-tip-jie">已采纳</span> -->
          
          <!-- <span class="jie-admin" type="del" style="margin-left: 20px;">删除</span>
          <span class="jie-admin" type="set" field="stick" rank="1">置顶</span> 
          <span class="jie-admin" type="set" field="stick" rank="0" style="background-color:#ccc;">取消置顶</span>
          <span class="jie-admin" type="set" field="status" rank="1">加精</span> 
          <span class="jie-admin" type="set" field="status" rank="0" style="background-color:#ccc;">取消加精</span> -->
          
          <div class="fly-list-hint"> 
            <i class="iconfont" title="回答">&#xe60c;</i> ${art_map.remarknum}
            <i class="iconfont" title="人气">&#xe60b;</i> ${art_map.visitnum}
          </div>
        </div>
        <div class="detail-about">
          <a class="jie-user" href="">
            <img src="${pageContext.request.contextPath}/headphoto/${art_map.head_url}" alt="">
            <cite>
               ${art_map.nickname}
              <em><fmt:formatDate value="${art_map.releasetime}" pattern="yyyy-MM-dd"/> </em>
            </cite>
          </a>
          <div class="detail-hits" data-id="{{rows.id}}">
            <span style="color:#FF7200">悬赏：${art_map.paykiss}飞吻</span>
            <span class="layui-btn layui-btn-mini jie-admin" type="edit"><a href="/jie/edit/{{rows.id}}">编辑此贴</a></span>
            <span class="layui-btn layui-btn-mini jie-admin " type="collect" data-type="add">收藏</span>
            <!--<span class="layui-btn layui-btn-mini jie-admin  layui-btn-danger" type="collect" data-type="add">取消收藏</span>-->
          </div>
        </div>
        
        <div class="detail-body photos" style="margin-bottom: 20px;">
         	${art_map.content}
        </div>
      </div>
     <script type="text/html" id="replymodel">
          <li data-id="13">
            <a name="item-121212121212"></a>
            <div class="detail-about detail-about-reply">
              <a class="jie-user" href="">
                <img src="${pageContext.request.contextPath}/headphoto/{{head_url}}" alt="">
                <cite>
                  <i>{{nickname}}</i>
                  <em style="color:#FF9E3F">活雷锋</em>
                </cite>
              </a>
              <div class="detail-hits">
                <span>{{replytime}}</span>
              </div>
            </div>
            <div class="detail-body jieda-body">
              <p>{{replycontent}}</p>
            </div>
            <div class="jieda-reply">
              <span class="jieda-zan" type="zan"><i class="iconfont icon-zan"></i><em>{{goodnum}}</em></span>
              <span type="reply"><i class="iconfont icon-svgmoban53"></i>回复</span>
              <div class="jieda-admin">
                <span type="edit">编辑</span>
                <span type="del">删除</span>
                <span class="jieda-accept" type="accept">采纳</span>
              </div>
            </div>
          </li>
        </script>
        <script type="text/html" id="allreplymodel">
   {{if reply!=null}}
     {{each reply as value i}}
          <li data-id="13">
            <a name="item-121212121212"></a>
            <div class="detail-about detail-about-reply">
              <a class="jie-user" href="">
                <img src="${pageContext.request.contextPath}/headphoto/{{value.head_url}}" alt="">
                <cite>
                  <i>{{value.nickname}}</i>
                  <em style="color:#FF9E3F">活雷锋</em>
                </cite>
              </a>
              <div class="detail-hits">
                <span>{{value.remarktime}}</span>
              </div>
            </div>
            <div class="detail-body jieda-body">
              {{value.content}}
            </div>
            <div class="jieda-reply">
              <span class="jieda-zan" type="zan"><i class="iconfont icon-zan"></i><em>{{value.goodnum}}</em></span>
              <span type="reply"><i class="iconfont icon-svgmoban53"></i>回复</span>
              <div class="jieda-admin">
                <span type="edit">编辑</span>
                <span type="del">删除</span>
                <span class="jieda-accept" type="accept">采纳</span>
              </div>
            </div>
          </li>
    {{/each}}
{{/if}}
        </script>
      <div class="fly-panel detail-box" style="padding-top: 0;">
        <a name="comment"></a>
        <ul class="jieda photos" id="jieda">
          <!-- <li class="fly-none">没有任何回答</li> -->
        </ul>
        
        <div class="layui-form layui-form-pane">
          <form action="#" method="post">
            <div class="layui-form-item layui-form-text">
              <div class="layui-input-block">
                <textarea id="L_content" name="content" required lay-verify="required" placeholder="我要回答"  class="layui-textarea" style="height: 150px;"></textarea>
              </div>
            </div>
            <div class="layui-form-item">
              <button type="button" class="layui-btn" id="replybtn">提交回答</button>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
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
  <div class="edge">
    
    <dl class="fly-panel fly-list-one" id="hovisit"> 
      
    </dl>
    
    <dl class="fly-panel fly-list-one" id="horemark"> 
      

    </dl>
</div>


<div class="footer">
  <p><a href="http://fly.layui.com/">Fly社区</a> 2017 &copy; <a href="http://www.layui.com/">layui.com</a></p>
  <p>
    <a href="http://fly.layui.com/auth/get" target="_blank">产品授权</a>
    <a href="http://fly.layui.com/jie/8157.html" target="_blank">获取Fly社区模版</a>
    <a href="http://fly.layui.com/jie/2461.html" target="_blank">微信公众号</a>
  </p>
</div>
<script src="${pageContext.request.contextPath}/res/layui/layui.js"></script>
<script>
layui.cache.page = 'jie';
layui.cache.user = {
  username: '游客'
  ,uid: -1
  ,avatar: '${pageContext.request.contextPath}/res/images/avatar/00.jpg'
  ,experience: 83
  ,sex: '男'
};
layui.config({
  version: "2.0.0"
  ,base: '${pageContext.request.contextPath}/res/mods/'
}).extend({
  fly: 'index'
}).use('fly', function(){
  var fly = layui.fly;
  //如果你是采用模版自带的编辑器，你需要开启以下语句来解析。

  $('.detail-body').each(function(){
    var othis = $(this), html = othis.html();
    othis.html(fly.content(html));
  });

});
</script>

</body>
</html>