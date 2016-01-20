package com.vodich.business;

import java.io.IOException;
import java.util.List;

import javax.jms.JMSException;

import com.vodich.core.bean.Result;
import com.vodich.core.bean.Scenario;

public interface ResultService {
	public Result load(String id);
	
	public List<Result> loadAll();
	
	public String actualizeResult(Scenario scenario) throws IOException, JMSException;
	
}
