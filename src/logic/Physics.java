package logic;

import java.awt.*;

public class Physics {

    public boolean checkCollisions(Rectangle r1, Rectangle r2) {
         if (r1.intersects(r2)){
             return true;
         }
         else {
             return false;
         }
    }
}
