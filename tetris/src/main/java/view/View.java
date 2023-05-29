package view;

import setting.GLOBAL;

import javax.swing.*;

public abstract class View extends JPanel {

    protected JFrame window;
    protected JPanel previousView;

    protected JPanel firstPanel, secondPanel, thirdPanel;

    public View(JFrame window, JPanel previousMenu) {
        this.window = window;
        this.previousView = previousMenu;

        setPreferredSize(GLOBAL.WINDOW_SIZE);
        setMaximumSize(GLOBAL.WINDOW_SIZE);


        firstPanel = new JPanel();
        add(firstPanel);

        secondPanel = new JPanel();
        add(secondPanel);

        thirdPanel = new JPanel();
        add(thirdPanel);
    }
}
