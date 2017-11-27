import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class p357 {
	static int[] coins = {1, 5, 10, 25, 50};
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		String input = f.readLine();
		while(input != null && !input.isEmpty())
		{
			int size = Integer.parseInt(input);
			long[] a = new long[size + 1];
			a[0] = 1;
			for(int c : coins)
				for(int i = c; i < a.length; i++)
					a[i] += a[i - c];
			long result = a[size];
			if(result == 1)
				System.out.printf("There is only 1 way to produce %d cents change.\n", size);
			else
				System.out.printf("There are %d ways to produce %d cents change.\n", result, size);
			input = f.readLine();
		}
	}
}
