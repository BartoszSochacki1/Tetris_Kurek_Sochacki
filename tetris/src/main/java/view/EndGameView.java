package view;

import builder.TableBuilder;
import helper.gui.BButton;
import helper.file.JSONOperations;
import setting.GLOBAL;

import javax.imageio.ImageIO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import static setting.GLOBAL.MAIN_MENU_BG;

public class EndGameView extends View implements ActionListener {

    private final int score;
    private final JTextField playerName;

    public EndGameView(JFrame window, JPanel previousMenu, int score) {
        super(window, previousMenu);

        this.score = score;

        JSONOperations jsonOperations = new JSONOperations(GLOBAL.SCORE_PATH);

        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

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


        JPanel tableContainer = new JPanel();
        tableContainer.setPreferredSize((new Dimension(360,80)));
        tableContainer.setMaximumSize(new Dimension(360,80));
        tableContainer.setLayout(new BoxLayout(tableContainer,BoxLayout.LINE_AXIS));
        secondPanel.add(tableContainer);

        tableContainer.setBackground(GLOBAL.SECONDARY_COLOR);

        /*
          kolumna number
         */

        JPanel numberCol = new JPanel();
        numberCol.setPreferredSize((new Dimension(80,40)));
        numberCol.setMaximumSize(new Dimension(80,40));
        tableContainer.add(numberCol);

        JLabel firstValue = new JLabel();
        firstValue.setFont(GLOBAL.PRIMARY_FONT);
        firstValue.setForeground(GLOBAL.MAIN_COLOR);
        numberCol.add(firstValue);

        ArrayList<TableBuilder> tableBuilders = jsonOperations.readFile();

        firstValue.setText(tableBuilders.size()+1+".");

        for (int i = 0; i < tableBuilders.size(); i++) {
            if(tableBuilders.get(i).getScore() < this.score){
                firstValue.setText(i+1+".");
                break;
            }
        }


        /*
          kolumna score
         */

        JPanel scoreCol = new JPanel();
        scoreCol.setPreferredSize((new Dimension(170,40)));
        scoreCol.setMaximumSize(new Dimension(170,40));
        tableContainer.add(scoreCol);

        JLabel scoreValue = new JLabel(this.score+"");
        scoreValue.setFont(GLOBAL.PRIMARY_FONT);
        scoreValue.setForeground(GLOBAL.MAIN_COLOR);
        scoreCol.add(scoreValue);

        /*
          kolumna player
         */

        JPanel playerCol = new JPanel();
        playerCol.setPreferredSize((new Dimension(200,40)));
        playerCol.setMaximumSize(new Dimension(200,40));
        tableContainer.add(playerCol);

        playerName = new JTextField("-nick-");
        playerName.setFont(GLOBAL.PRIMARY_FONT);
        playerName.setForeground(GLOBAL.MAIN_COLOR);
        playerName.setPreferredSize((new Dimension(100,30)));
        playerName.setMaximumSize(new Dimension(100,30));
        playerName.setHorizontalAlignment(SwingConstants.CENTER);
        playerName.setBackground(GLOBAL.SECONDARY_COLOR_BRIGHTER);
        playerName.setBorder(BorderFactory.createLineBorder(null,0));
        playerCol.add(playerName);

        /*

          kolumna next
         */

        JPanel nextCol = new JPanel();
        nextCol.setPreferredSize((new Dimension(80,80)));
        nextCol.setMaximumSize(new Dimension(80,80));
        nextCol.setLayout(new BoxLayout(nextCol,BoxLayout.LINE_AXIS));
        tableContainer.add(nextCol);

        BButton next = new BButton("DALEJ");
        next.setPreferredSize((new Dimension(80,80)));
        next.setMaximumSize(new Dimension(80,80));
        nextCol.add(next);

        numberCol.setOpaque(false);
        scoreCol.setOpaque(false);
        playerCol.setOpaque(false);
        nextCol.setOpaque(false);


        next.addActionListener(this);
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
        String name = playerName.getText();

        if(name.equals(""))
            name = "NN";
        JSONOperations jsonOperations = new JSONOperations(GLOBAL.SCORE_PATH);
        jsonOperations.saveToFile(new TableBuilder(score, name));

        this.setVisible(false);
        window.add(new MenuView(window));

    }
}
