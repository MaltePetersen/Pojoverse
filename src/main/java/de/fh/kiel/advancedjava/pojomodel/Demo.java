package de.fh.kiel.advancedjava.pojomodel;

import java.io.File;
import java.io.IOException;

public class Demo {
    static {
        File myObj = new File("filename.txt");
        try {
            myObj.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
    }
}
