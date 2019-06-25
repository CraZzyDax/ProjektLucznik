import java.lang.Math;

public class Physics {

    public static final int FIRST_POSITION_X = 200;
    public static final int FIRST_POSITION_Y = 250;

    private float a;
    private float v;
    private float forceX, forceY;
    private float ymax, xmax, t0, t1, tmax;
    private float g;


    float i;

    private Arrow arrow;
    private float windX;
    private float initPower;
    private float power;


    public Physics(float angle, float initialSpeed, float g, float windX) {
        i = 0;
        this.windX = windX;
        arrow = new Arrow();
        beginConfiguration();
        arrow.setAngleX(angle);
        this.g = g;
//        power = power * 0.001F;
        power = initialSpeed;
//        a = trim((float) Math.toRadians(arrow.getAngleX()));//???
        forceX = (float) (power * StrictMath.sin(arrow.getAngleX() / 180.0 * Math.PI));
        forceY = (float) (power * -StrictMath.cos(arrow.getAngleX() / 180.0 * Math.PI));
//        ymax = forceY * forceY / (2.0F * g);
//        xmax = 2.0F * forceX * forceY / g;
//        tmax = 2.0F * forceY / g;
//        t1 = forceY / g;
    }

    public synchronized void tick() {

        i = i + 0.1F;
//        System.out.println("tick" + i);
//        System.out.println("przedkosc poczatkowa:   " + power);
//        System.out.println("predkosc dla danego x: " + forceX);
//        System.out.println("predkosc dla danego y: " + forceY);
//        System.out.println("sinus kąta " + " " + arrow.getAngleX() + "    :  " + StrictMath.sin(arrow.getAngleX()));
//        System.out.println("cos kąta " + " " + arrow.getAngleX() + "    :  " + StrictMath.cos(arrow.getAngleX()));

        //arrow.setPosX((float) (arrow.getPosX() + i * StrictMath.cos(arrow.getAngleX())));
        //arrow.setPosY((float) (arrow.getPosY() * i * StrictMath.sin(arrow.getAngleX()) - (g / 0.2) * i * i));


        if ((arrow.getPosY() < 550) && (arrow.getPosX() < 950) && (arrow.getPosY() > 0)) {
            float y0 = arrow.getPosY(); //poprzednia iteracja (współrzedne)
            float x0 = arrow.getPosX();

            forceY = forceY + g*0.1f;
            forceX = forceX + windX*0.01f;
            arrow.setPosX(arrow.getPosX() + forceX);
            arrow.setPosY(arrow.getPosY() + forceY);

            float y1 = arrow.getPosY() + forceY;
            float x1 = arrow.getPosX() + forceX;

            float angle = (float) Math.toDegrees(Math.atan2(y1 - y0, x1 - x0));

            arrow.setAngleX(angle);


        } else {
            arrow.setPosX(arrow.getPosX());
            arrow.setPosY(arrow.getPosY());
        }

//        System.out.println("y=  " + arrow.getPosY());
//        System.out.println("x=  " + arrow.getPosX());

    }
    
    ////////////////////


    public static final int WALL_X = 929;
    public static final int WALL_Y = 580;

    public float getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public float getWindX() {
        return windX;
    }

    public void setWindX(int windX) {
        this.windX = windX;
    }

    public float getg() {
        return g;
    }

    public void setg(int g) {
        this.g = g;
    }

    public float getInitPower() {
        return initPower;
    }

    public void setInitPower(float initPower) {
        this.initPower = initPower;
    }


    private void beginConfiguration() {
        arrow.setPosX(FIRST_POSITION_X);
        arrow.setPosY(FIRST_POSITION_Y);
    }

    public Arrow getArrow() {
        return arrow;
    }


}