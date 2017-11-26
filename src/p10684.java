import java.util.Scanner;

public class p10684 
{
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		int size = scan.nextInt();
		while(size > 0)
		{
			int best = 0;
			int sum = 0;
			for(int i = 0; i < size; i++)
			{
				sum += scan.nextInt();
				if(sum < 0)
					sum = 0;
				else
					best = Math.max(best, sum);
			}
			if(best == 0)
			{
				System.out.println("Losing streak.");
			}
			else
				System.out.printf("The maximum winning streak is %d.\n", best);
			size = scan.nextInt();
		}
	}
}