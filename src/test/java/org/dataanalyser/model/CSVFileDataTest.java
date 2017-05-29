package org.dataanalyser.model;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dataanalyser.exception.CSVFileDataException;
import org.junit.Test;

public class CSVFileDataTest {

	private CSVFileData hypothesis01() {
		Map<String, Long> vars = new HashMap<>();
		vars.put("Var1", 10l);

		CSVData cvsData = new CSVData(1, 0, vars);

		CSVFileData data = new CSVFileData();
		data.add(cvsData);

		vars = new HashMap<>();
		vars.put("Var1", 20l);

		cvsData = new CSVData(2, 1, vars);
		data.add(cvsData);

		vars = new HashMap<>();
		vars.put("Var1", 30l);

		cvsData = new CSVData(3, 0, vars);
		data.add(cvsData);

		vars = new HashMap<>();
		vars.put("Var1", 40l);

		cvsData = new CSVData(4, 1, vars);
		data.add(cvsData);

		vars = new HashMap<>();
		vars.put("Var1", 50l);

		cvsData = new CSVData(5, 0, vars);
		data.add(cvsData);
		return data;
	}

	private CSVFileData hypothesis03() {
		Map<String, Long> vars = new HashMap<>();
		vars.put("Var1", 10l);
		vars.put("Var2", 9l);

		CSVData cvsData = new CSVData(1, 0, vars);

		CSVFileData data = new CSVFileData();
		data.add(cvsData);

		vars = new HashMap<>();
		vars.put("Var1", 20l);
		vars.put("Var2", 4l);
		cvsData = new CSVData(2, 1, vars);
		data.add(cvsData);

		vars = new HashMap<>();
		vars.put("Var1", 30l);
		vars.put("Var2", 1l);
		cvsData = new CSVData(3, 0, vars);
		data.add(cvsData);

		vars = new HashMap<>();
		vars.put("Var1", 40l);
		vars.put("Var2", 7l);
		cvsData = new CSVData(4, 1, vars);
		data.add(cvsData);

		vars = new HashMap<>();
		vars.put("Var1", 50l);
		vars.put("Var2", 5l);
		cvsData = new CSVData(5, 0, vars);
		data.add(cvsData);

		vars = new HashMap<>();
		vars.put("Var1", 20l);
		vars.put("Var2", 11l);
		cvsData = new CSVData(6, 0, vars);
		data.add(cvsData);

		vars = new HashMap<>();
		vars.put("Var1", 25l);
		vars.put("Var2", 6l);
		cvsData = new CSVData(7, 0, vars);
		data.add(cvsData);
		return data;
	}

	@Test(expected = CSVFileDataException.class)
	public void constructorValidationError1() {
		CSVFileData data = new CSVFileData();
		data.add(null);
	}

	@Test(expected = CSVFileDataException.class)
	public void constructorValidationError2() {
		Map<String, Long> vars = new HashMap<>();
		vars.put("Var1", 10l);

		CSVData cvsData = new CSVData(1, 0, vars);

		CSVFileData data = new CSVFileData();
		data.add(cvsData);

		vars = new HashMap<>();
		vars.put("Var1", 80l);
		vars.put("Var2", 8l);
		vars.put("Var3", 20l);
		vars.put("Var4", 41l);
		vars.put("Var5", 28l);

		cvsData = new CSVData(3, 1, vars);
		data.add(cvsData);
	}

	@Test
	public void fminAndFmaxValidation1() {
		CSVFileData data = hypothesis01();

		assertEquals(20l, data.getFmin("Var1"));
		assertEquals(40l, data.getFmax("Var1"));
	}

	@Test
	public void fminAndFmaxValidation2() {
		CSVFileData data = hypothesis03();

		assertEquals(20l, data.getFmin("Var1"));
		assertEquals(40l, data.getFmax("Var1"));

		assertEquals(4l, data.getFmin("Var2"));
		assertEquals(7l, data.getFmax("Var2"));
	}

	@Test(expected = CSVFileDataException.class)
	public void fminValidationError1() {
		Map<String, Long> vars = new HashMap<>();
		vars.put("Var1", 10l);

		CSVData cvsData = new CSVData(1, 1, vars);

		CSVFileData data = new CSVFileData();
		data.add(cvsData);

		data.getFmin("Var10");
	}

	@Test(expected = CSVFileDataException.class)
	public void fminValidationError2() {
		Map<String, Long> vars = new HashMap<>();
		vars.put("Var1", 10l);

		CSVData cvsData = new CSVData(1, 1, vars);

		CSVFileData data = new CSVFileData();
		data.add(cvsData);

		data.getFmin("");
	}

	@Test(expected = CSVFileDataException.class)
	public void fminValidationError3() {
		Map<String, Long> vars = new HashMap<>();
		vars.put("Var1", 10l);

		CSVData cvsData = new CSVData(1, 1, vars);

		CSVFileData data = new CSVFileData();
		data.add(cvsData);

		data.getFmin(null);
	}

	@Test(expected = CSVFileDataException.class)
	public void fmaxValidationError1() {
		Map<String, Long> vars = new HashMap<>();
		vars.put("Var1", 10l);

		CSVData cvsData = new CSVData(1, 1, vars);

		CSVFileData data = new CSVFileData();
		data.add(cvsData);

		data.getFmax("Var10");
	}

	@Test(expected = CSVFileDataException.class)
	public void fmaxValidationError2() {
		Map<String, Long> vars = new HashMap<>();
		vars.put("Var1", 10l);

		CSVData cvsData = new CSVData(1, 1, vars);

		CSVFileData data = new CSVFileData();
		data.add(cvsData);

		data.getFmax("");
	}

	@Test(expected = CSVFileDataException.class)
	public void fmaxValidationError3() {
		Map<String, Long> vars = new HashMap<>();
		vars.put("Var1", 10l);

		CSVData cvsData = new CSVData(1, 1, vars);

		CSVFileData data = new CSVFileData();
		data.add(cvsData);

		data.getFmax(null);
	}

	@Test
	public void getRemovedDataValidation1() {
		CSVFileData data = hypothesis01();
		
		List<CSVData> result = data.getNotRemovedData();
		
		assertEquals(3, result.size());
		assertEquals(2, result.get(0).getId());
		assertEquals(3, result.get(1).getId());
		assertEquals(4, result.get(2).getId());
	}

	@Test
	public void getRemovedDataValidation2() {
		CSVFileData data = hypothesis03();
		
		List<CSVData> result = data.getNotRemovedData();
		
		assertEquals(6, result.size());
		assertEquals(2, result.get(0).getId());
		assertEquals(3, result.get(1).getId());
		assertEquals(4, result.get(2).getId());
		assertEquals(5, result.get(3).getId());
		assertEquals(6, result.get(4).getId());
		assertEquals(7, result.get(5).getId());
	}

}
