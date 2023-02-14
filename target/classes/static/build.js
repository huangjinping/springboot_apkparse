// var build = {
//     buildList: function () {
//             console.log("dddddddddddddd======buildList==>>>>>>>>>");
//     }
// }

(function () {

    var menuList = [
        {
            name: "正式包检测",
            url: "index"
        }, {
            name: "占坑包检测",
            url: "debug1"
        }, {
            name: "大json检测",
            url: "nbjson"
        }
    ]

    function buildList() {
        var autoHtml = "";
        menuList.forEach(function (value, index){
            autoHtml +="<li><a href=\"/"+value.url+"\">"+value.name+"</a></li>";
        });
        $(".menuList").html(autoHtml);

    }

    buildList();
})();

