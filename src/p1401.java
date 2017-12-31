import java.util.*;
import java.io.*;

public class p1401 //RUNTIME ERROR
{
	static final int mod = 20071027;
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int caseNo = 1;
		for(String word = f.readLine(); word != null; word = f.readLine())
		{
			Trie t = new Trie();
			int size = Integer.parseInt(f.readLine());
			for(int i = 0; i < size; i++)
				t.add(f.readLine());
			int[] dp = new int[word.length() + 1];
			dp[dp.length - 1] = 1;
			for(int i = dp.length - 2; i >= 0; i--)
			{
				Node p = t.root;
				for(int j = i; j < dp.length - 1 &&
						p.children[word.charAt(j) - 'a'] != null; 
						p = p.children[word.charAt(j) - 'a'], j++)
				{
					if(p.children[word.charAt(j) - 'a'].end)
						dp[i] = (dp[i] + dp[j + 1]) % mod;
				}
			}
			System.out.printf("Case %d: %d\n", caseNo++, dp[0]);
			f.readLine();
		}
		f.close();
	}
	
	static class Trie
	{
		Node root;
		public Trie()
		{
			root = new Node(' ');
		}
		public void add(String word)
		{
			Node p = root;
			for(int i = 0; i < word.length(); i++)
			{
				int index = word.charAt(i) - 'a';
				if(p.children[index] == null)
					p.children[index] = new Node(word.charAt(i));
				p = p.children[index];
			}
			p.end = true;
		}
	}
	
	static class Node
	{
		Node[] children;
		boolean end;
		char val;
		
		public Node(char c)
		{
			val = c;
			children = new Node[26];
		}
		
		public String toString()
		{
			return "" + val;
		}
	}
	
	public static boolean equals(String word, String piece, int index)
	{
		for(int i = index - piece.length(), j = 0; i < index; i++, j++)
			if(word.charAt(i) != piece.charAt(j))
				return false;
		return true;
	}
}