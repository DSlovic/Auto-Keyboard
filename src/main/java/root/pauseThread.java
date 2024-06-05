package root;

import java.awt.*;

import static root.AutoTyper.*;
//this thread is separated from the main thread, so it may be stopped while keeping jNative listener active
public class pauseThread extends Thread{
    public void run(){
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException ex) {
            throw new RuntimeException(ex);
        }
        //outer loop for users desired repetition
        outerLoop:
        for(int i = 0; i<loopTimes; i++) {
            int row = 0;
            //inner loop to type each key
            for (String x : AutoTyper.getRecordedKeys()) {
                System.out.println(stopPlay);
                if (stopPlay) {
                    AutoTyper.stopPlay = false;
                    break outerLoop;
                }
                robot.keyPress(StringToNativeKey.strToKey(x));
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                robot.keyRelease(StringToNativeKey.strToKey(x));
                //stops this thread for the duration of user defined amount of time
                try {
                    Thread.sleep(Long.parseLong(getTypingDelay().get(row)));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                row++;
            }
        }
        AutoTyper.newBtn.setEnabled(true);
        AutoTyper.editButtonsBtn.setEnabled(true);
        AutoTyper.addBtn.setEnabled(true);
        AutoTyper.playing= false;
    }
}
