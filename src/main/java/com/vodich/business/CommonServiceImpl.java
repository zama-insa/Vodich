package com.vodich.business;

public class CommonServiceImpl implements CommonService {

	@Override
	public int getConsumerNum() {
		return 5; // fixed for now
	}

	@Override
	public int getProducerNum() {
		return 5; // fixed for now
	}
	
	private CommonServiceImpl() {}
	private static CommonServiceImpl instance;
	public static CommonService getInstance() {
		if (instance == null) {
			instance = new CommonServiceImpl();
		}
		return instance;
	}

}
