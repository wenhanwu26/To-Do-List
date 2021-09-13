package main.Math;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.*;

public class Prime extends JPanel implements Compute{
    private JPanel organizer, left;
    private JLabel information;
    private JTextField start, end, numberOfPrime, number, isPrime;
    private JTextArea result;
    private JButton compute;

    public Prime() {
        organizer = new JPanel();

        start = new JTextField();
        end = new JTextField();
        numberOfPrime = new JTextField();
        numberOfPrime.setEditable(false);
        JPanel input = new JPanel();
        input.setBorder(new TitledBorder(new EtchedBorder(), "User Input"));
        input.setLayout(new GridLayout(3, 2));
        input.add(new JLabel("Start: "));
        input.add(start);
        input.add(new JLabel("End: "));
        input.add(end);
        input.add(new JLabel("Number of Prime: "));
        input.add(numberOfPrime);


        number = new JTextField();
        isPrime = new JTextField();
        isPrime.setEditable(false);
        JPanel anotherInput = new JPanel();
        anotherInput.setBorder(new TitledBorder(new EtchedBorder(), "User Input"));
        anotherInput.setLayout(new GridLayout(2, 2));
        anotherInput.add(new JLabel("Number: "));
        anotherInput.add(number);
        anotherInput.add(new JLabel("isPrime: "));
        anotherInput.add(isPrime);

        left = new JPanel();
        left.setLayout(new GridLayout(2, 1));
        left.add(input);
        left.add(anotherInput);

        result = new JTextArea(20, 40);
        result.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(result);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        result.setLineWrap(true);
        result.setWrapStyleWord(true);

        compute = new JButton("Compute");
        compute.addActionListener(event -> compute());

        information = new JLabel("Find the prime number from 'Start' to 'End' ");
        organizer.setLayout(new BorderLayout());
        organizer.add(information, BorderLayout.PAGE_START);
        organizer.add(left, BorderLayout.LINE_START);
        organizer.add(scrollPane, BorderLayout.LINE_END);
        organizer.add(compute, BorderLayout.PAGE_END);
        add(organizer);
    }

    public void compute() {
        int start = 0;
        int end = 0;
        try {
            start = Integer.parseInt(this.start.getText());
            end = Integer.parseInt(this.end.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, " Invalid Argument ");
            return;
        }
        IntStream stream = IntStream.range(start, end + 1).filter(Prime::isPrime);
        List<Integer> list = stream.boxed().collect(Collectors.toList());
        ArrayList<Integer>
                prime = new ArrayList<Integer>(list);
        numberOfPrime.setText(prime.size()+"");
        String primeNum = "";
        int count = 0;
        for(Integer num:prime){
            primeNum+=(num+", ");
            count++;
        }
        result.setText(primeNum);

        boolean isPrime = false;
        int test = 0;
        try{
            test = Integer.parseInt(number.getText());
        }catch (Exception e){
            this.isPrime.setText("false");
            return;
        }
        this.isPrime.setText(isPrime(test)+"");
    }

    public static boolean isPrime(int num) {
        if(num<=1){
            return false;
        }
        for (int i = num - 1; i > 1; i--) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}
