package com.shit.minecraft.util;

import org.lwjgl.util.vector.Vector3f;

/**
 * Created by eamondo2 on 9/24/14.
 */
public class MatrixRotations {
    //this will be a util file that contains methods for performing matrix rotations
    //on Vector3f objects.
    //Most important will be the UberRot function
    public static Vector3f uberRot(float theta, Vector3f PointA, Vector3f PointB, Vector3f Uv) {
        Vector3f returnVec = new Vector3f(0f, 0f, 0f);
        //calculate theta in radians
        float angrad = (float) Math.toRadians(theta);
        //calculate cosine and sine of theta
        float costheta = (float) Math.cos(angrad);
        float sintheta = (float) Math.sin(angrad);
        /*
        Rotation of PointB around PointA about axis represented by Uv
        R(theta) = costheta+uv.x^2(1-costheta) | uv.x*uv.y(1-costheta)-uv.z(sintheta) | uv.x*uv.z(1-costheta)+uv.y*sintheta
                   uv.y*uv.x(1-costheta)+uv.z*sintheta | costheta+uv.y^2(1-costheta) | uv.y*uv.z(1-costheta)-uv.x*sintheta
                   uv.z*uv.x(1-costheta)-uv.y(sintheta) | uv.z*uv.y(1-costheta)+uv.x(sintheta) | costheta+uv.z^2(1-costheta)
        */


        //ensure that Uv is normalized
        Uv.normalise(Uv);
        //translate to origin
        returnVec.x = PointB.getX();
        returnVec.y = PointB.getY();
        returnVec.z = PointB.getZ();
        returnVec.x -= PointA.x;
        returnVec.y -= PointA.y;
        returnVec.z -= PointA.z;
        //determine rotation vector
        float m00 = (costheta + (Uv.x * Uv.x) * (1 - costheta));
        //System.out.println("M00" + m00);
        float m01 = (Uv.x * Uv.y * (1 - costheta) - Uv.z * (sintheta));
        //System.out.println("M01" + m01);
        float m02 = (Uv.x * Uv.z * (1 - costheta) + Uv.y * sintheta);
        //System.out.println("M02" + m02);
        float m10 = (Uv.y * Uv.x * (1 - costheta) + Uv.z * sintheta);
        //System.out.println("M10" + m10);
        float m11 = (costheta + (Uv.y * Uv.y) * (1 - costheta));
        //System.out.println("M11" + m11);
        float m12 = (Uv.y * Uv.z * (1 - costheta) - Uv.x * sintheta);
        //System.out.println("M12" + m12);
        float m20 = (Uv.z * Uv.x * (1 - costheta) - Uv.y * (sintheta));
        //System.out.println("M20" + m20);
        float m21 = (Uv.z * Uv.y * (1 - costheta) + Uv.x * (sintheta));
        //System.out.println("M21" + m21);
        float m22 = (costheta + (Uv.z * Uv.z) * (1 - costheta));
        //System.out.println("M22" + m22);
        //multiply returnVec by Matrix
        //problem code
        float a = returnVec.getX();
        float b = returnVec.getY();
        float c = returnVec.getZ();

        returnVec.x = ((m00 * a) + (m01 * b) + (m02 * c));
        returnVec.y = ((m10 * a) + (m11 * b) + (m12 * c));
        returnVec.z = ((m20 * a) + (m21 * b) + (m22 * c));
        //translate back from origin
        returnVec.x += PointA.x;
        returnVec.y += PointA.y;
        returnVec.z += PointA.z;


        return returnVec;
    }

    public static Vector3f vectorRot(float theta, Vector3f Uv, Vector3f PointB) {

        Vector3f returnVec = new Vector3f(0f, 0f, 0f);
        Uv.normalise();
        PointB.normalise();
        //calculate theta in radians
        float angrad = (float) Math.toRadians(theta);
        //calculate cosine and sine of theta
        float costheta = (float) Math.cos(angrad);
        float sintheta = (float) Math.sin(angrad);
        /*
        Rotation of PointB around PointA about axis represented by Uv
        R(theta) = costheta+uv.x^2(1-costheta) | uv.x*uv.y(1-costheta)-uv.z(sintheta) | uv.x*uv.z(1-costheta)+uv.y*sintheta
                   uv.y*uv.x(1-costheta)+uv.z*sintheta | costheta+uv.y^2(1-costheta) | uv.y*uv.z(1-costheta)-uv.x*sintheta
                   uv.z*uv.x(1-costheta)-uv.y(sintheta) | uv.z*uv.y(1-costheta)+uv.x(sintheta) | costheta+uv.z^2(1-costheta)
        */


        //ensure that Uv is normalized
        Uv.normalise(Uv);
        //translate to origin
        returnVec.x = PointB.getX();
        returnVec.y = PointB.getY();
        returnVec.z = PointB.getZ();

        //determine rotation vector
        float m00 = (costheta + (Uv.x * Uv.x) * (1 - costheta));
        //System.out.println("M00" + m00);
        float m01 = (Uv.x * Uv.y * (1 - costheta) - Uv.z * (sintheta));
        //System.out.println("M01" + m01);
        float m02 = (Uv.x * Uv.z * (1 - costheta) + Uv.y * sintheta);
        //System.out.println("M02" + m02);
        float m10 = (Uv.y * Uv.x * (1 - costheta) + Uv.z * sintheta);
        //System.out.println("M10" + m10);
        float m11 = (costheta + (Uv.y * Uv.y) * (1 - costheta));
        //System.out.println("M11" + m11);
        float m12 = (Uv.y * Uv.z * (1 - costheta) - Uv.x * sintheta);
        //System.out.println("M12" + m12);
        float m20 = (Uv.z * Uv.x * (1 - costheta) - Uv.y * (sintheta));
        //System.out.println("M20" + m20);
        float m21 = (Uv.z * Uv.y * (1 - costheta) + Uv.x * (sintheta));
        //System.out.println("M21" + m21);
        float m22 = (costheta + (Uv.z * Uv.z) * (1 - costheta));
        //System.out.println("M22" + m22);
        //multiply returnVec by Matrix
        //problem code
        float a = returnVec.getX();
        float b = returnVec.getY();
        float c = returnVec.getZ();

        returnVec.x = ((m00 * a) + (m01 * b) + (m02 * c));
        returnVec.y = ((m10 * a) + (m11 * b) + (m12 * c));
        returnVec.z = ((m20 * a) + (m21 * b) + (m22 * c));
        //translate back from origin


        return returnVec;
    }

}
