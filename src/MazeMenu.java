import java.io.*;


/**
 * Priyanka Pathak
 * COMS 3134 - Fall 2010
 * Homework Assignment Four - Maze Assignment
 * December 16, 2010
 * 
 */

public class MazeMenu implements Serializable{
	
	static String choice;
	static BufferedReader reader;
	static Maze maze = new Maze();
	
	
	public static void main(String[] args) throws IOException {
		reader = new BufferedReader(new InputStreamReader(System.in)); 
		
		System.out.println("What would you like to do? \n1. Create a maze. \n2. Save current maze to file. \n3. Load a maze from a file." +
				"\n4. Print out the maze. \n5. Solve maze.");
		
		try{
			choice = reader.readLine();
			
			if (choice.equals("1")){
				//Run buildMaze method
				maze.buildMaze();
				System.out.println("Maze successfully created! \n");
				main(args);
			}else if (choice.equals("2")){
				//run saveMaze method
				saveMaze();
				System.out.println("Maze successfully saved! \n");
				main(args);
			}else if (choice.equals("3")){
				//run loadMaze method
				loadMaze();
				System.out.println("Maze successfully loaded! \n");
				main(args);
			}else if (choice.equals("4")){
				//run printMaze method
				System.out.println("This maze looks like this: \n");
				maze.printMaze();
				main(args);
			}else if (choice.equals("5")){
				//run solveMaze method
				System.out.println("Solving...\n");
				maze.solveMaze(0, 0);
				main(args);
			}else{
				System.out.println("Please choose one of the options, 1-5.");
				main(args);
			}
		
		}
		catch (IOException e){
			e.printStackTrace();
		}

	}
	
	
	public static void saveMaze(){
		//Take maze and store it into a Serializable file called Maze.ser
		try {
	            FileOutputStream fileOut = new FileOutputStream("Maze.ser");
	            ObjectOutputStream out = new ObjectOutputStream(fileOut);

	            out.writeObject(maze);

	            out.close();
	            fileOut.close();	
           
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public static void loadMaze(){
		//Take maze from Serializable file Maze.ser and load into program
        try {

             InputStream file = new FileInputStream("Maze.ser" );
             ObjectInput input = new ObjectInputStream (file);
                
             maze = (Maze)input.readObject();
                  
             input.close();
             file.close();

	            
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
	}

}
