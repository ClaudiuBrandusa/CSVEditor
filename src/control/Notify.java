package control;

import java.util.Scanner;

public class Notify {
	static Scanner scanner = new Scanner(System.in);
	
	public static void sendError(String str)
	{
		System.err.println(str);
	}
	
	public static void sendWarning(String str)
	{
		System.out.println(str);
	}
	
	public static void announce(String str)
	{
		System.out.println(str);
	}
	
	public static String getInput(String msg)
	{
		if(msg.contains(":"))
		{
			System.out.print(msg);
		}else
		{
			System.out.print(msg+": ");
		}
		
		System.out.println();
		return scanner.next();
	}
	
	public static int getInt(String msg)
	{
		int val = -1;
		String s = getInput(msg);
		try
		{
			val = Integer.parseInt(s);
		}catch (Exception e)
		{
			return -1;
		}
		return val;
	}
}
