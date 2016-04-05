<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/mag/style/idialog.css" rel="stylesheet" id="lhgdialoglink">
<title>编辑信息</title>
<script type="text/javascript" src="/mag/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="/mag/js/Validform_v5.3.2_min.js"></script>
<script type="text/javascript" src="/mag/js/lhgdialog.js"></script>
<script type="text/javascript" src="/mag/js/WdatePicker.js"></script>
<script type="text/javascript" src="/mag/js/swfupload.js"></script>
<script type="text/javascript" src="/mag/js/swfupload.queue.js"></script>
<script type="text/javascript" src="/mag/js/swfupload.handlers.js"></script>
<script type="text/javascript" charset="utf-8" src="/mag/js/kindeditor-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/mag/js/zh_CN.js"></script>
<script type="text/javascript" src="/mag/js/layout.js"></script>
<link href="/mag/style/WdatePicker.css" rel="stylesheet" type="text/css">
<link href="/mag/style/style.css" rel="stylesheet" type="text/css">
<link href="/mag/style/default.css" rel="stylesheet">
<script type="text/javascript">
    $(function () {
        //初始化表单验证
        $("#form1").initValidform();

        //初始化编辑器
        var editor = KindEditor.create('.editor', {
            width: '98%',
            height: '350px',
            resizeType: 1,
            filterMode: false,//是否开启过滤模式
            uploadJson: '/Verwalter/editor/upload?action=EditorFile',
            fileManagerJson: '/Verwalter/editor/upload?action=EditorFile',
            allowFileManager: true
        });
        
        //初始化上传控件
        $(".upload-img").each(function () {
            $(this).InitSWFUpload({ 
                sendurl: "/Verwalter/upload", 
                flashurl: "/mag/js/swfupload.swf",
                filetypes: "*.*;" 
            });
        });
        //批量上传 zhangji
        $(".upload-show360").each(function () {
            $(this).InitSWFUpload_show360({ 
                btntext: "批量上传", 
                btnwidth: 66, 
                single: false, 
                water: true, 
                thumbnail: true, 
                filesize: "5120", 
                sendurl: "/Verwalter/upload", 
                flashurl: "/mag/js/swfupload.swf", 
                filetypes: "*.jpg;*.jpge;*.png;*.gif;" 
            });
        });

        //（缩略图）
        var txtPic = $("#txtImgUrl").val();
        if (txtPic == "" || txtPic == null) {
            $(".thumb_ImgUrl_show").hide();
        }
        else {
            $(".thumb_ImgUrl_show").html("<ul><li><div class='img-box1'><img src='" + txtPic + "' bigsrc='" + txtPic + "' /></div></li></ul>");
            $(".thumb_ImgUrl_show").show();
        }

        $("#txtImgUrl").blur(function () {
            var txtPic = $("#txtImgUrl").val();
            if (txtPic == "" || txtPic == null) {
                $(".thumb_ImgUrl_show").hide();
            }
            else {
                $(".thumb_ImgUrl_show").html("<ul><li><div class='img-box1'><img src='" + txtPic + "' bigsrc='" + txtPic + "' /></div></li></ul>");
                $(".thumb_ImgUrl_show").show();
            }
        });
        
        //设置封面图片的样式
        $(".photo-list ul li .img-box img").each(function () {
            if ($(this).attr("src") == $("#hidFocusPhoto").val()) {
                $(this).parent().addClass("selected");
            }
        });
        
     // 添加弹窗
        $("#addItem").click(function(){
            showDialogItem();
        });
           
        //创建窗口
        function showDialogItem(obj) {
            var objNum = arguments.length;
            
            var giftDialog = $.dialog({
                id: 'giftDialogId',
                lock: true,
                max: false,
                min: false,
                title: "下载资料",
                content: 'url:/Verwalter/list/dialog/article?total=' + $("#var_box_gift").children("tr").length,
                width: 600,
                height: 300
            });
            
            //如果是修改状态，将对象传进去
            if (objNum == 1) {
                giftDialog.data = obj;
            }
        }
        
        //删除促销赠品节点
        function delGiftNode(obj) {
            $(obj).parent().parent().remove();
        }
        
        
    });
    
    //创建促销赠品窗口
