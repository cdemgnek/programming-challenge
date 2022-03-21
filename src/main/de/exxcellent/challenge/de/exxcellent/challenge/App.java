package de.exxcellent.challenge;

import java.io.IOException;
import java.io.InputStream;

/**
 * The entry class for your solution. This class is only aimed as starting
 * point and not intended as baseline for your software
 * design. Read: create your own classes and packages as appropriate.
 *
 * @author Benjamin Schmid <benjamin.schmid@exxcellent.de>
 */
public final class App {

    private static final String weatherDataFileName = "weather.csv";
    private static final String[] weatherColumnTitles =
            { "MnT", "MxT", "Day" };

    private static final String footballDataFileName = "football.csv";
    private static final String[] footballColumnTitles =
            { "Goals Allowed", "Goals", "Team" };


    /**
     *  @return the day Number of the day with the smallest
     *  temperature Spread
     */
    public static String analyseWeatherData() throws IOException {
        InputStream dataInput =
                App.class.getResourceAsStream(weatherDataFileName);
        Read_AnalyseData tempSpreadAnalyser =
                new Read_AnalyseData(dataInput,
                        weatherColumnTitles[0],
                        weatherColumnTitles[1],
                        weatherColumnTitles[2]);

        return tempSpreadAnalyser.getSmallestDifferenceIndex();
    }

    /**
     *  @return the Team Name of the Team with the smallest
     *  distance between allowed Goals and Goals
     */
    public static String analyseFootballData() throws IOException {
        InputStream dataInput =
                App.class.getResourceAsStream(footballDataFileName);
        Read_AnalyseData footballGoalsAnalyzer =
                new Read_AnalyseData(dataInput,
                        footballColumnTitles[0],
                        footballColumnTitles[1],
                        footballColumnTitles[2]);

        return footballGoalsAnalyzer.getSmallestDifferenceIndex();
    }

    /**
     * This is the main entry method of your program.
     *
     * @param args The CLI arguments passed
     */
    public static void main(String... args) throws IOException {

        // Call of the day analysis function  …
        String dayWithSmallestTempSpread = analyseWeatherData();
        System.out.printf("Day with smallest temperature spread : %s%n",
                dayWithSmallestTempSpread);

        // Call of the goal analysis function …
        String teamWithSmallestGoalSpread = analyseFootballData();
        System.out.printf("Team with smallest goal spread : %s%n",
                teamWithSmallestGoalSpread);
    }
}
