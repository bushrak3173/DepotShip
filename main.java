import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class main extends JFrame{

	private JPanel jpMain;
    private createBoard jpBoard;
    private Grid myGrid;
	
	public main(int row, int col, int bombs) {
		
		myGrid = new Grid(row,col,bombs);
		
		myGrid.display_bomb();
		myGrid.display_count();
		
		Image icon = Toolkit.getDefaultToolkit().getImage("Image/boom.png");  
		this.setIconImage(icon);  
		
		jpMain = new JPanel();
		jpMain.setLayout(new BorderLayout());
		jpBoard = new createBoard();
		
		jpMain.add(jpBoard,BorderLayout.CENTER);
	    this.add(jpMain);
	    
	    this.setSize(650, 650);
	    this.setTitle("Depot-Ship Game");
	    setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	private class createBoard extends JPanel implements ActionListener{
	
		private JButton [][] board;
		private final int NUM_ROWS = myGrid.getNumRows();
		private final int NUM_COLS = myGrid.getNumColumns();
		int [][] count_grid = myGrid.getCountGrid();
	    int bombNumbers = myGrid.getNumBombs();
		int cell_checked = 0;              
		int cellsWithoutBomb = ( NUM_ROWS * NUM_COLS ) - bombNumbers;
		boolean [][] visited = new boolean[NUM_ROWS][NUM_COLS];
		
		public createBoard (){
			setLayout(new GridLayout(NUM_ROWS,NUM_COLS));
			displayBoard();
		}
		
		public void displayBoard() {
			board = new JButton[NUM_ROWS][NUM_COLS];
			for(int r = 0; r<board.length; r++){
				for(int c = 0; c < board[r].length; c++){
					board[r][c] = new JButton();
					Font bigFont = new Font(Font.SERIF, Font.BOLD, 12);
				    board[r][c].setForeground(Color.BLACK);
					board[r][c].setOpaque(true);
					board[r][c].setBackground(new Color(179, 153, 255));
					board[r][c].setFont(bigFont);
					board[r][c].addActionListener(this);
					board[r][c].setEnabled(true);
					board[r][c].setText("");
					this.add(board[r][c]);
					
				}
			}
			
		}
		
		private  void reveal_content () {
			ImageIcon bombImg = new ImageIcon("Image/boom.png");
			ImageIcon scalebombImg = new ImageIcon(bombImg.getImage().getScaledInstance(45,45, Image.SCALE_FAST));
			
			for(int i = 0 ; i <count_grid.length; i++)
			{
				for(int j = 0; j< count_grid[i].length; j++)
				{
				   if(!myGrid.isBombAtLocation(i, j)) {	
				   board[i][j].setText(Integer.toString(myGrid.getCountAtLocation(i, j)));
				   }
				   else {
					                 
					   board[i][j].setIcon(scalebombImg);//showing bomb 
				   }
				}
			}
			
		}
		
		
		private void reveal_adjacentzeroCell(int r, int c)
		{
	          
			
			if( (r>0) && !(myGrid.isBombAtLocation(r-1, c)))
			{
				
				board[r-1][c].setText(Integer.toString(myGrid.getCountAtLocation(r-1, c)));  //up
				if(board[r-1][c].isEnabled()) {
				cell_checked++;
				board[r-1][c].setEnabled(false);
				
				}
				

			}
			if((r+1 < NUM_ROWS) && !(myGrid.isBombAtLocation(r+1, c)))
			{
				board[r+1][c].setText(Integer.toString(myGrid.getCountAtLocation(r+1, c)));   //down
				if(board[r+1][c].isEnabled()) {
				cell_checked++;
				board[r+1][c].setEnabled(false);
				}
				
				
			}
			if((c+1 <NUM_COLS) && !(myGrid.isBombAtLocation(r, c+1)))
			{
				board[r][c+1].setText(Integer.toString(myGrid.getCountAtLocation(r, c+1))); //right
				if(board[r][c+1].isEnabled()) {
					cell_checked++;
					board[r][c+1].setEnabled(false);
					}
				
				
				
			}
			if((c >0 ) && !(myGrid.isBombAtLocation(r, c-1)) )
			{
				board[r][c-1].setText(Integer.toString(myGrid.getCountAtLocation(r, c-1))); //left
				if(board[r][c-1].isEnabled()) {
					cell_checked++;
					board[r][c-1].setEnabled(false);
					}
				
				
				
			}
			if((r > 0 &&  c>0)&& !(myGrid.isBombAtLocation(r-1, c-1))) 
			{
				board[r-1][c-1].setText(Integer.toString(myGrid.getCountAtLocation(r-1, c-1))); // main dia left corner
				if(board[r-1][c-1].isEnabled()) {
					cell_checked++;
					board[r-1][c-1].setEnabled(false);
					}
				
				
				
				 
			}
			if((r + 1 < NUM_ROWS) &&(c+1 < NUM_COLS) && !(myGrid.isBombAtLocation(r+1, c+1)))
			{
				board[r+1][c+1].setText(Integer.toString(myGrid.getCountAtLocation(r+1, c+1))); //main dia right corner
				if(board[r+1][c+1].isEnabled()) {
					cell_checked++;
					board[r+1][c+1].setEnabled(false);
					}
				
				
				
			}
			if((r + 1 < NUM_ROWS) &&(c > 0) && !(myGrid.isBombAtLocation(r+1, c-1)))
			{
				board[r+1][c-1].setText(Integer.toString(myGrid.getCountAtLocation(r+1, c-1))); //2nd dia left corner
				if(board[r+1][c-1].isEnabled()) {
					cell_checked++;
					board[r+1][c-1].setEnabled(false);
					}
				
				
				
				
			}
			if((r > 0 ) &&(c +1 < NUM_COLS) && !(myGrid.isBombAtLocation(r-1, c+1)))
			{
				board[r-1][c+1].setText(Integer.toString(myGrid.getCountAtLocation(r-1, c+1))); //2nd dia right corner
				if(board[r-1][c+1].isEnabled()) {
					cell_checked++;
					board[r-1][c+1].setEnabled(false);
					}
			
				
				
				
			}
			
	}
	
	public void my_recursive(int r, int c)
	{
		if(r <0 || r >=NUM_ROWS || c<0 || c>=NUM_COLS)
		{
			return ;
		}
		if(myGrid.getCountAtLocation(r, c) != 0)
		{
		    return ;
		}
		if(visited[r][c] == true)
		{
			return ;
		}
		
			
		       visited[r][c] = true;
		   
			   board[r][c].setText(Integer.toString(myGrid.getCountAtLocation(r, c)));   
			   if(board[r][c].isEnabled()) {
				cell_checked++;
				board[r][c].setEnabled(false);
				}
			 
			      my_recursive(r-1, c); 
			      my_recursive(r+1, c); 
			      my_recursive(r, c-1); 
				  my_recursive(r-1, c-1); 
				  my_recursive(r+1,c+1);  
				  my_recursive(r+1,c-1);  
				  my_recursive(r-1, c+1); 
				  
			
				this.reveal_adjacentzeroCell(r, c);

		}

		
		public void clearBoard() {
			
			cell_checked = 0;
			for(int row=0; row<board.length; row++){
				for(int col=0; col < board[row].length; col++){
					board[row][col].setOpaque(true);
					board[row][col].setBackground(new Color(0,255,127));
					board[row][col].setText("");
					board[row][col].setEnabled(true);
					board[row][col].setIcon(null);
					visited[row][col] = false;
					
				}
			}
			
		}
		
		
		private void promptAgain(){
			ImageIcon askImg = new ImageIcon("Image/next.png");
			ImageIcon scaleaskImg = new ImageIcon(askImg.getImage().getScaledInstance(80,80, Image.SCALE_FAST));

			int yesNo = JOptionPane.showConfirmDialog(null, "Play Again?", "Yes or No", JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE, scaleaskImg);
           
			if(yesNo == JOptionPane.YES_OPTION){ 
			clearBoard();
			}
			else{   
				
	             System.exit(EXIT_ON_CLOSE);
			}
		}
		
		
		
		
		
		
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			JButton btnclk = (JButton) e.getSource();
			ImageIcon happyImg = new ImageIcon("Image/happy_1.png");
			ImageIcon scalehappyImg = new ImageIcon(happyImg.getImage().getScaledInstance(80,80, Image.SCALE_FAST));
			
			ImageIcon sadImg = new ImageIcon("Image/boom.png");
			ImageIcon scalesadImg = new ImageIcon(sadImg.getImage().getScaledInstance(80,80, Image.SCALE_FAST));
			
			
			

	            int row = 0, col =0;
	            for(int r =0; r<board.length; r++ )
	            {
	            	for(int c=0; c<board[r].length; c++)
	            	{
	            		if(board[r][c] == btnclk)
	            		{
	            			row = r;
	            			col = c;
	            			break;
	            		}
	            	}
	            }
			    if(myGrid.isBombAtLocation(row, col))         //HAS BOMB
			    {
			    	reveal_content();
			    	
			    	
			    	JOptionPane.showMessageDialog(
                            null,
                            "Game Over",
                            "Game Over", JOptionPane.INFORMATION_MESSAGE,
                            scalesadImg);
			    	
			    	promptAgain();
			    }	
			    else if(!myGrid.isBombAtLocation(row, col))            
	            {
			    	if(myGrid.getCountAtLocation(row, col) != 0) {
			    	this.board[row][col].setText(Integer.toString(myGrid.getCountAtLocation(row, col)));
			    	
			    	if(board[row][col].isEnabled()) {         
				    	cell_checked++;
				    	board[row][col].setEnabled(false);
				    	
			    	}
			    	}  
			    	else { 
			    	this.my_recursive(row, col);    
			    	}
	                if(cell_checked == this.cellsWithoutBomb)
			    	{
	                	 JOptionPane.showMessageDialog(
	                             null,
	                             "Game has been won",
	                             "Winner", JOptionPane.INFORMATION_MESSAGE,
	                             scalehappyImg);
			    		
			    		promptAgain();
			    	}
	            	
	            	
	            }
			    
			
		}
		
	}
	


}