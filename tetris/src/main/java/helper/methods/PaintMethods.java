package helper.methods;

import setting.GAME;
import setting.GLOBAL;
import tetrimino.Block;
import tetrimino.Tetrimino;

import java.awt.*;

public class PaintMethods {

    public void paintRect(Graphics2D g2d, int x, int y, Color color){

        g2d.setColor(GLOBAL.SECONDARY_COLOR);
        g2d.setStroke(new BasicStroke(GLOBAL.BORDER));
        g2d.drawRect(x,y,GAME.SIZE,GAME.SIZE);

        g2d.setColor(color);
        g2d.fillRect(x+(GLOBAL.BORDER/2),y+(GLOBAL.BORDER/2),
                GAME.SIZE- GLOBAL.BORDER,GAME.SIZE- GLOBAL.BORDER);
    }

    public void paintBackground(Graphics2D g2d){
        for(int i =0;i<GAME.COLS;i++){
            for(int j =0;j<GAME.ROWS;j++){
                paintRect(g2d, i*GAME.SIZE,j*GAME.SIZE, GLOBAL.MAIN_COLOR);
            }
        }
    }

    public void paintTetrimino(Graphics2D g2d, Tetrimino tetrimino){
        for (Block block:tetrimino.getBlocks()) {
            paintRect(g2d, block.getX(),block.getY(),block.getColor());
        }
    }

    public void paintBlocks(Graphics2D g2d, Block[][] blocks){
        for (Block[] b: blocks) {
            for(Block block:b){
                if(block!=null)
                    paintRect(g2d,block.getX(),block.getY(),block.getColor());
            }
        }

    }
}
