package it.micheledichio.revolut.moneytransfers.repository;

public class Database {
	
	private static Database dbInstance = new Database();
	private CountryAbstractRepository countryRepository;
	private CurrencyAbstractRepository currencyRepository;
	private RateAbstractRepository rateRepository;
	private AccountAbstractRepository accountRepository;
	private UserAbstractRepository userRepository;
	private TransferAbstractRepository transferRepository;
	
	public static Database getInstance() {
		return dbInstance;
	}
	
	private Database() {
		countryRepository = new CountryRepository();
		currencyRepository = new CurrencyRepository();
		rateRepository = new RateRepository();
		accountRepository = new AccountRepository();
		userRepository = new UserRepository();
		transferRepository = new TransferRepository();
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

	public AccountAbstractRepository getAccountRepository() {
		return accountRepository;
	}

	public UserAbstractRepository getUserRepository() {
		return userRepository;
	}
	
	public TransferAbstractRepository getTransferRepository() {
		return transferRepository;
	}

}
