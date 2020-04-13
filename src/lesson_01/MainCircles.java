package lesson_01;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainCircles extends JFrame {
    private static final int POS_X = 400;
    private static final int POS_Y = 200;
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;
    Sprite[] sprites = new Sprite[10];
    private final float backgroundFlag=100;
    int counter =0;
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainCircles();
            }
        });
    }

    private MainCircles() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(POS_X, POS_Y, WINDOW_WIDTH, WINDOW_HEIGHT);
        GameCanvas canvas = new GameCanvas(this);
        add(canvas, BorderLayout.CENTER);
        setTitle("Circles");
        initApplication();
        add_delete_sprites();
        setVisible(true);
    }

    private void initApplication() {
        for (int i = 0; i < sprites.length; i++) {
            sprites[i] = new Ball();
        }
    }

    void onCanvasRepainted(GameCanvas canvas, Graphics g, float deltaTime) {
        update(canvas, deltaTime);
        render(canvas, g);
        counter+=deltaTime*backgroundFlag;
        if(counter>=backgroundFlag) {
            Background background = new Background(canvas);
            counter = 0;
        }
    }

    private void update(GameCanvas canvas, float deltaTime) {
        for (int i = 0; i < sprites.length; i++) {
            sprites[i].update(canvas, deltaTime);
        }
    }

    private void render(GameCanvas canvas, Graphics g) {
        for (int i = 0; i < sprites.length; i++) {
            sprites[i].render(canvas, g);
        }
    }

    private void add_delete_sprites () {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    Sprite[] addSprites = new Sprite[sprites.length + 1];
                    for (int i = 0; i < sprites.length; i++) {
                        addSprites[i] = sprites[i];
                    }
                    addSprites[sprites.length] = new Ball();
                    sprites = addSprites;

                } else if (SwingUtilities.isRightMouseButton(e)) {

                    Sprite[] deleteSprites = new Sprite[sprites.length - 1];
                    for (int i = 0; i < sprites.length - 1; i++) {
                        deleteSprites[i] = sprites[i+1];
                    }
                    sprites = deleteSprites;
                }

            }
        });
    }
    }