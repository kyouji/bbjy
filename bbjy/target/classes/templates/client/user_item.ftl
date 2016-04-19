<div class="addrecorditem">
    <img src="/client/crmimages/avatar.png" class="avatar" alt=""/>
    <div class="addform">
        <textarea name="info" id="info"></textarea>

        <div class="uploadannex">
            <span>附件</span>
            <input type="file">
        </div>
        <span class="oclock">提醒</span>
        <input type="text" class="inputdate" id="datetimepicker1" readonly/>
        <input type="text" class="inputtitle" id="theme"/>

        <div class="btnwrap">
            <input type="button" onclick="addItem();"  class="btnsubmit" value="提交"/>
        </div>

    </div>
    <script>
        $('#datetimepicker1').datetimepicker({
            format: 'Y-m-d',
            lang: 'ch',
            formatDate: 'Y-m-d',
            timepicker: false,
            onSelectDate: function () {
            }
        });
        
        $(function(){
            $('.editicon').editclick('.edititem');
        });
    </script>

</div>
<ul class="recordlist">
    <#if user?? && user.itemLIst??>
    <#list user.itemLIst as item>
        <li>
            <img src="${item.imagesUrl!'/client/crmimages/default.jpg'}" class="avatar" alt="${item.manager!''}"/>
            <div class="recordinfo">
                <span class="title"><label>${item.manager!''}</label>【提醒：<#if item.itemTime??>${item.itemTime?string('yyyy-MM-dd')}</#if>  ${item.theme!''}】</span>
    
                <p>${item.info!''}</p>
                <label class="date"><#if item.createTime??>${item.createTime?string('yyyy-MM-dd HH:mm:ss')}</#if></label>
            </div>
        </li>
    </#list>
    </#if>
</ul>