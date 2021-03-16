package view;

import model.Polynomial;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class PolynomialInput extends JPanel{
    private static final String dummyText="a1x^n+...+a(n-1)x+an";
    private final JLabel polynomialName;
    private final JTextField polynomialInput;
    public  PolynomialInput(String polynomialName){
        this.polynomialInput=new JTextField(dummyText);
        this.polynomialInput.setFont(View.TEXT_BOX_FONT);
        this.polynomialInput.setForeground(View.IDLE_TEXT_COLOR);
        this.polynomialInput.setBackground(View.TEXTBOX_BACKGROUND_COLOR);
        this.polynomialInput.setPreferredSize(new Dimension(225,35));
        //this.polynomialInput.setBorder(BorderFactory.createLineBorder(View.TEXT_COLOR,1));
        this.polynomialInput.setBorder(new EmptyBorder(1,1,1,1));
        this.polynomialInput.addFocusListener(new FocusListener(){
            @Override
            public void focusGained(FocusEvent e){
                if(PolynomialInput.this.polynomialInput.getText().equals(dummyText))
                {
                    PolynomialInput.this.polynomialInput.setText("");
                    PolynomialInput.this.polynomialInput.setForeground(View.TEXT_COLOR);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(PolynomialInput.this.polynomialInput.getText().trim().equals(""))
                {
                    PolynomialInput.this.polynomialInput.setText(dummyText);
                    PolynomialInput.this.polynomialInput.setForeground(View.IDLE_TEXT_COLOR);
                }
            }
        });

        this.polynomialName=new JLabel(polynomialName+"(x)=");
        this.polynomialName.setForeground(View.TEXT_COLOR);
        this.polynomialName.setBackground(View.BACKGROUND_COLOR.darker());
        this.polynomialName.setFont(View.TEXT_FONT);
        GroupLayout layout=new GroupLayout(this);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addComponent(this.polynomialName)
                        .addComponent(this.polynomialInput)

        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(this.polynomialName)
                                .addComponent(this.polynomialInput)
        )
        );
        setLayout(layout);

        this.setBackground(View.BACKGROUND_COLOR);
    }
public Polynomial getPolynomial(){
        if(polynomialInput.getText().equals(dummyText))
            return new Polynomial();
        return Polynomial.parsePolynomial(this.polynomialInput.getText());
}
}
