package cn.edu.nefu.gdms.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by dingyunxiang on 16/12/13.
 */
public class EqualMap<K, V> {
    private HashMap<K, V> map1 = new HashMap<K, V>();
    private HashMap<V, K> map2 = new HashMap<V, K>();

    private List<K> key = new ArrayList<K>();
    private List<V> value = new ArrayList<V>();

    public int size() {
        return key.size();
    }

    public boolean isEmpty() {
        return map1.isEmpty();
    }

    public boolean containsKey(K k) {
        return map1.containsKey(k) || map2.containsKey(k);
    }


    public void clear() {
        map1.clear();
        map2.clear();
        key.clear();
        value.clear();
    }

    public List<K> keySet() {
        return key;
    }

    public List<V> values() {
        return value;
    }


    public void putVal(K k, V v) {
        key.add(k);
        value.add(v);
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
