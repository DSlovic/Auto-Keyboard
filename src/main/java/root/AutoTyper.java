package root;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

public class AutoTyper implements NativeKeyListener{

    private static JTextField txtSpam;
    private static JTextField txtInterval;

    private static JTextField txtRecordBtn;

    private static JTextField txtPlayBtn;
    public static JButton newBtn;

    public static JButton editButtonsBtn;

    public static JButton addBtn;

    static ArrayList<String> recordedKeys = new ArrayList<String>();

    static ArrayList<String> typingDelay = new ArrayList<String>();

    private boolean record = false;

    public static boolean playing = false;

    public static boolean stopPlay = false;
    private boolean typing = false;

    private long startTime = 0;

    public static int loopTimes = 3;
    //key to toggle recording
    private static String recordAction = "F7";
    //key to toggle playing
    private static String playAction = "F6";

    //key press method for every key press
    public void nativeKeyPressed(NativeKeyEvent e) {
        System.out.println("Key Pressed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));

        if (e.getKeyCode() == NativeKeyEvent.VC_ESCAPE) {
            try {
                GlobalScreen.unregisterNativeHook();
            } catch (NativeHookException nativeHookException) {
                nativeHookException.printStackTrace();
            }
        }
        if(NativeKeyEvent.getKeyText(e.getKeyCode()).equals("End")){
            System.exit(0);
        }
        //adds key and delay time when recording after each key type
        if(record && !NativeKeyEvent.getKeyText(e.getKeyCode()).equals(recordAction)){
            recordedKeys.add(NativeKeyEvent.getKeyText(e.getKeyCode()));
            if(typing){
                typingDelay.add("" + timerStop());
            }
            timerStart();
            typing=true;
        }
        //starts new recordings and erases previous recordings if not recording or laying
        if((NativeKeyEvent.getKeyText(e.getKeyCode()).equals(recordAction)) && (!record) && (!playing)){
            record=true;
            System.out.println("Recording");
            recordedKeys.clear();
            typingDelay.clear();
            txtSpam.setText("");
            newBtn.setEnabled(false);
            editButtonsBtn.setEnabled(false);
            addBtn.setEnabled(false);
        }//or stops ongoing recording
        else if(NativeKeyEvent.getKeyText(e.getKeyCode()).equals(recordAction) && record){
            String temp= "";
            record=false;
            typing=false;
            typingDelay.add("" + timerStop());
            System.out.println("Stopping Recording");
            newBtn.setEnabled(true);
            editButtonsBtn.setEnabled(true);
            addBtn.setEnabled(true);
            //assembles key/delay pattern on edit bar
            int row = 0;
            for(String x: recordedKeys){
                temp = temp + x + "||" + typingDelay.get(row) + "||";
                row++;
            }
            txtSpam.setText(temp);
        }
        //sets boolean for play stopping in pauseThread class
        if((NativeKeyEvent.getKeyText(e.getKeyCode()).equals(playAction)) && (!record) && (playing)){
            stopPlay = true;
            newBtn.setEnabled(true);
            editButtonsBtn.setEnabled(true);
            addBtn.setEnabled(true);
        }
        //starts playing recorded keys delay is done via separate thread so user can stop playing recorded keys
        if((NativeKeyEvent.getKeyText(e.getKeyCode()).equals(playAction)) && (!record) && (!playing)){
            playing = true;
            System.out.println("Playing");
            newBtn.setEnabled(false);
            editButtonsBtn.setEnabled(false);
            addBtn.setEnabled(false);
            pauseThread threads = new pauseThread();
            threads.start();
        }

    }
    //other jNative methods, these are not used
    public void nativeKeyReleased(NativeKeyEvent e) {
        try {
            Thread.sleep(10);
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
        System.out.println("Key Released: " + e.getKeyCode());
    }

    public void nativeKeyTyped(NativeKeyEvent e) {
        System.out.println("Key Typed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
    }
    //saves current system time in milliseconds when key is pressed during recording
    public void timerStart(){
        startTime = System.currentTimeMillis();
    }
    //saves time of the next key type and calculates typing delay between key types
    public long timerStop(){
        long stopTime = System.currentTimeMillis();
        return stopTime - startTime;
    }
//Following method does not execute properly due to the sleep thread being executed before set text can be completed
    public static void editBarError(String tmp, String errorText){
        txtSpam.setText(errorText);
        //So I added useless loop
        for(int i = 0; i<1000; i++){
            i++;
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        txtSpam.setText(tmp);
    }


    public static ArrayList<String> getRecordedKeys() {
        return recordedKeys;
    }

    public static ArrayList<String> getTypingDelay() {
        return typingDelay;
    }



    public static void main(String[]args){
        //applies key/time edits from the edit bar
        ActionListener editKeys = new ActionListener(){
            public void actionPerformed(ActionEvent evt){
                String temp = txtSpam.getText();
                String line = "";
                ArrayList<String> recordedKeysTemp = recordedKeys;
                ArrayList<String> typingDelayTemp = typingDelay;
                int lineCount = 0;
                int countingLines = 0;
                boolean toKeys = true;
                //checks if the edit bar is empty
                if(txtSpam.getText().equals("")){
                    editBarError(temp, "The edit bar is empty");
                }else{
                    //counting and checking if there is a proper number of break lines
                    lineCount = (recordedKeys.size() + typingDelay.size()) * 2;
                    for(char x:  txtSpam.getText().toCharArray()){
                        if (x == '|') ++countingLines;
                    }
                    if(lineCount != countingLines){
                        editBarError(temp, "Must contain two lines after key or time");
                    }else{
                        //reads edit bar and uses break lines as a break/start point of delay time and keys
                        countingLines = 0;
                        recordedKeys.clear();
                        typingDelay.clear();
                        for(char x:  txtSpam.getText().toCharArray()){
                            if (x == '|') {
                                ++countingLines;
                                if(countingLines==1 && toKeys){
                                    recordedKeys.add(line);
                                    line="";
                                    //for every first line a char is added
                                }else if (countingLines==1){
                                    typingDelay.add(line);
                                    line="";
                                }
                            }
                            //for every second line delay time and key arrays are switched
                            else{
                                if(countingLines == 2 && toKeys){
                                    toKeys=false;
                                    countingLines=0;
                                }else if(countingLines == 2){
                                    toKeys=true;
                                    countingLines=0;
                                }
                                line = line + x;
                            }
                        }
                    }
                }
            }
        };
        //applies edits for a number of loops, keys for play and recording when changed by user
        ActionListener editActionKeys = new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                //checks if user has inputted valid integer
                try{
                    if(Integer.parseInt(txtInterval.getText())>0)
                        loopTimes = Integer.parseInt(txtInterval.getText());
                    else txtInterval.setText("Error");
                }catch (NumberFormatException e1){
                    txtInterval.setText("Error");
                }//checks if a valid key is imputed
                if(StringToNativeKey.strToKey(txtRecordBtn.getText()) != -1)
                    recordAction = txtRecordBtn.getText();
                else {
                    System.out.println("Invalid key");
                    txtRecordBtn.setText("Error");
                }
                if(StringToNativeKey.strToKey(txtPlayBtn.getText()) != -1)
                    playAction = txtPlayBtn.getText();
                else {
                    System.out.println("Invalid key");
                    txtRecordBtn.setText("Error");
                }

            }
        };
        //adds a pair of default delay time and key for user to edit so user can write a pattern without recording
        ActionListener addTimeAndKey = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txtSpam.setText(txtSpam.getText() + "A||100||");
                recordedKeys.add("A");
                typingDelay.add("100");
            }
        };
        //jNative initiation
        try {
            GlobalScreen.registerNativeHook();
        }
        catch (NativeHookException ex) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());

