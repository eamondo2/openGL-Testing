package com.shit.minecraft;

import com.shit.minecraft.util.MatrixRotations;
import com.shit.minecraft.util.Player;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.gluPerspective;

/**
 * Created by eamon_000(and more so REUBEN)`on 9/17/2014.
 * credit to Eamon, Adam, REUBEN, Zach
 *
 */
public class ShitMinecraft {
    public static boolean closeRequested = false;
    public static float angle = 0f;
    public static Player p = new Player(new Vector3f(0f, 0f, 1f), new Vector3f(1f, 0f, 0f), new Vector3f(0f, 1f, 0f), new Vector3f(0f, 0f, 1f), 0f, 0f, new Vector3f(0f, 0f, -10f), new Vector3f(-5f, 10f, 0f));
    public static void main(String[] args){
		System.out.println("Hello Woldr");
        System.out.println("Reuben cant spell...");
        System.out.println("Zach forgot a semicolon");
        Vector3f tv = new Vector3f(1f, 0f, 0f);
        Vector3f test = MatrixRotations.uberRot(90f, new Vector3f(0f, 0f, 0f), tv, new Vector3f(0f, 0f, 1f));
        System.out.println(test.x + " " + test.y + " " + test.z);
        run();
        cleanup();




	}

    public static void cleanup() {
        Display.destroy();
    }
    //initializes all openGL functions, sets display mode, and enables depth buffers etc
    public static void initGL(){
        glMatrixMode(GL11.GL_PROJECTION);
        glLoadIdentity();
        gluPerspective(50.0f, (float) Display.getWidth() / Display.getHeight(), 0.1f, 200f);
        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();
        glShadeModel(GL_SMOOTH);
        glClearColor(0f, 0f, 0f, 0f);
        glClearDepth(1.0f);
        glEnable(GL_DEPTH_TEST);
        glDepthFunc(GL_LEQUAL);
        glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);
        glTranslatef(0f, 0f, -10f);


    }
    //initializes display, sets the screen size, and grabs the mouse
    public static void init(){
        try {
            Display.setDisplayMode(new DisplayMode(640, 400));
            Display.setTitle("ShittyMinecraft");

            Display.create();
            Mouse.setGrabbed(true);
            Mouse.setCursorPosition(Display.getWidth() / 2, Display.getHeight() / 2);
            Mouse.getDX();
            Mouse.getDY();

        } catch (LWJGLException e) {
            e.printStackTrace();
        }

    }
    //contains main loop as well as the logic processes and ensuring that the application doesn't take all of the cpu cycles when in the background
    public static void run(){

        init();
        initGL();
        while (!closeRequested) {
            //every game loop, update the display, and swap the buffers
            //essentially pushed rendered scene to front, and refreshes the screen for
            //future updates

            Display.update();
            if (Display.isActive()) {
                logic();
                render();

            } else {
                //if the display is not active, then sleep for 100 milliseconds to give the
                //cpu time off.
                //if the display isn't active, then the user has
                //alt tabbed etc, and nothing needs to be done.
                //may fix in the future, to process logic in the background, especially if
                //multiplayer implemented.
                if (!Display.isDirty()) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                } else {
                    logic();
                    render();
                }
            }


        }

    }
    //calls the logical functions, calculating look position, change in movement
    //also calculates physics (eventually) and generally does all the heavy lifting
    //most of this will be calls to other functions
    public static void logic(){
	    Display.sync(60);
        camera();
        if (Display.isCloseRequested()) {
            closeRequested = true;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
            closeRequested = true;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_E)) {

        }
        if (angle < 360) angle += 1f;
        else angle = 0;

    }

    //camera control class
    public static void camera() {
        //needs to poll for mouse position changes, reset mouse position to middle each frame
        //update look position by rotating along appropriate axis by angle of delta mouse x and y
        //multiplied by the mouse sensitivity
        //calculate forward, backward, left right movement
        //multiply the speed in each direction by the unit vector for that direction (local axes)
        //add to the lookPos and the camPos

        //poll for mouse
        float mouseX = Mouse.getDX();
        float mouseY = Mouse.getDY();
        Mouse.setCursorPosition(Display.getWidth() / 2, Display.getHeight() / 2);
        p.cYaw((-mouseX * .05f));
        p.cPitch((mouseY * .05f));
        p.lookAt();


    }

    //self explanatory. update display, render out shapes etc.
    //will have two render modes, 2d and 3d, for the menus and the general world
    //also calls many other external functions to render out the scene

    public static void render(){
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        //glLoadIdentity();

        //glTranslatef(0f, 0f, -10f);

        //glRotatef(angle, 0f, 1f, 0f);
        //glRotatef(angle, 1f, 0f, 0f);

        glPushMatrix();
        glTranslatef(0f, 0f, -20f);
        glRotatef(angle, 0f, 1f, 0f);
        glRotatef(angle, 1f, 0f, 0f);
        GL11.glBegin(GL11.GL_QUADS);

        // Front Face
        glColor3f(0f, 1f, 0f);
        GL11.glVertex3f(-1.0f, -1.0f, 1.0f);   // Bottom Left Of The Texture and Quad

        GL11.glVertex3f(1.0f, -1.0f, 1.0f);   // Bottom Right Of The Texture and Quad

        GL11.glVertex3f(1.0f, 1.0f, 1.0f);   // Top Right Of The Texture and Quad

        GL11.glVertex3f(-1.0f, 1.0f, 1.0f);   // Top Left Of The Texture and Quad

        // Back Face
        glColor3f(1f, 0f, 0f);
        GL11.glVertex3f(-1.0f, -1.0f, -1.0f);   // Bottom Right Of The Texture and Quad

        GL11.glVertex3f(-1.0f, 1.0f, -1.0f);   // Top Right Of The Texture and Quad

        GL11.glVertex3f(1.0f, 1.0f, -1.0f);   // Top Left Of The Texture and Quad

        GL11.glVertex3f(1.0f, -1.0f, -1.0f);   // Bottom Left Of The Texture and Quad

        // Top Face
        glColor3f(0f, 0f, 1f);
        GL11.glVertex3f(-1.0f, 1.0f, -1.0f);   // Top Left Of The Texture and Quad

        GL11.glVertex3f(-1.0f, 1.0f, 1.0f);   // Bottom Left Of The Texture and Quad

        GL11.glVertex3f(1.0f, 1.0f, 1.0f);   // Bottom Right Of The Texture and Quad

        GL11.glVertex3f(1.0f, 1.0f, -1.0f);   // Top Right Of The Texture and Quad

        // Bottom Face
        glColor3f(1f, 0f, 0f);
        GL11.glVertex3f(-1.0f, -1.0f, -1.0f);   // Top Right Of The Texture and Quad

        GL11.glVertex3f(1.0f, -1.0f, -1.0f);   // Top Left Of The Texture and Quad

        GL11.glVertex3f(1.0f, -1.0f, 1.0f);   // Bottom Left Of The Texture and Quad

        GL11.glVertex3f(-1.0f, -1.0f, 1.0f);   // Bottom Right Of The Texture and Quad

        // Right face

        GL11.glVertex3f(1.0f, -1.0f, -1.0f);   // Bottom Right Of The Texture and Quad

        GL11.glVertex3f(1.0f, 1.0f, -1.0f);   // Top Right Of The Texture and Quad

        GL11.glVertex3f(1.0f, 1.0f, 1.0f);   // Top Left Of The Texture and Quad

        GL11.glVertex3f(1.0f, -1.0f, 1.0f);   // Bottom Left Of The Texture and Quad

        // Left Face

        GL11.glVertex3f(-1.0f, -1.0f, -1.0f);   // Bottom Left Of The Texture and Quad

        GL11.glVertex3f(-1.0f, -1.0f, 1.0f);   // Bottom Right Of The Texture and Quad

        GL11.glVertex3f(-1.0f, 1.0f, 1.0f);   // Top Right Of The Texture and Quad

        GL11.glVertex3f(-1.0f, 1.0f, -1.0f);   // Top Left Of The Texture and Quad
        GL11.glEnd();
        glPopMatrix();

        glBegin(GL_QUADS);
        glVertex3f(-10f, -10f, -10f);
        glVertex3f(10f, -10f, -10f);
        glVertex3f(10f, -10f, 10f);
        glVertex3f(-10f, -10f, 10f);
        glEnd();
        glPushMatrix();

        glBegin(GL_LINES);
        glColor3f(0f, 0f, 1f);
        glVertex3f(-10f, 0f, 0f);
        glVertex3f(10f, 0f, 0f);
        glColor3f(0f, 1f, 0f);
        glVertex3f(0f, 10f, 0f);
        glVertex3f(0f, -10f, 0f);
        glColor3f(1f, 0f, 0f);
        glVertex3f(0f, 0f, -10f);
        glVertex3f(0f, 0f, 10f);
        glEnd();
        glPopMatrix();
    }

}
