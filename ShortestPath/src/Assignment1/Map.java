/* PLEASE DO NOT MODIFY A SINGLE STATEMENT IN THE TEXT BELOW.
 READ THE FOLLOWING CAREFULLY AND FILL IN THE GAPS
I hereby declare that all the work that was required to 
solve the following problem including designing the algorithms
and writing the code below, is solely my own and that I received
no help in creating this solution and I have not discussed my solution 
with anybody. I affirm that I have read and understood
 the Senate Policy on Academic honesty at 
https://secretariat-policies.info.yorku.ca/policies/academic-honesty-senate-policy-on/
and I am well aware of the seriousness of the matter and the penalties that I will face as a 
result of committing plagiarism in this assignment.
BY FILLING THE GAPS,YOU ARE SIGNING THE ABOVE STATEMENTS.
Full Name: Patrick Li
Student Number: 216377640
Course Section: Section A
*/

package Assignment1;
import java.util.*;

/**
 * 
 * @author EECS2030 Team
 *
 */

public class Map {
	boolean [][] map; 
	private int row;
	private int column;
	private String path = "";
	/**
	 * This is the constructor that constructs the city map, 
	 * which is a grid of row by column.
	 * @param row is the number of east-west streets of the city
	 * @param column is the number of north-south streets of the city
	 */
	public Map(int row, int column) {
		this.row = row;
		this.column = column;
		map = new boolean[this.row][this.column];
	}
	/**
	 * This method checks the correctness of the input parameters. If the preconditions are not met 
	 * an exception is thrown, otherwise depending to the direction, it calls 
	 * one of the four recursive functions of goSouthWest, goSouthEast, goNorthWest and goNorthEast.
	 * @param startRow is the starting row of the path 
	 * @param startCol is the starting column of the path
	 * @param destRow is the destination row
	 * @param destCol is the destination column
	 * @param path is the path that is constructed while the recursive method is called. In first round,  it will be "".
	 * @return returns a string representing the path to the destination. The format of the output is (x1,y1) (x2,y2) (x3,y3)...
	 * @pre the integer parameters should be in the range of the city grid.(i.e. [0, N) if N is the number of east-west streets and [0, M) if 
	 * M is the number of north-south streets.) 
	 * @exception IllegalArgumentException if any of the precondition did not meet.
	 */
	public String getPath (int startRow, int startCol, int destRow, int destCol, String path) {
		
		//Checks if the parameters are in range of the city grid.
		if(startRow < 0 || startRow > this.row) throw new IllegalArgumentException("Starting Row is less than zero: " + startRow);
		if(startCol < 0 || startCol > this.column) throw new IllegalArgumentException("Starting Column is less than zero: " + startCol);
		if(destRow < 0 || destRow > this.row) throw new IllegalArgumentException("Destination row is greater than the number of east-west streets: " + destRow);
		if(destCol < 0 || destCol > this.column) throw new IllegalArgumentException("Destination column is greater than the number of north-west streets: " + destCol);
		
		//Calls the methods to go North and either East or West.
		if(startRow < destRow && startCol < destCol) {
			path = goNorthEast(startRow, startCol, destRow, destCol, path);
		} 
		if(startRow < destRow && startCol > destCol){
			path = goNorthWest(startRow, startCol, destRow, destCol, path);
		}
		 
		///Calls the methods to go South and either East or West.
		if(startRow > destRow && startCol < destCol) {
			path = goSouthEast(startRow, startCol, destRow, destCol, path);
		}
		if(startRow > destRow && startCol > destCol) {
			path = goSouthWest(startRow, startCol, destRow, destCol, path);
		}
		
		//If the column coordinates are the same, calls the method to go South.
		if(startRow > destRow && startCol == destCol) {
			path = goSouthEast(startRow, startCol, destRow, destCol, path);
		}
		
		//If the column coordinates are the same, calls the method to go North.
		if(startRow < destRow && startCol == destCol) {
			path = goNorthEast(startRow, startCol, destRow, destCol, path);
		}
		
		//If the row coordinates are the same, calls the method to go East.
		if (startRow == destRow && startCol < destCol) {
			path = goNorthEast(startRow, startCol, destRow, destCol, path);
		}
		
		//If the row coordinates are the same, calls the method to go West.
		if (startRow == destRow && startCol > destCol) {
			path = goNorthWest(startRow, startCol, destRow, destCol, path);
		}
		return path;
	}
	/**
	 * This method returns a path from the source (startRow, startCol) to the destination (destRow, destCol).
	 * Please note that the returning path does not include the starting point.  
	 * @param startRow is the starting row of the path 
	 * @param startCol is the starting column of the path
	 * @param destRow is the destination row
	 * @param destCol is the destination column
	 * @param path is the path that is constructed while the recursive method is called. In first round,  it will be "".
	 * @return returns a string representing the path to the destination. The format of the output is (x1,y1) (x2,y2) (x3,y3)...
	 * @pre <code> startRow >= destRow </code> and <code> startCol >= destCol </code>
	 */
	
