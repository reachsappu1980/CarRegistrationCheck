package com.search;

import com.search.emums.RegHeaders;
import com.search.util.CSVFileReaderUtil;
import com.search.util.TextSearchUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

//https://howtodoinjava.com/java/collections/arraylist/compare-two-arraylists/

public class CompareRegistrationDetails {

    private static final String NUMBER_PLATE_PATTERN = "[A-Z]{2}[0-9]{2}[A-Z]{3}";

    public void compereRegistration() throws IOException {

        CSVFileReaderUtil reader = new CSVFileReaderUtil();
        List<String> numberPlates = reader.readRecords(getFileAsInputStream("car_output.txt"), RegHeaders.REGISTRATION.name());

        TextSearchUtil searchUtil = new TextSearchUtil();
        List<String> foundPlates = searchUtil.searchString(NUMBER_PLATE_PATTERN, getFileAsInputStream("car_input.txt"));

        numberPlates.removeAll(foundPlates);
        System.out.println(numberPlates);

    }

    private InputStream getFileAsInputStream(String filePath) {
        return getClass().getClassLoader().getResourceAsStream(filePath);
    }

    public static void main(String[] args) throws IOException {
        CompareRegistrationDetails c = new CompareRegistrationDetails();
        c.compereRegistration();
    }
}
