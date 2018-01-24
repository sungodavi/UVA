import java.util.*;
import java.io.*;

public class p10079
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		for(long num = Long.parseLong(f.readLine()); num >= 0; num = Long.parseLong(f.readLine()))
		{
			out.println(1 + num * (num + 1) / 2);
		}
		out.close();
	}
}