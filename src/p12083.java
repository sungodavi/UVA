import java.util.*;
import java.io.*;

public class p12083 
{
	static LinkedList<Integer>[] list;
	static boolean[] visited;
	static int[] match;
	public static void main(String[] args) throws IOException 
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int times = Integer.parseInt(f.readLine());
		while(times-- > 0)
		{
			int size = Integer.parseInt(f.readLine());
			list = new LinkedList[size];
			Student[] student = new Student[size];
			for(int i = 0; i < size; i++)
			{
				st = new StringTokenizer(f.readLine());
				int h = Integer.parseInt(st.nextToken());
				String g = st.nextToken();
				String m = st.nextToken();
				String s = st.nextToken();
				student[i] = new Student(h, g, m, s);
				list[i] = new LinkedList<Integer>();
			}
			for(int i = 0; i < size - 1; i++)
				for(int j = i + 1; j < size; j++)
					if(student[i].canDate(student[j]))
					{
						if(student[i].male)
							list[i].add(j);
						else
							list[j].add(i);
					}
			out.println(mis());
		}
		out.close();
	}
	
	public static int mis()
	{
		int count = 0;
		match = new int[list.length];
		Arrays.fill(match, -1);
		for(int u = 0; u < list.length; u++)
		{
			visited = new boolean[list.length];
			if(augment(u))
				count++;
		}
		return list.length - count;
	}
	
	public static boolean augment(int u)
	{
		if(visited[u])
			return false;
		visited[u] = true;
		for(int v : list[u])
		{
			if(match[v] == -1 || augment(match[v]))
			{
				match[v] = u;
				return true;
			}
		}
		return false;
	}
	
	static class Student
	{
		int height;
		boolean male;
		String music, sport;
		public Student(int height, String gender, String music, String sport)
		{
			this.height = height;
			this.male = gender.equals("M") ? true : false;
			this.music = music;
			this.sport = sport;
		}
		
		public boolean canDate(Student s)
		{
			return Math.abs(height - s.height) <= 40 && male != s.male && music.equals(s.music) && !sport.equals(s.sport);
		}
	}
}