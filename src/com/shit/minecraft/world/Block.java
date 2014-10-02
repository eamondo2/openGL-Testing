package com.shit.minecraft.world;

import com.shit.minecraft.util.AABB;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created by eamondo2 on 9/24/14.
 */
public class Block {

    private AABB aaBB;
    //need to implement mesh type thing to load points from

    private Vector3f pos;

    public Block(Vector3f po) {
        this.pos = po;
    }
    //must store location, mesh (at this point just a simple cube shape)
    //returns the AABB for the block

    public Vector3f getLocation() {
        return pos;
    }

    public AABB getAABB() {
        return this.aaBB;
    }
    //iterate through points, return edge points

    //needs to be retrofitted with an array of points, and an iterator.
    public void render() {
        //translate to location, render mesh
        // Front Face
        glPushMatrix();
        glTranslatef(0 - pos.x, 0 - pos.y, 0 - pos.z);
        glBegin(GL_QUADS);
        glColor3f(0f, 0f, 1f);
        GL11.glVertex3f(-1.0f, -1.0f, 1.0f);   // Bottom Left Of The Texture and Quad

        GL11.glVertex3f(1.0f, -1.0f, 1.0f);   // Bottom Right Of The Texture and Quad

        GL11.glVertex3f(1.0f, 1.0f, 1.0f);   // Top Right Of The Texture and Quad

        GL11.glVertex3f(-1.0f, 1.0f, 1.0f);   // Top Left Of The Texture and Quad

        // Back Face
        glColor3f(0f, 0f, 1f);
        GL11.glVertex3f(-1.0f, -1.0f, -1.0f);   // Bottom Right Of The Texture and Quad

        GL11.glVertex3f(-1.0f, 1.0f, -1.0f);   // Top Right Of The Texture and Quad

        GL11.glVertex3f(1.0f, 1.0f, -1.0f);   // Top Left Of The Texture and Quad

        GL11.glVertex3f(1.0f, -1.0f, -1.0f);   // Bottom Left Of The Texture and Quad

        // Top Face
        glColor3f(0f, 1f, 0f);
        GL11.glVertex3f(-1.0f, 1.0f, -1.0f);   // Top Left Of The Texture and Quad

        GL11.glVertex3f(-1.0f, 1.0f, 1.0f);   // Bottom Left Of The Texture and Quad

        GL11.glVertex3f(1.0f, 1.0f, 1.0f);   // Bottom Right Of The Texture and Quad

        GL11.glVertex3f(1.0f, 1.0f, -1.0f);   // Top Right Of The Texture and Quad

        // Bottom Face
        glColor3f(0f, 0f, 1f);
        GL11.glVertex3f(-1.0f, -1.0f, -1.0f);   // Top Right Of The Texture and Quad

        GL11.glVertex3f(1.0f, -1.0f, -1.0f);   // Top Left Of The Texture and Quad

        GL11.glVertex3f(1.0f, -1.0f, 1.0f);   // Bottom Left Of The Texture and Quad

        GL11.glVertex3f(-1.0f, -1.0f, 1.0f);   // Bottom Right Of The Texture and Quad

        // Right face
        glColor3f(0f, 0f, 1f);
        GL11.glVertex3f(1.0f, -1.0f, -1.0f);   // Bottom Right Of The Texture and Quad

        GL11.glVertex3f(1.0f, 1.0f, -1.0f);   // Top Right Of The Texture and Quad

        GL11.glVertex3f(1.0f, 1.0f, 1.0f);   // Top Left Of The Texture and Quad

        GL11.glVertex3f(1.0f, -1.0f, 1.0f);   // Bottom Left Of The Texture and Quad

        // Left Face
        glColor3f(0f, 0f, 1f);
        GL11.glVertex3f(-1.0f, -1.0f, -1.0f);   // Bottom Left Of The Texture and Quad

        GL11.glVertex3f(-1.0f, -1.0f, 1.0f);   // Bottom Right Of The Texture and Quad

        GL11.glVertex3f(-1.0f, 1.0f, 1.0f);   // Top Right Of The Texture and Quad

        GL11.glVertex3f(-1.0f, 1.0f, -1.0f);   // Top Left Of The Texture and Quad
        GL11.glEnd();
        glPopMatrix();


    }


}
