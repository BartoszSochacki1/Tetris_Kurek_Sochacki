package game;

import helper.methods.PaintMethods;
import setting.GAME;
import setting.KEY;
import tetrimino.*;
import view.EndGameView;
import view.GameView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Random;


public class Tetris extends JPanel {

    private final Block[][] blocks;
    private final PaintMethods paintMethods;
    private final NextTetrimino nextTetrimino;
    private final GameView gameView;
    private final JFrame window;
    private final Timer timer;
    private final Score score;

    private int speed;
    private long nextFrameTime;
    private long fallTime;
    private Tetrimino tetrimino;

    public Tetris(JFrame window, GameView gameView, Score score, JPanel nextTetrimino) {
        this.score = score;
        this.gameView = gameView;
        this.window = window;

        this.paintMethods = new PaintMethods();
        this.speed = score.getSpeed().getValue();

        this.fallTime = GAME.DROP_TIME;

        blocks = new Block[GAME.COLS][GAME.ROWS];

        tetrimino = newTetrimino();
        this.nextTetrimino = new NextTetrimino(newTetrimino());

        nextTetrimino.add(this.nextTetrimino);

        addKeyListeners();

        nextFrameTime = System.currentTimeMillis() + fallTime;
        timer = new Timer(GAME.DELAY, e -> {

            calculate();
            repaint();

        });
        timer.start();
    }

    private void calculate(){

        if(nextFrameTime < System.currentTimeMillis()){
            if(tetrimino.isFelt(blocks)){


                for (Block block:tetrimino.getBlocks()) {

                    blocks[block.getX()/GAME.SIZE][block.getY()/GAME.SIZE] = block;
                }

                checkLines();
                isGameEnd();

                tetrimino = nextTetrimino.getTetrimino();
                nextTetrimino.setTetrimino(newTetrimino());

            }else{
                tetrimino.gravit();
            }

            nextFrameTime = System.currentTimeMillis() + fallTime;
        }



    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g.create();

        //rysowanie tła
        paintMethods.paintBackground(g2d);

        //rysowanie bloków
        paintMethods.paintBlocks(g2d,blocks);

        //rysowanie tetrimino
        paintMethods.paintTetrimino(g2d,tetrimino);

        //odświeżenie grafiki
        g2d.dispose();
    }


    private Tetrimino newTetrimino(){
        int rand = new Random().nextInt(7);

        switch (rand){
            case 0:
                return new Itetrimino();
            case 1:
                return new Jtetrimino();
            case 2:
                return new Ltetrimino();
            case 3:
                return new Stetrimino();
            case 4:
                return new Ttetrimino();
            case 5:
                return new Ztetrimino();
            default:
                return new Otetrimino();
        }
    }


    private void checkLines() {
        int counter;
        for(int j =0;j<GAME.ROWS;j++){

            counter = 0;
            for(int i = 0;i<GAME.COLS;i++){
                if(blocks[i][j]!=null)
                    counter ++;
            }




            if(counter == GAME.COLS){
                for(int k = 0;k<GAME.COLS;k++)
                    blocks[k][j]=null;

                score.getScore().addValue(Score.LINE*this.speed);
                score.getLines().addValue(1);

                if(this.speed != score.calculateSpeed()){

                    score.getSpeed().addValue(1);
                    this.speed++;
                    this.fallTime=this.fallTime-100;
                }


                for(int l = j;l>0;l--) { //grawitacja
                    for (int m = 0; m < GAME.COLS; m++) {
                        blocks[m][l] = blocks[m][l-1];
                        if(blocks[m][l]!=null)
                            blocks[m][l].setY(blocks[m][l].getY()+GAME.SIZE);

                    }
                }
                checkLines();

            }
        }
    }

    private void isGameEnd(){

        if(blocks[5][1]!=null || blocks[6][1]!=null) {


            timer.stop();

            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            gameView.setVisible(false);
            window.add(new EndGameView(window,null, score.getScore().getValue()));

        }

    }



    private void addKeyListeners(){
        int IFW = JComponent.WHEN_IN_FOCUSED_WINDOW;

        getInputMap(IFW).put(KeyStroke.getKeyStroke("UP"), KEY.UP);
        getInputMap(IFW).put(KeyStroke.getKeyStroke("LEFT"), KEY.LEFT);
        getInputMap(IFW).put(KeyStroke.getKeyStroke("DOWN"), KEY.DOWN);
        getInputMap(IFW).put(KeyStroke.getKeyStroke("RIGHT"), KEY.RIGHT);
        getInputMap(IFW).put(KeyStroke.getKeyStroke("SPACE"), KEY.SPACE);

        getActionMap().put(KEY.UP, pressed(KEY.UP));
        getActionMap().put(KEY.LEFT, pressed(KEY.LEFT));
        getActionMap().put(KEY.DOWN, pressed(KEY.DOWN));
        getActionMap().put(KEY.RIGHT, pressed(KEY.RIGHT));
        getActionMap().put(KEY.SPACE, pressed(KEY.SPACE));

    }

    private Action pressed(int button) {

        return new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(button==KEY.LEFT){
                    tetrimino.goleft(blocks);
                }
                if(button==KEY.RIGHT){
                    tetrimino.goright(blocks);
                }
                if(button==KEY.UP){
                    tetrimino.rotate();
                }
                if(button==KEY.DOWN){
                    nextFrameTime = System.currentTimeMillis();
                    score.getScore().addValue(Score.DOWN_BUTTON*speed);
                }

                if(button==KEY.SPACE){
                    int count = 0;
                    while (!tetrimino.isFelt(blocks)){
                        tetrimino.gravit();
                        count++;
                    }
                    score.getScore().addValue(Score.SPACE*count*speed);

                }

            }
        };
    }

}
