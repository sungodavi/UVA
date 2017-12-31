import java.util.*;
import java.io.*;

public class p277
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new FileReader("input.txt"));
		int times = Integer.parseInt(f.readLine());
		f.readLine();
		boolean flag = false;
		while(times-- > 0)
		{
			if(flag)
				System.out.println();
			else
				flag = true;
			System.out.println("Item Id,Item Desc, Item Price");
			for(String input = f.readLine(); input != null; input = f.readLine())
				System.out.println(process(input));
		}
	}
	
	public static String process(String s) //[code, name, desc, ext, price]
	{
		StringTokenizer st = new StringTokenizer(s, ",");
		String[] parts = new String[5];
		int i;
		for(i = 0; i < parts.length && st.hasMoreTokens(); i++)
			parts[i] = st.nextToken();
		for(; i < parts.length; i++)
			parts[i] = "";
		
		StringBuilder result = new StringBuilder();
		for(i = parts[0].length(); i < 3; i++)
			result.append("0");
		result.append(parts[0]);result.append(parts[2]);result.append(',');
	}
}