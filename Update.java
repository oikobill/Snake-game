import java.util.TimerTask;

public class Update {
	/* direction in which the snake is heading: 
	'u' -> up
	'd' -> down
	'l' -> left
	'r' -> right
	*/ 
	char direction;
	int[][] board;

	public Update(int[][] board, char direction) {
		this.direction = direction;
		this.board = board;
	}

	public void update_position() {
		
	}

	public static void main(String[] args) {
	}
}