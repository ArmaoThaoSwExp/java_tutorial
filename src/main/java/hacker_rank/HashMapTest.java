/*
 * https://www.hackerrank.com/challenges/phone-book/problem
 */
package hacker_rank;

import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Scanner;
import java.lang.StringBuilder;

public class HashMapTest {
    private HashMap phone;
    HashMapTest() {
        phone = new HashMap<String, String>();
    }

    private int parseNextSIntAndStartNextLine(Scanner sc) {
        /*
         * Parse the next full line.
         */
        assert sc.hasNextInt() : "There is nothing to read";
        int result = sc.nextInt();
        sc.nextLine();  /* Clear out rest of line */
        return result;
    }

    private String parseNextStringAndStartNextLine(Scanner sc) {
        /*
         * Parse the next full line.
         */
        assert sc.hasNextLine() : "There is nothing to read";
        String name = new String();
        name = sc.nextLine();
        return name;
    }

    public ArrayList<String> parseArgs() {
        int totalEntries = 0;
        ArrayList<String> queries = new  ArrayList<String>();
        System.out.println("Enter inputs");
        try (Scanner sc = new Scanner(System.in)) {
            assert sc.hasNextInt() : "Missing first item which is number of data to input into hash map";
            totalEntries = this.parseNextSIntAndStartNextLine(sc);
            for (int i = 0; i < totalEntries; i++) {
                String name = this.parseNextStringAndStartNextLine(sc);
                String number = this.parseNextStringAndStartNextLine(sc);
                this.phone.put(name, number);
            }

            System.out.println("Grab all queries");

            while (true) {
                String query;
                try {
                    query = this.parseNextStringAndStartNextLine(sc);
                    if (query.equalsIgnoreCase("q")) {
                        break; /* Break when user enters in 'q' or 'Q' */
                    }
                }
                catch (AssertionError e) {
                    break;
                }
                queries.add(query);
            }
        }
        return queries;
    }

    public static void main(String[] args) {
        System.out.println("Hash Map Test Started!");
        HashMapTest hmt = new HashMapTest();
        ArrayList<String> queries = hmt.parseArgs();
        for (String query : queries) {
            if (hmt.phone.containsKey(query)) {
                System.out.println(query + "=" + hmt.phone.get(query));
            }
            else {
                System.out.println("Not found");
            }
        }
    }
}