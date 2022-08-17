package com.serviceaccount.application;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.serviceaccount.application.ServiceaccountApplication;
import com.serviceaccount.controller.ServiceAccountCotroller;
import com.serviceaccount.model.Accounts;
import com.serviceaccount.model.BalanceUpdate;
import com.serviceaccount.model.CustomerAccountType;
import com.serviceaccount.repo.AccountsRepository;
import com.serviceaccount.resources.AccountTypeEnum;
import com.serviceaccount.service.AccountsService;

import lombok.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ServiceAccountCotrollerTest {
	
	@Autowired
    private MockMvc mockMvc;

    ObjectMapper mapper = new ObjectMapper();
    ObjectWriter writer = mapper.writer();
    
  
	@Test
	public void createUser_test() throws Exception {
		
		String exampleCourseJson ="{"
				+ "    \"customerId\" : 3,"
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
	public void createUser_test_noemail() throws Exception {
		
		String exampleCourseJson ="{"
				+ "    \"customerId\" : 3,"
				+ "    \"customerName\" : \"Jerold Caballero\","
				+ "    \"customerMobile\": \"0918723112\","
				+ "    \"address1\": \"somewhere\","
				+ "    \"address2\": \"over therer\","
				+ "    \"accounType\": \"S\","
				+ "    \"customerEmail\": \"\""
				+ "}";
	
		    	  Accounts record = mapper.readValue(exampleCourseJson, Accounts.class);
				mockMvc.perform(MockMvcRequestBuilders
							.post("/api/v1/accounts").content(asJsonString(record)).accept(MediaType.APPLICATION_JSON)
							.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
	   }
	
	@Test
	public void createUser_test_noAccounttype() throws Exception {
		
		String exampleCourseJson ="{"
				+ "    \"customerName\" : \"Jerold Caballero\","
				+ "    \"customerMobile\": \"0918723112\","
				+ "    \"address1\": \"somewhere\","
				+ "    \"address2\": \"over therer\","
				+ "    \"accounType\": \"\","
				+ "    \"customerEmail\": \"test\""
				+ "}";
	
		    	  Accounts record = mapper.readValue(exampleCourseJson, Accounts.class);
				mockMvc.perform(MockMvcRequestBuilders
							.post("/api/v1/accounts").content(asJsonString(record)).accept(MediaType.APPLICATION_JSON)
							.contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().json("[{\"customerNumber\":null,\"transactionStatusCode\":\"400 BAD_REQUEST\",\"transactionStatusDescription\":\"Account Type is required field Option [S,C]\"}]"));
		
	   }
	
	
	@Test
	public void createUser_testbulk() throws Exception {
		
		String exampleCourseJson ="[\r\n"
				+ "    {\r\n"
				+ "    \"customerName\" : \"Kenn\",\r\n"
				+ "    \"customerMobile\": \"0918723112\",\r\n"
				+ "    \"address1\": \"somewhere\",\r\n"
				+ "    \"address2\": \"over therer\",\r\n"
				+ "    \"accounType\": \"S\",\r\n"
				+ "    \"customerEmail\": \"test@gmail.com\"\r\n"
				+ "    },\r\n"
				+ "    {\r\n"
				+ "    \"customerName\" : \"Meg\",\r\n"
				+ "    \"customerMobile\": \"0918723112\",\r\n"
				+ "    \"address1\": \"Happy\",\r\n"
				+ "    \"address2\": \"Sn carlos\",\r\n"
				+ "    \"accounType\": \"S\",\r\n"
				+ "    \"customerEmail\": \"test@gmail.com\"\r\n"
				+ "   }\r\n"
				+ "]";
	
		ArrayList<Accounts> record = mapper.readValue(exampleCourseJson,ArrayList.class);
			mockMvc.perform(MockMvcRequestBuilders
						.post("/api/v1/accounts/bulk").content(asJsonString(record)).accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());

	   }
	
	
	@Test
	public void createUser_testbulk_accounttypenull() throws Exception {
		
		String exampleCourseJson ="[\r\n"
				+ "    {\r\n"
				+ "    \"customerName\" : \"Kenn\",\r\n"
				+ "    \"customerMobile\": \"0918723112\",\r\n"
				+ "    \"address1\": \"somewhere\",\r\n"
				+ "    \"address2\": \"over therer\",\r\n"
				+ "    \"accounType\": \"S\",\r\n"
				+ "    \"customerEmail\": \"test@gmail.com\"\r\n"
				+ "    },\r\n"
				+ "    {\r\n"
				+ "    \"customerName\" : \"Meg\",\r\n"
				+ "    \"customerMobile\": \"0918723112\",\r\n"
				+ "    \"address1\": \"Happy\",\r\n"
				+ "    \"address2\": \"Sn carlos\",\r\n"
				+ "    \"accounType\": \"\",\r\n"
				+ "    \"customerEmail\": \"test@gmail.com\"\r\n"
				+ "   }\r\n"
				+ "]";
	
		ArrayList<Accounts> record = mapper.readValue(exampleCourseJson,ArrayList.class);
			mockMvc.perform(MockMvcRequestBuilders
						.post("/api/v1/accounts/bulk").content(asJsonString(record)).accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated())
			.andExpect(content().json("[{\"customerNumber\":null,\"transactionStatusCode\":\"400 BAD_REQUEST\",\"transactionStatusDescription\":\"Account Type is required field Option [S,C]\"}]"));;

	   }
	
	@Test
	public void createUser_testbulk_emptyemail() throws Exception {
		
		String exampleCourseJson ="[\r\n"
				+ "    {\r\n"
				+ "    \"customerName\" : \"Kenn\",\r\n"
				+ "    \"customerMobile\": \"0918723112\",\r\n"
				+ "    \"address1\": \"somewhere\",\r\n"
				+ "    \"address2\": \"over therer\",\r\n"
				+ "    \"accounType\": \"S\",\r\n"
				+ "    \"customerEmail\": \"\"\r\n"
				+ "    },\r\n"
				+ "    {\r\n"
				+ "    \"customerName\" : \"Meg\",\r\n"
				+ "    \"customerMobile\": \"0918723112\",\r\n"
				+ "    \"address1\": \"Happy\",\r\n"
				+ "    \"address2\": \"Sn carlos\",\r\n"
				+ "    \"accounType\": \"S\",\r\n"
				+ "    \"customerEmail\": \"test@gmail.com\"\r\n"
				+ "   }\r\n"
				+ "]";
	
		ArrayList<Accounts> record = mapper.readValue(exampleCourseJson,ArrayList.class);
			mockMvc.perform(MockMvcRequestBuilders
						.post("/api/v1/accounts/bulk").content(asJsonString(record)).accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());

	   }
	
	
	@Test
	public void updateUser_balance_notfound() throws Exception {
		

		String exampleBalanaceJson ="{\r\n"
				+ "    \"accounType\" : \"S\",\r\n"
				+ "    \"amount\": \"11100\"\r\n"
				+ "}";
		
		BalanceUpdate recordAccount = mapper.readValue(exampleBalanaceJson,BalanceUpdate.class);
			mockMvc.perform(MockMvcRequestBuilders
						.post("/api/v1/accounts/addbalance/3").param("id", "3").content(asJsonString(recordAccount)).accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON))
			.andExpect(content().json("[{\"customerNumber\":\"3\",\"transactionStatusCode\":\"401\",\"transactionStatusDescription\":\"Customer Account not found\"}]"));;;

	   }
	

	
	public static String asJsonString(final Object obj) throws JsonProcessingException {
	        return new ObjectMapper().writeValueAsString(obj);
	
	}

}
