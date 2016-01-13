package com.vodich.business;

public class CommonServiceImpl implements CommonService {

	@Override
	public int getConsumerNum() {
		return 5; // constant for now
	}

	@Override
	public int getProducerNum() {
		return 5; // constant for now
	}

	CommonServiceImpl() {
	}

	private static CommonServiceImpl instance;

	public static CommonService getInstance() {
		if (instance == null) {
			instance = new CommonServiceImpl();
		}
		return instance;
	}

}
