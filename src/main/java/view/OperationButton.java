package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class OperationButton extends JButton {
    public OperationButton(String text){
        super(text);
        setPreferredSize(new Dimension(190,85));
        setBackground(View.BUTTON_COLOR);
        setForeground(View.TEXT_COLOR);
        setFont(View.TEXT_FONT.deriveFont(View.TEXT_FONT.getSize()*1.6f));
        setBorder(new EmptyBorder(2,2,2,2));
    }
}
