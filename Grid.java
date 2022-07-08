import java.util.Random;


public class Grid {
	private boolean [][] bombGrid;
	private int [][] countGrid;
	private int numRows, numColumns;
	private int numBombs;
	
	
	public Grid()
	{
		numRows = 10;
		numColumns = 10;
		bombGrid = new boolean[numRows][numColumns];
		countGrid = new int[numRows][numColumns];
		numBombs = 25;
		this.createBombGrid();
		this.createCountGrid();
	}
	public Grid(int rows, int columns)
	{
		numRows = rows;
		numColumns = columns;
		bombGrid = new boolean[rows][columns];
		countGrid = new int[rows][columns];
		numBombs = 25;
		this.createBombGrid();
		this.createCountGrid();
	}
	public Grid(int rows, int columns, int numBombs) 
	{
		numRows = rows;
		numColumns = columns;
		bombGrid = new boolean[rows][columns];
		countGrid = new int[rows][columns];
		this.numBombs = numBombs;
		this.createBombGrid();
		this.createCountGrid();
	}
	
	public int getNumRows() {
		return this.numRows;
	}
	public int getNumColumns() {
		return this.numColumns;
	}
	public int getNumBombs() {
		return this.numBombs;
	}
	public boolean [][] getBombGrid() {
		boolean [][] mybombGrid = new boolean[numRows][numColumns];
		for(int i = 0; i<bombGrid.length; i++)
		{
			for(int j=0; j<bombGrid[i].length; j++)
			{
				mybombGrid[i][j] = bombGrid[i][j];
			}
	}
				
	 return mybombGrid;
	}
	public int [][] getCountGrid(){
		int [][] mycountGrid = new int[numRows][numColumns];
		for(int i = 0; i<countGrid.length; i++)
		{
			for(int j=0; j<countGrid[i].length; j++)
			{
				mycountGrid[i][j] = countGrid[i][j];
			}
	}
				
	
		return mycountGrid;
	}
	public boolean isBombAtLocation(int row, int column) {
		if(bombGrid[row][column] == true)
		{
			return true;
		}
		return false;
	}
	
	public int getCountAtLocation(int row, int column) {
		int count = countGrid[row][column];
	    return count;
	}
	
	private void createBombGrid() {          
		for(int i = 0; i<bombGrid.length; i++)
		{
			for(int j=0; j<bombGrid[i].length; j++)
			{
				bombGrid[i][j] = false;
				
			}
		}
		
		int bombNumbers = this.numBombs;
		while(bombNumbers > 0) {
				
					Random rand_num1 = new Random();
					Random rand_num2 = new Random();
					int row = rand_num1.nextInt(this.numRows);
					int col = rand_num2.nextInt(this.numColumns);
					
					if(bombGrid[row][col] == false && bombNumbers > 0)
					{
						bombGrid[row][col] = true;
						bombNumbers--;
					}
	}
			
}
	
  private void createCountGrid() 
  {
		int count =0;
	
	for(int rows =0; rows< numRows; rows++)
	{
	  for(int cols = 0 ; cols<numColumns; cols++)
	   {

		  if(bombGrid[rows][cols] == true)       
		{  
			count++;

		}
		if( (rows >0) && bombGrid[rows-1][cols] == true)        
		{
			
			count++;
			
		}
	    if(((rows+1 ) <numRows )&&bombGrid[rows+1][cols] == true)                 
	    {
	    	
	    	count++;
			
	    }
		if(((cols+1) < numColumns) && bombGrid[rows][cols+1] == true)   
		{
			
			count++;
			
		}
		if((cols > 0) && bombGrid[rows][cols-1] == true)
		{
			
			count++;
			
		}
		
		
		if((rows > 0 && cols > 0) && bombGrid[rows-1][cols-1] == true) 
		{
			
			count++;
			
		}
		
		if(((rows + 1) < numRows ) && ((cols + 1) < numColumns ) && bombGrid[rows+1][cols+1] == true) 
		{
			count++;
			
		}
		
		if( ((rows + 1) < numRows ) && (cols > 0 ) && bombGrid[rows+1][cols-1] == true) 
		{
			count++;
			
		}
		if((rows > 0  ) && ((cols + 1) < numColumns ) && bombGrid[rows-1][cols+1] == true) 
		{
			count++;
			
		}
		
	     countGrid[rows][cols] = count;
	     count = 0;
	     
	     
		}
	}
			
			
			
 }
		
		
		
		
	
  public void display_bomb() {              
		for(int i = 0 ; i <bombGrid.length; i++)
		{
			for(int j=0; j<bombGrid[i].length; j++)
			{
				if(bombGrid[i][j] == true) {
				System.out.print("T"+" ");
				}
				else
				{
					System.out.print("F"+" ");
				}
			}
			System.out.println();
		}
	}
	
	public void display_count() {             
		for(int i = 0 ; i <countGrid.length; i++)
		{
			for(int j=0; j<countGrid[i].length; j++)
			{
				System.out.print(countGrid[i][j]+"  ");
			}
			System.out.println();
		}
	}
}