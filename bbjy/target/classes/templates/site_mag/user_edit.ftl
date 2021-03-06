<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/mag/style/idialog.css" rel="stylesheet" id="lhgdialoglink">
<title>编辑会员信息</title>
<script type="text/javascript" src="/mag/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="/mag/js/Validform_v5.3.2_min.js"></script>
<script type="text/javascript" src="/mag/js/lhgdialog.js"></script>
<script type="text/javascript" src="/mag/js/WdatePicker.js"></script>
<script type="text/javascript" src="/mag/js/swfupload.js"></script>
<script type="text/javascript" src="/mag/js/swfupload.queue.js"></script>
<script type="text/javascript" src="/mag/js/swfupload.handlers.js"></script>
<script type="text/javascript" src="/mag/js/layout.js"></script>
<link href="/mag/style/style.css" rel="stylesheet" type="text/css">
<link href="/mag/style/WdatePicker.css" rel="stylesheet" type="text/css">

<!-- 企业资料样式 -->

<link href="/client/css/team.css" rel="stylesheet" type="text/css" />

<style>
.apply_content dd div .hide{display:none;}
.apply_content dd .hide{display:none;}
.apply_content dt .hide{display:none;}
.apply_content .hide{display:none;}

</style>
<script>
$(document).ready(function(){

	$("#step1").Validform({
			tiptype:4,
	  	    ajaxPost:true,
            callback: function (data) { 
	            if (data.code == 0)
	            {
					$.dialog.alert("提交成功");
				    $(".menu").removeClass("selected");
    				$(".menu").eq(1).addClass("selected");
				    $(".tab-content").hide();
    				$(".tab-content").eq(1).show();
				}
	            else 
	            {
	                $.dialog.alert(data.msg);
	                if (data.check == 0)
	                {
	                	location.href='/Verwalter/login';
	                }
	                else if (data.check ==1)
	                {
	                	location.href='/enterprise/check';
	                }
	            }
       		 }
	});
	
	$("#dataUpload").Validform({
			tiptype:4,
	  	    ajaxPost:true,
            callback: function (data) { 
	            if (data.code == 0)
	            {
					$.dialog.alert("提交成功");
				}
	            else 
	            {
	                $.dialog.alert(data.msg);
	                if (data.check == 0)
	                {
	                	location.href='/Verwalter/login';
	                }
	                else if (data.check ==1)
	                {
	                	location.href='/enterprise/check';
	                }
	            }
       		 }
	});
});

function showEnter(){
	$(".enter").css("display","block");
	$(".pro").css("display","none");
}
function showPro(){
	$(".pro").css("display","block");
	$(".enter").css("display","none");
}

function forbidsubmit()
{
	$("#submitbutton").attr("disabled",true);
	$("#submitbutton").css("background","#666666");
}

function allowsubmit()
{
	$("#submitbutton").removeAttr("disabled");
	$("#submitbutton").css("background","#e67817");
}

function setStatusId(id , statusId)
{
	
	    $.ajax({
        type:"post",
        url:"/Verwalter/user/enterprise/status",
        data:{"id":id,"statusId":statusId},
        success:function(data){
           if (data.code == 0)
           {
            	location.reload();
            }
            else{
            	alert(msg);
            	location.href='/Verwalter/login';
            }
        }
    });
}

function done()
{
    $.dialog.alert("上传资料成功！");	
}
<#if done?? &&done == 1>
window.onload=done;
</#if>

