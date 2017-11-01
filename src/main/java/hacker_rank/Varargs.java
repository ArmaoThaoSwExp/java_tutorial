package hacker_rank.varargs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.Set;
import java.util.HashSet;
import java.lang.Integer;

class Add {
    public void add(int... ints) {
        int sum = 0;
        int curr_int = 0;
        for (int i = 0; i < ints.length; i++) {
            curr_int = ints[i];
            if (i != 0) {
                System.out.print('+');
            }
            System.out.print(curr_int);
            sum += curr_int;
        }
        if (ints.length > 1) {
            System.out.print('=');
            System.out.println(sum);
        }
    }
}

public class Varargs {
    public static void main(String[] args) {
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int n1 = Integer.parseInt(br.readLine());
            int n2 = Integer.parseInt(br.readLine());
            int n3 = Integer.parseInt(br.readLine());
            int n4 = Integer.parseInt(br.readLine());
            int n5 = Integer.parseInt(br.readLine());
            int n6 = Integer.parseInt(br.readLine());
            Add ob = new Add();
            ob.add(n1,n2);
            ob.add(n1,n2,n3);
            ob.add(n1,n2,n3,n4,n5);
            ob.add(n1,n2,n3,n4,n5,n6);
            Method[] methods = Add.class.getDeclaredMethods();
            Set<String> set = new HashSet<>();
            boolean overload = false;
            for(int i = 0; i < methods.length; i++)
            {
                if(set.contains(methods[i].getName()))
                {
                    overload = true;
                    break;
                }
                set.add(methods[i].getName());

            }
            if(overload)
            {
                throw new Exception("Overloading not allowed");
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}

