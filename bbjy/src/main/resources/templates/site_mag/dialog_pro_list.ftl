<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>编辑资料</title>
<script type="text/javascript" src="/mag/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="/mag/js/layout.js"></script>
<script type="text/javascript" src="/mag/js/jquery.lazyload.min.js"></script>
<script type="text/javascript" src="/mag/js/lhgdialog.js?skin=idialog"></script>
<script type="text/javascript" src="/mag/js/WdatePicker.js"></script>
<script type="text/javascript" src="/mag/js/swfupload.js"></script>
<script type="text/javascript" src="/mag/js/swfupload.queue.js"></script>
<script type="text/javascript" src="/mag/js/swfupload.handlers.js"></script>
<link href="/mag/style/style.css" rel="stylesheet" type="text/css">
<link href="/mag/style/pagination.css" rel="stylesheet" type="text/css">

<script type="text/javascript">

$(document).ready(function(){
    //初始化上传控件
$(".upload-img").each(function () {
    $(this).InitSWFUpload({ 
        sendurl: "/Verwalter/dataUpload", 
        flashurl: "/mag/js/swfupload.swf",
        filetypes: "*.*;" 
    });
});
});
    //窗口API
    var api = frameElement.api, W = api.opener;
    api.button({
        name: '确定',
        focus: true,
        callback: function () {
            submitForm();
            return false;
        }
    }, {
        name: '取消'
    });
    

    //页面加载完成执行
    $(function () {
        if ($(api.data).length > 0) {
            var parentObj = $(api.data).parent().parent(); //取得节点父对象            
            //开始赋值
            $("#txtItemtdFinance_title").val($(parentObj).find("input[id='title']").val()); 
            $("#firstYear").val($(parentObj).find("input[id='firstYear']").val()); 
            $("#second").val($(parentObj).find("input[id='second']").val()); 
            $("#third").val($(parentObj).find("input[id='third']").val()); 
            $("#fourth").val($(parentObj).find("input[id='fourth']").val()); 
            $("#fifth").val($(parentObj).find("input[id='fifth']").val()); 
            $("#sixth").val($(parentObj).find("input[id='sixth']").val()); 
            $("#seventh").val($(parentObj).find("input[id='seventh']").val()); 
        }
    });

    //创建促销赠品窗口
    function show_goods_select_dialog(obj) {
        var objNum = arguments.length;
        var zengpinDialog = $.dialog({
            id: 'zengpinhDialogId',
            lock: true,
            max: false,
            min: false,
            title: "促销赠品",
            content: 'url:/Verwalter/goods/list/dialog/gift',
            width: 800,
            height: 550
        });
        //如果是修改状态，将对象传进去
        if (objNum == 1) {
            zengpinDialog.data = obj;
        }
    }
    
    //删除促销赠品节点
    function del_goods_gift(obj) {
        $(obj).parent().parent().remove();
    }
    
    //提交表单处理
    function submitForm() {
        //验证表单
        if ($("#txtItemtdFinance_title").val() == ""
          || $("#fileUrl").val() == "") {
            W.$.dialog.alert('请填写完整信息！', function () { $("#txtItemtdFinance_title").focus(); }, api);
            return false;
        }

        //添加或修改
        if ($(api.data).length > 0) {
        
            var parentObj = $(api.data).parent().parent();
		 
            parentObj.find("input[id='title']").val($("#txtItemtdFinance_title").val());
            parentObj.find("input[id='firstYear']").val($("#firstYear").val());
            parentObj.find("input[id='second']").val($("#second").val());
            parentObj.find("input[id='third']").val($("#third").val());
            parentObj.find("input[id='fourth']").val($("#fourth").val());
            parentObj.find("input[id='fifth']").val($("#fifth").val());
            parentObj.find("input[id='sixth']").val($("#sixth").val());
            parentObj.find("input[id='seventh']").val($("#seventh").val());
        } else {
		 
            var trHtml = '<tr class="td_c">'
            + '<td><input name="dataList[${total!'0'}].id" type="hidden" value="" />'
            + '<input type="text" name="dataList[${total!'0'}].sortId" class="td-input" value="99" style="width:90%;" /></td>'
            + '<td><input type="text" id="title" name="dataList[${total!'0'}].title" class="td-input" value="' + $("#txtItemtdFinance_title").val() + '" style="width:90%;" /></td>'
            + '<td><input type="text" id="firstYear" name="dataList[${total!'0'}].firstYear" class="td-input" value="' + $("#firstYear").val() + '" style="width:90%;" /></td>'
            + '<td><input type="text" id="second" name="dataList[${total!'0'}].second" class="td-input" value="' + $("#second").val() + '" style="width:90%;" /></td>'
            + '<td><input type="text" id="third" name="dataList[${total!'0'}].third" class="td-input" value="' + $("#third").val() + '" style="width:90%;" /></td>'
            + '<td><input type="text" id="fourth" name="dataList[${total!'0'}].fourth" class="td-input" value="' + $("#fourth").val() + '" style="width:90%;" /></td>'
            + '<td><input type="text" id="fifth" name="dataList[${total!'0'}].fifth" class="td-input" value="' + $("#fifth").val() + '" style="width:90%;" /></td>'
            + '<td><input type="text" id="sixth" name="dataList[${total!'0'}].sixth" class="td-input" value="' + $("#sixth").val() + '" style="width:90%;" /></td>'
            + '<td><input type="text" id="seventh" name="dataList[${total!'0'}].seventh" class="td-input" value="' + $("#seventh").val() + '" style="width:90%;" /></td>'
            + '<td>'
            + '<i class="icon"></i>'
            + '<a title="编辑" class="img-btn edit operator" onclick="show_goods_gift_dialog(this);">编辑</a>'
            + '<a title="删除" class="img-btn del operator" onclick="del_goods_gift(this);">删除</a>'
            + '</td>'
            + '</tr>'
            //如果是窗口调用则添加到窗口
            if (!api.get('dialogChannel') || !api.get('dialogChannel')) {
                $("#var_box_pro", W.document).append(trHtml);
                $("#totalGift", W.document).val(parseInt($("#totalGift", W.document).val())+1);
            } else {
                $("#var_box_pro", api.get('dialogChannel').document).append(trHtml);
                $("#totalGift", W.document).val(parseInt($("#totalGift", W.document).val())+1);
            }
        }
        api.close();
    }
    
    $(function () {
        $(".itemzengpin_select").click(function () {

            var itemzengpin_title = $(this).attr("itemzengpin_title");
            var itemzengpin_id = $(this).attr("itemzengpin_id");


            $("#txtItemtdFinance_title").val(itemzengpin_title);
            $("#txtItemtdFinance_Id").val(itemzengpin_id);
        });
    });
    

