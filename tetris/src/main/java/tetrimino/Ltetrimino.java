package tetrimino;


import setting.GAME;

public class Ltetrimino extends Tetrimino{

    public Ltetrimino() {
        color = GAME.LTETRIMINO_COLOR;
        rotate_state = 0;
        blocks = new Block[4];
        blocks[0] = new Block(GAME.MIDDLE+GAME.SIZE,2*GAME.SIZE,color);
        blocks[1] = new Block(GAME.MIDDLE,2*GAME.SIZE,color);

        blocks[2] = new Block(GAME.MIDDLE,GAME.SIZE,color);
        blocks[3] = new Block(GAME.MIDDLE,0,color);
    }

    @Override
    public void rotate() {
        switch (rotate_state){
            case 0:
                if(blocks[3].getX() == GAME.MIN_X)
                    break;

                blocks[0].setX(blocks[0].getX()-2*GAME.SIZE);

                blocks[1].setY(blocks[1].getY()-GAME.SIZE);
                blocks[1].setX(blocks[1].getX()-GAME.SIZE);

                blocks[3].setY(blocks[3].getY()+GAME.SIZE);
                blocks[3].setX(blocks[3].getX()+GAME.SIZE);


                rotate_state = 1;
                break;
            case 1:

                blocks[0].setY(blocks[0].getY()-2*GAME.SIZE);

                blocks[1].setY(blocks[1].getY()-GAME.SIZE);
                blocks[1].setX(blocks[1].getX()+GAME.SIZE);

                blocks[3].setY(blocks[3].getY()+GAME.SIZE);
                blocks[3].setX(blocks[3].getX()-GAME.SIZE);

                rotate_state = 2;
                break;
            case 2:

                if(blocks[3].getX() > GAME.MAX_X-2*GAME.SIZE)
                    break;

                blocks[0].setX(blocks[0].getX()+2*GAME.SIZE);

                blocks[1].setY(blocks[1].getY()+GAME.SIZE);
                blocks[1].setX(blocks[1].getX()+GAME.SIZE);

                blocks[3].setY(blocks[3].getY()-GAME.SIZE);
                blocks[3].setX(blocks[3].getX()-GAME.SIZE);
                rotate_state = 3;
                break;
            case 3:

                if (blocks[3].getY() > GAME.MAX_Y-GAME.SIZE*2)
                    break;

                blocks[0].setY(blocks[0].getY()+2*GAME.SIZE);

                blocks[1].setY(blocks[1].getY()+GAME.SIZE);
                blocks[1].setX(blocks[1].getX()-GAME.SIZE);

                blocks[3].setY(blocks[3].getY()-GAME.SIZE);
                blocks[3].setX(blocks[3].getX()+GAME.SIZE);

                rotate_state = 0;
                break;
        }
    }

}
