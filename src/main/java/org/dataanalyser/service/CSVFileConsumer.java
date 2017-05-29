package org.dataanalyser.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dataanalyser.exception.CSVFileConsumerException;
import org.dataanalyser.model.CSVData;
import org.dataanalyser.model.CSVFileData;

public class CSVFileConsumer {
	private InputStream file;
	private List<String> variables = new ArrayList<>();
	private CSVFileData fileData = new CSVFileData();
	
	public CSVFileConsumer(InputStream file) {
		if (file == null)
			throw new CSVFileConsumerException("File can't be NULL!");
		
		this.file = file;
		
		consumeFile();
	}
	
	public List<CSVData> getNotRemovedData() {
		return fileData.getNotRemovedData();
	} 
	
	public List<String> getVariables() {
		return variables;
	}

	private void consumeFile() {	
		try {
	    InputStreamReader isr = new InputStreamReader(file);
			BufferedReader in = new BufferedReader(isr);
			
			int lineNum = 0;

			while (in.ready()) {
				if (lineNum++ == 0)
					consumeHeader(in.readLine());
				else
				  consumeLine(in.readLine());
			}

			in.close();
		}
		catch (Exception e) {
			throw new CSVFileConsumerException(e.getMessage());
		}
	}

	private void consumeHeader(String line) {
		String[] values = line.split(",");
		
		if (values.length >= 3) {
			for (int i=1; i<values.length-1; i++)
				variables.add(values[i]);
		}
		else
			throw new CSVFileConsumerException("Invalid file content!");
	}

	private void consumeLine(String line) {
		String[] values = line.split(",");

		if (values.length == variables.size()+2) {
			long id = Long.decode(values[0]);
			long decision = Long.decode(values[values.length-1]);
			Map<String, Long> vars = new HashMap<>();
			
			for (int i=1; i<values.length-1; i++){
				vars.put(variables.get(i-1), Long.decode(values[i]));
			}
			
			fileData.add(new CSVData(id, decision, vars));
		}
		else
			throw new CSVFileConsumerException("Data incompatible with the header!");
	}

}
