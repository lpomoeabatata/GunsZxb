/**
 * 初始化账单管理详情对话框
 */
var BillmanageInfoDlg = {
    billmanageInfoData : {}
};

/**
 * 清除数据
 */
BillmanageInfoDlg.clearData = function() {
    this.billmanageInfoData = {};
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BillmanageInfoDlg.set = function(key, val) {
    this.billmanageInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BillmanageInfoDlg.get = function(key) {
    return $("#" + key).val();
};

/**
 * 关闭此对话框
 */
BillmanageInfoDlg.close = function() {
    parent.layer.close(window.parent.Billmanage.layerIndex);
};

/**
 * 收集数据
 */
BillmanageInfoDlg.collectData = function() {
    this.set('id').set('billApplicant').set('billTel').set('billType').set('billMoney').set('billEntry').set('billDate').set('billState').set('billAuditor').set('billRemarks');
};

/**
 * 提交添加
 */
BillmanageInfoDlg.addSubmit = function() {
    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/billmanage/add", function(data){
        Feng.success("添加成功!");
        window.parent.Billmanage.table.refresh();
        BillmanageInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.billmanageInfoData);
    ajax.start();
};

/**
 * 提交修改
 */
BillmanageInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();
    document.getElementById("billState").options.remove(0);
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/billmanage/update", function(data){
        Feng.success("审核完成!");
        window.parent.Billmanage.table.refresh();
        BillmanageInfoDlg.close();
    },function(data){
        Feng.error("出现问题!" + data.responseJSON.message + "!");
    });
    ajax.set(this.billmanageInfoData);
    ajax.start();
};

$(function() {
    var State=document.getElementById("billState1").value;
    var Auditor = document.getElementById("billAuditor").value;
    var Applicant= document.getElementById("billApplicant").value;
    var User=document.getElementById("billUser").value;
    if (State==="未审核"&&Auditor==="超级管理员") {
        var div1 = document.getElementById("shenhe");
        var button1=document.getElementById("submit");
        button1.setAttribute("style", "display:none");
        div1.setAttribute("style", "display:none");
    } else if (State==="未审核"&&Auditor==="临时"){
        var div1 = document.getElementById("shenhe");
        var button1=document.getElementById("submit");
        button1.setAttribute("style", "display:none");
        div1.setAttribute("style", "display:none");
    }else if (State==="超级管理员审核通过"){
        var div1 = document.getElementById("shenhe");
        var button1=document.getElementById("submit");
        button1.setAttribute("style", "display:none");
        div1.setAttribute("style", "display:none");
    }else if (State==="超级管理员驳回"&&Auditor==="超级管理员"){
        var div1 = document.getElementById("shenhe");
        var button1=document.getElementById("submit");
        button1.setAttribute("style", "display:none");
        div1.setAttribute("style", "display:none");
    }else if(State==="管理员审核通过"&&Auditor==="管理员"){
        var div1 = document.getElementById("shenhe");
        var button1=document.getElementById("submit");
        button1.setAttribute("style", "display:none");
        div1.setAttribute("style", "display:none");
    }else if(State==="管理员驳回"&&Applicant!==User){
        var div1 = document.getElementById("shenhe");
        var button1=document.getElementById("submit");
        button1.setAttribute("style", "display:none");
        div1.setAttribute("style", "display:none");
    }else if(State==="管理员驳回"&&Applicant===User) {
        var div1 = document.getElementById("shenhe");
        div1.setAttribute("style", "display:none");
    }
});
