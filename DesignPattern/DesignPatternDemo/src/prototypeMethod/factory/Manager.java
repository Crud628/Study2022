package prototypeMethod.factory;

import java.util.HashMap;

/**
 * @author Keason
 * @version 创建时间：2022年11月13日 下午9:13:18
 * @TODO
 * 
 */
public class Manager {
	private HashMap showcase = new HashMap();

	public void register(String name, Product proto) {
		showcase.put(name, proto);
	}

	public Product create(String protoname) {
		Product p = (Product) showcase.get(protoname);
		return p.createClone();
	}
}
