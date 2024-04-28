package com.le.lerpc.serializer;

import java.io.IOException;

/**
 * 序列化接口
 */
public interface Serializer {
    /**
     * 序列化
     * @param object
     * @return
     * @param <T>
     * @throws IOException
     */

    <T> byte[] serialize(T object) throws IOException;

    /**
     * 反序列化
     * @param object
     * @return
     * @param <T>
     * @throws IOException
     */
    <T> T deserialize(byte [] bytes,Class<T> type) throws IOException;
}