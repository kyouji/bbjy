<script type="text/javascript">
$(document).ready(function(){
    $("#form1").Validform({
        tiptype: 4,
        ajaxPost:true,
        callback:function(data){
            $('#wrapcustomreinfo').css("display","block");
            $("#wrapcustomreinfo").html(data.responseText);
        }
    });
})
</script>

<form action="/user/add" id="form1" method="post">
<input type="hidden" name="id" id="id" value="<#if user??>${user.id?c}</#if>">
<ul class="consultantinfo">
            <li>
                <label class="servicepeople">服务顾问：</label>

                <div class="warpedit">
                    <label class="lbltxt"><#if man??>${man.realName!''}</#if></label>

                    <div class="edititem">
                        <div class="select_item">
                            <input type="text" class="txtselect" name="txtselect" userid="0" value="<#if man??>${man.realName!''}</#if>" readonly/>
                            <input type="hidden" id="hideitem" name="managerid" value="<#if man??>${man.id?c}</#if>">
                            <div class="txtselect_item">
                                <div class="initem">
                                    <#if manager_list??>
                                    <#list manager_list as manager>
                                        <a href="JavaScript:;" userid="${manager.id?c}" title="">${manager.realName!''}</a>    
                                    </#list>
                                    </#if>
                                </div>
                            </div>
                        </div>
                    </div>
                    <i class="editicon"></i>
                </div>
            </li>
            <li>
                <label class="statusicon">状态：</label>

                <div class="warpedit">
                    <label class="lbltxt">潜在</label>
                    <div class="edititem">
                        <div class="select_item">
                            <input type="text" class="txtselect" name="txtselect" userid="0" value="潜在" readonly/>
                            <input type="hidden" name="statusId" value="1">
                            <div class="txtselect_item">
                                <div class="initem">
                                    <a href="JavaScript:;" userid="1" title="">潜在</a>
                                    <a href="JavaScript:;" userid="2" title="">跟进</a>
                                    <a href="JavaScript:;" userid="3" title="">成交</a>
                                    <a href="JavaScript:;" userid="4" title="">冻结</a>    
                                </div>
                            </div>
                        </div>
                    </div>
                    <i class="editicon"></i>
                </div>
            </li>
        </ul>
        
        <div class="detailitem">
            <!-- 个人信息 -->
            <ul>
                <li>
                    <span class="detailtitle">中文姓名：</span>

                    <div class="warpedit">
                        <label class="lbltxt"><#if user??>${user.username!''}<#else>&emsp;</#if></label>
                        <div class="edititem">
                            <div class="select_item">
                                <input type="text" name="username" value="<#if user??>${user.username!''}</#if>" class="txtnormal">
                             </div>
                        </div>
                        <i class="editicon"></i>
                    </div>
                </li>
                <li>
                    <span class="detailtitle detailtitle1">英文姓名：</span>
                    <div class="warpedit">
                        <label class="lbltxt"><#if user??>${user.englishName!''}<#else>&emsp;</#if></label>
                        <div class="edititem">
                            <div class="select_item">
                                <input type="text" name="englishName" value="<#if user??>${user.englishName!''}</#if>" class="txt">
                             </div>
                        </div>
                        <i class="editicon"></i>
                    </div>
                </li>
                <li>
                    <span class="detailtitle">身份证号：</span>

                    <div class="warpedit">
                        <label class="lbltxt"><#if user??>${user.idCard!''}<#else>&emsp;</#if></label>
                        <div class="edititem">
                            <div class="select_item">
                                <input type="text" name="idCard" value="<#if user??>${user.idCard!''}</#if>" class="txt">
                             </div>
                        </div>
                        <i class="editicon"></i>
                    </div>
                </li>
                <li>
                    <span class="detailtitle detailtitle1">婚姻状况：</span>

                    <div class="warpedit">
                        <label class="lbltxt" ><#if user??>${user.marital!''}<#else>&emsp;</#if> </label>
                        <div class="edititem">
                            <div class="select_item">
                                <input type="text" name="marital" value="<#if user??>${user.marital!''}</#if>" class="txt">
                             </div>
                        </div>
                        <i class="editicon"></i>
                    </div>
                </li>
                <li>
                    <span class="detailtitle">国籍：</span>

                    <div class="warpedit">
                        <label class="lbltxt"><#if user??>${user.nationality!''}<#else>&emsp;</#if></label>
                        <div class="edititem">
                            <div class="select_item">
                                <input type="text" name="nationality" value="<#if user??>${user.nationality!''}</#if>" class="txt">
                             </div>
                        </div>
                        <i class="editicon"></i>
                    </div>
                </li>
                <li>
                    <span class="detailtitle detailtitle1">性别：</span>

                    <div class="warpedit">
                        <label class="lbltxt"><#if user??>${user.sex!''}<#else>&emsp;</#if></label>
                        <div class="edititem">
                            <div class="select_item">
                                <input type="text" name="sex" value="<#if user??>${user.sex!''}</#if>" class="txt">
                             </div>
                        </div>
                        <i class="editicon"></i>
                    </div>
                </li>
                <li>
                    <span class="detailtitle">电邮：</span>

                    <div class="warpedit">
                        <label class="lbltxt"><#if user??>${user.email!''}<#else>&emsp;</#if></label>
                        <div class="edititem">
                            <div class="select_item">
                                <input type="text" name="email" value="<#if user??>${user.email!''}</#if>" class="txt">
                             </div>
                        </div>
                        <i class="editicon"></i>
                    </div>
                </li>
                <li>
                    <span class="detailtitle detailtitle1">出生日期：</span>

                    <div class="warpedit">
                        <label class="lbltxt"><#if user?? && user.birthday??>${user.birthday?string('yyyy-MM-dd')}<#else>&emsp;</#if></label>
                        <div class="edititem">
                            <div class="select_item">
                                <input name="birthday" type="text" value="<#if user?? && user.birthday??>${user.birthday?string('yyyy-MM-dd')}</#if>" class="txt date"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',lang:'zh-cn'})" >
                             </div>
                        </div>
                        <i class="editicon"></i>
                    </div>
                </li>
                <li class="longedit">
                    <span class="detailtitle ">住宅/永久地址：</span>

                    <div class="warpedit">
                        <label class="lbltxt"><#if user??>${user.address!''}<#else>&emsp;</#if></label>
                        <div class="edititem">
                            <div class="select_item">
                                <input type="text" name="address" value="<#if user??>${user.address!''}</#if>" class="txt">
                             </div>
                        </div>
                        <i class="editicon"></i>
                    </div>
                </li>
                <li class="longedit">
                    <span class="detailtitle ">通讯地址/邮编：</span>
                    <div class="warpedit">
                        <label class="lbltxt"><#if user??>${user.mailingAddress!''}<#else>&emsp;</#if></label>
                        <div class="edititem">
                            <div class="select_item">
                                <input type="text" name="mailingAddress" value="<#if user??>${user.mailingAddress!''}</#if>" class="txt">
                             </div>
                        </div>
                        <i class="editicon"></i>
                    </div>
                </li>
                <li class="longedit">
                    <span class="detailtitle ">联络电话：</span>

                    <div class="warpedit">
                        <label class="lbltxt"
                               title="手机：    13883845583    住宅：    023-8888888   公司：    400-8888888">手机：
                            13883845583 住宅： 023-8888888 公司： 400-8888888</label>
                        <i class="editicon"></i>
                    </div>
                </li>
                <li>
                    <span class="detailtitle">公司业务性质：</span>

                    <div class="warpedit">
                        <label class="lbltxt"><#if user??>${user.nature!''}&nbsp;<#else>&nbsp;</#if></label>
                        <div class="edititem">
                            <div class="select_item">
                                <input type="text" name="nature" value="<#if user??>${user.nature!''}</#if>" class="txt">
                             </div>
                        </div>
                        <i class="editicon"></i>
                    </div>
                </li>
                <li>
                    <span class="detailtitle detailtitle1">公司名称：</span>

                    <div class="warpedit">
                        <label class="lbltxt"><#if user??>${user.company!''}&nbsp;<#else>&nbsp;</#if></label>
                        <div class="edititem">
                            <div class="select_item">
                                <input type="text" name="company" value="<#if user??>${user.company!''}</#if>" class="txt">
                             </div>
                        </div>
                        <i class="editicon"></i>
                    </div>
                </li>
                <li>
                    <span class="detailtitle">现职工作年期：</span>

                    <div class="warpedit">
                        <label class="lbltxt"><#if user??>${user.workYear!''}<#else>&emsp;</#if></label>
                        <div class="edititem">
                            <div class="select_item">
                                <input type="text" name="workYear" value="<#if user??>${user.workYear!''}</#if>" class="txt">
                             </div>
                        </div>
                        <i class="editicon"></i>
                    </div>
                </li>
                <li>
                    <span class="detailtitle detailtitle1">现时每月收入：</span>

                    <div class="warpedit">
                        <label class="lbltxt"><#if user??>${user.income!''}<#else>&emsp;</#if></label>
                        <div class="edititem">
                            <div class="select_item">
                                <input type="text" name="income" value="<#if user??>${user.income!''}</#if>" class="txt">
                             </div>
                        </div>
                        <i class="editicon"></i>
                    </div>
                </li>
                <li>
                    <span class="detailtitle">职务及主要职务：</span>

                    <div class="warpedit">
                        <label class="lbltxt"><#if user??>${user.position!''}<#else>&emsp;</#if></label>
                        <div class="edititem">
                            <div class="select_item">
                                <input type="text" name="position" value="<#if user??>${user.position!''}</#if>" class="txt">
                             </div>
                        </div>
                        <i class="editicon"></i>
                    </div>
                </li>
                <li>
                    <span class="detailtitle detailtitle1">公司地址：</span>

                    <div class="warpedit">
                        <label class="lbltxt"><#if user??>${user.comAddress!''}<#else>&emsp;</#if></label>
                        <div class="edititem">
                            <div class="select_item">
                                <input type="text" name="comAddress" value="<#if user??>${user.comAddress!''}</#if>" class="txt">
                             </div>
                        </div>
                        <i class="editicon"></i>
                    </div>
                </li>
                <li class="longedit btnwrap"  style="text-align:center; display:none;">
                   <input  type="submit" class="btnsubmit" value="保存">
                </li>
            </ul>
        </div>
        </form>
        
        <style>
            #familyinfowrap .warpedit > label {
            width:80%;
            height:44px;
             overflow : hidden;
          text-overflow: ellipsis;
          display: -webkit-box;
          -webkit-line-clamp: 1;
          -webkit-box-orient: vertical;
            }
        </style>
        
        <div class="detailitem" id="familyinfowrap">
        <#if user?? && user.familyList??>
        <#list user.familyList as fam>
            <div id="familyinfoitem${(fam_index+1)?c}">
                <div class="addnewfamily">
                    <span>新增家庭信息</span>
                    <label><input type="radio" class="addfamily" onclick="changeRela(${fam.id?c},1,${user.id?c});" <#if fam.relation?? && fam.relation==1>checked="checked"</#if> name="relation${fam_index?c}">配偶</label>
                    <label><input type="radio" class="addfamily" onclick="changeRela(${fam.id?c},2,${user.id?c});" <#if fam.relation?? && fam.relation==2>checked="checked"</#if> name="relation${fam_index?c}">子女</label>
                    <label><input type="radio" class="addfamily" onclick="changeRela(${fam.id?c},3,${user.id?c});" <#if fam.relation?? && fam.relation==3>checked="checked"</#if> name="relation${fam_index?c}">父母</label>
                </div>
                <ul>
                    <input type="hidden" name="fId" value="${fam.id?c}" id="fId${fam_index?c}">
                    <li><span class="detailtitle">中文姓名：</span>
                        <div class="warpedit">
                            <label>${fam.username!''}</label>
                            <div class="edititem">
                                   <input type="text" name="username" id="username${fam_index?c}" value="${fam.username!''}"/>
                             </div>
                             <i class="editicon"></i>
                        </div>
                     </li>
                    <li><span class="detailtitle detailtitle1">英文姓名：</span>
                        <div class="warpedit">
                            <label><#if fam.englishName??>${fam.englishName!''}</#if></label>
                            <div class="edititem">
                                   <input type="text" name="englishName" id="englishName${fam_index?c}" value="${fam.englishName!''}"/>
                             </div>
                             <i class="editicon"></i>
                        </div>
                    </li>
                    <li><span class="detailtitle">身份证号：</span>
                        <div class="warpedit">
                            <label><#if fam.idCard??>${fam.idCard!' '}</#if></label>
                            <div class="edititem">
                                   <input type="text" name="idCard" id="idCard${fam_index?c}" value="${fam.idCard!''}"/>
                             </div>
                             <i class="editicon"></i>
                        </div>
                    </li>
                    <li><span class="detailtitle detailtitle1">婚姻状况：</span>
                        <div class="warpedit">
                            <label><#if fam.marital??>${fam.marital!' '}</#if></label>
                            <div class="edititem">
                                   <input type="text" name="marital" id="marital${fam_index?c}" value="${fam.marital!''}"/>
                             </div>
                             <i class="editicon"></i>
                        </div>
                    </li> 
                    <li><span class="detailtitle">国籍：</span>
                        <div class="warpedit">
                            <label><#if fam.marital??>${fam.nationality!' '}</#if></label>
                            <div class="edititem">
                                   <input type="text" name="nationality" id="nationality${fam_index?c}" value="${fam.nationality!''}"/>
                             </div>
                             <i class="editicon"></i>
                        </div>
                    </li>
                    <li><span class="detailtitle detailtitle1">性别：</span>
                        <div class="warpedit">
                            <label><#if fam.sex??>${fam.sex!' '}</#if></label>
                            <div class="edititem">
                                   <input type="text" name="sex" id="sex${fam_index?c}" value="${fam.sex!''}"/>
                             </div>
                             <i class="editicon"></i>
                        </div>
                    </li>
                    <li class="longedit"><span class="detailtitle ">出生年月：</span>
                        <div class="warpedit">
                        <label class="lbltxt"><#if fam?? && fam.birthday??>${fam.birthday?string('yyyy-MM-dd')}</#if></label>
                        <div class="edititem">
                            <div class="select_item">
                                <input name="birthday" id="birthday${fam_index?c}" type="text" value="<#if fam?? && fam.birthday??>${fam.birthday?string('yyyy-MM-dd')}</#if>" class="txt date"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',lang:'zh-cn'})" >
                             </div>
                        </div>
                        <i class="editicon"></i>
                    </div>
                    </li>
                    <li><span class="detailtitle">公司业务性质：</span>
                        <div class="warpedit">
                            <label><#if fam.nature??>${fam.nature!''}</#if></label>
                            <div class="edititem">
                                   <input type="text" name="nature" id="nature${fam_index?c}" value="${fam.nature!''}"/>
                             </div>
                             <i class="editicon"></i>
                        </div>
                    </li>
                    <li><span class="detailtitle detailtitle1">公司名称：</span>
                        <div class="warpedit">
                            <label><#if fam.company??>${fam.company!''}<#else>&nbsp;</#if></label>
                            <div class="edititem">
                                   <input type="text" name="company" id="company${fam_index?c}" value="${fam.company!''}"/>
                             </div>
                             <i class="editicon"></i>
                        </div>
                    </li>
                    <li><span class="detailtitle">现职工作年期：</span>
                        <div class="warpedit">
                            <label><#if fam.workYear??>${fam.workYear!''}</#if></label>
                            <div class="edititem">
                                   <input type="text" name="workYear" id="workYear${fam_index?c}" value="${fam.workYear!''}"/>
                             </div>
                             <i class="editicon"></i>
                        </div>
                    </li>
                    <li><span class="detailtitle detailtitle1">现时每月收入：</span>
                        <div class="warpedit">
                            <label><#if fam.income??>${fam.income!''}<#else>&nbsp;</#if></label>
                            <div class="edititem">
                                   <input type="text" name="income" id="income${fam_index?c}" value="${fam.income!''}"/>
                             </div>
                             <i class="editicon"></i>
                        </div>
                    </li>
                    <li><span class="detailtitle">职位及主要职务：</span>
                        <div class="warpedit">
                            <label><#if fam.position??>${fam.position!''}</#if></label>
                            <div class="edititem">
                                   <input type="text" name="position" id="position${fam_index?c}" value="${fam.position!''}"/>
                             </div>
                             <i class="editicon"></i>
                        </div>
                    </li>
                    <li><span class="detailtitle detailtitle1">公司地址：</span>
                        <div class="warpedit">
                            <label><#if fam.comAddress??>${fam.comAddress!''}</#if></label>
                            <div class="edititem">
                                   <input type="text" name="comAddress" id="comAddress${fam_index?c}" value="${fam.comAddress!''}"/>
                             </div>
                             <i class="editicon"></i>
                        </div>
                    </li>
                    <li class="longedit btnwrap"  style="text-align:center; display:none;">
                        <input  type="buttom" readonly  onclick="addFamily(${fam_index?c})" class="btnsubmit" value="保存">
                    </li>
                </ul>
            </div>
            <script>
                $(function(){
                    $('.editicon').editclick('.edititem');
                });
            </script>
        </#list>
        </#if>
    
            <!-- 家庭信息-->
            <script>
                <#if user?? && user.familyList??>
                    var num = ${user.familyList?size};
                <#else>
                    var num = 0;
                </#if>
                $(function () {
                    $("#familyinfoitem1 .addtitle").addfamilyinfo(num);
                });

                $.fn.addfamilyinfo = function (num) {
                    var num1 = num + 1;
                    $(this).bind('mousedown', function () {
                        var strtitle = ' <div id="familyinfoitem' + num1 + '"><div class="addnewfamily" >' +
                                '<a href="javascript:;" title="" class="addtitle">+ 新增家庭信息</a>' +

                                '</div></div>';
                        $("#familyinfowrap").append(strtitle);

                    });
                    $(this).bind('click', function () {
                        var html1 = '<span>新增家庭信息</span>' +
                                '<label><input type="radio" class="addfamily" name="relation" value="1"/>配偶</label>' +
                                '<label><input type="radio" class="addfamily" name="relation" value="2"/>子女</label>' +
                                '<label><input type="radio" class="addfamily" name="relation" value="3"/>父母</label>';

                        $(this).parent().append(html1);
                        
                        var strthis = $(this);

                       var strinfo = '<ul>' +
                               '<li><span class="detailtitle">中文姓名：</span><div class="warpedit"><label>&emsp;</label><div class="edititem">'+
                                    '<input type="text" name="username" id="username'+num1+'"  /></div><i class="editicon"></i></div>'+
                               '</li>'+
                               '<li><span class="detailtitle detailtitle1">英文姓名：</span><div class="warpedit"><label>&emsp;</label><div class="edititem">'+
                                    '<input type="text" name="englishName" id="englishName'+num1+'"  /></div><i class="editicon"></i></div>'+
                               '</li>'+
                               '<li><span class="detailtitle">身份证号：</span><div class="warpedit"><label>&emsp;</label><div class="edititem">'+
                                    '<input type="text" name="idCard" id="idCard'+num1+'"  /></div><i class="editicon"></i></div>'+
                               '</li>'+
                               '<li><span class="detailtitle detailtitle1">婚姻状况：</span><div class="warpedit"><label>&emsp;</label><div class="edititem">'+
                                    '<input type="text" name="marital" id="marital'+num1+'"  /></div><i class="editicon"></i></div>'+
                               '</li>'+
                               '<li><span class="detailtitle">国籍：</span><div class="warpedit"><label>&emsp;</label><div class="edititem">'+
                                    '<input type="text" name="nationality" id="nationality'+num1+'"  /></div><i class="editicon"></i></div>'+
                               '</li>'+
                               '<li><span class="detailtitle detailtitle1">性别：</span><div class="warpedit"><label>&emsp;</label><div class="edititem">'+
                                    '<input type="text" name="sex" id="sex'+num1+'"  /></div><i class="editicon"></i></div>'+
                               '</li>'+
                               '<li class="longedit"><span class="detailtitle ">出生年月：</span><div class="warpedit"><label>&emsp;</label><div class="edititem">'+
                                    '<input type="text" name="birthday" id="birthday'+num1+'"   /></div><i class="editicon"></i></div>'+
                               '</li>'+
                               '<li><span class="detailtitle">公司业务性质：</span><div class="warpedit"><label>&emsp;</label><div class="edititem">'+
                                    '<input type="text" name="nature" id="nature'+num1+'"  /></div><i class="editicon"></i></div>'+
                               '</li>'+
                               '<li><span class="detailtitle detailtitle1">公司名称：</span><div class="warpedit"><label>&emsp;</label><div class="edititem">'+
                                    '<input type="text" name="company" id="company'+num1+'"  /></div><i class="editicon"></i></div>'+
                               '</li>'+
                               '<li><span class="detailtitle">现职工作年期：</span><div class="warpedit"><label>&emsp;</label><div class="edititem">'+
                                    ' <input type="text" name="workYear" id="workYear'+num1+'"  /></div><i class="editicon"></i></div>'+
                               '</li>'+
                               '<li><span class="detailtitle detailtitle1">现时每月收入：</span><div class="warpedit"><label>&emsp;</label><div class="edititem">'+
                                    ' <input type="text" name="income" id="income'+num1+'"  /></div><i class="editicon"></i></div>'+
                               '</li>'+
                               '<li><span class="detailtitle">职位及主要职务：</span><div class="warpedit"><label>&emsp;</label><div class="edititem">'+
                                    ' <input type="text" name="position" id="position'+num1+'"  /></div><i class="editicon"></i></div>'+
                               '</li>'+
                               '<li><span class="detailtitle detailtitle1">公司地址：</span><div class="warpedit"><label>&emsp;</label><div class="edititem">'+
                                    ' <input type="text" name="comAddress" id="comAddress'+num1+'"  /></div><i class="editicon"></i></div>'+
                               '</li>'+
                               '<li class="longedit btnwrap"  style="text-align:center; display:none;">'+
                                   '<input  type="buttom" readonly  onclick="addFamily('+num1+')" class="btnsubmit" value="保存">'+
                               '</li>'
                           '</ul>';
 
                        $(this).parent().parent().append(strinfo);
                        $(this).remove();
                        $("#familyinfoitem" + num1 + " .addtitle").addfamilyinfo(num1);
                        $('.editicon').editclick('.edititem');

                    });

                }

            </script>

                <div id="familyinfoitem1">
                    <div class="addnewfamily">
                        <a href="javascript:;" class="addtitle">+ 新增家庭信息</a>
                    </div>
                </div>
            </div>
