/**
 * Created by ftt on 2016/2/25.
 */
$(function () {
    /*
     * please don't change this code  Start
     * */
    var containname = $('.container');
    var containerheight = containname.height();
    if (containerheight <= 700) {
        // leftmenu height setting
        $('.leftnav').height(containname.height());

        $('.statuscount').height(containname.height() - 70);


        // detaildialog height setting
        $('.dialog-detailinfo').height(containname.height());

    } else if ($(window).height() > 770) {
        // leftmenu height setting
        $('.leftnav').height($(window).height() - 85);
        $('.statuscount').height($(window).height() - 85 - 68);

        // detaildialog height setting
        $('.dialog-detailinfo').height($(window).height() - 86);
    }
    if (containerheight > 800) {
        $('.leftnav').height(containname.height());

        $('.statuscount').height(containname.height() - 68);
    }
    setInterval(function () {
        var containname = $('.container');
        var containerheight = containname.height();
        if (containerheight <= 700) {
            // leftmenu height setting
            $('.leftnav').height($(window).height() - 85);

            $('.statuscount').height($(window).height() - 85 - 70);

            // detaildialog height setting
            $('.dialog-detailinfo').height(containname.height());

        } else if ($(window).height() > 770) {
            // leftmenu height setting
            $('.leftnav').height($(window).height() - 85);
            $('.statuscount').height($(window).height() - 85 - 70);

            // detaildialog height setting
            $('.dialog-detailinfo').height($(window).height() - 86);
        }
        if (containerheight > 800) {
            $('.leftnav').height(containname.height());

            $('.statuscount').height(containname.height() - 69);
        }
    }, 100);
    // manager loginout slidedown js
    $('#managerhover').managerdown('wrap_manager');
    $('div > .parentnode').togglemenu('div.children');
    $('input[name="txtselect"]').fttselect();
    /*
     * please don't change this code  End
     * */
});


// detailinfo for dialog js，just an example
function showdetaildialog(dialoghref, dialogid) {
    $(dialoghref).each(function () {
        $(this).click(function () {
        var id=$(this).attr('articleId');
    	 $.ajax({
			      type:"post",
			      url:"/data/detail",
			      data:{"id":id},
			      success:function(data){
						$('#' + dialogid).html(data);
						$('#' + dialogid).find('.dialog-close').bind('click', function () {
			        $('#' + dialogid).animate({right: "-800px"}, 800);
			    });
			      }
			  });	
        	$('#' + dialogid).css("right", "-800px");
            $('#' + dialogid).animate({right: "0"}, 800);
        });
    });
}

// manager loginout slidedown js
$.fn.managerdown = function (wrapname) {
    $(this).hover(function () {
        $(this).find('.' + wrapname).stop(true, false).slideDown(300);
    }, function () {
        $(this).find('.' + wrapname).stop(true, false).slideUp(300);
    })
};

$.fn.togglemenu = function (toggleobj) {
    $(this).each(function () {
        var i = false;
        var str = $(this).siblings(toggleobj).css('display');
        str == 'none' ? i = false : i = true;
        console.info(str);
        $(this).bind('click', function () {
            if (i == true) {
                $(this).siblings(toggleobj).stop(true, false).slideUp(500);
            } else {
                $(this).siblings(toggleobj).stop(true, false).slideDown(500);
            }
            i = !i;
        });
    });
}

// 保单列表查询保单下拉框js
$.fn.fttselect = function () {
    $(this).focus(function () {
        var height1 = $(this).height();
        var height2 = $(this).css('margin-top');
        var height3 = 0;
        $(this).siblings("label").height() != null ? height3 = $(this).siblings("label").height() : height3;
        var toheight = parseInt(height1, 0) + parseInt(height2, 0) + parseInt(height3, 0) + 10;
        $(this).siblings(".txtselect_item").css('top', toheight + 'px');
        $(this).siblings(".txtselect_item").stop(true, false).slideDown(300);
        $(this).siblings(".txtselect_item").find('div a').click(function () {
            var idkey = $(this).attr('userid');
            var txt = $(this).text();
            var inputtext = $(this).parents('.txtselect_item').siblings('input[name="txtselect"]');
            inputtext.attr('userid', idkey);
            inputtext.val(txt);
            $(this).parents('.txtselect_item').stop(true, false).slideUp(300);
        });
    }).blur(function () {
        $(this).siblings(".txtselect_item").stop(true, false).slideUp();
    });
}

