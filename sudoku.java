package sudokupkg;


public class sudoku {
	
	boolean DEBUG = false;
	
	int puzzle[][] = 
			{
		    {0, /* */ 0, 0, 0, 0, 0, 0, 0, 0, 0}, // First row, first column - no elements
		    {0, /* */ 0, 0, 0, 0, 0, 0, 7, 0, 0},
		    {0, /* */ 0, 0, 7, 0, 2, 0, 1, 0, 0},
		    {0, /* */ 0, 5, 0, 0, 6, 3, 0, 0, 9},
		    {0, /* */ 0, 0, 0, 4, 9, 0, 2, 6, 0},
		    {0, /* */ 1, 0, 0, 0, 0, 0, 0, 0, 8},
		    {0, /* */ 0, 6, 4, 0, 5, 2, 0, 0, 0},
		    {0, /* */ 8, 0, 0, 5, 4, 0, 0, 9, 0},
		    {0, /* */ 0, 0, 9, 0, 3, 0, 4, 0, 0},
		    {0, /* */ 0, 0, 3, 0, 0, 0, 0, 0, 0},
			};
	
	void print() {
		
		int i;
		
		System.out.print("      ");
		for(int j = 1; j <= 3; j++)
			System.out.print(j + "  ");
	
		System.out.print("|  ");
		
		for(int j = 4; j <= 6; j++)
			System.out.print(j + "  ");
		
		System.out.print("|  ");
		
		for(int j = 7; j <= 9; j++)
			System.out.print(j + "  ");
		
		System.out.println();
		System.out.println("      ===============================");
		
		for(i = 1; i <=3 ; i++)
			System.out.println(i + "  |  " + puzzle[i][1] + "  " +  puzzle[i][2] + "  " + puzzle[i][3] + "  |  " +
					puzzle[i][4] + "  " + puzzle[i][5] + "  " + puzzle[i][6] + "  |  " + 
					puzzle[i][7] + "  " + puzzle[i][8] + "  " + puzzle[i][9]);
		
		System.out.println("      -------------------------------");

		for(i = 4; i <=6 ; i++)
			System.out.println(i + "  |  " + puzzle[i][1] + "  " +  puzzle[i][2] + "  " + puzzle[i][3] + "  |  " +
					puzzle[i][4] + "  " + puzzle[i][5] + "  " + puzzle[i][6] + "  |  " + 
					puzzle[i][7] + "  " + puzzle[i][8] + "  " + puzzle[i][9]);
		
		System.out.println("      -------------------------------");
		
		for(i = 7; i <=9 ; i++)
			System.out.println(i + "  |  " + puzzle[i][1] + "  " +  puzzle[i][2] + "  " + puzzle[i][3] + "  |  " +
					puzzle[i][4] + "  " + puzzle[i][5] + "  " + puzzle[i][6] + "  |  " + 
					puzzle[i][7] + "  " + puzzle[i][8] + "  " + puzzle[i][9]);
		
	} //print
	
	boolean isPuzzleSolved() {
		
		for(int i = 1; i <= 9; i++) {
			for(int j = 1; j <= 9 ; j++) {
				if(puzzle[i][j] == 0)
					return false;
			}
		}
		
		return true;
	} //isPuzzleSolved
	
