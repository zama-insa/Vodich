package com.vodich.dao;

import java.util.List;

import com.vodich.core.bean.Scenario;

public interface ScenarioDAO {

	public void save(Scenario scenario) throws DAOException;

	public boolean delete(String scenarioId) throws DAOException;

	public Scenario load(String scenarioId) throws DAOException;

	public Scenario loadByName(String scenarioName) throws DAOException;

	public List<Scenario> loadAll();
}
