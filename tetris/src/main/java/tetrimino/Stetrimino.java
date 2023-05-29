package tetrimino;

import setting.GAME;

public class Stetrimino extends Tetrimino{

    public Stetrimino() {
        color = GAME.STETRIMINO_COLOR;
        rotate_state = 0;
        blocks = new Block[4];
        blocks[0] = new Block(GAME.MIDDLE-GAME.SIZE,GAME.SIZE,color);
        blocks[1] = new Block(GAME.MIDDLE,GAME.SIZE,color);
        blocks[2] = new Block(GAME.MIDDLE,0,color);
        blocks[3] = new Block(GAME.MIDDLE+GAME.SIZE,0,color);
    }

    @Override
    public void rotate() {
        switch (rotate_state){
            case 0:
                if (blocks[1].getY() >= GAME.MAX_Y-GAME.SIZE)
                    break;

                blocks[0].setY(blocks[0].getY()-GAME.SIZE);
                blocks[0].setX(blocks[0].getX()+GAME.SIZE);

                blocks[2].setY(blocks[2].getY()+GAME.SIZE);
                blocks[2].setX(blocks[2].getX()+GAME.SIZE);

                blocks[3].setY(blocks[3].getY()+2*GAME.SIZE);

                rotate_state = 1;
                break;

            case 1:

                if(blocks[1].getX() == GAME.MIN_X)
                    break;

                blocks[0].setY(blocks[0].getY()+GAME.SIZE);
                blocks[0].setX(blocks[0].getX()-GAME.SIZE);

                blocks[2].setY(blocks[2].getY()-GAME.SIZE);
                blocks[2].setX(blocks[2].getX()-GAME.SIZE);

                blocks[3].setY(blocks[3].getY()-2*GAME.SIZE);

                rotate_state = 0;
                break;

        }
    }


}
