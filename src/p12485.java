import java.util.*;
import java.io.*;

public class p12485 {
	public static void main(String[] args) throws IOException 
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		for(String input = f.readLine(); input != null; input = f.readLine())
		{
			int[] a = new int[Integer.parseInt(input)];
			st = new StringTokenizer(f.readLine());
			int sum = 0;
			for(int i = 0; i < a.length; i++)
				sum += a[i] = Integer.parseInt(st.nextToken());
			if(sum % a.length != 0)
				out.println(-1);
			else
			{
				int avg = sum / a.length;
				int result = 0;
				for(int i = 0; i < a.length; i++)
					result += Math.abs(avg - a[i]);
				result = (result + 1) >> 1;
				out.println(result + 1);
			}
		}
		out.close();
	}
}