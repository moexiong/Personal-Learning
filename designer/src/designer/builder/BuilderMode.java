package designer.builder;

/**
 * @author: Shixiong Zhan
 * @date: 2021/2/28 13:37
 * @description: 建造者（Builder）模式的主要角色如下。
 * 产品角色（Product）：它是包含多个组成部件的复杂对象，由具体建造者来创建其各个零部件。
 * 抽象建造者（Builder）：它是一个包含创建产品各个子部件的抽象方法的接口，通常还包含一个返回复杂产品的方法 getResult()。
 * 具体建造者(Concrete Builder）：实现 Builder 接口，完成复杂产品的各个部件的具体创建方法。
 * 指挥者（Director）：它调用建造者对象中的部件构造与装配方法完成复杂对象的创建，在指挥者中不涉及具体产品的信息。
 */
public class BuilderMode {

    public static void main(String[] args) {
        AndroidBuilder builder = new AndroidBuilder();
        Director director = new Director(builder);
        Phone android = director.construct();

        System.out.println(android.toString());
    }

}

/**
 * 产品角色（Product）
 */
class Phone {
    private String screen;
    private String os;
    private String size;

    public String getScreen() {
        return screen;
    }
    public void setScreen(String screen) {
        this.screen = screen;
    }
    public String getOs() {
        return os;
    }
    public void setOs(String os) {
        this.os = os;
    }
    public String getSize() {
        return size;
    }
    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "screen='" + screen + '\'' +
                ", os='" + os + '\'' +
                ", size='" + size + '\'' +
                '}';
    }
}

/**
 * 抽象建造者（Builder）
 */
abstract class PhoneBuilder {
    protected Phone phone = new Phone();
    public abstract void buildScreen();
    public abstract void buildOs();
    public abstract void buildSize();
    public final Phone getPhone() {
        return this.phone;
    }
}

/**
 * 具体建造者(Concrete Builder）
 */
class AndroidBuilder extends PhoneBuilder {

    @Override
    public void buildScreen() {
        this.phone.setScreen("全面屏");
    }

    @Override
    public void buildOs() {
        this.phone.setOs("Android");
    }

    @Override
    public void buildSize() {
        this.phone.setSize("7英寸");
    }
}
class IOSBuilder extends PhoneBuilder {

    @Override
    public void buildScreen() {
        this.phone.setScreen("非全面屏");
    }

    @Override
    public void buildOs() {
        this.phone.setOs("IOS");
    }

    @Override
    public void buildSize() {
        this.phone.setSize("6.5英寸");
    }
}

/**
 * 指挥者（Director）
 */
class Director {
    private final PhoneBuilder phoneBuilder;
    public Director (PhoneBuilder phoneBuilder) {
        this.phoneBuilder = phoneBuilder;
    }

    /**
     * 构建产品
     * @return
     */
    public Phone construct() {
        this.phoneBuilder.buildScreen();
        this.phoneBuilder.buildOs();
        this.phoneBuilder.buildSize();
        return this.phoneBuilder.getPhone();
    }
}