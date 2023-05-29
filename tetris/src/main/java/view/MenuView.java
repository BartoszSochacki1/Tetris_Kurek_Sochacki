package view;

import helper.gui.BButton;
import setting.GLOBAL;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static setting.GLOBAL.MAIN_MENU_BG;

public class MenuView extends View implements ActionListener {

    private final BButton game;
    private final BButton scores;

    public MenuView(JFrame window) {
        super(window,null);

        setLayout(new BoxLayout(this,BoxLayout.LINE_AXIS));

        firstPanel.setPreferredSize((new Dimension(270,900)));
        firstPanel.setMaximumSize(new Dimension(270,900));
        firstPanel.setOpaque(false);

        secondPanel.setPreferredSize((new Dimension(360,900)));
        secondPanel.setMaximumSize(new Dimension(360,900));
        secondPanel.setLayout(new BoxLayout(secondPanel,BoxLayout.LINE_AXIS));
        secondPanel.setOpaque(false);

        thirdPanel.setPreferredSize((new Dimension(270,900)));
        thirdPanel.setMaximumSize(new Dimension(270,900));
        thirdPanel.setOpaque(false);

        JPanel menu = new JPanel();
        menu.setPreferredSize((new Dimension(360,400)));
        menu.setMaximumSize(new Dimension(360,400));
        menu.setLayout(new BoxLayout(menu,BoxLayout.PAGE_AXIS));
        menu.setOpaque(false);
        secondPanel.add(menu);


        game = new BButton("START");
        game.setButton(GLOBAL.BUTTON_SIZE);
        scores = new BButton("WYNIKI");
        scores.setButton(GLOBAL.BUTTON_SIZE);
        BButton exit = new BButton("WYJŚCIE");
        exit.setButton(GLOBAL.BUTTON_SIZE);


        menu.add(Box.createVerticalStrut(15));
        menu.add(game);
        menu.add(Box.createVerticalStrut(30));
        menu.add(scores);
        menu.add(Box.createVerticalStrut(30));
        menu.add(exit);

        game.addActionListener(this);
        scores.addActionListener(this);
        exit.addActionListener(this);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        BufferedImage background = null;
        try {
            background =  ImageIO.read(getClass().getResource(MAIN_MENU_BG));
        } catch (IOException e) {
            e.printStackTrace();
        }
       g.drawImage(background,0,0,900,900,this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton)e.getSource();

        if(button == game){

            setVisible(false);
            GameView newGame = new GameView(window,this);
            window.add(newGame);

        }else if(button == scores){

            setVisible(false);
            ScoresView newScores = new ScoresView(window,this);
            window.add(newScores);

        }else{
            window.dispose();
        }

    }

}
