import javax.swing.*;
import java.util.List;

public class AppThread extends SwingWorker<Void, Integer> {
    private int value=0;
    private int sleepTime;
    private JLabel label;
    private JProgressBar progressBar;
    private JLabel grandTotal;
    private boolean flag=true;

    public AppThread (JLabel label, JProgressBar progressBar, int sleepTime, JLabel grandTotal) {
        this.sleepTime = sleepTime;
        this.label = label;
        this.progressBar = progressBar;
        this.grandTotal = grandTotal;
    }

    public AppThread (int value, JLabel label, JProgressBar progressBar, int sleepTime, JLabel grandTotal) {
        this.value = value;
        this.sleepTime = sleepTime;
        this.label = label;
        this.progressBar = progressBar;
        this.grandTotal = grandTotal;
    }

    public Void doInBackground() {
        for (int i = value; i <= 100; i++) {
            if (!flag)
            {
                value++;
                break;
            }
            this.value = i;
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            publish(value);
        }
        return null;
    }

    public void done() {

    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public int getValue() {
        return value;
    }

    @Override
    protected void process (List<Integer> chunks) {
        int value = chunks.get(chunks.size() - 1);

        label.setText(Integer.toString(value));
        progressBar.setValue(value);
        progressBar.setStringPainted(true);

        if (value < 100) {
            grandTotal.setText(Integer.toString(Integer.parseInt(grandTotal.getText()) + 1));
        }
    }
}