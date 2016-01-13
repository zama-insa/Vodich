package com.vodich.business;

import java.util.List;

import javax.jms.JMSException;

import com.vodich.core.bean.Scenario;
import com.vodich.dao.DAOException;

public interface ScenarioService {

	public void save(Scenario scenario) throws DAOException;

	public void launch(String scenarioId) throws DAOException, JMSException;

	public boolean delete(String scenarioId) throws DAOException;

	public List<Scenario> loadAll();

	public Scenario load(String scenarioID) throws DAOException;

	public Scenario loadByName(String scenarioName) throws DAOException;
}
