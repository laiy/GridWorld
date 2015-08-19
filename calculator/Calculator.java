import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator extends JFrame{

    private JButton[] b = new JButton[10];
    private JTextField[] t = new JTextField[2]; 
    
    private String op;
    private int boxSize = 60;
     
    public Calculator() {
         
        this.setLayout(null);

        t[0] = new JTextField("");
        b[1] = new JButton("");
        t[1] = new JTextField("");
        b[3] = new JButton("=");
        b[4] = new JButton("");
        
        b[5] = new JButton("+");
        b[5].addActionListener(new CalculateSymbol());
        b[6] = new JButton("-");
        b[6].addActionListener(new CalculateSymbol());
        b[7] = new JButton("*");
        b[7].addActionListener(new CalculateSymbol());
        b[8] = new JButton("/");
        b[8].addActionListener(new CalculateSymbol());
        b[9] = new JButton("OK");
        b[9].addActionListener(new Result());
         
        for(int i = 1; i < 10; i++) {
            if (i != 2) {
                this.add(b[i]);
            }
        }

        for (int i = 0; i < 2; i++) {
            this.add(t[i]);
        }

        t[0].setBounds(0, 0, boxSize, boxSize);
        t[1].setBounds(120, 0, boxSize, boxSize);

        for (int i = 1; i < 5; i++) {
            if (i != 2) {
                b[i].setBounds(boxSize * i, 0, boxSize, boxSize);
            }
        }

        for (int i = 5; i < 10; i++) {
            b[i].setBounds((i - 5) * boxSize, boxSize, boxSize, boxSize);
        }
            
        this.setSize(300, 150);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
     
    class CalculateSymbol implements ActionListener{
        public void actionPerformed(ActionEvent e){
            Object obj=e.getSource();
            op = ((JButton)obj).getText();
            b[1].setText(op);
        }
    }

    class Result implements ActionListener{
        private int ans;
        public void actionPerformed(ActionEvent e){
            switch (op) {
                case "+":
                    ans = Integer.parseInt(t[0].getText()) + Integer.parseInt(t[1].getText());
                    b[4].setText(ans + "");
                    break;
                case "-":
                    ans = Integer.parseInt(t[0].getText()) - Integer.parseInt(t[1].getText());
                    b[4].setText(ans + "");
                    break;
                case "*":
                    ans = Integer.parseInt(t[0].getText()) * Integer.parseInt(t[1].getText());
                    b[4].setText(ans + "");
                    break;
                case "/":
                    ans = Integer.parseInt(t[0].getText()) / Integer.parseInt(t[1].getText());
                    b[4].setText(ans + "");
                    break;
            }
        }
    }

    public static void main(String[] args) {
        new Calculator();
    }

}
