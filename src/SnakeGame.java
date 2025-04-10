import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class SnakeGame extends JPanel implements ActionListener {
    private class Tile {
        int x;
        int y;

        Tile(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    int boardWidth;
    int boardHeight;
    int tileSize = 25;

    //Snake
    Tile snakeHead;

    //Food
    Tile food;
    Random random;

    //game logic
    Timer gameLoop;
    int velocityX;
    int velocityY;


    SnakeGame(int boardHeight, int boardWidth) {
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        setPreferredSize(new Dimension(this.boardWidth, this.boardHeight));
        setBackground(Color.black);

        snakeHead = new Tile(5, 5);

        food = new Tile(10,10);

        random = new Random();
        placeFood();

        velocityX = 0;
        velocityY = 0;

        gameLoop = new Timer(100,this);
        gameLoop.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {

        final int SQUARES_PER_ROW = boardWidth / tileSize;
        //Grid
        for (int i = 0; i < SQUARES_PER_ROW; i++) {
            g.drawLine(i*tileSize, 0, i*tileSize, boardHeight);
            g.drawLine(0, i*tileSize, boardWidth, i*tileSize);
        }

        //Food
        g.setColor(Color.red);
        g.fillRect(food.x * tileSize, food.y * tileSize, tileSize, tileSize);


        //Snake
        g.setColor(Color.green);
        g.fillRect(snakeHead.x * tileSize, snakeHead.y * tileSize, tileSize, tileSize);
    }

    public void placeFood() {
        final int SQUARES_PER_ROW = boardWidth / tileSize;
        final int SQUARES_PER_COLUMN = boardHeight / tileSize;

        food.x = random.nextInt(SQUARES_PER_ROW); // 600/25 = 24
        food.y = random.nextInt(SQUARES_PER_COLUMN);
    }

    public void move() {

        //Snake head
        snakeHead.x += velocityX;
        snakeHead.y += velocityY;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
    }
}
