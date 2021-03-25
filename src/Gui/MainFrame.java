package Gui;

import Gui.Login;
import objects.Employee;

import javax.swing.*;
import java.awt.*;

public class MainFrame  extends JFrame {



//        public static void execute() {
//
//            mainFrame = new JFrame();
//            mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//            mainFrame.setSize(new Dimension(1280, 768));
//            mainFrame.setVisible(true);
//        }


        public static void main (String[]args){

            Employee employee = new Employee();

            Login login = new Login();
            login.executeLogin();

            //mainFrame.setVisible(false);
        }

}

