package com.shit.minecraft.world;

import com.shit.minecraft.util.AABB;
import org.lwjgl.util.vector.Vector3f;

/**
 * Created by eamondo2 on 9/24/14.
 */
public class Block {
    private AABB aaBB;
    private Vector3f pos;

    //must store location, mesh (at this point just a simple cube shape)
    //returns the AABB for the block

    public Vector3f getLocation() {
        return pos;
    }

    public AABB getAABB() {
        return this.aaBB;
    }


    public void render() {
        //translate to location, render mesh


    }


}
