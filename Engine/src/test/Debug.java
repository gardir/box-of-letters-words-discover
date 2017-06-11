package test;


import imageparse.Image;

import javax.swing.*;

/**
 * Created by gardir on 08.06.17.
 */
public class Debug {

    public static void main(String[] args) {


        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add( new Image() );
        frame.setVisible(true);

    }

}
