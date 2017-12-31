import java.util.*;
import java.io.*;

public class p350 //SUBMIT
{
	static int z, i, m;
	
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int k = 1;
		for(String input = f.readLine(); !input.equals("0 0 0 0"); input = f.readLine())
		{
			StringTokenizer st = new StringTokenizer(input);
			z = Integer.parseInt(st.nextToken());
			i = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			System.out.printf("Case %d: %d\n", k++, findCycle(Integer.parseInt(st.nextToken())));
		}
	}
	
	public static int findCycle(int x0)
	{
		int t,h;
		t = h = x0;
		do
		{
			t = f(t);
			h = f(f(h));
		} while(t != h);
		h = f(t);
		int lambda = 1;
		while(t != h)
		{
			h = f(h);
			lambda++;
		}
		return lambda;
	}
	public static int f(int x)
	{
		return (z * x + i) % m;
	}
}
