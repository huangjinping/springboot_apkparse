/**
 * Created by harrishuang on 2019/3/4.
 */
function getParameter(name) {
    var quary = window.location.search;
    var len = quary.length;
    var start = quary.indexOf(name);
    if (start == -1) {
        return "";
    }
    var end = quary.indexOf("&", start);
    if (end == -1) {
        var param = quary.substring(start);
        return param.split("=")[1];
    }
    var param = quary.substring(start, end);
    return param.split("=")[1];
}


//阿拉伯数字转中文数字
function NoToChinese(num) {
    if (!/^\d*(\.\d*)?$/.test(num)) {
        alert("Number is wrong!");
        return "Number is wrong!";
    }
    var AA = new Array("零", "一", "二", "三", "四", "五", "六", "七", "八", "九");
    var BB = new Array("", "十", "百", "千", "万", "亿", "点", "");
    var a = ("" + num).replace(/(^0*)/g, "").split("."),
        k = 0,
        re = "";
    for (var i = a[0].length - 1; i >= 0; i--) {
        switch (k) {
            case 0:
                re = BB[7] + re;
                break;
            case 4:
                if (!new RegExp("0{4}\\d{" + (a[0].length - i - 1) + "}$").test(a[0]))
                    re = BB[4] + re;
                break;
            case 8:
                re = BB[5] + re;
                BB[7] = BB[5];
                k = 0;
                break;
        }
        if (k % 4 == 2 && a[0].charAt(i + 2) != 0 && a[0].charAt(i + 1) == 0) re = AA[0] + re;
        if (a[0].charAt(i) != 0) re = AA[a[0].charAt(i)] + BB[k % 4] + re;
        k++;
    }
    if (a.length > 1) //加上小数部分(如果有小数部分)
    {
        re += BB[6];
        for (var i = 0; i < a[1].length; i++) re += AA[a[1].charAt(i)];
    }
    return re;
};

var digitUppercase = function (n) {
    var fraction = ['角', '分'];
    var digit = [
        '零', '壹', '贰', '叁', '肆',
        '伍', '陆', '柒', '捌', '玖'
    ];
    var unit = [
        ['元', '万', '亿'],
        ['', '拾', '佰', '仟']
    ];
    var head = n < 0 ? '欠' : '';
    n = Math.abs(n);
    var s = '';
    for (var i = 0; i < fraction.length; i++) {
        s += (digit[Math.floor(n * 10 * Math.pow(10, i)) % 10] + fraction[i]).replace(/零./, '');
    }
    s = s || '整';
    n = Math.floor(n);
    for (var i = 0; i < unit[0].length && n > 0; i++) {
        var p = '';
        for (var j = 0; j < unit[1].length && n > 0; j++) {
            p = digit[n % 10] + unit[1][j] + p;
            n = Math.floor(n / 10);
        }
        s = p.replace(/(零.)*零$/, '').replace(/^$/, '零') + unit[0][i] + s;
    }
    return head + s.replace(/(零.)*零元/, '元')
        .replace(/(零.)+/g, '零')
        .replace(/^整$/, '零元整');
}


function hdCheckedHr(capitalId) {
    if (("hrym" == capitalId) || ("hr" == capitalId) || ("haier_rent" == capitalId)) {
        return true;
    }
    return false;
}

function myTrim(x) {
    return x.replace(/\s+/g, "");
//        return x.replace(/^\s+|\s+$/gm,'');
}

String.prototype.trim = function () {
    return this.replace(/(^\s*)|(\s*$)/g, "");
}
String.prototype.ltrim = function () {
    return this.replace(/(^\s*)/g, "");
}
String.prototype.rtrim = function () {
    return this.replace(/(\s*$)/g, "");
}

String.prototype.startWith = function (str) {
    if (str == null || str == "" || this.length == 0 || str.length > this.length)
        return false;
    if (this.substr(0, str.length) == str)
        return true;
    else
        return false;
    return true;
}

String.prototype.endWith = function (str) {
    if (str == null || str == "" || this.length == 0 || str.length > this.length) {
        return false;
    }
    if (this.substring(this.length - str.length)) {
        return true;
    } else {
        return false;
    }
    return true;
};


String.prototype.formatNoPassByName = function () {
    var str = this;
    console.log("=========================================================>>>>>======");
    console.log(str);
    if (null != str && str != undefined) {
        if (str.length <= 3) {
            return "*" + str.substring(1, str.length);
        } else if (str.length > 3 && str.length <= 6) {
            return "**" + str.substring(2, str.length);
        } else if (str.length > 6) {
            return str.substring(0, 2) + "****" + str.substring(6, str.length)
        }
    } else {
        return "";
    }
}


