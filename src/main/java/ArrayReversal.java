
public class ArrayReversal {
    public ArrayReversal() {
        /* Do nothing for constructor */
    }

    static String reverseStr(String inStr) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < inStr.length(); i++) {
            result.insert(0, inStr.charAt(i));
        }
        return result.toString();
    }

    public static void main(String[] args) {
        for (String arg: args) {
            String result = reverseStr(arg);
            System.out.println(String.format("Input string is %s; reversal string is %s", arg, result));
        }
    }
}
