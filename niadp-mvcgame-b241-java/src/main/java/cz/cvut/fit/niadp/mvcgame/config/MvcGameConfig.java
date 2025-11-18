package cz.cvut.fit.niadp.mvcgame.config;

public class MvcGameConfig {

    private MvcGameConfig(){

    }

    public static final int MAX_X = 1920;
    public static final int MAX_Y = 1080;
    public static final int MOVE_STEP = 10;

    public static final int CANNON_POS_X = 50;
    public static final int CANNON_POS_Y = MAX_Y / 2;

    public static final double INIT_ANGLE = 0;
    public static final int INIT_POWER = 10;
    public static final double ANGLE_STEP = Math.PI / 18;
    public static final int POWER_STEP = 1;
    public static final double GRAVITY = 9.81;
    public static final int MAGIC_TIME_CONST = 100;



}