String.prototype.formatPhone = function () {
    var str = this;
    if (isEmpty(str)) {
        return str;
    }
    if (str.length < 9) {
        return str;
    }
    var pat = /(\d{3})\d*(\d{4})/
    var b = str.replace(pat, '$1****$2');
    return b;
}

function authOperator(param) {
    var userInfo = param.userInfo;
    var backUrl = param.backUrl;
    var LoginTokenA = JSON.parse(localStorage.getItem('register'));
    var operatorUrl_ios = "https://api.51datakey.com/h5/importV3/index.html#/carrier?showTitleBar=NO&apiKey=" + mojieapiKey + "&goBackEnable=1&userId=" +
        LoginTokenA.id +
        "&carrier_name=" + encodeURIComponent(userInfo.idName) +
        "&carrier_phone="
        + userInfo.phoneNo + "&carrier_idcard="
        + userInfo.idNo + "&backUrl=" + encodeURIComponent(backUrl);
    window.location.href = operatorUrl_ios;
}


function authLearning(param) {
    var userInfo = param.userInfo;
    var backUrl = param.backUrl;
    var LoginTokenA = JSON.parse(localStorage.getItem('register'));
    var xunxinUrl_ios = "https://api.51datakey.com/h5/importV3/index.html#/chsi?showTitleBar=NO&apiKey=" + mojieapiKey + "&goBackEnable=1&userId=" +
        LoginTokenA.id +
        "&carrier_phone="
        + userInfo.phoneNo + "&carrier_idcard="
        + userInfo.idNo + "&backUrl=" + encodeURIComponent(backUrl);
    window.location.href = xunxinUrl_ios;
}


function inputUpdateColor() {
    requestAnimationFrame(inputUpdateColor);
    // $("input:disabled").each(function (p1, p2, p3) {
    //     if ($(p2).val()) {
    //         $(p2).css("-webkit-text-fill-color", "red");
    //         $(p2).css("color", "red");
    //         $(p2).css("opacity","1");
    //     } else {
    //         $(p2).css("-webkit-text-fill-color", "#ccc");
    //         $(p2).css("color", "#ccc");
    //         $(p2).css("opacity","1");
    //
    //     }
    // });

    $("input[type!='checkbox']").each(function (p1, p2, p3) {
        if ($(p2).val()) {
            $(p2).css("-webkit-text-fill-color", "black");
            $(p2).css("color", "black");
            $(p2).css("opacity", "1");
        } else {
            $(p2).css("-webkit-text-fill-color", "#ccc");
            $(p2).css("color", "#ccc");
            $(p2).css("opacity", "1");
        }
    });
}


var loadJS = function (url, el) {
    var head = document.getElementsByTagName('head');
    if (head && head.length) {
        head = head[0];
    } else {
        head = document.body;
    }
    var script = document.createElement('script');
    script.src = url;
    script.type = "text/javascript";
    head.appendChild(script);
    script.onload = script.onreadystatechange = function () {
//script 标签，IE 下有 onreadystatechange 事件, w3c 标准有 onload 事件
//这些 readyState 是针对IE8及以下的，W3C 标准的 script 标签没有 onreadystatechange 和 this.readyState ,
//文件加载不成功 onload 不会执行，
//(!this.readyState) 是针对 W3C标准的, IE 9 也支持 W3C标准的 onload
        if ((!this.readyState) || this.readyState == "complete" || this.readyState == "loaded") {
            el.success();
        }
        el.complate();
    }//end onreadystatechange
}


function uploadData(e) {


    if (window && window.$) {
        try {
            // console.log("------http://pv.sohu.com/cityjson?ie=utf-8");
            loadJS("https://pv.sohu.com/cityjson?ie=utf-8", {
                success: function () {
                    sessionStorage.setItem("cip", returnCitySN['cip']);
                    console.log("success");

                },
                complate: function () {
                    console.log("complate");
                }
            });
        } catch (e) {

        }
    }


    console.log("==============uploadData================>>>>");
    if (!isLogin()) {
        return;
    }

    console.log(typeof httpss);
    console.log("==============uploadData1================>>>>");


    if (window.$ && (typeof httpss) != 'undefined') {
        var LoginTokenA = JSON.parse(localStorage.getItem('register'));
        var params = {};
        var https = httpss;

        params.token = LoginTokenA.token;
        params.id = LoginTokenA.id;
        params.opr_type = "1";
        if (e && !isEmpty(e.opr_type)) {
            params.opr_type = e.opr_type;
        }
        if (e && !isEmpty(e.business_id)) {
            params.business_id = e.business_id;
        }
        params.phone_model = window.navigator.userAgent;

        if (!isEmpty(params.phone_model)) {
            if (params.phone_model.length > 100) {
                params.phone_model = params.phone_model.substring(0, 99);
            }
        }
        params.area = window.location.href;
        if (!isEmpty(params.area)) {
            if (params.area.length > 200) {
                params.area = params.area.substring(0, 200);
            }
        }

        var lbsAddress = localStorage.getItem("lbsAddress");
        if (!isEmpty(lbsAddress)) {
            params.imei = lbsAddress;
        }

        var cip = sessionStorage.getItem("cip");
        if (!isEmpty(cip)) {
            params.ip = cip;
        }

        console.log(params);
        console.log("==============uploadData3================>>>>");

        $.ajax({
            url: https + "mobile/operation/recordOperation",
            dataType: "text",
            type: "POST",
            data: params,
            error: function (msg) {
                console.log("==============uploadData5================>>>>");
                console.log("");
                console.log(msg)
            },
            success: function (data) {
                console.log("==============uploadData4================>>>>");
                console.log(data);
            }
        })
    } else {
        console.log("没有引入jquery");
    }

}


