public class Update {
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
}

