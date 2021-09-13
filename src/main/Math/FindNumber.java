package main.Math;

import javax.swing.*;

public class FindNumber extends JTabbedPane {
    private Multiple multiple ;
    private PrimeFactorization primeFactorization;
    private Prime prime;
    private GCD gcd;
    private LCM lcm;
    private RandomNumber randomNumber;
    public FindNumber(){
        randomNumber = new RandomNumber();
        multiple = new Multiple();
        primeFactorization = new PrimeFactorization();
        prime = new Prime();
        gcd = new GCD();
        lcm = new LCM();
        addTab("Random number",randomNumber);
        addTab("Multiple",multiple);
        addTab("Prime Factorization",primeFactorization);
        addTab("Prime Number",prime);
        addTab("GCD",gcd);
        addTab("LCM",lcm);
    }

}
