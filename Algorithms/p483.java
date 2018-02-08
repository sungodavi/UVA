import java.util.*;
import java.io.*;

public class p483
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		StringTokenizer st;
		for(String input = f.readLine(); input != null; input = f.readLine())
		{
			st = new StringTokenizer(input);
			boolean flag = false;
			while(st.hasMoreTokens())
			{
				StringBuilder s = new StringBuilder(st.nextToken());
				if(flag)
					out.print(" ");
				else
					flag = true;
				out.print(s.reverse());
			}
			out.println();
		}
		out.close();
	}
}