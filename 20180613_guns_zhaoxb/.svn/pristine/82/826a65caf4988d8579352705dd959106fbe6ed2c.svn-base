package com.stylefeng.guns.common.persistence.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
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
 * @since 2018-06-19
 */
@TableName("bill_zhaoxb")
public class BillZhaoxb extends Model<BillZhaoxb>  implements Serializable{

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
	@TableField("bill_Applicant")
	private String billApplicant;
	@TableField("bill_Tel")
	private String billTel;
	@TableField("bill_Type")
	private String billType;
	@TableField("bill_Money")
	private Integer billMoney;
	@TableField("bill_Entry")
	private String billEntry;
	@TableField("bill_Date")
	private Date billDate;
	@TableField("bill_State")
	private String billState;
	@TableField("bill_Auditor")
	private  String billAuditor;
	@TableField("bill_Remarks")
	private  String billRemarks;
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBillApplicant() {
		return billApplicant;
	}

	public void setBillApplicant(String billApplicant) {
		this.billApplicant = billApplicant;
	}

	public String getBillTel() {
		return billTel;
	}

	public void setBillTel(String billTel) {
		this.billTel = billTel;
	}

	public String getBillType() {
		return billType;
	}

	public void setBillType(String billType) {
		this.billType = billType;
	}

	public Integer getBillMoney() {
		return billMoney;
	}

	public void setBillMoney(Integer billMoney) {
		this.billMoney = billMoney;
	}

	public String getBillEntry() {
		return billEntry;
	}

	public void setBillEntry(String billEntry) {
		this.billEntry = billEntry;
	}

	public Date getBillDate() {
		return billDate;
	}

	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}

	public String getBillState() {
		return billState;
	}

	public void setBillState(String billState) {
		this.billState = billState;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "BillZhaoxb{" +
			"id=" + id +
			", billApplicant=" + billApplicant +
			", billTel=" + billTel +
			", billType=" + billType +
			", billMoney=" + billMoney +
			", billEntry=" + billEntry +
			", billDate=" + billDate +
			", billState=" + billState +
			", billAuditor=" + billAuditor +
			", billRemarks=" + billRemarks +
			"}";
	}

	public String getBillAuditor() {
		return billAuditor;
	}

	public void setBillAuditor(String billAuditor) {
		this.billAuditor = billAuditor;
	}

	public String getBillRemarks() {
		return billRemarks;
	}

	public void setBillRemarks(String billRemarks) {
		this.billRemarks = billRemarks;
	}
}
