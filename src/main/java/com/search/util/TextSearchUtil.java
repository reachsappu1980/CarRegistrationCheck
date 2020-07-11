package com.search.util;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextSearchUtil {

    /**
     * Search for a String based on the supplied reg expression pattern
     *
     * @param searchPattern Search pattern (reg exp)
     * @param inputStream   File path
     */
    public List<String> searchString(String searchPattern, InputStream inputStream) {

        List<String> listMatches = new ArrayList<>();
        Scanner fileScanner = new Scanner(inputStream);
        Pattern pattern = Pattern.compile(searchPattern);
        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();
            Matcher matcher = pattern.matcher(line);
            while (matcher.find()) {
                listMatches.add(matcher.group(0).replaceAll(" ",""));
            }
        }
        return listMatches;
    }




}
