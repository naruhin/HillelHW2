import java.util.HashMap;

public class Main {
    public static void main(String[] args) {

        //HashedSet
        HashedSet set = new HashedSet();
        set.add("Dima");
        set.add("Vasya");
        set.add("Vasya");
        set.add("Serega");

        System.out.println("HASHSET REALISATION\n");
        System.out.println(set);
        System.out.println("Is set empty? - " + set.isEmpty());
        System.out.println("Size of set - " + set.size());
        System.out.println("If set conteins \"Dima\"? - " +  set.contains("Dima"));
        System.out.println("Remove \"Vasya\" - " + set.remove("Vasya"));
        System.out.println(set + " size - " + set.size());

        //HashedMap
        HashedMap map = new HashedMap();
        map.put("Oleg","23");
        map.put("Sergey","32");
        map.put("Anatoly","24");
        map.put("Nikita","27");
        map.put("Nikolay","27");
        map.put("Kirill","19");
        map.put("Pasha","22");
        map.put("Vladimir","33");

        System.out.println("\n\nHASHMAP REALISATION\n");
        System.out.println("Contains nikita? - " + map.containsKey("Nikita"));
        System.out.println("GET - " + map.get("Pasha"));
        System.out.println("Size of map - " + map.size());
        System.out.println("Is map empty? - " + map.isEmpty());
        System.out.println("Capacity of map " + map.capacity());
        System.out.println(map);
        System.out.println("removing Kirill - " + map.remove("Kirill"));
        System.out.println(map);
    }
}
