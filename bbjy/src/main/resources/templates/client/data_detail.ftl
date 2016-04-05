        <div class="dialog-title">
            <label class="title">${menuName!''}</label>
            <a href="javascript:;" class="dialog-close" title="">关闭窗口</a>
        </div>
        <#if tdArticle??>
        <div class="dialog-content">
            <div class="detail-title">
                <span class="title">
                <#if tdArticle.title??>
                    <#if tdArticle.title?length gt 40>
                    	${tdArticle.title[0..39]}...
                    <#else>
                    	${tdArticle.title!''}
                    </#if>
                </#if>
                </span>
                <span class="publishdate">發布時間 : <#if tdArticle.updateTime??>${tdArticle.updateTime?string("yyyy-MM-dd HH:mm")}</#if></span>
            </div>
            <div class="detail-content">
                ${tdArticle.content!''}
            </div>
        </div>
        </#if>
