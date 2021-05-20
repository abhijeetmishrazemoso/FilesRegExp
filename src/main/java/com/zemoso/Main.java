package com.zemoso;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.Collection;
import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Main {
    public static void main(String[] args) {
        File f = new File("/home");
        String[] extensions = {"txt","log","java"};
        //reading only particular extensions
        Collection<File> files = FileUtils.listFiles(f, extensions, true);
        Iterator<File> iterator = files.iterator();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the Regex:Type 'exit' to Exit:");
        String regex = sc.next();
        if (regex.equals("exit")) {
            System.exit(1);
        }
        while (iterator.hasNext()) {
            File tempFile = iterator.next();
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(tempFile.getName());
            if (m.find()) {
                String output = String.format("Found '%s' at %d, %d in file:%s", m.group(), m.start(), m.end(), tempFile.getName());
                System.out.println(output);
            }
        }
    }

}
