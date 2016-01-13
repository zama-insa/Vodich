package com.vodich.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;

import javax.jms.JMSException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.vodich.core.bean.Flow;
import com.vodich.core.bean.Scenario;
import com.vodich.core.util.JMSUtils;
import com.vodich.dao.DAOException;
import com.vodich.dao.ScenarioDAO;

@RunWith(MockitoJUnitRunner.class)
public class ScenarioServiceImplTest {

	@Mock
	ScenarioDAO scenarioDAOMock;

	@Mock
	JMSUtils jmsUtilsMock;

	ScenarioServiceImpl scenarioServiceImpl;

	@Before
	public void setUp() {
		scenarioServiceImpl = new ScenarioServiceImpl(scenarioDAOMock, jmsUtilsMock);
	}

	@Test
	public void testLoadByIdNonExistScenario() throws DAOException {
		when(scenarioDAOMock.load("42")).thenReturn(null);
		assertEquals(scenarioServiceImpl.load("42"), null);
	}

	@Test(expected = DAOException.class)
	public void testLaunchNonExistScenario() throws DAOException, JMSException {
		when(scenarioDAOMock.load("42")).thenReturn(null);
		scenarioServiceImpl.launch("42");
	}

	@Test
	public void testLaunchExistingScenario() throws DAOException, JMSException {
		Scenario s = new Scenario();
		s.setFlows(new ArrayList<Flow>());
		when(scenarioDAOMock.load("42")).thenReturn(s);
		scenarioServiceImpl.launch("42");
	}

	@Test
	public void testDeleteNonExistingScenario() throws DAOException {
		String scenarioId = "42";
		when(scenarioDAOMock.delete("42")).thenReturn(false);
		assertFalse(scenarioServiceImpl.delete(scenarioId));
	}

	@Test
	public void testDeleteExistingScenario() throws DAOException {
		String scenarioId = "42";
		when(scenarioDAOMock.delete("42")).thenReturn(true);
		assertTrue(scenarioServiceImpl.delete(scenarioId));
	}

	@Test
	public void testLoadAll() {
		when(scenarioDAOMock.loadAll()).thenReturn(new ArrayList<Scenario>());
		assertNotNull(scenarioServiceImpl.loadAll());
	}

	@Test
	public void testLoadByNameNotFound() throws DAOException {
		when(scenarioDAOMock.loadByName("42")).thenReturn(null);
		assertNull(scenarioServiceImpl.loadAll());
	}

	@Test
	public void testLoadByNameFound() throws DAOException {
		String sname = "deadpool revenge";
		Scenario s = new Scenario();
		s.setId("42");
		s.setCreatedAt(new Date());
		s.setName(sname);
		when(scenarioDAOMock.loadByName(sname)).thenReturn(s);
		assertEquals(s, scenarioServiceImpl.loadByName(sname));
	}

}
