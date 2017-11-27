import java.util.*;
import java.io.*;

public class p10851 {
	public static void main(String[] args) throws IOException 
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int times = Integer.parseInt(f.readLine());
		while(times-- > 0)
		{
			char[][] a = new char[10][];
			for(int r =0 ; r < a.length; r++)
				a[r] = f.readLine().toCharArray();
			
			char[] result = new char[a[0].length - 2];
			for(int c = 1; c < a[0].length - 1; c++)
			{
				int num = 0;
				for(int r = a.length - 2; r > 0; r--)
				{
					num = (num << 1) + (a[r][c] == '/' ? 0 : 1);
				}
				//System.out.println(num);
				result[c - 1] = (char)num;
			}
			System.out.println(new String(result));
			f.readLine();
		}
	}
}