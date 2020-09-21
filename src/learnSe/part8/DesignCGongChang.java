package learnSe.part8;
//8.设计模式
//
//3.工厂模式
//  1.概念
//      定义一个工厂类，负责创建一些类的实例；这样客户端就不需要创建实例，需要时就让工厂创建，从工厂获取
//  2.优点
//      1.创建目标实例，只需要知道是哪个实例（名称）
//      2.拓展性强
//  3.缺点
//      1.每次增加一个工厂的产品，就要增加具体的类和实现工厂，这使得类的数量增加的更多

public class DesignCGongChang {
    public static void main(String[] args) {
        PersonFactory pf = new PersonFactory();
        OldPerson oldPerson = (OldPerson) pf.getPerson("OldPerson");
        YoungPerson youngPerson = (YoungPerson)pf.getPerson("YoungPerson");

        oldPerson.speak();
        youngPerson.speak();
    }
}



interface Person {
    void speak();
}

class OldPerson implements Person {
    public void speak() {
        System.out.println("wo 25 sui");
    }
}
class YoungPerson implements Person {

    @Override
    public void speak() {
        System.out.println("wo 5 sui");
    }
}

//创建一个工厂类，负责创建上述对象
class PersonFactory {

    public Person getPerson(String str) {
        if (str.equals("OldPerson")) {
            return new OldPerson();
        } else if (str.equals("YoungPerson")) {
            return new YoungPerson();
        }

        return null;
    }
}