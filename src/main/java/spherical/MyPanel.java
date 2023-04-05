package spherical;

import java.awt.Dimension;

import javax.swing.*;
import java.awt.*;

class MyPanel extends JPanel{
    
    MyPanel(int h, int w, Color c){

        this.setBackground(c);
        this.setPreferredSize(new Dimension(w, h));

    }

}
