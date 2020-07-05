package online.banzhuan.javabase.jvm.classloader.loader;

import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author haisenbao
 * @date 2020/7/4 23:29
 */
@Slf4j
public class ClassBytesClassLoader extends ClassLoader {

    private byte[] classBytes;

    public ClassBytesClassLoader() {
        // 将父加载器置空，可以在加载类的时候不再委派父加载器加载（默认父加载器为系统类加载器）；
        // 但此时将不能兼容于系统类加载器的环境
        // super(null);
    }

    @Override
    protected Class<?> findClass(String className) throws ClassNotFoundException {
        return defineClass(className, classBytes, 0, classBytes.length);
    }

    public Class<?> loadClassFromClassBytes(String className, byte[] classBytes) throws ClassNotFoundException {
        this.classBytes = classBytes;
        return this.loadClass(className);
    }

}
