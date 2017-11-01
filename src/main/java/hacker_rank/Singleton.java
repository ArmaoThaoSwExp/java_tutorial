package hacker_rank;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.lang.reflect.*;


public class Singleton {
    private static Singleton singleton = null;
    public String str;

    private Singleton () {
        /* Hidden from public */
    }

    public static Singleton getSingleInstance () {
        if (singleton == null) {
            singleton = new Singleton();
        }
        return singleton;
    }
}
