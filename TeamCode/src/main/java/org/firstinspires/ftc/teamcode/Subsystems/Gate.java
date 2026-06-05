package org.firstinspires.ftc.teamcode.Subsystems;

import android.os.SystemClock;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Gate {
    public enum States{
        OPEN,
        CLOSE
    }
    public long timeSnapshot;
    public long gateTime;
    public long deltaTime;
    private States state = States.CLOSE;
    Servo gateServo;
    public static double openPosition = 1;
    public static double closePosition = 0;
    public void initiate(HardwareMap hardwareMap){
        gateServo = hardwareMap.servo.get("gate");
    }
    public void setState(States newState){
        state = newState;
        timeSnapshot = System.currentTimeMillis();
        gateTime = 1000;
    }
    public void update(){
        switch (state){
            case OPEN:
                gateServo.setPosition(openPosition);
                deltaTime = System.currentTimeMillis() - timeSnapshot;
                if (deltaTime > gateTime)
                    setState(States.CLOSE);
                break;
            case CLOSE:
                gateServo.setPosition(closePosition);
                break;
        }

    }
    public States getState (){
        return state;
    }
}
