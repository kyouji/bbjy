        <div class="dialog-title">
            <label class="title">${menuName!''}</label>
            <a href="javascript:;" class="dialog-close" title="">关闭窗口</a>
        </div>
        <#if tdArticle??>
        <div class="dialog-content">
            <div class="detail-title">
                <span class="title"> ${tdArticle.title!''}</span>
            </div>
            <div class="detail-content">
               <div class="marketinglist">
                   <ul class="ulth">
                       <li class="li01">文件名称</li>
                       <li>使用说明</li>
                       <li class="li02">下载</li>
                   </ul>
                   <#if tdArticle.dataList??>
                   <#list tdArticle.dataList as data>
	                   <ul>
	                       <li class="li01">
	                       <a title="${data.title!''}" style="text-decoration:none;">
	                       	<#if data.title??&&data.title?length gt 0>
	                       		<#if data.title??>
				                    <#if data.title?length gt 20>
				                    	${data.title[0..19]}...
				                    <#else>
				                    	${data.title!''}
				                    </#if>
				                </#if>
	                       	<#else>
	                       		<#if tdArticle.title??>
				                    <#if tdArticle.title?length gt 20>
				                    	${tdArticle.title[0..19]}...
				                    <#else>
				                    	${tdArticle.title!''}
				                    </#if>
				                </#if>
	                       	</#if>
	                       	</a></li>
	                       <li><a title="${data.brief!''}" style="text-decoration:none;">
	                       <#if data.brief?? && data.brief?length gt 0>
	                       		<#if data.brief??>
				                    <#if data.brief?length gt 20>
				                    	${data.brief[0..19]}...
				                    <#else>
				                    	${data.brief!''}
				                    </#if>
				                </#if>
	                       <#else>
	                       &nbsp;
	                       </#if>
	                       </a></li>
	                       <li class="li02">
	                           <a <#if data.url?? && data.url?length gt 0>
		                        href="/download/data?id=${data.id?c}" title="<#if data.filename?? && data.filename?length gt 0>${data.filename!''}<#else>${data.title!''}</#if>"
		                        <#else>
		                        href="javascript:void(0)" style="color:#666666;"
		                        </#if> class="a_pdf" >下载</a>
	                       </li>
	                   </ul>
                   </#list>
                   </#if>
               </div>
                
            </div>
        </div>
        </#if>