function show_goods_gift_dialog(obj) {
            var objNum = arguments.length;
            
            var giftDialog = $.dialog({
                id: 'giftDialogId',
                lock: true,
                max: false,
                min: false,
                title: "下载资料",
                content: 'url:/Verwalter/list/dialog/article?total=' + $("#var_box_gift").children("tr").length,
                width: 600,
                height: 300
            });
            
            //如果是修改状态，将对象传进去
            if (objNum == 1) {
                giftDialog.data = obj;
            }
}
    
//删除促销商品节点
function del_goods_gift(obj) {
    $(obj).parent().parent().remove();
}


    $(document).ready(function(){
        //初始化可编辑字段
        var infoTypeId = null;
        $(".info-type").css("display","none");
        <#if article?? && article.categoryId??>
	        <#if category_list??>
	        	<#list category_list as item>
	        	<#if article.categoryId == item.id>
	        		infoTypeId = ${item.infoType?c};
        		</#if>
        		</#list>	
        	</#if>
        </#if>
        if(infoTypeId == null || infoTypeId == 0){
        	$(".info-type-0").css("display","block");
        }else if(infoTypeId == 1){
        	$(".info-type-1").css("display","block");
        }else if (infoTypeId == 2){
        	$(".info-type-2").css("display","block");
        }	
    });
    
    
    //根据讯息种类不同显示不同的可编辑字段
     $(function(){  
        $("#ddlCategoryId").change(function(){      
        var catId = $(this).children('option:selected').val();  
        var id = null;
        var infoType = null;
        <#if category_list??>
            <#list category_list as item>
            	<#if item.infoType??>
	            	id = ${item.id?c};
	            	infoType = ${item.infoType?c};
	            	if(id == catId){
	            		$(".info-type").css("display","none");
	            		
	            		if(infoType == 0 || infoType == null){
	            			$(".info-type-0").css("display","block");
	            		} else if (infoType == 1){
	            			$(".info-type-1").css("display","block");
	            		} else if (infoType == 2){
	            			$(".info-type-2").css("display","block");
	            		}
	            	}
        		</#if>
        	</#list>
        </#if>	
        });			
     });  
