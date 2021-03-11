package designer.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author: Shixiong Zhan
 * @date: 2021/2/28 14:28
 * @description: 代理模式的主要角色如下。
 * 抽象主题（Subject）类：通过接口或抽象类声明真实主题和代理对象实现的业务方法。
 * 真实主题（Real Subject）类：实现了抽象主题中的具体业务，是代理对象所代表的真实对象，是最终要引用的对象。
 * 代理（Proxy）类：提供了与真实主题相同的接口，其内部含有对真实主题的引用，它可以访问、控制或扩展真实主题的功能。
 */
public class DynamicProxyMode {

    public static void main(String[] args) {
        TMallShop tMallShop = new TMallShop();

        FoodShop foodShop = tMallShop.getInstance(new BaiCaoWeiShop());
        foodShop.sell();
    }

}

/**
 * 抽象主题（Subject）类
 */
interface FoodShop {
    void sell();
}

/**
 * 真实主题（Real Subject）类
 */
class BaiCaoWeiShop implements FoodShop {

    @Override
    public void sell() {
        System.out.println("The food is 5$/1kg");
    }
}

/**
 * 代理（Proxy）类
 */
class TMallShop implements InvocationHandler {

    private FoodShop foodShop;
    public FoodShop getInstance(FoodShop foodShop) {
        this.foodShop = foodShop;
        Class<?> clazz = foodShop.getClass();
        return (FoodShop) Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), this);
    }

    private void preSell() {
        System.out.println("The food is very delicious!");
    }
    private void postSell() {
        System.out.println("30$ minus 2$");
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        this.preSell();
        Object invoke = method.invoke(this.foodShop, args);
        this.postSell();
        return invoke;
    }
}