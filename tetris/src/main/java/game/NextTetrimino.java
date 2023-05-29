package game;

import helper.methods.PaintMethods;
import setting.GLOBAL;
import tetrimino.Block;
import tetrimino.Tetrimino;

import javax.swing.*;
import java.awt.*;

public class NextTetrimino extends JPanel {

    private Tetrimino tetrimino;
    private final PaintMethods paintMethods;
    public NextTetrimino(Tetrimino tetrimino) {
        this.tetrimino = tetrimino;
        this.paintMethods = new PaintMethods();
        setBackground(GLOBAL.SECONDARY_COLOR);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g.create();
        g2d.setColor(GLOBAL.MAIN_COLOR);
        g2d.setFont(GLOBAL.SECONDARY_FONT);
        g2d.drawString("NASTĘPNY: ",40,20);

        for (Block b :tetrimino.getBlocks() ) {
            paintMethods.paintRect(g2d,b.getX()-150,b.getY()+40,b.getColor());
        }

        g2d.dispose();
    }

    public void setTetrimino(Tetrimino tetrimino) {
        this.tetrimino = tetrimino;
        repaint();
    }

    public Tetrimino getTetrimino() {
        return tetrimino;
    }
}
