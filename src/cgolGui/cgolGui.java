package cgolGui;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class cgolGui {
	
	
	int width;
	int height;
	int[][] oldGrid, newGrid, swapGrid; 
	
	Scanner scanner;	
	
	
	public cgolGui(int height, int width, String fileName) throws FileNotFoundException {
		this.width = width;
		this.height = height; 
		this.oldGrid = new int[height][width]; 	
		this.newGrid = new int[height][width];
		this.swapGrid = new int[height][width];
		this.scanner = new Scanner(new FileReader(fileName));
		String line;
		int counter = 0; 
		
		while (scanner.hasNextLine()) {
			line = scanner.nextLine();
			for (int j=0; j<line.length(); j++) {
				if (line.charAt(j) == '*') {
					oldGrid[counter][j] = 1;						
				} else {
					oldGrid[counter][j] = 0;
					}
			}
			counter++;
		}	
	}
	
	
	
	public void printGrid() {	
		System.out.println("------------");
		for (int i=0; i<height; i++) {
			String line = "|";
			for (int j=0; j<width; j++) {
				if (oldGrid[i][j]==0) {
					line += " ";
				} else {
					line += "*";
				}
			}
			line += "|";
			System.out.println(line);	
		}	
		System.out.println("------------\n");
	
		/*String arrayContents = "";
		for (int x = 0; x < height; x++) {
			arrayContents += Arrays.toString(oldGrid[x])+", ";
		}
		arrayContents.substring(0, arrayContents.length()-2);
		System.out.println("Array Contents are:\n" +arrayContents);	*/
	}
	
	
	public int getHeight() {
		return height;
	}
	
	
	public int getWidth() {
		return width; 
	}
	
	
	public int[][] getGen() {
		return oldGrid; 
	}
	
	
	public int getCont(int y, int x) {
		return oldGrid[y][x]; 
	}
	
	
	public int getNeighbours(int y, int x) {
		int counter = 0;
		//if statements check for index out of bound errors
		
		try {
		//top left
		if (y != 0 && x != 0) {
			counter += getCont(y-1, x-1);				
		}		
		//top middle
		if (y != 0) {
			counter += getCont(y-1, x);			
		}
		//top right
		if (y != 0 && x != getWidth()-1) {
			counter += getCont(y-1, x+1);
		}
		//current left
		if (x != 0) {
			counter += getCont(y, x-1);
		}
		//current right
		if (x != getWidth()-1) {
			counter += getCont(y, x+1);
		}
		//bottom left
		if (y != getHeight()-1 && x!= 0) {
			counter += getCont(y+1, x-1);
		}		
		//bottom middle
		if (y != getHeight()-1) {
			counter += getCont(y+1, x);
		}		
		//bottom right
		if (y != getHeight()-1 && x!= getWidth()-1) {
			counter += getCont(y+1, x+1);
		}
		} catch (Exception e) {
			System.out.println(e);
		}
		return counter;
	}
	
	public void run() {
		for (int y=0; y<getHeight(); y++) {
			for (int x=0; x<getWidth(); x++) {
				if (oldGrid[y][x] == 1) {
					if (getNeighbours(y, x) < 2) {
						newGrid[y][x] = 0;					
					} else if (getNeighbours(y, x) > 3) {
						newGrid[y][x] = 0; 
					} else {
						newGrid[y][x] = 1;
					}
				} else {
					if (getNeighbours(y, x) == 3) {
						newGrid[y][x] = 1;
					}
				}
			}
		}
		oldGrid = newGrid;
		newGrid = swapGrid; 
	}

	
	
	public static void main(String[] args) throws FileNotFoundException {
		cgolGui board = new cgolGui(12, 20, "seed.txt");
		board.printGrid(); 
		System.out.println("Width is: "+board.getWidth());
		System.out.println("Height is: "+board.getHeight());
		
		//System.out.println(board.getNeighbours(0,1));
		
		board.run();
		board.printGrid();
		board.run();
		board.printGrid();
		//System.out.println(board.newGrid[0][3]);
		}
	
}
		
