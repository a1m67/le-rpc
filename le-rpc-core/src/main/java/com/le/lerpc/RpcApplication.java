package com.le.lerpc;

import com.le.lerpc.config.RpcConfig;
import com.le.lerpc.constant.RpcConstant;
import com.le.lerpc.utils.ConfigUtils;
import lombok.extern.slf4j.Slf4j;

import javax.naming.ConfigurationException;

/**
 * 双检锁单例模式的经典实现,支持在获取配置时才调用 init 方法实现懒加载。
 */
// 后续只需要使用 RpcConfig rpc = RpcApplication.getRpcConfig(); 便可以获取到RPC配置
@Slf4j
public class RpcApplication implements RpcConstant{
    private static volatile RpcConfig rpcConfig;
    /**
     * 框架初始化，支持传入自定义配置
     *
     * @param newRpcConfig
     */
    public static void init(RpcConfig newRpcConfig) {
        rpcConfig = newRpcConfig;
        log.info("rpc init, config = {}", newRpcConfig.toString());
    }
    /**
     * 初始化
     */
    public static void init() {
        RpcConfig newRpcConfig;
        try {
            newRpcConfig = ConfigUtils.loadConfig(RpcConfig.class, RpcConstant.DEFAULT_CONFIG_PREFIX);
        } catch (Exception e) {
            newRpcConfig = new RpcConfig();
        }
        init(newRpcConfig);
    }

    /**
     * 获取配置
     *
     * @return
     */
    public static RpcConfig getRpcConfig() {
        if (rpcConfig == null) {
            synchronized (RpcApplication.class) {
                if (rpcConfig == null) {
                    init();
                }
            }
        }
        return rpcConfig;
    }

}
