import java.util.*;
import java.io.*;

public class p11597
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		int caseNo = 0;
		for(int num = Integer.parseInt(f.readLine()); num > 0; num = Integer.parseInt(f.readLine()))
		{
			out.printf("Case %d: %d\n",++caseNo, num >> 1);
		}
		out.close();
	}
}