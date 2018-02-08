public class ExtendedEuclid
{
	static int x, y;
	public int extendedEuclid(int a, int b)
	{
		if(b == 0)
		{
			x = 0;
			y = 1;
			return a;
		}
		int d = extendedEuclid(b, a % b);
		int x1 = y;
		int y1 = x - (a / b) * y;
		x = x1;
		y = y1;
		return d;
	}
}