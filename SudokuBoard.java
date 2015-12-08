import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


/**
 * SudokuBoard class
 *
 * @author Jessica Li
 * @version 12/05/15
 */ 
public class SudokuBoard
{
	private int[][] board;

	/** 
	 * Constructor for SudokuBoard. Initializes board and reads CSV file by calling read(). 
	 * Therefore, the data in the CSV are transferred to board. 
	 */ 
	public SudokuBoard()
	{
		board = new int[9][9];
		read(); 

	}

	/** 
	 * Copy Constructor. Takes in a SudokuBoard and copies its values into board using a nested for loop. 
	 * 
	 * @param b 	the SudokuBoard being copied
	 */ 
	public SudokuBoard( SudokuBoard b )
	{
		board = new int[9][9];
		for ( int i = 0; i < 9; i++ )
		{
			for ( int j = 0; j < 9; j++ )
			{
				board[i][j] = b.get( i, j );
			}
		}
	}

	/** 
	 * Reads the CSV file and copies contents into board. CSV file must be names SudokuProblem. 
	 * Copies contents from individual line to String[] of the line using the .split() operator. 
	 * For loop copies lines into board. If String length is 0, puts 0 in board, which represents an empty space. Otherwise, calls parseInt() 
	 * on the value and adds to corresponding spot in board. Throws exception if not a value between 1 and 9. 
	 */ 
	public void read()
	{
		String pathname = "SudokuProblem.csv";
		File file = new File(pathname);	
		Scanner input = null;
		try
		{
			input = new Scanner(file);
		}
		catch (FileNotFoundException ex)
		{
			System.out.println(" Cannot open " + pathname );
			System.exit(1);
		}
		
		//index counts rows of board, is incremented after each iteration of while loop.
		int index = 0; 
		while( input.hasNextLine() )
		{
			String line = input.nextLine();
			String[] lineArray = line.split( "," );
			for ( int i = 0; i < lineArray.length; i++ )
			{
				//I test for empty string instead of dash because making a CSV with empty spaces is easier. 
				if ( lineArray[i].length() == 0 )
					board[index][i] = 0; 
				else
				{
					if ( Integer.parseInt( lineArray[i] ) > 9 || Integer.parseInt( lineArray[i] ) < 1 )
						throw new IndexOutOfBoundsException( "The values in the board must be between 1 and 9. " );
					else
						board[index][i] = Integer.parseInt( lineArray[i] ); 
				}
			}
			index++;
		}
	}


	/**
	 * Places numeral n at position (r,c)
	 *
	 * @param r 	row number
	 * @param c 	column number
	 * @param n 	inserted numeral
	 */
	public void place( int r, int c, int n )
	{
		board[r][c] = n;
	}

	/** 
	 * Return the numeral at position (r,c). 
	 * 
	 * @param r 	row number
	 * @param c 	column number
	 * @return 		the value are (r,c)
	 */ 
	public int get( int r, int c )
	{
		return board[r][c];
	}

	/**
	 * Remove the numeral at position (r,c). Sets spot to 0. 
	 *
	 * @param r 	row number
	 * @param c 	column number
	 */ 
	public void remove( int r, int c)
	{
		board[r][c] = 0; 
	}

	/**
	 * True if the board would allow placing n at (r,c). 
	 * Tests rows and columns for the same number within one for loop, returns false if same. Creates variables testRow and testCol for testing the square. 
	 * Divides row and col number by 3 to find which section of board it is in. For loop tests the square, returning false if the value is the same. 
	 * Otherwise, returns true. 
	 *
	 * @param r 	row number
	 * @param c 	column number
	 * @param n 	number being tested for if it can be placed at (r, n)
	 * @return 		true if no values in row, column or square are the same as n; false otherwise
	 */ 
	public boolean canPlace( int r, int c, int n)
	{
		//do I need to take care of it the spot is already filled? Won't ever happen, right?

		//test row and column
		for ( int i = 0; i < 9; i++ )
		{
			if ( board[r][i] == n )
				return false;
			if ( board[i][c] == n )
				return false;
		}


		//test square
		//insists that i initialize these
		int testRow = 999;
		int testCol = 999;
		if ( r/3 == 0 )
			testRow = 0;
		if ( r/3 == 1 )
			testRow = 3;
		if ( r/3 == 2 )
			testRow = 6;

		if ( c/3 == 0 )
			testCol = 0;
		if ( c/3 == 1 )
			testCol = 3;
		if ( c/3 == 2 )
			testCol = 6;

		for ( int i = testRow; i < testRow+3; i++ )
		{
			for ( int j = testCol; j < testCol+3; j++ )
			{
				//System.out.println( "" + i + " " + j ); 		//for testing
				if ( board[i][j] == n )
				{
					//System.out.println( "problem: " + i + " " + j ); 			//for testing
					return false;
				}
			}
		}

		return true;
	}

