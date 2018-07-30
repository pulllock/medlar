package me.cxis.spring.cache;

import com.taobao.tair.DataEntry;
import com.taobao.tair.Result;
import com.taobao.tair.ResultCode;
import com.taobao.tair.TairManager;
import org.springframework.cache.Cache;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class TairCache implements Cache {

    private TairManager tairManager;

    private String name;

    private int namespace;

    private int timeout;

    public TairCache(String name, int namespace, TairManager tairManager) {
        this(name, namespace, 0, tairManager);
    }

    public TairCache(String name, int namespace, int timeout, TairManager tairManager) {
        this.name = name;
        this.namespace = namespace;
        this.timeout = timeout;
        this.tairManager = tairManager;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Object getNativeCache() {
        return tairManager;
    }

    @Override
    public ValueWrapper get(Object key) {
        String cacheKey = String.format("%s-%s", this.name, key);
        Result<DataEntry> result = tairManager.get(namespace, cacheKey);
        if (result.isSuccess() && result.getRc() == ResultCode.SUCCESS) {
            return () -> result.getValue().getValue();
        }
        return null;
    }

    @Override
    public <T> T get(Object key, Class<T> type) {
        return (T) get(key).get();
    }

    @Override
    public <T> T get(Object o, Callable<T> callable) {
        return null;
    }

    @Override
    public void put(Object key, Object value) {
        if (null == value) {
            return;
        }

        if (value instanceof Serializable) {
            String cacheKey = String.format("%s-%s", name, key);
            ResultCode result = tairManager.put(
                namespace,
                cacheKey,
                (Serializable) value,
                0,
                timeout
            );

            if (result != ResultCode.SUCCESS) {
                System.out.println(String.format("put %s -- %s into cache error: %s", key, value, result.getMessage()));
            }
        } else {
            throw new RuntimeException(
                String.format("put %s -- %s into cache error: value must be serializable", key, value)
            );
        }
    }

    @Override
    public ValueWrapper putIfAbsent(Object key, Object value) {
        ValueWrapper valueWrapper = get(key);
        if (valueWrapper == null) {
            put(key, value);
        }
        return valueWrapper;
    }

    @Override
    public void evict(Object key) {
        String cacheKey = String.format("%s-%s", name, key);
        ResultCode result = tairManager.delete(namespace, cacheKey);
        if (result == ResultCode.SUCCESS || result == ResultCode.DATANOTEXSITS || result == ResultCode.DATAEXPIRED) {
            return;
        } else {
            String msg = String.format("evict %s cache error: %s", key, result.getMessage());
            System.out.println(msg);
            throw new RuntimeException(msg);
        }
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    public <T,E> List<T> mget(List<E> keys) {
        List<String> cacheKey = keys
            .stream()
            .map(k -> String.format("%s-%s", this.name, k))
            .collect(toList());
        Result<List<DataEntry>> result = tairManager.mget(namespace, cacheKey);

        if (result.isSuccess() && (result.getRc() == ResultCode.SUCCESS) || result.getRc() == ResultCode.PARTSUCC) {
            return result
                .getValue()
                .stream()
                .map(dataEntry -> (T)dataEntry.getValue())
                .collect(toList());
        }
        return new ArrayList<>();
    }

    public void setTairManager(TairManager tairManager) {
        this.tairManager = tairManager;
    }

    public void setNamespace(int namespace) {
        this.namespace = namespace;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

}
