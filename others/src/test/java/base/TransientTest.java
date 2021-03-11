package base;

import org.junit.Test;
import utils.SerializableUtil;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: 詹世雄
 * @date: 2021/3/7 16:30
 * @description:
 */
public class TransientTest {

    @Test
    public void testMap() {
        Map<String, String> map = new HashMap<>();
        map.put("hello", "world");

        System.out.println("map: " + map.toString());

        System.out.println("serialize...");
        File file = SerializableUtil.serializeObjToFile(map);
        map = SerializableUtil.deserializeObjFromFile(file, Map.class);
        System.out.println("deserialize");

        System.out.println("map: " + map.toString());
    }

    @Test
    public void testHashMap() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("hello", "world");

        System.out.println("hashMap: " + hashMap.toString());

        System.out.println("serialize...");
        File file = SerializableUtil.serializeObjToFile(hashMap);
        hashMap = SerializableUtil.deserializeObjFromFile(file, HashMap.class);
        System.out.println("deserialize");

        System.out.println("hashMap: " + hashMap.toString());
    }
}
