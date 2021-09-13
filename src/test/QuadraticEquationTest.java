package test;

import main.Math.QuadraticEquation;
import org.junit.Test;

import static org.junit.Assert.*;

public class QuadraticEquationTest {
private QuadraticEquation test = new QuadraticEquation();
    @Test
    public void quadraticEquation() {
        assertEquals(test.quadraticEquation("5,0,0"),"Root number: 1 Root is 0.0");
        assertEquals(test.quadraticEquation("-1,2,3"),"Root number: 2 First root:-1.0 Second root:3.0");
        assertEquals(test.quadraticEquation("1,2,3"),"Root number: 0 No root");
    }
}