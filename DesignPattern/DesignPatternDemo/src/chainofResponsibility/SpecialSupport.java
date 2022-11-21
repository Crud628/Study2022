package chainofResponsibility;

/**
 * @author Keason
 * @version 创建时间：2022年11月21日 下午10:07:48
 * @TODO
 * 
 */
public class SpecialSupport extends Support {
	private int number; // 只能解决指定编号的问题

	public SpecialSupport(String name, int number) { // 构造函数
		super(name);
		this.number = number;
	}

	protected boolean resolve(Trouble trouble) { // 解决问题的方法
		if (trouble.getNumber() == number) {
			return true;
		} else {
			return false;
		}
	}
}
