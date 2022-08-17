package com.serviceaccount.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.serviceaccount.model.Accounts;
import com.serviceaccount.model.BalanceUpdate;
import com.serviceaccount.model.CustomerAccountType;
import com.serviceaccount.repo.AccountsRepository;
import com.serviceaccount.repo.CustAccountTypeRepo;
import com.serviceaccount.resources.ConstantVaraibles;

@Service
public class AccountsService {
     @Autowired
      AccountsRepository accountsRepository;  
     
     @Autowired
      CustAccountTypeRepo custAccountTypeRepo;
     
     //Customer Account Services 
     public Accounts createCustomer(Accounts acc) throws Exception {
    	 
    	   if(acc.getCustomerEmail() == null || acc.getCustomerEmail().isEmpty()){
    	        throw new RuntimeException(ConstantVaraibles.ExceptionMessageEmail);
    	    }
    	   
    	   if(acc.getAccounType() == null || acc.getAccounType().equals("")){
   	            throw new RuntimeException(ConstantVaraibles.ExceptionMessageAccountType);
   	       }
    	    return accountsRepository.save(acc);
     }
     
     public ArrayList<Accounts> createCustomerBulk(ArrayList<Accounts> acc) {
    	 
    	 acc.forEach((n) -> {
    		 if(n.getCustomerEmail() == null || n.getCustomerEmail().isEmpty()){
     	        throw new RuntimeException(ConstantVaraibles.ExceptionMessageEmail);
     	      }
    		 
    		 if(n.getAccounType() == null || n.getAccounType().equals("")){
    	            throw new RuntimeException(ConstantVaraibles.ExceptionMessageAccountType);
    	       }
    		
    	    });
    	 
 	    return (ArrayList<Accounts>) accountsRepository.saveAll(acc);
     }
     
     public Accounts getCustomerDetails(int id) {
 	    return accountsRepository.findByCustomerId(id);
 	 }
     
     //Adding Balance Services 
     public void addBalance(int id,BalanceUpdate balanceUpdate){
    	 CustomerAccountType custacctin = custAccountTypeRepo.findByCustomerIdAndAccountType(id,balanceUpdate.getAccounType());
    	 custacctin.setAvailableBalance(custacctin.getAvailableBalance()+ balanceUpdate.getAmount());
           custAccountTypeRepo.save(custacctin);
     }
     
     //Customer Account Type Services
     public CustomerAccountType getCustomerAccountType(int id) {
		return custAccountTypeRepo.findByCustomerId(id);
     }
     
     public CustomerAccountType getAccountIdBalance(CustomerAccountType custacct) {
 		return custAccountTypeRepo.findByAccountNumber(custacct.getAccountNumber());
      }
     
     public void createInitailCustomerAccountType(Accounts acc) {
    	 CustomerAccountType custacc = new CustomerAccountType();
    	 custacc.setAvailableBalance(0);
    	 custacc.setAccountType(acc.getAccounType());
    	 custacc.setCustomerId((int) acc.getCustomerId());
    	 custAccountTypeRepo.save(custacc);
 	}
}
