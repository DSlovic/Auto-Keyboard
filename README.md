# Auto-Keyboard
This program records and plays keyboard key strokes just as you type it.
You can simulate key strokes and delay between each stroke. 

# Download

Just download autoKeyboard.jar file and open it to use it.

# How to use

You have two main features of recording and playing. To record key types, press F7 and type away. To stop the recording, press F7 again. You can see the typing displayed on the edit bar after recording. The edit bar consists of key strokes and a delay time before each key stroke. They are separated by two break lines (||).

The second feature is to play the recording; press F6. To stop the automatic keyboard from playing the recording, press F6 again.

You can change the number of times the recording will play by changing the number in the "Loop" text field and pressing the "Edit Button/Loop" button. The default number of loops is three. You can also change the default play/record button the same way by replacing it with the valid key text (see the valid key text list) and pressing the "Edit Button/Loop".

To edit recorded text, replace the keys in the edit bar with a new key text that is valid (example: A||100|| --> B||100||). You can also change the delay time between the key strokes (example: A||100|| --> A||1000||). When you make the changes, just press the "Edit Time/Keys" button. Remember that the pattern in the edit bar must include a valid key text followed by two break lines, and then the delay time followed by two break lines. There must be a one time delay for each key stroke. To add new key strokes, press the "Add Time & Key" button, and the default pair of key and time will be added to the end of the edit bar, after which you can edit.

To clear the edit bar, double-tap the record key (default: F7).

To exit the auto-keyboard, press the End key. To disable the keyboard listener, press the Escape key.

In case anything goes wrong, reopen the program.

# Requierments

Java 1.8 or higher

# Valid key text list

All first letters and letters after spaces are written in capital.

Alphabet: A B C D E F... X Y Z

Numers: 1 2 3 4... 9 0

F numbers: F1 F2 F3... F11 F12

Controles (no quotes): "Tab" "Caps Lock" "Ctrl" "Shift" "Alt" "Space" "Home" "End" "Left" "Right" "Up" "Down" "Page Up" "Page Down" "Enter" "Backspace" "Escape"

Other (no quotes):"Minus"->(-) "Equals"->(=) "Comma"->(,) "Period"->(.) "Slash"->(/) "Semicolon"->(;) "Quote"->(') "Open Bracket"->([) "Close Bracket"->(]) "Back Quote"->(`)

Keys Shift, Ctrl and Alt on the left side chould be invalid as well as some other unusual keyboard key placements.

# External links
Made using jNativeHook native key listener
Link: https://github.com/kwhat/jnativehook

