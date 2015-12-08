/**
 * SudokuSolver class
 *
 * @author  Jessica Li
 * @version 12/05/15
 */ 
public class SudokuSolver
{
	private Stack<SudokuBoard> states;
	
	/**
	 * SudokuSolver constructor. Initializes states, creates a new SudokuBoard base, which holds the initial Sudoku problem, and adds base to states. 
	 */ 
	public SudokuSolver()
	{
		//what goes here?
		states = new LinkedList<SudokuBoard>();
		SudokuBoard base = new SudokuBoard(); 
		states.push( base );
	}

	/**
	 * Solves the Sudoku problem. 
	 * Pops a board off states. If the board is solved, returns board. Otherwise, calls/finds getMostConstrainedSpot(), setting to array cspot. 
	 * For loop tests values 1-10, seeing if they can be placed at location cspot by calling canPlace(). If yes, creates a new SudokuBoard, 
	 * adds a possible value to the new board, and pushes new board onto states. 
	 * This all is placed inside a forever true while loop so it will continue until the board is solved. 
	 */ 
	public SudokuBoard solve()
	{
		while ( true )
		{
			SudokuBoard board = states.pop();
			if ( board.solved() )
				return board;
			int[] cspot = board.getMostConstrained(); 
			//System.out.println( cspot[0]);
			//System.out.println(cspot[1]);
			//System.out.println( board.numPossibilities( 5, 7) );
			for ( int i = 1; i < 10; i++ )
			{
				if ( board.canPlace( cspot[0], cspot[1], i ) )
				{
					SudokuBoard nextBoard = new SudokuBoard( board ); 
					nextBoard.place( cspot[0], cspot[1], i );
					states.push( nextBoard );
				}
			}
			//why is the recursve version not working???
			//why do i not need to have another return statement with iteration?
			//solve(); 
			//return board;
		}
	}


	/**
	 * Prints the board on the top of Stack states. 
	 */ 
	public void print()
	{
		System.out.println( states.peek() );
	}

}