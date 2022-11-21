package facade.pageMaker;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

/**
 * @author Keason
 * @version 创建时间：2022年11月21日 下午10:13:38
 * @TODO
 * 
 */
public class PageMaker {
	private PageMaker() { // 防止外部new出PageMaker的实例，所以声明为private方法
	}

	public static void makeWelcomePage(String mailaddr, String filename) {
		try {
			Properties mailprop = Database.getProperties("src/facade/maildata");
			String username = mailprop.getProperty(mailaddr);
			HtmlWriter writer = new HtmlWriter(new FileWriter(filename));
			writer.title("Welcome to " + username + "'s page!");
			writer.paragraph("欢迎来到" + username + "的主页。");
			writer.paragraph("等着你的邮件哦！");
			writer.mailto(mailaddr, username);
			writer.close();
			System.out.println(filename + " is created for " + mailaddr + " (" + username + ")");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
