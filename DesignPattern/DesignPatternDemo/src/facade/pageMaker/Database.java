package facade.pageMaker;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author Keason
 * @version 创建时间：2022年11月21日 下午10:12:54
 * @TODO
 * 
 */
public class Database {
    private Database() {    // 防止外部new出Database的实例，所以声明为private方法
    }
    public static Properties getProperties(String dbname) { // 根据数据库名获取Properties
        String filename = dbname + ".txt";
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream(filename));
        } catch (IOException e) {
            System.out.println("Warning: " + filename + " is not found.");
        }
        return prop;
    }
}
