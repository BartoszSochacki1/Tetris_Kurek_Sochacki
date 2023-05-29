package game;

import builder.ScoreBuilder;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

public class ScoreTest{


    @Test
    public void testScore() {
        Score score = new Score();
        ScoreBuilder scoreBuilder = score.getSpeed();
        scoreBuilder.addValue(1);

        int value = scoreBuilder.getValue();
        Assert.assertEquals(2,value);
    }

    @Test
    public void testCaltulateSpeed(){
        Score score = new Score();
        score.getLines().addValue(70);

        int level = score.calculateSpeed();

        Assert.assertEquals(6,level);
    }
}