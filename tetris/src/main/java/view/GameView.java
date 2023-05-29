package view;

import game.Score;
import game.Tetris;
import helper.gui.BButton;
import setting.GAME;
import setting.GLOBAL;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameView extends View implements ActionListener {

    public GameView(JFrame window, JPanel previousMenu)  {
        super(window, previousMenu);

        Score score = new Score();

        setLayout(new BoxLayout(this,BoxLayout.LINE_AXIS));
        setBackground(GLOBAL.SECONDARY_COLOR);

        firstPanel.setPreferredSize(new Dimension(225,900));
        firstPanel.setMaximumSize(new Dimension(225,900));
        firstPanel.setLayout(new BoxLayout(firstPanel,BoxLayout.PAGE_AXIS));
        firstPanel.setOpaque(false);

        secondPanel.setPreferredSize(new Dimension(450,900));
        secondPanel.setMaximumSize(new Dimension(450,900));
        secondPanel.setLayout(new BoxLayout(secondPanel,BoxLayout.LINE_AXIS));
        secondPanel.setOpaque(false);

        thirdPanel.setPreferredSize(new Dimension(225,900));
        thirdPanel.setMaximumSize(new Dimension(225,900));
        thirdPanel.setLayout(new BoxLayout(thirdPanel,BoxLayout.PAGE_AXIS));
        thirdPanel.setOpaque(false);

        secondPanel.setBackground(Color.green);

        /*

        lewy panel

         */

        JPanel leftUpper = new JPanel();
        leftUpper.setPreferredSize(new Dimension(200,100));
        leftUpper.setMaximumSize(new Dimension(200,100));
        leftUpper.setLayout(new BoxLayout(leftUpper,BoxLayout.LINE_AXIS));
        firstPanel.add(leftUpper);

        JPanel leftLower = new JPanel();
        leftLower.setPreferredSize(new Dimension(225,800));
        leftLower.setMaximumSize(new Dimension(225,800));
        leftLower.setLayout(new BoxLayout(leftLower,BoxLayout.LINE_AXIS));
        leftLower.setOpaque(false);
        firstPanel.add(leftLower);



        JPanel scoreContainer = new JPanel();
        scoreContainer.setPreferredSize(new Dimension(225,200));
        scoreContainer.setMaximumSize(new Dimension(225,200));
        scoreContainer.setOpaque(false);
        leftLower.add(scoreContainer);

        scoreContainer.add(score.build());

        BButton back = new BButton("POWRÓT");
        back.setButton(new Dimension(200,100));
        back.addActionListener(this);
        leftUpper.add(back);


         /*

        prawy panel

         */

        JPanel rightCenter = new JPanel();
        rightCenter.setPreferredSize(new Dimension(200,900));
        rightCenter.setMaximumSize(new Dimension(200,900));
        rightCenter.setLayout(new BoxLayout(rightCenter,BoxLayout.LINE_AXIS));
        rightCenter.setOpaque(false);
        thirdPanel.add(rightCenter);

        JPanel nextTetrimino = new JPanel();
        nextTetrimino.setPreferredSize(new Dimension(200,250));
        nextTetrimino.setMaximumSize(new Dimension(200,250));
        nextTetrimino.setLayout(new BoxLayout(nextTetrimino,BoxLayout.PAGE_AXIS));
        rightCenter.add(nextTetrimino);

        /*
        środkowy panel

         */

        JPanel tetris = new Tetris(window,this, score,nextTetrimino);
        tetris.setPreferredSize(new Dimension(GAME.COLS*GAME.SIZE,GLOBAL.BASIC_SIZE));
        tetris.setMaximumSize(new Dimension(GAME.COLS* GAME.SIZE,GLOBAL.BASIC_SIZE));
        secondPanel.add(tetris);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setVisible(false);
        previousView.setVisible(true);
    }



}
