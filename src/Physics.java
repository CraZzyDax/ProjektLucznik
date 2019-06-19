import java.lang.Math;

public class Physics {

    public static final int FIRST_POSITION_X = 500;
    public static final int FIRST_POSITION_Y = 250;

    private double a;
    private double v;
    private double vx, vy;
    private double ymax, xmax, t0, t1, tmax;
    private double g;


    double i;

    private Arrow arrow;
    private double windX;
    private double initPower;
    private double power;


    public Physics(double angle, double initialSpeed, double g, double windX) {
        i = 0;
        arrow = new Arrow();
        beginConfiguration();
        arrow.setAngleX(angle);
        this.g = g;
        power = initialSpeed;
        a = trim(Math.toRadians(arrow.getAngleX()));
        vx = (power*0.001) * StrictMath.cos(arrow.getAngleX());
        vy = (power *0.001) * StrictMath.sin(arrow.getAngleX());
        ymax = vy * vy / (2.0 * g);
        xmax = 2.0 * vx * vy / g;
        tmax = 2.0 * vy / g;
        t1 = vy / g;
    }

    private double trim(double a) {
        if (a < 0) a = -a;
        while (a > Math.PI / 2.0) a = a - Math.PI / 2.0;
        return a;
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


    //tick 50/s
    ///    public double getXfor(double t) {
    //        return vx * t;
    //    }
    //
    //    public double getYfor(double t) {
    //        return vy * t - g * t * t / 2.0;
    //    }

    public synchronized void tick() {

        i = i + 0.1;
        System.out.println("tick" + i);
        System.out.println("przedkosc poczatkowa:   " + power);
        System.out.println("predkosc dla danego x: " + vx);
        System.out.println("predkosc dla danego y: " + vy);
        System.out.println("sinus kąta " + " " + arrow.getAngleX() + "    :  " + StrictMath.sin(arrow.getAngleX()));
        System.out.println("cos kąta " + " " + arrow.getAngleX() + "    :  " + StrictMath.cos(arrow.getAngleX()));



        arrow.setPosX((arrow.getPosX() + i * StrictMath.cos(arrow.getAngleX())));
        arrow.setPosY((arrow.getPosY() * i * StrictMath.sin(arrow.getAngleX()) - (g / 0.2) * i * i));

        /*
        if ((arrow.getPosY() < 550) && (arrow.getPosX() < 900) && (arrow.getPosY() > 0)) {



            arrow.setPosX((arrow.getPosX() + i * StrictMath.cos(arrow.getAngleX())));

            arrow.setPosY((arrow.getPosY() * i * StrictMath.sin(arrow.getAngleX()) - (g / 0.2) * i * i));

        } else {
            arrow.setPosX(arrow.getPosX());
            arrow.setPosY(arrow.getPosY());
        }
*/
        System.out.println(arrow.getPosY());
        System.out.println(arrow.getPosX());

    }
}