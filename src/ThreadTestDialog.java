import javax.swing.*;
import java.awt.event.*;

public class ThreadTestDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonStart;
    private JButton buttonPause;
    private JProgressBar progressBar1;
    private JProgressBar progressBar2;
    private JProgressBar progressBar3;
    private JProgressBar progressBar4;
    private JButton buttonResume;
    private JLabel labelThread1;
    private JLabel labelThread2;
    private JLabel labelThread3;
    private JLabel labelThread4;
    private JLabel labelThread1Total;
    private JLabel labelThread2Total;
    private JLabel labelThread3Total;
    private JLabel labelThread4Total;
    private JLabel labelGrandTotal;
    private JLabel labelGrandTotalValue;


    public AppThread thread1 = new AppThread(labelThread1Total, progressBar1, 30, labelGrandTotalValue);
    public AppThread thread2 = new AppThread(labelThread2Total, progressBar2, 75, labelGrandTotalValue);
    public AppThread thread3 = new AppThread(labelThread3Total, progressBar3, 50, labelGrandTotalValue);
    public AppThread thread4 = new AppThread(labelThread4Total, progressBar4, 20, labelGrandTotalValue);

    public ThreadTestDialog() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonStart);

        buttonStart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onStart();
            }
        });

        buttonPause.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onPause();
            }
        });

        buttonResume.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onResume();
            }
        });

        // call dispose() when cross is clicked00
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        // call dispose() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onStart() {
        thread1.execute();
        thread2.execute();
        thread3.execute();
        thread4.execute();
    }

    private void onPause() {
        thread1.setFlag(false);
        thread2.setFlag(false);
        thread3.setFlag(false);
        thread4.setFlag(false);
    }

    private void onResume() {
        int tempValue;

        tempValue = thread1.getValue();
        thread1 = new AppThread(tempValue, labelThread1Total, progressBar1, 30, labelGrandTotalValue);
        thread1.execute();

        tempValue = thread2.getValue();
        thread2 = new AppThread(tempValue, labelThread2Total, progressBar2, 75, labelGrandTotalValue);
        thread2.execute();

        tempValue = thread3.getValue();
        thread3 = new AppThread(tempValue, labelThread3Total, progressBar3, 50, labelGrandTotalValue);
        thread3.execute();

        tempValue = thread4.getValue();
        thread4 = new AppThread(tempValue, labelThread4Total, progressBar4, 20, labelGrandTotalValue);
        thread4.execute();
    }

    public static void main(String[] args) {
        ThreadTestDialog dialog = new ThreadTestDialog();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
