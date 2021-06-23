
import java.util.Arrays;

public class HashedMap implements MyMap{
    private final int MAXIMUM_CAPACITY = Integer.MAX_VALUE / 2;
    private final int DEFAULT_CAPACITY = 16;
    private final float DEFAULT_LOAD_FACTOR = 0.75f;

    private Node[] hashTable;
    private int size = 0;
    private float threshold;

    //Default constructor
    public HashedMap(){
        hashTable = new Node[DEFAULT_CAPACITY];
        threshold = hashTable.length * DEFAULT_LOAD_FACTOR;
    }

    public HashedMap(int initialCapacity, float loadFactor) {
        if (initialCapacity < 0)
            throw new IllegalArgumentException("Illegal initial capacity: " + initialCapacity);
        if (initialCapacity > MAXIMUM_CAPACITY)
            initialCapacity = MAXIMUM_CAPACITY;

        hashTable = new Node[initialCapacity];
        this.threshold = hashTable.length * loadFactor;
    }

    public HashedMap(int initialCapacity) {
        hashTable = new Node[initialCapacity];
        threshold = hashTable.length * DEFAULT_LOAD_FACTOR;
    }


    public static class Node implements MyMap.Node {
        private final String key;
        private String value;
        private Node next;

        public Node(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public void setNextNode(Node next){
            this.next = next;
        }

        public Node getNextNode() {
            return next;
        }

        @Override
        public String toString() {
            return key + " = " + value;
        }

        @Override
        public String getKey() { return key; }
        @Override
        public String getValue() { return value; }
        @Override
        public void setValue(String value) { this.value = value; }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Node)) return false;
            Node myNode = (Node) o;
            return key.equals(myNode.key) && value.equals(myNode.value);
        }

        @Override
        public int hashCode() {
            int hash = 17;
            for(int i = 0; i < key.length(); i++)
                hash = (31 * hash + key.charAt(i));
            return hash;
        }
    }


    @Override
    public boolean put(String key, String value) {
        if(size + 1 >= threshold){
            threshold *= 2;
            expandTable();
        }

        Node newNode = new Node(key,value);
        int indexInTable = getTableIndex(newNode);
        if(hashTable[indexInTable] == null){
            hashTable[indexInTable] = newNode;
            size++;
            return true;
        }
        else{
            Node oldNode = hashTable[indexInTable];
            if(collisionProcessing(oldNode,newNode)){
                return true;
            }else return sameKeyDifferentValue(hashTable[indexInTable], newNode, newNode.getValue());
        }
    }


    private boolean sameKeyDifferentValue(Node oldNode, Node newNode, String value){
        if(newNode.getKey().equals(oldNode.getKey()) && !newNode.getValue().equals(oldNode.getValue())){
            hashTable[getTableIndex(oldNode)].setValue(value);
            return true;
        }
        return false;
    }

    private boolean collisionProcessing(Node oldNode, Node newNode){
        if(getTableIndex(oldNode) == getTableIndex(newNode) && !oldNode.equals(newNode)){
            oldNode.setNextNode(newNode);
            size++;
            return true;
        }
        return false;
    }


    private void expandTable(){
        Node[] oldHashTable = hashTable;
        hashTable = new Node[oldHashTable.length * 2];
        size = 0;
        for (Node node : oldHashTable) {
            if(node != null){
                Node next = node.getNextNode();
                while(next != null){
                    put(node.getNextNode().getKey(), node.getNextNode().getValue());
                    next = next.getNextNode();
                }
                put(node.getKey(), node.getValue());
            }
        }
    }

    public int capacity() {
        return (hashTable != null) ? hashTable.length : (int) ((threshold > 0) ? threshold : DEFAULT_CAPACITY);
    }


    @Override
    public String remove(String key) {
        String value = null;
        for (Node node : hashTable) {
            if (node != null && node.getKey().equals(key)){
                if(node.getNextNode() != null){
                    value = node.getValue();
                    hashTable[getTableIndex(node)] = node.getNextNode();
                }
                else{
                    value = node.getValue();
                    hashTable[getTableIndex(node)] = null;
                    size--;
                }

            }
        }
        return value;
    }


    @Override
    public boolean containsKey(String key) {
        for (Node node : hashTable) {
            if (node != null && node.getKey().equals(key)){
                return true;
            }
        }
        return false;
    }

    @Override
    public String get(String key) {
        String result = null;
        for (Node node : hashTable) {
            if (node != null && node.getKey().equals(key)){
                result = node.toString();
            }
        }
        return result;
    }

    @Override
    public void clear() {
        size = 0;
        hashTable = new Node[DEFAULT_CAPACITY];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    private int getTableIndex(Node node){
        return (node.hashCode() & 0x7fffffff) % hashTable.length;
    }


    @Override
    public Node[] toArray() {
        Node[] nodes = new Node[size];
        int index = 0;
        for (Node node : hashTable) {
            if (node != null) {
                nodes[index++] = node;
                if (node.getNextNode() != null) {
                    nodes[index++] = node.getNextNode();
                }
            }
        }
        return nodes;
    }


    @Override
    public String toString() {
        return Arrays.toString(toArray());
    }
}
