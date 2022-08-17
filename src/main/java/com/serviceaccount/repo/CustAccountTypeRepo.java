package com.serviceaccount.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.serviceaccount.model.CustomerAccountType;
import com.serviceaccount.resources.AccountTypeEnum;


@Repository
public interface CustAccountTypeRepo extends CrudRepository<CustomerAccountType, Long> {
	CustomerAccountType findByCustomerId(int customerId);
	CustomerAccountType findByCustomerIdAndAccountType(int customerId,AccountTypeEnum accountType);
	CustomerAccountType findByAccountNumber(int customerId);
}