	private String goSouthWest(int startRow, int startCol, int destRow, int destCol , String path) {
		//If one of the paths is equivalent to the destination point, it returns the path. 
		if(startRow == destRow && startCol == destCol) {
			return path;
		}
		
		//If the startRow > destRow, it recursively calls itself with startRow = startRow - 1.
		if(startRow > destRow) {
			String newPath = "(" + Integer.toString(startRow - 1) + "," + Integer.toString(startCol) + ") ";
			path += goSouthWest(startRow - 1, startCol, destRow, destCol, newPath);
			
		}
		//If the startRow > destRow, it recursively calls itself with startCol = startCol - 1.
		else if(startCol > destCol) {
			String newPath = "(" + Integer.toString(startRow) + "," + Integer.toString(startCol - 1) + ") ";
			path += goSouthWest(startRow, startCol - 1, destRow, destCol, newPath);
		}
		return path;
	}
	
	/**
	 * This method returns a path from the source (startRow, startCol) to the destination (destRow, destCol).
	 * Please note that the returning path does not include the starting point. 
	 * @param startRow is the starting row of the path 
	 * @param startCol is the starting column of the path
	 * @param destRow is the destination row
	 * @param destCol is the destination column
	 * @param path is the path that is constructed while the recursive method is called. In first round,  it will be "".
	 * @return returns a string representing the path to the destination. The format of the output is (x1,y1) (x2,y2) (x3,y3)...
	 * @pre <code> startRow >= destRow </code> and <code> startCol <= destCol </code>
	 */
	private String goSouthEast(int startRow, int startCol, int destRow, int destCol , String path) {
		//If one of the paths is equivalent to the destination point, it returns the path. 
		if(startRow == destRow && startCol == destCol) {
			return path;
		}
		
		//If the startRow > destRow, it recursively calls itself with startRow = startRow - 1.
		if(startRow > destRow) {
			String nextPath = "(" + Integer.toString(startRow - 1) + "," + Integer.toString(startCol) + ") ";
			path += goSouthEast(startRow - 1, startCol, destRow, destCol, nextPath);
			
		}
		//If the startRow > destRow, it recursively calls itself with startCol = startCol + 1.
		else if(startCol < destCol) {
			String nextPath = "(" + Integer.toString(startRow) + "," + Integer.toString(startCol + 1) + ") ";
			path += goSouthEast(startRow, startCol + 1, destRow, destCol, nextPath);
		}
		return path;
	}
	
	/**
	 * This method returns a path from the source (startRow, startCol) to the destination (destRow, destCol).
	 * Please note that the returning path does not include the starting point. 
	 * @param startRow is the starting row of the path 
	 * @param startCol is the starting column of the path
	 * @param destRow is the destination row
	 * @param destCol is the destination column
	 * @param path is the path that is constructed while the recursive method is called. In first round,  it will be "".
	 * @return returns a string representing the path to the destination. The format of the output is (x1,y1) (x2,y2) (x3,y3)...
	 * @pre <code> startRow <= destRow </code> and <code> startCol >= destCol </code>
	 */
	private String goNorthEast(int startRow, int startCol, int destRow, int destCol , String path) {
		//If one of the paths is equivalent to the destination point, it returns the path. 
		if(startRow == destRow && startCol == destCol) {
			return path;
		}
		
		//If the startRow < destRow, it recursively calls itself with startRow = startRow + 1.
		if(startRow < destRow) {
			String nextPath = "(" + Integer.toString(startRow + 1) + "," + Integer.toString(startCol) + ") ";
			path += goNorthEast(startRow + 1, startCol, destRow, destCol, nextPath);
			
		}
		//If the startRow < destRow, it recursively calls itself with startCol = startCol + 1.
		else if(startCol < destCol) {
			String nextPath = "(" + Integer.toString(startRow) + "," + Integer.toString(startCol + 1) + ") ";
			path += goNorthEast(startRow, startCol + 1, destRow, destCol, nextPath);
		}
		return path;
	}

