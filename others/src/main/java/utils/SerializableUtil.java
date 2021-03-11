package utils;

import java.io.*;
import java.util.UUID;

/**
 * @author: 詹世雄
 * @date: 2021/3/7 16:57
 * @description: 对象序列化工具，测试用
 */
public class SerializableUtil {

    private static final String BASE_DIR = System.getProperty("user.dir").concat(File.separator)
            .concat("src").concat(File.separator)
            .concat("test").concat(File.separator)
            .concat("resources").concat(File.separator)
            .concat("files");
    private static final String EXT = ".out";

    public static <T> File serializeObjToFile(T obj) {
        if (obj == null) {
            return null;
        }
        File file = new File(BASE_DIR.concat(File.separator).concat(UUID.randomUUID().toString()).concat(EXT));
        ObjectOutputStream outputStream = null;
        try {
            if (file.exists()) {
                file.delete();
            }
            boolean newFile = file.createNewFile();
            if (!newFile) {
                return null;
            }
            outputStream = new ObjectOutputStream(new FileOutputStream(file));
            outputStream.writeObject(obj);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                }
            }
        }
        return file;
    }

    public static <T> T deserializeObjFromFile(File file, Class<T> clazz) {
        if (file == null || clazz == null) {
            return null;
        }
        T t = null;
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file))) {
            Object o = inputStream.readObject();
            t = (T) o;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        file.delete();
        return t;
    }

}
