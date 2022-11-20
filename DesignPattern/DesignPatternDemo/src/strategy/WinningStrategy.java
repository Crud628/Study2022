package strategy;

import java.util.Random;

/**
 * @author Keason
 * @version 创建时间：2022年11月19日 下午9:38:30
 * @TODO
 * 
 */
public class WinningStrategy implements Strategy {
    private Random random;
    private boolean won = false;
    private Hand prevHand;
    public WinningStrategy(int seed) {
        random = new Random(seed);
    }
    public Hand nextHand() {
        if (!won) {
            prevHand = Hand.getHand(random.nextInt(3));
        }
        return prevHand;
    }
    public void study(boolean win) {
        won = win;
    }
}
