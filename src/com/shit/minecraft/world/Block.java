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

    public static void render(float x, float y, float z, float scale, float zscale) {
        //translate to location, render mesh
        // Front Face
        glPushMatrix();
        glTranslatef(x, y, z);
        glBegin(GL_QUADS);
        glColor3f(0f, 0f, 1f);
        GL11.glVertex3f(-1.0f * scale, -1.0f * scale, 1.0f * zscale);   // Bottom Left Of The Texture and Quad

        GL11.glVertex3f(1.0f * scale, -1.0f * scale, 1.0f * zscale);   // Bottom Right Of The Texture and Quad

        GL11.glVertex3f(1.0f * scale, 1.0f * scale, 1.0f * zscale);   // Top Right Of The Texture and Quad

        GL11.glVertex3f(-1.0f * scale, 1.0f * scale, 1.0f * zscale);   // Top Left Of The Texture and Quad

        // Back Face
        glColor3f(0f, 0f, 1f);
        GL11.glVertex3f(-1.0f * scale, -1.0f * scale, -1.0f * zscale);   // Bottom Right Of The Texture and Quad

        GL11.glVertex3f(-1.0f * scale, 1.0f * scale, -1.0f * zscale);   // Top Right Of The Texture and Quad

        GL11.glVertex3f(1.0f * scale, 1.0f * scale, -1.0f * zscale);   // Top Left Of The Texture and Quad

        GL11.glVertex3f(1.0f * scale, -1.0f * scale, -1.0f * zscale);   // Bottom Left Of The Texture and Quad

        // Top Face
        glColor3f(0f, 1f, 0f);
        GL11.glVertex3f(-1.0f * scale, 1.0f * scale, -1.0f * zscale);   // Top Left Of The Texture and Quad

        GL11.glVertex3f(-1.0f * scale, 1.0f * scale, 1.0f * zscale);   // Bottom Left Of The Texture and Quad

        GL11.glVertex3f(1.0f * scale, 1.0f * scale, 1.0f * zscale);   // Bottom Right Of The Texture and Quad

        GL11.glVertex3f(1.0f * scale, 1.0f * scale, -1.0f * zscale);   // Top Right Of The Texture and Quad

        // Bottom Face
        glColor3f(0f, 0f, 1f);
        GL11.glVertex3f(-1.0f * scale, -1.0f * scale, -1.0f * zscale);   // Top Right Of The Texture and Quad

        GL11.glVertex3f(1.0f * scale, -1.0f * scale, -1.0f * zscale);   // Top Left Of The Texture and Quad

        GL11.glVertex3f(1.0f * scale, -1.0f * scale, 1.0f * zscale);   // Bottom Left Of The Texture and Quad

        GL11.glVertex3f(-1.0f * scale, -1.0f * scale, 1.0f * zscale);   // Bottom Right Of The Texture and Quad

        // Right face
        glColor3f(0f, 0f, 1f);
        GL11.glVertex3f(1.0f * scale, -1.0f * scale, -1.0f * zscale);   // Bottom Right Of The Texture and Quad

        GL11.glVertex3f(1.0f * scale, 1.0f * scale, -1.0f * zscale);   // Top Right Of The Texture and Quad

        GL11.glVertex3f(1.0f * scale, 1.0f * scale, 1.0f * zscale);   // Top Left Of The Texture and Quad

        GL11.glVertex3f(1.0f * scale, -1.0f * scale, 1.0f * zscale);   // Bottom Left Of The Texture and Quad

        // Left Face
        glColor3f(0f, 0f, 1f);
        GL11.glVertex3f(-1.0f * scale, -1.0f * scale, -1.0f * zscale);   // Bottom Left Of The Texture and Quad

        GL11.glVertex3f(-1.0f * scale, -1.0f * scale, 1.0f * zscale);   // Bottom Right Of The Texture and Quad

        GL11.glVertex3f(-1.0f * scale, 1.0f * scale, 1.0f * zscale);   // Top Right Of The Texture and Quad

        GL11.glVertex3f(-1.0f * scale, 1.0f * scale, -1.0f * zscale);   // Top Left Of The Texture and Quad
        GL11.glEnd();
        glPopMatrix();


    }

    public Vector3f getLocation() {
        return pos;
    }
    //iterate through points, return edge points

    public AABB getAABB() {
        return this.aaBB;
    }

    //needs to be retrofitted with an array of points, and an iterator.
    public void render() {
        this.render(pos.x, pos.y, pos.z, 10, 10);
    }


}
