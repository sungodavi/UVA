import java.util.*;
import java.io.*;

public class p282
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		boolean flag = false;
		for(String input = f.readLine(); input != null; input = f.readLine())
		{
			if(flag)
				System.out.println();
			else
				flag = true;
			ArrayList<String> list = new ArrayList<String>();
			while(!input.equals("end"))
			{
				list.add(input);
				input = f.readLine();
			}
			
//			System.out.println(list);
			
			for(String command = f.readLine(); !command.equals("end"); command = f.readLine())
			{
				System.out.println(command);
				st = new StringTokenizer(command);
				st.nextToken();
				String from = st.nextToken();
				String[] parts = from.split("\\*");
				String to = st.nextToken();
				String[] resultParts = to.split("\\*");
				
				for(String s : list)
				{
					if(check(s, parts))
					{
						String result = resultParts[0] + 
								s.substring(parts[0].length(), s.length() - parts[1].length()) + resultParts[1];
						System.out.printf("mv %s %s\n", s, result);
					}
				}
			}
		}
	}
	
	public static boolean check(String s, String[] parts)
	{
		try
		{
			return s.substring(0, parts[0].length()).equals(parts[0]) &&
				s.substring(s.length() - parts[1].length()).equals(parts[1]);
		}
		catch(Exception e)
		{
			return false;
		}
	}
}