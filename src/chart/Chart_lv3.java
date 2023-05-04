package chart;

public class Chart_lv3 extends Chart_lv2 implements Chart {
	int mode;

	public Chart_lv3() {
		super();
		mode = 0;
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
		} else if(cmd.startsWith("REPLACE")) {
			String[] str = cmd.substring(8).replace("\\\'", "\b").split("' '");
			replace(str[0].replace("\b", "\\\'").substring(1), str[1].replace("\b", "\\\'").substring(0,str[1].length()-1));
		} else if(cmd.startsWith("MODE")) {
			MODE(cmd.split(" ")[1]);
		} else {
			move(cmd.split(" "));
		}
	}
	
	@Override
	void write(String s, int x, int y) {
		// TODO Auto-generated method stub
		if (mode==0) {
			super.write(s, x, y);
		}else if(mode==1) {
			arr[x][y] = s + (s.length()<arr[x][y].length()?arr[x][y].substring(s.length()):"");
		}else if(mode==2) {
			arr[x][y] += s;
		}
	}
	
	void MODE(String md) {
		if (md.startsWith("basic")) {
			mode = 0;
		} else if(md.startsWith("insert")) {
			mode = 1;
		} else if(md.startsWith("append")) {
			mode = 2;
		}
	}
	
	void replace(String str1, String str2) {
		workRange(new Worker() {
			@Override
			void work(Chart c, int x, int y, String... args) {
				// TODO Auto-generated method stub
				((Chart_lv3) c).replaceOne(x, y,str1,str2);
			}
		});
	}
	
	void replaceOne(int x, int y, String str1, String str2) {
		arr[x][y].replace(str1, str2);
	}
	
	
	
}
