package chart;

import java.util.Arrays;

public class Chart_lv1 implements Chart {
	String[][] arr;
	int cursorX;
	int cursorY;

	public Chart_lv1() {
		super();
		arr = new String[50][50];
		cursorX = 0;
		cursorY = 0;
	}
	
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
	
	@Override
	public String print() {
		// TODO Auto-generated method stub
		if (arr[cursorX][cursorY] == null) {
			return "EMPTY";
		}
		return arr[cursorX][cursorY];
	}
	
	void delete() {
		// TODO Auto-generated method stub
		arr[cursorX][cursorY] = null;
	}

	void writeValue(String s) {
		arr[cursorX][cursorY] = s;
	}

	void move(String[] moves) {
		// TODO Auto-generated method stub
		int iternum = 1;
		String dir = "";
		for (String move : moves) {
			if ("urld".contains(move)) {
				cursorMove(dir, iternum);
				iternum = 1;
				dir = move;
			} else {
				iternum = Integer.parseInt(move);
			}
		}
		cursorMove(dir, iternum);
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
			if(i==0) {
				break;
			}
			i--;
			System.out.println(Arrays.toString(Arrays.copyOfRange(row, 0, 5)));
		}
	}
	
	void mode(String s) {
		// TODO Auto-generated method stub	
	}
	
	void replace(String str1, String str2) {
		// TODO Auto-generated method stub	
	}
}
