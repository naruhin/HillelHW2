import java.util.Arrays;


public class HashedSet implements MySet{
    private final HashedMap map = new HashedMap();

    // Dummy value to associate with an String in the backing Map
    private String PRESENT = "";

    @Override
    public boolean add(String e) {
        return map.put(e, PRESENT);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public boolean contains(String o) {
        return map.containsKey(o);
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean remove(String o) {
        return map.remove(o) != null;
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public String[] toArray() {
        String[] result = new String[map.size()];
        MyMap.Node[] nodes = map.toArray();
        for (int i = 0; i < result.length;i++){
            result[i] = nodes[i].getKey();
        }
        return result;
    }

    @Override
    public String toString() {
        return Arrays.toString(toArray());
    }
}