// 右侧客户状态显示
$.fn.statusshow = function (warpid1, wrapid2, showitemid, lineclassname) {
    var iture = true;
    $(this).click(function () {
        if (iture == true) {
            $("#" + warpid1).animate({width: "78%"}, 500);
            $("#" + wrapid2).animate({width: "21%", borderLeftWidth: "1px", marginLeft: '1%'}, 500, function () {
                $(this).find('#' + showitemid).css('display', 'block');
                $(this).find(lineclassname).each(function () {
                    var widthlenght = $(this).find('label').width();
                    $(this).find('label').width(0);
                    $(this).find('label').animate({width: widthlenght}, 500);
                });
            });
            iture = !iture;
        } else {
            $("#" + warpid1).animate({width: "100%"}, 500);
            $("#" + wrapid2).find('#' + showitemid).css('display', 'none');
            $("#" + wrapid2).animate({width: "0", borderLeftWidth: "0", marginLeft: '0'}, 500);
            iture = !iture;
        }
        $(this).toggleClass('on');
    });
}

/*
 点击nav切换内容
 navwrap nav容器id
 contentwrap 内容容器id
 navcurclass nav选中classname
 */
function togglenavitem(navwrap, contentwrap, navcurclass) {
    $(navwrap).children().each(function () {
        $(this).click(function () {
            $(this).addClass(navcurclass);
            $(this).siblings().removeClass(navcurclass);
            $(contentwrap).children(':eq(' + $(this).index() + ')').fadeIn();
            $(contentwrap).children(':eq(' + $(this).index() + ')').siblings().hide();
        });
    });
}

function showinfo(showdivid) {
    $(showdivid).fadeIn();
    $(showdivid).siblings().hide();
}

// 实现多选框全选
function checkbox(checkallname, checkboxname) { // 全选名称 选项名称

    var checkitems = $(checkboxname),
        checkallitem = $(checkallname),
        itemslength = checkitems.length,
        arrstr = [];

    checkallitem.bind('click', function () {
        var allcheck = $(this);
        if ($(this).prop("checked") == true) {
            arrstr.length = itemslength;
            //console.info(checkitems.length);
            $(this).siblings('.searchitem').hide();
            $(this).siblings('.edititem').fadeIn();
        }
        else {
            $(this).siblings('.searchitem').fadeIn();
            $(this).siblings('.edititem').hide();
            arrstr.length = 0;
        }
        $(checkboxname).each(function () {
            if (allcheck.prop('checked') == true) {
                $(this).fadeIn();
                $(this).siblings(".lblordernum").hide();
                $(this).prop('checked', true);
            } else if (allcheck.prop('checked') == false) {
                $(this).hide();
                $(this).siblings(".lblordernum").fadeIn();
                $(this).prop('checked', false);
            }
        });
    });
    $(checkboxname).each(function () {
        $(this).bind('click', function () {
            if ($(this).prop('checked') == true) {
                arrstr.push($(this));
            } else {
                arrstr.shift($(this));
            }
            if (arrstr.length == itemslength) {
                checkallitem.prop('checked', true);
            } else if (arrstr.length == 0) {
                $(checkallname).siblings('.edititem').hide();
                $(checkallname).siblings('.searchitem').fadeIn();
                $(checkboxname).hide();
                $(checkboxname).siblings('.lblordernum').fadeIn();
            } else {
                checkallitem.prop('checked', false);
            }
        });
    });
}

// 弹窗并控制弹窗显示内容
$.fn.showdialogdetail = function (option) {
    var defaults = {
        dialogid: '#datedialog', // 弹窗id名
        navwrapid: '#navitem1', // 弹窗内nav容器id
        navcurrentclass:'current', // nav 选中样式
        choosenavnum: 0, //选择nav的下标 0联系信息 1 客户信息 2 预约签单
        contentwrapid: '#contentitem1', //内容容器id
        closeherf: '.dialog-close', // 关闭弹窗标签样式名
        detailcontentid: '#infoform' // 内容内要显示的div id名
    };
    var options = $.extend(defaults, option);

    $(this).click(function () {
        $(options.dialogid).animate({right: "0"}, 500);
        $(options.navwrapid).children().removeClass(options.navcurrentclass);
        $(options.navwrapid).children(":eq(" + options.choosenavnum + ")").addClass(options.navcurrentclass);
        $(options.contentwrapid).children().hide();
        $(options.contentwrapid).children(":eq(" + options.choosenavnum + ")").fadeIn();
        if (options.choosenavnum == 2) {
            $(options.detailcontentid).fadeIn();
            $(options.detailcontentid).siblings().hide();
        }
    });
    $(options.dialogid).find(options.closeherf).click(function () {
        $(options.dialogid).animate({right: "-800px"}, 500);

    });

}

