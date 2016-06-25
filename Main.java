public class Main {
    public static void MainMenu(){
      
            // Main Menu dimensions
            StdDraw.setXscale(0.0, 16.0);
            StdDraw.setYscale(0.0, 16.0);
            
            // Main Menu Background        
            
            // Main Menu Play
            StdDraw.picture(8.0, 10.0, "play.png");
            
            // Main Menu High Scores
            StdDraw.picture(8.0, 6.0, "highscore.png");
            
            // Main Menu Exit
            StdDraw.picture(8.0, 2.0, "exit.png");
            
            while (true) 
            {
                if (StdDraw.mousePressed())
                {
                    //  if Exit clicked, exit the program
                    if (StdDraw.mouseX() >= 5.0 && StdDraw.mouseX() <= 11.0 &&
                        StdDraw.mouseY() >= 1.5 && StdDraw.mouseY() <= 3.6) 
                    {
                        System.exit(0);
                    }
                    // if Play clicked
                    else if (StdDraw.mouseX() >= 4.0 && StdDraw.mouseX() <= 12.0 &&
                             StdDraw.mouseY() >= 9.0 && StdDraw.mouseY() <= 11.0) 
                    {                                        
                        Game game = new Game();    
                        game.drawWindow();
                        game.initializeSnake(2); // deal somehow with default num_foods
                        game.render();
                        break;
                    }
                    // if High Scores clicked
                    else if (StdDraw.mouseX() >= 5.0 && StdDraw.mouseX() <= 11.0 &&
                             StdDraw.mouseY() >= 5.0 && StdDraw.mouseY() <= 7.0) 
                    {
                        // highScores(scores);
                         break;
                    }
                   
                }
                
            }
     }
     public static void main(String[] args) {
         while(true) MainMenu();
    }
}