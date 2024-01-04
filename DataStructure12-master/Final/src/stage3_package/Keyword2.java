package stage3_package;

public class Keyword2
{
	public String name;
	public double weight;

	public Keyword2(String name, double weight)
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
