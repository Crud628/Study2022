package bridge;

/**
 * @author Keason
 * @version 创建时间：2022年11月19日 下午9:30:00
 * @TODO
 * 
 */
public class Display {
	private DisplayImpl impl;

	public Display(DisplayImpl impl) {
		this.impl = impl;
	}

	public void open() {
		impl.rawOpen();
	}

	public void print() {
		impl.rawPrint();
	}

	public void close() {
		impl.rawClose();
	}

	public final void display() {
		open();
		print();
		close();
	}
}
