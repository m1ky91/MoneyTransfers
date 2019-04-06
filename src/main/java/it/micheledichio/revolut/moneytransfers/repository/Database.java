package it.micheledichio.revolut.moneytransfers.repository;

public class Database {
	
	private static Database dbInstance = new Database();
	private CountryAbstractRepository countryRepository;
	
	public static Database getInstance() {
		return dbInstance;
	}
	
	private Database() {
		countryRepository = new CountryRepository();
	}
	
	public CountryAbstractRepository getCountryRepository() {
		return countryRepository;
	}

}
