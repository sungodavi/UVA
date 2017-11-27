import java.util.*;
import java.io.*;

public class p10878 {
	public static void main(String[] args) throws IOException 
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		f.readLine();
		for(String input = f.readLine(); input.charAt(0) != '_'; input = f.readLine())
		{
			int num = 0;
			for(int i = 1; i < input.length() - 1; i++)
			{
				if(input.charAt(i) == '.')
					continue;
				num <<= 1;
				if(input.charAt(i) == 'o')
					num += 1;
			}
			System.out.print((char)num);
		}
	}
}