/**
 * SudokuSolverRunner class
 *
 * @author  Jessica Li
 * @version 12/05/15
 */ 
public class SudokuSolverRunner
{
	/**
	 * Runner for SudokuSolver class. 
	 * Creates a SudokuSolver, which automatically creates the board with the values in the CSV file. 
	 * Prints the first board of the stack within the solver, which is the original game board. 
	 * Prints the results of a call to solve(). 
	 *
	 * @param args 		main method parameter
	 */ 
	public static void main( String[] args )
	{
		SudokuSolver solver = new SudokuSolver();
		solver.print();
		System.out.println( );

		System.out.println( solver.solve() );
	}
}