package chart;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class FileRead {
	public static String[] readFile(String path) {
		BufferedReader br = null;
		String[] ret = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(path), "utf-8"));
			String line = br.readLine();
			ret = new String[Integer.parseInt(line)];
			int index = 0;
			while ((line = br.readLine()) != null) {
				ret[index++] = line;
			}
			return ret;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				br.close();
			} catch (Exception e) {
			}
		}
	}
}
