/**
 * 列表栏点新增查看
 */
function showUser(id){
	$.ajax({
		type : "post",
		url : "/user/edit",
		data : {"id":id},
		success:function(data){
//        	$('#wrapcustomreinfo').css("display","block");
            $("#wrapcustomreinfo").html(data);
        }
	})
}

/**
 * 点击家庭关系修改
 * 
 */
function changeRela(id,re,userId){

	$.ajax({
		type : "post",
		url : "/family/edit",
		data : {"id":id,"relation":re,"userId":userId},
		success:function(data){
        	$('#wrapcustomreinfo').css("display","block");
            $("#wrapcustomreinfo").html(data);
        }
	})
}

/**
 * 新增事项
 * 
 */
function addItem(){
	var userId = parseInt($("#id").val());
	var info = $("#info").val();
	var ItemTime = $("#datetimepicker1").val();
	var theme =$("#theme").val();
	
	if(undefined == userId || ""== userId ||isNaN(userId))
	{
		alert("请先输入客户信息");
		return;
	}
	
	if(undefined == info || "" == info)
	{
		alert("请输入提醒内容");
		return ;
	}
	
	if(undefined == theme || "" == theme)
	{
		alert("请输入提醒主题");
		return ;
	}
	
	$.ajax({
		type : "post",
		url : "/Item/add",
		data : {"userId":userId,
			"info":info,
			"ItemTime":ItemTime,
			"theme":theme},
		success:function(data){
//        	$('#wrapcustomreinfo').css("display","block");
            $("#user_item").html(data);
        }
	})
	
}
/**
 * 新增家庭成员
 * 
 */
function addFamily(num){
	var fId = $("#fId"+num).val();
	var userId = parseInt($("#id").val());
	var username = $("#username"+num).val(); // 名字
	var englishName =$("#englishName"+num).val(); // 英文名
	var idCard =$("#idCard"+num).val(); // 身份证
	var marital =$("#marital"+num).val(); // 婚姻状况
	var nationality =$("#nationality"+num).val(); // 
	var sex =$("#sex"+num).val();
	var birthday =$("#birthday"+num).val();
	var nature =$("#nature"+num).val();
	var company =$("#company"+num).val();
	var workYear =$("#workYear"+num).val();
	var income =$("#income"+num).val();
	var position =$("#position"+num).val();
	var comAddress =$("#comAddress"+num).val();
	
	if(undefined == userId || ""== userId ||isNaN(userId))
	{
		alert("请先输入客户信息");
		return;
	}
	
	if(undefined == username || "" == username)
	{
		alert("请输入名字");
		return ;
	}
	
	$.ajax({
		type:"post",
        url:"/user/family/add",
        data:{"userId":userId,
        	"fId":fId,
        	"username":username,
        	"englishName":englishName,
        	"idCard":idCard,
        	"marital":marital,
        	"nationality":nationality,
        	"sex":sex,
        	"birthday":birthday,
        	"nature":nature,
        	"company":company,
        	"workYear":workYear,
        	"income":income,
        	"position":position,
        	"comAddress":comAddress,},
        success:function(data){
        //	$('#wrapcustomreinfo').css("display","block");
            $("#wrapcustomreinfo").html(data);
        }
	})
}

function editItem(id,statusId){
	$.ajax({
		type : "post",
		url : "/Item/edit",
		data : {"id":id,
			"statusId":statusId},
		success:function(data){
			window.location.reload();
        }
	})
}