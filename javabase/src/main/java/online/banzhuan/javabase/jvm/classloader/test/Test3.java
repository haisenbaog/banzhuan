package online.banzhuan.javabase.jvm.classloader.test;

import lombok.extern.slf4j.Slf4j;
import online.banzhuan.javabase.jvm.classloader.compiler.JavaStringCompiler;
import online.banzhuan.javabase.jvm.classloader.domain.Say;
import online.banzhuan.javabase.jvm.classloader.loader.ClassBytesClassLoader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author haisenbao
 * @date 2020/7/5 18:30
 */
@Slf4j
public class Test3 {

    public static String readJavaFile(String javaFilePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(javaFilePath));
        StringBuilder fileContent = new StringBuilder();
        String line = reader.readLine();
        while (line != null) {
            fileContent.append(line).append("\n");
            line = reader.readLine();
        }
        return fileContent.toString();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException, InterruptedException {
        while (true) {
            String javaFilePath = "G:\\java\\classLoader\\SayImpl.java";
            String fileContent = readJavaFile(javaFilePath);
            log.info("fileContent:\n{}", fileContent);

            JavaStringCompiler compiler = new JavaStringCompiler();
            Map<String, byte[]> byteArrs = compiler.compile("SayImpl.java", fileContent);

            String className = "online.banzhuan.javabase.jvm.classloader.domain.SayImpl";
            ClassBytesClassLoader classLoader = new ClassBytesClassLoader();
            Class<?> loadedClass = classLoader.loadClassFromClassBytes(className, byteArrs.get(className));
            log.info("loadedClass:\n{}", loadedClass);

            Say instance = (Say) loadedClass.newInstance();
            String sayContent = instance.sayHello("张三");
            log.info("sayContent:{}", sayContent);

            TimeUnit.SECONDS.sleep(10);
        }
    }
}
