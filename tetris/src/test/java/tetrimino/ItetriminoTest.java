package tetrimino;

import org.junit.Assert;
import org.junit.Test;
import setting.GAME;

import java.util.Comparator;

import static setting.GAME.ITETRIMINO_COLOR;
import static setting.GAME.ROWS;

public class ItetriminoTest{

    @Test
    public void testRotate() {
        Block[] checkBlocks = new Block[4];
        checkBlocks[0] = new Block(180,45, ITETRIMINO_COLOR);
        checkBlocks[1] = new Block(225,45, ITETRIMINO_COLOR);
        checkBlocks[2] = new Block(270,45, ITETRIMINO_COLOR);
        checkBlocks[3] = new Block(315,45, ITETRIMINO_COLOR);

        Itetrimino itetrimino = new Itetrimino();
        Block[] blocks = itetrimino.getBlocks();

        itetrimino.rotate();

        Comparator<Block> comparator = (a,b)->( a.getY()-b.getY() ) + ( a.getX()-b.getX() );

        for (int i = 0; i < blocks.length; i++) {
            Assert.assertEquals(0,comparator.compare(blocks[i],checkBlocks[i]));
        }


    }

    @Test
    public void testCollisions() {
        Otetrimino otetrimino = new Otetrimino();
        Block[][] playBoard = new Block[GAME.COLS][GAME.ROWS];
        Assert.assertTrue(otetrimino.goleft(playBoard));
        Assert.assertTrue(otetrimino.goleft(playBoard));
        Assert.assertTrue(otetrimino.goleft(playBoard));
        Assert.assertTrue(otetrimino.goleft(playBoard));

        Assert.assertFalse(otetrimino.goleft(playBoard));
    }

    @Test
    public void testFelt() {
        Otetrimino otetrimino = new Otetrimino();
        Block[][] playBoard = new Block[GAME.COLS][GAME.ROWS];
        Ltetrimino ltetrimino = new Ltetrimino();
        Assert.assertFalse(ltetrimino.isFelt(playBoard));

        for (int i = 0; i < ROWS; i++) {
            ltetrimino.gravit();
        }

        Assert.assertTrue(ltetrimino.isFelt(playBoard));

    }
}