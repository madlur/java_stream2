package lesson_01;

import java.awt.*;

public class Background  {

    Background(GameCanvas canvas){
        Color color = new Color (
                (int)(Math.random() * 255),
                (int)(Math.random() * 255),
                (int)(Math.random() * 255));
        canvas.setBackground(color);
    }

}
