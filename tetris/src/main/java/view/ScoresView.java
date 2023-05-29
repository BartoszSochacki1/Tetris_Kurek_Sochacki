package view;

import builder.TableBuilder;
import helper.gui.BBasicScrollBarUI;
import helper.gui.BButton;
import helper.file.JSONOperations;
import setting.GLOBAL;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import static setting.GLOBAL.MAIN_MENU_BG;


public class ScoresView extends View implements ActionListener, MouseListener {

    public ScoresView(JFrame window, JPanel previousMenu) {
        super(window, previousMenu);

        setLayout(new BoxLayout(this,BoxLayout.LINE_AXIS));

        firstPanel.setPreferredSize((new Dimension(250,900)));
        firstPanel.setMaximumSize(new Dimension(250,900));
        firstPanel.setOpaque(false);

        secondPanel.setPreferredSize((new Dimension(400,900)));
        secondPanel.setMaximumSize(new Dimension(400,900));
        secondPanel.setLayout(new BoxLayout(secondPanel,BoxLayout.LINE_AXIS));
        secondPanel.setOpaque(false);

        thirdPanel.setPreferredSize((new Dimension(250,900)));
        thirdPanel.setMaximumSize(new Dimension(250,900));
        thirdPanel.setOpaque(false);


        JPanel table = new JPanel();
        table.setLayout(new BoxLayout(table, BoxLayout.PAGE_AXIS));
        table.setOpaque(false);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize((new Dimension(400,800)));
        scrollPane.setMaximumSize(new Dimension(400,800));
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.GREEN,0));

        JScrollBar jScrollBar = new JScrollBar();
        jScrollBar.setBackground(GLOBAL.SECONDARY_COLOR);
        jScrollBar.setUI(new BBasicScrollBarUI());

        scrollPane.setVerticalScrollBar(jScrollBar);
        scrollPane.getVerticalScrollBar().setUnitIncrement(20);
        secondPanel.add(scrollPane);


        BButton back = new BButton("POWRÓT");
        back.setButton(new Dimension(200,100));
        back.setForeground(GLOBAL.MAIN_COLOR);
        back.addMouseListener(this);
        firstPanel.add(back);

        back.addActionListener(this);

        JSONOperations jsonOperations = new JSONOperations(GLOBAL.SCORE_PATH);
        ArrayList<TableBuilder> tableBuilders = jsonOperations.readFile();
        for (int i = 0; i < tableBuilders.size(); i++) {
            drawLine(table, i+1, tableBuilders.get(i));
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.setVisible(false);
        previousView.setVisible(true);
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

    private void drawLine(JPanel panel, int i, TableBuilder tableBuilder){
        JPanel row = new JPanel();
        row.setPreferredSize((new Dimension(360,60)));
        row.setMaximumSize(new Dimension(360,60));
        row.setLayout(new BoxLayout(row,BoxLayout.LINE_AXIS));
        row.setBackground(GLOBAL.SECONDARY_COLOR);
        row.addMouseListener(this);
        panel.add(row);

        JPanel number_col = new JPanel();
        number_col.setPreferredSize((new Dimension(80,40)));
        number_col.setMaximumSize(new Dimension(80,40));
        number_col.setBackground(GLOBAL.SECONDARY_COLOR);

        number_col.setOpaque(false);
        row.add(number_col);

        JLabel first_value = new JLabel(i+".");
        first_value.setFont(GLOBAL.SECONDARY_FONT);
        first_value.setForeground(GLOBAL.MAIN_COLOR);
        number_col.add(first_value);

        //

        JPanel score_col = new JPanel();
        score_col.setPreferredSize((new Dimension(170,40)));
        score_col.setMaximumSize(new Dimension(170,40));
        score_col.setOpaque(false);
        row.add(score_col);

        JLabel score_value = new JLabel(tableBuilder.getScore()+"");
        score_value.setFont(GLOBAL.SECONDARY_FONT);
        score_value.setForeground(GLOBAL.MAIN_COLOR);

        score_col.add(score_value);

        //

        JPanel player_col = new JPanel();
        player_col.setPreferredSize((new Dimension(200,40)));
        player_col.setMaximumSize(new Dimension(200,40));
        player_col.setOpaque(false);
        row.add(player_col);

        JLabel player_value = new JLabel(tableBuilder.getName()+"");
        player_value.setFont(GLOBAL.SECONDARY_FONT);
        player_value.setForeground(GLOBAL.MAIN_COLOR);

        player_col.add(player_value);

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
        Component component =(Component)e.getSource();
        component.setBackground(GLOBAL.SECONDARY_COLOR_BRIGHTER);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        Component component =(Component)e.getSource();
        component.setBackground(GLOBAL.SECONDARY_COLOR);
    }

}
