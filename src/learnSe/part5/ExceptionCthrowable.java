package learnSe.part5;
//5.异常
//知识点
//记忆
//    1.throwable继承体系
//    2.throwable常用方法     printStackTrace()

import org.junit.Test;

public class ExceptionCthrowable {

    @Test
    public void throwableTest() {
        try {
            int[] arr = {1, 2, 3};
            System.out.println(arr[5]);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("finally");
        }
    }
}
