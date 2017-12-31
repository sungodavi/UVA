import java.util.*;
import java.io.*;
import java.awt.Point;
public class p10233 
{
	public static Point convert(int num)
	{
		int r = (int)Math.sqrt(num);
		int c = num - r * r - r;
		return new Point(++r, c);
	}
	public static double dist(Point p1, Point p2)
	{
		double dx = (p1.x - p2.x) * Math.sqrt(3) / 2;;
		double dy = p1.y - p2.y;
		return Math.sqrt(dx * dx + dy * dy);
	}
}