	/**
	 * Gets the spot in board with the least amount of values that can possibly be placed there. 
	 * Creates int[] rowCol to hold row + col numbers of most constrained spot. Sets minPossibilities to big number (as long as it is >9). 
	 * Within nested for loop, tests if a space in board is empty. If so, calls numPossibilities and if numPossibilities is less than 
	 * minPossibilities, sets to new minPossibilities and sets new values for rowCol. Returns rowCol. 
	 * 
	 * @return 		an int[] holding the row and column number of the most constrained spot
	 */ 
	public int[] getMostConstrained()
	{
		//holds the position of the mostConstrained
		int[] rowCol = new int[2];
		int minPossibilities = 10;

		for ( int i = 0; i < 9; i++ )
		{
			for ( int j = 0; j < 9; j++ )
			{
				//ensures the space is empty
				if ( board[i][j] == 0 )
				{

					int np = numPossibilities( i, j );
					if ( np < minPossibilities )
					{
						minPossibilities = np;
						rowCol[0] = i;
						rowCol[1] = j; 
					}


				}
			}
		}

		return rowCol;

	}

	/**
	 * Finds the number of possible values that could go in a location of board. 
	 * Variable count holds number of possible values. For loop goes from 1-10, testing location with canPlace() for each number. 
	 * If canPlace() returns true, increments count. Returns count. 
	 *
	 * @param r 	row number
	 * @param c 	column number
	 * @return  	number of possible values for (r, c)
	 */ 
	public int numPossibilities( int r, int c )
	{
		int count = 0; 
		for ( int i = 1; i < 10; i++ )
		{
			if ( canPlace( r, c, i ) )
			{
				//System.out.println( "* " + i );
				count++; 
			}
		}
		return count;
	}

	/**
	 * Tests if board is solved by finding no blank spots on the board. 
	 * Nested foreach loop tests each value of board, returning false if the value equals 0, representing an empty spot. 
	 * If loop ends without returning false, returns true. 
	 *
	 * @return 		true if board is complete, false otherwise
	 */ 
	public boolean solved()
	{
		for ( int[] a : board )
		{
			for ( int n : a )
			{
				if ( n == 0 )
					return false;
			}
		}
		return true;
	}

	/**
	 * Returns String representation of board. First and last lines consist of "--". Other lines are separated by "|". 
	 *
	 * @return 		String representation of board. 
	 */ 
	public String toString()
	{
		String toReturn = "";

		for ( int i = 0; i < 9; i++ ) 
			toReturn += " –– ";
		toReturn += "\n";

		for ( int[] a : board )
		{
			toReturn += "|";
			for ( int n : a )
			{
				if ( n == 0 )
					toReturn += "  " + " |";
				else
					toReturn += " " + n + " |"; 
			}
			toReturn += "\n"; 
		}

		for ( int i = 0; i < 9; i++ )
			toReturn += " –– ";

		return toReturn;
	}

	/**
	 * Main method only for testing purposes. 
	 *
	 * @param args 		main method paramenter
	 */ 
	public static void main( String[] args )
	{
		SudokuBoard gameBoard = new SudokuBoard();
		System.out.println( gameBoard );
		//int[] spot = gameBoard.getMostConstrained(); 
		//System.out.println( "" + spot[0] + spot[1] );

		int x = gameBoard.numPossibilities( 1, 7 );
		System.out.println();
		System.out.println( x );
		System.out.println();
		//System.out.println( gameBoard.canPlace( 1, 7, 2 ));
	}

}