</script>
</head>
<body class="mainbody">
<form method="post" action="/Verwalter/article/save" id="form1">
<div>
<input type="hidden" name="__EVENTTARGET" id="__EVENTTARGET" value="${__EVENTTARGET!""}" />
<input type="hidden" name="__EVENTARGUMENT" id="__EVENTARGUMENT" value="${__EVENTARGUMENT!""}" />
<input type="hidden" name="__VIEWSTATE" id="__VIEWSTATE" value="${__VIEWSTATE!""}" />
</div>
<input name="menuId" type="text" value='${mid!""}' style="display:none;">
<input name="channelId" type="text" value='${cid!""}' style="display:none">
<input name="recommendId" type="text" value='<#if article??&&article.recommendId??>${article.recommendId?c!""}</#if>' style="display:none">
<input name="id" type="text" value='<#if article??>${article.id!""}</#if>' style="display:none">
    <!--导航栏-->
    <div class="location">
        <a href="/Verwalter/content/list?cid=${cid!""}&mid=${mid!""}" class="back"><i></i><span>
            返回列表页</span></a> 
        <a href="/Verwalter/center" class="home"><i></i><span>首页</span></a>
        <i class="arrow"></i>
        <a href="/Verwalter/content/list?cid=${cid!""}&mid=${mid!""}"><span>
            内容管理</span></a> <i class="arrow"></i><span>编辑信息</span>
    </div>
    <div class="line10">
    </div>
    <!--/导航栏-->
    <!--内容-->
    <div class="content-tab-wrap">
        <div id="floatHead" class="content-tab">
            <div class="content-tab-ul-wrap">
                <ul>
                    <li><a href="javascript:;" onclick="tabs(this);" class="selected">基本信息</a></li>
                    <li class="info-type-1  info-type"><a href="javascript:;" onclick="tabs(this);" class="">下载资料</a></li>
                    <li><a href="javascript:;" onclick="tabs(this);" class="">SEO选项</a></li>
                </ul>
            </div>
        </div>
    </div>
    <div class="tab-content" style="display: block;">
        <dl>
            <dt>所属类别</dt>
            <dd>
                <div class="rule-single-select">
                    <select name="categoryId" id="ddlCategoryId" datatype="*" sucmsg=" " nullmsg="请选择！" class="Validform_error" style="display: none;">
                    	<#if article??>
                    	<#else>
                    	<option value="">请选择类别...</option>
                    	</#if>
                        <#if category_list??>
                            <#list category_list as c>
                                <option value="${c.id!""}" <#if mid==11 && c.layerCount?? && c.layerCount == 1>disabled="disabled"</#if> <#if article?? && article.categoryId==c.id>selected="selected"</#if>><#if c.layerCount?? && c.layerCount gt 1><#list 1..(c.layerCount-1) as a>　</#list>├ </#if>${c.title!""}</option>
                            </#list>
                        </#if>
                    </select>
                </div>
                <#if article??>
                    <span class="Validform_checktip Validform_right"></span>
                <#else>
                    <span class="Validform_checktip Validform_wrong">请选择！</span>
                </#if>
            </dd>
        </dl>
        <dl>
            <dt>显示状态</dt>
            <dd>
                <div class="rule-multi-radio multi-radio">
                    <span id="rblStatus" style="display: none;">
                        <input type="radio" name="statusId" value="0" <#if !article?? || article?? && article.statusId?? && article.statusId==0>checked="checked"</#if> ><label>正常</label>
                        <input type="radio" name="statusId" value="1" <#if article?? && article.statusId?? && article.statusId==1>checked="checked"</#if>><label>待审核</label>
                        <input type="radio" name="statusId" value="2" <#if article?? && article.statusId?? && article.statusId==2>checked="checked"</#if>><label>不显示</label>
                    </span>
                </div>
            </dd>
        </dl>
        <dl>
            <dt class="info-type-0 info-type">内容标题</dt>
            <dt class="info-type-1 info-type">讯息名称</dt>
            <dt class="info-type-2 info-type">节日名</dt>
            <dd>
                <input name="title" type="text" value="<#if article??>${article.title!""}</#if>" id="txtTitle" class="input normal" datatype="*2-250" sucmsg=" ">
                <span class="Validform_checktip">*标题最多250个字符</span>
            </dd>
        </dl>
        
        <dl class="info-type-0  info-type">
            <dt class="info-type-0 info-type">附件</dt>
            <dd>
                <input name="imgUrl" type="text" id="txtImgUrl" value="<#if article??>${article.imgUrl!""}</#if>" class="input normal upload-path">
                <input name="filesize" type="text" class="upload-size" style="display:none;">
                <div class="upload-box upload-img "></div>
                <div class="photo-list thumb_ImgUrl_show" style="display: none;">
                    <ul>
                        <li>
                            <div class="img-box1 "></div>
                        </li>
                    </ul>
                </div>
            </dd>
        </dl>
        
        <dl class=" info-type-0 info-type">
	        <dt >文件名</dt>
	        <dd>
	            <input name="filename" type="text" id="filename" value="<#if article??>${article.filename!""}</#if>" class="input normal upload-path">
	            <span class="Validform_checktip">*下载文件的文件名称，不填则默认为讯息名称</span>
	        </dd>
	    </dl>
        
        <dl>
            <dt>排序数字</dt>
            <dd>
                <input name="sortId" type="text" value="<#if article??>${article.sortId!""}<#else>99</#if>" id="txtSortId" class="input txt100" datatype="n" sucmsg=" ">
                <span class="Validform_checktip">*数字，越小越向前</span>
            </dd>
        </dl>
        <dl>
            <dt>浏览次数</dt>
            <dd>
                <input name="viewCount" type="text" value="0" value="<#if article??>${article.viewCount!""}</#if>" id="txtClick" class="input txt100" datatype="n" sucmsg=" ">
                <span class="Validform_checktip">点击浏览该信息自动+1</span>
            </dd>
        </dl>

        <dl class="info-type-0 info-type">
            <dt>发布时间</dt>
            <dd>
                <div class="input-date">
                    <input name="createTime" type="text" id="txtAddTime" value="<#if article??>${article.createTime!""}</#if>" class="input date" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',lang:'zh-cn'})" datatype="/^\s*$|^\d{4}\-\d{1,2}\-\d{1,2}\s{1}(\d{1,2}:){2}\d{1,2}$/" errormsg="请选择正确的日期" sucmsg=" ">
                    <i>日期</i>
                </div>
                <span class="Validform_checktip">不选择默认当前发布时间</span>
            </dd>
        </dl>
        
        <dl class="info-type-1 info-type">
            <dt>更新时间</dt>
            <dd>
                <div class="input-date">
                    <input name="updateTime" type="text" id="txtUpdateTime" value="<#if article??>${article.updateTime!""}</#if>" class="input date" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',lang:'zh-cn'})" datatype="/^\s*$|^\d{4}\-\d{1,2}\-\d{1,2}\s{1}(\d{1,2}:){2}\d{1,2}$/" errormsg="请选择正确的日期" sucmsg=" ">
                    <i>日期</i>
                </div>
                <span class="Validform_checktip">不选择默认当前发布时间</span>
            </dd>
        </dl>

        <dl class="info-type-2 info-type">
            <dt>日期</dt>
            <dd>
                <div class="input-date">
                    <input name="theDate" type="text" id="date" value="<#if article??&&article.date??>${article.date?string("yyyy-MM-dd")}</#if>" class="input date" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',lang:'zh-cn'})"  errormsg="请选择正确的日期" sucmsg=" ">
                    <i>日期</i>
                </div>
                <span class="Validform_checktip"></span>
            </dd>
        </dl>        
        <#-- 批量上传 zhangji-->
        <#--
        <#if mid = 11 || mid = 10>
        <dl id="div_show360_container">
            <dt>展示图片</dt>
            <dd>
                <div class="upload-box upload-show360"></div>
                <div class="photo-list_show360">
                    <ul>
                        <#if article?? && article.showPictures??>
                            <#list article.showPictures?split(",") as uri>
                                <#if uri != "">
                                <li>
                                    <input type="hidden" name="hid_photo_name_show360" value="0|${uri!""}|${uri!""}">
                                    <div class="img-box">
                                        <img src="${uri!""}" bigsrc="${uri!""}">
                                    </div>
                                    <a href="javascript:;" onclick="delImg(this);">删除</a>
                                </li>
                                </#if>
                            </#list>
                        </#if>
                    </ul>
                </div>
            </dd>
        </dl>
        </#if>
        -->
        <#-- 批量上传 zhangji  end-->
        <#--<dl>
            <dt>URL链接</dt>
            <dd>
                <input name="linkUrl" type="text" value="<#if article??>${article.linkUrl!""}</#if>" maxlength="255" id="txtLinkUrl" class="input normal">
                <span class="Validform_checktip">填写后直接跳转到该网址</span>
            </dd>
        </dl>
        -->
        <dl id="div_source">

            <dt>
                <span id="div_source_title">信息来源</span></dt>
            <dd>
                <input name="source" type="text" value="<#if article??>${article.source!""}<#else>本站</#if>" id="field_control_source" class="input normal">
                <span id="div_source_tip" class="Validform_checktip">非必填，最多50个字符</span>
            </dd>
        </dl>
        
        <dl>
            <dt>备注</dt>
            <dd>
                <textarea name="brief" rows="2" cols="20" id="txtZhaiyao" class="input" datatype="*0-255" sucmsg=" "><#if article??>${article.brief!""}</#if></textarea>
                <span class="Validform_checktip"></span>
            </dd>
        </dl>
        <dl>
            <dt>内容描述</dt>
            <dd>
                <textarea name="content" class="editor" style="visibility:hidden;"><#if article??>${article.content!""}</#if></textarea>
            </dd>
        </dl>
    </div>
       