</script>
<script type="text/javascript">
$(function () {
    //初始化表单验证
    $("#form_user").initValidform();

    //初始化上传控件
    $(".upload-img").each(function () {
        $(this).InitSWFUpload({ 
            sendurl: "/Verwalter/dataUpload", 
            flashurl: "/mag/js/swfupload.swf"
        });
    });

<#--

        //（缩略图1）
    var txtPic = $("#txtImgUrl1").val();
    if (txtPic == "" || txtPic == null) {
        $(".thumb_ImgUrl_show1").hide();
    }
    else {
        $(".thumb_ImgUrl_show1").html("<ul><li><div class='img-box1'><img src='/images/" + txtPic + "' bigsrc='" + txtPic + "' /></div></li></ul>");
        $(".thumb_ImgUrl_show1").show();
    }
            //（缩略图2）
    var txtPic = $("#txtImgUrl2").val();
    if (txtPic == "" || txtPic == null) {
        $(".thumb_ImgUrl_show2").hide();
    }
    else {
        $(".thumb_ImgUrl_show2").html("<ul><li><div class='img-box1'><img src='/images/" + txtPic + "' bigsrc='" + txtPic + "' /></div></li></ul>");
        $(".thumb_ImgUrl_show2").show();
    }
            //（缩略图3）
    var txtPic = $("#txtImgUrl3").val();
    if (txtPic == "" || txtPic == null) {
        $(".thumb_ImgUrl_show3").hide();
    }
    else {
        $(".thumb_ImgUrl_show3").html("<ul><li><div class='img-box1'><img src='/images/" + txtPic + "' bigsrc='" + txtPic + "' /></div></li></ul>");
        $(".thumb_ImgUrl_show3").show();
    }
            //（缩略图4）
    var txtPic = $("#txtImgUrl4").val();
    if (txtPic == "" || txtPic == null) {
        $(".thumb_ImgUrl_show4").hide();
    }
    else {
        $(".thumb_ImgUrl_show4").html("<ul><li><div class='img-box1'><img src='/images/" + txtPic + "' bigsrc='" + txtPic + "' /></div></li></ul>");
        $(".thumb_ImgUrl_show4").show();
    }
            //（缩略图5）
    var txtPic = $("#txtImgUrl5").val();
    if (txtPic == "" || txtPic == null) {
        $(".thumb_ImgUrl_show5").hide();
    }
    else {
        $(".thumb_ImgUrl_show5").html("<ul><li><div class='img-box1'><img src='/images/" + txtPic + "' bigsrc='" + txtPic + "' /></div></li></ul>");
        $(".thumb_ImgUrl_show5").show();
    }

	  //(刷新图1)
    $("#txtImgUrl1").blur(function () {
        var txtPic = $("#txtImgUrl1").val();
        if (txtPic == "" || txtPic == null) {
            $(".thumb_ImgUrl_show").hide();
        }
        else {
            $(".thumb_ImgUrl_show").html("<ul><li><div class='img-box1'><img src='/images/" + txtPic + "' bigsrc='" + txtPic + "' /></div></li></ul>");
            $(".thumb_ImgUrl_show").show();
        }
    });  
    	  //(刷新图2)
    $("#txtImgUrl2").blur(function () {
        var txtPic = $("#txtImgUrl2").val();
        if (txtPic == "" || txtPic == null) {
            $(".thumb_ImgUrl_show2").hide();
        }
        else {
            $(".thumb_ImgUrl_show2").html("<ul><li><div class='img-box1'><img src='/images/" + txtPic + "' bigsrc='" + txtPic + "' /></div></li></ul>");
            $(".thumb_ImgUrl_show2").show();
        }
    });  
    	  //(刷新图3)
    $("#txtImgUrl3").blur(function () {
        var txtPic = $("#txtImgUrl3").val();
        if (txtPic == "" || txtPic == null) {
            $(".thumb_ImgUrl_show3").hide();
        }
        else {
            $(".thumb_ImgUrl_show3").html("<ul><li><div class='img-box1'><img src='/images/" + txtPic + "' bigsrc='" + txtPic + "' /></div></li></ul>");
            $(".thumb_ImgUrl_show3").show();
        }
    });  
    	  //(刷新图4)
    $("#txtImgUrl4").blur(function () {
        var txtPic = $("#txtImgUrl4").val();
        if (txtPic == "" || txtPic == null) {
            $(".thumb_ImgUrl_show4").hide();
        }
        else {
            $(".thumb_ImgUrl_show4").html("<ul><li><div class='img-box1'><img src='/images/" + txtPic + "' bigsrc='" + txtPic + "' /></div></li></ul>");
            $(".thumb_ImgUrl_show4").show();
        }
    });  
    	  //(刷新图5)
    $("#txtImgUrl5").blur(function () {
        var txtPic = $("#txtImgUrl5").val();
        if (txtPic == "" || txtPic == null) {
            $(".thumb_ImgUrl_show5").hide();
        }
        else {
            $(".thumb_ImgUrl_show5").html("<ul><li><div class='img-box1'><img src='/images/" + txtPic + "' bigsrc='" + txtPic + "' /></div></li></ul>");
            $(".thumb_ImgUrl_show5").show();
        }
    });  
    	  //(刷新图2)
    $("#txtImgUrl2").blur(function () {
        var txtPic = $("#txtImgUrl2").val();
        if (txtPic == "" || txtPic == null) {
            $(".thumb_ImgUrl_show2").hide();
        }
        else {
            $(".thumb_ImgUrl_show2").html("<ul><li><div class='img-box1'><img src='/images/" + txtPic + "' bigsrc='" + txtPic + "' /></div></li></ul>");
            $(".thumb_ImgUrl_show2").show();
        }
    });  
    	  //(刷新图3)
    $("#txtImgUrl3").blur(function () {
        var txtPic = $("#txtImgUrl3").val();
        if (txtPic == "" || txtPic == null) {
            $(".thumb_ImgUrl_show3").hide();
        }
        else {
            $(".thumb_ImgUrl_show3").html("<ul><li><div class='img-box1'><img src='/images/" + txtPic + "' bigsrc='" + txtPic + "' /></div></li></ul>");
            $(".thumb_ImgUrl_show3").show();
        }
    });  
    	  //(刷新图4)
    $("#txtImgUrl4").blur(function () {
        var txtPic = $("#txtImgUrl4").val();
        if (txtPic == "" || txtPic == null) {
            $(".thumb_ImgUrl_show4").hide();
        }
        else {
            $(".thumb_ImgUrl_show4").html("<ul><li><div class='img-box1'><img src='/images/" + txtPic + "' bigsrc='" + txtPic + "' /></div></li></ul>");
            $(".thumb_ImgUrl_show4").show();
        }
    });  
    	  //(刷新图5)
    $("#txtImgUrl5").blur(function () {
        var txtPic = $("#txtImgUrl5").val();
        if (txtPic == "" || txtPic == null) {
            $(".thumb_ImgUrl_show5").hide();
        }
        else {
            $(".thumb_ImgUrl_show5").html("<ul><li><div class='img-box1'><img src='/images/" + txtPic + "' bigsrc='" + txtPic + "' /></div></li></ul>");
            $(".thumb_ImgUrl_show5").show();
        }
    });  
    -->
    //设置封面图片的样式
    $(".photo-list ul li .img-box img").each(function () {
        if ($(this).attr("src") == $("#hidFocusPhoto").val()) {
            $(this).parent().addClass("selected");
        }
    }); 
});   

