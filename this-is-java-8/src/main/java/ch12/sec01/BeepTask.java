package ch12.sec01;

import java.awt.*;

public class BeepTask implements Runnable {
    @Override
    public void run() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        for (int i = 0; i < 5; i++) {
            toolkit.beep();
            System.out.println("댕");
            try {
                Thread.sleep(500);
            } catch (Exception ex) {
                // no op
            }
        }
    }
}
