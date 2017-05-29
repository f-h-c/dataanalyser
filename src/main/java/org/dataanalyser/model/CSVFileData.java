package org.dataanalyser.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dataanalyser.exception.CSVFileDataException;

public class CSVFileData {

	private List<CSVData> data = new ArrayList<>();
	private Map<String, Long> fmin = new HashMap<>();
	private Map<String, Long> fmax = new HashMap<>();
	private boolean processed = false;

	private void defineFmin(String var) {
		Long min = fmin.get(var);

		for (CSVData cvsData : data) {
			if (cvsData.getDecision() == 1)
				if (min == null || cvsData.getVar(var).compareTo(min) < 0)
					min = cvsData.getVar(var);
		}

		fmin.put(var, min);
	}

	private void defineFmax(String var) {
		Long max = fmax.get(var);

		for (CSVData cvsData : data) {
			if (cvsData.getDecision() == 1)
				if (max == null || cvsData.getVar(var).compareTo(max) > 0)
					max = cvsData.getVar(var);
		}

		fmax.put(var, max);
	}

	private void processData() {
		if (data.size() > 0) {
			for (String var : data.get(0).getVariables()) {
				defineFmin(var);
				defineFmax(var);
			}
			
			for (CSVData cvsData : data) {
				cvsData.evaluate(fmin, fmax);
			}

			processed = true;
		}
	}

	public void add(CSVData cvsData) {
		if (cvsData == null)
			throw new CSVFileDataException("CVSData can't be NULL!");

		if (data.size() == 0 || data.get(0).getVariables().equals(cvsData.getVariables())) {
			data.add(cvsData);

			processed = false;
		}
		else
			throw new CSVFileDataException("CVSData must have the same variables!");
	}

	public long getFmin(String var) {
		Long result = null;

		if (!processed)
			processData();

		result = fmin.get(var);

		if (result == null)
			throw new CSVFileDataException("Variable column - " + var + " doesn't exist!");
		else
			return result.longValue();
	}

	public long getFmax(String var) {
		Long result = null;

		if (!processed)
			processData();

		result = fmax.get(var);

		if (result == null)
			throw new CSVFileDataException("Variable column - " + var + " doesn't exist!");
		else
			return result.longValue();
	}
	
	public List<CSVData> getNotRemovedData() {
		List<CSVData> result = new ArrayList<>();

		if (!processed)
			processData();
		
		for (CSVData cvsData : data) {
			if (!cvsData.isRemoved())
				result.add(cvsData);
		}
		
		return result;
	}

}
