package odessa.shop.UI;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame{

    Frame(String title, int x, int y, int width, int height) {
        this.setTitle(title);
        setLayout(new FlowLayout());
        setBounds(x, y, width, height);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
    }

    Frame(String title) {
        this(title, 150, 150, 330, 175);
    }

}
