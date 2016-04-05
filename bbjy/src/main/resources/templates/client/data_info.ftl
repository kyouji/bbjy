<!DOCTYPE html><html lang="en"><head>    <meta charset="UTF-8">    <meta name="description" content=""/>    <meta name="keywords" content=""/>    <meta name="author" content=""/>    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1"/>    <title>讯息<#if catTitle??>-${catTitle!''}<#else>讯息</#if></title>    <!--[if lt IE 9]>    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>    <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>    <![endif]-->    <link rel="shortcut icon" href="/client/crmimages/icon.ico" />    <link rel="stylesheet" href="/client/css/crmglobal.css"/>    <link rel="stylesheet" href="/client/css/crmsite1.css"/>    <script src="/client/js/jquery-1.11.0.js" type="text/javascript"></script>    <script src="/client/js/crmcommon.js" type="text/javascript"></script></head><body><!-- crm头部 --><#include "/client/common_header.ftl" /><script>    $(function(){        showdetaildialog("#listdetailitem .dialoghref","datedialog");    });        function infoPage(catId,page){    	location.href='/data/info?catId='+catId+'&page='+page;    }        function selectInfoPage(catId,totalPage){    	var page = $("#select_page").val()-1;    	var totalPage = totalPage -1;    	if(page == ""){    		page = 0;    	}else if(page < 1){    		page= 0;    	}else if(page > totalPage){    		page = totalPage;    	}    	location.href='/data/info?catId='+catId+'&page='+page;    }</script><div class="container" id="contentwrap">    <div class="leftnav">        <span class="infoicon">讯息</span>        <#if cat_list??>        <#list cat_list as cat>        	<a href="/data/info?catId=${cat.id?c}" title="${cat.title!''}" <#if catId?? && catId == cat.id>class="current"</#if> >${cat.title!''}</a>        </#list>        </#if>    </div>    <div class="right-content">        <span class="contenttitle contenttitlebg"><#if catTitle??>${catTitle!''}<#else>讯息</#if></span>        <div class="incontent">            <div class="listitem" id="listdetailitem">        	<#if infoType??&& (infoType == 1 || infoType == 0)>                <ul class="ulth">                    <li class="li01">讯息名称</li>                    <li>备注</li>                    <li>文件大小</li>                    <li>更新时间</li>                </ul>                <#if info_page??>                <#list info_page.content as article>	                <ul>	                    <li class="li01"><label style="float:left;">${article_index+1}. </label><a href="javascript:;" class="dialoghref" articleId="${article.id?c}"  title="${article.title!''}">	                    <#if article.title??>		                    <#if article.title?length gt 30>		                    	${article.title[0..29]}...		                    <#else>		                    	${article.title!''}		                    </#if>	                    </#if>	                    </a></li>	                    <li><#if article.brief?? && article.brief?length gt 0>${article.brief!''}<#else>&nbsp;</#if></li>	                    <li><#if article.filesize??>${(article.filesize/1024)?string("0.0")}Kb<#else>无资料</#if></li>	                    <li>	                        <label><#if article.updateTime??>${article.updateTime?string("yyyy-MM-dd")}</#if></label>	                        <a 	                        <#if article.imgUrl?? && article.imgUrl?length gt 0>	                        href="/download?id=${article.id?c}" title="<#if article.filename??&&article.filename?length gt 0>${article.filename}<#else>${article.title}</#if>"	                        <#else>	                        href="javascript:void(0)" style="color:#666666;"	                        </#if> class="a_download" title="无资料">下载</a>	                    </li>	                </ul>                    </#list>                </#if>               <#elseif infoType??&&infoType == 2>             	<ul class="ulth">                    <li class="li03">节日名</li>                    <li>日期</li>                    <li>星期</li>                    <li class="li02">备注</li>                </ul>                <#if info_page??>                <#list info_page.content as article>	                <ul>	                    <li class="li03">${article.title!''}</li>	                    <li><#if article.date??>${article.date?string("MM月dd日")}<#else>&nbsp;</#if></li>	                    <li>	                    		<#if ("week"+article_index)?eval??>	                    			${("week"+article_index)?eval}	                    		<#else>	                    			未知	                    		</#if>	                    </li>	                    <li class="li02">${article.brief!''}</li>	                </ul>                    </#list>                </#if>                               </#if>            </div>            <div class="page">        	<#if info_page??>        	<#assign PAGE_DATA = info_page>                <label class="pagesite">                当前第${PAGE_DATA.number*size+1}到${PAGE_DATA.number*size+PAGE_DATA.numberOfElements}条，共${PAGE_DATA.totalElements}条</label>                <span class="disabled" onclick="javascript:infoPage(${catId!''},'0')" style="cursor:pointer;">首页</span>                <#assign continueEnter=false>            	<#if PAGE_DATA.number+1 == 1>                	<span class="disabled">&lt; 上一页</span>            	<#else>               		 <a href="javascript:infoPage(${catId!''},'${PAGE_DATA.number-1}')" class="lastPage">&lt;上一页</a>            	</#if>            					<#if PAGE_DATA.totalPages gt 0>                <#list 1..PAGE_DATA.totalPages as page>                    <#if page <= 3 || (PAGE_DATA.totalPages-page) < 3 || (PAGE_DATA.number+1-page)?abs<3 >                        <#if page == PAGE_DATA.number+1>                            <span class="current">${page}</span>                        <#else>                            <a href="javascript:infoPage(${catId!''},'${page-1}')" tcdNumber>${page}</a>                        </#if>                        <#assign continueEnter=false>                    <#else>                        <#if !continueEnter>                            ...                            <#assign continueEnter=true>                        </#if>                    </#if>                </#list>            	</#if>                            <#if PAGE_DATA.number+1 == PAGE_DATA.totalPages || PAGE_DATA.totalPages==0>	                <span class="disabled">下一页&gt;</span>	            <#else>	                <a href="javascript:infoPage(${catId!''},'${PAGE_DATA.number+1}')" class="nextPage">下一页&gt;</a>	            </#if>                <input type="text" id="select_page" value="${PAGE_DATA.number+1}"/>                <input type="button" value="确定" style="cursor:pointer;" onclick="javascript:selectInfoPage(${catId!''},${PAGE_DATA.totalPages})"/>            </#if>            </div>        </div>    </div>	<div class="dialogwrap dialog-detailinfo" id="datedialog">		<#include "/client/data_detail.ftl">	</div></div></body></html>