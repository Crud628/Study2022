package chainofResponsibility;

/**
 * @author Keason
 * @version 创建时间：2022年11月21日 下午10:07:23
 * @TODO
 * 
 */
public class OddSupport extends Support {
	public OddSupport(String name) { // 构造函数
		super(name);
	}

	protected boolean resolve(Trouble trouble) { // 解决问题的方法
		if (trouble.getNumber() % 2 == 1) {
			return true;
		} else {
			return false;
		}
	}
}
