package root;

import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;

import java.awt.*;
import java.awt.event.KeyEvent;
//takes a string coverts it to a number of a key event and returns it
public class StringToNativeKey {
    public static int strToKey(String str){
        switch(str) {
            case "A":
                return KeyEvent.VK_A;
            case "B":
                return KeyEvent.VK_B;
            case "C":
                return KeyEvent.VK_C;
            case "D":
                return KeyEvent.VK_D;
            case "E":
                return KeyEvent.VK_E;
            case "F":
                return KeyEvent.VK_F;
            case "G":
                return KeyEvent.VK_G;
            case "H":
                return KeyEvent.VK_H;
            case "I":
                return KeyEvent.VK_I;
            case "J":
                return KeyEvent.VK_J;
            case "K":
                return KeyEvent.VK_K;
            case "L":
                return KeyEvent.VK_L;
            case "M":
                return KeyEvent.VK_M;
            case "N":
                return KeyEvent.VK_N;
            case "O":
                return KeyEvent.VK_O;
            case "P":
                return KeyEvent.VK_P;
            case "Q":
                return KeyEvent.VK_Q;
            case "R":
                return KeyEvent.VK_R;
            case "S":
                return KeyEvent.VK_S;
            case "T":
                return KeyEvent.VK_T;
            case "U":
                return KeyEvent.VK_U;
            case "V":
                return KeyEvent.VK_V;
            case "W":
                return KeyEvent.VK_W;
            case "X":
                return KeyEvent.VK_X;
            case "Y":
                return KeyEvent.VK_Y;
            case "Z":
                return KeyEvent.VK_Z;
            case "Back Quote":
                return KeyEvent.VK_BACK_QUOTE;
            case "Tab":
                return KeyEvent.VK_TAB;
            case "Caps Lock":
                return KeyEvent.VK_CAPS_LOCK;
            case "Shift":
                return KeyEvent.VK_SHIFT;
            case "Ctrl":
                return KeyEvent.VK_CONTROL;
            case "Alt":
                return KeyEvent.VK_ALT;
            case "Back Slash":
                return KeyEvent.VK_BACK_SLASH;
            case "Space":
                return KeyEvent.VK_SPACE;
            case "Close Bracket":
                return KeyEvent.VK_CLOSE_BRACKET;
            case "Open Bracket":
                return KeyEvent.VK_OPEN_BRACKET;
            case "Quote":
                return KeyEvent.VK_QUOTE;
            case "Semicolon":
                return KeyEvent.VK_SEMICOLON;
            case "Slash":
                return KeyEvent.VK_SLASH;
            case "Period":
                return KeyEvent.VK_PERIOD;
            case "Comma":
                return KeyEvent.VK_COMMA;
            case "Equals":
                return KeyEvent.VK_EQUALS;
            case "Minus":
                return KeyEvent.VK_MINUS;
            case "Left":
                return KeyEvent.VK_LEFT;
            case "Up":
                return KeyEvent.VK_UP;
            case "Right":
                return KeyEvent.VK_RIGHT;
            case "Down":
                return KeyEvent.VK_DOWN;
            case "End":
                return KeyEvent.VK_END;
            case "Page Down":
                return KeyEvent.VK_PAGE_DOWN;
            case "Page Up":
                return KeyEvent.VK_PAGE_UP;
            case "Home":
                return KeyEvent.VK_HOME;
            case "Enter":
                return KeyEvent.VK_ENTER;
            case "Escape":
                return KeyEvent.VK_ESCAPE;
            case "1":
                return KeyEvent.VK_1;
            case "2":
                return KeyEvent.VK_2;
            case "3":
                return KeyEvent.VK_3;
            case "4":
                return KeyEvent.VK_4;
            case "5":
                return KeyEvent.VK_5;
            case "6":
                return KeyEvent.VK_6;
            case "7":
                return KeyEvent.VK_7;
            case "8":
                return KeyEvent.VK_8;
            case "9":
                return KeyEvent.VK_9;
            case "F1":
                return KeyEvent.VK_F1;
            case "F2":
                return KeyEvent.VK_F2;
            case "F3":
                return KeyEvent.VK_F3;
            case "F4":
                return KeyEvent.VK_F4;
            case "F5":
                return KeyEvent.VK_F5;
            case "F6":
                return KeyEvent.VK_F6;
            case "F7":
                return KeyEvent.VK_F7;
            case "F8":
                return KeyEvent.VK_F8;
            case "F9":
                return KeyEvent.VK_F9;
            case "F10":
                return KeyEvent.VK_0;
            case "F11":
                return KeyEvent.VK_F11;
            case "F12":
                return KeyEvent.VK_F12;
            case "Backspace":
                return KeyEvent.VK_BACK_SPACE;
            default: return  -1;

        }
    }
}
