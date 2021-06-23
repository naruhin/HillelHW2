/*
Написать метод который принимает 2 строки - a, b.
Метод должне вернуть количество символов которые у них различаются.
Если строки разной длины то вернуть -1.

Примеры:
a="asdf", b="qwerty" => -1
a="abb", b="bba" => 0
a="abc", b="dca" => 1
a="aaa", b="fga" => 2
a="abc", b="dfg" => 3
 */

import java.util.HashSet;
import java.util.Set;

public class AlternativeTask {
    public static int compareStrings(String a, String b) {
        int result;

        Set<Character> charSet1 = new HashSet<>();
        Set<Character> charSet2 = new HashSet<>();

        //Filling sets with chars from strings a and b
        for (int i = 0; i < a.length(); i++) { charSet1.add(a.charAt(i)); }
        for (int i = 0; i < b.length(); i++) { charSet2.add(b.charAt(i)); }

//        System.out.println(a + " - to set : " + charSet1);
//        System.out.println(b + " - to set : " + charSet2);

        if (a.length() == b.length()) {
            int temp1 = 0;
            int temp2 = 0;

            //Compare all chars in string with set in string A and string B
            //To correctly calculate the number of differences in strings we check them from two sides
            for (int i = 0; i < a.length(); i++) { temp1 = !charSet2.contains(a.charAt(i)) ? temp1 + 1 : temp1; }
            for (int i = 0; i < b.length(); i++) { temp2 = !charSet1.contains(b.charAt(i)) ? temp2 + 1 : temp2; }

            result = temp1 != temp2 ? Math.max(temp1, temp2) : temp1;
        }
        else { result = -1; }

        return result;
    }

    public static void main(String[] args) {
        System.out.println("a=\"asdf\", b=\"qwerty\" => " + compareStrings("asdf", "qwerty") + "\n");
        System.out.println("a=\"abb\", b=\"bba\" => " + compareStrings("abb", "bba") + "\n");
        System.out.println("a=\"abc\", b=\"dca\" => " + compareStrings("abc", "dca") + "\n");
        System.out.println("a=\"aaa\", b=\"fga\" => " + compareStrings("aaa", "fga") + "\n");
        System.out.println("a=\"abc\", b=\"dfg\" => " + compareStrings("abc", "dfg") + "\n");
    }
}
