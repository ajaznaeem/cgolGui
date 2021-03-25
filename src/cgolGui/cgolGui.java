package cgolGui;

public class cgolGui {
	
	int width;
	int height;
	int[][] grid; 
	
	public cgolGui(int width, int height) {
		this.width = width;
		this.height = height; 
		this.grid = new int[width][height]; 		
	}
	
	public void printGrid() {
		System.out.println("---------");
		
			for (int i=0; i<height; i++) {
				String line = "|";
				for (int j=0; j<width; j++) {
					if (grid[j][i]==0) {
						line += ".";
					} else {
						line += "*";
					}
				}
				line += "|";
				System.out.println(line);	
			}	
			
		System.out.println("---------\n");
	}
	
	public void setAlive(int x, int y) {
		this.grid[x][y] = 1; 
	}
	
	public void setDead(int x, int y) {
		this.grid[x][y] = 0; 
	}
	
	public static void main(String[] args) {
		cgolGui board = new cgolGui(8, 5);
		board.setAlive(3,3);
		board.setAlive(4,3);
		board.setAlive(5,3);
		board.setAlive(6,3);
		board.printGrid();
		
	}
}
