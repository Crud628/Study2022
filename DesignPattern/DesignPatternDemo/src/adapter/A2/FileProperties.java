package adapter.A2;

import java.io.*;
import java.util.*;

public class FileProperties extends Properties implements FileIO {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -6201870760250142650L;
	
	public void readFromFile(String filename) throws IOException {
        load(new FileInputStream(filename));
    }
    public void writeToFile(String filename) throws IOException {
        store(new FileOutputStream(filename), "written by FileProperties");
    }
    public void setValue(String key, String value) {
        setProperty(key, value);
    }
    public String getValue(String key) {
        return getProperty(key, "");
    }
}