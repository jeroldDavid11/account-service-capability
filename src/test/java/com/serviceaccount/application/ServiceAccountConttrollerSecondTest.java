package com.serviceaccount.application;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.serviceaccount.model.Accounts;
import com.serviceaccount.model.BalanceUpdate;
import com.serviceaccount.model.CustomerAccountType;
import com.serviceaccount.service.AccountsService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class ServiceAccountConttrollerSecondTest {


	@Autowired
    private MockMvc mockMvc;
	
	@MockBean
	AccountsService accountsService;
	
    ObjectMapper mapper = new ObjectMapper();
    ObjectWriter writer = mapper.writer();
   
	@Test
	public void createUser_test() throws Exception {
		
		String exampleCourseJson ="{"
				+ "    \"customerId\" : 1,"
				+ "    \"customerName\" : \"Jerold Caballero\","
				+ "    \"customerMobile\": \"0918723112\","
				+ "    \"address1\": \"somewhere\","
				+ "    \"address2\": \"over therer\","
				+ "    \"accounType\": \"S\","
				+ "    \"customerEmail\": \"test\""
				+ "}";
	
		    Accounts record = mapper.readValue(exampleCourseJson, Accounts.class);
			mockMvc.perform(MockMvcRequestBuilders
						.post("/api/v1/accounts").content(asJsonString(record)).accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());

	   }
	@Test
	public void updateUser_balance() throws Exception {
		String exampleCourseJson ="{"
				+ "    \"customerId\" : 1,"
				+ "    \"customerName\" : \"Jerold Caballero\","
				+ "    \"customerMobile\": \"0918723112\","
				+ "    \"address1\": \"somewhere\","
				+ "    \"address2\": \"over therer\","
				+ "    \"accounType\": \"S\","
				+ "    \"customerEmail\": \"test\""
				+ "}";
	
		Accounts record = mapper.readValue(exampleCourseJson, Accounts.class);
	
		doReturn(record).when(accountsService).createCustomer(record);


		String exampleBalanaceJson ="{\r\n"
				+ "    \"accounType\" : \"S\",\r\n"
				+ "    \"amount\": \"11100\"\r\n"
				+ "}";
		
		BalanceUpdate recordAccount = mapper.readValue(exampleBalanaceJson,BalanceUpdate.class);

			mockMvc.perform(MockMvcRequestBuilders
						.post("/api/v1/accounts/addbalance/1").param("id", "1").content(asJsonString(recordAccount)).accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON)).andExpect(status().is2xxSuccessful());

	   }
	
	
	@Test
	public void getUser_Balance_not_found() throws Exception {
		String exampleCourseJson ="{"
				+ "    \"customerId\" : 1,"
				+ "    \"customerName\" : \"Jerold Caballero\","
				+ "    \"customerMobile\": \"0918723112\","
				+ "    \"address1\": \"somewhere\","
				+ "    \"address2\": \"over therer\","
				+ "    \"accounType\": \"S\","
				+ "    \"customerEmail\": \"test\""
				+ "}";
	
		Accounts record = mapper.readValue(exampleCourseJson, Accounts.class);
		
		doReturn(record).when(accountsService).createCustomer(record);
	
		String exampleBalanaceJson ="{\r\n"
				+ "    \"accountNumber\" : \"10001\"\r\n"
				+ "}";
		
		CustomerAccountType recordAccount = mapper.readValue(exampleBalanaceJson,CustomerAccountType.class);

			mockMvc.perform(MockMvcRequestBuilders
						.get("/api/v1/accounts/balance").content(asJsonString(recordAccount)).accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON)).andExpect(status().is4xxClientError());

	   }
	
	
	
	public static String asJsonString(final Object obj) throws JsonProcessingException {
	        return new ObjectMapper().writeValueAsString(obj);
	
	}

}
