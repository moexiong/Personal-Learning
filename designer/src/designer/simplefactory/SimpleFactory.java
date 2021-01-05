package designer.simplefactory;

import java.math.BigDecimal;

/**
 * 简单工厂模式
 */
public class SimpleFactory {

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

    public static void main(String[] args) {
        // 简单工厂就是你要啥，我给啥就行，不需要关心细节
        Operator operator = SimpleFactory.getOperator(OperatorType.ADD);
        double result = operator.getResult(1.1, 2.2);
        System.out.println(result);
    }

}
/**
 * 这里都放在一起，方便阅读
 * 接口，利用接口的多态性，实现多个不同的行为
 */
interface Operator {
    double getResult(double a, double b);
}

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