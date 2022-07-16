package src.window;

import javax.swing.JPanel;

import java.awt.Graphics;
import java.util.ArrayList;

public class Canvas extends JPanel {
    private ArrayList<Drawable> drawables;

    public Canvas() {
        this.drawables = new ArrayList<Drawable>();

        setFocusable(true);
        requestFocusInWindow();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Drawable drawable: this.drawables) {
            drawable.draw(g);
        }
    }

    public void addDrawable(Drawable drawable) {
        this.drawables.add(drawable);
    }
}