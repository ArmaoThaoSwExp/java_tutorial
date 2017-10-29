
enum TestEnumOutsideClass{APPLE, BANAN, OK}

class FreshJuice {
    enum FreshJuiceSize{ SMALL, MEDIUM, LARGE };
    FreshJuiceSize size;
}

public class FreshJuiceTest {
    public static void main(String args[]) {
        FreshJuice juice = new FreshJuice();
        juice.size = FreshJuice.FreshJuiceSize.MEDIUM;

        TestEnumOutsideClass test_enum = TestEnumOutsideClass.APPLE;
        System.out.println("Size: " + juice.size);
        System.out.println("test_enum should be apple: " + test_enum);
    }
}
