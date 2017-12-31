import java.util.*;
import java.io.*;

public class p382 
{
	public static void main(String[] args) throws IOException
	{
		Scanner scan = new Scanner(System.in);
		System.out.println("PERFECTION OUTPUT");
		for(int num = scan.nextInt(); num > 0; num = scan.nextInt())
		{
			int sum = 1;
			if(num == 1)
				sum = 0;
			else
				for(int i = 2; i * i <= num; i++)
				{
					if(num % i == 0)
					{
						int k = num / i;
						sum += i;
						if(i != k)
							sum += k;
					}
				}
			System.out.printf("%5d  ", num);
			if(sum == num)
				System.out.println("PERFECT");
			else if(sum < num)
				System.out.println("DEFICIENT");
			else
				System.out.println("ABUNDANT");
		}
		System.out.println("END OF OUTPUT");
	}
}
