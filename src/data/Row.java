package data;

import finals.CSVFinals;

public class Row {
	int index;
	String data[];
	
	public Row(int index, String data[])
	{
		this.index = index;
		this.data = data;
	}
	
	public String[] getData()
	{
		return data;
	}
	
	public int getIndex()
	{
		return index;
	}
	
	public void setIndex(int i)
	{
		index = i;
	}
	
	public String getData(int column)
	{
		if(data.length > column)
		{
			return data[column];
		}
		// we will return an empty line because the introduced column index does not exist
		return "";
	}
	
	// with this method we will be able to modify the row's values
	public void set(int column, String val)
	{
		if(data.length > column)
		{
			data[column] = val;
		}
	}
	
	public String toString()
	{
		String str = index+CSVFinals.getDelimiter();
		if(data.length == 0)
		{
			return str;
		}else if(data.length == 1)
		{
			return str+data[0];
		}
		str+=data[0];
		for(int i=1;i<data.length;i++)
		{
			str+=CSVFinals.getDelimiter()+data[i];
		}
		return str;
	}
}
