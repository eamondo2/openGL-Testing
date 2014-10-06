package com.shit.minecraft;

import com.shit.minecraft.physics.rect;
import com.shit.minecraft.util.Player;
import com.shit.minecraft.world.Block;
import com.shit.minecraft.world.Map;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;

import java.util.ArrayList;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.gluOrtho2D;
import static org.lwjgl.util.glu.GLU.gluPerspective;

/**
 * Created by eamon_000(and more so REUBEN)`on 9/17/2014.
 * credit to Eamon, Adam, REUBEN, Zach
 *
 */
public class ShitMinecraft {
    public static boolean[] keyLifted = new boolean[256];
    public static long time;
    public static double delta;
    public static float timescale = 1f;
    public static boolean closeRequested = false;
    public static boolean nav = false;
    public static ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
    public static float FOV = 50f;
    public static float angle = 0f;
    public static Player p = new Player(new Vector3f(0f, 0f, 1f), new Vector3f(1f, 0f, 0f), new Vector3f(0f, 1f, 0f), new Vector3f(0f, 0f, 1f), 0f, 0f, new Vector3f(-5f, 10f, -10f), new Vector3f(-5f, 10f, 0f));
    public static boolean isPaused = false;
    public static rect r;
    public static void main(String[] args){
		System.out.println("Hello Woldr");
        System.out.println("Reuben can't spell...");
        System.out.println("Zach forgot a semicolon");

        Vector3f v = new Vector3f(1f, 1f, 0f);
        v = normalize(v);
        System.out.println("NORM" + v);


        run();
        cleanup();




	}

    public static Vector3f normalize(Vector3f a) {
        float mag = a.x + a.y + a.z;
        Vector3f returnVec = new Vector3f(a.x / mag, a.y / mag, a.z / mag);
        return returnVec;

    }
    public static void deltaT() {
        //newtime-oldtime
        //delta*(whatever operation) = time scaled operation
        long t = Sys.getTime() * 1000;
        t /= Sys.getTimerResolution();
        delta = t - time;
        time = t;
        delta = delta * timescale;
        System.out.println("DELTA" + delta);
        System.out.println("SCALE" + timescale);


    }

    public static void changeTimeScale(float t) {
        timescale = t;
    }
    public static void cleanup() {
        Display.destroy();
    }


