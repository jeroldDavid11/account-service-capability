package com.serviceaccount.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.serviceaccount.model.Accounts;
import com.serviceaccount.model.CustomerAccountType;

@Repository
public interface AccountsRepository extends CrudRepository<Accounts, Long> {
	Accounts findByCustomerId(int customerId);
}
