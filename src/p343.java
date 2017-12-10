import java.util.*;
import java.io.*;

public class p343 
{
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		outer:
		while(scan.hasNext())
		{
			String n1 = scan.next();
			String n2 = scan.next();
			for(int b1 = 2; b1 <= 36; b1++)
			{
				for(int b2 = 2; b2 <= 36; b2++)
				{
					try
					{
						if(Long.parseLong(n1, b1) == Long.parseLong(n2, b2))
						{
							System.out.printf("%s (base %d) = %s (base %d)\n", n1, b1, n2, b2);
							continue outer;
						}
					}
					catch(Exception e)
					{
						
					}
				}
			}
			System.out.printf("%s is not equal to %s in any base 2..36\n", n1, n2);
		}
	}
}
