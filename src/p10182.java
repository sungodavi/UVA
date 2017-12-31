import java.util.*;
import java.io.*;

public class p10182 
{
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		while(scan.hasNext())
		{
			solve(scan.nextInt());
		}
	}
	public static void solve(int n)
	{
		int size = 1;
		int count = 1;
		int x = 0, y = 0;
		for(int i = 1; i <= size && count < n; i++, count++)
			y++;
		while(count < n)
		{
			for(int i = 1; i <= size && count < n; i++, count++)
				--x;
			for(int i = 1; i <= size && count < n; i++, count++)
				--y;
			for(int i = 1; i <= size && count < n; i++, count++)
			{
				++x;
				--y;
			}
			for(int i = 1; i <= size && count < n; i++, count++)
				++x;
			for(int i = 1; i <= size +  1 && count < n; i++, count++)
				++y;
			for(int i = 1; i <= size && count < n; i++, count++)
			{
				--x;
				++y;
			}
			++size;
		}
		System.out.println(x + " " + y);
		return;
	}
}