function isMiniPrograms() {
    var isMiniprogram = false;
    // 判断是否是小程序打开
    if (wx && wx.miniProgram) {
        wx.miniProgram.getEnv(function (res) {
            if (res.miniprogram) {
                //在小程序中
                isMiniprogram = true;
            }

        });
    }

    return isMiniprogram;
}


function isWeiXin() {
    //window.navigator.userAgent属性包含了浏览器类型、版本、操作系统类型、浏览器引擎类型等信息，这个属性可以用来判断浏览器类型
    var ua = window.navigator.userAgent.toLowerCase();
    //通过正则表达式匹配ua中是否含有MicroMessenger字符串
    if (ua.match(/MicroMessenger/i) == 'micromessenger') {
        return true;
    } else {
        return false;
    }
}


function iosDate(data) {
    return data = data.replace(/\-/g, '/')
}


Date.prototype.Format = function (fmt) { //author: meizz
    var o = {
        "M+": this.getMonth() + 1,                 //月份
        "d+": this.getDate(),                    //日
        "h+": this.getHours(),                   //小时
        "m+": this.getMinutes(),                 //分
        "s+": this.getSeconds(),                 //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds()             //毫秒
    };
    if (/(y+)/.test(fmt))
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}

function isEmpty(src) {
    if (typeof (src) == 'undefined' || src == null || src == "" || src == 'null') {
        return true;
    } else {
        return false;
    }
}


function isLogin() {
    var data = localStorage.getItem('register')
    if (isEmpty(data)) {
        return false;
    }
    var LoginTokenAS = JSON.parse(localStorage.getItem('register'));
    if (LoginTokenAS == null) {
        return false;
    }
    if (isEmpty(LoginTokenAS.token)) {
        return false;
    }
    return true;
}


function dismissLoading(e) {
    var loading = document.getElementById("loading_hjp");
    if (loading) {
        loading.remove();
    }
}

function showLoading(e) {
    dismissLoading();
    var img = document.createElement("img");
    img.width = 50;
    img.height = 50;
    img.style.objectFit = 'cover';
    img.src = "ic_loadging.gif";
    img.style.cssText = "position: fixed;" +
        "top: 50%;left: 50%;" +
        "transform: translate(-50%, -50%);" +
        "z-index: 999999999999;";
    if (e) {
        if (!isEmpty(e.src)) {
            img.src = e.src;
        }
    }
    var div = document.createElement("div");
    div.id = "loading_hjp";
    div.style.cssText = "max-width:60%;" +
        "min-width:150px;" +
        "min-height:100px;" +
        "padding:14px 14px;color: rgb(255, 255, 255);" +
        "line-height: 40px;" +
        "text-align: center;" +
        "border-radius: 4px;" +
        "position: fixed;" +
        "top: 50%;left: 50%;" +
        "transform: translate(-50%, -50%);" +
        "z-index: 999999999999;" +
        "background: rgba(0, 0, 0,.7);" +
        "font-size: 16px;";
    div.appendChild(img);


    // var p = document.createElement("p");
    // p.style.cssText="color:white";
    // div.append(p);
    // if (e) {
    //     if (!isEmpty(e.message)) {
    //         p.innerHTML = e.message;
    //     }
    // }
    document.body.appendChild(div);
}


