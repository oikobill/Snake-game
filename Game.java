/*
* The board that we use to represent our grid:
* 0 represents an empty tile 
* 1 represents a tile occupied by the Snake's body
* 2 represents the head of the Snake (represented with red in the app)
* 3 represents the butt of the Snake
* 4 Food tile
*/

public class Game {
    // Determines window dimensions
    int windowHeight;
    int windowWidth;
    // Represents the grid in which the Snake moves
    int[][] board;
    /* The coordinates for the head of the snake and the last tile that 
    comprises the snake (coordinates in the grid)*/
    int[] head_coordinates;
    int[] butt_coordinates;
    // which way the snake is moving (which one was the last arrow pressed)
    String current_direction;
    // last top 10 scores
    int[] scores;
    //current score
    int current_score=0;

    
    public Game(int[] scores) {
        this.windowWidth=40;
        this.windowHeight=40;
        this.board = new int[windowWidth][windowHeight];
        head_coordinates = new int[2];
        butt_coordinates = new int[2];
        this.head_coordinates[0] = windowWidth/2;
        this.head_coordinates[1] = windowHeight/2;
        this.butt_coordinates[0] = windowWidth/2 - 4;
        this.butt_coordinates[1] = windowHeight/2;
        this.current_direction = "r";
        this.scores = scores;
    }
    
    public void drawWindow() {
    /* Creates a window for the application*/
        StdDraw.clear();
        StdDraw.setXscale(0.0, windowWidth);
        StdDraw.setYscale(0.0, windowHeight);       
    }
    
    public void generate_food(int num_foods) {
        /* Randomly generates tiles that will be the "target"(we call it food)*/
        int x_cor;
        int y_cor;
        for (int i=0; i<num_foods;i++) {
            do {
                x_cor = (int) (Math.random()*this.windowWidth);
                y_cor = (int) (Math.random()*this.windowHeight);
            } while(this.board[x_cor][y_cor]!=0 && x_cor==0 || x_cor==windowWidth-1 ||
                y_cor==0 || y_cor==windowHeight-1);
            this.board[x_cor][y_cor]=4;
        }
    }
    
    public void initializeSnake(int num_foods) {
        /* Creates a snake in the middle of the window*/
        // set head
        board[head_coordinates[0]][head_coordinates[1]] = 2;
        for (int i=1; i<=4; i++) {
            board[windowWidth/2 - i][windowHeight/2] = 1;
        }
                
        // randomly generate food
        generate_food(num_foods);
    }
    
    public void render() {
        // Renders the board
        StdDraw.clear();
        
        // paint fence
        StdDraw.setPenColor(StdDraw.ORANGE);
        StdDraw.setPenRadius(0.015);
        StdDraw.line(0.0, 0.0, 0.0, 1.0 * this.windowHeight);
        StdDraw.line(1.0 * this.windowWidth, 1.0 * this.windowHeight, 0.0, 1.0 * this.windowHeight);
        StdDraw.line(1.0 * this.windowWidth, 0.0, 1.0 * this.windowWidth, 1.0 * this.windowHeight);
        StdDraw.line(1.0 * this.windowWidth, 0.0, 0.0, 0.0);
        
        // render snake and food
        StdDraw.setPenRadius(0.005);
        for(int i=0; i<this.windowWidth; i++) {
            for (int j=0; j<this.windowHeight;j++) {
               if (this.board[i][j]==4){      // food              
                    StdDraw.picture(i+0.5, j+0.5, "buttons/mouse_icon.png", 2.0, 2.0) ;
                }
                else if (this.board[i][j]==1 || this.board[i][j] == 3) {
                    StdDraw.setPenColor(StdDraw.BLACK);
                    StdDraw.filledSquare(i+0.5, j+0.5, 0.5);
                } else if (this.board[i][j]==2) {
                    StdDraw.setPenColor(StdDraw.RED);
                    StdDraw.filledSquare(i+0.5, j+0.5, 0.5);
                } 
            }
        }
    }
    public void startGame() {
        /*Brings evrything together. 
         * Draws the window for the app.
         * Initializes the Snake
         * Includes key listener (triggers actions when arrows are pressed)*/ 
        StdDraw.clear();
        this.drawWindow();
        this.initializeSnake(2);
        while (true) {
            //Up arrow pressed
            if (StdDraw.isKeyPressed(38) && !(this.current_direction.equals("d"))) {
                this.current_direction = "u";
            }
            //Left arrow pressed
            else if (StdDraw.isKeyPressed(37) && !(this.current_direction.equals("r"))) {
                this.current_direction = "l";
            }
            //Right arrow pressed
            else if (StdDraw.isKeyPressed(39) && !(this.current_direction.equals("l"))) {
                this.current_direction = "r";
            }
            //Down arrow pressed
            else if (!(this.current_direction.equals("u")) && StdDraw.isKeyPressed(40)) {
                this.current_direction = "d";
            }
            this.render();
            StdDraw.show(100);
            try {
                Update.updateBoard(this);
            } catch(Exception e) {
                // Game Over Menu goes here!
                Main.gameOver(this.scores, this.current_score);
            }
            
        }
    }
}
