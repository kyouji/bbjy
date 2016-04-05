$(function () {
    init();
    $('#fileimg').change(function () {
        var objUrl = getObjectURL(this.files[0]);
        //获取图片的宽高
        console.log("objUrl = " + objUrl);
        if (objUrl) {
            $('.jcrop_w > img').attr("src", objUrl);

            $('.pre-1 > img').attr("src", objUrl);
            init();
        }
    });


});

//建立一個可存取到該file的url
function getObjectURL(file) {
    var url = null;
    if (window.createObjectURL != undefined) { // basic
        url = window.createObjectURL(file);
    } else if (window.URL != undefined) { // mozilla(firefox)
        url = window.URL.createObjectURL(file);
    } else if (window.webkitURL != undefined) { // webkit or chrome
        url = window.webkitURL.createObjectURL(file);
    }
    return url;
}
function init() {
    //默认图像居中显示
    cutImage($(".jcrop_w>img"));

    var _Jw = ($("#target").width() - 120) / 2,
        _Jh = ($("#target").height() - 120) / 2,
        _Jw2 = _Jw + 120,
        _Jh2 = _Jh + 120;

    $('#target').Jcrop({
        setSelect: [_Jw, _Jh, _Jw2, _Jh2],
        onChange: showPreview,
        onSelect: showPreview,
        bgFade: true,
        bgColor: "black",
        aspectRatio: 120 / 120,
        bgOpacity: .8

    });
}
//
function pre_img2(obj, rx, iw, ry, ih, cx, cy, ow, oh) {
    obj.css({
        width: Math.round(rx * iw) + 'px',
        height: Math.round(ry * ih) + 'px'
    });
    if (cy >= oh && cx >= ow) {
        obj.css({
            marginLeft: '-' + Math.round(rx * (cx - ow)) + 'px',
            marginTop: '-' + Math.round(ry * (cy - oh)) + 'px'
        });
    } else if (cy <= oh && cx >= ow) {
        obj.css({
            marginLeft: "-" + Math.round(rx * (cx - ow)) + 'px',
            marginTop: Math.round(ry * (oh - cy)) + 'px'
        });
    } else if (cy >= oh && cx <= ow) {
        obj.css({
            marginLeft: Math.round(rx * (ow - cx)) + 'px',
            marginTop: '-' + Math.round(ry * (cy - oh)) + 'px'
        });
    } else if (cy <= oh && cx <= ow) {
        obj.css({
            marginLeft: Math.round(rx * (ow - cx)) + 'px',
            marginTop: Math.round(ry * (oh - cy)) + 'px'
        });
    }

};
//默认图像位置
function cutImage(obj) {
    var w = 320,
        h = 320,
        iw = obj.width(),
        ih = obj.height();
    if (iw > w || ih > h) {
        if (iw / ih > w / h) {
            obj.css({
                width: w,
                height: w * ih / iw,
                top: (h - (w * ih / iw)) / 2,
                left: 0
            });
        } else {
            obj.css({
                height: h,
                width: h * iw / ih,
                top: 0,
                left: (w - (h * iw / ih)) / 2
            });
        }
    } else if (iw / ih == w / h) {
        obj.css({
            width: w,
            height: h,
            left: 0,
            top: 0
        });
    } else {
        obj.css({
            left: (w - iw) / 2,
            top: (h - ih ) / 2
        });
    }
}
function showPreview(c) {
    var iw = $('.jcrop_w>img').width(),
        ih = $('.jcrop_w>img').height(),
        ow = (320 - iw) / 2,
        oh = (320 - ih) / 2,
        rx = 120 / c.w,
        ry = 120 / c.h,
        rx1 = 90 / c.w,
        ry1 = 90 / c.h,
        rx2 = 40 / c.w,
        ry2 = 40 / c.h,
        _data = $(".jc-demo-box").attr("data");

    /*if (( $.browser.version == 7.0 || $.browser.version == 6.0) && (_data == 90 || _data == 270)) {
     pre_img2($('.pre-1 img'), rx, ih, ry, iw, c.x, c.y, ow, oh);
     } else {*/
    pre_img2($('.pre-1 img'), rx, iw, ry, ih, c.x, c.y, ow, oh);
    /*}*/
    $('#x').val(c.x);
    $('#y').val(c.y);
    $('#w').val(c.w);
    $('#h').val(c.h);
}