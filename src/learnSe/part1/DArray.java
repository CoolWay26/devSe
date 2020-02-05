package learnSe.part1;
//1.4基本概念（Ⅲ）
//知识点
//记忆
//    1.方法（重载，参数传递-基本|引用）
//    2.数组（初始化，内存情况，基本操作-遍历求和最值反转）
//    3.二维数组（格式，内存，基本操作-遍历求和）
//了解
//1.方法    完成特定功能的代码块
//    1.目的：复用性
//    2.格式：
//        修饰符 返回值类型 方法名(参数类型 参数名1,参数类型 参数名2...) {
//            方法体语句;
//            return 返回值;
//        }
//        格式说明：   写方法最重要的是明确返回值类型和参数列表
//        修饰符：public static等，用于限制权限、描述状态等
//        返回值类型：功能结果的数据类型
//        方法名：符合命名规则即可，用来调用
//        参数：
//            实际参数：就是实际参与运算的参数
//            形式参数；就是方法定义上的，用于接收实际参数的
//            参数类型：就是参数的数据类型
//            参数名：就是变量名
//        方法体语句：就是完成功能的代码
//        return：用来结束方法
//        返回值：功能的结果，由return返回给调用者
//    3.方法定义和调用：
//        1.方法不能嵌套定义（方法间平级）
//        2.方法不调用不执行
//        3.有返回值就要有return
//****
//    4.方法重载  同一个类中，方法名相同，参数列表不同（个数，类型，顺序--顺序不同其实没什么意义）;与返回值类型无关。
//        public int sum(int a, int b) {
//            int sum = a + b;
//            return sum;
//        }
//        public float sum(int a, float b) {
//            float sum = a + b;
//            return sum;
//        }
//    5.参数传递
//        基本数据类型值传递不改变原值，因为方法调用后就会弹栈，局部变量随之消失
//        引用数据类型值传递会改变原值，因为实际改变的是堆中的数据，通过地址访问数据，与方法弹栈与否没有关系
//2.数组
//    1.概念
//        1.为了存储多个同类型的值
//        2.可以存基本数据类型，也可以存引用数据类型
//    2.格式
//        数据类型[] 数组名 = new 数据类型[数组的长度];
//        int[] arr = new int[5];
//        int[] arr = {...};
//    3.初始化
//        动态初始化，定义长度，系统根据数据类型给出默认值
//        静态初始化，定义数组元素（实际上，系统还是会先给出默认值，然后才进行显式初始化）
//        默认值：
//            整数类型	byte,short,int,long	    默认为0
//            浮点类型	float,double		    默认为0.0
//            布尔类型	boolean			        默认false
//            字符类型	char				    默认为'\ u0000'	\ u是转义字符意为Unicode编码
//    4.数组在堆中存储的情况
//        栈：局部变量、方法
//        堆：new出来的数组、对象
//        栈中arr存的是数组的地址，真正的数组存在堆中
//            int[] arr1 = new int[5];
//            int[] arr2 = new int[5];    //new会创建一个新的数组
//            int[] arr3 = arr2;  //arr2.arr3两个引用指向同一个数组
//    5.数组基本操作    前提：注意预防角标越界和空指针（数组为null）
//        1.遍历
//            public void getAll(int[] arr) {
//                //1
//                if (arr != null) {
//                    for (int a : arr) {
//                        System.out.print(a + " ");
//                    }
//                }
//                //2
//                for (int i = 0;i<arr.length-1;i++) {
//                System.out.print(arr[i] + ",");
//                }
//                System.out.print(arr[arr.length-1]);
//            }
//        2.获取最值
//            public int getMaxValue(int[] arr) {
//                if (arr != null) {
//                    int max = arr[0];
//                    for (int temp : arr) {
//                        if (temp > max) {
//                        max = temp;
//                        }
//                    }
//                    return max;
//                }
//                return -1;
//            }
//        3.反转
//            public void reverseArr(int[] arr) {
//                if (arr != null) {
//                    int temp;
//                    for (int i = 0;i<arr.length/2;i++) {
//                        temp = arr[i];
//                        arr[i] = arr[arr.length-1-i];
//                        arr[arr.length-1-i] = temp;
//                    }
//                }
//            }
//        4.查表法
//            public String getOneDay(int index) {
//                if (index >= 0) {
//                    String[] arr = {"表0位置","表1位置","表2位置"};
//                    return arr[index];
//                }else {
//                    return "索引有误！";
//                }
//            }
//        5.查找索引
//            public int getIndex(int[] arr, int value) {
//                if (arr != null) {
//                    for (int i = 0;i<arr.length;i++) {
//                        if (value == arr[i]) {
//                            return i;
//                        }
//                    }
//                }
//                return -1;
//            }
//3.二维数组
//    1.格式
//        int[][] arr = new int[3][2];
//        int[][] arr = new int[3][]; //第一个数值是一定要有的，这个数值决定了最终会初始化多少个一维数组，而这些一维数组的情况可以后续进行初始化
//        int[][] arr = {{1,2},{3,4,5},{6,7,8,9}};    //最终创建的数组长度是可以不一样的
//    2.二维数组存储情况
//        栈中数组变量存的是一维数组的地址，该一维数组中存的是多个一维数组的地址
//        对于第一个一维数组，由于存的是引用型的地址值，所以创建时默认初始化的值都为null
//    3.二维数组基本操作
//        1.遍历（求和）
//            外循环控制二维数组长度（一维数组的个数）
//            内循环控制每个一维数组的长度
//            public void getAllDouble(int[][] arr) {
//                for (int[] arrValue : arr) {
//                    for (int value : arrValue) {
//                        System.out.print(value + " ");
//                    }
//                System.out.println();
//                }
//
//                for (int i = 0;i<arr.length;i++){
//                    for (int j = 0;j<arr[i].length;j++) {
//                        if (j==arr[i].length-1) {
//                            System.out.print(arr[i][j] + "\n");
//                        }else {
//                            System.out.print(arr[i][j] + " ");
//                        }
//                    }
//                }
//            }


