package main.Math;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class PrimeFactorization extends JPanel implements Compute {
    private JPanel organizer, left;
    private JLabel information;
    private JTextField number;
    private JTextArea result;
    private JButton compute;

    public PrimeFactorization() {
        organizer = new JPanel();

        number = new JTextField();
        JPanel input = new JPanel();
        input.setBorder(new TitledBorder(new EtchedBorder(), "User Input"));
        input.setLayout(new GridLayout(1, 2));
        input.add(new JLabel("Number: "));
        input.add(number);
        left = new JPanel();
        left.add(input);

        result = new JTextArea(5, 20); //change to scroll pane
        result.setEditable(false);
        result.setLineWrap(true);
        result.setWrapStyleWord(true);

        compute = new JButton("Compute");
        compute.addActionListener(event -> compute());

        information = new JLabel("Compute the prime factorization for 'Number'  ");
        organizer.setLayout(new BorderLayout());
        organizer.add(information, BorderLayout.PAGE_START);
        organizer.add(left, BorderLayout.LINE_START);
        organizer.add(result, BorderLayout.LINE_END);
        organizer.add(compute, BorderLayout.PAGE_END);
        add(organizer);
    }
    public void compute(){
        int number = 0;
        try{
            number = Integer.parseInt(this.number.getText());
        }catch (Exception e){
            JOptionPane.showMessageDialog(this, " Invalid Argument ");
            return;
        }
        String result = factor(number);
        this.result.setText(result.substring(0,result.length()-2)); //1 occur exception
    }
    public String factor(int num){
        String factor = "";
        int prime = 2;
        while(num!=1){
            if(num%prime==0){
                factor+=(prime+" * ");
                num/=prime;
//                System.out.println(num);
            }else{
                prime++;
            }
        }
        return factor;
    }
}
