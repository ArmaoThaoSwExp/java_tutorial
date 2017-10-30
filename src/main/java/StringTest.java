public class StringTest {
    public static void main(String[] args) {
        System.out.println("String Test Class. Print all string in args.");
        System.out.println("Length of args: " + (args.length));
        for (String x: args) {
            System.out.println(x);
        }
        System.out.println("StringTest class!");

        String myStr = "Hello World!";
        System.out.println("myStr: " + myStr);
        char[] helloArray = {'h', 'e', 'l', 'l', 'o', '.'};
        String helloString = new String(helloArray);
        System.out.println("helloString: " + helloString);

        String helloWorld = new String("Hello World!");
        System.out.println("helloWorld Compare to 'Hello': " + helloWorld.compareTo("Hello"));
        System.out.println("helloWorld Compare to 'World!': " + helloWorld.compareTo("World!"));
    }
}