package com.eamondo2.gltest.physics;

import com.eamondo2.gltest.GlTest;
import org.lwjgl.util.vector.Vector3f;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created by eamondo2 on 10/2/14.
 */
public class Rect {
    //temp class for rectangular static body, 2d
    public Vector3f[] verts = new Vector3f[4];
    public Vector3f pos;
    public Vector3f uVdir = new Vector3f(0f, 0f, 0f);
    public Vector3f V = new Vector3f(0f, 0f, 0f);
    public float mass;
    public float v;
    public float g = 9.80665f;
    public Vector3f gDir = new Vector3f(0f, 1f, 0f);

    public Rect(Vector3f posin, float massin, Vector3f[] vertsin, Vector3f uVdirin, float vin) {
        this.mass = massin;
        this.verts = vertsin;
        this.uVdir = uVdirin;
        this.v = vin;
        this.pos = posin;
    }

    public void render() {
        //iterate across verts, render
        glPushMatrix();
        glLoadIdentity();
        glTranslatef(pos.x, pos.y, pos.z);
        glColor3f(1f, 0f, 0f);
        glBegin(GL_QUADS);
        for (int i = 0; i < verts.length; i++) {
            glVertex3f(verts[i].x, verts[i].y, verts[i].z);


        }
        glEnd();
        glPopMatrix();
        //render vectors
        glBegin(GL_LINES);
        glColor3f(0f, 1f, 0f);
        glVertex3f(pos.x, pos.y, pos.z);
        /*
        multiply velocity by the unit vector and add to the position to obtain the vector representation
        of the speed + direction

         */
        glVertex3f(pos.x + (V.x * uVdir.x), pos.y + (-V.y * uVdir.y), pos.z + (V.z * uVdir.z));

        glEnd();


    }

    public void update() {
        //calculate change in position due to V and vdir
        uVdir = GlTest.normalize(uVdir);
        System.out.println("UVDIR" + uVdir);
        //calculate acceleration
        //f = m*a
        //a = f/m
        Vector3f tempVNewt = new Vector3f((mass * v) * uVdir.x, (mass * v) * uVdir.y, (mass * v) * uVdir.z);
        System.out.println("VNEWT" + tempVNewt);
        Vector3f tempGNewt = new Vector3f((mass * (-g)) * gDir.x, (mass * (-g)) * gDir.y, (mass * (-g)) * gDir.z);
        System.out.println("GNEWT" + tempGNewt);
        System.out.println("UVDIR" + uVdir);
        Vector3f NewtVdir = new Vector3f(tempVNewt.x + tempGNewt.x, tempVNewt.y + tempGNewt.y, tempVNewt.z + tempGNewt.z);
        System.out.println("UVDIRNEWT" + NewtVdir);
        V = NewtVdir;
        pos.x += (NewtVdir.x / mass);
        pos.y += -(NewtVdir.y / mass);
        pos.z += (NewtVdir.z / mass);
        System.out.println("Position" + pos);

        uVdir = GlTest.normalize(uVdir);


    }


}
