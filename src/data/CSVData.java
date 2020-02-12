package data;

import java.util.ArrayList;
import java.util.Scanner;

import control.Notify;
import utils.CSVWriter;

public class CSVData {
	String name;
	String headers[];
	ArrayList<Row> rows;
	
	public CSVData(String name, String headers[])
	{
		this.name = name;
		this.headers = headers;
		rows = new ArrayList<Row>();
	}
	
	public CSVData(String name)
	{
		this.name = name;
		rows = new ArrayList<Row>();
	}
	
	public CSVData(String name, ArrayList<String> headers)
	{
		this.name = name;
		this.headers = new String[headers.size()];
		for(int i = 0; i<headers.size(); i++)
		{
			this.headers[i] = headers.get(i);
		}
		rows = new ArrayList<Row>();
	}
	
	public void createRow()
	{
		if(headers == null || headers.length < 2)
		{
			setHeaders();
			if(headers.length < 2)
			{
				Notify.sendError("not enough headers");
				return;
			}
		}
		System.out.println("introduce the row's data");
		String answers[] = new String[headers.length-1];
		for(int i = 1;i<headers.length;i++) // we are starting at 1 because at the 0 position is the index
		{
			answers[i-1] = Notify.getInput(headers[i]);
			System.out.println();
		}
		if(rows.size() == 0)
		{
			rows.add(new Row(0,answers));
			return;
		}
		rows.add(new Row(rows.get(rows.size()-1).getIndex()+1,answers));
	}
	
	boolean checkString(String str)
	{
		if(str.contains(","))
		{
			return false;
		}
		return true;
	}
	
	void setHeaders()
	{
		Notify.announce("introduce your headers\nintroduce a , (comma) in order to stop the process");
		String in = Notify.getInput("introduce the header name");
		ArrayList<String> values = new ArrayList<String>();
		while(!in.equals(","))
		{
			if(checkString(in))
			{
				values.add(in);
			}else
			{
				System.out.println("you can't introduce ',' (comma) inside a header");
			}
			
			in = Notify.getInput("introduce the header name");
		}
		headers = new String[values.size()+1];
		headers[0] = "index";
		for(int i = 1; i < headers.length; i++)
		{
			headers[i] = values.get(i-1);
		}
	}
	
	public void append(Row row)
	{
		rows.add(row);
	}
	
	public void remove(int i)
	{
		boolean status = false;
		int index = 0;
		for(int j=0;j<rows.size();j++)
		{
			if(rows.get(j).getIndex() == i)
			{
				index = j;
				status = true;
			}
		}
		if(status)
		{
			rows.remove(index);
			for(Row row : rows)
			{
				if(row.getIndex() > i)
				{
					row.setIndex(row.getIndex()-1);
				}
			}
		}
	}
	
	public void append(Row rows[])
	{
		for(Row row : rows)
		{
			this.rows.add(row);
		}
	}
	
	public void append(ArrayList<Row> rows)
	{
		for(Row row : rows)
		{
			this.rows.add(row);
		}
	}
	
	public Row getRow(int index)
	{
		if(index < rows.size())
		{
			return rows.get(index);
		}
		return null;
	}
	
	public ArrayList<Row> getRows()
	{
		return rows;
	}
	
	public int getRowsLength()
	{
		return rows.size();
	}
	
	public String[] getHeaders()
	{
		return headers;
	}
	
	public void print()
	{
		System.out.print("|");
		for(String header : headers)
		{
			System.out.print(header+"|");
		}
		System.out.println();
		for(Row row : rows)
		{
			System.out.println(row);
		}
	}
}