            System.exit(1);
        }

        GlobalScreen.addNativeKeyListener(new AutoTyper() {

        });
        //the rest is interface stuff
        newBtn = new JButton("Edit Time/Keys");
        editButtonsBtn = new JButton("Edit Buttons/Loop");
        addBtn = new JButton("Add Time & Key");
        newBtn.setBackground(Color.BLACK);
        newBtn.setForeground(Color.GREEN);
        editButtonsBtn.setBackground(Color.BLACK);
        editButtonsBtn.setForeground(Color.GREEN);
        addBtn.setBackground(Color.BLACK);
        addBtn.setForeground(Color.GREEN);
        JLabel lbl1 = new JLabel("Keys/Time:");
        JLabel lbl2 = new JLabel("Loop:");
        JLabel lbl3 = new JLabel("Record Key:");
        JLabel lbl4 = new JLabel("Play Key:");
        txtSpam = new JTextField("", 30);
        txtSpam.setBackground(Color.GRAY);
        txtSpam.setForeground(Color.GREEN);
        txtInterval = new JTextField("3",3);
        txtInterval.setBackground(Color.BLACK);
        txtInterval.setForeground(Color.GREEN);
        txtRecordBtn = new JTextField("F7", 3);
        txtRecordBtn.setBackground(Color.BLACK);
        txtRecordBtn.setForeground(Color.GREEN);
        txtPlayBtn = new JTextField("F6", 3);
        txtPlayBtn.setBackground(Color.BLACK);
        txtPlayBtn.setForeground(Color.GREEN);

        newBtn.addActionListener(editKeys);
        editButtonsBtn.addActionListener(editActionKeys);
        addBtn.addActionListener(addTimeAndKey);

        JPanel intervalpane = new JPanel();
        intervalpane.add(lbl2,BorderLayout.EAST);
        intervalpane.add(txtInterval,BorderLayout.WEST);

        JPanel recordPane = new JPanel();
        recordPane.add(lbl3,BorderLayout.EAST);
        recordPane.add(txtRecordBtn,BorderLayout.WEST);

        JPanel playPane = new JPanel();
        playPane.add(lbl4,BorderLayout.EAST);
        playPane.add(txtPlayBtn,BorderLayout.WEST);

        JPanel bottompane = new JPanel();
        bottompane.add(intervalpane,BorderLayout.WEST);
        bottompane.add(recordPane,BorderLayout.CENTER);
        bottompane.add(playPane,BorderLayout.EAST);

        JPanel toppane = new JPanel();
        toppane.add(lbl1,BorderLayout.EAST);
        toppane.add(txtSpam,BorderLayout.NORTH);

        JPanel newpane = new JPanel();
        newpane.add(newBtn, BorderLayout.WEST);
        newpane.add(editButtonsBtn, BorderLayout.CENTER);
        newpane.add(addBtn, BorderLayout.EAST);

        JPanel pane = new JPanel(new BorderLayout());
        pane.add(toppane,BorderLayout.NORTH);
        pane.add(bottompane,BorderLayout.CENTER);
        pane.add(newpane, BorderLayout.SOUTH);

        JFrame frame = new JFrame("Auto Keyboard");
        //frame.setIconImage(
         //       new ImageIcon(Objects.requireNonNull(AutoTyper.class.getClassLoader().getResource("Steve-Zondicons-Keyboard.32.ico"))).getImage()
        //);
        frame.setForeground(Color.BLACK);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(pane);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
    }

}
