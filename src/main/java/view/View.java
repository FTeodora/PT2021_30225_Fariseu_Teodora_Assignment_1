package view;
import model.Polynomial;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;


public class View extends JFrame{
    public static final Color TEXT_COLOR=new Color(181, 250, 229);
    public static final Color IDLE_TEXT_COLOR=new Color(166, 244, 220, 133);
    public static final Color BACKGROUND_COLOR=new Color(101, 94, 109);
    public static final Color BUTTON_COLOR=new Color(60, 55, 68);
    public static final Color TEXTBOX_BACKGROUND_COLOR=new Color(141, 137, 166);

    public static final Font TEXT_BOX_FONT=new Font("Cambria",Font.PLAIN,16);
    public static final Font TEXT_FONT=new Font("Poor Richard",Font.ITALIC,24);

    private final JTextPane resultLabel;
    private final PolynomialInput polynomial1Field,polynomial2Field;
    private final OperationButton[] operationButtons;

    public View(){
        super("Calculator polinoame");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(680,600);
        setResizable(false);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        JPanel content = new JPanel();
        content.setBorder(new EmptyBorder(35,30,20,30));
        content.setBackground(View.BACKGROUND_COLOR);
        this.add(content,BorderLayout.CENTER);

        operationButtons=new OperationButton[6];
        JPanel buttonPanel=new JPanel(new GridLayout(2,0,10,10));
        buttonPanel.setBackground(View.BACKGROUND_COLOR);
        String[] operations={"P(x)+Q(x)","P(x)-Q(x)","P(x)*Q(x)","P(x)/Q(x)","P'(x)","∫P(x)dx"};

        for(int i=0;i<operations.length;i++)
        {
            operationButtons[i]=new OperationButton(operations[i]);
            buttonPanel.add(operationButtons[i]);
        }
        content.add(buttonPanel);

        polynomial1Field=new PolynomialInput("P");
        polynomial2Field=new PolynomialInput("Q");
        JPanel polynomials=new JPanel();
        polynomials.setBackground(View.BACKGROUND_COLOR);
        GroupLayout polynomialsLayout=new GroupLayout(polynomials);
        polynomialsLayout.setAutoCreateGaps(true);
        polynomialsLayout.setAutoCreateContainerGaps(true);

        polynomialsLayout.setHorizontalGroup(
                polynomialsLayout.createSequentialGroup()
                        .addComponent(this.polynomial1Field)
                        .addComponent(this.polynomial2Field)

        );
        polynomialsLayout.setVerticalGroup(
                polynomialsLayout.createSequentialGroup()
                        .addGroup(polynomialsLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(this.polynomial1Field)
                                .addComponent(this.polynomial2Field)
                        )
        );
        polynomials.setLayout(polynomialsLayout);

        content.add(polynomials);

        resultLabel=new JTextPane();
        resultLabel.setFont(View.TEXT_BOX_FONT.deriveFont(View.TEXT_BOX_FONT.getSize()*1.4f));
        resultLabel.setBackground(View.TEXTBOX_BACKGROUND_COLOR);
        resultLabel.setForeground(View.TEXT_COLOR);
        resultLabel.setPreferredSize(new Dimension(590,230));
        resultLabel.setEditable(false);
        content.add(resultLabel);
        content.validate();
        content.repaint();
    }
    public void setResultLabel(String result){
        this.resultLabel.setText(result);
    }
    public Polynomial getFirstPolynomial(){
        return this.polynomial1Field.getPolynomial();
    }
    public Polynomial getSecondPolynomial(){
        return this.polynomial2Field.getPolynomial();
    }
    public void addOperationButtonListener(int index, ActionListener l){
        this.operationButtons[index].addActionListener(l);
    }
}
