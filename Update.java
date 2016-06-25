public class Update {
<<<<<<< HEAD
    public static void updateBoard(Game game) {
        if (game.current_direction.equals("r")) {
            right(game);
            butt(game);
            return;
        } else if(game.current_direction.equals("l")) {
            left(game);
            butt(game);
            return;
        } else if(game.current_direction.equals("u")) {
            up(game);
            butt(game);
            return;
        } else if(game.current_direction.equals("d")) {
            down(game);
            butt(game);
            return;
        }
    }
    
    public static void right(Game game) {
        //head update
        game.board[game.head_coordinates[0]+1][game.head_coordinates[1]] = 2;
        game.board[game.head_coordinates[0]][game.head_coordinates[1]] = 1;
        game.head_coordinates[0]++;
    } 
    public static void left(Game game) {
        //head update
        game.board[game.head_coordinates[0]-1][game.head_coordinates[1]] = 2;
        game.board[game.head_coordinates[0]][game.head_coordinates[1]] = 1;
        game.head_coordinates[0]--;
    }
    public static void up(Game game) {
        //head update
        game.board[game.head_coordinates[0]][game.head_coordinates[1]+1] = 2;
        game.board[game.head_coordinates[0]][game.head_coordinates[1]] = 1;
        game.head_coordinates[1]++;
    }
    public static void down(Game game) {
        //head update
        game.board[game.head_coordinates[0]][game.head_coordinates[1]-1] = 2;
        game.board[game.head_coordinates[0]][game.head_coordinates[1]] = 1;
        game.head_coordinates[1]--;
    }
    public static void butt(Game game) {
        //butt update
        //ensure no array out of bounds horizontally
        if (game.butt_coordinates[0] < game.windowWidth - 1 && game.butt_coordinates[0] > 0) {
            //right
            if(game.board[game.butt_coordinates[0] + 1] [game.butt_coordinates[1]] == 1) {
                game.board[game.butt_coordinates[0] + 1] [game.butt_coordinates[1]] = 3; 
                game.board[game.butt_coordinates[0]] [game.butt_coordinates[1]] = 0;
                game.butt_coordinates[0]++;
            }
            else if(game.board[game.butt_coordinates[0] - 1] [game.butt_coordinates[1]] == 1) {
                game.board[game.butt_coordinates[0] - 1] [game.butt_coordinates[1]] = 3;
                game.board[game.butt_coordinates[0]] [game.butt_coordinates[1]] = 0;
                game.butt_coordinates[0]--;
            }
        }
        //ensure no array out of bounds vertically
        if (game.butt_coordinates[1] < game.windowHeight - 1 && game.butt_coordinates[1] > 0) {
            if(game.board[game.butt_coordinates[0]] [game.butt_coordinates[1] + 1] == 1) {
                game.board[game.butt_coordinates[0]]  [game.butt_coordinates[1] + 1] = 3;
                //game.board[game.butt_coordinates[0]] [game.butt_coordinates[1]] = 0;
                game.butt_coordinates[1]++;
            }
            else if(game.board[game.butt_coordinates[0]] [game.butt_coordinates[1] - 1] == 1) {
                game.board[game.butt_coordinates[0]] 
                    [game.butt_coordinates[1] - 1] = 3;
                game.board[game.butt_coordinates[0]][game.butt_coordinates[1]] = 0;
                game.butt_coordinates[1]--;
            }
=======
        public static void updateBoard(Game game) {
            if (game.current_direction.equals("r")) {
                right(game);
                return;
            } else if(game.current_direction.equals("l")) {
                left(game);
                return;
            } else if(game.current_direction.equals("u")) {
                up(game);
                return;
            } else if(game.current_direction.equals("d")) {
                down(game);
                return;
            }
        }
        
        public static void right(Game game) throws IndexOutOfBoundsException {
            // check if self eating
            if ((game.board[game.head_coordinates[0]+1][game.head_coordinates[1]]==1) ||
                (game.board[game.head_coordinates[0]+1][game.head_coordinates[1]]==3)) {
                throw new IndexOutOfBoundsException();
            }
            // check if you eat food
            if (game.board[game.head_coordinates[0]+1][game.head_coordinates[1]]==4) {
                game.current_score += 1; 
                game.generate_food(1);
            }
            //head update
            game.board[game.head_coordinates[0]+1][game.head_coordinates[1]] = 2;
            game.board[game.head_coordinates[0]][game.head_coordinates[1]] = 1;
            game.head_coordinates[0]++;
        } 
        public static void left(Game game) throws IndexOutOfBoundsException {
                // check if self eating
                if ((game.board[game.head_coordinates[0]-1][game.head_coordinates[1]]==1) ||
                    (game.board[game.head_coordinates[0]-1][game.head_coordinates[1]]==3)) {
                    throw new IndexOutOfBoundsException();
                }
                // check if you eat food
                if (game.board[game.head_coordinates[0]-1][game.head_coordinates[1]]==4) {
                    game.current_score += 1; 
                    game.generate_food(1);
                }
                
                //head update
                game.board[game.head_coordinates[0]-1][game.head_coordinates[1]] = 2;
                game.board[game.head_coordinates[0]][game.head_coordinates[1]] = 1;
                game.head_coordinates[0]--;
        }
        public static void up(Game game) throws IndexOutOfBoundsException {
                // check if self eating
            if ((game.board[game.head_coordinates[0]][game.head_coordinates[1]+1]==1) ||
                (game.board[game.head_coordinates[0]][game.head_coordinates[1]+1]==3)) {
                throw new IndexOutOfBoundsException();
            }
                // check if you eat food
                if (game.board[game.head_coordinates[0]][game.head_coordinates[1]+1]==4) {
                    game.current_score += 1; 
                    game.generate_food(1);
                }
                //head update
                game.board[game.head_coordinates[0]][game.head_coordinates[1]+1] = 2;
                game.board[game.head_coordinates[0]][game.head_coordinates[1]] = 1;
                game.head_coordinates[1]++;
        }
        public static void down(Game game) throws IndexOutOfBoundsException {
                // check if self eating
                if ((game.board[game.head_coordinates[0]][game.head_coordinates[1]-1]==1) ||
                    (game.board[game.head_coordinates[0]][game.head_coordinates[1]-1]==3)) {
                    throw new IndexOutOfBoundsException();
                }
                // check if you eat food
                if (game.board[game.head_coordinates[0]][game.head_coordinates[1]-1]==4) {
                        game.current_score += 1; 
                        game.generate_food(1);
                }
                //head update
                game.board[game.head_coordinates[0]][game.head_coordinates[1]-1] = 2;
                game.board[game.head_coordinates[0]][game.head_coordinates[1]] = 1;
                game.head_coordinates[1]--;
>>>>>>> 90d2ae3e3e4bae34488a09fa93d50ae5f1ad44cb
        }
    }

}

