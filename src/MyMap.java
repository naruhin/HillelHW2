public interface MyMap {
    interface Node {
        String getKey();
        String getValue();
        void setValue(String value);
    }

    void clear();

    boolean containsKey(String key);

    String get(String key);

    boolean isEmpty();

    String remove(String key); // Returns the value to which this map previously associated the key, or null

    boolean put(String key, String value); // Returns the previous value associated with key, or null

    int size();

    Node[] toArray();
}
