package app.swing;

import control.BlockPresenter;
import control.Command;
import control.DownCommand;
import control.LeftCommand;
import control.RightCommand;
import control.UpCommand;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import model.Block;
import view.BlockDisplay;
import control.BlockPresenter;
import java.util.HashMap;
import javax.swing.JPanel;


public class Main extends JFrame{
    private static final int BLOCK_SIZE = 100;
    private BlockDisplay blockDisplay;
    private Map<String,Command> commands = new HashMap<>();
    
    public static void main(String[] args) {
        new Main().execute();
    }
    private final BlockPresenter blockPresenter;
    
    public Main(){
        this.setTitle("Block Shifter");
        this.setSize(700,750);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().add(blockPanel());
        this.add(toolbar(), BorderLayout.SOUTH);
        
        Block block = new Block(4,4);
        this.blockPresenter = new BlockPresenter(block, blockDisplay);
        this.commands.put("up", new UpCommand(block));
        this.commands.put("down", new DownCommand(block));
        this.commands.put("left", new LeftCommand(block));
        this.commands.put("right", new RightCommand(block));
    }
    
    private void execute() {
        this.setVisible(true);
    }
    
    private JPanel blockPanel(){
        BlockPanel blockPanel =  new BlockPanel(Block.MAX, BLOCK_SIZE);
        this.blockDisplay = blockPanel;
        return blockPanel;
    }
    
    private Component toolbar() {
        JMenuBar toolbar = new JMenuBar();
        toolbar.setLayout(new FlowLayout(FlowLayout.CENTER));
        toolbar.add(button("left"));
        toolbar.add(button("right"));
        toolbar.add(button("up"));
        toolbar.add(button("down"));
        return toolbar;
    }

    private JButton button(String name) {
        JButton btn = new JButton(name);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evn){
                commands.get(name).execute();
            }
        });
        return btn;
    }
}
