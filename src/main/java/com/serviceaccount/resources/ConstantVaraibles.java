package com.serviceaccount.resources;

public class ConstantVaraibles {
	
	//Exception Message
	public static final String ExceptionMessageEmail = "com.serviceaccount.model.Accounts.customerEmail";
	public static final String ExceptionMessageAccountType = "com.serviceaccount.model.Accounts.accounType";
	
	//Status Success/Exception Description
	public static final String ExceptionOptionAccountType = "Account Type is required field Option [S,C]";
	public static final String ExceptionEmailRequiredField = "Email is required field";
	public static final String CreatedCustomerAccount = "Customer account created";
	public static final String FoundCustomerAccount = "Customer Account found";
	public static final String NotFoundCustomerAccount = "Customer Account not found";
	public static final String NoCustomerAccountType = "Account Balance not found or dormant";
	
	//Status Code Success/Exception Description
	public static final String FoundCustomerAccountCode = "302";
	public static final String NotFoundCustomerAccountCode = "401";
	
	//Splitting Values Identifier
	public static final String SplitExceptionMessage = " : ";
	
	//OATH key
	public static final String ClientIDAuthentication = "admin";
	public static final String ClientSecretPassword = "secret-key";
	public static final String PrivateKey = "-----BEGIN RSA PRIVATE KEY-----\r\n"
	   		+ "MIIEowIBAAKCAQEApeN/ME8D9h43YkQr+uKQWCeu0VyeSmisDg/BUT8GGVFMGUd+\r\n"
	   		+ "jZS0OekUNc4EqW8z54OkJ/nlQ805BmDc9j2h6GxAw6+DGgwTNREHrW6MJ9KBII8l\r\n"
	   		+ "GtMofu1oJSblHAmbrVz8/5eqy9EUbgusiep4IYWIIarz6mI+BCyGqoYchFG+j3WI\r\n"
	   		+ "gs/p79g8U9vDseHgVtz5a9r6qWnU1vHaYiX36o3qf1Yt+Um3ufZzk1pKFpIMyXUU\r\n"
	   		+ "nDM8GeSJ/sEuf4ZtKc72vy9Ilo3i7qW1BmKQxm6KL7DXMiR2TD778XDiHrOnBBmt\r\n"
	   		+ "WKuLHmTILwG9y7S/58kpaY/rh6p3HLiLVgyiZwIDAQABAoIBAHULLpJHLeRZLXqH\r\n"
	   		+ "3wAFGxMX7KCLWKPndn31X9CTRxpV4ft0FjaO1RqG+Vt9IIzriROwLgKGrtxH0BRQ\r\n"
	   		+ "A5c5pSL4PjnTfAzEuEiY23HircDrJXBzjxp+IfRsTS7pYn2FyfFnypoLyNZFjsSW\r\n"
	   		+ "OAaXCGuea45VsAOPjXIkeF+YgEZaw+u6zyoV27Vg1ITe7PmEDrg45tQpIc2r3+66\r\n"
	   		+ "yi8Qe0CMnOSd7M1G2yUtO0wDBn77nWoUIkGYVhnp975opIY0iqTRIuOp9/MBHXcj\r\n"
	   		+ "0DdN+3R4IoyGGfOVe/CyBgkBnkZGaQ7Tgy1v8HBoahnzhI2039LJMgtn5+sMbPfI\r\n"
	   		+ "KUJWIgECgYEAzriPnVnJYd1Za7Op8t6cdSMi/mNWb4CJZkWqhjESFlZE6DcVSGWF\r\n"
	   		+ "aUo8m/4JAJpAVvwCqPLA7ejX+9z5rJNaU6ojCfKipRgFOMlfIg3rQjXTv4xy7n+7\r\n"
	   		+ "sKie1gRWaa0UDOaaXl7cJx29UmkWQHaLLEa3WCGm1mY2YcCEiXNFKH8CgYEAzW8W\r\n"
	   		+ "fUrV9PcN6FWAYIvHw83Q1v83iI2bQ16jq3pz2KB3QzSd/OaR8hFt1jQQhS2ltIfm\r\n"
	   		+ "7N7NabpZhe+j4a9xoZisxKUl6Jq9uOSar4VR/7bDaVl+iZh1Q76ndpAM9z+jPM9j\r\n"
	   		+ "N5XI2O2PZXYIYBgT409ZAphgNLzjvXbWdbEtUhkCgYB9Pw8sW8kKtUzHV1yBnOSY\r\n"
	   		+ "iAhcrp0hA3S79TujADogNHadZzpK2c+iAmuEa77SkaKn0RpfeGzx+CBcJf9T0xdo\r\n"
	   		+ "F24RSePHOS14wXEVNe+y9t/rb1H3NIboOtG5g3lDn6zFQDaJNNoYDQm205q6xdf+\r\n"
	   		+ "pH3TNNhbkCQkx7rFeISPSwKBgHGoi3XIiLWeVlh7rao8y8o+PdQlofDv/733R3Bh\r\n"
	   		+ "MGTi6vkIzHwWiRq3n+BKL/Wu+qX2JsJYxqc/TbZ3jmEX8zNnx/aINIAL1PnNqPOB\r\n"
	   		+ "PIvQ7y6DMCoqan6PphFHw5Sr5cGIMCmZumSikpqdAidASwiRZvqzmU60TwANFrxI\r\n"
	   		+ "yX1JAoGBAJel0aDG7HByKZ8NaGWwN9zyWANc/FVOVXU8EMoAp2vkV7C/JOkJHGhs\r\n"
	   		+ "DBJ8Md4hCkHavApgTpfA+pf1NCGRyV4D7YeXohAlHsgeGACC1fGy3LqfU3ysrkR3\r\n"
	   		+ "zawIQyy47/5MBniRAiGy8A1u+t4eXYaCMAQPPquEFeqQgm/Ta1AP\r\n"
	   		+ "-----END RSA PRIVATE KEY-----";
	public static final String PublicKey ="-----BEGIN PUBLIC KEY-----\r\n"
	   		+ "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEApeN/ME8D9h43YkQr+uKQ\r\n"
	   		+ "WCeu0VyeSmisDg/BUT8GGVFMGUd+jZS0OekUNc4EqW8z54OkJ/nlQ805BmDc9j2h\r\n"
	   		+ "6GxAw6+DGgwTNREHrW6MJ9KBII8lGtMofu1oJSblHAmbrVz8/5eqy9EUbgusiep4\r\n"
	   		+ "IYWIIarz6mI+BCyGqoYchFG+j3WIgs/p79g8U9vDseHgVtz5a9r6qWnU1vHaYiX3\r\n"
	   		+ "6o3qf1Yt+Um3ufZzk1pKFpIMyXUUnDM8GeSJ/sEuf4ZtKc72vy9Ilo3i7qW1BmKQ\r\n"
	   		+ "xm6KL7DXMiR2TD778XDiHrOnBBmtWKuLHmTILwG9y7S/58kpaY/rh6p3HLiLVgyi\r\n"
	   		+ "ZwIDAQAB\r\n"
	   		+ "-----END PUBLIC KEY-----";
	
	public static final int RefreshTokenValiditySeconds = 60 * 60 * 24 * 30; // default 30 days.
	public static final int AccessTokenValiditySeconds = 60 * 60 * 12; // default 12 hours.
	public static final String AccessTokenscopesRead = "read";
	public static final String AccessTokenscopesWrite = "write";
}
