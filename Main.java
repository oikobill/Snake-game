import java.io.*;
import java.util.PriorityQueue;
import java.awt.Font;

public class Main {
    
    // High Scores Screen
     public static void highScores(int[] scores)
    {
        // High Score Dimensions
        StdDraw.setXscale(0.0, 16.0);
        StdDraw.setYscale(0.0, 16.0);
        
        // High Scores Background
        StdDraw.picture(8.0, 8.0, "backgrounds/stars.jpg");
       
        // High Scores Title
        StdDraw.setPenColor(StdDraw.YELLOW);
        Font font = new Font("Arial", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.text(8.0, 13.0, "High Scores");
        
        // create Main Body
        //StdDraw.setPenColor(StdDraw.GRAY);
        //StdDraw.filledRectangle(8.0, 6.0, 2.0, 5.5);
        
        // create list of high scores
        StdDraw.setPenColor(StdDraw.WHITE);
        font = new Font("Arial", Font.PLAIN, 20);
        StdDraw.setFont(font);
        for (int i = 1; i <= 10; i++) 
        { 
            StdDraw.text(6.5, 11.5 - i, i + ": ");
        }
        for (int i = 0; i < 10; i++) 
        { 
            StdDraw.text(8.5, 10.5 - i, scores[i] + "");
       }
                
        // Back Button
        StdDraw.setPenColor(StdDraw.GRAY);
        StdDraw.filledRectangle(14.5, 0.5, 1.5, 0.5);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.text(14.5, 0.5, "Back");
        
        // Button Functionality
         while (true) 
        {
            if (StdDraw.mousePressed())
            {
                if (StdDraw.mouseX() >= 13.0 && StdDraw.mouseX() <= 16.0 &&
                    StdDraw.mouseY() >= 0.0 && StdDraw.mouseY() <= 1.0)
                {
                    StdDraw.clear();
                    MainMenu(scores);
                }
            }            
         }        
    }
     
    // GameOver screen 
    public static void gameOver(int[] scores, int current_score) 
    {   StdDraw.clear();    
        
        // Game Over screen dimensions
        StdDraw.setXscale(0.0, 16.0);
        StdDraw.setYscale(0.0, 16.0);
        
        // Game Over Background        
        //StdDraw.picture(8.0, 8.0, "gameover.png");
        String fileName = "txt/HighScores.txt";
        int temp1 = 0;
        int temp2 = 0;
        
        // comparing last score (11th entry in file) to the 10 scores in array
        for (int i = 0; i < 10; i++)
        {
            if (current_score > scores[i])
            {
                temp1 = scores[i];                
                for (int j = i; j < 9; j++)
                {
                    temp2 = scores[j + 1];
                    scores[j + 1] = temp1;
                    temp1 = temp2; 
                }
                
                scores[i] = current_score;
                break;
            }
        }
        try
        {
            FileWriter fileWriter = new FileWriter(fileName);
            
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            
            for (int i = 0; i < 10; i++)
            {
                bufferedWriter.write("" + scores[i]);
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        }
        
        catch (IOException ex)
        {
            StdOut.println("Error writing file '" + fileName + "'");
        }
        
        // Main Menu High Scores
        StdDraw.picture(8.0, 8.0, "buttons/blanksquare.png");
        StdDraw.text(8.0, 8.0, "Score: "+current_score);
        
        // Main Menu Retry
        StdDraw.picture(8.0, 4.0, "buttons/retry.png");
        
        // Main Menu Retry
        StdDraw.picture(8.0, 1.0, "buttons/menu.png");        
        StdDraw.show();
        
        while(true){
            // Button Interactions
            if (StdDraw.mousePressed()) 
            {       
                // if Retry clicked, initiate new Game
                if (StdDraw.mouseX() >= 5.0 && StdDraw.mouseX() <= 11.0 && 
                    StdDraw.mouseY() >= 4.0 && StdDraw.mouseY() <= 7.0) 
                {
                    Game game = new Game(scores);
                    game.startGame();               
                }
                // if Main Menu button clicked, return to Main Menu
                else if (StdDraw.mouseX() >= 5.0 && StdDraw.mouseX() <= 11.0 
                             && StdDraw.mouseY() >= 0.0 && StdDraw.mouseY() <=  3.0) 
                { 
                    StdDraw.clear();
                    MainMenu(scores);    
                }           
            }
        }
    }
    // Main Menu Screen
    public static void MainMenu(int [] scores){
        
        // Main Menu dimensions
        StdDraw.setXscale(0.0, 16.0);
        StdDraw.setYscale(0.0, 16.0);
        
        // Main Menu backgrounds
        StdDraw.picture(10.0, 10.0, "backgrounds/main_backround.png");

        // Main Menu icons        
        StdDraw.picture(8.0, 14.0, "buttons/logo.png");
        StdDraw.picture(12.5, 14.0, "buttons/app_icon.png");
        // Main Menu Play
        StdDraw.picture(8.0, 9.0, "buttons/play.png");
        
        // Main Menu High Scores
        StdDraw.picture(8.0, 5.0, "buttons/highscore.png");
        
        // Main Menu Exit
        StdDraw.picture(15.5, 15.5, "buttons/exit_icon.png");
        
        // Buttons Functionality
        while (true) 
        {
            if (StdDraw.mousePressed())
            {
                //  if Exit clicked, exit the program
                if (StdDraw.mouseX() >= 15.5 && StdDraw.mouseX() <= 16.0 &&
                    StdDraw.mouseY() >= 15.5 && StdDraw.mouseY() <= 16.0) 
                {
                    System.exit(0);
                }
                // if Play clicked
                else if (StdDraw.mouseX() >= 4.0 && StdDraw.mouseX() <= 12.0 &&
                         StdDraw.mouseY() >= 8.0 && StdDraw.mouseY() <= 10.0) 
                {  
                    Game game = new Game(scores);
                    game.startGame();
                }
                // if High Scores clicked
                else if (StdDraw.mouseX() >= 5.0 && StdDraw.mouseX() <= 11.0 &&
                         StdDraw.mouseY() >= 4.0 && StdDraw.mouseY() <= 6.0) 
                {
                    StdDraw.clear();
                    highScores(scores);                    
                }                
            }          
        }
    }
    public static void main(String[] args) {
        
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>(10);
        
        // read high scores from txt file
        String fileName = "txt/HighScores.txt";        
        try
        {
            FileReader fileReader = new FileReader(fileName);
            
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            
            for (int i = 0; i < 10; i++)
            {
                queue.add(-1*Integer.parseInt(bufferedReader.readLine()));
            }
            bufferedReader.close();
        }catch (FileNotFoundException ex)
        {
            StdOut.println("Unable to open file '" + fileName + "'");
        }
        catch (IOException ex)
        {
            StdOut.println("Error reading file '" + fileName + "'");
        }

        // move elements from Priority Queue to Array
        int[] scores = new int[10];
        for (int i = 0; i < 10; i++)
            {
                scores[i] = -1*queue.poll();
            }        
        MainMenu(scores);
    }
}