package com.search.util;


import com.search.emums.RegHeaders;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CSVFileReaderUtil {

    /**
     * This method skip the header when read records from csv
     * Return list of values for a give header key
     */
    public List<String> readRecords(InputStream inputStream, String key) throws IOException {
        List<String> value = new ArrayList<>();
        Iterable<CSVRecord> records = readRecords(inputStream);
        for (CSVRecord record : records) {
            value.add(record.get(key));
        }
        return value;
    }
    /**
     * This method skip the header when read records from csv
     * Return list of values for a give header key
     */
    public List<RegRecord> getRegRecords(InputStream inputStream) throws IOException {
        List<RegRecord> value = new ArrayList<>();
        Iterable<CSVRecord> records = readRecords(inputStream);
        for (CSVRecord record : records) {
              value.add(new RegRecord(record.get(RegHeaders.REGISTRATION.name()),record.get(RegHeaders.MAKE.name()),record.get(RegHeaders.MODEL.name()),record.get(RegHeaders.COLOR.name()),record.get(RegHeaders.YEAR.name())));
        }
        return value;
    }

    /**
     * Returns rows excluding header
     */
    public Iterable<CSVRecord> readRecords(InputStream inputStream) throws IOException {
        return CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(new InputStreamReader(inputStream));
    }

}
