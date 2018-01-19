package com.eamondo2.gltest.world;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created by eamondo2 on 9/24/14.
 */
public class Map {
    private static int height;
    private static int width;
    private static int depth;
    //needs to have an array of blocks.
    //should it be chunks like gltest? or some other method?
    //chunk based works best, as you can update chunks in a radius around the player
    //adds for more efficiency
    /*
    radius loaded chunk segments, has to be top down hierarchy to allow for physics calculations
    down the stack
         */
    //test case, back left corner start, looking down from top, back left corner top left, columns down, rows across, layers descending

    public static Vector3f[][][] mapArray = new Vector3f[height][width][depth];

    public Map(int height, int width, int depth) {
        this.height = height;
        this.width = width;
        this.depth = depth;
    }

    public static void render() {
        //vertical layers
        for (int h = 0; h <= height; h += 3) {
            //left to right columns
            for (int w = 0; w <= width; w += 3) {
                //back to front rows
                for (int d = 0; d <= depth; d += 3) {
                    //placeholder render function
                    //mapArray[h][w][d] = new Vector3f(h, w, d);
                    glPushMatrix();
                    glTranslatef(0 - w, 0 - h, 0 - d);
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

        }


    }


}
