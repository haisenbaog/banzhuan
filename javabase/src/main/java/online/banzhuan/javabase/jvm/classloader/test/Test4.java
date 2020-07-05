package online.banzhuan.javabase.jvm.classloader.test;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.IOException;

/**
 * @author haisenbao
 * @date 2020/7/5 19:26
 */
public class Test4 {

    public static void main(String[] args) throws IOException {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
        File[] files = new File[]{new File("G:\\java\\classLoader\\SayImpl.java")} ;
        Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjects(files);

        compiler.getTask(null, fileManager, null, null, null, compilationUnits).call();

        fileManager.close();
    }
}
