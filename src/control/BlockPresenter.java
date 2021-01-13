package control;

import model.Block;
import view.BlockDisplay;

public class BlockPresenter implements Block.Observer{
    private final Block block;
    private final BlockDisplay blockDisplay;

    public BlockPresenter(Block block, BlockDisplay blockDisplay) {
        this.block = block;
        this.block.register(this);
        this.blockDisplay = blockDisplay;
        this.blockDisplay.on(moved());
        this.paint();
    }

    @Override
    public void changed() {
        paint();
    }

    private void paint() {
        blockDisplay.display(block.x()-1, Block.MAX - block.y());
    }

    private BlockDisplay.Moved moved() {
        return new BlockDisplay.Moved() {
            @Override
            public void to(int x, int y) {
                block.pos(x+1, Block.MAX-y);
            }
        };
    }
    
    
}
