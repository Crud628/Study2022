package factoryMethod.sample;

import factoryMethod.sample.IDCard.IDCardFactory;
import factoryMethod.sample.framework.Factory;
import factoryMethod.sample.framework.Product;

/**
 * @author Keason
 * @version 创建时间：2022年11月13日 下午8:39:23
 * @TODO
 * 
 */
public class Main {
	
    public static void main(String[] args) {
        Factory factory = new IDCardFactory();
        Product card1 = factory.create("小明");
        Product card2 = factory.create("小红");
        Product card3 = factory.create("小刚");
        card1.use();
        card2.use();
        card3.use();
    }
}
