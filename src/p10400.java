import java.util.*;
import java.io.*;

public class p10400
{
	static int[] a;
	static int[] result;
	static int target;
	static final int LIMIT = 32000;
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int times = Integer.parseInt(f.readLine());
		
		while(times-- > 0)
		{
			st = new StringTokenizer(f.readLine());
			a = new int[Integer.parseInt(st.nextToken())];
			for(int i = 0; i < a.length; i++)
				a[i] = Integer.parseInt(st.nextToken());
			target = Integer.parseInt(st.nextToken());
			result = new int[a.length - 1];
			if(recurse(1, a[0]))
				System.out.println(build());
			else
				System.out.println("NO EXPRESSION");
		}
	}
	
	public static StringBuilder build()
	{
		StringBuilder s = new StringBuilder();
		s.append(a[0]);
		for(int i = 1; i < a.length; i++)
		{
			switch(result[i - 1])
			{
			case 1: s.append('+'); break;
			case 2: s.append('-'); break;
			case 3: s.append('*'); break;
			case 4: s.append('/'); break;
			}
			s.append(a[i]);
		}
		s.append("=" +  target);
		return s;
	}
	public static boolean recurse(int index, int curr)
	{
		if(index == a.length)
			return curr == target;
		if(curr < -LIMIT || curr > LIMIT)
			return false;
		result[index - 1] = 1;
		if(recurse(index + 1, curr + a[index]))
			return true;
		result[index - 1] = 2;
		if(recurse(index + 1, curr - a[index]))
			return true;
		result[index - 1] = 3;
		if(recurse(index + 1, curr * a[index]))
			return true;
		if(curr % a[index] == 0)
		{
			result[index - 1] = 4;
			if(recurse(index + 1, curr / a[index]))
				return true;
		}
		result[index - 1] = 0;
		return false;
	}
}