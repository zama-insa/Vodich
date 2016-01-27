package com.vodich.dao;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.action.delete.DeleteResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.vodich.core.bean.Scenario;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ElasticsearchUtils.class)
public class ScenarioDAOImplTest {
	private static ScenarioDAO scenarioDAO = ScenarioDAOImpl.getInstance();
 
	@Before
	public void setUp() {
		PowerMockito.mockStatic(ElasticsearchUtils.class);
	}
	
	@Test(expected=DAOException.class)
	public void saveTestScenarioNameNull() throws DAOException{
		Scenario sc = new Scenario();
		scenarioDAO.save(sc);
	}
	
	@Test(expected=DAOException.class)
	public void saveTestScenarioNameEmptyl() throws DAOException{
		Scenario sc = new Scenario ();
		sc.setName("");
		scenarioDAO.save(sc);	
	}
	
	@SuppressWarnings("unchecked")
	@Test(expected=DAOException.class)
	public void saveTestScenarioWithNameSaveFailed() throws DAOException{
		Scenario sc = new Scenario ();
		sc.setName("test");
		when(ElasticsearchUtils.saveScenario(sc)).thenThrow(DAOException.class);
		scenarioDAO.save(sc);
	}
	
	@Test
	public void saveTestScenarioWithNameSaveSucceded() throws DAOException{
		Scenario sc = new Scenario ();
		sc.setName("test");
		scenarioDAO.save(sc);
	}
	
	@Test
	public void loadScenario() throws DAOException {
		Scenario sc = new Scenario ();
		sc.setName("yo");
		when(ElasticsearchUtils.load("42")).thenReturn(sc);
		assertEquals(scenarioDAO.load("42"), sc);
	}
	
	@Test
	public void loadScenarioByName() throws DAOException {
		Scenario sc = new Scenario ();
		sc.setName("yo");
		when(ElasticsearchUtils.loadByName("42")).thenReturn(sc);
		assertEquals(scenarioDAO.loadByName("42"), sc);
	}
	
	@Test
	public void loadAllTest() {
		Scenario sc = new Scenario ();
		sc.setName("yo");
		List<Scenario> scenarii = new ArrayList<>();
		scenarii.add(sc);
		scenarii.add(sc);
		when(ElasticsearchUtils.loadScenarii()).thenReturn(scenarii);
		assertEquals(scenarioDAO.loadAll().size(), 2);
	}
	
	@Test
	public void deleteScenarioTest() throws DAOException{
		DeleteResponse response = new DeleteResponse();
		when(ElasticsearchUtils.deleteScenario("42")).thenReturn(response);
		assertEquals(scenarioDAO.delete("42"), response.isFound());
	}
	
	
}
