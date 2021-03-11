package designer.proxy;

/**
 * @author: Shixiong Zhan
 * @date: 2021/2/28 14:11
 * @description: 代理模式的主要角色如下。
 * 抽象主题（Subject）类：通过接口或抽象类声明真实主题和代理对象实现的业务方法。
 * 真实主题（Real Subject）类：实现了抽象主题中的具体业务，是代理对象所代表的真实对象，是最终要引用的对象。
 * 代理（Proxy）类：提供了与真实主题相同的接口，其内部含有对真实主题的引用，它可以访问、控制或扩展真实主题的功能。
 */
public class StaticProxyMode {

    public static void main(String[] args) {
        // 静态代理缺陷：一个代理只能代理一个真实主题
        DrinkShop drinkShop = new TaoBaoShop();
        drinkShop.sell();
    }

}

/**
 * 抽象主题（Subject）类
 */
interface DrinkShop {
    void sell();
}

/**
 * 真实主题（Real Subject）类
 */
class WaHaHaShop implements DrinkShop {

    @Override
    public void sell() {
        System.out.println("The water is 3$");
    }
}

/**
 * 代理（Proxy）类
 */
class TaoBaoShop implements DrinkShop {

    private final WaHaHaShop shop = new WaHaHaShop();

    private void preSell() {
        System.out.println("The water is very good!");
    }
    private void postSell() {
        System.out.println("10$ minus 1$");
    }

    @Override
    public void sell() {
        this.preSell();
        this.shop.sell();
        this.postSell();
    }
}