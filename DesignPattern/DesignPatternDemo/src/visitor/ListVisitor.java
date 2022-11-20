package visitor;

import java.util.Iterator;

/**
 * @author Keason
 * @version 创建时间：2022年11月20日 下午9:14:40
 * @TODO
 * 
 */
public class ListVisitor extends Visitor {

	private String currentdir = ""; // 当前访问的文件夹的名字

	public void visit(File file) { // 在访问文件时被调用
		System.out.println(currentdir + "/" + file);
	}

	public void visit(Directory directory) { // 在访问文件夹时被调用
		System.out.println(currentdir + "/" + directory);
		String savedir = currentdir;
		currentdir = currentdir + "/" + directory.getName();
		Iterator it = directory.iterator();
		while (it.hasNext()) {
			Entry entry = (Entry) it.next();
			entry.accept(this);
		}
		currentdir = savedir;
	}
}
