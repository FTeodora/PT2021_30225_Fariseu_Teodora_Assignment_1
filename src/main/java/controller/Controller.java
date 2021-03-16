package controller;

import model.Model;
import view.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {
    private Model model;
    private View view;
    public Controller(){
        model=new Model();
        view=new View();
        view.addOperationButtonListener(0,new AddListener());
        view.addOperationButtonListener(1,new DifListener());
        view.addOperationButtonListener(2,new MultiplyListener());
        view.addOperationButtonListener(3,new DivListener());
        view.addOperationButtonListener(4,new DeriveListener());
        view.addOperationButtonListener(5,new IntegrateListener());
    }
    class AddListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            view.setResultLabel(model.sum(view.getFirstPolynomial(),view.getSecondPolynomial()));

        }
    }
    class DifListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            view.setResultLabel(model.dif(view.getFirstPolynomial(),view.getSecondPolynomial()));
        }
    }
    class MultiplyListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            view.setResultLabel(model.multiply(view.getFirstPolynomial(),view.getSecondPolynomial()));
        }
    }
    class DivListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            view.setResultLabel(model.divide(view.getFirstPolynomial(),view.getSecondPolynomial()));

        }
    }
    class DeriveListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            view.setResultLabel(model.derive(view.getFirstPolynomial()));

        }
    }
    class IntegrateListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            view.setResultLabel(model.integrate(view.getFirstPolynomial()));

        }
    }
}
