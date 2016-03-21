package ground;


public class Ground {
    private GroundCell[][] landscape;
    private int length;
    private int width;

    public Ground(int length, int width){
        this.length = length;
        this.width = width;
        landscape = new GroundCell[this.length][this.width];
        for(int i = 0; i < landscape.length; i++){
            for(int j = 0; j < landscape[i].length; j++){
                landscape[i][j] = new GroundCell(i,j);
            }
        }
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public GroundCell getCell(int i, int j){
        return landscape[i][j];
    }
}
