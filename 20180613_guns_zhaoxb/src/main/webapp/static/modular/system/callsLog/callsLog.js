/**
 * 来电日志管理初始化
 */
var CallsLog = {
    id: "CallsLogTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
CallsLog.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: '日志编号', field: 'callsLogId', visible: true, align: 'center', valign: 'middle'},
        {title: '来电号码', field: 'callsNum', visible: true, align: 'center', valign: 'middle'},
        {title: '来电日志', field: 'callsLog', visible: true, align: 'center', valign: 'middle'},
        {title: '来电备注', field: 'callsRemarks', visible: true, align: 'center', valign: 'middle'},
        {title: '来电地址', field: 'callsAddress', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
CallsLog.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        CallsLog.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加来电日志
 */
CallsLog.openAddCallsLog = function () {
    var index = layer.open({
        type: 2,
        title: '添加来电日志',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/callsLog/callsLog_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看来电日志详情
 */
CallsLog.openCallsLogDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '来电日志详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/callsLog/callsLog_update/' + CallsLog.seItem.callsLogId
        });
        this.layerIndex = index;
}
};

/**
 * 删除来电日志
 */
CallsLog.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/callsLog/delete", function (data) {
            Feng.success("删除成功!");
            CallsLog.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("callsLogId",this.seItem.callsLogId);
        ajax.start();
    }
};

/**
 * 查询来电日志列表
 */
CallsLog.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    CallsLog.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = CallsLog.initColumn();
    var table = new BSTable(CallsLog.id, "/callsLog/list", defaultColunms);
    table.setPaginationType("client");
    CallsLog.table = table.init();
});
