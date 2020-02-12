package utils;

import java.util.ArrayList;

import control.Notify;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import data.CSVData;
import data.Row;

public class CSVWriter {
	String name;
	
	public CSVWriter(String name)
	{
		this.name = name;
	}
	
	public void write(CSVData data)
	{
		ArrayList<Row> rows = data.getRows();
		String headers[] = data.getHeaders();
		try 
		{
			BufferedWriter bf = new BufferedWriter(new FileWriter(name));
			//writeHeaders(headers);
			bf.write(String.join(",", headers)+System.lineSeparator()); // writing the headers
			for(Row row : rows)
			{
				bf.write(row.toString()+System.lineSeparator());
			}
			bf.close();
		}catch (IOException e)
		{
			Notify.sendError("problem encountered at writing data");
		}catch (Exception e)
		{
			Notify.sendError("something went wrong");
		}
	}
}
