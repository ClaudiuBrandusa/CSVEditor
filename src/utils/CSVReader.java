package utils;

import java.util.ArrayList;
import java.util.Arrays;

import control.Notify;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import data.CSVData;
import data.Row;
import finals.CSVFinals;

public class CSVReader {
	String fileName;
	ArrayList<String> headers;
	ArrayList<Row> rows;
	
	public CSVReader(String file)
	{
		fileName = file;
		setRows();
	}
	
	void setRows()
	{
		rows = new ArrayList<Row>();
		try
		{
			FileReader file = null;
			try 
			{
				file = new FileReader(fileName);
			}catch (FileNotFoundException e)
			{
				Notify.sendWarning("file not found");
				headers = new ArrayList<String>();
				return;
			}
			BufferedReader br = new BufferedReader(file);
			String line = br.readLine();
			if(line == null)
			{
				line = "";
			}
			setHeaders(line);
			while((line = br.readLine()) != null)
			{
				rows.add(setRow(line));
			}
			
			br.close();
		}catch (IOException e)
		{
			Notify.sendError("invalid filename");
			e.printStackTrace();
		}catch (Exception e)
		{
			Notify.sendError("something went wrong");
			e.printStackTrace();
		}
	}
	
	void setHeaders(String line)
	{
		if(line.length()==0)
		{
			headers = new ArrayList<String>();
			return;
		}
		headers = new ArrayList<String>();
		for(String str : line.split(","))
		{
			headers.add(str);
		}
	}
	
	Row setRow(String line)
	{
		String values[] = line.split(CSVFinals.getDelimiter());
		int index = Integer.parseInt(values[0]);
		return new Row(index, Arrays.copyOfRange(values, 1, values.length));
	}
	
	CSVData read()
	{
		CSVData data = new CSVData(fileName, headers);
		data.append(rows);
		return data;
	}
}
