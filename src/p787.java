import java.util.*;
import java.io.*;
import java.math.*;

public class p787 {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		String input = f.readLine();
		//System.setOut(new PrintStream("output.txt"));
		while(input != null && !input.isEmpty())
		{
			StringTokenizer st = new StringTokenizer(input);
			long num;
			BigInteger current = BigInteger.ONE;
			num = Integer.parseInt(st.nextToken());
			BigInteger best = BigInteger.valueOf(num);
			long firstNeg = 0, lastNeg = 0, countNeg = 0;
			BigInteger beforeNeg = null, afterNeg = BigInteger.ONE;
			int numCount = 0;
			boolean flag = true;
			while(num != -999999)
			{
				if(num == 0)
				{
					best = best.max(BigInteger.ZERO);
					if(!flag)
						best = best.max(find(current, beforeNeg, afterNeg, firstNeg, lastNeg, countNeg, numCount));
					current = BigInteger.ONE;
					flag = true;
					firstNeg = lastNeg = countNeg = numCount = 0;
					beforeNeg = null;
					afterNeg = BigInteger.ONE;
				}
				else
				{
					flag = false;
					current = current.multiply(BigInteger.valueOf(num));
					numCount++;
					if(num < 0)
					{
						if(firstNeg == 0)
						{
							firstNeg = lastNeg = num;
							beforeNeg = current.divide(BigInteger.valueOf(num));
						}
						else
						{
							lastNeg = num;
							afterNeg = BigInteger.ONE;
						}
						countNeg++;
					}
					else if(firstNeg != 0)
						afterNeg = afterNeg.multiply(BigInteger.valueOf(num));
				}
				num = Integer.parseInt(st.nextToken());
			}
			if(flag)
				System.out.println(best);
			else
				System.out.println(best.max(find(current, beforeNeg, afterNeg, firstNeg, lastNeg, countNeg, numCount)));
			input = f.readLine();
		}
	}
	
	public static BigInteger find(BigInteger current, BigInteger beforeNeg, BigInteger afterNeg, long firstNeg, long lastNeg, long countNeg, int numCount)
	{
		//System.out.println(current + " " + firstNeg + " " + lastNeg + " " + countNeg + " " + numCount);
		//System.out.println(beforeNeg + " " + afterNeg);
		if(numCount == 1 || countNeg % 2 == 0)
			return current;
		BigInteger d = beforeNeg.multiply(BigInteger.valueOf(firstNeg)).max(afterNeg.multiply(BigInteger.valueOf(lastNeg)));
		//System.out.println(d);
		return current.divide(d);

	}
}