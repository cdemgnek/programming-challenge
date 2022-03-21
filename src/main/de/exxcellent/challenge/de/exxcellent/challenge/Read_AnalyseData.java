package de.exxcellent.challenge;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import static java.lang.Math.abs;

public class Read_AnalyseData {

    // data Input
    private InputStream in;

    // Header of the column with the minimum values.
    private String minValueCol;

    // Header of the column with the maximum values.
    private String maxValueCol;


    // Header of the column with the day Number.
    private String indexCol;

    //Define whether the input data was read successfully
    private boolean fileRead = false;

    //minimum Spread Value
    private int minSpread = 0;

    //day Number of the actual line with the smallest spread
    private String smallestDifferenceIndex;


    private CSVParser parser;

    /**
     * Constructor of the Read_AnalyseData class: initialise
     * a new object of this class
     */
    public Read_AnalyseData(final InputStream in, final String minColHeader, final String maxColHeader,
                            final String dayNumberHeader) {
        this.in = in;

        minValueCol = minColHeader;
        maxValueCol = maxColHeader;
        indexCol = dayNumberHeader;
    }

    /**
     * Read a CSV File and save the data in a CSV Parser
     */
    private void readCSVFile() throws IOException{
        if (fileRead) {
            return;
        }


        parser = CSVParser.parse(in, StandardCharsets.UTF_8,
                CSVFormat.DEFAULT.withHeader());
        fileRead = true;
    }


    /**
     * Get the Data(Index, Min- and Maxvalue) from a CSV Parser and calculate the spread
     * if the spread value is negative, the absolute value will
     * be taken.
     * if two indexes have the same spread value then, the first
     * index is taken.
     * @return day Number of the day with the smallest spread
     */
    public String getSmallestDifferenceIndex() throws IOException {
        if (!fileRead) {
            readCSVFile();
        }

        for (CSVRecord record : parser) {
            int min, max;
            try {
                min = Integer.parseInt(record.get(minValueCol));
                max = Integer.parseInt(record.get(maxValueCol));
            } catch (NumberFormatException e) {
                throw new IOException("Incorrect input number", e);
            }

            int spread = max - min;
            if (spread < 0){
                spread = abs(spread);
            }

            // Assume that the first record is wanted.
            if (smallestDifferenceIndex == null || spread < minSpread) {
                minSpread = spread;
                smallestDifferenceIndex = record.get(indexCol);
            }

        }
        return smallestDifferenceIndex;
    }

}