<div class="tab-content info-type-0  info-type" style="display: none;">

        <dl>
            <dt>资料下载</dt>
            <dd>
                <a id="addItem" class="icon-btn add"><i></i><span>添加资料</span></a>
                <span class="Validform_checktip"></span>
            </dd>
        </dl>
        <dl>
            <dt></dt>
            <dd>
                <table border="0" cellspacing="0" cellpadding="0" class="border-table" width="98%">
                    <thead>
                        <tr>
                            <th width="6%">
                                排序
                            </th>
                            <th width="15%">
                                标题
                            </th>
                            <th width="25%">
                                使用说明
                            </th>
                            <th width="5%">
                                下载次数
                            </th>
                            <th width="10%">
                                文件名
                            </th>
                            <th width="10%">
                                路径
                            </th>
                            <th width="6%">
                                操作
                            </th>
                        </tr>
                    </thead>
                    <tbody id="var_box_gift">
                    <#if article??>
                        <#if article.dataList??>
                            <#list article.dataList as gift>
                                <tr class="td_c">
                                    <td>
                                        <input type="hidden" name="dataList[${gift_index}].id"  value="${gift.id?c!''}">
                                        <input type="text" name="dataList[${gift_index}].sortId" class="td-input" value="${gift.sortId!''}" style="width:90%;">
                                    </td>
                                    <td>
                                        <input type="text" id="title" name="dataList[${gift_index}].title" class="td-input" value="${gift.title!''}" style="width:90%;">
                                    </td>
                                    <td>
                                        <input type="text" id="amount" name="dataList[${gift_index}].brief" class="td-input" value="${gift.brief!''}" style="width:90%;">
                                    </td>
                                    <td>
                                        <input type="text" id="count" name="dataList[${gift_index}].count" class="td-input" value="<#if gift.count??>${gift.count?c}<#else>0</#if>" style="width:90%;">
                                    </td>
                                    <td>
                                        <input type="text" id="price" name="dataList[${gift_index}].filename" class="td-input" value="${gift.filename!''}" style="width:90%;">
                                    </td>
                                    <td>
                                        <input type="text" id="money" name="dataList[${gift_index}].url" class="td-input" value="${gift.url!''}" style="width:90%;">
                                    </td>
                                    <td>
                                        <i class="icon"></i>
                                        <a title="编辑" class="img-btn edit operator" onclick="show_goods_gift_dialog(this);">编辑</a>
                                        <a title="删除" class="img-btn del operator" onclick="del_goods_gift(this);">删除</a>
                                    </td>
                                </tr>
                            </#list>
                        </#if>
                       </#if> 
                    </tbody>
                </table>
            </dd>
        </dl>