public class DArray {
    public static void main (String[] args) {
//        int[] intArr = {1,2,3};
//        Test ts = new Test();
//        ts.getAll(intArr);
//        int max = ts.getMaxValue(intArr);
//        System.out.println(max);
//        ts.reverseArr(intArr);
//        ts.getAll(intArr);
//        String result = ts.getOneDay(0);
//        System.out.print(result);

//        int[][] arr = {{1,2},{3,4,5},{6,7,8,9}};
//        ts.getAllDouble(arr);
    }
}

//class Test {
////方法重载
////    public int sum(int a, int b) {
////        int sum = a + b;
////        return sum;
////    }
////    public float sum(int a, float b) {
////        float sum = a + b;
////        return sum;
////    }
////数组遍历
//    public void getAll(int[] arr) {
//        if (arr != null) {
////            for (int a : arr) {
////                System.out.print(a + " ");
////            }
//
//        for (int i = 0;i<arr.length-1;i++) {
//            System.out.print(arr[i] + ",");
//        }
//        System.out.print(arr[arr.length-1]);
//        }
//    }
////数组最值
//    public int getMaxValue(int[] arr) {
//        if (arr != null) {
//            int max = arr[0];
//            for (int temp : arr) {
//                if (temp > max) {
//                    max = temp;
//                }
//            }
//            return max;
//        }
//        return -1;
//    }
////数组反转
//    public void reverseArr(int[] arr) {
//        if (arr != null) {
//            int temp;
//            for (int i = 0;i<arr.length/2;i++) {
//                temp = arr[i];
//                arr[i] = arr[arr.length-1-i];
//                arr[arr.length-1-i] = temp;
//            }
//        }
//    }
////查表法
//    public String getOneDay(int index) {
//        if (index >= 0) {
//            String[] arr = {"表0位置","表1位置","表2位置"};
//            return arr[index];
//        }else {
//            return "索引有误！";
//        }
//    }
////查找元素索引
//    public int getIndex(int[] arr, int value) {
//        if (arr != null) {
//            for (int i = 0;i<arr.length;i++) {
//                if (value == arr[i]) {
//                    return i;
//                }
//            }
//        }
//        return -1;
//    }
////二维数组遍历
//    public void getAllDouble(int[][] arr) {
//        for (int[] arrValue : arr) {
//            for (int value : arrValue) {
//                System.out.print(value + " ");
//            }
//            System.out.println();
//        }
//
//        for (int i = 0;i<arr.length;i++){
//            for (int j = 0;j<arr[i].length;j++) {
//                if (j==arr[i].length-1) {
//                    System.out.print(arr[i][j] + "\n");
//                }else {
//                    System.out.print(arr[i][j] + " ");
//                }
//            }
//        }
//    }
//}