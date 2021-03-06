package classloader;

/**
 * @author zhouliang
 * @since 2018-06-28 11:38
 * @desc Java虚拟机可以安装多个类加载器，系统默认三个主要的类加载器，每个加载器负责加载特定位置的类：
         BootStrap，ExtClassLoader，AppClassLoader。
        当Java虚拟机要加载一个类时，一般来说，一个加载请求由AppClassLoader发起，AppClassLoader将请求委托给ExtClassLoader，
        ExtClassLoader再将请求委托给它的父加载器BootStrapClassLoader，
        BootStrapLoader找到该类则加载，则一次向下委托，直到AppClassLoader，如果AppClassLoader还未找到该类则会抛出ClassNotFound的异常！
 **/
public class ClassLoader {
    public static void main(String[] args) {
        // 拿到一个类加载器
        java.lang.ClassLoader classLoader = ClassLoader.class.getClassLoader();
        // 循环依次遍历loader的父类
        while (classLoader != null){
            System.out.println("current loader is => " + classLoader);
            classLoader = classLoader.getParent();
        }
        // 最顶层的loader
        System.out.println("top loader is => " + classLoader);
    }
}
