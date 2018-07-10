/**
 * 投票管理初始化
 */
var Vote = {
    id: "VoteTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Vote.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Vote.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Vote.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加投票
 */
Vote.openAddVote = function () {
    var index = layer.open({
        type: 2,
        title: '添加投票',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/vote/vote_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看投票详情
 */
Vote.openVoteDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '投票详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/vote/vote_update/' + Vote.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除投票
 */
Vote.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/vote/delete", function (data) {
            Feng.success("删除成功!");
            Vote.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("voteId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询投票列表
 */
Vote.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Vote.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Vote.initColumn();
    var table = new BSTable(Vote.id, "/vote/list", defaultColunms);
    table.setPaginationType("client");
    Vote.table = table.init();
});



//在每个投票选项后面写了个div，用div的宽度来代表当前该选项的投票数。
function vote(){    //函数vote，当点击确认投票的时候，调用vote方法
    //for循环的条件是，所有投票选项的个数。
    for(var i = 0; i < document.getElementsByName("option").length; i++){
        //查找到是哪个投票选项被选中
        if(document.getElementsByName("option")[i].checked === true){
            var width = document.getElementById(i).style.width; //获取到当前选项的宽度。
            width = parseInt(width);//将宽度转化为int型，因为获取到的width的单位是px
            width += 15;//改变width的值，这里就是定义每次投票的进度条的增速
            document.getElementById(i).style.width = width+"px";//修改原div的宽度

            var label = "label"+i;//lable标签里面写的是当前的投票数目。
            var num = document.getElementById(label).innerText;//获取到当前的票数
            document.getElementById(label).innerText = ++num;//票数加1，并修改原值
        }
    }


    //alert("投票成功");

}





;
