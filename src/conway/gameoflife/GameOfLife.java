package conway.gameoflife;
// A simple Java program to implement Game of Life 
public class GameOfLife 
{ 
	public static void main(String[] args){ 

		
		if(args == null || args.length == 0) {
			System.out.println("Usage: [size of random shape]");
			System.exit(1);
		}
		if(!args[0].matches("^[0-9]+$")) {
			System.out.println("Argument must be an integer");
			System.exit(1);
		}
		int kernelSize = Integer.valueOf(args[0]);
		
		int M = 100; 
		// Intiliazing the grid. 
		int[][] grid = new int[M][M];
		for(int i=0;i<M;i++) 
			for(int j=0;j<M;j++) 
				grid[i][j] = 0;

		
//		/*
//		 * WOW
//		 */
//		int[][] shape = new int[][] {
//			{0,1,1,1},
//			{1,0,1,1},
//			{1,1,1,1},
//			{1,0,0,1},
//		};
		/*
		 * ANother amazing one
		 */
//		int[][] shape = new int[][] {
//			{1,1,1,1},
//			{1,0,0,1},
//			{1,1,0,1},
//			{1,0,1,0},
//		};
		/*
		 * Icnredible!
		 */
//		int[][] shape = new int[][] {
//			{0, 1, 0},
//			{1, 1, 0},
//			{0, 1, 1},
//		};

		
		//WOWO!
//		int[][] shape = new int[][] {
//			{1, 1, 1},
//			{1, 0, 1},
//			{0, 0, 1},
//		};
		
		//This becomes the glider
//		int[][] shape = new int[][] {
//			{1, 0, 1, 0},
//			{1, 1, 1, 1},
//			{1, 1, 0, 0},
//			{1, 0, 0, 1},
//		}
		int[][] shape = randomShape(kernelSize);
		
		grid = insertShape(grid, shape);
		// Displaying the grid 
		System.out.println("Original Generation"); 
		displayLife(grid);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		int i=0;
		int[][] oldgrid = new int[grid.length][grid.length];
		while(i++ < 10000) {
			int[][] newgrid = nextGeneration(grid);
			displayLife(newgrid);
			if(isEmpty(grid)) {
				System.out.println("End game: No life left\tGenerations: " + i);
				System.exit(1);
			}
			if(isSteadyState(newgrid, grid) || isSteadyState(newgrid, oldgrid)) {
				System.out.println("End game: Steady state\tGenerations: " + i);
				System.exit(1);
			}
			oldgrid = grid.clone();
			grid = newgrid.clone();
		}
		System.out.println("And life goes on...");
	} 

	private static int[][] randomShape(int M) {
		int[][] shape = new int[M][M];
		for(int i=0;i<M;i++) 
			for(int j=0;j<M;j++)
				shape[i][j] = Math.random() > 0.5 ? 1 : 0;
				
		return shape;
	}

	private static boolean isSteadyState(int[][] newgrid, int[][] grid) {
		for(int i=0;i<grid.length;i++) 
			for(int j=0;j<grid.length;j++) 
				if(newgrid[i][j] != grid[i][j]) 
					return false;
		return true;
	}

	private static boolean isEmpty(int[][] grid) {
		for(int i=0;i<grid.length;i++) 
			for(int j=0;j<grid.length;j++) 
				if(grid[i][j] == 1) 
					return false;
		return true;
	}

	private static int[][] insertShape(int[][] grid, int[][] shape) {
		return insertShape(grid, shape, grid.length/2-shape.length/2, grid.length/2-shape.length/2);
	}
	private static int[][] insertShape(int[][] grid, int[][] shape, int insertX, int insertY) {
		int M = grid.length;
		int[][] newgrid = new int[M][M];
		for(int i=0;i<M;i++) 
			for(int j=0;j<M;j++) 
				newgrid[i][j] = grid[i][j];
		for(int i=0;i<shape.length;i++) 
			for(int j=0;j<shape.length;j++) 
				newgrid[insertX+i][insertY+j] = shape[i][j];
		return newgrid;
	}

	// Function to print next generation 
	static int[][] nextGeneration(int grid[][]) 
	{ 
		int M = grid.length;
		int[][] future = new int[M][M]; 

		// Loop through every cell 
		for (int l = 1; l < M - 1; l++){ 
			for (int m = 1; m < M - 1; m++){ 
				// Finding number of neighbors that are alive 
				int aliveNeighbours = 0; 
				for (int i = -1; i <= 1; i++) 
					for (int j = -1; j <= 1; j++) 
						aliveNeighbours += grid[l + i][m + j]; 

				// The cell needs to be subtracted from 
				// its neighbors as it was counted before 
				aliveNeighbours -= grid[l][m]; 

				// Implementing the Rules of Life 

				// Cell is lonely and dies 
				if ((grid[l][m] == 1) && (aliveNeighbours < 2)) 
					future[l][m] = 0; 

				// Cell dies due to over population 
				else if ((grid[l][m] == 1) && (aliveNeighbours > 3)) 
					future[l][m] = 0; 

				// A new cell is born 
				else if ((grid[l][m] == 0) && (aliveNeighbours == 3)) 
					future[l][m] = 1; 

				// Remains the same 
				else
					future[l][m] = grid[l][m]; 
			} 
		} 

		return future; 
	} 
	
	
	public static void displayLife(int[][] grid) {
		int M = grid.length;
		System.out.print("-");
		for(int j=0;j<M;j++) {
			System.out.print("-");
		}
		System.out.println("-");
		for (int i = 0; i < M; i++) {
			System.out.print("|");
			for (int j = 0; j < M; j++) 
				if (grid[i][j] == 0) 
					System.out.print(" "); 
				else
					System.out.print("*"); 
			System.out.println("|"); 
		}
	}
} 
