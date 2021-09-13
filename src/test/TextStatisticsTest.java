package test;

import main.Text.TextStatistics;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TextStatisticsTest {

    @Test
    void testCountNumOfWords() {
        assertEquals(TextStatistics.countNumOfWords("Hello"),1);
        assertEquals(TextStatistics.countNumOfWords("Hello World "),2);
    }

    @Test
    void testCountAverageWordsLength() {
        assertEquals(TextStatistics.countAverageWordsLength("Hello"),5);
        assertEquals(TextStatistics.countAverageWordsLength("Hello World "),5);
    }

    @Test
    void testCountNumberOfVowels() {
        assertEquals(TextStatistics.countNumberOfVowels("Hello"),2);
        assertEquals(TextStatistics.countNumberOfVowels("Hello World "),3);
    }
}