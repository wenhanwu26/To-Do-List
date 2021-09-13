package main.Math;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Multiple extends JPanel implements Compute{
    private JPanel organizer, left;
    private JLabel information;
    private JTextField multiple, start, end, numberOfMultiple;
    private JTextArea result;
    private JButton compute;

    public Multiple() {
        organizer = new JPanel();

        multiple = new JTextField();
        start = new JTextField();
        end = new JTextField();
        numberOfMultiple = new JTextField();
        numberOfMultiple.setEditable(false);
        JPanel input = new JPanel();
        input.setBorder(new TitledBorder(new EtchedBorder(), "User Input"));
        input.setLayout(new GridLayout(4, 2));
        input.add(new JLabel("Multiple: "));
        input.add(multiple);
        input.add(new JLabel("Start: "));
        input.add(start);
        input.add(new JLabel("End: "));
        input.add(end);
        input.add(new JLabel("Number of Multiple: "));
        input.add(numberOfMultiple);
        left = new JPanel();
        left.add(input);


        result = new JTextArea(20, 40); //change to scroll pane
        result.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(result);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        result.setLineWrap(true);
        result.setWrapStyleWord(true);

        compute = new JButton("Compute");
        compute.addActionListener(event -> compute());

        information = new JLabel("Find the multiple of 'Multiple' from 'Start' to 'End' ");
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
        IntStream stream = IntStream.range(start, end + 1).filter(this::isMultiple);
        List<Integer> list = stream.boxed().collect(Collectors.toList());
        ArrayList<Integer>
                multiple = new ArrayList<Integer>(list);
        numberOfMultiple.setText(multiple.size() + "");
        String multipleNum = "";
        int count = 0;
        for (Integer num : multiple) {
            multipleNum += (num + ", ");
            count++;
        }
        result.setText(multipleNum);
    }

    public boolean isMultiple (int num) {
        int multiple = 0;
        try {
            multiple = Integer.parseInt(this.multiple.getText());
            if(multiple==0){
                throw new Exception();
            }
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(this, " Invalid Argument ");
            return false;
        }
        return num % multiple == 0;
    }
}
