import java.util.*;
import java.io.*;

public class p679 {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int times = Integer.parseInt(f.readLine());
		while(times-- > 0)
		{
			st = new StringTokenizer(f.readLine());
			int depth = Integer.parseInt(st.nextToken());
			int index = Integer.parseInt(st.nextToken());
			int result = 1;
			for(int i = 1; i < depth; i++)
			{
				result <<= 1;
				if(index != 0 && (index & 1) == 0)
					result++;
				index = (index + 1) >> 1;
			}
			out.println(result);
		}
		out.close();
	}
}