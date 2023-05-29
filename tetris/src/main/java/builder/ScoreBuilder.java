package builder;

import setting.GLOBAL;

import javax.swing.*;

public class ScoreBuilder{
    private final JLabel jlheader;
    private final JLabel lvalue;
    private int value;

    public ScoreBuilder(String header, int value) {
        this.jlheader = new JLabel(header);
        this.lvalue = new JLabel(value+"");
        this.value = value;

        this.jlheader.setFont(GLOBAL.SECONDARY_FONT);
        this.lvalue.setFont(GLOBAL.SECONDARY_FONT);

        this.jlheader.setForeground(GLOBAL.MAIN_COLOR);
        this.lvalue.setForeground(GLOBAL.MAIN_COLOR);

    }

    public int getValue() {
        return value;
    }

    public void addValue(int val) {
        this.value = this.value + val;
        this.lvalue.setText(this.value+"");
    }

    public JPanel buildScore(){
        JPanel jpmain = new JPanel();
        jpmain.setLayout(new BoxLayout(jpmain,BoxLayout.PAGE_AXIS));

        JPanel jpheader = new JPanel();
        jpheader.setLayout(new BoxLayout(jpheader,BoxLayout.LINE_AXIS));
        jpheader.add(jlheader);


        JPanel jpvalue = new JPanel();
        jpvalue.setLayout(new BoxLayout(jpvalue,BoxLayout.LINE_AXIS));
        jpvalue.add(lvalue);


        jpmain.add(jpheader);
        jpmain.add(jpvalue);

        jpheader.setOpaque(false);
        jpvalue.setOpaque(false);
        jpmain.setOpaque(false);

        return jpmain;
    }
}
