package ground;


public class GroundCell {
    private CellState state = CellState.FREE;
    private int x;
    private int y;
    public GroundCell(int x, int y){
        this.x = x;
        this.y = y;
    }
    public CellState getState(){
        return state;
    }
}