function initJsConfig() {
    var JsConfigUrl = "";
    if (app_debug) {
        JsConfigUrl = "" + https + "weixin/getAllJsConfig";
    } else {
        JsConfigUrl = "" + https + "sikuPay/getJsConfig";
    }
    // JsConfigUrl = "" + https + "weixin/getAllSignToken";


    $.ajax({
        type: "POST",
        url: JsConfigUrl,
        dataType: "text",
        data: {
            "url": location.href.split('#')[0]
        },
        success: function (data) {
            var result = eval('(' + data + ')');
            console.log(result)
            wx.config({
                // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
                debug: false,
                // 必填，公众号的唯一标识
                appId: result.result.appId,
                // 必填，生成签名的时间戳
                timestamp: result.result.timestamp,
                // 必填，生成签名的随机串
                nonceStr: result.result.nonceStr,
                // 必填，签名，见附录1
                signature: result.result.signature,
                // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
                jsApiList: ['checkJsApi', 'scanQRCode'],

            });
        }
    })

    wx.error(function (res) {
        console.log(res)
//            alert("出错了：" + res.errMsg);
        //这个地方的好处就是wx.config配置错误，会弹出窗口哪里错误，然后根据微信文档查询即可。
    });
}

function GetQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]);
    return null;
}

//获取用户的openId
function getWxOpenid() {
    //获取用户的openId
    var LoginTokenA = JSON.parse(localStorage.getItem('register'));
    var jsData = {};
    jsData.jsCode = GetQueryString("code");
    jsData.type = '01'
    jsData.token = LoginTokenA.token;
    jsData.id = LoginTokenA.id;
    var https = httpss;
    $.ajax({
        url: https + "mobile/payment/getWxOpenid",
        dataType: "text",
        type: "POST",
        data: jsData,
        error: function (msg) {
            dismissLoading();
            console.log(msg)
        },
        success: function (data) {
            var resdataH = eval('(' + data + ')');
            console.log(resdataH);
            alert(resdataH);
            if (resdataH.code == 0) {
                var openIdA = resdataH.result.openId;
                if (!isEmpty(openIdA)) {
                    localStorage.setItem('openIdAH', openIdA)
                }
            }
        }
    })
}


//删除本地图片
function clearLocationPic() {
    localStorage.removeItem("HD_PIC_DATA");
}

var DyOrderType = {
    NRX: 'N'
}

/**
 *沃付问题！
 */
function onCheckWord() {

    var auditMessageDataH = {};
    if (localStorage.getItem('fenqiShenhe1') == 1) {
        auditMessageDataH = JSON.parse(localStorage.getItem('auditMessageDataH'));
        getCheckWord();
    } else if (localStorage.getItem('fenqiShenhe1') == 2) {
        multiSubmit();
    }

    function getCheckWord() {
        var LoginTokenA = JSON.parse(localStorage.getItem('register'));
        var tijiaoDataH = {};
        var https = httpss;
        tijiaoDataH.id = LoginTokenA.id;
        tijiaoDataH.token = LoginTokenA.token;
        tijiaoDataH.packageId = auditMessageDataH.packageId;
        tijiaoDataH.caseId = localStorage.getItem('zhuanAncaseId');
        tijiaoDataH.applyAmout = auditMessageDataH.fenqiJIneMH;

        console.log(tijiaoDataH)
        showLoading();
        $.ajax({
            type: "POST",
            url: "" + https + "mobile/order/countOrderByIdNo",
            dataType: "text",
            data: tijiaoDataH,
            success: function (res) {
                dismissLoading()
                var submitList = eval('(' + res + ')');
                console.log(submitList)
                if (submitList.code == 0) {
                    var result = submitList.result;

                    if ("1" == result.refuseOrder) {
                        showToast("暂不支持使用该资方办理分期");
                        return;
                    }
                    if ("1" == result.hasOrder) {
                        $.MsgBox.Confirm({
                            msg: "您当前已有还款中订单，是否确认再次提交？",
                            positiveTitle: "取消",
                            negativeTitle: "确认",
                            callback: function () {

                            },
                            negativeCallback: function () {
                                multiSubmit();
                            }
                        });
                        return;
                    }

                    multiSubmit();

                } else {
                    showToast(submitList.message);
                }
            },
            error: function (msg) {
                dismissLoading();
                console.log(msg)
                showToast("服务异常请重试");
            }
        })
    }
}

function dyCheckResponse(res) {
    if (!isEmpty(res)) {
        if (-2 == res.code) {
            window.location.href = "../login/login.html?v=" + Math.random();
            return false;
        }
    }
    return true;
}


