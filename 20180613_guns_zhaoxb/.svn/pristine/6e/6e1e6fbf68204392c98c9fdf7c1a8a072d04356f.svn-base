/**
 * 文件上传管理初始化
 */
var FileImport = {
    id: "FileImportTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
FileImport.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
FileImport.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        FileImport.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加文件上传
 */
FileImport.openAddFileImport = function () {
    var index = layer.open({
        type: 2,
        title: '添加文件上传',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/fileImport/FileImport_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看文件上传详情
 */
FileImport.openFileImportDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '文件上传详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/FileImport/FileImport_update/' + FileImport.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除文件上传
 */
FileImport.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/FileImport/delete", function (data) {
            Feng.success("删除成功!");
            FileImport.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("FileImportId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询文件上传列表
 */
FileImport.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    FileImport.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = FileImport.initColumn();
    var table = new BSTable(FileImport.id, "/FileImport/list", defaultColunms);
    table.setPaginationType("client");
    FileImport.table = table.init();
});
