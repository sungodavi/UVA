import java.util.*;
import java.io.*;

public class p11900 {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int times = Integer.parseInt(f.readLine());
		int caseNo = 0;
		while(times-- > 0)
		{
			st = new StringTokenizer(f.readLine());
			int[] a = new int[Integer.parseInt(st.nextToken())];
			int count = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(f.readLine());
			int curr = 0;
			int i;
			for(i = 0; i < count && st.hasMoreTokens(); i++)
			{
				int egg = Integer.parseInt(st.nextToken());
				if(curr + egg > w)
					break;
				curr += egg;
			}
			out.printf("Case %d: %d\n", ++caseNo, i);
		}
		out.close();
	}
}