</script>
</head>
 
<body>
<div class="div-content">
    <dl>
      <dt>标题<b style="color:red;">*</b></dt>
      <dd>
        <input type="text" id="txtItemtdFinance_title" class="input normal">
        <span class="Validform_checktip">*</span>
      </dd>
    </dl>
    <dl>
      <dt>第一年</dt>
      <dd>
        <input type="text" id="firstYear" class="input normal" > 
        <span class="Validform_checktip">*</span>
      </dd>
    </dl>
    <dl>
      <dt>第二年</dt>
      <dd>
        <input type="text" id="second" class="input normal"> 
        <span class="Validform_checktip">*</span>
      </dd>
    </dl>
    <dl>
      <dt>第三年</dt>
      <dd>
        <input type="text" id="third" class="input normal" > 
        <span class="Validform_checktip">*</span>
      </dd>
    </dl>
    <dl>
      <dt>第四年</dt>
      <dd>
        <input type="text" id="fourth" class="input normal" > 
        <span class="Validform_checktip">*</span>
      </dd>
    </dl>
    <dl>
      <dt>第五年</dt>
      <dd>
        <input type="text" id="fifth" class="input normal" > 
        <span class="Validform_checktip">*</span>
      </dd>
    </dl>
    <dl>
      <dt>第六年</dt>
      <dd>
        <input type="text" id="sixth" class="input normal"> 
        <span class="Validform_checktip">*</span>
      </dd>
    </dl>
    <dl>
      <dt>第七年</dt>
      <dd>
        <input type="text" id="seventh" class="input normal" > 
        <span class="Validform_checktip">*</span>
      </dd>
    </dl>
</div>

</body>
</html>