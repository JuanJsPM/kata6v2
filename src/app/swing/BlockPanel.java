package app.swing;

import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import model.Block;
import view.BlockDisplay;

public class BlockPanel extends JPanel implements BlockDisplay{
    
    private final int blockSize;
    private final int max;
    private int x;
    private int y;
    private Moved moved = new Moved.Null();

    public BlockPanel(int max, int blockSize) {
        this.max = max;
        this.blockSize = blockSize;
        MouseHandler mouseHandler = new MouseHandler();
        this.addMouseListener(mouseHandler);
        this.addMouseMotionListener(mouseHandler);
    }

    @Override 
    public void paint(Graphics g){
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.BLACK);
        int max = this.max * this.blockSize;
        for (int i = 0; i <= max; i+=blockSize) {
            g.drawLine(0, i, max, i);
            g.drawLine(i, 0, i, max);
        }
        
        g.setColor(Color.RED);
        g.fillRect(x * blockSize, y * blockSize, blockSize, blockSize);
        
    }

    @Override
    public void display(int x, int y) {
        this.x = x;
        this.y = y;
        repaint();
    }

    @Override
    public void on(Moved moved) {
        this.moved = moved;
    }

    private class MouseHandler implements MouseMotionListener, MouseListener{
        private boolean grabbed = false;

        @Override
        public void mouseDragged(MouseEvent event) {
            if (grabbed) moved.to(event.getX()/blockSize, event.getY()/blockSize);
        }

        @Override
        public void mouseMoved(MouseEvent event) {
        }

        @Override
        public void mouseClicked(MouseEvent event) {
        }

        @Override
        public void mousePressed(MouseEvent event) {
            grabbed = event.getX()/blockSize == x && event.getY()/blockSize == y;
        }

        @Override
        public void mouseReleased(MouseEvent event) {
        }

        @Override
        public void mouseEntered(MouseEvent event) {
        }

        @Override
        public void mouseExited(MouseEvent event) {
        }

        private boolean isIn(int x, int target) {
           return x == target;
        }
    }
    
}
