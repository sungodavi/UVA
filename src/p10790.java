import java.util.*;
import java.io.*;
public class p10790
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		StringTokenizer st;
		int caseNo = 0;
		for(String input = f.readLine();; input = f.readLine())
		{
			st = new StringTokenizer(input);
			int t = Integer.parseInt(st.nextToken());
			if(t == 0)
				break;
			int b = Integer.parseInt(st.nextToken());
			if(t > b)
			{
				int temp = t;
				t = b;
				b = temp;
			}
			long result = (long)b * (b - 1) / 2 * t * (t - 1) / 2;
			System.out.printf("Case %d: %d\n", ++caseNo, result);
		}
	}
}