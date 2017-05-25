package cz.evcino.pacman.utils;

import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.glBufferData;

import java.io.IOException;
import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cz.evcino.pacman.ApplicationConstants;
import cz.evcino.pacman.ObjLoaderFullFaces;

public class ModelUtils {

    protected static final Logger logger = LoggerFactory.getLogger(ModelUtils.class);

    public static ObjLoaderFullFaces loadModelData(String filename, int modelBuffer) {
        ObjLoaderFullFaces model = new ObjLoaderFullFaces(ApplicationConstants.PATH_TO_MODELS + filename);
        try {
            model.loadModel();
        } catch (IOException ex) {
            logger.error("it's broken", ex);
            System.exit(1);
        }
        int dotLength = 3 * 8 * model.getTriangleCount();
        FloatBuffer modelData = BufferUtils.createFloatBuffer(dotLength);
        for (int f = 0; f < model.getTriangleCount(); f++) {
            int[] pi = model.getVertexIndices().get(f);
            int[] ni = model.getNormalIndices().get(f);
            int[] ti = model.getTexcoordIndices().get(f);
            for (int i = 0; i < 3; i++) {
                float[] position = model.getVertices().get(pi[i]);
                modelData.put(position);
                float[] normal = model.getNormals().get(ni[i]);
                modelData.put(normal);
               // if (!model.isNoFaceNormals()) {
                    float[] texcoord = model.getTexcoords().get(ti[i]);
                    modelData.put(texcoord);
               // } else {
                 //   modelData.put(-1);
                //}

            }
        }
        modelData.rewind();
        glBindBuffer(GL_ARRAY_BUFFER, modelBuffer);
        glBufferData(GL_ARRAY_BUFFER, modelData, GL_STATIC_DRAW);
        return model;
    }

}
