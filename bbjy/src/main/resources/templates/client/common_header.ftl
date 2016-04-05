<div class="crmheader">
    <h1 class="h1key">宝贝金融</h1>
    <a href="/" class="logo" title="">首页</a>

    <div class="headernav">
        <a href="/" title="宝贝金融首页" <#if !active?? || active?? && active=1>class="current"</#if>>首页</a>
        <a href="/data/info" title="讯息" <#if active?? && active=2>class="current"</#if>>讯息</a>
        <a href="javascript:void(0)" title="暂未开放">客户</a>
        <a href="javascript:void(0)" title="暂未开放">保单</a>
        <a href="/data/source" title="行销资源" <#if active?? && active=5>class="current"</#if>>行销资源</a>
        <a href="javascript:void(0)" title="暂未开放">个人管理</a>
        <a href="javascript:void(0)" title="暂未开放">设置</a>
    </div>
    <div class="search-wrap">
        <input type="text"/>
        <input type="button" class="btnsubmit"/>
    </div>
    <div class="header-avatar">
        <span class="avatar">
        	<img src="<#if user??&&user.headImageUrl??&&user.headImageUrl != "">${user.headImageUrl}<#else>/client/crmimages/default.jpg</#if>" width=54 height=54 alt="头像"/>
        </span>
        <div class="welcome" id="managerhover">
            <label><#if user??>欢迎,${user.username!''}<#else>请登陆</#if></label>
            <div class="wrap_manager">
                <a href="javascript:void(0)" title="暂未开放">个人设置</a>
                <a href="/logout" title="">退出系统</a>
            </div>
        </div>
    </div>
</div>