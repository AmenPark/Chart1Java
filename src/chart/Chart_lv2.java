package chart;

import java.util.Arrays;

public class Chart_lv2 implements Chart {
	String[][] arr;
	int cursorX;
	int cursorY;
	int[] range;

	@Override
	public void insertCommand(String cmd) {
		// TODO Auto-generated method stub
		if (cmd.startsWith("'")) {
			writeValue(cmd.substring(1, cmd.length() - 1));
		} else if (cmd.startsWith("PRINT")) {
			System.out.println(print());
		} else if (cmd.startsWith("DELETE")) {
			delete();
		} else {
			move(cmd.split(" "));
		}
	}

	public Chart_lv2() {
		super();
		arr = new String[50][50];
		cursorX = 0;
		cursorY = 0;
		range = new int[2];
		range[0] = 0;
		range[1] = 0;
		// TODO Auto-generated constructor stub
	}

	@Override
	public String print() {
		// TODO Auto-generated method stub
		if (arr[cursorX][cursorY] == null) {
			return "EMPTY";
		}
		return arr[cursorX][cursorY];
	}

	void move_cmd(String[] moves) {
		// TODO Auto-generated method stub
		if (moves[0].startsWith("shift")) {
			range[0] = cursorX;
			range[1] = cursorY;
			moves = Arrays.copyOfRange(moves, 1, moves.length);
			move(moves);
		} else {
			move(moves);
			range[0] = cursorX;
			range[1] = cursorY;
		}
	}

	void workRange(Worker w, String... args) {
		int x = Math.min(range[0], this.cursorX);
		int yMin = Math.min(range[1], this.cursorY);
		int yMax = Math.max(range[1], this.cursorY);
		for (; x <= Math.max(range[0], this.cursorX); x++) {
			for (int y = yMin; y <= yMax; y++) {
				w.work(this, x, y, args);
			}
		}
	}

	void writeValue(String s) {
		s = s.replace("\\\\", "\\").replace("\\n", "\n").replace("\\\'", "\'");
		workRange(new Worker() {
			@Override
			void work(Chart c, int x, int y, String... args) {
				((Chart_lv2) c).write(args[0], x, y);
			}
		}, s);
	}

	void delete() {
		workRange(new Worker() {
			@Override
			void work(Chart c, int x, int y, String... args) {
				// TODO Auto-generated method stub
				((Chart_lv2) c).d(x, y);
			}
		});
	}

	void d(int x, int y) {
		arr[x][y] = null;
	}

	void write(String s, int x, int y) {
		arr[x][y] = s;
	}

	void move(String[] moves) {
		// TODO Auto-generated method stub
		int beforeX = cursorX;
		int beforeY = cursorY;
		int iternum = 1;
		String dir = "";
		String move = null;
		int i = 0;
		if (moves[0].startsWith("shift")) {
			i++;
		}
		for (; i < moves.length; i++) {
			move = moves[i];
			if ("urld".contains(move)) {
				cursorMove(dir, iternum);
				iternum = 1;
				dir = move;
			} else {
				iternum = Integer.parseInt(move);
			}
		}
		cursorMove(dir, iternum);
		if (moves[0].startsWith("shift")) {
			range[0] = beforeX;
			range[1] = beforeY;
		}else {
			range[0] = cursorX;
			range[1] = cursorY;
		}
	}

	void cursorMove(String dir, int iternum) {
		if (dir.contains("u")) {
			cursorUp(iternum);
		} else if (dir.contains("d")) {
			cursorDown(iternum);
		} else if (dir.contains("r")) {
			cursorRight(iternum);
		} else if (dir.contains("l")) {
			cursorLeft(iternum);
		}
	}

	private void cursorRight(int a) {
		for (int i = 0; i < a; i++) {
			cursorRight();
		}
	}

	private void cursorLeft(int a) {
		for (int i = 0; i < a; i++) {
			cursorLeft();
		}
	}

	private void cursorDown(int a) {
		for (int i = 0; i < a; i++) {
			cursorDown();
		}
	}

	private void cursorUp(int a) {
		for (int i = 0; i < a; i++) {
			cursorUp();
		}
	}

	private void cursorUp() {
		if (cursorX > 0) {
			cursorX--;
		}
	}

	private void cursorDown() {
		if (cursorX < 49) {
			cursorX++;
		}
	}

	private void cursorLeft() {
		if (cursorY > 0) {
			cursorY--;
		}
	}

	private void cursorRight() {
		if (cursorY < 49) {
			cursorY++;
		}
	}

	public void show() {
		// TODO Auto-generated method stub
		int i = 5;
		for (String[] row : arr) {
			if (i == 0) {
				break;
			}
			i--;
			System.out.println(Arrays.toString(Arrays.copyOfRange(row, 0, 5)));
		}
	}
}
