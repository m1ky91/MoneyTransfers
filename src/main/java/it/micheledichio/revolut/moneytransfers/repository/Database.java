package it.micheledichio.revolut.moneytransfers.repository;

public class Database {
	
	private static Database dbInstance = new Database();
	private CountryAbstractRepository countryRepository;
	private CurrencyAbstractRepository currencyRepository;
	private RateAbstractRepository rateRepository;
	
	public static Database getInstance() {
		return dbInstance;
	}
	
	private Database() {
		countryRepository = new CountryRepository();
		currencyRepository = new CurrencyRepository();
		rateRepository = new RateRepository();
	}
	
	public CountryAbstractRepository getCountryRepository() {
		return countryRepository;
	}

	public CurrencyAbstractRepository getCurrencyRepository() {
		return currencyRepository;
	}
	
	public RateAbstractRepository getRateRepository() {
		return rateRepository;
	}

}
