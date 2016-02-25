package ch03_lambda_library.exec;

import java.util.Comparator;

public class Exec07 {
    public static void main(String[] args) {
        Comparator<String> stringComparator = stringComparator(false, true, true);
        System.out.println("no-reverse ignoreCase ignoreSpace 'Hel  lO' - 'H  ELL O' : " + stringComparator.compare("Hel  lO", "H ELL O"));
        System.out.println("no-reverse ignoreCase ignoreSpace 'abc' - 'DEF' : " + stringComparator.compare("abc", "DEF"));

        stringComparator = stringComparator(false, false, false);
        System.out.println("no-reverse no-ignoreCase no-ignoreSpace 'Hel  lO' - 'H  ELL O' : " + stringComparator.compare("Hel  lO", "H ELL O"));
        System.out.println("no-reverse no-ignoreCase no-ignoreSpace 'abc' - 'DEF' : " + stringComparator.compare("abc", "DEF"));

        stringComparator = stringComparator(true, false, false);
        System.out.println("reverse no-ignoreCase no-ignoreSpace 'Hel  lO' - 'H  ELL O' : " + stringComparator.compare("Hel  lO", "H ELL O"));
        System.out.println("reverse no-ignoreCase no-ignoreSpace 'abc' - 'DEF' : " + stringComparator.compare("abc", "DEF"));

    }

    public static Comparator<String> stringComparator(final boolean reverse, final boolean ignoreCase, final boolean ignoreSpace) {
        return (s1, s2) -> {
            String compare1 = s1;
            String compare2 = s2;
            if (reverse) {
                compare1 = s2;
                compare2 = s1;
            }

            if (ignoreSpace) {
                compare1 = compare1.replaceAll("\\s", "");
                compare2 = compare2.replaceAll("\\s", "");
            }
            if (ignoreCase) {
                return compare1.compareToIgnoreCase(compare2);
            }

            return compare1.compareTo(compare2);
        };

    }
}
