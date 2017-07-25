package UI;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame{

    Frame(String title, int x, int y, int width, int height) {
        this.setTitle(title);
        setLayout(new FlowLayout());
        setBounds(x, y, width, height);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    Frame(String title, int width, int height) {
        this(title, 50, 50, width, height);
    }

    Frame(String title) {
        this(title, 50, 50, 300, 120);
    }

    Frame() {
        this("My Swing Application");
    }
}
