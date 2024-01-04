package stage4_package;

public class Keyword3
{
	public String name;
	public double weight;

	public Keyword3(String name, double weight)
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
