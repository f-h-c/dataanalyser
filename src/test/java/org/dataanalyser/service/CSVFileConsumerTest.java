package org.dataanalyser.service;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

import org.dataanalyser.exception.CSVFileConsumerException;
import org.dataanalyser.model.CSVData;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class CSVFileConsumerTest {

	@Rule
	public TemporaryFolder temp = new TemporaryFolder();

	private InputStream createFileError01() throws IOException {
		File file = temp.newFile("exampleA_input.csv");

		PrintWriter fileRecorder = new PrintWriter(file);

		fileRecorder.print("Id,Var1,Decision\n");
		fileRecorder.print("1,0\n");
		fileRecorder.print("2,20,1\n");
		fileRecorder.print("3,30,0\n");
		fileRecorder.print("4,40,1\n");
		fileRecorder.print("5,50,0\n");

		fileRecorder.close();

		return new FileInputStream(file);
	}

	private InputStream createFileError02() throws IOException {
		File file = temp.newFile("exampleA_input.csv");

		PrintWriter fileRecorder = new PrintWriter(file);

		fileRecorder.print("Id,Var1,Decision\n");
		fileRecorder.print("1,10,10,0\n");
		fileRecorder.print("2,20,1\n");
		fileRecorder.print("3,30,0\n");
		fileRecorder.print("4,40,1\n");
		fileRecorder.print("5,50,0\n");

		fileRecorder.close();

		return new FileInputStream(file);
	}

	private InputStream createFile01() throws IOException {
		File file = temp.newFile("exampleA_input.csv");

		PrintWriter fileRecorder = new PrintWriter(file);

		fileRecorder.print("Id,Var1,Decision\n");
		fileRecorder.print("1,10,0\n");
		fileRecorder.print("2,20,1\n");
		fileRecorder.print("3,30,0\n");
		fileRecorder.print("4,40,1\n");
		fileRecorder.print("5,50,0\n");

		fileRecorder.close();

		return new FileInputStream(file);
	}

	private InputStream createFile03() throws IOException {
		File file = temp.newFile("exampleC_input.csv");

		PrintWriter fileRecorder = new PrintWriter(file);
		
		fileRecorder.print("Id,Var1,Var2,Decision\n");
		fileRecorder.print("1,10,9,0\n");
		fileRecorder.print("2,20,4,1\n");
		fileRecorder.print("3,30,1,0\n");
		fileRecorder.print("4,40,7,1\n");
		fileRecorder.print("5,50,5,0\n");
		fileRecorder.print("6,20,11,0\n");
		fileRecorder.print("7,25,6,0\n");

		fileRecorder.close();

		return new FileInputStream(file);
	}

	@Test
	public void getNotRemovedDataValidation1() {
		try {
			CSVFileConsumer consumer = new CSVFileConsumer(createFile01());
			
			List<CSVData> result = consumer.getNotRemovedData();
			
			assertEquals(3, result.size());
			assertEquals(2, result.get(0).getId());
			assertEquals(3, result.get(1).getId());
			assertEquals(4, result.get(2).getId());
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void getNotRemovedDataValidation3() {
		try {
			CSVFileConsumer consumer = new CSVFileConsumer(createFile03());
			
			List<CSVData> result = consumer.getNotRemovedData();
			
			assertEquals(6, result.size());
			assertEquals(2, result.get(0).getId());
			assertEquals(3, result.get(1).getId());
			assertEquals(4, result.get(2).getId());
			assertEquals(5, result.get(3).getId());
			assertEquals(6, result.get(4).getId());
			assertEquals(7, result.get(5).getId());
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test(expected = CSVFileConsumerException.class)
	public void createValidationError1() {
		new CSVFileConsumer(null);
	}

	@Test(expected = CSVFileConsumerException.class)
	public void createValidationError2() {
		try {
			new CSVFileConsumer(createFileError01());
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test(expected = CSVFileConsumerException.class)
	public void createValidationError3() {
		try {
			new CSVFileConsumer(createFileError02());
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	

}
