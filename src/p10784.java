import java.util.*;
import java.io.*;

public class p10784
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int caseNo = 0;
		for(long num = Long.parseLong(f.readLine()); num > 0; num = Long.parseLong(f.readLine()))
		{
			double result = (3 + Math.sqrt(8 * num + 9)) / 2;
			System.out.printf("Case %d: %d\n", ++caseNo, (long)(Math.ceil(result)));
		}
	}
}