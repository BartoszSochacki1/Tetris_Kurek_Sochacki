package helper.gui;

import setting.GLOBAL;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class BButton extends JButton implements MouseListener {
    public BButton(String text) {
        super(text);

        setBackground(GLOBAL.SECONDARY_COLOR);
        setForeground(GLOBAL.MAIN_COLOR);
        setFont(GLOBAL.PRIMARY_FONT);
        setBorder(BorderFactory.createLineBorder(null,0));
        setFocusable(false);
        this.addMouseListener(this);
    }

    public void setButton(Dimension size){
        setPreferredSize(size);
        setMaximumSize(size);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        JButton jb =(JButton)e.getSource();
        jb.setBackground(GLOBAL.SECONDARY_COLOR_BRIGHTER);

    }

    @Override
    public void mouseExited(MouseEvent e) {
        JButton jb =(JButton)e.getSource();
        jb.setBackground(GLOBAL.SECONDARY_COLOR);
    }


}