</script>
</head>

<body class="mainbody">

<!--导航栏-->
<div class="location" style="position: static; top: 0px;">
  <a href="/Verwalter/user/list/${roleId!""}"><i></i><span>返回列表页</span></a>
  <a href="/Verwalter/center" class="home"><i></i><span>首页</span></a>
  <i class="arrow"></i>
  <span>用户管理</span>
  <i class="arrow"></i>
  	<span>编辑信息</span>
</div>
<div class="line10"></div>
<!--/导航栏-->

<!--内容-->
<div class="content-tab-wrap">
  <div id="floatHead" class="content-tab" style="position: static; top: 52px;">
    <div class="content-tab-ul-wrap">
      <ul>
          <li><a href="javascript:;" onclick="tabs(this);" class="menu">安全设置</a></li>
        <#--
        <li><a href="javascript:;" onclick="tabs(this);">账户信息</a></li>
        -->
      </ul>
    </div>
  </div>
</div>


<!--安全设置-->
<form name="form_user" method="post" action="/Verwalter/user/save" id="form_user">
<div class="tab-content" >  

<div>
<input type="hidden" name="__VIEWSTATE" id="__VIEWSTATE" value="${__VIEWSTATE!""}" >
<input type="hidden" name="userId" value="<#if user??>${user.id!""}</#if>" >
<input type="hidden" name="roleId" value="<#if user??>${user.roleId!""}<#else>0</#if>" >
</div>
 <dl>
    <dt>用户状态</dt>
    <dd>
      <div class="rule-multi-radio">
        <span id="rblStatus">
            <#--
            <input type="radio" name="statusId" value="0" datatype="n" <#if user?? && user.statusId?? && user.statusId==0>checked="checked"</#if>>
            <label>待审核</label>
            -->
            <input type="radio" name="statusId" value="1" datatype="n" <#if !user?? || user.statusId?? && user.statusId==1>checked="checked"</#if>>
            <label>正常</label>
            <input type="radio" name="statusId" value="2" datatype="n" <#if user?? && user.statusId?? && user.statusId==2>checked="checked"</#if>>
            <label>禁用</label>
        </span>
      </div>
      <span class="Validform_checktip">*禁用账户无法登录</span>
    </dd>
  </dl>
    <dl>
        <dt>用户名：</dt>
        <dd>
            <#if user??>
                <span>${user.username!""}</span>
            <#else>
                <input name="username" type="text" maxlength="200" class="input normal" datatype="s6-20" ajaxurl="/Verwalter/user/check<#if user??>?id=${user.id}</#if>" sucmsg=" " minlength="2">
            </#if>
            <span class="Validform_checktip">
        </span></dd>
    </dl>
  <dl>
    <dt>登录密码</dt>
    <dd><input name="password" type="password" value="<#if user??>${user.password!''}</#if>" class="input normal" datatype="*6-20" nullmsg="请设置密码" errormsg="密码范围在6-20位之间" sucmsg=" " value=""> <span class="Validform_checktip">*登录的密码，至少6位</span></dd>
  </dl>
  <dl>
    <dt>确认密码</dt>
    <dd><input name="password1" type="password" value="<#if user??>${user.password!''}</#if>" class="input normal" datatype="*" recheck="password" nullmsg="请再输入一次密码" errormsg="两次输入的密码不一致" sucmsg=" " value=""> <span class="Validform_checktip">*再次输入密码</span></dd>
  </dl>
  <dl>
    <dt>邮箱账号</dt>
    <dd><input name="email" type="text" value="<#if user??>${user.email!""}</#if>" id="txtEmail" class="input normal" datatype="e" ignore="ignore" sucmsg=" " > </dd>
  </dl>
  <dl>
    <dt>手机号码</dt>
    <dd><input name="mobile" type="text" value="<#if user??>${user.mobile!""}</#if>" class="input normal" datatype="m|/^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/" ajaxurl="/Verwalter/user/check/mobile<#if user??>?id=${user.id?c!''}</#if>" sucmsg=" " ><span class="Validform_checktip">*联系手机号</span></dd>
  </dl>
  


<!--/安全设置-->


   <!--工具栏-->
<div class="page-footer">
  <div class="btn-list">
    <input type="submit" name="btnSubmit" value="提交保存" id="btnSubmit" class="btn">
    <input name="btnReturn" type="button" value="返回上一页" class="btn yellow" onclick="javascript:history.back(-1);">
  </div>
  <div class="clear"></div>

</div>
<!--/工具栏-->
</div>



 </form>

</body></html>