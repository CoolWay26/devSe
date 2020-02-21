package learnSe.part4;
//4.集合框架
//      泛型
//知识点
//记忆
//    1.概述，注意2个，好处2个，格式
//    2.Arrays.asList()   数组转为ArrayList集合的结构，实际上只是生成了一个ArrayList的内部类对象，不能进行增删元素的操作，但可以进行其他集合的操作
//    3.Collection的非静态方法toArray(T[] a) 通过泛型赋予数组元素类型，参数数组的长度如何限制
//了解
//    1.泛型类，泛型方法，泛型接口     只有方法的泛型是定义在声明的前面，类和接口都是定义在声明的末尾
//    2.可变参数  注意点
//1.概述
//    1.概述
//        1.由来
//            为了解决Object转型问题（Object类可以接收任意对象，转型存在隐患）
//        2.作用
//            限制集合中只能存储该类型及其子类对象
//    2.好处
//        1.将运行期的错误转换到编译期，更安全
//            迭代强转的时候，可能会出现ClassCaseException
//        2.不再需要强转
//            向集合中存入数据时，会自动提升为Object，加上泛型后取出就是泛型的类型，不再是Object类型
//    3.注意
//        1.<>中放的必须是引用数据类型，定义成Object是无意义的（因为集合中存的一定是引用型变量）
//        2.jdk7以后，后面的的泛型可以不写，但<>不能省略
//2.泛型类     class GenericsClass<Generics> {code}
//    1.格式
//        public class 类名<泛型类型1,…>        创建该类对象时，传入泛型，即可使用泛型
//3.泛型方法    public<Inte> Inte getInteger (Inte inte) {code}
//    1.注意
//        1.方法泛型如果与类的泛型不一致，则需要自行声明新的泛型，个人认为方法泛型主要通过方法的参数来给定
//        2.方法的泛型可以用于方法的各处（返回值，参数，各种操作），所谓类的泛型，方法的泛型，是限制了泛型的作用域
//        3.静态方法必须明确自己的泛型，因为静态方法没有办法在new对象的时候传入泛型
//4.泛型接口    interface GenInte<Inte> {code}
//    1.在implements时给接口加上泛型（否则报错）   class GenInteImpl implements GenInte<Integer> {code}
//5.泛型通配符 <?>任意的java类       List<?> listUknown = new ArrayList<A>()
//    1.当右边泛型无法确定时，左边使用泛型通配符<?>
//    2.拓展
//        ? extends E     E及其子类
//        ? super E       E及其父类
//6.增强for循环
//    1.作用
//        简化数组和Collection集合的遍历（底层是迭代器实现），所以一般不使用迭代器进行遍历（读取的操作）
//    2.注意
//        由于底层是迭代器，所以不能对集合进行结构性变化的操作（增删）
//        已知遍历List集合的三种方式
//            一般循环遍历，通过索引遍历，遍历过程中可以通过操作索引进行增删
//            迭代器遍历，只能通过ListIterator迭代器的add，remove方法在当前索引处进行增删
//            增强for循环，不能进行增删，但遍历起来简单直接
//7.静态导入
//    1.格式
//        import static 包名….类名.方法名;
//    2.注意
//        1.方法必须是静态的,如果有多个同名的静态方法，容易不知道使用谁，这个时候要使用，必须加前缀
//        2.意义不大，知道就行
//8.可变参数
//    1.格式
//        1.修饰符 返回值类型 方法名(数据类型…  变量名){}       定义方法的时候不知道该定义多少个参数
//    2.注意
//        1.可变参数其实是一个数组
//        2.如果一个方法有多个参数，那么可变参数放在最后，否则前面的参数可能会被可变参数接收
//9.Arrays.asList()
//    1.概述
//        1.将数组转为List集合
//        2.这是Arrays类的静态方法，生成的是一个ArrayList的内部类对象，该方法的返回值类型是List，因此不能用ArrayList接收
//        3.对于存储非引用型数据的数组，会将整个数组作为元素生成ArrayList集合
//        4.这样生成的List集合，实际上只是获取了List集合的结构，不能进行add,remove的操作，但可以进行其他的集合相关的操作，如迭代器遍历
//10.Collection的非静态方法toArray(T[] a)
//    给参数定义了泛型，从而可以在调用时限制生成数组的元素类型
//        参数即是将要生成的数组，且该数组长度要<=目标集合的size()，否则多余的元素会默认赋值null
//        String[] arr = list.toArray(new String[0]);
//    没有参数的toArray()会默认成Object
//        Object[] arr1 = list.toArray();


import java.util.LinkedList;

public class CollectionsCGenerics {
    public static void main (String[] args) {
        //泛型类
//        GenericsClass<Integer> gc = new GenericsClass<>();
//        gc.setGen(1);
//        gc.getGen();
//        gc.getInteger(2);
        //增强for
//        ArrayList<String> list = new ArrayList<String>();
//        list.add("A");
//        list.add("B");
//        list.add("C");
//        for (String str : list) {
//            System.out.println(str);
//        }
//
//        String[] arr = list.toArray(new String[0]);
//        String[] arr1 = list.toArray();//报错
//        Object[] arr2 = list.toArray();
        colToArr();
    }

    //可变参数
    public static void varPar(int... arr) {
        for (int i : arr) {
            System.out.println(i);
        }
    }
    //Collection的非静态方法toArray(T[] arr)

    public static void colToArr() {
        LinkedList<Integer> list = new LinkedList();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        Integer[] arr = list.toArray(new Integer[5]);
        System.out.println(arr);
    }

}


//泛型类
//class GenericsClass<Generics> {
//    Generics gen;
//
//    public void getGen () {
//        System.out.println(gen.toString());
//    }
//
//    public void setGen (Generics gen) {
//        this.gen = gen;
//    }
//
//    //泛型方法
//    public<Inte> Inte getInteger (Inte inte) {
//        System.out.println(inte.toString());
//        return inte;
//    }
//}

//泛型接口
//interface GenInte<Inte> {
//    public static final String INTE_VALUE = "INTE";
//    int INTE_NUM = 1;
//    void getInteger(Inte inte);
//}
//
//class GenInteImpl implements GenInte<Integer> {
//
//    @Override
//    public void getInteger(Integer integer) {
//
//    }
//}




