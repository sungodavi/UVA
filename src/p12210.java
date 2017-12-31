import java.util.*;
import java.io.*;

public class p12210
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int k = 1;
		for(String input = f.readLine(); !input.equals("0 0"); input = f.readLine())
		{
			System.out.printf("Case %d: ", k++);
			st = new StringTokenizer(input);
			int b = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int min = Integer.MAX_VALUE;
			for(int i = 0; i < b; i++)
				min = Math.min(min, Integer.parseInt(f.readLine()));
			if(b > s)
				System.out.printf("%d %d\n", b - s, min);
			else
				System.out.println("0");
			for(int i = 0; i < s; i++)
				f.readLine();
		}
	}
}