function dyCheckAsync(e) {
    var timer = setTimeout(function () {
        clearTimeout(timer)
        var param = {};
        var user = JSON.parse(localStorage.getItem('register'));
        if (isEmpty(user)) {
            return;
        }

        param.id = user.id;
        param.token = user.token;
        console.log(param);
        var https = httpss;
        $.ajax({
            type: "POST",
            url: "" + https + "mobile/customInfo/advanced",
            dataType: "text",
            data: param,
            success: function (res) {
                var resloginData = eval('(' + res + ')');
                console.log(resloginData)
                if (!dyCheckResponse(resloginData)) {
                    return;
                }
                if (resloginData.code == 0) {

                } else {
                    if (!isEmpty(resloginData.message)) {
                        showToast(resloginData.message)
                    } else {
                        showToast("支付失败请重试！")
                    }
                }
                if (e) {
                    if (e.onCallBack) {
                        e.onCallBack(resloginData);
                    }
                }
            },
            error: function (msg) {

            }
        });
    }, 1000)
}


/**
 * 综合提交
 */
function multiSubmit() {

    if (localStorage.getItem('fenqiShenhe1') == 1) {
        var auditMessageDataH = JSON.parse(localStorage.getItem('auditMessageDataH'));
        if (auditMessageDataH != null) {
            var protocol = auditMessageDataH.protocol;
            if (!isEmpty(protocol)) {
                //跳转到晋商链接
                window.location.href = "../newUrl/jsCustContractUrl.html?position=11&source=h5&version=" + Math.random();
                return;
            }
        }
        submitOrder({});
    } else if (localStorage.getItem('fenqiShenhe1') == 2) {

        var auditMessageDataH = JSON.parse(localStorage.getItem('auditMessageDataH'));
        if (auditMessageDataH != null) {
            var protocol = auditMessageDataH.protocol;
            if (!isEmpty(protocol)) {
                //跳转到晋商链接
                window.location.href = "../newUrl/jsCustContractUrl.html?position=11&source=h5&version=" + Math.random();
                return;
            }
        }
        submitRefuse({});
    }
}


/**
 * 提交分期订单
 * @param e
 */
function submitOrder(e) {
    //    获取保存的图片
    function getPicData() {
        var result = {
            picList: "",
            htPicList: ""
        };
        var picDataString = localStorage.getItem("HD_PIC_DATA");
        if (picDataString) {
            var picData = JSON.parse(picDataString);
            if (picData.hetongImgArr) {
                var hetongImgArr = picData.hetongImgArr;
                hetongImgArr = picData.hetongImgArr;
                var list = [];
                if (hetongImgArr.length > 0) {
                    hetongImgArr.forEach(function (e) {
                        list.push({
                            relativePath: e
                        });
                    });
                    result.picList = JSON.stringify(list);
                }
            }
            if (picData.qitaImgArr) {
                var qitaImgArr = picData.qitaImgArr;
                var list = [];
                qitaImgArr.forEach(function (e) {
                    list.push({
                        relativePath: e
                    });
                    result.htPicList = JSON.stringify(list);
                });
            }
        }
//        localStorage.setItem("HD_PIC_DATA","");
        return result;
    }

    var picData = getPicData();
    var LoginTokenA = JSON.parse(localStorage.getItem('register'));
    var auditMessageDataH = JSON.parse(localStorage.getItem('auditMessageDataH'));
    // console.log(auditMessageDataH)
    var tijiaoDataH = {};
    var https = httpss;

    tijiaoDataH.id = LoginTokenA.id;
    tijiaoDataH.token = LoginTokenA.token;
    tijiaoDataH.commoditys = localStorage.getItem('commodityList')
    // tijiaoDataH.applyAmount = theRequest.fenqiJIneMH  //申请价格
    tijiaoDataH.applyAmount = auditMessageDataH.fenqiJIneMH  //申请价格
    tijiaoDataH.downpayment = 0  //首付款
    // tijiaoDataH.totalAmount = theRequest.zongjia  //总价
    tijiaoDataH.totalAmount = auditMessageDataH.zongjia //总价
    // tijiaoDataH.packageId = theRequest.packageId  //商品包ID
    tijiaoDataH.packageId = auditMessageDataH.packageId  //商品包ID
    tijiaoDataH.caseId = localStorage.getItem('zhuanAncaseId')  //专案ID
    // tijiaoDataH.businessId = theRequest.bussinessId //商户ID
    tijiaoDataH.businessId = auditMessageDataH.bussinessId //商户ID
    tijiaoDataH.bankcardId = localStorage.getItem("BankSSId")  //银行卡ID
    // tijiaoDataH.picList = ''  //其他图片
    // tijiaoDataH.orderNo = ''  //订单号
    // tijiaoDataH.terminal = ''  //终端（下单渠道）
    tijiaoDataH.applyIp = '127.0.0.1'  //订单号
    tijiaoDataH.lbsAddress = '116.38,39.90'  //
    tijiaoDataH.imei = '111111'  //
    tijiaoDataH.lbsAddressName = '天安门'  //
    tijiaoDataH.requestType = 'h5'
    tijiaoDataH.picList = picData.htPicList;
    tijiaoDataH.htPicList = picData.picList;

    var lbsAddress = localStorage.getItem("lbsAddress");
    if (!isEmpty(lbsAddress)) {
        tijiaoDataH.lbsAddress = lbsAddress;
    }

    var useUserk12 = localStorage.getItem("useUserk12");
    if (!isEmpty(useUserk12)) {
        var useUser = JSON.parse(useUserk12);
        tijiaoDataH.useUserName = useUser.useUserName;
        tijiaoDataH.useUserage = useUser.useUserage;
        tijiaoDataH.serviceStartDt = useUser.serviceStartDt;
        tijiaoDataH.serviceEndDt = useUser.serviceEndDt;
        tijiaoDataH.userReltion = useUser.userReltionValue;
    }
    console.log(tijiaoDataH);
    console.log(JSON.stringify(tijiaoDataH));

    showLoading();
    $.ajax({
        type: "POST",
        url: "" + https + "mobile/order/submit",
        dataType: "text",
        data: tijiaoDataH,
        timeout: 60 * 1000,
        success: function (res) {
            dismissLoading()
            var submitList = eval('(' + res + ')');
            console.log(submitList)
            $(".loading").hide();


            if (submitList.code == 0) {
                console.log(submitList.result.bestSignUrl)
                if (!isEmpty(submitList.result.urlFor91)) {
                    location.href = submitList.result.urlFor91;
                    return;
                }


                if ("1" == submitList.result.payFlag) {
                    location.href = "../myBill/beheadInterest.html?actionType=submitOrder&applyIdId=" + submitList.result.applyId;
                    return;
                }

                if (submitList.result.bestSignUrl == undefined) {
                    location.href = '../auditMessage/jiaoyiXiang.html?v=' + Math.random() + '&applyIdA=' + submitList.result.applyId + '&orderStatus=' + submitList.result.orderStatus;
                } else {
                    location.href = "../OrderDetails/agreement.html?actionType=submitOrder&applyId=" + submitList.result.applyId;
                }
            } else {
                if (!isEmpty(submitList.message)) {
                    showToast(submitList.message);
                } else {
                    showToast("提交订单失败,请稍后重试！");

                }

            }
        },
        error: function (msg) {
            dismissLoading()
            $(".loading").hide();
            console.log(msg)
            showToast("服务异常请重试");
        }
    })
}


