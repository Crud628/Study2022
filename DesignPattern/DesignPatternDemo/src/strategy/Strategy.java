package strategy;

/**
 * @author Keason
 * @version 创建时间：2022年11月19日 下午9:37:17
 * @TODO
 * 
 */
public interface Strategy {
	public abstract Hand nextHand();

	public abstract void study(boolean win);
}
