import view.MenuView;

import javax.swing.*;

public class Window extends JFrame {
    public Window() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setTitle("TETRIS by Joanna Kurek and Bartosz Sochacki");

        add(new MenuView(this));
        pack();
    }
}
