import java.util.*;
import java.io.*;

public class p1238 //UNSOLVED
{
	public static boolean[][][] visited; //index, open, val
	public static void main(String[] args) throws IOException 
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		for(String input = f.readLine(); input != null; input = f.readLine()) 
		{
			String[] temp = input.split(" ");
			boolean[] neg = new boolean[temp.length >> 1];
			int[] a = new int[neg.length + 1];
			int sum = 0;
			for(int i = 0; i < a.length; i++)
			{
				a[i] = Integer.parseInt(temp[2 * i]);
				neg[i] = temp[2 * i + 1].charAt(0) == '-' ? true : false;
			}
			a[a.length - 1] = Integer.parseInt(temp[temp.length - 1]);
		}
		
	}
}