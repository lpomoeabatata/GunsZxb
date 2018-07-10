/**
 * 初始化来电日志详情对话框
 */
var CallsLogInfoDlg = {
    callsLogInfoData : {}
};

/**
 * 清除数据
 */
CallsLogInfoDlg.clearData = function() {
    this.callsLogInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CallsLogInfoDlg.set = function(key, val) {
    this.callsLogInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CallsLogInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
CallsLogInfoDlg.close = function() {
    parent.layer.close(window.parent.CallsLog.layerIndex);
}

/**
 * 收集数据
 */
CallsLogInfoDlg.collectData = function() {
    this.set('callsLogId').set('callsLog').set('callsRemarks').set('callsNum').set('callsAddress');
}

/**
 * 提交添加
 */
CallsLogInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/callsLog/add", function(data){
        Feng.success("添加成功!");
        window.parent.CallsLog.table.refresh();
        CallsLogInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });

    ajax.set(this.callsLogInfoData);
    ajax.start();
};

/**
 * 提交修改
 */
CallsLogInfoDlg.editSubmit = function() {
    this.clearData();
    this.collectData();
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/callsLog/update", function(data){
        Feng.success("修改成功!");
        window.parent.CallsLog.table.refresh();
        CallsLogInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.callsLogInfoData);
    ajax.start();
};
