package cn.edu.nefu.gdms.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

/**
 * Created by dingyunxiang on 16/12/13.
 */
public class EqualMap<K, V> {
    private HashMap<K, V> map1 = new HashMap<K, V>();
    private HashMap<V, K> map2 = new HashMap<V, K>();

    public int size() {
        return map1.size();
    }

    public boolean isEmpty() {
        return map1.isEmpty();
    }

    public boolean containsKey(K k) {
        return map1.containsKey(k) || map2.containsKey(k);
    }

    public Object removeFromOne(K k) {
        return map1.remove(k);
    }

    public void clear() {
        map1.clear();
        map2.clear();
    }

    public Set keySet() {
        return map1.keySet();
    }

    public Collection values() {
        return map1.values();
    }


    public void putVal(K k, V v) {
        map1.put(k, v);
        map2.put(v, k);
    }

    public V getFromKey(K k) {
        return map1.get(k);
    }

    public K getFromVal(V v) {
        return map2.get(v);
    }

    public void clean() {
        map1.clear();
        map2.clear();
    }

}
