package tetrimino;

import setting.GAME;

public class Itetrimino extends Tetrimino{

    public Itetrimino() {
        color = GAME.ITETRIMINO_COLOR;
        rotate_state = 0;
        blocks = new Block[4];
        blocks[0] = new Block(GAME.MIDDLE,0,color);
        blocks[1] = new Block(GAME.MIDDLE,GAME.SIZE,color);
        blocks[2] = new Block(GAME.MIDDLE,2*GAME.SIZE,color);
        blocks[3] = new Block(GAME.MIDDLE,3*GAME.SIZE,color);
    }

    @Override
    public void rotate() {
        switch (rotate_state){
            case 0:

                if(blocks[0].getX() == GAME.MIN_X ||
                        blocks[0].getX() > GAME.MAX_X - 3*GAME.SIZE)
                    break;



                blocks[0].setY(blocks[1].getY());
                blocks[0].setX(blocks[1].getX()-GAME.SIZE);

                blocks[2].setY(blocks[1].getY());
                blocks[2].setX(blocks[1].getX()+GAME.SIZE);

                blocks[3].setY(blocks[1].getY());
                blocks[3].setX(blocks[1].getX()+2*GAME.SIZE);

                this.rotate_state = 1;
                break;

            case 1:
                if (blocks[0].getY() > GAME.MAX_Y-GAME.SIZE*3)//leza poziomo, wystarczy sprawdzic jeden blok
                    break;


                blocks[0].setY(blocks[1].getY()-GAME.SIZE);
                blocks[0].setX(blocks[1].getX());

                blocks[2].setY(blocks[1].getY()+GAME.SIZE);
                blocks[2].setX(blocks[1].getX());

                blocks[3].setY(blocks[1].getY()+2*GAME.SIZE);
                blocks[3].setX(blocks[1].getX());

                this.rotate_state = 0;
                break;
        }
    }


}
