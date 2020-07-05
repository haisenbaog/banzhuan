package online.banzhuan.javabase.jvm.classloader.loader;

import lombok.extern.slf4j.Slf4j;

import java.io.*;

/**
 * @author haisenbao
 * @date 2020/7/4 23:29
 */
@Slf4j
public class LoadClassFromClassFileByClassName extends ClassLoader {

    private static final String innerPath = "C:\\Users\\86150\\IdeaProjects\\banzhuan\\javabase\\target\\classes\\";
    private static final String outerPath = "G:\\java\\classLoader\\";
    private static final String basePath = outerPath;


    public LoadClassFromClassFileByClassName() {
        // 将父加载器置空，可以在加载类的时候不再委派父加载器加载（默认父加载器为系统类加载器）；
        // 但此时将不能兼容于系统类加载器的环境
        // super(null);
    }

    @Override
    protected Class<?> findClass(String className) throws ClassNotFoundException {
        String classFileName = basePath + className.replaceAll("\\.", "/") + ".class";
        byte[] classBytes = getBytesByClassFileName(classFileName);
        return defineClass(classFileName, classBytes, 0, classBytes.length);
    }

    private byte[] getBytesByClassFileName(String classFileName) {
        byte[] classBytes = new byte[0];

        try (InputStream inputStream = new FileInputStream(classFileName)) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

            byte[] buf = new byte[1024];
            int bytesRead = 0;
            while ((bytesRead = inputStream.read(buf)) != -1) {
                byteArrayOutputStream.write(buf, 0, bytesRead);
            }

            classBytes = byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return classBytes;
    }
}
