package it.micheledichio.moneytransfers.model;

import io.swagger.annotations.ApiModelProperty;

public class User implements Validable {
	
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String account;
	private String mobilePhone;
	private String email;
	
	public User() {}

	public User(String username, String password, String firstName, String lastName, String account) {
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.account = account;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@ApiModelProperty(hidden = true)
	@Override
	public boolean isValid() {
		return username != null && !username.isEmpty()
				&& password != null && !password.isEmpty()
				&& firstName != null && !firstName.isEmpty()
				&& lastName != null && !lastName.isEmpty();
	}

}
