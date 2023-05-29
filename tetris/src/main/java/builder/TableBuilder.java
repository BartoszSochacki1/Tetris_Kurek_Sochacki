package builder;

public class TableBuilder {
    private final int score;
    private final String name;

    public TableBuilder( int score, String name) {
        this.score = score;
        this.name = name;
    }


    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

}
