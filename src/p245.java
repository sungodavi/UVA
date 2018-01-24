import java.util.*;
import java.io.*;

public class p245
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		ArrayList<String> list = new ArrayList<String>();
		for(String input = f.readLine(); !input.equals("0"); input = f.readLine())
		{
			for(int i = 0; i < input.length(); i++)
			{
				if(Character.isDigit(input.charAt(i)))
				{
					StringBuilder s = new StringBuilder();
					for(; i < input.length() && Character.isDigit(input.charAt(i)); i++)
						s.append(input.charAt(i));
				}
			}
		}
	}
}