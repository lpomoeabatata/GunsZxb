package com.stylefeng.guns.common.persistence.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhaoxb
 * @since 2018-06-20
 */
public class Process extends Model<Process> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
	@TableField("process_Auditor")
	private String processAuditor;
	@TableField("process_Date")
	private Date processDate;
	@TableField("process_Remarks")
	private String processRemarks;
	@TableField("process_Id")
	private Integer processId;
	@TableField("process_State")
	private String processState;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProcessAuditor() {
		return processAuditor;
	}

	public void setProcessAuditor(String processAuditor) {
		this.processAuditor = processAuditor;
	}

	public Date getProcessDate() {
		return processDate;
	}

	public void setProcessDate(Date processDate) {
		this.processDate = processDate;
	}

	public String getProcessRemarks() {
		return processRemarks;
	}

	public void setProcessRemarks(String processRemarks) {
		this.processRemarks = processRemarks;
	}

	public Integer getProcessId() {
		return processId;
	}

	public void setProcessId(Integer processId) {
		this.processId = processId;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Process{" +
			"id=" + id +
			", processAuditor=" + processAuditor +
			", processDate=" + processDate +
			", processRemarks=" + processRemarks +
			", processId=" + processId +
			", processState=" + processState +
			"}";
	}

	public String getProcessState() {
		return processState;
	}

	public void setProcessState(String processState) {
		this.processState = processState;
	}
}
