package Gui;

import Gui.Login;
import javax.swing.*;
import java.awt.*;

public class MainFrame  extends JFrame {
    private static JFrame mainFrame;


//        public static void execute() {
//
//            mainFrame = new JFrame();
//            mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//            mainFrame.setSize(new Dimension(1280, 768));
//            mainFrame.setVisible(true);
//        }


        public static void main (String[]args){

            Login.executeLogin();
            //mainFrame.setVisible(false);
        }

}

