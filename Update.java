public class Update {
    public static void updateBoard(Game game) {
        if (game.current_direction.equals("r")) {
            butt(game);
            right(game);
            return;
        } else if(game.current_direction.equals("l")) {
            butt(game);
            left(game);
            return;
        } else if(game.current_direction.equals("u")) {
            butt(game);
            up(game);
            return;
        } else if(game.current_direction.equals("d")) {
            butt(game);
            down(game);
            return;
        }
    }
    
    private static void right(Game game) throws IndexOutOfBoundsException {
        // check if self eating
        if ((game.board[game.head_coordinates[0]+1][game.head_coordinates[1]]==1) ||
            (game.board[game.head_coordinates[0]+1][game.head_coordinates[1]]==3)) {
            throw new IndexOutOfBoundsException();
        }
        // Check if ypu hit an obstacle
        if ((game.board[game.head_coordinates[0]+1][game.head_coordinates[1]]==5)) {
            throw new IndexOutOfBoundsException();
        }
        // check if you eat food
        if (game.board[game.head_coordinates[0]+1][game.head_coordinates[1]]==4) {
            game.current_score += 1; 
            game.generate_food(1);
            enhance_butt(game);
        }
        //head update
        game.board[game.head_coordinates[0]+1][game.head_coordinates[1]] = 2;
        game.board[game.head_coordinates[0]][game.head_coordinates[1]] = 1;
        game.head_coordinates[0]++;
    } 
    private static void left(Game game) throws IndexOutOfBoundsException {
        // check if self eating
        if ((game.board[game.head_coordinates[0]-1][game.head_coordinates[1]]==1) ||
            (game.board[game.head_coordinates[0]-1][game.head_coordinates[1]]==3)) {
            throw new IndexOutOfBoundsException();
        }
        // Check if ypu hit an obstacle
        if ((game.board[game.head_coordinates[0]-1][game.head_coordinates[1]]==5)) {
            throw new IndexOutOfBoundsException();
        }
        // check if you eat food
        if (game.board[game.head_coordinates[0]-1][game.head_coordinates[1]]==4) {
            game.current_score += 1; 
            game.generate_food(1);  
            enhance_butt(game);
        }                
        //head update
        game.board[game.head_coordinates[0]-1][game.head_coordinates[1]] = 2;
        game.board[game.head_coordinates[0]][game.head_coordinates[1]] = 1;
        game.head_coordinates[0]--;
    }
    private static void up(Game game) throws IndexOutOfBoundsException {
        // check if self eating
        if ((game.board[game.head_coordinates[0]][game.head_coordinates[1]+1]==1) ||
            (game.board[game.head_coordinates[0]][game.head_coordinates[1]+1]==3)) {
            throw new IndexOutOfBoundsException();
        }
        // Check if ypu hit an obstacle
        if ((game.board[game.head_coordinates[0]][game.head_coordinates[1]+1]==5)) {
            throw new IndexOutOfBoundsException();
        }
        // check if you eat food
        if (game.board[game.head_coordinates[0]][game.head_coordinates[1]+1]==4) {
            game.current_score += 1; 
            game.generate_food(1);
            enhance_butt(game);
        }
        //head update
        game.board[game.head_coordinates[0]][game.head_coordinates[1]+1] = 2;
        game.board[game.head_coordinates[0]][game.head_coordinates[1]] = 1;
        game.head_coordinates[1]++;
    }
    private static void down(Game game) throws IndexOutOfBoundsException {
        // check if self eating
        if ((game.board[game.head_coordinates[0]][game.head_coordinates[1]-1]==1) ||
            (game.board[game.head_coordinates[0]][game.head_coordinates[1]-1]==3)) {
            throw new IndexOutOfBoundsException();
        } 
        // Check if ypu hit an obstacle
        if ((game.board[game.head_coordinates[0]][game.head_coordinates[1]-1]==5)) {
            throw new IndexOutOfBoundsException();
        }
        // check if you eat food
        if (game.board[game.head_coordinates[0]][game.head_coordinates[1]-1]==4) {
            game.current_score += 1; 
            game.generate_food(1);
            enhance_butt(game);
        }
        //head update
        game.board[game.head_coordinates[0]][game.head_coordinates[1]-1] = 2;
        game.board[game.head_coordinates[0]][game.head_coordinates[1]] = 1;
        game.head_coordinates[1]--;
    }
    
    private static void butt(Game game) {
        //butt updates  
        // right
        if(game.butt_coordinates[0] < game.windowWidth - 1  && game.board[game.butt_coordinates[0] + 1] [game.butt_coordinates[1]] == 1) {
            game.board[game.butt_coordinates[0]] [game.butt_coordinates[1]] = 0;
            game.board[game.butt_coordinates[0] + 1][game.butt_coordinates[1]]=3;
            game.butt_coordinates[0]++;
        }
        // left
        else if(game.butt_coordinates[0] > 0 && game.board[game.butt_coordinates[0] - 1] [game.butt_coordinates[1]] == 1 ) {
            game.board[game.butt_coordinates[0]] [game.butt_coordinates[1]] = 0;
            game.board[game.butt_coordinates[0] - 1][game.butt_coordinates[1]]=3;
            game.butt_coordinates[0]--;
        }
        // up
        else if( game.butt_coordinates[1] < game.windowHeight - 1 && game.board[game.butt_coordinates[0]] [game.butt_coordinates[1] + 1] == 1 ) {
            game.board[game.butt_coordinates[0]] [game.butt_coordinates[1]] = 0;
            game.board[game.butt_coordinates[0]][game.butt_coordinates[1]+1]=3;
            game.butt_coordinates[1]++;
        }
        // down
        else if(game.butt_coordinates[1] > 0 && game.board[game.butt_coordinates[0]][game.butt_coordinates[1] - 1] == 1 ) {
            game.board[game.butt_coordinates[0]][game.butt_coordinates[1]] = 0;
            game.board[game.butt_coordinates[0]][game.butt_coordinates[1]-1]=3;
            game.butt_coordinates[1]--;       
        }
    }
    
    private static void enhance_butt(Game game) {
        // enhance left
        if(game.butt_coordinates[0] < game.windowWidth - 1  && game.board[game.butt_coordinates[0] + 1][game.butt_coordinates[1]] == 1) {
            game.board[game.butt_coordinates[0]] [game.butt_coordinates[1]] = 1;
            game.board[game.butt_coordinates[0] - 1][game.butt_coordinates[1]]=3;
            game.butt_coordinates[0]--;
        }
        // enhance right
        else if(game.butt_coordinates[0] > 0 && game.board[game.butt_coordinates[0] - 1] [game.butt_coordinates[1]] == 1 ) {
            game.board[game.butt_coordinates[0]] [game.butt_coordinates[1]] = 1;
            game.board[game.butt_coordinates[0] + 1][game.butt_coordinates[1]]=3;
            game.butt_coordinates[0]++;
        }
        // up
        else if( game.butt_coordinates[1] < game.windowHeight - 1 && game.board[game.butt_coordinates[0]] [game.butt_coordinates[1] + 1] == 1 ) {
            game.board[game.butt_coordinates[0]] [game.butt_coordinates[1]] = 1;
            game.board[game.butt_coordinates[0]][game.butt_coordinates[1] - 1] = 3;
            game.butt_coordinates[1]--;
        }
        // down
        else if(game.butt_coordinates[1] > 0 && game.board[game.butt_coordinates[0]][game.butt_coordinates[1] - 1] == 1 ) {
            game.board[game.butt_coordinates[0]][game.butt_coordinates[1]] = 1;
            game.board[game.butt_coordinates[0]][game.butt_coordinates[1] + 1]=3;
            game.butt_coordinates[1]++;       
        }
    }
}














