package com.zemoso;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Main {
    public static void main(String[] args) {
        File f = new File("/home");
        String[] extensions = {"txt","log"};
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
            String allLines = getAllLines(tempFile);
            Matcher m = p.matcher(allLines ==null?"":allLines);
            while(m.find()) {
                String output = String.format("Found '%s' at %d, %d in file:%s",m.group(),m.start(),m.end(),tempFile.getName());
                System.out.println(output);
            }
        }
    }

    /**
     * Gets all lines from a file
     * @param file File to get text from
     * @return Contents of File as single String
     */
    private static String getAllLines(File file) {
        try {
            List<String> strings = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
            return String.join(System.lineSeparator(), strings);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        }
}
