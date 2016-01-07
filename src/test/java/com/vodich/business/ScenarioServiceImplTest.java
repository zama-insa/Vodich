package com.vodich.business;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

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
import com.vodich.dao.ResultDAO;
import com.vodich.dao.ScenarioDAO;

@RunWith(MockitoJUnitRunner.class)
public class ScenarioServiceImplTest {
	
	@Mock
	ScenarioDAO scenarioDAOMock;
	
	@Mock
	JMSUtils jmsUtilsMock;
	
	@Mock
	ResultDAO resultDAOMock;
	
	ScenarioServiceImpl scenarioServiceImpl;
	
	@Before
	public void setUp() {
		scenarioServiceImpl = new ScenarioServiceImpl(scenarioDAOMock, resultDAOMock, jmsUtilsMock);
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
}
