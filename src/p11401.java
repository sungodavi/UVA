import java.util.*;
import java.io.*;

public class p11401
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		for(int n = Integer.parseInt(f.readLine()); n >= 3; n = Integer.parseInt(f.readLine()))
		{
			long result = 0;
			for(long i = n - 2; i >= 2; i -= 2)
			{
				result += i * (i - 1) >> 1;
			}
			System.out.println(result);
		}
	}
}