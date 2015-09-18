package exceptions;

public abstract class CellException extends Exception
{
	private static final long serialVersionUID = 1L;
	private int cellX;
	private int cellY;
	private String message;
	
	public CellException()
	{
		
	}
	public CellException(int x, int y)
	{
		cellX = x;
		cellY = y;
		message = "";
	}
	public CellException(int cellX, int cellY, String message)
	{
		super();
		this.cellX = cellX;
		this.cellY = cellY;
		this.message = message;
	}
	public CellException(String mes)
	{
		message = mes;
	}
	public int getCellX()
	{
		return cellX;
	}
	public int getCellY()
	{
		return cellY;
	}
	public String getMessage()
	{
		return message;
	}
	
}