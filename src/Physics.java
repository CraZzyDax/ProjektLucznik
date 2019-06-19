import java.lang.Math;

public class Physics {

    public static final int FIRST_POSITION_X = 100;
    public static final int FIRST_POSITION_Y = 350;

    private double a;
    private double v;
    private double vx, vy;
    private double ymax, xmax, t0, t1, tmax;
    private double g;

    private Arrow arrow;
    private double windX;
    private double initPower;
    private double power;


    public Physics(double angle, double initialSpeed,double g,double windX) {
        arrow = new Arrow();
        beginConfiguration();
        arrow.setAngleX(angle);
        this.g = g;
        v = initialSpeed;
        a = trim(Math.toRadians(angle));
        vx = v * StrictMath.cos(a);
        vy = v * StrictMath.sin(a);
        ymax = vy * vy / (2.0 * g);
        xmax = 2.0 * vx * vy / g;
        tmax = 2.0 * vy / g;
        t1 = vy / g;
    }

    private double trim(double kat) {
        if (kat < 0) kat = -kat;
        while (kat > Math.PI / 2.0) kat = kat - Math.PI / 2.0;
        return kat;
    }

    public double getXfor(double t) {
        return vx * t;
    }

    public double getYfor(double t) {
        return vy * t - g * t * t / 2.0;
    }


    ////////////////////



    public static final int WALL_X = 929;
    public static final int WALL_Y = 580;

    public double getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public double getWindX() {
        return windX;
    }

    public void setWindX(int windX) {
        this.windX = windX;
    }

    public double getg() {
        return g;
    }

    public void setg(int g) {
        this.g = g;
    }

    public double getInitPower() {
        return initPower;
    }

    public void setInitPower(double initPower) {
        this.initPower = initPower;
    }


    private void beginConfiguration() {
        arrow.setPosX(FIRST_POSITION_X);
        arrow.setPosY(FIRST_POSITION_Y);
    }

    public Arrow getArrow() {
        return arrow;
    }

    public synchronized void tick() {
        System.out.println("tick");


        if ((arrow.getPosY() < WALL_Y) && (arrow.getPosX() < WALL_X) && (arrow.getPosY() > 0)) {

            arrow.setPosX(arrow.getPosX() + 5.5 + power + 0.5);

            arrow.setPosY(getArrow().getPosY() + power);


        } else {
            arrow.setPosX(arrow.getPosX());
            arrow.setPosY(arrow.getPosY());
        }

        System.out.println(arrow.getPosY());
        System.out.println(arrow.getPosX());

    }
}