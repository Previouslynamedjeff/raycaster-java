package src.window;

import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.Timer;
import java.awt.GridLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window {
    private JFrame frame;

    private Timer drawTimer;

    public Window(String title, int width, int height, int delay) {
        this.frame = new JFrame(title);
        this.frame.getContentPane().setPreferredSize(new Dimension(width, height));
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setResizable(false);
        this.frame.setLayout(new GridLayout());

        this.drawTimer = new Timer(delay, new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                draw();
            }
        });
    }

    public void start() {
        this.frame.setVisible(true);
        this.frame.pack();
        this.drawTimer.start();
    }

    public void draw() {
        this.frame.repaint();
    }

    public void addCanvas(Canvas canvas) {
        this.frame.add(canvas);
    }
}
