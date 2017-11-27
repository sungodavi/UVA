import java.util.*;
import java.io.*;

public class p325
{
	public static void main(String[] args) throws IOException 
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		String regex = "[+-]?\\d+(\\.\\d+([eE][-+]?\\d+)?|[eE][-+]?\\d+)";
		for(String input = f.readLine(); !input.equals("*"); input = f.readLine())
		{
			input = input.trim();
			if(input.matches(regex))
				System.out.println(input + " is legal.");
			else
				System.out.println(input + " is illegal.");
		}
	}
}