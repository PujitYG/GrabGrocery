package com.microservice.authservice.DTO;

public class Token {
	
	private String username;
	private String jwt;
	private String email;
	
	private Token() {
		super();
	}
	
	private Token(String username,String jwt,String email) {
		this.username=username;
		this.jwt=jwt;
		this.email=email;
	}
	
	
	public static class TokenBuilder {
		private String username="";
		private String jwt="";
		private String email="";
		
		
		public TokenBuilder username(String username) {
			this.username=username;
			return this;
		}
		
		public TokenBuilder jwt(String jwt) {
			this.jwt=jwt;
			return this;
		}
		
		public TokenBuilder email(String email) {
			this.email=email;
			return this;
		}
		
		public Token build() {
			return new Token(this.username,this.jwt,this.email);
		}
		
	}
	
	
	
	public String getUsername() {
		return username;
	}

	public String getJwt() {
		return jwt;
	}

	public String getEmail() {
		return email;
	}

	public static TokenBuilder buider() {
		return new TokenBuilder();
	}
		

}
