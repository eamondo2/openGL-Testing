package com.shit.minecraft.util;

import org.lwjgl.util.vector.Vector3f;

import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.util.glu.GLU.gluLookAt;

/**
 * Created by eamondo2 on 9/25/14.
 */
public class Player {
    public float speed;
    private Vector3f lookV;
    private Vector3f Yvec;
    private Vector3f Xvec;
    private Vector3f Zvec;
    private float pitch;
    private float yaw;
    private Vector3f lookPos;
    private Vector3f camPos;

    public Player(Vector3f look, Vector3f X, Vector3f Y, Vector3f Z, float p, float y, Vector3f lp, Vector3f cam) {
        //constructor for instantiating a new Player
        //WIP, more to come.
        this.lookV = look;
        this.Yvec = Y;
        this.Xvec = X;
        this.Zvec = Z;
        this.pitch = p;
        this.yaw = y;
        this.lookPos = lp;
        this.camPos = cam;
    }


    public void cYaw(float y) {
        this.yaw += y;
        this.lookPos = MatrixRotations.uberRot(y, camPos, lookPos, Yvec);
        //this.Xvec = MatrixRotations.vectorRot(y, this.Yvec, this.Xvec);
        //this.Zvec = MatrixRotations.vectorRot(y, this.Yvec, this.Zvec);
        System.out.println("LOOKPOS" + lookPos.x + " " + lookPos.y + " " + lookPos.z);
    }

    public void cPitch(float p) {
        this.pitch += p;
        this.lookPos = MatrixRotations.uberRot(p, this.camPos, this.lookPos, this.Xvec);
        //this.Yvec = MatrixRotations.vectorRot(p, this.Xvec, this.Yvec);
        //this.Zvec = MatrixRotations.vectorRot(p, this.Xvec, this.Zvec);
        System.out.println("LOOKPOS" + lookPos.x + " " + lookPos.y + " " + lookPos.z);
    }

    public float getPitch() {
        return this.pitch;
    }

    public float getYaw() {
        return this.yaw;
    }

    //change look point
    public void lookAt() {

        glLoadIdentity();
        //gluLookAt(lookPos.x, lookPos.y, lookPos.z, camPos.x, camPos.y, camPos.z, Yvec.x, Yvec.y, Yvec.z);
        gluLookAt(camPos.x, camPos.y, camPos.z, lookPos.x, lookPos.y, lookPos.z, Yvec.x, Yvec.y, Yvec.z);


    }

    //calculates rotation etc for this frame
    public void update() {

    }


}
