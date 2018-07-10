/**
 * 账单管理管理初始化
 */
var Billmanage = {
    id: "BillmanageTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Billmanage.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: '账单编号', field: 'id', visible: true, align: 'center', valign: 'middle'},
        {title: '申请人', field: 'billApplicant', visible: true, align: 'center', valign: 'middle'},
        {title: '申请人电话', field: 'billTel', visible: true, align: 'center', valign: 'middle'},
        {title: '账单类型', field: 'billType', visible: true, align: 'center', valign: 'middle'},
        {title: '账单金额', field: 'billMoney', visible: true, align: 'center', valign: 'middle'},
        {title: '录入人', field: 'billEntry', visible: true, align: 'center', valign: 'middle'},
        {title: '录入时间', field: 'billDate', visible: true, align: 'center', valign: 'middle'},
        {title: '账单状态', field: 'billState', visible: true, align: 'center', valign: 'middle'},
        {title: '备注', field: 'billRemarks', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Billmanage.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Billmanage.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加账单管理
 */
Billmanage.openAddBillmanage = function () {
    var index = layer.open({
        type: 2,
        title: '添加账单管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/billmanage/billmanage_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看账单管理详情
 */
Billmanage.openBillmanageDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '账单管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/billmanage/billmanage_update/' + Billmanage.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除账单管理
 */
Billmanage.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/billmanage/delete", function (data) {
            Feng.success("删除成功!");
            Billmanage.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("billmanageId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询账单管理列表
 */
Billmanage.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Billmanage.table.refresh({query: queryData});
};

$(function () {

    var defaultColunms = Billmanage.initColumn();
    var table = new BSTable(Billmanage.id, "/billmanage/list", defaultColunms);
    table.setPaginationType("client");
    Billmanage.table = table.init();


        $.post("/billmanage/name",{},function (data) {
            $( "#condition" ).autocomplete({
                source: data,
                max: 12,    //列表里的条目数
                minChars: 0,    //自动完成激活之前填入的最小字符
                width: 400,     //提示的宽度，溢出隐藏
                scrollHeight: 300,   //提示的高度，溢出显示滚动条
                matchContains: true,    //包含匹配，就是data参数里的数据，是否只要包含文本框里的数据就显示
                autoFill: false,    //自动填充
                messages: {
                    noResults: '',
                    results: function() {}
                }
            });
        });


});
