package chart;

public class Chart_lv4 extends Chart_lv3 implements Chart {
	String[][] clipboard;

	public Chart_lv4() {
		super();
		mode = 0;
	}

	@Override
	public void insertCommand(String cmd) {
		// TODO Auto-generated method stub
		if (cmd.startsWith("CTRL")) {
			String c = cmd.split(" ")[1];
			if (c.startsWith("X")) {
				ctrlX();
			} else if (c.startsWith("V")) {
				ctrlV();
			} else if (c.startsWith("C")) {
				ctrlC();
			}
		} else {
			super.insertCommand(cmd);
		}
	}
	
	void ctrlX() {
		ctrlC();
		delete();
	}

	void ctrlC() {
		int minX = Math.min(cursorX, range[0]);
		int minY = Math.min(cursorY, range[1]);
		clipboard = new String[Math.abs(cursorX - range[0]) + 1][Math.abs(cursorY - range[1]) + 1];
		workRange(new Worker() {
			@Override
			void work(Chart c, int x, int y, String... args) {
				// TODO Auto-generated method stub
				((Chart_lv4)c).copy(x,y,Integer.parseInt(args[0]), Integer.parseInt(args[1]));
			}
		}, minX+"", minY+"");
	}
	
	void copy(int x, int y, int i, int j) {
		clipboard[i][j] = arr[x][y];
	}

	void ctrlV() {
		if (clipboard != null) {
			for (int x = cursorX; x < Math.min(50, cursorX + clipboard.length); x++) {
				for (int y = cursorY; y < Math.min(50, cursorY + clipboard[0].length); y++) {
					arr[x][y] = clipboard[x - cursorX][y - cursorY];
				}
			}
		}
	}

}
