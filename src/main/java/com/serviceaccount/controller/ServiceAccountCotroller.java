package com.serviceaccount.controller;

import java.util.ArrayList;
import java.util.List;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.serviceaccount.application.ServiceaccountApplication;
import com.serviceaccount.model.Accounts;
import com.serviceaccount.model.BalanceUpdate;
import com.serviceaccount.model.CustomerAccountType;
import com.serviceaccount.model.ResponsePayload;
import com.serviceaccount.resources.ConstantVaraibles;
import com.serviceaccount.service.AccountsService;

@RestController
@RequestMapping("/api/v1")
public class ServiceAccountCotroller {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ServiceaccountApplication.class);

	 @Autowired
	 AccountsService accountsService; 
	
	 /* Creation Services*/
	       @RequestMapping(value="/accounts", method=RequestMethod.POST)
	        public ResponseEntity<Object> createUser(@RequestBody Accounts accounts) {
	       
	    	   List<ResponsePayload>  entities = new ArrayList<ResponsePayload>();
       		
	        try {
	        	        accountsService.createCustomer(accounts);
			            ResponsePayload entity = new ResponsePayload();
			            entity.setCustomerNumber(String.valueOf(accounts.getCustomerId()));
			            entity.setTransactionStatusCode(HttpStatus.CREATED.toString());
			            entity.setTransactionStatusDescription(ConstantVaraibles.CreatedCustomerAccount);
			            entities.add(entity);
			            accountsService.createInitailCustomerAccountType(accounts);
			            LOGGER.info("Success Customer Creation for service accounts {} details ({})", HttpStatus.CREATED.toString(),ConstantVaraibles.CreatedCustomerAccount);
	        }catch(RuntimeException exc) {
				 if(exc.getMessage().equalsIgnoreCase(ConstantVaraibles.ExceptionMessageEmail)) {
						ResponsePayload entity = new ResponsePayload();
			            entity.setTransactionStatusCode(HttpStatus.BAD_REQUEST.toString());
			            entity.setTransactionStatusDescription(ConstantVaraibles.ExceptionEmailRequiredField);
			            entities.add(entity);
			            LOGGER.error("Failed Creation {} reason {}", HttpStatus.BAD_REQUEST.toString(),ConstantVaraibles.ExceptionEmailRequiredField);
					}else if(exc.getMessage().equalsIgnoreCase(ConstantVaraibles.ExceptionMessageAccountType)){
						ResponsePayload entity = new ResponsePayload();
			            entity.setTransactionStatusCode(HttpStatus.BAD_REQUEST.toString());
			            entity.setTransactionStatusDescription(ConstantVaraibles.ExceptionOptionAccountType);
			            entities.add(entity);
			            LOGGER.error("Failed Creation {} reason {}", HttpStatus.BAD_REQUEST.toString(),ConstantVaraibles.ExceptionOptionAccountType);
					}
				
             } catch (Exception e) {
					ResponsePayload entity = new ResponsePayload();
		            entity.setTransactionStatusCode(HttpStatus.BAD_REQUEST.toString());
		            entity.setTransactionStatusDescription(e.getMessage());
		            entities.add(entity);
		            LOGGER.error("Failed Creation {} reason {}", HttpStatus.BAD_REQUEST.toString(),e.getMessage());
			 }
	           return new ResponseEntity<Object>(entities, HttpStatus.CREATED);
				 
	         }
	       
	        @RequestMapping(value="/accounts/bulk", method=RequestMethod.POST)
	        public ResponseEntity<Object> createUserBulk(@RequestBody ArrayList<Accounts> accounts){
	        	List<ResponsePayload> entities = new ArrayList<ResponsePayload>();
	        	if(accounts != null && !accounts.isEmpty()) {
	        		try {
						accountsService.createCustomerBulk(accounts);
						
						//Group Account Number Generated
						StringBuilder builder = new StringBuilder();
						accounts.forEach((n) -> {
							builder.append(","+n.getCustomerId());
							accountsService.createInitailCustomerAccountType(n);
						});
						builder.deleteCharAt(0);
						
						ResponsePayload entity = new ResponsePayload();
						
						entity.setCustomerNumber(builder.toString());
			            entity.setTransactionStatusCode(HttpStatus.CREATED.toString());
			            entity.setTransactionStatusDescription("Bulk Uploaded Done "+ accounts.size());
			            entities.add(entity);
			            LOGGER.info("Payload Success Creation {} contains {}", HttpStatus.CREATED.toString(),"Bulk Uploaded Done "+ accounts.size());
					}catch(RuntimeException exc) {
						 if(exc.getMessage().equalsIgnoreCase(ConstantVaraibles.ExceptionMessageEmail)) {
								ResponsePayload entity = new ResponsePayload();
					            entity.setTransactionStatusCode(HttpStatus.BAD_REQUEST.toString());
					            entity.setTransactionStatusDescription(ConstantVaraibles.ExceptionEmailRequiredField);
					            entities.add(entity);
					            LOGGER.error("Failed Creation {} reason {}", HttpStatus.BAD_REQUEST.toString(),ConstantVaraibles.ExceptionEmailRequiredField);
							}else if(exc.getMessage().equalsIgnoreCase(ConstantVaraibles.ExceptionMessageAccountType)){
								ResponsePayload entity = new ResponsePayload();
					            entity.setTransactionStatusCode(HttpStatus.BAD_REQUEST.toString());
					            entity.setTransactionStatusDescription(ConstantVaraibles.ExceptionOptionAccountType);
					            entities.add(entity);
					            LOGGER.error("Failed Creation {} reason {}", HttpStatus.BAD_REQUEST.toString(),ConstantVaraibles.ExceptionOptionAccountType);
							}
		             } catch (Exception e) {
							ResponsePayload entity = new ResponsePayload();
				            entity.setTransactionStatusCode(HttpStatus.BAD_REQUEST.toString());
				            entity.setTransactionStatusDescription(e.getMessage());
				            entities.add(entity);
				            LOGGER.error("Failed Creation {} reason {}", HttpStatus.BAD_REQUEST.toString(),e.getMessage());
					}
	        	}else {
	        		ResponsePayload entity = new ResponsePayload();
		            entity.setTransactionStatusCode(HttpStatus.BAD_GATEWAY.toString());
		            entity.setTransactionStatusDescription("Empty Bulk Request ");
		            entities.add(entity);
		            LOGGER.error("Failed Creation {} reason {}", HttpStatus.BAD_GATEWAY.toString(),"Empty Bulk Request ");
	        	}
				return new ResponseEntity<Object>(entities, HttpStatus.CREATED);
	        }
	     
	 /* Get Services*/    
	        @RequestMapping(value="/accounts/{id}", method=RequestMethod.GET)
		    public ResponseEntity<Object> getCustAccountType(@PathVariable("id") int id) {
		    	 Accounts b;
		    	 CustomerAccountType a;
		    	 JSONObject json;
	    	     List<ResponsePayload> entities = new ArrayList<ResponsePayload>();
			  try {
				a = accountsService.getCustomerAccountType(id);
				b = accountsService.getCustomerDetails(id);
				 
					 json = new JSONObject();
		    	     json.put("CustomerName", b.getCustomerName());
		    	     json.put("CustomerMobile", b.getCustomerMobile());
		    	     json.put("CustomerEmail", b.getCustomerEmail());
		    	     json.put("Address1", b.getAddress1());
		    	     json.put("Address2", b.getAddress2());
		    	    
		    	   
		    	     JSONArray subReportArr = new JSONArray();
		    	     JSONObject subReport1 = new JSONObject();    
		    	     subReport1.put("AccountNumber", a.getAccountNumber());
		    	     subReport1.put("AccountType", a.getAccountType());
		    	     subReport1.put("availableBalance", a.getAvailableBalance());
		    	     subReportArr.add(0, subReport1);
		    	     json.put(b.getAccounType(), subReportArr);
		    	     json.put("transactionStatusCode", ConstantVaraibles.FoundCustomerAccountCode);
		    	     json.put("transactionStatusDescription",ConstantVaraibles.FoundCustomerAccount);
		    	     
		    	     LOGGER.info("Payload Success Creation {} contains {}", ConstantVaraibles.FoundCustomerAccountCode,ConstantVaraibles.FoundCustomerAccount);
				
			 } catch (Exception e) {
					ResponsePayload entity = new ResponsePayload();
		            entity.setTransactionStatusCode(ConstantVaraibles.NotFoundCustomerAccountCode);
		            entity.setTransactionStatusDescription(ConstantVaraibles.NotFoundCustomerAccount);
		            entities.add(entity);
		            LOGGER.error("Failed Creation {} reason {}", ConstantVaraibles.NotFoundCustomerAccountCode,ConstantVaraibles.NotFoundCustomerAccount);
		            return new ResponseEntity<Object>(entities, HttpStatus.BAD_REQUEST);
			    }
	  
			     return new ResponseEntity<Object>(json.toString(), HttpStatus.ACCEPTED);

	         }

	        @RequestMapping(value="/accounts/balance", method=RequestMethod.GET)
		    public ResponseEntity<Object> getCustAccountBalance(@RequestBody CustomerAccountType custacct) {
		    	 CustomerAccountType a;
		    	 JSONObject json;
		    	 List<ResponsePayload> entities = new ArrayList<ResponsePayload>();
			  try {
				a = accountsService.getAccountIdBalance(custacct);
				 
				 json = new JSONObject();
	   
	    	     JSONArray subReportArr = new JSONArray();
	    	     JSONObject subReport1 = new JSONObject();    
	    	     subReport1.put("AccountNumber", a.getAccountNumber());
	    	     subReport1.put("AccountType", a.getAccountType());
	    	     subReport1.put("AvailableBalance", a.getAvailableBalance());
	    	     subReportArr.add(0, subReport1);
	    	     json.put(a.getAccountType(), subReportArr);
	    	     json.put("transactionStatusCode", ConstantVaraibles.FoundCustomerAccountCode);
	    	     json.put("transactionStatusDescription",ConstantVaraibles.FoundCustomerAccount);
	    	     LOGGER.info("Payload Success Creation {} contains {}", ConstantVaraibles.FoundCustomerAccountCode,ConstantVaraibles.FoundCustomerAccount);
			} catch (Exception e) {
				
				ResponsePayload entity = new ResponsePayload();
	            entity.setTransactionStatusCode(ConstantVaraibles.NotFoundCustomerAccountCode);
	            entity.setTransactionStatusDescription(ConstantVaraibles.NoCustomerAccountType);
	            entities.add(entity);
	            LOGGER.error("Failed Creation {} reason {}", ConstantVaraibles.NotFoundCustomerAccountCode,ConstantVaraibles.NoCustomerAccountType);
	            return new ResponseEntity<Object>(entities, HttpStatus.BAD_REQUEST);
			}
			 return new ResponseEntity<Object>(json.toString(), HttpStatus.ACCEPTED);
	      } 
	
	 /* Updates Services*/          
	        @RequestMapping(value="/accounts/addbalance/{id}", method=RequestMethod.POST)
		    public ResponseEntity<Object> updateBalance(@RequestBody BalanceUpdate balanceUpdate,@PathVariable("id") int id) {
	    	 List<ResponsePayload> entities = new ArrayList<ResponsePayload>();

	    	  try {
				  accountsService.addBalance(id, balanceUpdate);
				  ResponsePayload entity = new ResponsePayload();
				    entity.setCustomerNumber(String.valueOf(id));
		            entity.setTransactionStatusCode(HttpStatus.ACCEPTED.toString());
		            entity.setTransactionStatusDescription("Balance Addedd into "+ balanceUpdate.getAccounType() + " the ammount " + balanceUpdate.getAmount());
		            entities.add(entity);
		            LOGGER.info("Payload Success Creation {} contains {}", HttpStatus.ACCEPTED.toString(),"Balance Addedd into "+ balanceUpdate.getAccounType() + " the ammount " + balanceUpdate.getAmount());
				} catch (Exception e) {
					ResponsePayload entity = new ResponsePayload();
					entity.setCustomerNumber(String.valueOf(id));
		            entity.setTransactionStatusCode(ConstantVaraibles.NotFoundCustomerAccountCode);
		            entity.setTransactionStatusDescription(ConstantVaraibles.NotFoundCustomerAccount);
		            entities.add(entity);
		            LOGGER.error("Failed Creation {} reason {}", ConstantVaraibles.NotFoundCustomerAccountCode,ConstantVaraibles.NotFoundCustomerAccount);
		            return new ResponseEntity<Object>(entities, HttpStatus.BAD_REQUEST);
				}	
	    	        return new ResponseEntity<Object>(entities, HttpStatus.ACCEPTED);
	           }
	     

}
