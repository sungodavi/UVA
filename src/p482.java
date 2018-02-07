import java.util.*;
import java.io.*;

public class p482 {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int times = Integer.parseInt(f.readLine());
		for(int t = 0; t < times; t++)
		{
			if(t > 0)
				out.println();
			f.readLine();
			st = new StringTokenizer(f.readLine());
			int[] a = new int[st.countTokens()];
			for(int i = 0; i < a.length; i++)
				a[Integer.parseInt(st.nextToken()) - 1] = i;
			String[] items = f.readLine().split(" ");
			for(int i = 0; i < a.length; i++)
				out.println(items[a[i]]);
		}
		out.close();
	}
}