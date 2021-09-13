package main.Text;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class TextStatistics extends JPanel {
    private JTextField numberOfWords, averageLength, numberOfVowels, keyword, numberOfKeyWord, orginalWord, modifyWord, numberOfModification;
    private JButton compute;
    private JTextArea text;


    // MODIFIES: this
    // EFFECTS:  construct the panel, add several textfields and buttons
    public TextStatistics() {
        numberOfWords = new JTextField();
        averageLength = new JTextField();
        numberOfVowels = new JTextField();
        keyword = new JTextField();
        numberOfKeyWord = new JTextField();

        numberOfWords.setEditable(false);
        averageLength.setEditable(false);
        numberOfVowels.setEditable(false);
        numberOfKeyWord.setEditable(false);
        JPanel statisticsPanel = new JPanel();
        statisticsPanel.setBorder(new TitledBorder(new EtchedBorder(), "Statistics"));
        statisticsPanel.setLayout(new GridLayout(5, 2));
        statisticsPanel.add(new JLabel("Number of words: "));
        statisticsPanel.add(numberOfWords);
        statisticsPanel.add(new JLabel("Average word length: "));
        statisticsPanel.add(averageLength);
        statisticsPanel.add(new JLabel("Number of vowels: "));
        statisticsPanel.add(numberOfVowels);
        statisticsPanel.add(new JLabel("Find number of 'this' word"));
        statisticsPanel.add(keyword);
        statisticsPanel.add(new JLabel("Number of keyword"));
        statisticsPanel.add(numberOfKeyWord);

        orginalWord = new JTextField();
        modifyWord = new JTextField();
        numberOfModification = new JTextField();
        numberOfModification.setEditable(false);
        JPanel modifyPanel = new JPanel();
        modifyPanel.setBorder(new TitledBorder(new EtchedBorder(), "Modify"));
        modifyPanel.setLayout(new GridLayout(3, 2));
        modifyPanel.add(new JLabel("Word want change: "));
        modifyPanel.add(orginalWord);
        modifyPanel.add(new JLabel("Replace with: "));
        modifyPanel.add(modifyWord);
        modifyPanel.add(new JLabel("Number of changes: "));
        modifyPanel.add(numberOfModification);

        JPanel right = new JPanel();
        right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));
        right.add(statisticsPanel);
        right.add(modifyPanel);

        text = new JTextArea(20, 40);
        text.setLineWrap(true);
        text.setWrapStyleWord(true);
        JScrollPane scroll = new JScrollPane(text);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        JPanel organizer = new JPanel();
        organizer.setLayout(new BorderLayout());

        compute = new JButton("Compute Statistics");
        compute.addActionListener(e -> compute());
        // organizer.add(information, BorderLayout.PAGE_START);
        organizer.add(scroll, BorderLayout.LINE_START);
        organizer.add(right, BorderLayout.LINE_END);
        organizer.add(compute, BorderLayout.PAGE_END);

        add(organizer);
    }

    // EFFECTS:  get the user input, and compute and set the result on panel accordingly.
    public void compute() {
        String result = text.getText();
        text.setText(result.replaceAll("\\b"+orginalWord.getText()+"\\b", modifyWord.getText()));
        numberOfModification.setText(findNumberOfTheWord(result, orginalWord.getText())+"");
        result = text.getText();
        numberOfWords.setText(countNumOfWords(result) + "");
        averageLength.setText(Math.floor(countAverageWordsLength(result) * 100) / 100 + "");
        numberOfVowels.setText(countNumberOfVowels(result) + "");
        numberOfKeyWord.setText(findNumberOfTheWord(result, keyword.getText()) + "");
    }

    //EFFECTS:  receive a string, and return number of words in the string.
    public static int countNumOfWords(String str) {
        if (str == null || str.isEmpty()) {
            return 0;
        }
        String[] words = str.split("\\s+");
        return words.length;
    }

    //EFFECTS:  receive a string, and return the average number of words in the string.
    public static double countAverageWordsLength(String str) {
        double totalChar = 0;
        if (str == null || str.isEmpty()) {
            return totalChar;
        }
        String[] words = str.split("\\s+");
        for (String s : words) {
            totalChar += s.length();
        }
        return totalChar / words.length;
    }

    //EFFECTS:  receive a string, and count number of vowels in the string.
    public static int countNumberOfVowels(String str) {
        int count = 0;
        str = str.toLowerCase();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == 'a' || str.charAt(i) == 'e' || str.charAt(i) == 'i' || str.charAt(i) == 'o'
                    || str.charAt(i) == 'u') {
                count++;
            }
        }
        return count;
    }

    //EFFECTS:  receive two string, and count number of the second string appear in the first string.
    public static int findNumberOfTheWord(String context, String word) {
        if (context == null || context.isEmpty()) {
            return 0;
        }
        int count = 0;
        String[] words = context.split("\\s+");
        for (String s : words) {
            if (s.equals(word)) {
                count++;
            }
        }
        return count;
    }

}
