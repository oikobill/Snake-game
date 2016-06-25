public class Game {
	private int windowHeight;
	private int windowWidth;
	private int[][] board;
	private int[] head_coordinates;
	private int[] butt_coordinates;

	public Game() {
		this.windowWidth=60;
		this.windowHeight=60;
		this.board = new int[60][60];
		head_coordinates = new int[2];
	    butt_coordinates = new int[2];
		this.head_coordinates[0] = windowWidth/2;
		this.head_coordinates[1] = windowHeight/2;
		this.butt_coordinates[0] = windowWidth/2 - 4;
		this.butt_coordinates[1] = windowHeight/2;
		}

	public Game(int height, int width) {
		this.windowWidth=width;
		this.windowHeight=height;
		this.board = new int[width][height];
		this.head_coordinates[0] = windowWidth/2;
		this.head_coordinates[1] = windowHeight/2;
		this.butt_coordinates[0] = windowWidth/2 - 4;
		this.butt_coordinates[1] = windowHeight/2;
	}

	public void drawWindow() {
		StdDraw.clear();
        StdDraw.setXscale(0.0, windowWidth);
        StdDraw.setYscale(0.0, windowHeight);       
	}

	public void generate_food(int num_foods) {
		int x_cor;
		int y_cor;
		for (int i=0; i<num_foods;i++) {
			do {
				x_cor = (int) (Math.random()*this.windowWidth);
				y_cor = (int) (Math.random()*this.windowHeight);
			} while(this.board[x_cor][y_cor]!=0);
			this.board[x_cor][y_cor]=4;
		}
	}

	public void initializeSnake(int num_foods) {
		// set head
		board[head_coordinates[0]][head_coordinates[1]] = 2;
		for (int i=1; i<=3; i++) {
			board[windowWidth/2 - i][windowHeight/2] = 1;
		}
		// set butt
		board[butt_coordinates[0]][butt_coordinates[1]] =  3;

		// randomly generate food
		generate_food(num_foods);
	}

	public void render() {
		StdDraw.clear();
		for(int i=0; i<this.windowWidth; i++) {
			for (int j=0; j<this.windowHeight;j++) {
				if (this.board[i][j]==1 |this.board[i][j]==3) {
					StdDraw.setPenColor(StdDraw.BLACK);
					StdDraw.filledSquare(i+0.5, j+0.5, 0.5);
				} else if (this.board[i][j]==2) {
					StdDraw.setPenColor(StdDraw.RED);
					StdDraw.filledSquare(i+0.5, j+0.5, 0.5);
				} else if (this.board[i][j]==4){
					StdDraw.setPenColor(StdDraw.BLUE);
					StdDraw.filledSquare(i+0.5, j+0.5, 0.5);
				}
			}
		}
	}
    public void startGame() {  
        this.drawWindow();
        this.initializeSnake(2);
        while (true) {
        	this.render();
        	StdDraw.show(100);
        	try {
        		updateBoard();
        	} catch(ArrayIndexOutOfBoundsException e) {
        		// Game Over Menu goes here!
        		System.out.println("Exception"+ e+" caught!");
        		System.exit(0);
        	}
        	
        }
    }
    
	public void updateBoard() {
		//head update
		this.board[this.head_coordinates[0]+1][this.head_coordinates[1]] = 2;
		this.board[this.head_coordinates[0]][this.head_coordinates[1]] = 1;
		this.head_coordinates[0]++;

		//head update
		this.board[this.butt_coordinates[0]][this.head_coordinates[1]] = 0;
		this.butt_coordinates[0]++;
		this.render();
	}

    public static void main(String[] args) {
    	Game game = new Game();
    	game.startGame();
    }
}