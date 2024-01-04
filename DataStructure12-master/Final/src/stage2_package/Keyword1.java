package stage2_package;

public class Keyword1
{
	public String name;
	public double weight;

	public Keyword1(String name, double weight)
	{
		this.name = name;
		this.weight = weight;
	}

	@Override
	public String toString()
	{
		return "[" + name + "," + weight + "]";
	}
}
