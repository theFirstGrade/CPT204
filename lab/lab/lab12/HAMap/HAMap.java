package lab12.HAMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Hash-based Map
 */
public class HAMap<K, V> implements Iterable<K> {

    /**
     * Represents a key-value pair.
     */
    private class Entry {
        K key;
        V value;

        Entry(K k, V v) {
            key = k;
            value = v;
        }
    }

    private static final int DEFAULT_CAPACITY = 16;
    private static final double DEFAULT_LOAD_FACTOR = 1.5;
    private ArrayList<ArrayList<Entry>> buckets;
    private HashSet<K> keySet;
    private int numBuckets;
    private int numEntries;
    private final double loadFactor;

    /**
     * @return a set of the keys contained in this map.
     */
    public HashSet<K> keySet() {
        return keySet;
    }

    /**
     * @return the number of entries in this map.
     */
    public int size() {
        return numEntries;
    }

    /**
     * @return the number of buckets in this map.
     */
    public int getNumBuckets() {
        return numBuckets;
    }

    /*
     ***************************
     * DO NOT MODIFY CODE ABOVE
     ***************************
     */


    /*
     ***** HELPER METHODS START *****
     */


    // INCLUDE your helper methods in EACH of your submissions that use them





    /*
     ***** HELPER METHODS END *****
     */


    // LAB EXERCISE 12.2 CONSTRUCTORS

    public HAMap(int initialCapacity, double loadFactor) {
        this.loadFactor = loadFactor;
        buckets = new ArrayList<>();
        for (int i = 0; i < initialCapacity; i++) {
            buckets.add(new ArrayList<>());
        }
        numBuckets = initialCapacity;
        numEntries = 0;
        keySet = new HashSet<>();

    }

