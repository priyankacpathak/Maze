import java.util.*;
import java.io.*;

/**
 * Priyanka Pathak
 * COMS 3134 - Fall 2010
 * Homework Assignment Four - Maze Assignment
 * December 16, 2010
 * 
 */

public class Cell implements Serializable{
	//Defines a cell by declaring four booleans indicated the existence of a wall, and their corresponding weights
	
	private static Random random = new Random();
	
	public boolean left = true;
	public int wLeft;
	
	public boolean right = true;
	public int wRight;
	
	public boolean top = true;
	public int wTop; 
	
	public boolean bottom = true;
	public int wBottom;
	
	public int distance = Integer.MAX_VALUE;
	public boolean visited = false;
		
	public void knockDownWall(int i){
		switch(i){
		case 0: left = false; break;
		case 1: right = false; break;
		case 2: top = false; break;
		case 3: bottom = false; break;
		}
	}
	
}
