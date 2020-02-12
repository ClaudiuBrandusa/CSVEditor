package control;

import finals.CSVFinals;
import utils.CSVFile;

public class Main {
	static CSVFile file;
	static boolean control = true;
	
	static String commands[] = {"new - creates a new file", "load - loads the specified file", "save - saves the current file", "add - add a new row", "remove - removes a given row from the current file", "print - shows the file", "stop - terminates the process"};
	
	public static void main(String[] args) throws Exception
	{
		
		String cmd = "";
		while(control)
		{
			Notify.announce("available commands:\n"+String.join("\n", commands));
			cmd = Notify.getInput("your command:");
			activateCommand(cmd);
		}
	}
	
	static String getFileName()
	{
		String s = "";
		while(s.length() == 0)
		{
			Notify.announce("introduce the file name");
			s = Notify.getInput("file name: ");
			if(CSVFinals.checkFileName(s)) // true means that the filename contains not allowed chars, else its ok
			{
				Notify.sendWarning("the file's name cannot contain restricted symbols, try using letters and numbers only");
				s = "";
			}
		}
		return s;
	}
	
	static int getIndex()
	{
		int i = -1;
		while(file.getLen() < i || i < 0)
		{
			i = Notify.getInt("introduce the index of the row");
		}
		return i;
	}
	
	static void pause()
	{
		Notify.getInput("press any key to continue");
	}
	
	static void activateCommand(String cmd) throws Exception
	{
		switch(cmd.toLowerCase())
		{
			case "new":
				file = new CSVFile(getFileName());
				break;
			case "load":
				file = new CSVFile(getFileName());
				file.load();
				pause();
				break;
			case "save":
				if(file == null)
				{
					Notify.sendError("file not found");
					break;
				}
				file.save();
				pause();
				break;
			case "add":
				if(file == null)
				{
					Notify.sendError("file not found");
					break;
				}
				file.addRow();
				break;
			case "remove":
				if(file == null)
				{
					Notify.sendError("file not found");
					pause();
					break;
				}
				file.remove(getIndex());
				break;
			case "print":
				if(file == null)
				{
					Notify.sendError("file not found");
					pause();
					break;
				}
				file.print();
				pause();
				break;
			case "stop":
				control = false;
				break;
			default:
				Notify.announce("unknown command");
				break;
		}
	}
}
