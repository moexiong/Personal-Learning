package designer.simplefactory;

import java.math.BigDecimal;

/**
 * @author: Shixiong Zhan
 * @date: 2021/2/28 13:37
 * @description: 简单工厂模式的主要角色如下：
 * 简单工厂（SimpleFactory）：是简单工厂模式的核心，负责实现创建所有实例的内部逻辑。工厂类的创建产品类的方法可以被外界直接调用，创建所需的产品对象。
 * 抽象产品（Product）：是简单工厂创建的所有对象的父类，负责描述所有实例共有的公共接口。
 * 具体产品（ConcreteProduct）：是简单工厂模式的创建目标。
 */
public class SimpleFactoryMode {

    public static void main(String[] args) {
        // 简单工厂就是你要啥，我给啥就行，不需要关心细节
        Operator operator = OperatorFactory.getOperator(OperatorFactory.OperatorType.ADD);
        double result = operator.getResult(1.1, 2.2);
        System.out.println(result);
    }

}

/**
 * 简单工厂（SimpleFactory）
 */
class OperatorFactory {
    enum OperatorType {
        ADD,
        SUB,
        MUL,
        DIV
    }

    public static Operator getOperator(OperatorType type) {
        switch (type) {
            case ADD:
                // 我是细节，我啥都没
                return new Add();
            case SUB:
                System.out.println("我也是细节，偷偷输出了一句：hello world!");
                return new Sub();
            case MUL:
                return new Mul();
            case DIV:
                return new Div();
        }
        throw new IllegalArgumentException();
    }
}

/**
 * 抽象产品（Product）
 */
interface Operator {
    double getResult(double a, double b);
}

/**
 * 具体产品（ConcreteProduct）
 */
class Add implements Operator {
    @Override
    public double getResult(double a, double b) {
        return new BigDecimal(a).add(new BigDecimal(b)).doubleValue();
    }
}
class Sub implements Operator {
    @Override
    public double getResult(double a, double b) {
        return new BigDecimal(a).subtract(new BigDecimal(b)).doubleValue();
    }
}
class Mul implements Operator {
    @Override
    public double getResult(double a, double b) {
        return new BigDecimal(a).multiply(new BigDecimal(b)).doubleValue();
    }
}
class Div implements Operator {
    @Override
    public double getResult(double a, double b) {
        return this.getResult(a, b, 2);
    }
    public double getResult(double a, double b, int scale) {
        return new BigDecimal(a).divide(new BigDecimal(b), scale).doubleValue();
    }
}