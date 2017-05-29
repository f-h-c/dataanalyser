package org.dataanalyser.model;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.dataanalyser.exception.CSVDataException;
import org.junit.Test;


public class CSVDataTest {

	@Test
	public void constructValidation1() {
		Map<String, Long> vars = new HashMap<>();
		vars.put("Var1", 10l);
		
		CSVData data = new CSVData(1, 0, vars);
		
		assertEquals(1, data.getId());
		assertEquals(0, data.getDecision());
		
		assertEquals(1, data.getVars().size());
		assertEquals(new Long(10), data.getVars().get("Var1"));
	}
	
	@Test
	public void constructValidation2() {
		Map<String, Long> vars = new HashMap<>();
		vars.put("Var1", 80l);
		vars.put("Var2", 8l);
		vars.put("Var3", 20l);
		vars.put("Var4", 41l);
		vars.put("Var5", 28l);
		
		CSVData data = new CSVData(3, 1, vars);
		
		assertEquals(3, data.getId());
		assertEquals(1, data.getDecision());
		
		assertEquals(5, data.getVars().size());
		assertEquals(new Long(80), data.getVars().get("Var1"));
		assertEquals(new Long(8), data.getVars().get("Var2"));
		assertEquals(new Long(20), data.getVars().get("Var3"));
		assertEquals(new Long(41), data.getVars().get("Var4"));
		assertEquals(new Long(28), data.getVars().get("Var5"));
	}

	@Test(expected = CSVDataException.class)
	public void constructValidationError1() {
		Map<String, Long> vars = new HashMap<>();
		vars.put("Var1", 10l);
		
		new CSVData(1, 25, vars);
	}

	@Test(expected = CSVDataException.class)
	public void constructValidationError2() {
		Map<String, Long> vars = new HashMap<>();
		vars.put("Var1", 10l);
		
		new CSVData(1, -1, vars);
	}

	@Test(expected = CSVDataException.class)
	public void constructValidationError3() {
		Map<String, Long> vars = new HashMap<>();
		
		new CSVData(1, 1, vars);
	}

	@Test(expected = CSVDataException.class)
	public void constructValidationError4() {
		new CSVData(1, 1, null);
	}

}
