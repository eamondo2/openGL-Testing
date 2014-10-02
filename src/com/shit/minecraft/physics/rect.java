package com.shit.minecraft.physics;

import org.lwjgl.util.vector.Vector3f;

/**
 * Created by eamondo2 on 10/2/14.
 */
public class rect {
    //temp class for rectangular static body, 2d
    public Vector3f[] verts = new Vector3f[4];
    public Vector3f uVdir = new Vector3f(0f, 0f, 0f);
    public Vector3f uNorm = new Vector3f(0f, 0f, 0f);
    public float mass;
    public float v;

    public rect(float massin, Vector3f[] vertsin, Vector3f uVdirin, float vin) {
        this.mass = massin;
        this.verts = vertsin;
        this.uVdir = uVdirin;
        this.v = vin;
    }

    public void render() {
        //iterate across verts, render

    }

    public void update() {
        //calculate change in position due to V and vdir


    }


}
