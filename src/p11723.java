import java.util.*;
import java.io.*;

public class p11723 
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int k = 1;
		for(String input = f.readLine(); !input.equals("0 0"); input = f.readLine())
		{
			StringTokenizer st = new StringTokenizer(input);
			int n = Integer.parseInt(st.nextToken());
			int letters = Integer.parseInt(st.nextToken());
			int result = n / letters - (n % letters == 0 ? 1 : 0);
			System.out.printf("Case %d: ", k++);
			if(result > 26)
				System.out.println("impossible");
			else
				System.out.println(result);
		}
	}
}