	/**
	 * This method returns a path from the source (startRow, startCol) to the destination (destRow, destCol).
	 * Please note that the returning path does not include the starting point. 
	 * @param startRow is the starting row of the path 
	 * @param startCol is the starting column of the path
	 * @param destRow is the destination row
	 * @param destCol is the destination column
	 * @param path is the path that is constructed while the recursive method is called. In first round,  it will be "".
	 * @return returns a string representing the path to the destination. The format of the output is (x1,y1) (x2,y2) (x3,y3)...
	 * @pre <code> startRow <= destRow </code> and <code> startCol >= destCol </code>
	 */
	private String goNorthWest(int startRow, int startCol, int destRow, int destCol , String path) {
		//If one of the paths is equivalent to the destination point, it returns the path.
		if(startRow == destRow && startCol == destCol) {
			return path;
		}
		
		//If the startRow < destRow, it recursively calls itself with startRow = startRow + 1.
		if(startRow < destRow) {
			String nextPath = "(" + Integer.toString(startRow + 1) + "," + Integer.toString(startCol) + ") ";
			path += goNorthWest(startRow + 1, startCol, destRow, destCol, nextPath);
			
		}
		//If the startRow < destRow, it recursively calls itself with startCol = startCol + 1.
		else if(startCol > destCol) {
			String nextPath = "(" + Integer.toString(startRow) + "," + Integer.toString(startCol - 1) + ") ";
			path += goNorthWest(startRow, startCol - 1, destRow, destCol, nextPath);
		}
		return path;
	}
	
	/**
	 * This method find a path from (startRow, startCol) to a border point of the city. 
	 * Please note that the starting point should be included in the path.
	 * @param startRow is the starting row of the path
	 * @param startCol is the starting column of the path
	 * @return is a path from (starting row, staring col) to a border point of the city. The format of the output is (x1,y1) (x2,y2) (x3,y3)...
	 */
	public String findPath(int startRow, int startCol) {
		//Random generates a number between 0 and 3.
		Random rand = new Random();
		int randomNum = rand.nextInt(4);
		
		//Acquires the borders of the city.
		int borderRow = this.row - 1;
		int borderCol = this.column - 1;
		
		//Acquires the current position
		boolean mapPoint = map[startRow][startCol];
		
		//Tests to see if the intersection was previously passed, if so resets the method to start over again.
		if(mapPoint == true) {
			
			//Acquires the row and column of the original start position.
			char originalRow = path.charAt(1);
			char originalCol = path.charAt(3);
			
			//Converts the chars into ints
			int begRow = Character.getNumericValue(originalRow);
			int begCol = Character.getNumericValue(originalCol);
			
			//Resets the path and map, and restarts the findPath method.
			path = "";
			map = new boolean[this.row][this.column];
			return findPath(begRow, begCol);
		}
		else {
			map[startRow][startCol] = true;
		}
		
		//Adds the current position to the path string.
		path += "(" + Integer.toString(startRow) + "," + Integer.toString(startCol) + ") ";	
		
		//If the current position is 0 or at the border for either row or column, it returns the path.
		if(startRow == 0 || startRow == borderRow || startCol == 0 || startCol == borderCol) {
			return path;
		}
		
		//Based on the random numbers, it will select a random direction to go towards.
		//Moves in the North Direction.
		if(randomNum == 0) {
			path = findPath(startRow + 1, startCol);
		}
		//Moves in the East Direction.
		else if(randomNum == 1) {
			path = findPath(startRow, startCol + 1);
		}
		//Moves in the South Direction.
		else if(randomNum == 2) {
			path = findPath(startRow - 1, startCol);
		}
		//Moves in the West Direction.
		else {
			path = findPath(startRow, startCol - 1);
		} 
		return path;
	}
	
		
} // end of class