    public HAMap() {

        this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);

    }

    public HAMap(int initialCapacity) {
        this(initialCapacity, DEFAULT_LOAD_FACTOR);

    }


    // LAB EXERCISE 12.3 CLEAR

    /**
     * Removes all of the entries from this map.
     */
    public void clear() {
        buckets = new ArrayList<>();
        for (int i = 0; i < numBuckets; i++) {
            buckets.add(new ArrayList<>());
        }
        keySet = new HashSet<>();
        numEntries = 0;
    }

    private int reduce(K key, int capacity) {
        return Math.floorMod(key.hashCode(), capacity);
    }

    private void resize(int newCapacity) {

        ArrayList<ArrayList<Entry>> newBuckets = new ArrayList<>();
        for (int i = 0; i < newCapacity; i++) {
            newBuckets.add(new ArrayList<>());
        }

        for (K key : this) {
            int newIndex = reduce(key, newCapacity);
            newBuckets.get(newIndex).add(new Entry(key, get(key)));
        }

        buckets = newBuckets;
        numBuckets = newCapacity;
    }

    // LAB EXERCISE 12.4 CONTAINS KEY and ITERATOR

    /**
     * @param key to be checked
     * @return true iff this map contains an entry with the specified key
     */
    public boolean containsKey(K key) {


        return keySet.contains(key);
    }

    /**
     * @return an Iterator that iterates over the stored keys
     */
    @Override
    public Iterator<K> iterator() {


        return keySet.iterator();
    }


    // EXERCISE 12.1 GET

    /**
     * @param key of the value to be returned
     * @return the value to which the specified key is mapped
     * null if this map contains no entries of the key
     */
    public V get(K key) {

        if (key != null && containsKey(key)) {
            int newIndex = reduce(key, numBuckets);
            ArrayList<Entry> entries = buckets.get(newIndex);
            for (Entry e : entries) {
                if (e.key.equals(key)) {
                    return e.value;
                }
            }
        }


        return null;
    }


    // EXERCISE 12.2 PUT

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained an entry with that key, the old value is replaced.
     * The key is not null.
     *
     * @param key   of the entry to be added
     * @param value of the entry to be added
     */
    public void put(K key, V value) {
        double ratio = (double) numEntries / numBuckets;
        if (ratio >= loadFactor) {
            resize(numBuckets * 2);
        }
        int newIndex = reduce(key, numBuckets);
        if (!containsKey(key)) {
            keySet.add(key);
            buckets.get(newIndex).add(new Entry(key, value));
            numEntries++;
        } else {
            for (int i = 0; i < buckets.get(newIndex).size(); i++) {
                if (buckets.get(newIndex).get(i).key.equals(key)) {
                    buckets.get(newIndex).get(i).value = value;
                }
            }
        }

    }


    // EXERCISE 12.3 REMOVE

    /**
     * Removes the entry for the specified key only if it is
     * currently mapped to the specified value.
     *
     * @param key   of the entry to be removed
     * @param value of the entry to be removed
     * @return the value if entry found,
     * null otherwise
     */
    public V remove(K key, V value) {
//        if (containsKey(key)) {
//            int index = 0;
//            int newIndex = reduce(key, numBuckets);
//            for (int i = 0; i < buckets.get(newIndex).size(); i++) {
//                if (buckets.get(newIndex).get(i).key.equals(key)) {
//                    index = i;
////                    Entry remove = buckets.get(newIndex).remove(i);
//                    numEntries--;
////                    return remove.value;
//                }
//            }
//            ArrayList<Entry> entries = buckets.get(newIndex);
//            ArrayList<Entry> newEntries = new ArrayList<>();
//            for (int i = 0; i < entries.size(); i++) {
//                if (i != index) {
//                    newEntries.add(new Entry(entries.get(i).key, entries.get(i).value));
//                    System.out.println();
//                }
//            }
//            buckets.set(newIndex, newEntries);
////            System.out.println(buckets.get(0));
//            return entries.get(index).value;
//        }


        if (containsKey(key)) {
            int newIndex = reduce(key, numBuckets);
            for (int i = 0; i < buckets.get(newIndex).size(); i++) {
                if (buckets.get(newIndex).get(i).key.equals(key) && buckets.get(newIndex).get(i).value.equals(value)) {
                    Entry remove = buckets.get(newIndex).remove(i);
                    keySet.remove(key);
                    numEntries--;
                    return remove.value;
                }
            }
        }

        return null;
    }

    public static void main(String[] args) {
//        HAMap<String, Integer> map = new HAMap<>();
//        System.out.println(map.containsKey("a"));
//        map.put("a", 1);
//        System.out.println(map.containsKey("a"));
//        System.out.println(map.get("a"));
//        System.out.println(map.size());
//        map.put("b", 2);
//        map.put("c", 3);
//        System.out.println(map.remove("a", 1));
//        for (String key : map) {
//            System.out.println("(" + key + ", " + map.get(key) + ")");
//        }
//        map.clear();
//        System.out.println(map.size());
//        System.out.println(map.containsKey("b"));
//        System.out.println(map.containsKey("c"));

//        HAMap<Integer, String> map = new HAMap<>(32);
//        System.out.println(map.size());
//        System.out.println(map.get(0));
//        System.out.println("setsize=" + map.keySet().size());
//        map.put(0, "a");
//        System.out.println(map.get(0));
//        System.out.println("size=" + map.size());
//        System.out.println("nbuckets=" + map.getNumBuckets());
//        System.out.println("setsize=" + map.keySet().size());
//
//        map.put(0, "b");
//        System.out.println(map.get(0));
//        System.out.println("size=" + map.size());
//        System.out.println("nbuckets=" + map.getNumBuckets());
//        System.out.println("setsize=" + map.keySet().size());
//
//        for (int i = 1; i <= 46; i++) {
//            map.put(i, "x" + i);
//        }
//        System.out.println("size=" + map.size());
//        System.out.println("nbuckets=" + map.getNumBuckets());
//        System.out.println("setsize=" + map.keySet().size());
//        map.put(47, "x" + 47);
//        System.out.println("size=" + map.size());
//        System.out.println("nbuckets=" + map.getNumBuckets());
//        System.out.println("setsize=" + map.keySet().size());
//        map.put(48, "x" + 48);
//        System.out.println("size=" + map.size());
//        System.out.println("nbuckets=" + map.getNumBuckets());
//        System.out.println("setsize=" + map.keySet().size());
//        map.put(49, "x" + 49);
//        System.out.println("size=" + map.size());
//        System.out.println("nbuckets=" + map.getNumBuckets());
//        System.out.println("setsize=" + map.keySet().size());
//
//        System.out.println(map.remove(10, "x"));
//        System.out.println(map.get(10));
//        System.out.println(map.containsKey(10));
//        System.out.println("size=" + map.size());
//        System.out.println("nbuckets=" + map.getNumBuckets());
//        System.out.println("setsize=" + map.keySet().size());
//
//        System.out.println(map.remove(10, new String("x10")));
//        System.out.println(map.get(10));
//        System.out.println(map.containsKey(10));
//        System.out.println("size=" + map.size());
//        System.out.println("nbuckets=" + map.getNumBuckets());
//        System.out.println("setsize=" + map.keySet().size());
//
//        map.put(10, "abc");
//        System.out.println(map.get(10));
//        System.out.println("size=" + map.size());
//        System.out.println("nbuckets=" + map.getNumBuckets());
//        System.out.println("setsize=" + map.keySet().size());
//
//        map.put(10, new String("abc"));
//        System.out.println(map.get(10));
//        System.out.println("size=" + map.size());
//        System.out.println("nbuckets=" + map.getNumBuckets());
//        System.out.println("setsize=" + map.keySet().size());
//
//        map.clear();
//        System.out.println(map.get(0));
//        System.out.println(map.get(49));
//        System.out.println(map.containsKey(0));
//        System.out.println("size=" + map.size());
//        System.out.println("nbuckets=" + map.getNumBuckets());
//        System.out.println("setsize=" + map.keySet().size());

        HAMap<Integer, String> map = new HAMap<>(32);
        System.out.println(map.size());
        System.out.println(map.get(0));
        System.out.println("setsize=" + map.keySet().size());
        map.put(0, "a");
        System.out.println(map.get(0));
        System.out.println("size=" + map.size());
        System.out.println("nbuckets=" + map.getNumBuckets());
        System.out.println("setsize=" + map.keySet().size());

        map.put(0, "b");
        System.out.println(map.get(0));
        System.out.println("size=" + map.size());
        System.out.println("nbuckets=" + map.getNumBuckets());
        System.out.println("setsize=" + map.keySet().size());

        for (int i = 1; i <= 46; i++) {
            map.put(i, "x" + i);
        }
        System.out.println("size=" + map.size());
        System.out.println("nbuckets=" + map.getNumBuckets());
        System.out.println("setsize=" + map.keySet().size());
        map.put(47, "x" + 47);
        System.out.println("size=" + map.size());
        System.out.println("nbuckets=" + map.getNumBuckets());
        System.out.println("setsize=" + map.keySet().size());
        map.put(48, "x" + 48);
        System.out.println("size=" + map.size());
        System.out.println("nbuckets=" + map.getNumBuckets());
        System.out.println("setsize=" + map.keySet().size());
        map.put(49, "x" + 49);
        System.out.println("size=" + map.size());
        System.out.println("nbuckets=" + map.getNumBuckets());
        System.out.println("setsize=" + map.keySet().size());

        System.out.println(map.remove(10, "x"));
        System.out.println(map.get(10));
        System.out.println(map.containsKey(10));
        System.out.println("size=" + map.size());
        System.out.println("nbuckets=" + map.getNumBuckets());
        System.out.println("setsize=" + map.keySet().size());

        System.out.println(map.remove(10, new String("x10")));
        System.out.println(map.get(10));
        System.out.println(map.containsKey(10));
        System.out.println("size=" + map.size());
        System.out.println("nbuckets=" + map.getNumBuckets());
        System.out.println("setsize=" + map.keySet().size());

        map.put(10, "abc");
        System.out.println(map.get(10));
        System.out.println("size=" + map.size());
        System.out.println("nbuckets=" + map.getNumBuckets());
        System.out.println("setsize=" + map.keySet().size());

        map.put(10, new String("abc"));
        System.out.println(map.get(10));
        System.out.println("size=" + map.size());
        System.out.println("nbuckets=" + map.getNumBuckets());
        System.out.println("setsize=" + map.keySet().size());

        map.clear();
        System.out.println(map.get(0));
        System.out.println(map.get(49));
        System.out.println(map.containsKey(0));
        System.out.println("size=" + map.size());
        System.out.println("nbuckets=" + map.getNumBuckets());
        System.out.println("setsize=" + map.keySet().size());
    }

}
