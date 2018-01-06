import java.util.*;
import java.io.*;

public class p562 {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int times = Integer.parseInt(f.readLine());
		while(times-- > 0)
		{
			int[] a = new int[Integer.parseInt(f.readLine())];
			st = new StringTokenizer(f.readLine());
			int sum = 0;
			for(int i = 0; i < a.length; i++)
				sum += a[i] = Integer.parseInt(st.nextToken());
			
			boolean[] dp = new boolean[(sum >> 1) + 1];
			dp[0] = true;
			int result = 0;
			for(int c : a)
				for(int i = dp.length - 1; i >= c; i--)
					if(dp[i - c])
					{
						dp[i] = true;
						if(i > result)
							result = i;
					}
			out.println(sum - (result << 1));
		}
		out.close();
	}
}