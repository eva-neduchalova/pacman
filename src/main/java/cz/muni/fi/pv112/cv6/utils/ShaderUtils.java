package cz.muni.fi.pv112.cv6.utils;

import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL20.GL_COMPILE_STATUS;
import static org.lwjgl.opengl.GL20.GL_FRAGMENT_SHADER;
import static org.lwjgl.opengl.GL20.GL_LINK_STATUS;
import static org.lwjgl.opengl.GL20.GL_VERTEX_SHADER;
import static org.lwjgl.opengl.GL20.glAttachShader;
import static org.lwjgl.opengl.GL20.glCompileShader;
import static org.lwjgl.opengl.GL20.glCreateProgram;
import static org.lwjgl.opengl.GL20.glCreateShader;
import static org.lwjgl.opengl.GL20.glGetProgramInfoLog;
import static org.lwjgl.opengl.GL20.glGetProgrami;
import static org.lwjgl.opengl.GL20.glGetShaderInfoLog;
import static org.lwjgl.opengl.GL20.glGetShaderi;
import static org.lwjgl.opengl.GL20.glLinkProgram;
import static org.lwjgl.opengl.GL20.glShaderSource;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import cz.muni.fi.pv112.cv6.ApplicationConstants;

public class ShaderUtils {
    
    public static int loadProgramByShaderMainName(String name) throws IOException {
        return loadProgram(String.join("", ApplicationConstants.PATH_TO_SHADERS, name, ".vs.glsl"),
                    String.join("", ApplicationConstants.PATH_TO_SHADERS, name, ".fs.glsl"));
    }
    


    private static int loadProgram(String vertexShaderFile, String fragmentShaderFile) throws IOException {
        // load vertex and fragment shaders (GLSL)
        int vs = loadShader(vertexShaderFile, GL_VERTEX_SHADER);
        int fs = loadShader(fragmentShaderFile, GL_FRAGMENT_SHADER);

        // create GLSL program, attach shaders and compile it
        int program = glCreateProgram();
        glAttachShader(program, vs);
        glAttachShader(program, fs);
        glLinkProgram(program);

        int status = glGetProgrami(program, GL_LINK_STATUS);
        if (status == GL_FALSE) {
            String log = glGetProgramInfoLog(program);
            System.err.println(log);
        }

        return program;

    }
    
    private static int loadShader(String filename, int shaderType) throws IOException {
        String source = readAllFromResource(filename);
        int shader = glCreateShader(shaderType);

        // create and compile GLSL shader
        glShaderSource(shader, source);
        glCompileShader(shader);

        // check GLSL shader compile status
        int status = glGetShaderi(shader, GL_COMPILE_STATUS);
        if (status == GL_FALSE) {
            String log = glGetShaderInfoLog(shader);
            System.err.println(log);
        }

        return shader;
    }
    
    private static String readAllFromResource(String resource) throws IOException {
        return FileUtils.readFileToString(new File(resource));
    }

}
