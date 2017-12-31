import java.util.*;
import java.io.*;

public class p264 
{
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		while(scan.hasNext())
		{
			int num = scan.nextInt();
			int n = 1;
			int sum = 1;
			while(sum + n + 1 < num)
			{
				sum += n + 1;
				n++;
			}
			int top, bottom;
			if(num == 1)
				top = bottom = n;
			else
			{
				top = num - sum;
				bottom = n + 2 - top;
			}
			if((n & 1) == 0)
				System.out.printf("TERM %d IS %d/%d\n", num, bottom, top);
			else
				System.out.printf("TERM %d IS %d/%d\n", num, top, bottom);
		}
	}
}
