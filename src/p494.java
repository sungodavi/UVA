import java.util.*;
import java.io.*;

public class p494 {
	public static void main(String[] args) throws IOException 
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		for(String input = f.readLine(); input != null && !input.isEmpty(); input = f.readLine())
		{
			System.out.println(input.replaceAll("[^a-zA-Z]+", " ").trim().split(" ").length);
		}
	}
}