    //initializes all openGL functions, sets display mode, and enables depth buffers etc
    public static void initGL(){
        glMatrixMode(GL11.GL_PROJECTION);
        glLoadIdentity();
        gluPerspective(FOV, (float) Display.getWidth() / Display.getHeight(), 0.1f, 200f);
        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();
        glShadeModel(GL_SMOOTH);
        glClearColor(0f, 0f, 0f, 0f);
        glClearDepth(1.0f);
        glEnable(GL_DEPTH_TEST);
        glDepthFunc(GL_LEQUAL);
        glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);
        glTranslatef(0f, 0f, -10f);
        Vector3f[] verts = new Vector3f[4];
        verts[0] = new Vector3f(-10f, 10f, 1f);
        verts[1] = new Vector3f(10f, 10f, 1f);
        verts[2] = new Vector3f(10f, -10f, 1f);
        verts[3] = new Vector3f(-10f, -10f, 1f);
        r = new rect(new Vector3f(0f, 0f, 0f), 15f, verts, new Vector3f(.5f, .5f, 0f), 10f);


    }
    //initializes display, sets the screen size, and grabs the mouse
    public static void pushOrtho() {
        glMatrixMode(GL_PROJECTION);
        glPushMatrix();
        glLoadIdentity();
        gluOrtho2D(-(Display.getWidth() / 2), Display.getWidth() / 2, Display.getHeight() / 2, -(Display.getHeight() / 2));
        glMatrixMode(GL_MODELVIEW);
        glPushMatrix();
        glLoadIdentity();
        glDisable(GL_DEPTH_TEST);


    }

    public static void popOrtho() {
        glEnable(GL_DEPTH_TEST);
        glMatrixMode(GL_PROJECTION);
        glPopMatrix();
        glMatrixMode(GL_MODELVIEW);
        glPopMatrix();


    }
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
            //provides delta T between last frame and this one
            deltaT();
            if (Display.isActive()) {

                logic();

                if (!isPaused) render();
                else menuRender();


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
                    if (!isPaused) render();
                    else menuRender();
                }
            }


        }

    }

    public static void menuRender() {
        //2D rendering for menu

        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        pushOrtho();


        r.update();
        r.render();


        popOrtho();



    }

    //paused key is index 0
    public static void pausedInput() {

        if (Keyboard.isKeyDown(Keyboard.KEY_P) && keyLifted[0]) {
            isPaused = !isPaused;
            Mouse.setGrabbed(true);

        }
        if (!Keyboard.isKeyDown(Keyboard.KEY_P)) {
            keyLifted[0] = true;
        } else {
            keyLifted[0] = false;
        }
        if (Display.isCloseRequested()) {
            closeRequested = true;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
            closeRequested = true;
        }
    }

    public static void menuLogic() {

    }

    public static void input() {
        if (Display.isCloseRequested()) {
            closeRequested = true;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
            closeRequested = true;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
            p.forward((float) (-1 * delta));
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
            p.forward((float) (1 * delta));
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
            p.strafe((float) (-1 * delta));
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
            p.strafe((float) (1 * delta));
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_N)) {
            nav = true;

        }

        if (Mouse.isButtonDown(0)) {
            //fire a projectile
            Projectile f = new Projectile(new Vector3f(p.getlook().x, p.getlook().y, p.getlook().z), .5f, 50000, new Vector3f(p.getPos().x, p.getPos().y, p.getPos().z), new Vector3f(p.getXvec().x, p.getXvec().y, p.getXvec().z), new Vector3f(p.getYvec().x, p.getYvec().y, p.getYvec().z), p.getPitch(), p.getYaw());
            //f.update();
            projectiles.add(f);
            System.out.println("PROJECTILE FIRED");

        }
        if (Keyboard.isKeyDown(Keyboard.KEY_P) && keyLifted[0]) {
            isPaused = !isPaused;
            Mouse.setGrabbed(false);

        }
        if (!Keyboard.isKeyDown(Keyboard.KEY_P)) {
            keyLifted[0] = true;
        } else {
            keyLifted[0] = false;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
            p.elevate((float) (1 * delta));
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
            p.elevate((float) (-1 * delta));
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_L)) {

        }
        if (Keyboard.isKeyDown(Keyboard.KEY_B) && !Keyboard.isKeyDown(Keyboard.KEY_LCONTROL)) {
            changeTimeScale(timescale - .005f);

        }
        if (Keyboard.isKeyDown(Keyboard.KEY_B) && Keyboard.isKeyDown(Keyboard.KEY_LCONTROL)) {
            changeTimeScale(timescale + .005f);
        }
    }

    //calls the logical functions, calculating look position, change in movement
    //also calculates physics (eventually) and generally does all the heavy lifting
    //most of this will be calls to other functions
    public static void logic() {
        Display.sync(60);
        if (!isPaused) {
            camera();
            input();
            projectileLogic();

        } else {
            pausedInput();
            menuLogic();

        }


    }

    //iterates through projectiles, updates position/passes control to projectiles
    public static void projectileLogic() {
        ArrayList<Projectile> tmp = new ArrayList<Projectile>();
        for (Projectile p : projectiles) {
            p.update();
            if (p.destroy) {
                tmp.add(p);
            }
        }
        for (Projectile p : tmp) {
            projectiles.remove(p);
        }


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
    public static void navLine() {
        if (nav) {
            glBegin(GL_LINES);
            glColor3f(.5f, 0f, .5f);
            glVertex3f(p.getPos().x, p.getPos().y - 1, p.getPos().z);
            glVertex3f(0f, 0f, 0f);
            glEnd();
            nav = false;
        }
    }
    public static void render(){
        //not important code
        if (angle < 360) angle += 10f * (delta);
        else angle = 0;
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        for (Projectile p : projectiles) {
            p.render();
        }
        navLine();
        Block b = new Block(new Vector3f(10f, 10f, 0f));
        b.render();
        Map m = new Map(1, 50, 50);
        m.render();


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
