package tetrimino;


import setting.GAME;

public class Otetrimino extends Tetrimino{

    public Otetrimino() {
        color = GAME.OTETRIMINO_COLOR;
        rotate_state = 0;
        blocks = new Block[4];
        blocks[0] = new Block(GAME.MIDDLE-GAME.SIZE,0,color);
        blocks[1] = new Block(GAME.MIDDLE-GAME.SIZE,GAME.SIZE,color);
        blocks[2] = new Block(GAME.MIDDLE,0,color);
        blocks[3] = new Block(GAME.MIDDLE,GAME.SIZE,color);
    }

    @Override
    public void rotate() {

    }
}
