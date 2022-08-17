package com.serviceaccount.model;

import javax.persistence.*;

import com.serviceaccount.resources.AccountTypeEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name="table_accounts")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Accounts {
	
	@TableGenerator(name = "CustomerNumber_Gen", table = "ID_GEN", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VAL", pkColumnValue = "CustomerNumber_Gen", initialValue = 0, allocationSize = 100000)
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "CustomerNumber_Gen")
	private int customerId;
	
	private String customerName;
	
	private String customerMobile;
	
	@Column(nullable = false) 
	private String customerEmail;
	 
	private String address1;
	
	private String address2;
	
	@Column(nullable = false) 
	private AccountTypeEnum accounType;
	

	@Override
	public String toString() {
		return "Accounts [customerId=" + customerId + ", customerName=" + customerName + ", customerMobile="
				+ customerMobile + ", customerEmail=" + customerEmail + ", address1=" + address1 + ", address2="
				+ address2 + ", accounType=" + accounType + ", getCustomerName()=" + getCustomerName()
				+ ", getCustomerMobile()=" + getCustomerMobile() + ", getCustomerEmail()=" + getCustomerEmail()
				+ ", getAddress1()=" + getAddress1() + ", getAddress2()=" + getAddress2() + ", getAccounType()="
				+ getAccounType() + ", getCustomerId()=" + getCustomerId() + "]";
	}



	public int getCustomerId() {
		return customerId;
	}



	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}



	public String getCustomerName() {
		return customerName;
	}



	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}



	public String getCustomerMobile() {
		return customerMobile;
	}



	public void setCustomerMobile(String customerMobile) {
		this.customerMobile = customerMobile;
	}



	public String getCustomerEmail() {
		return customerEmail;
	}



	public void setCustomerEmail(String customerEmail) {
		if(customerEmail.equalsIgnoreCase("")){
			this.customerEmail = null;
		}else{
			this.customerEmail = customerEmail;
		}
	}

	public String getAddress1() {
		return address1;
	}



	public void setAddress1(String address1) {
		this.address1 = address1;
	}



	public String getAddress2() {
		return address2;
	}



	public void setAddress2(String address2) {
		this.address2 = address2;
	}



	public AccountTypeEnum getAccounType() {
		return accounType;
	}



	public void setAccounType(AccountTypeEnum accounType) {
		this.accounType = accounType;
	}



}
