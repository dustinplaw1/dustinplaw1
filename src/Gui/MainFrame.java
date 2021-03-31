package Gui;



import Gui.Login;
import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.swing.plaf.synth.SynthLookAndFeel;
import java.awt.*;

public class MainFrame{


    public static void main(String[] args) {
        //ad a look and feel design to the GUI
        try{
            UIManager.setLookAndFeel(new  NimbusLookAndFeel ());
        //throw an exception if not allowed
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }


        //Create an instance of a login and
        Login login = new Login();
        try {
            login.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
