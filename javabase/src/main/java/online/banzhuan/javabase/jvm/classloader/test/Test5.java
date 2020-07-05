package online.banzhuan.javabase.jvm.classloader.test;

import redis.clients.jedis.Jedis;

/**
 * @author haisenbao
 * @date 2020/7/5 21:20
 */
public class Test5 {

    public static void main(String[] args) {
        try {
            String fileContent = Test3.readJavaFile("G:\\java\\classLoader\\SayImpl.java");
            try (Jedis jedis = new Jedis("localhost", 6379)) {
                String className = "online.banzhuan.javabase.jvm.classloader.domain.SayImpl";
                jedis.set(className, fileContent);
                System.err.println(jedis.get(className));
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
