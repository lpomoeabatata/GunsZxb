package com.stylefeng.guns.common.persistence.model;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhaoxb
 * @since 2018-06-15
 */
@TableName("calls_log")
public class CallsLog extends Model<CallsLog> {

    private static final long serialVersionUID = 1L;

	@TableId(value="calls_LogId", type= IdType.AUTO)
	private Integer callsLogId;
	@TableField("calls_Log")
	private String callsLog;
	@TableField("calls_Remarks")
	private String callsRemarks;
	@TableField("calls_Num")
	private String callsNum;
	@TableField("calls_Address")
	private String callsAddress;


	public Integer getCallsLogId() {
		return callsLogId;
	}

	public void setCallsLogId(Integer callsLogId) {
		this.callsLogId = callsLogId;
	}

	public String getCallsLog() {
		return callsLog;
	}

	public void setCallsLog(String callsLog) {
		this.callsLog = callsLog;
	}

	public String getCallsRemarks() {
		return callsRemarks;
	}

	public void setCallsRemarks(String callsRemarks) {
		this.callsRemarks = callsRemarks;
	}

	public String getCallsNum() {
		return callsNum;
	}

	public void setCallsNum(String callsNum) {
		this.callsNum = callsNum;
	}

	public String getCallsAddress() {
		return callsAddress;
	}

	public void setCallsAddress(String callsAddress) {
		this.callsAddress = callsAddress;
	}

	@Override
	protected Serializable pkVal() {
		return this.callsLogId;
	}

	@Override
	public String toString() {
		return "CallsLog{" +
			"callsLogId=" + callsLogId +
			", callsLog=" + callsLog +
			", callsRemarks=" + callsRemarks +
			", callsNum=" + callsNum +
			", callsAddress=" + callsAddress +
			"}";
	}
}
