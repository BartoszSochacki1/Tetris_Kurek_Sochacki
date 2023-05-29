package game;

import builder.ScoreBuilder;

import javax.swing.*;

public class Score {
    private final ScoreBuilder score;
    private final ScoreBuilder lines;
    private final ScoreBuilder speed;

    public static int DOWN_BUTTON = 1;
    public static int SPACE = 2;
    public static int LINE = 100;

    public Score() {

        score = new ScoreBuilder("WYNIK:",0);
        lines = new ScoreBuilder("LINIE:",0);
        speed = new ScoreBuilder("SZYBKOŚĆ:",1);

    }

    public JPanel build(){
        JPanel jp = new JPanel();

        jp.setLayout(new BoxLayout(jp,BoxLayout.PAGE_AXIS));
        jp.add(score.buildScore());
        jp.add(Box.createVerticalStrut(20));

        jp.add(lines.buildScore());
        jp.add(Box.createVerticalStrut(20));

        jp.add(speed.buildScore());

        jp.setOpaque(false);

        return jp;
    }

    public ScoreBuilder getScore() {
        return score;
    }

    public ScoreBuilder getLines() {
        return lines;
    }

    public ScoreBuilder getSpeed() {
        return speed;
    }

    public int calculateSpeed(){
        int lines =  this.lines.getValue();

        if(lines <10)
            return 1;
        else if (lines >=10 && lines<20)
            return 2;
        else if(lines > 19 && lines<30)
            return 3;
        else if(lines > 29 && lines<40)
            return 4;
        else if(lines > 39 && lines<50)
            return 5;
        else if(lines > 49 && lines<80)
            return 6;
        else if(lines > 79 && lines<110)
            return 7;
        else if(lines > 109 && lines<149)
            return 8;
        else if(lines > 149)
            return 9;

        return 1;
    }
}