/**
 * 打回修改的订单
 * @param e
 */
function submitRefuse(e) {

    function getPicData() {
        var result = {
            picList: "",
            htPicList: ""
        };
        var picDataString = localStorage.getItem("HD_PIC_DATA");
        if (picDataString) {
            var picData = JSON.parse(picDataString);
            if (picData.hetongImgArr) {
                var hetongImgArr = picData.hetongImgArr;
                hetongImgArr = picData.hetongImgArr;
                var list = [];
                if (hetongImgArr.length > 0) {
                    hetongImgArr.forEach(function (e) {
                        list.push({
                            relativePath: e
                        });
                    });
                    result.picList = JSON.stringify(list);
                }
            }
            if (picData.qitaImgArr) {
                var qitaImgArr = picData.qitaImgArr;
                var list = [];
                qitaImgArr.forEach(function (e) {
                    list.push({
                        relativePath: e
                    });
                    result.htPicList = JSON.stringify(list);

                });
            }
        }
        return result;
    }

    var picData = getPicData();
    var LoginTokenA = JSON.parse(localStorage.getItem('register'));
    var auditMessageDataH2 = JSON.parse(localStorage.getItem('auditMessageDataH2'));
    var tijiaoDataH = {};
    var https = httpss;
    tijiaoDataH.id = LoginTokenA.id;
    tijiaoDataH.token = LoginTokenA.token;
    tijiaoDataH.orderNo = auditMessageDataH2.commodityIds
    tijiaoDataH.bankcardId = localStorage.getItem("BankSSId");
    tijiaoDataH.picList = picData.htPicList;
    tijiaoDataH.htPicList = picData.picList;
    tijiaoDataH.requestType = 'h5'
    var lbsAddress = localStorage.getItem("lbsAddress");
    if (!isEmpty(lbsAddress)) {
        tijiaoDataH.lbsAddress = lbsAddress;
    }
    var useUserk12 = localStorage.getItem("useUserk12");
    if (!isEmpty(useUserk12)) {
        var useUser = JSON.parse(useUserk12);
        tijiaoDataH.useUserName = useUser.useUserName;
        tijiaoDataH.useUserage = useUser.useUserage;
        tijiaoDataH.serviceStartDt = useUser.serviceStartDt;
        tijiaoDataH.serviceEndDt = useUser.serviceEndDt;
        tijiaoDataH.userReltion = useUser.userReltionValue;
    }
    console.log(tijiaoDataH)
    showLoading();
    $.ajax({
        type: "POST",
        timeout: 60 * 1000,
        url: "" + https + "mobile/order/submit",
        dataType: "text",
        data: tijiaoDataH,
        success: function (res) {
            dismissLoading()
            var submitList = eval('(' + res + ')');
            console.log(submitList)
            if (submitList.code == 0) {
                if (!isEmpty(submitList.result.urlFor91)) {
                    location.href = submitList.result.urlFor91;
                    return;
                }

                if ("1" == submitList.result.payFlag) {
                    location.href = "../myBill/beheadInterest.html?actionType=submitOrder&applyIdId=" + submitList.result.applyId;
                    return;
                }
                if (submitList.result.bestSignUrl == undefined) {
                    location.href = '../auditMessage/jiaoyiXiang.html?v=' + Math.random() + '&applyIdA=' + submitList.result.applyId + '&orderStatus=' + submitList.result.orderStatus;
                } else {
                    location.href = "../OrderDetails/agreement.html?actionType=submitOrder&applyId=" + submitList.result.applyId;
                }
            } else {
                if (!isEmpty(submitList.message)) {
                    showToast(submitList.message);
                } else {
                    showToast("提交订单失败,请稍后重试！");
                }
            }
        },
        error: function (msg) {
            dismissLoading();
            console.log(msg)
            showToast("服务异常请重试");
        }
    })

}


