// var build = {
//     buildList: function () {
//             console.log("dddddddddddddd======buildList==>>>>>>>>>");
//     }
// }

(function () {

    var menuList = [
        // {
        //     name: "正式包检测531",
        //     url: "index531"
        // },
        // {
        //     name: "占坑包检测531",
        //     url: "debug531"
        // },
        {
            name: "安卓正式包检测",
            url: "index"
        }, {
            name: "安卓占坑包检测",
            url: "debug1"
        }, {
            name: "苹果正式包检测",
            url: "ios"
        }, {
            name: "苹果占坑包检测",
            url: "iosDebug"
        }, {
            name: "大json检测",
            url: "nbjson"
        }, {
            name: "appscheme测试",
            url: "appscheme"
        }, {
            name: "json交叉测试",
            url: "JsonNodeComparator"
        }, {
            name: "serverTest",
            url: "serverTest"
        }, {
            name: "更新文档文件",
            url: "./resetDocUpdate"
        }

        // , {
        //     name: "大json检测531",
        //     url: "nbjson531"
        // }
    ]

    function buildList() {
        var autoHtml = "";
        menuList.forEach(function (value, index) {
            autoHtml += "<li><a href=\"/" + value.url + "\">" + value.name + "</a></li>";
        });
        $(".menuList").html(autoHtml);
    }

    buildList();


})();