</div>  
    <div class="tab-content" style="display: none;">
        <dl>
            <dt>SEO标题</dt>
            <dd>
                <input name="seoTitle" type="text" maxlength="255" id="txtSeoTitle" value="<#if article??>${article.seoTitle!""}</#if>" class="input normal" datatype="*0-100" sucmsg=" ">
                <span class="Validform_checktip">255个字符以内</span>
            </dd>
        </dl>
        <dl>
            <dt>SEO关健字</dt>
            <dd>
                <textarea name="seoKeywords" rows="2" cols="20" id="txtSeoKeywords" class="input" datatype="*0-255" sucmsg=" "><#if article??>${article.seoKeywords!""}</#if></textarea>
                <span class="Validform_checktip">以“,”逗号区分开，255个字符以内</span>
            </dd>
        </dl>
        <dl>
            <dt>SEO描述</dt>
            <dd>
                <textarea name="seoDescription" rows="2" cols="20" id="txtSeoDescription" class="input" datatype="*0-255" sucmsg=" "><#if article??>${article.seoDescription!""}</#if></textarea>
                <span class="Validform_checktip">255个字符以内</span>
            </dd>
        </dl>
    </div>
    
    <!--/内容-->
    <!--工具栏-->
    <div class="page-footer">
        <div class="btn-list">
            <input type="submit" name="btnSubmit" value="提交保存" id="btnSubmit" class="btn">
            <input name="btnReturn" type="button" value="返回上一页" class="btn yellow" onclick="javascript:history.back(-1);">
        </div>
        <div class="clear">
        </div>
    </div>
    <!--/工具栏-->
    </form>


</body></html>