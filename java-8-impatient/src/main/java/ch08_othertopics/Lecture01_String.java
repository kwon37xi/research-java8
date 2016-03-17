package ch08_othertopics;

import java.time.ZoneId;

public class Lecture01_String {
    public static void main(String[] args) {
        // String join
        String joined = String.join("/", "usr","local","bin");
        System.out.println("Joined : " + joined);

        String ids = String.join(", ", ZoneId.getAvailableZoneIds());
        System.out.println("Zone IDs : " + ids);
    }
}
