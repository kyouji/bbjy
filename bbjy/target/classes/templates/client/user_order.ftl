
    <!--  日历表-->
    <div class="dateform" id="infoform" style="display: block;">
        <div class="choosedate">
            <input type="text" class="inputdate" id="datetimepicker2" readonly/>
        </div>
        <script>
            $('#datetimepicker2').datetimepicker({
                format: 'Y年m月d日',
                lang: 'ch',
                formatDate: 'Y年m月d日',
                timepicker: false,
                onSelectDate: function () {
                }
            });
        </script>
        <div class="weekitem">
            <label>SUN 周日</label>
            <label>MON 周一</label>
            <label>TUE 周二</label>
            <label>WED 周三</label>
            <label>THU 周四</label>
            <label>FRI 周五</label>
            <label>SAT 周六</label>
        </div>
        <div class="days">
            <div>
                <label class="otherday">12月27日</label>

                <div class="dayplane">
                    <a href="javascript:showinfo('#bookinglist');" infoid="1" class="a_checkmore"
                       title="一个确认预约">一个确认预约</a>
                </div>
            </div>
            <div>
                <label class="otherday">12月28日</label>
            </div>
            <div>
                <label class="otherday">12月29日</label>
            </div>
            <div>
                <label class="otherday">12月30日</label>
            </div>
            <div>
                <label class="otherday">12月31日</label>
            </div>
            <div>
                <label>1</label>
            </div>
            <div>
                <label>2</label>
            </div>
            <div>
                <label>3</label>
            </div>
            <div>
                <label>4</label>
            </div>
            <div class="current">
                <label>5</label>

                <div class="dayplane">
                    <a href="javascript:showinfo('#bookinglist');" infoid="1" class="a_checkmore"
                       title="一个确认预约">一个确认预约</a>
                    <a href="javascript:showinfo('#bookinglist');" infoid="1" class="a_checkmore"
                       title="一个确认预约">一个确认预约</a>
                    <a href="javascript:showinfo('#bookinglist');" infoid="1" class="a_checkmore"
                       title="一个确认预约">一个确认预约</a>
                    <a href="javascript:showinfo('#bookinglist');" infoid="1" class="a_checkmore"
                       title="一个确认预约">一个确认预约</a>
                    <a href="javascript:showinfo('#bookinglist');" infoid="1" class="a_checkmore"
                       title="一个确认预约">一个确认预约</a>
                    <a href="javascript:showinfo('#bookinglist');" infoid="1" class="a_checkmore"
                       title="一个确认预约">一个确认预约</a>

                </div>
            </div>
            <div>
                <label>6</label>
            </div>
            <div>
                <label>7</label>
            </div>
            <div>
                <label>8</label>
            </div>
            <div>
                <label>9</label>
            </div>
            <div>
                <label>10</label>
            </div>
            <div>
                <label>11</label>
            </div>
            <div>
                <label>12</label>
            </div>
            <div>
                <label>13</label>
            </div>
            <div>
                <label>14</label>
            </div>
            <div>
                <label>15</label>
            </div>
            <div>
                <label>16</label>
            </div>
            <div>
                <label>17</label>
            </div>
            <div>
                <label>18</label>
            </div>
            <div>
                <label>19</label>
            </div>
            <div>
                <label>20</label>
            </div>
            <div>
                <label>21</label>

            </div>
            <div>
                <label>22</label>
            </div>
            <div>
                <label>23</label>
            </div>
            <div>
                <label>24</label>
            </div>
            <div>
                <label>25</label>
            </div>
            <div>
                <label>26</label>
            </div>
            <div>
                <label>27</label>
            </div>
            <div>
                <label>28</label>
            </div>
            <div>
                <label>29</label>
            </div>
            <div>
                <label>30</label>
            </div>
            <div>
                <label>31</label>
            </div>
            <div><label class="otherday">1</label></div>
            <div><label class="otherday">2</label></div>
            <div><label class="otherday">3</label></div>
            <div><label class="otherday">4</label></div>
            <div><label class="otherday">5</label></div>
            <div><label class="otherday">6</label></div>
        </div>
    </div>
    <!-- 当日预约列表-->
    <div class="bookinglist" id="bookinglist" style="display: none;">
        <div class="listtop">
            <label>预约日期：2016-02-22 星期一</label>

            <a href="javascript:showinfo('#bookingprocedure');" class="addnewbooking" title="">+
                新增预约</a>
            <a href="javascript:showinfo('#infoform');" class="a_return" title="">返回</a>
        </div>
        <table class="listcontent">
            <tr>
                <th>预约编号</th>
                <th>预约状态</th>
                <th>服务顾问</th>
                <th>准持有人</th>
                <th>投保计划</th>
                <th>签约时间</th>
                <th>&nbsp;</th>
            </tr>
            <tr>
                <td>201602221423</td>
                <td>确认预约</td>
                <td>李娟</td>
                <td>周杰伦</td>
                <td>进泰安心保25年</td>
                <td>14:00~15:00</td>
                <td>
                    <a href="javascript:showinfo('#bookingprocedure');" title="">修改</a>
                    <label class="line">|</label>
                    <a href="#" title="">取消</a>
                </td>
            </tr>
            <tr>
                <td>201602221423</td>
                <td>确认预约</td>
                <td>李娟</td>
                <td>周杰伦</td>
                <td>进泰安心保25年</td>
                <td>14:00~15:00</td>
                <td>
                    <a href="javascript:showinfo('#bookingprocedure');" title="">修改</a>
                    <label class="line">|</label>
                    <a href="#" title="">取消</a>
                </td>
            </tr>
            <tr>
                <td>201602221423</td>
                <td>确认预约</td>
                <td>李娟</td>
                <td>周杰伦</td>
                <td>进泰安心保25年</td>
                <td>14:00~15:00</td>
                <td>
                    <a href="javascript:showinfo('#bookingprocedure');" title="">修改</a>
                    <label class="line">|</label>
                    <a href="#" title="">取消</a>
                </td>
            </tr>
            <tr>
                <td>201602221423</td>
                <td>确认预约</td>
                <td>李娟</td>
                <td>周杰伦</td>
                <td>进泰安心保25年</td>
                <td>14:00~15:00</td>
                <td>
                    <a href="javascript:showinfo('#bookingprocedure');" title="">修改</a>
                    <label class="line">|</label>
                    <a href="#" title="">取消</a>
                </td>
            </tr>
            <tr>
                <td>201602221423</td>
                <td>确认预约</td>
                <td>李娟</td>
                <td>周杰伦</td>
                <td>进泰安心保25年</td>
                <td>14:00~15:00</td>
                <td>
                    <a href="javascript:showinfo('#bookingprocedure');" title="">修改</a>
                    <label class="line">|</label>
                    <a href="#" title="">取消</a>
                </td>
            </tr>
            <tr>
                <td>201602221423</td>
                <td>确认预约</td>
                <td>李娟</td>
                <td>周杰伦</td>
                <td>进泰安心保25年</td>
                <td>14:00~15:00</td>
                <td>
                    <a href="javascript:showinfo('#bookingprocedure');" title="">修改</a>
                    <label class="line">|</label>
                    <a href="#" title="">取消</a>
                </td>
            </tr>
        </table>
    </div>

    <!-- 预约签单流程-->
    <div class="bookingprocedure" id="bookingprocedure" style="display: none;">
        <!-- Step1-->
        <div id="step1" style="display: block;" class="procedure">
            <div class="proceduretitle">
                <span>Step1:填写保单被保人及预约信息</span>
                <a href="javascript:showinfo('#bookinglist');" class="a_return" title="">返回</a>
            </div>
            <div class="procedurecontent">
                <span class="bookingdate">预约日期：2016-12-23 星期二</span>


                <table class="table_step1">
                    <tr>
                        <td><label>被保人：</label></td>
                        <td colspan="5">
                            <div class="select_item">
                                <input type="text" class="txtselect" name="txtselect" userid="0"
                                       value="张德江" readonly/>

                                <div class="txtselect_item">
                                    <div class="initem">
                                        <a href="JavaScript:;" userid="1" title="">张三</a>
                                        <a href="JavaScript:;" userid="2" title="">李四</a>
                                        <a href="JavaScript:;" userid="3" title="">王五</a>
                                        <a href="JavaScript:;" userid="1" title="">张三</a>
                                        <a href="JavaScript:;" userid="2" title="">李四</a>
                                        <a href="JavaScript:;" userid="3" title="">王五</a>
                                        <a href="JavaScript:;" userid="1" title="">张三</a>
                                        <a href="JavaScript:;" userid="2" title="">李四</a>
                                        <a href="JavaScript:;" userid="3" title="">王五</a>
                                        <a href="JavaScript:;" userid="1" title="">张三</a>
                                        <a href="JavaScript:;" userid="2" title="">李四</a>
                                        <a href="JavaScript:;" userid="3" title="">王五</a>
                                    </div>
                                </div>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td><label>预约时间：</label></td>
                        <td colspan="3">
                            <input type="text" class="timepicker" value="12:00"
                                   id="datetimepicker5"/><label style="display: inline-block">~</label>
                            <input type="text" class="timepicker" value="12:00" id="datetimepicker6"/>
                            <label class="rdihelp"><input type="checkbox" checked/>协助预约</label>

                            <script>
                                $('#datetimepicker5').datetimepicker({
                                    format: 'H:i',
                                    formatDate: 'H:i',
                                    datepicker: false,
                                    allowTimes: ['12:00', '13:00', '15:00', '17:00', '18:00', '19:00', '20:00']
                                });
                                $('#datetimepicker6').datetimepicker({
                                    format: 'H:i',
                                    formatDate: 'H:i',
                                    datepicker: false,
                                    allowTimes: ['12:00', '13:00', '15:00', '17:00', '18:00', '19:00', '20:00']
                                });
                            </script>

                        </td>
                        <td><label>预约顾问：</label></td>
                        <td>
                            <div class="select_item">
                                <input type="text" class="txtselect" name="txtselect" userid="0"
                                       value="张德江" readonly/>

                                <div class="txtselect_item">
                                    <div class="initem">
                                        <a href="JavaScript:;" userid="1" title="">张三</a>
                                        <a href="JavaScript:;" userid="2" title="">李四</a>
                                        <a href="JavaScript:;" userid="3" title="">王五</a>
                                        <a href="JavaScript:;" userid="1" title="">张三</a>
                                        <a href="JavaScript:;" userid="2" title="">李四</a>
                                        <a href="JavaScript:;" userid="3" title="">王五</a>
                                        <a href="JavaScript:;" userid="1" title="">张三</a>
                                        <a href="JavaScript:;" userid="2" title="">李四</a>
                                        <a href="JavaScript:;" userid="3" title="">王五</a>
                                        <a href="JavaScript:;" userid="1" title="">张三</a>
                                        <a href="JavaScript:;" userid="2" title="">李四</a>
                                        <a href="JavaScript:;" userid="3" title="">王五</a>
                                    </div>
                                </div>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td><label>带单顾问：</label></td>
                        <td>
                            <div class="select_item">
                                <input type="text" class="txtselect" name="txtselect" userid="0"
                                       value="张德江" readonly/>

                                <div class="txtselect_item">
                                    <div class="initem">
                                        <a href="JavaScript:;" userid="1" title="">张三</a>
                                        <a href="JavaScript:;" userid="2" title="">李四</a>
                                        <a href="JavaScript:;" userid="3" title="">王五</a>
                                        <a href="JavaScript:;" userid="1" title="">张三</a>
                                        <a href="JavaScript:;" userid="2" title="">李四</a>
                                        <a href="JavaScript:;" userid="3" title="">王五</a>
                                        <a href="JavaScript:;" userid="1" title="">张三</a>
                                        <a href="JavaScript:;" userid="2" title="">李四</a>
                                        <a href="JavaScript:;" userid="3" title="">王五</a>
                                        <a href="JavaScript:;" userid="1" title="">张三</a>
                                        <a href="JavaScript:;" userid="2" title="">李四</a>
                                        <a href="JavaScript:;" userid="3" title="">王五</a>
                                    </div>
                                </div>
                            </div>
                        </td>
                        <td><label>联络电话：</label></td>
                        <td>
                            <input type="text" class="ipttxt_normal"/>
                        </td>
                        <td><label>联络邮箱：</label></td>
                        <td>
                            <input type="text" class="ipttxt_normal"/>
                        </td>
                    </tr>
                    <tr>
                        <td><label>预计到港人数：</label></td>
                        <td><input type="text" class="ipttxt_small"/></td>
                        <td colspan="4">
                            <label class="rdihelp"><input type="checkbox" checked/>顾问不同行</label>
                        </td>
                    </tr>
                </table>
                <div class="btnwrap">
                    <a href="javascript:showinfo('#step2');" class="a_next" title="">下一步</a>
                    <a href="javascript:showinfo('#bookinglist');" class="a_cancel" title="">取消</a>
                </div>
            </div>
        </div>
        <!-- Step2-->
        <div id="step2" style="display: none;" class="procedure">
            <div class="proceduretitle">
                <span>Step2:选择保单持有人</span>
                <a href="javascript:showinfo('#step1');" class="a_return" title="">返回</a>
            </div>
            <div class="procedurecontent">
                <span class="bookingdate">预约日期：2016-12-23 星期二</span>
                <span class="bookingdate">预约时间：14:00-15:00</span>
                <span class="bookingdate">预约顾问：HL00000014-何薇</span>

                <div class="infoitem">
                    <label>保单持有人：</label>

                    <div class="select_item">
                        <input type="text" class="txtselect" name="txtselect" userid="0" value="张德江"
                               readonly/>

                        <div class="txtselect_item">
                            <div class="initem">
                                <a href="JavaScript:;" userid="1" title="">张三</a>
                                <a href="JavaScript:;" userid="2" title="">李四</a>
                                <a href="JavaScript:;" userid="3" title="">王五</a>
                                <a href="JavaScript:;" userid="1" title="">张三</a>
                                <a href="JavaScript:;" userid="2" title="">李四</a>
                                <a href="JavaScript:;" userid="3" title="">王五</a>
                                <a href="JavaScript:;" userid="1" title="">张三</a>
                                <a href="JavaScript:;" userid="2" title="">李四</a>
                                <a href="JavaScript:;" userid="3" title="">王五</a>
                                <a href="JavaScript:;" userid="1" title="">张三</a>
                                <a href="JavaScript:;" userid="2" title="">李四</a>
                                <a href="JavaScript:;" userid="3" title="">王五</a>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="infoitem">
                    <label>与被保人关系：</label>
                    <label class="lbldesc">本人</label>
                </div>

                <div class="btnwrap">
                    <a href="javascript:showinfo('#step1');" class="a_cancel" title="">上一步</a>
                    <a href="javascript:showinfo('#step3');" class="a_next" title="">下一步</a>
                    <a href="javascript:showinfo('#bookinglist');" class="a_cancel" title="">取消</a>
                </div>
            </div>
        </div>
        <!-- Step3-->
        <div id="step3" style="display: none;" class="procedure">
            <div class="proceduretitle">
                <span>Step3:填写计划内容</span>
                <a href="javascript:showinfo('#step2');" class="a_return" title="">返回</a>
            </div>
            <div class="procedurecontent">
                <span class="bookingdate">预约日期：2016-12-23 星期二</span>

                <table class="table_step1">
                    <tr>
                        <td class="td_th"><label>保险公司：</label></td>
                        <td>
                            <div class="select_item longselect">
                                <input type="text" class="txtselect" name="txtselect" userid="0"
                                       value="AIA友邦人寿" readonly/>

                                <div class="txtselect_item">
                                    <div class="initem">
                                        <a href="JavaScript:;" userid="1" title="">AIA友邦人寿</a>
                                        <a href="JavaScript:;" userid="2" title="">AIA友邦人寿</a>
                                        <a href="JavaScript:;" userid="3" title="">AIA友邦人寿</a>
                                    </div>
                                </div>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td class="td_th"><label>签约地点：</label></td>
                        <td>
                            <div class="select_item longselect">
                                <input type="text" class="txtselect" name="txtselect" userid="0"
                                       value="AIA才俊中心—海港城" readonly/>

                                <div class="txtselect_item">
                                    <div class="initem">
                                        <a href="JavaScript:;" userid="1" title="">AIA才俊中心—海港1</a>
                                        <a href="JavaScript:;" userid="2" title="">AIA才俊中心—海港2</a>
                                        <a href="JavaScript:;" userid="3" title="">AIA才俊中心—海港3</a>
                                    </div>
                                </div>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td class="td_th"><label>保单类型：</label></td>
                        <td>
                            <div class="select_item longselect">
                                <input type="text" class="txtselect" name="txtselect" userid="0"
                                       value="严重疾病保障" readonly/>

                                <div class="txtselect_item">
                                    <div class="initem">
                                        <a href="JavaScript:;" userid="1" title="">严重疾病保障1</a>
                                        <a href="JavaScript:;" userid="2" title="">严重疾病保障2</a>
                                        <a href="JavaScript:;" userid="3" title="">严重疾病保障3</a>
                                    </div>
                                </div>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td class="td_th"><label>主約計畫/年期：</label></td>
                        <td>
                            <div class="select_item longselect">
                                <input type="text" class="txtselect" name="txtselect" userid="0"
                                       value="友邦安心保" readonly/>

                                <div class="txtselect_item">
                                    <div class="initem">
                                        <a href="JavaScript:;" userid="1" title="">严重疾病保障1</a>
                                        <a href="JavaScript:;" userid="2" title="">严重疾病保障2</a>
                                        <a href="JavaScript:;" userid="3" title="">严重疾病保障3</a>
                                    </div>
                                </div>
                            </div>
                            <label>/</label>
                            <input type="text" class="ipttxt_small"/>
                            <label>年期</label>
                        </td>
                    </tr>
                    <tr>
                        <td class="td_th"><label>保额/保费：</label></td>
                        <td>
                            <input type="text" class="ipttxt_normal1" value="1500000.00"/>
                            <label>/</label>
                            <input type="text" class="ipttxt_normal1" value="1841.75"/>

                        </td>
                    </tr>
                    <tr>
                        <td class="td_th"><label>附约：</label></td>
                        <td>
                            <ul>
                                <li>
                                    <div class="select_item longselect">
                                        <input type="text" class="txtselect" name="txtselect" userid="0"
                                               value="友邦安心保" readonly/>

                                        <div class="txtselect_item">
                                            <div class="initem">
                                                <a href="JavaScript:;" userid="1" title="">严重疾病保障1</a>
                                                <a href="JavaScript:;" userid="2" title="">严重疾病保障2</a>
                                                <a href="JavaScript:;" userid="3" title="">严重疾病保障3</a>
                                            </div>
                                        </div>
                                    </div>
                                    <label>保费：</label><input type="text" class="ipttxt_normal1"/>
                                    <a href="javascript:;" onclick="$(this).parent().remove();" class="a_removeitem" title="移出"></a>
                                </li>
                            </ul>
                            <a href="javascript:;" id="addtreaty" class="a_addtreaty" title="">添加附约</a>
                            <script>

                                                $(function(){
                                                   $("#addtreaty").click(function(){
                                                        var ithis=$(this);
                                                       $.ajax({
                                                           type:"get",
                                                           url:"treaty.html",
                                                           dataType:"html",
//                                                           beforeSend:function(){$("#show").html("数据加载中...")},
                                                           success:function(result){
                                                               ithis.prev().append(result);
                                                           }
                                                       });
                                                   });
                                                });
                                            </script>


                        </td>
                    </tr>
                    <tr>
                        <td class="td_th"><label>保单币别：</label></td>
                        <td>
                            <label class="rdihelp"><input name="typemoney" checked
                                                          type="radio"/>港币</label>
                            <label class="rdihelp"><input name="typemoney" type="radio"/>美金</label>
                        </td>
                    </tr>
                    <tr>
                        <td class="td_th"><label>缴费期别：</label></td>
                        <td>
                            <label class="rdihelp"><input name="typegivemoney" checked type="radio"/>预缴全额保费</label>
                            <label class="rdihelp"><input name="typegivemoney" type="radio"/>月缴</label>
                            <label class="rdihelp"><input name="typegivemoney" type="radio"/>季缴</label>
                            <label class="rdihelp"><input name="typegivemoney" type="radio"/>半年缴</label>
                            <label class="rdihelp"><input name="typegivemoney" type="radio"/>年缴</label>
                            <label class="rdihelp"><input name="typegivemoney" type="radio"/>躉缴</label>
                        </td>
                    </tr>
                    <tr>
                        <td class="td_th"><label>首期保費：</label></td>
                        <td>
                            <label class="rdihelp"><input name="typegiveway" checked
                                                          type="radio"/>信用卡</label>
                            <label class="rdihelp"><input name="typegiveway" type="radio"/>汇票</label>
                            <label class="rdihelp"><input name="typegiveway" type="radio"/>支票</label>
                            <label class="rdihelp"><input name="typegiveway" type="radio"/>银联卡</label>
                            <label class="rdihelp"><input name="typegiveway" type="radio"/>现金</label>
                        </td>
                    </tr>
                    <tr>
                        <td class="td_th"><label>紅利分配：</label></td>
                        <td>
                            <label class="rdihelp"><input name="typegiveway" checked
                                                          type="radio"/>积存</label>
                            <label class="rdihelp"><input name="typegiveway" type="radio"/>现金支付</label>
                            <label class="rdihelp"><input name="typegiveway" type="radio"/>扣除保费</label>
                            <label class="rdihelp"><input name="typegiveway"
                                                          type="radio"/>购买付清保险</label>

                            <p style="color: #ec0303;line-height: 20px;">* 若無勾選則以「積存」為預設分配方式。</p>
                        </td>
                    </tr>
                    <tr>
                        <td class="td_th"><label>轉保聲明：</label></td>
                        <td>
                            <label style="line-height: 32px;">是否於過去或打算於未來12個月內以此份投保取代任何現有壽險保單，或現有保單內大部份的壽險？</label>

                            <label class="rdihelp"><input name="typecheck" checked
                                                          type="radio"/>是</label>
                            <label class="rdihelp"><input name="typecheck" type="radio"/>否</label>

                        </td>
                    </tr>
                    <tr>
                        <td class="td_th"><label>其他说明：</label></td>
                        <td>
                            <textarea class="txtareabig"></textarea>
                        </td>
                    </tr>

                </table>
                <div class="btnwrap">
                    <a href="javascript:showinfo('#step2');" class="a_cancel" title="">上一步</a>
                    <a href="javascript:showinfo('#step4');" class="a_next" title="">下一步</a>
                    <a href="javascript:showinfo('#bookinglist');" class="a_cancel" title="">取消</a>
                </div>
            </div>
        </div>
        <!-- Step4-->
        <div id="step4" style="display: none;" class="procedure">
            <div class="proceduretitle">
                <span>Step4:填寫受益人資料</span>
                <a href="javascript:showinfo('#step3');" class="a_return" title="">返回</a>
            </div>
            <div class="procedurecontent">
                <label class="lblchk"><input checked type="checkbox"/>Own Estate 作為我的遺產</label>

                <p><a href="javascript:;" id="addpeople1" class="a_addtreaty fr" title="">新增受益人</a></p>

                <script>

                                    $(function(){
                                        $("#addpeople1").click(function(){
                                            $.ajax({
                                                type:"get",
                                                url:"data1.html",
                                                dataType:"html",
//                                              beforeSend:function(){$("#show").html("数据加载中...")},
                                                success:function(result){
                                                    $("#addpeoplecontent1").append(result);
                                                }
                                            });
                                        });
                                    });
                                </script>

                <table class="listcontent" id="addpeoplecontent1">
                    <tr>
                        <th>英文姓名</th>
                        <th>中文姓名</th>
                        <th>與被保人關係</th>
                        <th>年龄</th>
                        <th>身分證/護照號碼</th>
                        <th>百分比</th>
                        <th>受益順位</th>
                        <th>&nbsp;</th>
                    </tr>
                    <tr>
                        <td>SHIN SU</td>
                        <td>苏见信</td>
                        <td>母子</td>
                        <td>41</td>
                        <td>42215256454121222</td>
                        <td>100</td>
                        <td>1</td>
                        <td>
                            <a href="#" class="a_updateicon" title="修改">修改</a>
                            <a href="#" class="a_deleteicon" title="删除">取消</a>
                        </td>
                    </tr>

                </table>

                <div class="btnwrap">
                    <a href="javascript:showinfo('#step3');" class="a_cancel" title="">上一步</a>
                    <a href="javascript:showinfo('#step5');" class="a_next" title="">下一步</a>
                    <a href="javascript:showinfo('#bookinglist');" class="a_cancel" title="">取消</a>
                </div>
            </div>
        </div>
        <!-- Step5-->
        <div id="step5" style="display: none;" class="procedure">
            <div class="proceduretitle">
                <span>Step5:填寫投保記錄</span>
                <a href="javascript:showinfo('#step4');" class="a_return" title="">返回</a>
            </div>
            <div class="procedurecontent">
                <span class="bookingdate">包含已投保及正在申請中任何保險，並請換算為等值港幣。</span>


                <table class="table_step2">
                    <tr>
                        <th>承保公司</th>
                        <th>簽發日期</th>
                        <th>人壽保險</th>
                        <th>住院入息</th>
                        <th>住院賠償</th>
                        <th>危疾保險</th>
                        <th>意外保險</th>
                        <th>殘疾</th>
                        <th>&nbsp;</th>
                    </tr>
                    <tr>
                        <td colspan="9">
                            <label class="lbltips">保单被保人</label>
                            <label class="rdihelp"><input name="rdiyesorno" type="radio"/>有，如下列</label>
                            <label class="rdihelp"><input name="rdiyesorno" type="radio"/>无</label>
                            <a href="javascript:;" id="addprotect" class="a_addtreaty fr" title="">新增投保记录</a>
                        </td>
                        <script>
                                            $(function(){
                                                $("#addprotect").click(function(){
                                                    $.ajax({
                                                        type:"get",
                                                        url:"data2.html",
                                                        dataType:"html",
//                                              beforeSend:function(){$("#show").html("数据加载中...")},
                                                        success:function(result){
                                                            $("#beprotect").append(result);
                                                        }
                                                    });
                                                });
                                            });
                                        </script>

                    </tr>
                    <tbody id="beprotect">
                        <tr>
                            <td><input type="text"/></td>
                            <td><input type="text"/></td>
                            <td><input type="text"/></td>
                            <td><input type="text"/></td>
                            <td><input type="text"/></td>
                            <td><input type="text"/></td>
                            <td><input type="text"/></td>
                            <td><input type="text"/></td>
                            <td>
                                <a href="#" class="a_updateicon" title="修改">修改</a>
                                <a href="#" class="a_deleteicon" title="删除">取消</a>
                            </td>
                        </tr>
                    </tbody>
                    <tr>
                        <td colspan="9">
                            <label class="lbltips">保单持有人</label>
                            <label class="rdihelp"><input name="rdiyesorno" type="radio"/>有，如下列</label>
                            <label class="rdihelp"><input name="rdiyesorno" type="radio"/>无</label>
                            <a href="javascript:;" id="addprotected" class="a_addtreaty fr" title="">新增投保记录</a>
                        </td>
                        <script>
                                            $(function(){
                                                $("#addprotected").click(function(){
                                                    $.ajax({
                                                        type:"get",
                                                        url:"data2.html",
                                                        dataType:"html",
//                                              beforeSend:function(){$("#show").html("数据加载中...")},
                                                        success:function(result){
                                                            $("#protected").append(result);
                                                        }
                                                    });
                                                });
                                            });
                                        </script>
                    </tr>
                    <tbody id="protected">
                        <tr>
                            <td><input type="text"/></td>
                            <td><input type="text"/></td>
                            <td><input type="text"/></td>
                            <td><input type="text"/></td>
                            <td><input type="text"/></td>
                            <td><input type="text"/></td>
                            <td><input type="text"/></td>
                            <td><input type="text"/></td>
                            <td>
                                <a href="#" class="a_updateicon" title="修改">修改</a>
                                <a href="#" class="a_deleteicon" title="删除">取消</a>
                            </td>
                        </tr>
                    </tbody>
                </table>

                <div class="btnwrap">
                    <a href="javascript:showinfo('#step4');" class="a_cancel" title="">上一步</a>
                    <a href="javascript:showinfo('#step6');" class="a_next" title="">下一步</a>
                    <a href="javascript:showinfo('#bookinglist');" class="a_cancel" title="">取消</a>
                </div>
            </div>
        </div>
        <!-- Step6-->
        <div id="step6" style="display: none;" class="procedure">
            <div class="proceduretitle">
                <span>Step6:上傳 KYC</span>
                <a href="javascript:showinfo('#step5');" class="a_return" title="">返回</a>
            </div>
            <div class="procedurecontent">
                <span class="bookingdate">预约日期：2016-12-23 星期二</span>
                <span class="bookingdate">预约时间：14:00-15:00</span>
                <span class="bookingdate">预约顾问：HL00000014-何薇</span>

                <div class="uploadfilewrap noborder">
                    <span class="spantitle">KYC档案：</span>

                    <div class="inwrap">
                        <div class="uploadannex">
                            <span class="spanupload">upload</span>
                            <input type="file"/>
                        </div>
                        <a href="#" class="blankdoc" title="">空白KYC文档</a>

                        <div class="doclist">
                            <div class="docitem">
                                <span>荀晓霞的财务分析表</span>
                                <a href="javascript:;" class="a_removeitem" title="删除"></a>
                            </div>
                            <div class="docitem">
                                <span>荀晓霞的财务分析表</span>
                                <a href="javascript:;" class="a_removeitem" title="删除"></a>
                            </div>
                        </div>
                    </div>


                </div>
                <div class="btnwrap">
                    <a href="javascript:showinfo('#step5');" class="a_cancel" title="">上一步</a>
                    <a href="javascript:showinfo('#step7');" class="a_next" title="">下一步</a>
                    <a href="javascript:showinfo('#bookinglist');" class="a_cancel" title="">取消</a>
                </div>
            </div>
        </div>
        <!-- Step7-->
        <div id="step7" style="display: none;" class="procedure">
            <div class="proceduretitle">
                <span>Step7:上傳健康告知、建議書及其他附件檔案</span>
                <a href="javascript:showinfo('#step6');;" class="a_return" title="">返回</a>
            </div>
            <div class="procedurecontent">
                <span class="bookingdate">预约日期：2016-12-23 星期二</span>
                <span class="bookingdate">预约时间：14:00-15:00</span>
                <span class="bookingdate">预约顾问：HL00000014-何薇</span>

                <div class="uploadfilewrap">
                    <span class="spantitle">健康告知：</span>

                    <div class="inwrap">
                        <div class="uploadannex">
                            <span class="spanupload">upload</span>
                            <input type="file"/>
                        </div>
                        <a href="#" class="blankdoc" title="">空白健康告知</a>

                        <div class="doclist">
                            <div class="docitem">
                                <span>荀晓霞的财务分析表.Doxc</span>
                                <a href="javascript:;" class="a_removeitem" title="删除"></a>
                            </div>
                            <div class="docitem">
                                <span>荀晓霞的财务分析表</span>
                                <a href="javascript:;" class="a_removeitem" title="删除"></a>
                            </div>
                        </div>
                    </div>


                </div>
                <div class="uploadfilewrap">
                    <span class="spantitle">建议书：</span>

                    <div class="inwrap">
                        <div class="uploadannex">
                            <span class="spanupload">upload</span>
                            <input type="file"/>
                        </div>
                        <div class="doclist">
                            <div class="docitem">
                                <span>AIA安邦人寿.pdf</span>
                                <a href="javascript:;" class="a_removeitem" title="删除"></a>
                            </div>
                        </div>
                    </div>


                </div>
                <div class="uploadfilewrap">
                    <span class="spantitle">其他附件：</span>

                    <div class="inwrap">
                        <div class="uploadannex">
                            <span class="spanupload">upload</span>
                            <input type="file"/>
                        </div>
                        <div class="doclist">
                            <div class="docitem">
                                <span>荀晓霞的财务分析表.Doxc</span>
                                <a href="javascript:;" class="a_removeitem" title="删除"></a>
                            </div>
                        </div>
                    </div>


                </div>
                <div class="annexdescripition">
                    <span class="desctitle">【簽約應備文件】</span>

                    <div class="desccontent">
                        <p>請客戶務必攜帶身份證正本、地址證明、出生紙/戶口本/生存證明前來簽約。</p>

                        <p>1. 地址證明：必須是三個月內以持有人為名的賬單，水／電／瓦斯／電話／信用卡／政府機關任 一賬單或由派出所開立的居住證明；若是以配
                            偶為名的賬單，則須提供結婚證，但視保險公司是否通融。</p>

                        <p>2. 出生紙/戶口本：被保人未滿18歲者必須提供，內容必須能夠證明持有人與被保人的親子關係。</p>

                        <p>3. 生存證明：被保人未滿18歲者必須提供，7歲以下請提供針咭(一年內有效）/7歲以上請提供附成績單的學生手冊(一年內有效)。</p>
                    </div>
                </div>
                <div class="btnwrap">
                    <a href="javascript:showinfo('#step6');" class="a_cancel" title="">上一步</a>
                    <a href="javascript:showinfo('#step8');" class="a_next" title="">完成</a>
                    <a href="javascript:showinfo('#bookinglist');" class="a_cancel" title="">取消</a>
                </div>
            </div>
        </div>
        <!-- Step8-->
        <div id="step8" style="display: none;" class="procedure">
            <div class="proceduretitle">
                <span>Step8:預約資料預覽檢視</span>
                <a href="javascript:showinfo('#step7');;" class="a_return" title="">返回</a>
            </div>
            <div class="procedurecontent">
                <span class="bookingdate">预约日期：2016-12-23 星期二</span>
                <span class="bookingdate">预约时间：14:00-15:00</span>
                <span class="bookingdate">预约顾问：HL00000014-何薇</span>

                <span class="infotitle">带单顾问：</span>
                <table class="table_step3">
                    <tr>
                        <td>姓名：</td>
                        <td><label>何伟</label></td>
                        <td>联系电话：</td>
                        <td><label>13883844455</label></td>
                        <td>联系邮箱：</td>
                        <td><label>1688@163.com</label></td>
                        <td>預計到港人數：</td>
                        <td><label>2</label></td>
                    </tr>
                </table>

                <span class="infotitle">保單被保人：</span>
                <table class="table_step3">
                    <tr>
                        <td>中文姓名：</td>
                        <td colspan="3"><label>苏见信</label></td>
                        <td>英文姓名：</td>
                        <td><label>shinsu</label></td>
                    </tr>
                    <tr>
                        <td>身份证号：</td>
                        <td colspan="3"><label>500232523523526536</label></td>
                        <td>婚姻状况：</td>
                        <td><label>已婚</label></td>
                    </tr>
                    <tr>
                        <td>国籍：</td>
                        <td><label>中国</label></td>
                        <td>性别：</td>
                        <td><label>男</label></td>
                        <td>出生年月日：</td>
                        <td><label>2016-02-03</label></td>
                    </tr>
                    <tr>
                        <td>Email电邮：：</td>
                        <td colspan="5"><label>shin@shin.com</label></td>
                    </tr>
                    <tr>
                        <td>住宅/永久地址：</td>
                        <td colspan="5"><label>臺灣省花蓮市1688號</label></td>
                    </tr>
                    <tr>
                        <td>通訊地址/郵編：</td>
                        <td colspan="5"><label>臺灣省花蓮市1688號</label></td>
                    </tr>
                    <tr>
                        <td>聯絡電話：</td>
                        <td colspan="5">手機：<label>1355555555</label> 住宅： <label>1355555555</label>公司：
                            <label>1355555555</label></td>
                    </tr>
                    <tr>
                        <td>公司業務性質：</td>
                        <td><label>餐饮</label></td>
                        <td>現職工作年期：</td>
                        <td><label>6</label></td>
                        <td>現時每『月』收入 ：</td>
                        <td><label>HKD6000.00</label></td>
                    </tr>

                    <tr>
                        <td>公司名稱：</td>
                        <td colspan="2"><label>天度集团</label></td>
                        <td>職位及主要職務：</td>
                        <td colspan="2"><label>網頁設計師 </label>
                    </tr>
                    <tr>
                        <td>公司地址：</td>
                        <td colspan="5"><label>重慶市渝北區黃龍路朗俊中心 </label></td>
                    </tr>
                </table>

                <span class="infotitle">保單持有人：</span>
                <table class="table_step3">
                    <tr>
                        <td>中文姓名：</td>
                        <td colspan="3"><label>苏见信</label></td>
                        <td>英文姓名：</td>
                        <td><label>shinsu</label></td>
                    </tr>
                    <tr>
                        <td>身份证号：</td>
                        <td colspan="3"><label>500232523523526536</label></td>
                        <td>婚姻状况：</td>
                        <td><label>已婚</label></td>
                    </tr>
                    <tr>
                        <td>国籍：</td>
                        <td><label>中国</label></td>
                        <td>性别：</td>
                        <td><label>男</label></td>
                        <td>出生年月日：</td>
                        <td><label>2016-02-03</label></td>
                    </tr>
                    <tr>
                        <td>Email电邮：：</td>
                        <td colspan="5"><label>shin@shin.com</label></td>
                    </tr>
                    <tr>
                        <td>住宅/永久地址：</td>
                        <td colspan="5"><label>臺灣省花蓮市1688號</label></td>
                    </tr>
                    <tr>
                        <td>通訊地址/郵編：</td>
                        <td colspan="5"><label>臺灣省花蓮市1688號</label></td>
                    </tr>
                    <tr>
                        <td>聯絡電話：</td>
                        <td colspan="5">手機：<label>1355555555</label> 住宅： <label>1355555555</label>公司：
                            <label>1355555555</label></td>
                    </tr>
                    <tr>
                        <td>公司業務性質：</td>
                        <td><label>餐饮</label></td>
                        <td>現職工作年期：</td>
                        <td><label>6</label></td>
                        <td>現時每『月』收入 ：</td>
                        <td><label>HKD6000.00</label></td>
                    </tr>

                    <tr>
                        <td>公司名稱：</td>
                        <td colspan="2"><label>天度集团</label></td>
                        <td>職位及主要職務：</td>
                        <td colspan="2"><label>網頁設計師 </label>
                    </tr>
                    <tr>
                        <td>公司地址：</td>
                        <td colspan="5"><label>重慶市渝北區黃龍路朗俊中心 </label></td>
                    </tr>
                </table>
                <span class="infotitle infotitle1">KYC：</span>

                <div class="doclist doclist1">
                    <div class="docitem">
                        <span>荀晓霞的财务分析表</span>
                    </div>
                    <div class="docitem">
                        <span>荀晓霞的财务分析表</span>
                    </div>
                </div>
                <span class="infotitle">計劃內容：</span>
                <table class="table_step3">
                    <tr>
                        <td>保险公司：</td>
                        <td><label>AIA友邦人寿</label></td>
                        <td>签约地点：</td>
                        <td><label>AIA友邦人寿大厦</label></td>
                    </tr>
                    <tr>
                        <td>保单类型：</td>
                        <td><label>嚴重疾病保障</label></td>
                        <td>主約計畫/年期：</td>
                        <td><label>進泰安心保25年期 / 25 年期</label></td>
                    </tr>
                    <tr>
                        <td>保額/保費：</td>
                        <td colspan="3"><label>中国</label></td>

                    </tr>
                    <tr>
                        <td>附约：</td>
                        <td colspan="3"></td>
                    </tr>

                    <tr>
                        <td>保單幣別：</td>
                        <td><label>美金</label></td>
                        <td>繳費期別：</td>
                        <td><label>年缴</label></td>
                    </tr>
                    <tr>
                        <td>首期保費：</td>
                        <td><label>银联卡</label></td>
                        <td>红利分配：</td>
                        <td><label>积存</label></td>
                    </tr>
                    <tr>
                        <td>Email电邮：：</td>
                        <td colspan="3"><label>shin@shin.com</label></td>
                    </tr>
                    <tr>
                        <td>轉保聲明：</td>
                        <td colspan="3">是否於過去或打算於未來12個月內以此份投保取代任何現有壽險保單，或現有保單內大部份的壽險？： <label>否</label>
                        </td>
                    </tr>

                    <tr>
                        <td>保單提前生效：</td>
                        <td colspan="3"><label>否</label></td>
                    </tr>
                    <tr>
                        <td>其他说明：</td>
                        <td colspan="3"></td>
                    </tr>
                </table>

                <div class="btnwrap">
                    <a href="javascript:showinfo('#step7');" class="a_cancel" title="">上一步</a>
                    <a href="#" class="a_next" title="">完成</a>
                    <a href="javascript:showinfo('#bookinglist');" class="a_cancel" title="">取消</a>
                </div>
            </div>
        </div>

    </div>