	void pigeonhole(int lowerRow, int higherRow, int lowerColumn, int higherColumn) {
		
		int numbersToPlace[] = new int[10];
		int placeholderMatrix[][] = new int[4][4];
		int k = 0;
		int i;
		int j;
		int a;
		int b;
		int c;
		int d;
		int e;
		int f;
		int g;
		int h;
		int m;
		int n;
		int x, y;
		int count = 0;
		int ix, iy;
				
		// all numbers potentially need to be placed
		for(k = 1; k <= 9; k++) {
			numbersToPlace[k] = 1;
 		}
		
		// Find the numbers that need to be potentially placed
		for(a = lowerRow; a <= higherRow; a++) {
			for(b = lowerColumn; b <= higherColumn; b++) {
				if(puzzle[a][b] != 0) {
					numbersToPlace[puzzle[a][b]] = 0;  // Remove numbers that don't need to be matched
				}
			}
		}
		
		// for each of the numbers to be potentially placed
		for(k = 1; k<= 9; k++) {
			// Use the index k for the number that needs to be matched
			if(numbersToPlace[k] != 0) {
				
				// k is the element that needs to be placed
				
				// initialize a place holder matrix for k
				for(c = 1; c <= 3; c++) {
					for(d = 1; d <= 3; d++) {
						placeholderMatrix[c][d] = 1;  // All spots are potential matches
					}
				}
				
				// remove the spots that are already occupied by other elements
				for(a = lowerRow; a <= higherRow; a++) {
					for(b = lowerColumn; b <= higherColumn; b++) {
						if(puzzle[a][b] != 0) {
							x = a % 3;
							y = b % 3;
							if(x == 0)
								x = 3;
							if(y == 0)
								y = 3;
							
							placeholderMatrix[x][y] = 0;     // This spot is already occupied by this element
						}
					}
				}
				
				// check row logic
				for(e = lowerRow; e <= higherRow; e++) {
					for(f = 1; f <= 9; f++) {
						if(puzzle[e][f] == k) {
							// mark row as not potential
							for(g = 1; g <= 3; g++) {
								x = e % 3;
								if(x == 0)
									x = 3;
								
								placeholderMatrix[x][g] = 0;
 							}
						}
					}											
				}
				
				// check column logic
				for(n = lowerColumn; n <= higherColumn; n++){
					for(m = 1; m <= 9; m++) {
						if(puzzle[m][n] == k) {
							// mark column as not potential
							for(h = 1; h <= 3; h++) {
								y = n % 3;
								if(y == 0)
									y = 3;
								
								placeholderMatrix[h][y] = 0;
							}
						}
					}
				}
				
				count = 0;
				ix = 0;
				iy = 0;
				for(c = 1; c <= 3; c++) {
					for(d = 1; d <= 3; d++) {
						if (placeholderMatrix[c][d] == 1) {
							ix = lowerRow + c - 1;
							iy = lowerColumn + d - 1;
							count++;
						}
					}
				}
				
				if(count == 1) {
					System.out.println("P: Row: " + ix + " Column: " + iy + " element placed: " + k);
					puzzle[ix][iy] = k;
				}
				
				
			} // if(numbersToPlace[k] != 0)
			
		} // for all numbers to be potentially placed		
		
	} // pigeonhole
	
	
	void solve() {
		
		int potential[] = new int[10];
		int count = 0;
		int k;
		int row;
		int column;
		long iterations = 0;
		int lowerRow;
		int lowerColumn;
		int higherRow;
		int higherColumn;
		int a;
		int b;
		
		while (isPuzzleSolved() == false) {
			
			iterations++;
			
			if(iterations > 10000)
				break; 
			
			if(DEBUG)
				System.out.println("iterations: " + iterations);
			
			// run through the puzzle
			for(int i = 1; i <= 9; i++) {
				for(int j = 1; j <= 9 ; j++) {
					
					row = i;
					column = j;
					if(DEBUG)
						System.out.println("Row: " + row + " Column: " + column);
					
					// solve only for unsolved spaces
					if(puzzle[i][j] == 0) {
					
						// solve for this particular element
						for (k = 1; k <= 9 ; k++)
							potential[k] = 1; // all elements are potential. 1 indicates potential. 0 indicates not potential.
 							
						// check row logic
						for(int c = 1; c <= 9; c++) {
							if(puzzle[row][c] != 0) {
								potential[puzzle[row][c]] = 0;
							}
						}
						
						// check column logic
						for(int r = 1; r <= 9; r++) {
							if(puzzle[r][column] != 0) {
								potential[puzzle[r][column]] = 0;
							}
						}
												
						// check square logic
						if(row % 3 == 1) {
							lowerRow = row;
						} else if(row % 3 == 2) {
							lowerRow = row - 1;
						} else {
							lowerRow = row - 2;
						}
						higherRow = lowerRow + 2;
						
						if(column % 3 == 1) {
							lowerColumn = column;
						} else if(column % 3 == 2) {
							lowerColumn = column - 1;
						} else {
							lowerColumn = column - 2;
						}
						higherColumn = lowerColumn + 2;
						
						for(a = lowerRow; a <= higherRow; a++) {
							for(b = lowerColumn; b <= higherColumn; b++) {
								if(puzzle[a][b] != 0) {
									potential[puzzle[a][b]] = 0;
								}
							}
 						}
	
						// check if potential array has only one element
						count = 0;
						for (k = 1; k <= 9 ; k++) {
							if(potential[k] == 1)
								count++;
						}							
						
						// if potential array has only one element, then fill up space with element, else continue
						if(count == 1) {
							for (k = 1; k <= 9 ; k++) {
								if(potential[k] == 1)
									break;
							}	
							
							// set the element in puzzle
							puzzle[i][j] = k;
							//if(DEBUG)
								System.out.println("NP: Row: " + row + " Column: " + column + " element placed: " + k);
							
						} //end if count == 1
						
						
						// pigeon hole logic
						pigeonhole(1, 3, 1, 3); // Square 1
						pigeonhole(1, 3, 4, 6); // Square 2
						pigeonhole(1, 3, 7, 9); // Square 3
						pigeonhole(4, 6, 1, 3); // Square 4
						pigeonhole(4, 6, 4, 6); // Square 5
						pigeonhole(4, 6, 7, 9); // Square 6
						pigeonhole(7, 9, 1, 3); // Square 7
						pigeonhole(7, 9, 4, 6); // Square 8
						pigeonhole(7, 9, 7, 9); // Square 9

					} //end if
					
				} //end for 
			} //end for 
 		} //end while
		
		
	} //solve
	
	
	public static void main(String args[]) {
		
		sudoku s = new sudoku();
		
		System.out.println("Unsolved puzzle");
		System.out.println("");
		s.print();
		System.out.println("");
		
		s.solve();
		
		System.out.println("Solved puzzle");
		System.out.println("");
		s.print();
		
	} // main

}

