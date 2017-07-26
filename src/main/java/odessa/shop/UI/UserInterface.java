package odessa.shop.UI;

import odessa.shop.ExcelHelper.ExcelHelper;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserInterface implements ActionListener {

    private Frame mainFrame;
    private JButton selectFile;
    private JButton start;
    private JButton exit;
    private JLabel labelPath;
    private JFileChooser fileChooser;
    private String absolutePathFile;
    public static JProgressBar progressBar;

    public UserInterface() {
        mainFrame = new Frame("Create TTN");
        mainFrame.setVisible(true);
        selectFile = new JButton("Select File");
        start = new JButton("Start");
        exit = new JButton("Exit");
        fileChooser = new JFileChooser();
        labelPath = new JLabel();
        selectFile.addActionListener(this);
        start.addActionListener(this);
        exit.addActionListener(this);

        progressBar = new JProgressBar();
        progressBar.setStringPainted(true);
        progressBar.setMinimum(0);
        progressBar.setMaximum(100);

        fileChooser.setFileFilter(new MyFilter());

        mainFrame.add(selectFile);
        mainFrame.add(start);
        mainFrame.add(exit);
        mainFrame.add(labelPath);
        mainFrame.add(progressBar);
        progressBar.setVisible(false);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Select File")) {
            int result = fileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                absolutePathFile = fileChooser.getSelectedFile().getAbsolutePath();
                labelPath.setText("Selected file: " + absolutePathFile);
            }
            else{
                labelPath.setText("File not selected!");
        }

        }
        else if (e.getActionCommand().equals("Start")) {

            if (absolutePathFile == null){
                labelPath.setText("File not selected!");
            }
            else {
                Thread t = new Thread(new Runnable() { public void run() {
                    progressBar.setVisible(true);
                    selectFile.setEnabled(false);
                    start.setEnabled(false);
                    exit.setEnabled(false);
                    new ExcelHelper(absolutePathFile);
                    UserInterface.progressBar.setValue(100);
                    selectFile.setEnabled(true);
                    start.setEnabled(true);
                    exit.setEnabled(true);
                }});
                t.start();
            }
        }
        else{
            System.exit(0);
        }
    }
}