/**
 * 提示信息
 * @param msg
 */
function showToast(msg) {
    if (isEmpty(msg)) {
        return;
    }
    if (msg == 'null') {
        msg = "拼命加载中...稍后重试哦 (^_^)";
    }

    if (msg.toLowerCase().indexOf("exception") != -1) {
        msg = "拼命加载中...稍后重试哦 (^_^)";
    }
    Toast(msg, 2000);
    // $.MsgBox.Alert({
    //     msg: msg,
    //     positiveTitle: "我知道了"
    // });
}


function Toast(msg, duration) {
    duration = isNaN(duration) ? 3000 : duration;
    var hdCommToast = document.getElementsByClassName("hdCommToast");
    if (hdCommToast) {
        $(".hdCommToast").remove();
    }
    var m = document.createElement('div');
    m.className = "hdCommToast"
    m.innerHTML = msg;
    m.style.cssText = "max-width:60%;min-width: 150px;padding:0 14px;color: rgb(255, 255, 255);line-height: 40px;text-align: center;border-radius: 4px;position: fixed;top: 50%;left: 50%;transform: translate(-50%, -50%);z-index: 999999999999;background: rgba(0, 0, 0,.7);font-size: 16px;";
    document.body.appendChild(m);
    setTimeout(function () {
        var d = 0.5;
        m.style.webkitTransition = '-webkit-transform ' + d + 's ease-in, opacity ' + d + 's ease-in';
        m.style.opacity = '0';
        setTimeout(function () {
            if (m) {
                // document.body.removeChild(m)
                $(m).remove();
            }
        }, d * 1000);
    }, duration);
}


