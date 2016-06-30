import java.io.*;
import java.util.PriorityQueue;
import java.awt.Font;
import javax.swing.JOptionPane;

public class Main {
    // global variables holding the names and scores of the 10 highest scoring players
     static String[] playernames = new String[10];
     static int[] scores = new int [10];
     static int difficulty = 1;

    // High Scores Screen
     public static void highScores()
    {
        // High Score Dimensions
        StdDraw.setXscale(0.0, 16.0);
        StdDraw.setYscale(0.0, 16.0);
        
        // High Scores Background
        StdDraw.picture(8.0, 8.0, "backgrounds/hs.png");
       
        // High Scores Title
        StdDraw.setPenColor(StdDraw.WHITE);
        Font font = new Font("Cabin Sketch", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.text(8.0, 13.0, "High Scores");
        
        // create list of high scores
        StdDraw.setPenColor(StdDraw.WHITE);
        
        // print list
        for (int i = 0; i < 10; i++) 
        { 
            font = new Font("Cabin Sketch", Font.PLAIN, 20);
            StdDraw.setFont(font);
            StdDraw.text(6.5 - 0.75, 11.5 - i, i + 1 +".  ");
            StdDraw.text(8.5 - 0.75, 11.5 - i, "" + playernames[i]);
            font = new Font("Cabin Sketch", Font.BOLD, 20);
            StdDraw.setFont(font);
            StdDraw.text(10.5 - 0.75, 11.5 - i, " " + scores[i]);
        }
        
        // Back Button
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.rectangle(15, 0.7, 0.9, 0.5);
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.text(15, 0.65, "Back");
        
        // Button Functionality
         while (true) 
        {
            if (StdDraw.mousePressed())
            {
                // if Back Button clicked, return ro Main Menu
                if (StdDraw.mouseX() >= 13.0 && StdDraw.mouseX() <= 16.0 &&
                    StdDraw.mouseY() >= 0.0 && StdDraw.mouseY() <= 1.0)
                {
                    StdDraw.clear();
                    MainMenu();
                }
            }            
         }        
    }
     
    // GameOver screen 
    public static void gameOver(int current_score) 
    {   StdDraw.clear();    
        // input username
        String current_name = JOptionPane.showInputDialog ( "                   Game Over! \n Enter player name (max 9 characters):" ); 
        // if the user does not give a player name the string becomes 
        // "Uknown player name"
        if (current_name==null) {
            current_name = "Unknown";
        }

        // Game Over screen dimensions
        StdDraw.setXscale(0.0, 16.0);
        StdDraw.setYscale(0.0, 16.0);
        
        // Game Over Background        
        StdDraw.picture(8.0, 8.0, "backgrounds/gameover.jpg", 16.0, 16.0);
        
        // High Scores Title
        StdDraw.setPenColor(StdDraw.GREEN);
        Font font = new Font("Cabin Sketch", Font.BOLD, 50);
        StdDraw.setFont(font);
        StdDraw.text(8.0, 13.0, "Game Over");
        
        // revert Font
        StdDraw.setPenColor(StdDraw.BLACK);
        font = new Font("Arial", Font.PLAIN, 40);
        StdDraw.setFont(font);
                
        // record Score
        String fileName = "txt/HighScores.txt";
        int temp1 = 0;
        int temp2 = 0;
        String temp3="";
        String temp4="";
        
        // comparing last score (11th entry in file) to the 10 scores in array
        for (int i = 0; i < 10; i++)
        {
            if (current_score > scores[i])
            {
                temp1 = scores[i];  
                temp3 = playernames[i];              
                for (int j = i; j < 9; j++)
                {
                    temp2 = scores[j + 1];
                    scores[j + 1] = temp1;
                    temp1 = temp2; 
                    ////////
                    temp4 = playernames[j + 1];
                    playernames[j + 1] = temp3;
                    temp3 = temp4; 
                }
                
                scores[i] = current_score;
                playernames[i] = current_name;
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
            for (int i = 0; i < 10; i++)
            {
                bufferedWriter.write("" + playernames[i]);
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        }
        
        catch (IOException ex)
        {
            StdOut.println("Error writing file '" + fileName + "'");
        }
        
        // Game Over - Score Box
        StdDraw.picture(8.0, 9.5, "buttons/blanksquare.png");
        StdDraw.text(8.0, 9.5, "Score: "+current_score);
        
        // Game Over  - Retry button
        StdDraw.picture(8.0, 5.5, "buttons/retry.png");
        
        // Game Over - Main Menu button
        StdDraw.picture(8.0, 2.5, "buttons/menu.png");        
        StdDraw.show();
        
        // Game Over Exit
        StdDraw.picture(15.5, 15.5, "buttons/exit_icon.png");
                
        while(true){
            // Button Interactions
            if (StdDraw.mousePressed()) 
            {    
                // if Exit button clicked
                if (StdDraw.mouseX() >= 15.0 && StdDraw.mouseX() <= 16.0 &&
                    StdDraw.mouseY() >= 15.0 && StdDraw.mouseY() <= 16.0) 
                {
                    System.exit(0);
                }                
                
                // if Retry clicked, initiate new Game
                else if (StdDraw.mouseX() >= 5.0 && StdDraw.mouseX() <= 11.0 && 
                         StdDraw.mouseY() >= 5.5 && StdDraw.mouseY() <= 8.5) 
                {
                    Game game = new Game(difficulty);
                    game.startGame();               
                }
                // if Main Menu button clicked, return to Main Menu
                else if (StdDraw.mouseX() >= 5.0 && StdDraw.mouseX() <= 11.0 
                             && StdDraw.mouseY() >= 1.5 && StdDraw.mouseY() <=  4.5) 
                { 
                    StdDraw.clear();
                    MainMenu();    
                }           
            }
        }
    }
    
    // Options Screen
    public static void Options() {
        // Options dimensions
        StdDraw.setXscale(0.0, 16.0);
        StdDraw.setYscale(0.0, 16.0);
        
        // Options - Background
        StdDraw.picture(10.0, 10.0, "backgrounds/main_backround.png");
        
        // Options - Exit
        StdDraw.picture(15.5, 15.5, "buttons/exit_icon.png");
        
        // Options - Difficulty
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.picture(8.0, 2.0, "buttons/blanksquare.png", 8.0, 1.7);
        Font font = new Font("Arial", Font.PLAIN, 28);
        StdDraw.setFont(font);        
        StdDraw.text(8.0, 2.0, "Adjust Difficulty");                
        
       // Options - Back Button
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.rectangle(15, 0.7, 0.9, 0.5);
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.text(15, 0.65, "Back");
        
        // Options - Difficulty Box
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.picture(8.0, 9.5, "buttons/blanksquare.png");
        StdDraw.text(8.0, 9.5, "Difficulty Level: "+ difficulty);           
      
        while(true){
            // Button Interactions
            if (StdDraw.mousePressed()) 
            {    
                // if Exit button clicked
                if (StdDraw.mouseX() >= 15.0 && StdDraw.mouseX() <= 16.0 &&
                    StdDraw.mouseY() >= 15.0 && StdDraw.mouseY() <= 16.0) 
                {
                    System.exit(0);
                }  
                // if Adjust difficulty clicked
                else if (StdDraw.mouseX() >= 5.0 && StdDraw.mouseX() <= 11.0 &&
                         StdDraw.mouseY() >= 1.0 && StdDraw.mouseY() <= 3.0) 
                {
                    
                    // check whether input is int
                    try {
                        difficulty = Integer.parseInt(JOptionPane.showInputDialog ("Enter difficulty level (1, 2, or 3): \n (1 = easy, 2 = medium, 3 = hard)"));
                    } catch(Exception e) {
                        difficulty = Integer.parseInt(JOptionPane.showInputDialog ("Please enter proper difficulty level (1, 2, or 3): \n (1 = easy, 2 = medium, 3 = hard)"));
                    }
                    
                    //check whether difficulty level is not proper
                    if( difficulty != 1 && difficulty != 2 && difficulty != 3) {
                        difficulty = Integer.parseInt(JOptionPane.showInputDialog ("Please enter proper difficulty level (1, 2, or 3): \n (1 = easy, 2 = medium, 3 = hard)"));
                    }
                    
                    //reset Options screen  once difficulty changed
                    StdDraw.clear();
                    Options();          
                }   
                // if Back Button clicked, return to Main Menu
                if (StdDraw.mouseX() >= 13.0 && StdDraw.mouseX() <= 16.0 &&
                    StdDraw.mouseY() >= 0.0 && StdDraw.mouseY() <= 1.0)
                {
                    StdDraw.clear();
                    MainMenu();
                }
            }
        }        
    }
    
    // Main Menu Screen
    public static void MainMenu(){
        
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
        
        // Main Menu Options
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.picture(8.0, 2.0, "buttons/blanksquare.png", 8.0, 1.7);
        Font font = new Font("Arial", Font.PLAIN, 28);
        StdDraw.setFont(font);        
        StdDraw.text(8.0, 2.0, "Options");
                
        // Main Menu Exit
        StdDraw.picture(15.5, 15.5, "buttons/exit_icon.png");
        
        // Buttons Functionality
        while (true) 
        {
            if (StdDraw.mousePressed())
            {
                //  if Exit clicked, exit the program
                if (StdDraw.mouseX() >= 15.0 && StdDraw.mouseX() <= 16.0 &&
                    StdDraw.mouseY() >= 15.0 && StdDraw.mouseY() <= 16.0) 
                {
                    System.exit(0);
                }
                // if Play clicked
                else if (StdDraw.mouseX() >= 4.0 && StdDraw.mouseX() <= 12.0 &&
                         StdDraw.mouseY() >= 8.0 && StdDraw.mouseY() <= 10.0) 
                {  
                    Game game = new Game(difficulty);
                    game.startGame();
                }
                // if High Scores clicked
                else if (StdDraw.mouseX() >= 5.0 && StdDraw.mouseX() <= 11.0 &&
                         StdDraw.mouseY() >= 4.0 && StdDraw.mouseY() <= 6.0) 
                {
                    StdDraw.clear();
                    highScores();                    
                }  
                // if Options clicked
                else if (StdDraw.mouseX() >= 5.0 && StdDraw.mouseX() <= 11.0 &&
                         StdDraw.mouseY() >= 1.0 && StdDraw.mouseY() <= 3.0) 
                {
                    StdDraw.clear();
                    Options();                    
                }   
            }          
        }
    }
    
    public static void main(String[] args) {
        
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>(10);
        
        // read high scores from txt file
        String fileName = "txt/HighScores.txt"; 
        // playernames array    
        try
        {
            FileReader fileReader = new FileReader(fileName);
            
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            
            for (int i = 0; i < 10; i++)
            {
                queue.add(-1*Integer.parseInt(bufferedReader.readLine()));
            }
            for (int i = 0; i < 10; i++)
            {
                playernames[i]=bufferedReader.readLine();
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
        for (int i = 0; i < 10; i++)
            {
                scores[i] = -1*queue.poll();
            }        
        MainMenu();
    }
}