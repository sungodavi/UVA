import java.util.*;
import java.io.*;

public class p124 
{
    static LinkedList<Integer>[] list;
    static int OK;
    static char[] indexes;
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        boolean flag = false;
        for(String input = f.readLine(); input != null; input = f.readLine())
        {
            if(flag)
                System.out.println();
            else
                flag = true;
            st = new StringTokenizer(input);
            int size = 0;
            indexes = new char[26];
            int[] map = new int[26];
            ArrayList<Character> inputList = new ArrayList<Character>();
            while(st.hasMoreTokens())
                inputList.add(st.nextToken().charAt(0));
            Collections.sort(inputList);
            for(char c : inputList)
            {
                map[c - 'a'] = size;
                indexes[size++] = c;
            }
            list = new LinkedList[size];
            for(int i = 0; i < size; i++)
                list[i] = new LinkedList<Integer>();
            OK = (1 << size) - 1;
            st = new StringTokenizer(f.readLine());
            while(st.hasMoreTokens())
            {
                char u = st.nextToken().charAt(0);
                char v = st.nextToken().charAt(0);
                list[map[u - 'a']].add(map[v - 'a']);
            }
            recurse(new ArrayList<Integer>(), 0);
        }
    }
    
    public static void recurse(ArrayList<Integer> result, int mask)
    {
        if(mask == OK)
        {
            for(int num : result)
                System.out.print(indexes[num]);
            System.out.println();
            return;
        }
        for(int i = 0; i < list.length; i++)
        {
            int flag = 1 << i;
            if((flag & mask) == 0 && !canReach(i, mask, new boolean[list.length]))
            {
                result.add(i);
                recurse(result, mask | flag);
                result.remove(result.size() - 1);
            }
        }
    }
    
    public static boolean canReach(int u, int mask, boolean[] visited)
    {
        if(visited[u])
            return false;
        visited[u] = true;
        if(((1 << u) & mask) != 0)
            return true;
        if(list[u] != null)
        {
            for(int v : list[u])
                if(canReach(v, mask, visited))
                    return true;
        }
        return false;
    }
}