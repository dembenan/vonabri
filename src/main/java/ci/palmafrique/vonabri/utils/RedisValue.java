package ci.palmafrique.vonabri.utils;

public interface RedisValue<T> {

    T get(String key);
    boolean save(String key, T value, boolean isDelay);
    void delete(String key);
    boolean supports(String s);
}
