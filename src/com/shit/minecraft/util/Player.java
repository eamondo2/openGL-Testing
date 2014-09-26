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
        this.lookPos.set(MatrixRotations.uberRot(y, camPos, lookPos, Yvec));
        this.Xvec.set(MatrixRotations.vectorRot(y, Yvec, Xvec));
        this.Zvec.set(MatrixRotations.vectorRot(y, Yvec, Zvec));
        this.lookV.set(MatrixRotations.vectorRot(y, Yvec, lookV));
        System.out.println("LOOKPOS" + lookPos.x + " " + lookPos.y + " " + lookPos.z);
    }

    public void cPitch(float p) {
        this.pitch += p;
        this.lookPos = MatrixRotations.uberRot(p, this.camPos, this.lookPos, this.Xvec);
        //this.Yvec.set(MatrixRotations.vectorRot(p, Xvec, Yvec));
        this.Zvec.set(MatrixRotations.vectorRot(p, Xvec, Zvec));
        this.lookV.set(MatrixRotations.vectorRot(p, Xvec, lookV));
        System.out.println("LOOKPOS" + lookPos.x + " " + lookPos.y + " " + lookPos.z);
    }

    public float getPitch() {
        return this.pitch;
    }

    public float getYaw() {
        return this.yaw;
    }

    //change look point
    public void forward(float inc) {
        Vector3f temp = new Vector3f(lookV.x * inc, lookV.y * inc, lookV.z * inc);
        lookPos.x = lookPos.x + temp.x;
        lookPos.y = lookPos.y + temp.y;
        lookPos.z = lookPos.z + temp.z;
        camPos.x = camPos.x + temp.x;
        camPos.y = camPos.y + temp.y;
        camPos.z = camPos.z + temp.z;
    }

    public void strafe(float inc) {
        Vector3f temp = new Vector3f(Xvec.x * inc, Xvec.y * inc, Xvec.z * inc);
        lookPos.x = lookPos.x + temp.x;
        lookPos.y = lookPos.y + temp.y;
        lookPos.z = lookPos.z + temp.z;
        camPos.x = camPos.x + temp.x;
        camPos.y = camPos.y + temp.y;
        camPos.z = camPos.z + temp.z;
    }
    public void lookAt() {

        glLoadIdentity();
        //gluLookAt(lookPos.x, lookPos.y, lookPos.z, camPos.x, camPos.y, camPos.z, Yvec.x, Yvec.y, Yvec.z);
        gluLookAt(camPos.x, camPos.y, camPos.z, lookPos.x, lookPos.y, lookPos.z, Yvec.x, Yvec.y, Yvec.z);


    }

    //calculates rotation etc for this frame
    public void update() {

    }


}
