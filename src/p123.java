import java.util.*;
import java.io.*;

public class p123 //BUGGED
{
	static TreeSet<String> ignored = new TreeSet<String>();
	
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		for(String s = f.readLine(); !s.equals("::"); s = f.readLine())
			ignored.add(s);
		ArrayList<Sentence> list = new ArrayList<Sentence>();
		for(String input = f.readLine(); input != null; input = f.readLine())
		{
			String[] a = input.toLowerCase().split(" ");
			for(int i = 0; i < a.length; i++)
			{
				String word = a[i];
				if(!ignored.contains(a[i]))
				{
					a[i] = word.toUpperCase();
					list.add(new Sentence(word, a));
					a[i] = word;
				}
			}
		}
		Collections.sort(list);
		for(Sentence s : list)
			System.out.println(s);
	}
	
	static class Sentence implements Comparable<Sentence>
	{
		String kw;
		StringBuilder s;
		public Sentence(String kw, String[] a)
		{
			this.kw = kw;
			s = new StringBuilder(a[0]);
			for(int i = 1; i < a.length; i++)
			{
				s.append(" ");
				s.append(a[i]);
			}
		}
		
		public int compareTo(Sentence sent)
		{
			return kw.compareTo(sent.kw);
		}
		
		public String toString()
		{
			return s.toString();
		}
	}
}
