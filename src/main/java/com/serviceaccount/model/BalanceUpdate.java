package com.serviceaccount.model;

import com.serviceaccount.resources.AccountTypeEnum;

public class BalanceUpdate {
	
	private int amount;
	private AccountTypeEnum accounType;
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public AccountTypeEnum getAccounType() {
		return accounType;
	}
	public void setAccounType(AccountTypeEnum accounType) {
		this.accounType = accounType;
	}

}
