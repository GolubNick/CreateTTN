package odessa.shop;

import odessa.shop.UI.UserInterface;
import javax.swing.*;

public class Program {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new UserInterface();
            }
        });
    }
}
