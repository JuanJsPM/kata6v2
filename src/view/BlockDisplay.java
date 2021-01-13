package view;

import model.Block;

public interface BlockDisplay{
    void display(int x, int y);
    void on(Moved moved);
    
    interface Moved {
        void to(int x, int y);

        public static class Null implements Moved {

            @Override
            public void to(int x, int y) {
                
            }

            
        }
    }
}
