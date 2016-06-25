public class Update {
	/* direction in which the snake is heading: 
	'u' -> up
	'd' -> down
	'l' -> left
	'r' -> right
	*/ 
	// char direction;
	int[][] board;
	int[] head;
	int[] butt;

	public Update(int[][] board) {
		// this.direction = direction;
		this.board = board;
	}

	public int[][] updateBoard() {
		int[][] tmp = new int[2][];
		this.head[0] = head[0]+1;
		this.head[1] = head[1];
		this.butt[0] = butt[0]+1;
		this.butt[1] = butt[1];
		tmp[0] = head;
		tmp[1] = butt;
		return tmp;
	}
}