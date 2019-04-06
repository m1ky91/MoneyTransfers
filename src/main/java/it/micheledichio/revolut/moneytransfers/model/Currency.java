package it.micheledichio.revolut.moneytransfers.model;

public class Currency {

	private String code;
	private String name;
	private Country country;
	
	public Currency() {}
	
	public Currency(String code, String name, Country country) {
		this.code = code;
		this.name = name;
		this.country = country;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

}
