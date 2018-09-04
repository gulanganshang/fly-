<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>发表问题 编辑问题 公用</title>
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <meta name="keywords" content="fly,layui,前端社区">
  <meta name="description" content="Fly社区是模块化前端UI框架Layui的官网社区，致力于为web开发提供强劲动力">
  <link rel="stylesheet" href="${ pageContext.request.contextPath }/res/layui/css/layui.css">
  <link rel="stylesheet" href="${ pageContext.request.contextPath }/res/css/global.css">
  
  <link rel="stylesheet" href="${pageContext.request.contextPath}/res/editor-md-master/css/editormd.css" />
  <script type="text/javascript" src="${ pageContext.request.contextPath }/res/js/jquery-2.1.4.min.js"></script>
  <script src="${pageContext.request.contextPath}/res/editor-md-master/editormd.min.js"></script>
  
   <script type="text/javascript" src="${ pageContext.request.contextPath }/res/js/template-web.js"></script>
  <script type="text/javascript">
  		$(function(){
  				$.ajax({
  	  				url:'${ pageContext.request.contextPath }/article/type',
  	  				type:'get',
  	  				dataType:'json',
  	  				success: function(data){
  	  					var list = {"artlist":data};
  	  					console.log(list);
  	  					var html = template("arttest",list);
  	  					$('#artype').html(html);
  	  				},
  	  				async: true
  	  			});
  				// 飞吻数不够
  				$('#addartform').submit(function(){
  					if(parseInt($('#login_kiss').val()) < parseInt($('#cost_kiss').val())){
  						$('#kissmsg').html('您的飞吻数太少了');
  						return false;
  					}
  				});
  				// -----------------------------------------------------------------------
  				var testEditor = editormd({
  	                id: "test-editormd",
  	                height: 640,
  	                width   : "100%",
  	                placeholder  : "文明社会，理性评论，支持Markdown",
  	                path: "${pageContext.request.contextPath}/res/editor-md-master/lib/",
  	                toolbarIcons: function () {
  	                    // Or return editormd.toolbarModes[name]; // full, simple, mini
  	                    // Using "||" set icons align right.
  	                    return editormd.toolbarModes['simple'];
  	                },
  	                //toolbar  : false,             // 关闭工具栏
  	                codeFold: true,
  	                searchReplace: true,
  	                saveHTMLToTextarea: true,      // 保存 HTML 到 Textarea
  	                htmlDecode: "style,script,iframe|on*",            // 开启 HTML 标签解析，为了安全性，默认不开启
  	                emoji: true,
  	                taskList: true,
  	                tocm: true,          // Using [TOCM]
  	                tex: true,                      // 开启科学公式 TeX 语言支持，默认关闭
  	                //previewCodeHighlight : false,  // 关闭预览窗口的代码高亮，默认开启
  	                flowChart: true,                // 疑似 Sea.js与 Raphael.js 有冲突，必须先加载 Raphael.js ，Editor.md 才能在 Sea.js 下正常进行；
  	                sequenceDiagram: true,          // 同上
  	                //dialogLockScreen : false,      // 设置弹出层对话框不锁屏，全局通用，默认为 true
  	                //dialogShowMask : false,     // 设置弹出层对话框显示透明遮罩层，全局通用，默认为 true
  	                //dialogDraggable : false,    // 设置弹出层对话框不可拖动，全局通用，默认为 true
  	                //dialogMaskOpacity : 0.4,    // 设置透明遮罩层的透明度，全局通用，默认值为 0.1
  	                //dialogMaskBgColor : "#000", // 设置透明遮罩层的背景颜色，全局通用，默认为 #fff
  	                imageUpload: true,
  	                imageFormats: ["jpg", "jpeg", "gif", "png", "bmp", "webp"],
  	                imageUploadURL: "{:url('api/uploader/uploadEditorImg?pic_type=10')}",
  	            });


  	            $("#submit").click(function () {
  	                var param = $("#article_form").serialize();
  	                $.post('${pageContext.request.contextPath}/save_topic', param)
  	                        .done(function (res) {
  	                                alert(res);
  	                                return false;//阻止默认行为
  	                        })
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
    <input type="hidden" id="login_kiss" value="${ login_user.kissnum }">
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
        <a href="${ pageContext.request.contextPath }/user/logout"><i class="iconfont icon-tuichu" style="top: 0; font-size: 22px;"></i>退了</a>
      </div>
      </c:if>
    </div>
  </div>
</div>

<div class="main layui-clear">
  <div class="fly-panel" pad20>
    <h2 class="page-title">发表问题</h2>
    <div class="layui-form layui-form-pane">
     <!-- 文章表单 -->
      <form id="addartform" action="${ pageContext.request.contextPath }/article/add" method="post">
        <div class="layui-form-item">
          <label for="L_title" class="layui-form-label">标题</label>
          <div class="layui-input-block">
            <input type="text" id="L_title" name="title" required lay-verify="required" autocomplete="off" class="layui-input">
          </div>
        </div>
        <div class="layui-form-item layui-form-text">
         <!--  <div class="layui-input-block">
            <textarea id="L_content" name="content" required lay-verify="required" placeholder="请输入内容" class="layui-textarea fly-editor" style="height: 260px;"></textarea>
          </div> -->
          <!-- markdown编辑器 -->
          <div class="editormd" id="test-editormd">
       		 <textarea class="editormd-markdown-textarea" name="topic_markdown_content" id = "topic_markdown_content"></textarea>
    	  </div>
          <label for="L_content" class="layui-form-label" style="top: -2px;">描述</label>
        </div>
        
        <!-- 渲染模板 -->
        <script type="text/html" id="arttest">
			{{ each  artlist as value i}}
				<option value="{{value.id}}" >{{value.typename}}</option> 
			{{/each}}
		</script>
        
        <div class="layui-form-item">
          <div class="layui-inline">
            <label class="layui-form-label">所在类别</label>
            <div class="layui-input-block">
              <select id="artype" lay-verify="required" name="type">
				
              </select>
            </div>
          </div>
          <div class="layui-inline">
            <label class="layui-form-label">悬赏飞吻</label>
            <div class="layui-input-block">
              <select name="paykiss" id="cost_kiss">
                <option value="5" selected>5</option>
                <option value="20">20</option>
                <option value="50">50</option>
                <option value="100">100</option>
              </select>
            </div>
          </div>
        </div>
         <div id="kissmsg" class="layui-form-mid layui-word-aux" style="color:red"></div>
        <div class="layui-form-item">
          <button class="layui-btn" lay-submit>立即发布</button>
        </div>
      </form>
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