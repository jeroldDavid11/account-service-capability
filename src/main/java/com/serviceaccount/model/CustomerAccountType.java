package com.serviceaccount.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.serviceaccount.resources.AccountTypeEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name="table_customer_accounts_types")
@Entity
public class CustomerAccountType {
	
	@TableGenerator(name = "AccountNumber_Gen", table = "ID_GEN", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VAL", pkColumnValue = "AccountNumber_Gen", initialValue = 10000, allocationSize = 100000)
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "AccountNumber_Gen")
	private int accountNumber; 
	private AccountTypeEnum accountType;
	private int customerId;
	private double availableBalance;

	public int getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	public AccountTypeEnum getAccountType() {
		return accountType;
	}
	public void setAccountType(AccountTypeEnum accountType) {
		this.accountType = accountType;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public double getAvailableBalance() {
		return availableBalance;
	}
	public void setAvailableBalance(double availableBalance) {
		this.availableBalance = availableBalance;
	}

}
