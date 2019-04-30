public class Physics {

    private Arrow arrow;

    private int windX;
    private int windY;
    private int windZ;
    private int power;

    public static final int WALL_X = 929;
    public static final int WALL_Y = 580;

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getWindX() {
        return windX;
    }

    public void setWindX(int windX) {
        this.windX = windX;
    }

    public int getWindY() {
        return windY;
    }

    public void setWindY(int windY) {
        this.windY = windY;
    }

    public int getWindZ() {
        return windZ;
    }

    public void setWindZ(int windZ) {
        this.windZ = windZ;
    }

    public Physics(){
        arrow = new Arrow();
        beginConfiguration();
    }

    private void beginConfiguration(){
        arrow.setPosX(100);
        arrow.setPosY(350);
        arrow.setAngleX(45);
    }

    public Arrow getArrow() {
        return arrow;
    }

    public void tick(){
        System.out.println("tick");
        arrow.setPosX(arrow.getPosX()+10);
    }
}
