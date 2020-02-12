package utils;

import control.Notify;
import data.CSVData;

public class CSVFile {
	String name;
	CSVData data;
	
	public CSVFile(String file) throws Exception
	{
		if(file.length() == 0)
		{
			throw new Exception("You cannot have a csvfile without a name.");
		}
		setName(file);
		setData();
	}
	
	void setName(String name)
	{
		if(name.endsWith(".csv"))
		{
			this.name = name;
		}else
		{
			this.name = name+".csv";
		}
	}
	
	public int getLen()
	{
		return data.getRowsLength();
	}
	
	void setData()
	{
		data = new CSVData(name);
	}
	
	public void addRow()
	{
		data.createRow();
	}
	
	public void save()
	{
		if(data == null)
		{
			Notify.sendError("the program couldn't save the data");
			return;
		}
		CSVWriter writer = new CSVWriter(name);
		writer.write(data);
		Notify.announce("saving successfully");
	}
	
	public void load()
	{
		CSVReader reader = new CSVReader(name);
		data = reader.read();
		Notify.announce("loading successfully");
	}
	
	public void remove(int i)
	{
		data.remove(i);
	}
	
	public void print()
	{
		System.out.println("file "+name);
		data.print();
	}
}
