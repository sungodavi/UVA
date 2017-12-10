import java.util.*;
import java.io.*;

public class p389 
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		for(String input = f.readLine(); input != null; input = f.readLine())
		{
			StringTokenizer st = new StringTokenizer(input);
			String num = st.nextToken();
			int b1 = Integer.parseInt(st.nextToken());
			int b2 = Integer.parseInt(st.nextToken());
			
			String result = Integer.toString(Integer.parseInt(num, b1), b2);
			if(result.length() > 7)
				System.out.println("  ERROR");
			else
				System.out.printf("%7S\n", result);
		}
	}
}
