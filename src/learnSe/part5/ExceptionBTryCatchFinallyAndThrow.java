package learnSe.part5;
//5.异常
//    try catch finally && throw
//知识点
//记忆
//    1.Try catch finally作用
//    2.catch捕获异常的方式
//    3.finally在return前执行
//了解
//    1.自定义异常
//
//
//1.try catch finally
//    1.简介
//        try		检测异常
//                    try部分的代码，只要遇到第一个异常以后，就会寻求异常处理
//                    处理异常以后，try部分剩余的代码将不会执行，但会继续执行try以外其他部分的代码
//        catch	捕获异常
//                    并在catch方法体内进行对应的处理操作
//                    catch可以有多个，针对不同异常进行不同处理，但通常，我们用多态的形式，通过Exception对象捕获异常
//                    如果一定要跟多个catch，那么小范围的异常在前面，否则会被大范围的隐藏掉
//                    jdk7以后可以使用|的形式catch多个异常
//        finally	进行一些必要操作，如释放资源（在IO流操作和数据库操作中会见到），所以不要在finally中进行逻辑操作
//                    被finally控制的语句体一定会执行，但存在特殊情况：在执行到finally之前jvm退出了(比如System.exit(0))
//                    finally在return前执行
//                        如果finally里面没有return语句，即使finally中操作了返回值相关的变量，也不能改变之前已经准备return的值
//                        但如果finally中做了return，那么显然其他的return也就没有机会执行了，return的值会被刷新为finally中获取的值
//2.throw
//    1.throw和throws
//        throw通知本方法
//            抛出一个异常对象名，后续代码不再继续执行（是剩下的所有代码都不会执行，不是某一块）
//        throws通知调用者
//            可以跟多个异常类名，用逗号隔开，表示抛出异常，由该方法的调用者来处理
//    2.对于编译时异常，方法中throw，那么方法上一定要throws；对于运行时异常，方法上不强制需要throws
//3.自定义异常
//    1.作用    现有异常类不足以满足特定需要时，使用自定义异常，比如：人的年龄超过理论值
//    2.概述
//        继承自Exception或者RuntimeException  RuntimeException不强制throws
//4.注意事项
//    1.针对重写
//        子类重写父类方法时，只能抛出父类已经抛出的异常类及其子类；显然，如果父类没有throws异常，那么子类也不能throws异常，只能用try catch处理
//    2.处理方式的选择
//        方法内能处理，或者需要后续代码继续执行，使用try catch
//        方法内不知道如何处理，或者后续代码不需要继续执行，使用throw
import org.junit.Test;
public class ExceptionBTryCatchFinallyAndThrow {
    @Test
    public void tryCatchTest() {
        try {
            int[] arr = {1,2,3,4,5};
            System.out.println(arr[5]);
            System.out.println("try内剩余的代码！");   //try中捕获异常后，try内剩余的代码将不会执行
        } catch (IndexOutOfBoundsException | NullPointerException e){   //jdk7以后可以用|的形式捕获多种异常
        } catch (Exception e) {     //但通常，我们习惯用多态的形式使用Exception捕获多种异常
            e.printStackTrace();
        } finally {
            System.out.println("finally!");
        }
        System.out.println("try之外的代码！");    //try中捕获异常后，try之外剩余的代码仍将执行
    }

    @Test
    public void finallyTest() {
        //finally在return前执行
        System.out.println(finallyMethod());
    }

    private int finallyMethod() {
        int[] arr = {1,2,3,4,5};
        try {
            return arr[0];
        } catch (Exception e) {

        } finally {
            arr[0] = 2;
            return arr[0];
        }
//        return -1;
    }

    //自定义异常例子
    @Test
    public void customExceptionTest () {
        int age = -1;
        if (age < 0 || age > 180) {
            throw new AgeOutOfBoundsException("超龄！");
        }
        System.out.println(age);
    }



}

class AgeOutOfBoundsException extends RuntimeException {
    public AgeOutOfBoundsException () {

    }
    public AgeOutOfBoundsException (String msg) {
        super(msg);
    }
}
