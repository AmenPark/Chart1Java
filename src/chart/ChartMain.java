package chart;

public class ChartMain {

	public static void main(String[] args) {
//		Chart chart = new Chart_lv2();
//		doTest(chart, "lv2.txt");
		String a = "abcde";
		String b = "123";
	}

	public static void doTest(Chart chart, String arg) {
		String[] cmds = FileRead.readFile(arg);
		for (String cmd : cmds) {
			if (cmd == null) {
				break;
			}
			chart.insertCommand(cmd);
			
		}
	}
}
