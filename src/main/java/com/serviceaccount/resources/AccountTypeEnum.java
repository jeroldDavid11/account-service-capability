package com.serviceaccount.resources;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public enum AccountTypeEnum {
	SAVINGS("S"), CHECKING("C");
	private String accountType;
	private AccountTypeEnum(String accountType) {
		this.accountType = accountType;
	}
	
	 public String getDepCode() {
	        return this.accountType;
	    }
	 
	 
	 @JsonCreator
	    public static AccountTypeEnum getAccounTypeFromCode(String value) {
	        for (AccountTypeEnum dep : AccountTypeEnum.values()) {
	            if (dep.getDepCode().equals(value)) {
	                return dep;
	            }
	        }
	        return null;
	    }
	 
}