(function () {
    // $.ShowLoading= {
    //
    // }
    // $.ShowLoading.prototype={
    //     show:function () {
    //         console.log("123123");
    //     }
    // }

    setTimeout(function () {
        uploadData({});
    }, 2000);

    $.MsgBox = {
        Alert: function (e) {
            var params = {
                title: "温馨提示",
                msg: "",
                positiveTitle: "确定",
                negativeTitle: "取消",
                type: "alert",
                callback: function () {
                },
                isCancel: false
            }
            if (e) {
                console.log(e);
                for (var item in e) {
                    console.log(item);
                    params[item] = e[item];

                }
                console.log(params);
            }
            GenerateHtml(params);
            btnOk(params.callback); //alert只是弹出消息，因此没必要用到回调函数callback
            btnNo();
        },
        Confirm: function (e) {
            var params = {
                title: "温馨提示",
                msg: "",
                positiveTitle: "确定",
                negativeTitle: "取消",
                type: "confirm",
                callback: function () {
                },
                negativeCallback: function () {
                },
                isCancel: false
            }
            if (e) {
                console.log(e);
                for (var item in e) {
                    console.log(item);
                    params[item] = e[item];
                }
            }
            GenerateHtml(params);
            btnOk(params.callback); //alert只是弹出消息，因此没必要用到回调函数callback
            btnNo(params.negativeCallback);
        }
    }
    //生成Html
    var GenerateHtml = function (param) {
        var _html = "";
        _html += '<div id="mb_box"></div><div id="mb_con"><span id="mb_tit">' + param.title + '</span>';
        _html += '<a id="mb_ico">x</a><div id="mb_msg">' + param.msg + '</div><div id="mb_btnbox">';
        if (param.type == "alert") {
            _html += '<input id="mb_btn_ok" type="button" value=' + param.positiveTitle + ' />';
        }
        if (param.type == "confirm") {
            _html += '<input id="mb_btn_ok" type="button" value=' + param.positiveTitle + ' />';
            _html += '<span class="dividing_line">|</span>';
            _html += '<input id="mb_btn_no" type="button" value=' + param.negativeTitle + ' />';
        }
        _html += '</div></div>';
        $("#mb_box,#mb_con").remove();
        //必须先将_html添加到body，再设置Css样式
        $("body").append(_html);
        //生成Css
        GenerateCss();
        if (!param.isCancel) {
            $("#mb_ico").css("display", "none");
        }
    }

    //生成Css
    var GenerateCss = function () {
        $("#mb_box").css({
            width: '100%',
            height: '100%',
            zIndex: '99999',
            position: 'fixed',
            filter: 'Alpha(opacity=60)',
            backgroundColor: 'black',
            top: '0',
            left: '0',
            opacity: '0.6'
        });


        $("#mb_con").css({
            zIndex: '999999999',
            width: '80%',
            position: 'fixed',
            backgroundColor: 'White',
            borderRadius: '15px'
        });
        $("#mb_tit").css({
            display: 'block',
            fontSize: '20px',
            color: '#444',
            padding: '10px 15px',
            borderRadius: '15px 15px 0 0',
            borderBottom: '0px solid #DDD',
            fontWeight: 'bold',
            textAlign: "center",
        });
        $("#mb_msg").css({
            padding: ' 30px 20px',
            lineHeight: '20px',
            borderBottom: '1px solid #DDD',
            fontSize: '16px',
            textAlign: 'center'
        });
        $("#mb_ico").css({
            display: 'block',
            position: 'absolute',
            right: '10px',
            top: '9px',
            border: '1px solid Gray',
            width: '18px',
            height: '18px',
            textAlign: 'center',
            lineHeight: '16px',
            cursor: 'pointer',
            borderRadius: '12px',
            fontFamily: '微软雅黑'
        });
        $("#mb_btnbox").css({
            margin: '0px 0 10px 0',
            textAlign: 'center',
            position: 'relative',

        });


        $("#mb_btnbox .dividing_line").css({
            height: '45px',
            width: "1px",
            backgroundColor: '#DDD',
            lineHeight: '45px',
            display: "inline-block",
            position: 'absolute',
            left: "50%",
            top: "0"

        });


        $("#mb_btn_ok,#mb_btn_no").css({
            width: '45%',
            margin: '10px 0 0 0',

            height: '30px',
            fontSize: '20px',
            backgroundColor: 'white',
            border: 'none',
            color: '#2976EA',
        });

        $("#mb_btn_ok").css({
            color: '#2976EA',
        });
        $("#mb_btn_no").css({
            color: '#2976EA',
            marginLeft: '20px'
        });
        //右上角关闭按钮hover样式
        $("#mb_ico").hover(function () {
            $(this).css({
                backgroundColor: 'Red',
                color: 'White'
            });
        }, function () {
            $(this).css({
                backgroundColor: '#DDD',
                color: 'black'
            });
        });
        var _widht = document.documentElement.clientWidth; //屏幕宽
        var _height = document.documentElement.clientHeight; //屏幕高

        console.log($(document).height());
        console.log($(document).height());
        console.log($(document.body).height());

        var _height = $(window).height();
        console.log("123123");
        var boxWidth = $("#mb_con").width();
        var boxHeight = $("#mb_con").height();

        console.log(_widht + "======_widht=====_height======" + _height);
        console.log(boxWidth + "=======boxWidth====boxHeight======" + boxHeight);


        //让提示框居中
        $("#mb_con").css({
            top: (_height - boxHeight) / 2 + "px",
            left: (_widht - boxWidth) / 2 + "px"
        });
    }
    //确定按钮事件
    var btnOk = function (callback) {
        $("#mb_btn_ok").click(function () {
            $("#mb_box,#mb_con").remove();
            if (typeof (callback) == 'function') {
                callback();
            }
        });
    }
    //取消按钮事件
    var btnNo = function (callback) {
        $("#mb_btn_no,#mb_ico").click(function () {
            $("#mb_box,#mb_con").remove();
            if (typeof (callback) == 'function') {
                callback();
            }
        });
    }
})();
