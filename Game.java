public class Game {
    int windowHeight;
    int windowWidth;
    int[][] board;
    int[] head_coordinates;
    int[] butt_coordinates;
    String current_direction;
    int[] scores;
    int current_score=0;
    
    public Game(int[] scores) {
        this.windowWidth=60;
        this.windowHeight=60;
        this.board = new int[60][60];
        head_coordinates = new int[2];
        butt_coordinates = new int[2];
        this.head_coordinates[0] = windowWidth/2;
        this.head_coordinates[1] = windowHeight/2;
        this.butt_coordinates[0] = windowWidth/2 - 4;
        this.butt_coordinates[1] = windowHeight/2;
        this.current_direction = "r";
        this.scores = scores;
    }
    
    public Game(int height, int width, int[] scores) {
        this.windowWidth=width;
        this.windowHeight=height;
        this.board = new int[width][height];
        this.head_coordinates[0] = windowWidth/2;
        this.head_coordinates[1] = windowHeight/2;
        this.butt_coordinates[0] = windowWidth/2 - 4;
        this.butt_coordinates[1] = windowHeight/2;
        this.current_direction = "r";
        this.scores = scores;
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
        StdDraw.clear();
        this.drawWindow();
        this.initializeSnake(2);
        while (true) {
            //If up arrows are pressed
            if (StdDraw.isKeyPressed(38) && !(this.current_direction.equals("d"))) {
                this.current_direction = "u";
            }
            else if (StdDraw.isKeyPressed(37) && !(this.current_direction.equals("r"))) {
                this.current_direction = "l";
            }
            else if (StdDraw.isKeyPressed(39) && !(this.current_direction.equals("l"))) {
                this.current_direction = "r";
            }
            else if (!(this.current_direction.equals("u")) && StdDraw.isKeyPressed(40)) {
                this.current_direction = "d";
            }
            this.render();
            StdDraw.show(100);
            try {
                Update.updateBoard(this);
            } catch(Exception e) {
                // Game Over Menu goes here!
                Main.gameOver(this.scores);
            }
            
        }
    }
}