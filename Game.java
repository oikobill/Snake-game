/*
* The board that we use to represent our grid:
* 0 represents an empty tile 
* 1 represents a tile occupied by the Snake's body
* 2 represents the head of the Snake (represented with red in the app)
* 3 represents the butt of the Snake
* 4 Food tile
* 5 Obstacle tile
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
    //current score
    int current_score=0;
    // render time 
    int render_time = 130;
    // previous score
    int previous_score=0;
    // difficulty level of game
    int difficulty;
    
    public Game(int difficulty) {
        this.difficulty = difficulty; // determined from Options screen
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
    }
        
    public void generate_food(int num_foods) {
        /* Randomly generates tiles that will be the "target"(we call it food)*/
        int x_cor;
        int y_cor;
        for (int i=0; i<num_foods;i++) {
            do {
                x_cor = (int) (Math.random()*this.windowWidth);
                y_cor = (int) (Math.random()*this.windowHeight);
            } while(this.board[x_cor][y_cor]!=0 || x_cor==0 || x_cor==windowWidth-1 ||
                y_cor==0 || y_cor==windowHeight-1);
            this.board[x_cor][y_cor]=4;
        }
    }
    
    private void initialize(int num_foods) {
        /* Creates a snake in the middle of the window*/
        // set head
        board[head_coordinates[0]][head_coordinates[1]] = 2;
        for (int i=1; i<=4; i++) {
            board[windowWidth/2 - i][windowHeight/2] = 1;
        }
        //  generate (4 * difficulty) obstacles
        int rate = 4 * this.difficulty;
        for (int i=0; i < rate;i++) {
            // find obstacle coordinates
            int x = (int) ((Math.random()*this.windowWidth + i * this.windowWidth) / rate); // space obstacles nicely; ensures reachability of food
            int y = (int) (Math.random()*this.windowHeight);
            if (x==0) x+=1;
            else if (x==windowWidth-1) x-=1;
            if (y==0) y+=1;
            else if (y==windowHeight-1) y-=1;
            // draw obstacle
            if (board[x][y]==0) {
                board[x][y] = 5;
                board[x][y-1] = 5;
                board[x-1][y] = 5;
                board[x][y+1] = 5;
                board[x+1][y] = 5;
            }
        }
        
        // randomly generate food
        generate_food(num_foods);
    }
    
    private void render() {
        // Renders the board
        StdDraw.clear();
        StdDraw.picture(windowWidth*0.5, windowHeight*0.5, 
            "backgrounds/game.png", windowWidth*1.0, windowHeight*1.0);
        
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
                    StdDraw.picture(i+0.5, j+0.5, "buttons/mouse_icon.png", 1.5, 1.5) ;
                }
                else if (this.board[i][j]==1 || this.board[i][j] == 3) {
                    StdDraw.setPenColor(StdDraw.BLACK);
                    StdDraw.filledSquare(i+0.5, j+0.5, 0.5);
                } else if (this.board[i][j]==2) {
                    StdDraw.setPenColor(StdDraw.RED);
                    StdDraw.filledSquare(i+0.5, j+0.5, 0.5);
                } else if (this.board[i][j]==5) {
                    StdDraw.setPenColor(StdDraw.BLUE);
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
        StdDraw.setXscale(0.0, windowWidth);
        StdDraw.setYscale(0.0, windowHeight); 
        this.initialize(2);
        
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
            
            // update speed of snake based on score
            if (render_time > 100) {
                StdDraw.show(render_time-3*current_score);
            } else if (render_time > 80){
                StdDraw.show(render_time-2*current_score);
            } else if (render_time > 60){ // min render time = 60
                StdDraw.show(render_time-1*current_score);
             }                
                
            // check for Gameover and update the board array/coordinates
            try {
                Update.updateBoard(this);
            } catch(Exception e) {
                // Game Over
                Main.gameOver(this.current_score);
            }
            
        }
    }
}
