package odessa.shop.UI;

import odessa.shop.ExcelHelper.ExcelHelper;
import odessa.shop.LogHelper.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserInterface implements ActionListener {

    private Frame mainFrame;
    private JButton selectFile;
    private JButton start;
    private JButton exit;
    private JLabel labelPath;
    private JLabel absolutePathFileLog;
    private JFileChooser fileChooser;
    private String absolutePathFile;

    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;

    public static JProgressBar progressBar;

    public UserInterface() {
        mainFrame = new Frame("Create TTN");
        mainFrame.setVisible(true);
        selectFile = new JButton("Select File");
        selectFile.setPreferredSize(new Dimension(100, 25));
        start = new JButton("Start");
        start.setPreferredSize(new Dimension(100, 25));
        exit = new JButton("Exit");
        exit.setPreferredSize(new Dimension(100, 25));
        fileChooser = new JFileChooser();

        labelPath = new JLabel();
        absolutePathFileLog = new JLabel();

        selectFile.addActionListener(this);
        start.addActionListener(this);
        exit.addActionListener(this);

        progressBar = new JProgressBar();
        progressBar.setStringPainted(true);
        progressBar.setMinimum(0);
        progressBar.setMaximum(100);
        progressBar.setPreferredSize(new Dimension(300, 25));

        fileChooser.setFileFilter(new MyFilter());

        panel1 = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();
        panel4 = new JPanel();
        mainFrame.add(panel1);
        mainFrame.add(panel2);
        mainFrame.add(panel3);
        mainFrame.add(panel4);

        panel1.add(selectFile);
        panel1.add(start);
        panel1.add(exit);
        panel2.add(labelPath);
        panel3.add(progressBar);
        panel4.add(absolutePathFileLog);
        progressBar.setVisible(false);
        absolutePathFileLog.setVisible(false);
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
                    absolutePathFileLog.setVisible(true);
                    absolutePathFileLog.setText(Logger.get().getLogPath());
                }});
                t.start();
            }
        }
        else{
            System.exit(0);
        }
    }
}
