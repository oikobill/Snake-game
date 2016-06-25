public class Game {
	private int windowHeight;
	private int windowWidth;
	private int[][] board;

	public Game() {
		this.windowWidth=60;
		this.windowHeight=60;
		this.board = new int[60][60];
	}

	public Game(int height, int width) {
		this.windowWidth=width;
		this.windowHeight=height;
		this.board = new int[width][height];
	}

	public void drawWindow() {
        StdDraw.setXscale(0.0, windowWidth);
        StdDraw.setYscale(0.0, windowHeight);       
        StdDraw.show();
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
		board[windowHeight/2][windowHeight/2] = 1;
		for (int i=1; i<=3; i++) {
			board[windowHeight/2 - i][windowHeight/2] = 2;
		}
		// set butt
		board[windowHeight/2 - 4][windowHeight / 2] =  3;

		// randomly generate food

		generate_food(num_foods);

	}

	public void render() {
		for(int i=0; i<this.windowWidth; i++) {
			for (int j=0; j<this.windowHeight;j++) {
				if (this.board[i][j]==2 |this.board[i][j]==3) {
					StdDraw.setPenColor(StdDraw.BLACK);
					StdDraw.filledSquare(i+0.5, j+0.5, 0.5);
				} else if (this.board[i][j]==1) {
					StdDraw.setPenColor(StdDraw.RED);
					StdDraw.filledSquare(i+0.5, j+0.5, 0.5);
				} else if (this.board[i][j]==4){
					StdDraw.setPenColor(StdDraw.BLUE);
					StdDraw.filledSquare(i+0.5, j+0.5, 0.5);
				}
			}
		}
	}

    public static void main(String[] args) {  
    	Game game = new Game();    
        game.drawWindow();
        game.initializeSnake(2); // deal somehow with default num_foods
        game.render();
    }
}