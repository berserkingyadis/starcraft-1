package model;

public class TestCell
{
	private int unit;
	
	public TestCell()
	{
		unit = 0;
	}
	public TestCell(int unit)
	{
		super();
		this.unit = unit;
	}

	public int getUnit()
	{
		return unit;
	}

	public void setUnit(int unit)
	{
		this.unit = unit;
	}
	public String toString()
	{
		if(unit == 0)
			return " ";
		else
			return new Integer(unit).toString();
	}
	
}

