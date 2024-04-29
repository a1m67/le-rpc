package com.le.lerpc.serializer;

import com.le.lerpc.spi.SpiLoader;

import java.util.HashMap;
import java.util.Map;

/**
 * 序列化器工厂（用于获取序列化器对象）
 */
public class SerializerFactory {
    /**
     * 序列化映射
     */
//    public static final Map<String, Serializer> KEY_SERIALIZER_MAP = new HashMap<String, Serializer>(){{
//         put(SerializerKeys.JDK, new JdkSerializer());
//         put(SerializerKeys.JSON, new JsonSerializer());
//         put(SerializerKeys.KRYO, new KryoSerializer());
//         put(SerializerKeys.HESSIAN, new HessianSerializer());
//    }};
    /*  等同于
        static {
        KEY_SERIALIZER_MAP = new HashMap<>();
        KEY_SERIALIZER_MAP.put("JDK", new JdkSerializer());
        KEY_SERIALIZER_MAP.put("JSON", new JsonSerializer());
        KEY_SERIALIZER_MAP.put("KRYO", new KryoSerializer());
        KEY_SERIALIZER_MAP.put("HESSIAN", new HessianSerializer());
    }
     */

    static {
        SpiLoader.load(Serializer.class);
    }

    /**
     * 默认序列化器
     */
    private static final Serializer DEFAULT_SERIALIZER = new HessianSerializer();

    public static Serializer getInstance(String key) {
        return SpiLoader.getInstance(Serializer.class, key);
    }

}
