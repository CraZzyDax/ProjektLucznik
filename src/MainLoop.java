public class MainLoop implements Runnable {

    private int idleTime =  200;
    private boolean running = true;
    private Physics physics;
    private boolean enableAutomatedTicks = true;

    public MainLoop(Physics physics) {
        this.physics = physics;
    }

    @Override
    public void run() {
        while(running){
            try {
                Thread.sleep(idleTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(enableAutomatedTicks)
                physics.tick();
        }
    }

    public void stopLoop(){
        running = false;
    }
    public void disableAutoamtedTicks(){
        enableAutomatedTicks = false;
    }
    public void enableAutoamtedTicks(){
        enableAutomatedTicks = true;
    }

    public boolean isEnableAutomatedTicks() {
        return enableAutomatedTicks;
    }
}
