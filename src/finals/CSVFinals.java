package finals;

public class CSVFinals {
	final static String DELIMITER = ",";
	
	static char RESTRICTED_FILE_NAME_CHARS[] = {'/','\\', ':', '*', ';','?' ,'"' ,'<' ,'>' ,'|'}; // those are available for Windows OS, for Mac only ':' is restricted
	
	public static String getDelimiter()
	{
		return DELIMITER;
	}
	
	public static boolean checkFileName(String s)
	{
		for(char c : RESTRICTED_FILE_NAME_CHARS)
		{
			if(s.contains(Character.toString(c)))
			{
				return true;
			}
		}
		return false;
	}
}
