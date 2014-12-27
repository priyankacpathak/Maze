import java.util.*;
import java.io.*;

/**
 * Priyanka Pathak
 * COMS 3134 - Fall 2010
 * Homework Assignment Four - Maze Assignment
 * December 16, 2010
 * 
 */

public class Maze implements Serializable{

	//The maze size is determined in the variables 'length' and 'width' here. The rest of the code simply refers to these
	//variables, so you only need to change them here to change the whole maze.
	//Note: The start and end of the maze are considered the first cell (maze[0][0]) and the last cell (maze[length-1][width-1])
	public static int length = 10;
	public static int width = 10;
	public Cell[][] maze = new Cell[length][width];
	private static Random random = new Random();
	public DisjointedSet ds = new DisjointedSet(length * width);
	private boolean isBuilt = false;

  private static final int LEFT = 0;
  private static final int RIGHT = 1;
  private static final int TOP = 2;
  private static final int BOTTOM = 3;

  private static int posToInt(int x, int y) {
    return x * length + y;
  }
	
	public void buildMaze(){
    // init the cells
    for (int i = 0; i < length; i++)
      for (int j = 0; j < width; j++)
        maze[i][j] = new Cell();

		while (!isBuilt) {
			int x = random.nextInt(length);
			int y = random.nextInt(width);
			int z = random.nextInt(4);
      int weight = random.nextInt(6) + 1; // no zero cost weights...
			
      // check to see if this is a valid wall to knock down
      if (x == 0 && z == LEFT)
        continue; // |x
      if (y == 0 && z == BOTTOM)
        continue; // .x.
                  // ---
      if (x == width - 1 && z == RIGHT)
        continue; // x|
      if (y == length - 1 && z == TOP)
        continue; // ---
                  // .x.
                  
      //Tests passed! Now knock down some WALLS!
      //Knock down a random wall by removing wall and union-ing the two adjacent cells
      maze[x][y].knockDownWall(z);

      // generate the weights from knocking down wall / do union
      int current = ds.find(posToInt(x, y));
      switch(z){
      case 0: 
        ds.union(ds.find(posToInt(x-1, y)), current); 
        maze[x-1][y].wRight = maze[x][y].wLeft = weight;
        maze[x-1][y].right = false;
        break;
      case 1: 
        ds.union(ds.find(posToInt(x+1, y)), current); 
        maze[x+1][y].wLeft = maze[x][y].wRight = weight;
        maze[x+1][y].left = true;
        break;
      case 2: 
        ds.union(ds.find(posToInt(x, y+1)), current); 
        maze[x][y+1].wBottom = maze[x][y].wTop = weight; 
        maze[x][y+1].bottom = true;
        break;
      case 3: 
        ds.union(ds.find(posToInt(x, y-1)), current); 
        maze[x][y-1].wTop = maze[x][y].wBottom = weight;
        maze[x][y-1].top = true;
        break;
      }

			//check and see if start and end are in same set; if true, isBuilt = true, if false, nothing
			if((ds.find(posToInt(0, 0))) == (ds.find(posToInt(length - 1, width - 1)))) {
				isBuilt = true;
			}
		}
		
	}
	
	public void printMaze(){
		//Prints maze

    for (int j = length - 1; j >= 0; j--) {
      // row top 
      for (int i = 0; i < width; i++) {
        if (maze[i][j].top)
          System.out.print("---");
        else 
          System.out.print("   ");
        if (i == width - 1)
          System.out.println();
      }
      //Row middle
      for (int i = 0; i < width; i++) {
        if (maze[i][j].left)
          System.out.print("|");
        else
          System.out.print(" ");
        System.out.print(" ");
        if (maze[i][j].right)
          System.out.print("|");
        else
          System.out.print(" ");
        if (i == width - 1)
          System.out.println();
      }
      if (j == 0) {
        for (int i = 0; i < width; i++)
          System.out.print("---");
        System.out.println();
      }
    }
	}
	
	public void solveMaze(int x, int y){
		//Solves maze and times it as well, then outputs the time
		//Assumes that each cell is a node in the graph and each missing wall is an edge
		long startTime = System.currentTimeMillis();
		int total = 0;
		
		//Check to see if maze[0][0] and maze[length-1][width-1] are in the same set
		if((ds.find(posToInt(0, 0))) == (ds.find(posToInt(length - 1, width - 1)))) {
			long endTime = System.currentTimeMillis();
			long totalTime = (endTime - startTime);
			System.out.println("Maze solved! Total time to solve maze: " + totalTime);
			return;
		}
		
		//Starting with first cell, find missing wall; set next cell accordingly
		if(maze[x][y].bottom == false){
			total += maze[x][y].wBottom;
			y = (y+1);
			solveMaze(x,y);
		}
		if(maze[x][y].top == false){
			total += maze[x][y].wTop;
			y = (y-1);
		}
		if(maze[x][y].left == false){
			total += maze[x][y].wLeft;
			x = (x-1);
		}
		if(maze[x][y].right == false){
			total += maze[x][y].wRight;
			x = (x+1);
		}
		//Add weight of wall to total
		//Get next cell
		//Continue until maze[0][0] and maze[length-1][width-1] are in the same set
		
	}
	
}
