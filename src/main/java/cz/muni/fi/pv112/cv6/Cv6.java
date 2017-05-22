package cz.muni.fi.pv112.cv6;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.GLFW_CONTEXT_VERSION_MAJOR;
import static org.lwjgl.glfw.GLFW.GLFW_CONTEXT_VERSION_MINOR;
import static org.lwjgl.glfw.GLFW.GLFW_FALSE;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_A;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_ESCAPE;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_F;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_L;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_T;
import static org.lwjgl.glfw.GLFW.GLFW_MOUSE_BUTTON_1;
import static org.lwjgl.glfw.GLFW.GLFW_MOUSE_BUTTON_2;
import static org.lwjgl.glfw.GLFW.GLFW_OPENGL_CORE_PROFILE;
import static org.lwjgl.glfw.GLFW.GLFW_OPENGL_PROFILE;
import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;
import static org.lwjgl.glfw.GLFW.GLFW_RESIZABLE;
import static org.lwjgl.glfw.GLFW.GLFW_TRUE;
import static org.lwjgl.glfw.GLFW.GLFW_VISIBLE;
import static org.lwjgl.glfw.GLFW.glfwCreateWindow;
import static org.lwjgl.glfw.GLFW.glfwDefaultWindowHints;
import static org.lwjgl.glfw.GLFW.glfwDestroyWindow;
import static org.lwjgl.glfw.GLFW.glfwGetPrimaryMonitor;
import static org.lwjgl.glfw.GLFW.glfwGetVideoMode;
import static org.lwjgl.glfw.GLFW.glfwGetWindowSize;
import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSetCursorPosCallback;
import static org.lwjgl.glfw.GLFW.glfwSetErrorCallback;
import static org.lwjgl.glfw.GLFW.glfwSetKeyCallback;
import static org.lwjgl.glfw.GLFW.glfwSetMouseButtonCallback;
import static org.lwjgl.glfw.GLFW.glfwSetWindowPos;
import static org.lwjgl.glfw.GLFW.glfwSetWindowShouldClose;
import static org.lwjgl.glfw.GLFW.glfwSetWindowSizeCallback;
import static org.lwjgl.glfw.GLFW.glfwShowWindow;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.glfw.GLFW.glfwSwapInterval;
import static org.lwjgl.glfw.GLFW.glfwTerminate;
import static org.lwjgl.glfw.GLFW.glfwWindowHint;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL11.GL_FILL;
import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL11.GL_FRONT_AND_BACK;
import static org.lwjgl.opengl.GL11.GL_LINE;
import static org.lwjgl.opengl.GL11.GL_LINEAR;
import static org.lwjgl.opengl.GL11.GL_LINEAR_MIPMAP_LINEAR;
import static org.lwjgl.opengl.GL11.GL_LINES;
import static org.lwjgl.opengl.GL11.GL_RGB;
import static org.lwjgl.opengl.GL11.GL_RGBA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MAG_FILTER;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MIN_FILTER;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_WRAP_S;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_WRAP_T;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.GL_TRIANGLE_STRIP;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_BYTE;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glDrawArrays;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glGenTextures;
import static org.lwjgl.opengl.GL11.glLineWidth;
import static org.lwjgl.opengl.GL11.glPolygonMode;
import static org.lwjgl.opengl.GL11.glTexImage2D;
import static org.lwjgl.opengl.GL11.glTexParameteri;
import static org.lwjgl.opengl.GL11.glViewport;
import static org.lwjgl.opengl.GL12.GL_BGR;
import static org.lwjgl.opengl.GL12.GL_BGRA;
import static org.lwjgl.opengl.GL12.GL_CLAMP_TO_EDGE;
import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.glActiveTexture;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.glBufferData;
import static org.lwjgl.opengl.GL15.glBufferSubData;
import static org.lwjgl.opengl.GL15.glGenBuffers;
import static org.lwjgl.opengl.GL20.GL_COMPILE_STATUS;
import static org.lwjgl.opengl.GL20.GL_FRAGMENT_SHADER;
import static org.lwjgl.opengl.GL20.GL_LINK_STATUS;
import static org.lwjgl.opengl.GL20.GL_VERTEX_SHADER;
import static org.lwjgl.opengl.GL20.glAttachShader;
import static org.lwjgl.opengl.GL20.glCompileShader;
import static org.lwjgl.opengl.GL20.glCreateProgram;
import static org.lwjgl.opengl.GL20.glCreateShader;
import static org.lwjgl.opengl.GL20.glDrawBuffers;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glGetAttribLocation;
import static org.lwjgl.opengl.GL20.glGetProgramInfoLog;
import static org.lwjgl.opengl.GL20.glGetProgrami;
import static org.lwjgl.opengl.GL20.glGetShaderInfoLog;
import static org.lwjgl.opengl.GL20.glGetShaderi;
import static org.lwjgl.opengl.GL20.glGetUniformLocation;
import static org.lwjgl.opengl.GL20.glLinkProgram;
import static org.lwjgl.opengl.GL20.glShaderSource;
import static org.lwjgl.opengl.GL20.glUniform1f;
import static org.lwjgl.opengl.GL20.glUniform1i;
import static org.lwjgl.opengl.GL20.glUniform3f;
import static org.lwjgl.opengl.GL20.glUniformMatrix4fv;
import static org.lwjgl.opengl.GL20.glUseProgram;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.GL_COLOR_ATTACHMENT0;
import static org.lwjgl.opengl.GL30.GL_DEPTH24_STENCIL8;
import static org.lwjgl.opengl.GL30.GL_DEPTH_STENCIL;
import static org.lwjgl.opengl.GL30.GL_DEPTH_STENCIL_ATTACHMENT;
import static org.lwjgl.opengl.GL30.GL_FRAMEBUFFER;
import static org.lwjgl.opengl.GL30.GL_FRAMEBUFFER_COMPLETE;
import static org.lwjgl.opengl.GL30.GL_UNSIGNED_INT_24_8;
import static org.lwjgl.opengl.GL30.glBindBufferBase;
import static org.lwjgl.opengl.GL30.glBindFramebuffer;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glCheckFramebufferStatus;
import static org.lwjgl.opengl.GL30.glFramebufferTexture2D;
import static org.lwjgl.opengl.GL30.glGenFramebuffers;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;
import static org.lwjgl.opengl.GL30.glGenerateMipmap;
import static org.lwjgl.opengl.GL31.GL_UNIFORM_BUFFER;
import static org.lwjgl.opengl.GL31.glDrawArraysInstanced;
import static org.lwjgl.opengl.GL31.glGetUniformBlockIndex;
import static org.lwjgl.opengl.GL31.glUniformBlockBinding;
import static org.lwjgl.system.MemoryStack.stackPush;
import static org.lwjgl.system.MemoryUtil.NULL;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.Arrays;
import java.util.Random;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.BufferUtils;
import org.lwjgl.Version;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryStack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;




public class Cv6 {
    
    protected static final Logger logger = LoggerFactory.getLogger(Cv6.class);

    private static final int NUMBER_OF_INSTANCES = StringUtils.countMatches(Arrays.toString(ApplicationConstants.MAZE_DEFINITION_STRING), "X"); // 100;

    private static final int SIZEOF_AXES_VERTEX = 6 * Float.BYTES;
    private static final int COLOR_OFFSET = 3 * Float.BYTES;

    private static final float AXES[] = {
            // .. position .......... color ....
            // x axis
            1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f,
            // y axis
            0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f,
            // z axis
            0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f };

    private static final int SIZEOF_MODEL_VERTEX = 8 * Float.BYTES;
    private static final int NORMAL_OFFSET = 3 * Float.BYTES;
    private static final int TEXCOORD_OFFSET = 6 * Float.BYTES;

    private Camera camera;

    // the window handle
    private long window;

    // window size
    private int width;
    private int height;
    private boolean resized = false;

    // animation
    private boolean animate = false;
    private float t = 0f;

    // rendering mode
    private int mode = GL_FILL;

    // model
    private ObjLoader cube;
    private ObjLoader dot;

    // our OpenGL resources
    private int axesBuffer;
    private int cubeBuffer;
    private int ghostBuffer;
    private int dotBuffer;
    private int axesArray;
    private int cubeArray;
    private int dotArray;

    // our GLSL resources
    private int axesProgram;
    private int axesAspectUniformLoc;
    private int axesLengthUniformLoc;
    private int axesMvpUniformLoc;

    private int woodTexture;
    private int cubeWoodTexLoc;

    private int dotProgram;

    private int dotMvpLoc;
    private int dotNLoc;
    private int dotModelLoc;

    private int dotViewLoc;
    private int dotProjectionLoc;

    private int modelLightPositionLoc;
    private int modelLightAmbientColorLoc;
    private int modelLightDiffuseColorLoc;
    private int modelLightSpecularColorLoc;

    private int dotEyePositionLoc;

    private int cubeProgram;
    private int cubeViewLoc;
    private int cubeProjectionLoc;
    private int cubeEyePositionLoc;

    private final Matrix4f[] modelMatrices = new Matrix4f[NUMBER_OF_INSTANCES];
    private final Vector4f[] dotColors = new Vector4f[NUMBER_OF_INSTANCES];

    FloatBuffer dotDataBuffer = BufferUtils.createFloatBuffer(NUMBER_OF_INSTANCES * (16 + 4));
    FloatBuffer cubeDataBuffer = BufferUtils.createFloatBuffer(NUMBER_OF_INSTANCES * 16);

    // Fullscreen quad
    // Task 1: make sure you understand why we need them
    private static final float[] SCREEN_QUAD_DATA = {
            // position (2D)
            -1.0f, 1.0f, -1.0f, -1.0f, 1.0f, 1.0f, 1.0f, -1.0f };
    private int screenQuadBuffer;
    private int screenQuadArray;

    // Framebuffer object
    private int fboWidth, fboHeight;
    private int fboColorTexture;
    private int fboDepthStencilTexture;
    private int fbo;

    // Postprocess shader
    private int postprocessProgram;
    private final int SCENE_TEXTURE_UNIT = 0;
    private int sceneTexLoc;
    private int animationTimeLoc;

    // Light data uniform buffer
    private final int LIGHT_DATA_INDEX = 0;
    private int lightDataUBO;

    // Light data uniform buffer
    private final int DOT_DATA_INDEX = 1;
    private int dotDataUBO;

    // Light data uniform buffer
    private final int CUBE_DATA_INDEX = 1;
    private int cubeDataUBO;

    public static void main(String[] args) {
        new Cv6().run();
    }

    public void run() {
        System.out.println("Hello LWJGL " + Version.getVersion() + "!");

        camera = new Camera();

        initGLFW();
        loop();

        // Free the window callbacks and destroy the window
        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);

        // Terminate GLFW and free the error callback
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

    private void initGLFW() {
        // Setup an error callback. The default implementation
        // will print the error message in System.err.
        GLFWErrorCallback.createPrint(System.err).set();

        // Initialize GLFW. Most GLFW functions will not work before doing this.
        if (!glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }

        // Configure GLFW
        glfwDefaultWindowHints(); // optional, the current window hints are already the default
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden after creation
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); // the window will be resizable
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3); // request the OpenGL 3.3 core profile context
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);

        // set initial width and height
        width = 640;
        height = 480;

        // Create the window
        window = glfwCreateWindow(width, height, "Hello World!", NULL, NULL);
        if (window == NULL) {
            throw new RuntimeException("Failed to create the GLFW window");
        }

        // Setup a key callback. It will be called every time a key is pressed, repeated or released.
        glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
            if (action == GLFW_RELEASE) {
                switch (key) {
                case GLFW_KEY_ESCAPE:
                    glfwSetWindowShouldClose(window, true); // We will detect this in the rendering loop
                    break;
                case GLFW_KEY_A:
                    animate = !animate;
                    break;
                case GLFW_KEY_T:
                    // TODO toggle fullscreen
                    break;
                case GLFW_KEY_L:
                    mode = GL_LINE;
                    break;
                case GLFW_KEY_F:
                    mode = GL_FILL;
                    break;
                }
            }
        });

        glfwSetMouseButtonCallback(window, (window, button, action, mods) -> {
            if (action == GLFW_PRESS) {
                if (button == GLFW_MOUSE_BUTTON_1) {
                    camera.updateMouseButton(Camera.Button.LEFT, true);
                } else if (button == GLFW_MOUSE_BUTTON_2) {
                    camera.updateMouseButton(Camera.Button.RIGHT, true);
                }
            } else if (action == GLFW_RELEASE) {
                if (button == GLFW_MOUSE_BUTTON_1) {
                    camera.updateMouseButton(Camera.Button.LEFT, false);
                } else if (button == GLFW_MOUSE_BUTTON_2) {
                    camera.updateMouseButton(Camera.Button.RIGHT, false);
                }
            }
        });

        glfwSetCursorPosCallback(window, (window, xpos, ypos) -> {
            camera.updateMousePosition(xpos, ypos);
        });

     // add window size callback
        glfwSetWindowSizeCallback(window, (window, width, height) -> {
            this.width = width;
            this.height = height;
            resized = true;
        });

        // Get the thread stack and push a new frame
        try (MemoryStack stack = stackPush()) {
            IntBuffer pWidth = stack.mallocInt(1); // int*
            IntBuffer pHeight = stack.mallocInt(1); // int*

            // Get the window size passed to glfwCreateWindow
            glfwGetWindowSize(window, pWidth, pHeight);

            // Get the resolution of the primary monitor
            GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

            // Center the window
            glfwSetWindowPos(window, (vidmode.width() - pWidth.get(0)) / 2, (vidmode.height() - pHeight.get(0)) / 2);
        } // the stack frame is popped automatically

        // Make the OpenGL context current
        glfwMakeContextCurrent(window);
        // Enable v-sync
        glfwSwapInterval(1);

        // Make the window visible
        glfwShowWindow(window);

        // This line is critical for LWJGL's interoperation with GLFW's
        // OpenGL context, or any context that is managed externally.
        // LWJGL detects the context that is current in the current thread,
        // creates the GLCapabilities instance and makes the OpenGL
        // bindings available for use.
        GL.createCapabilities();
    }

    private void loop() {
        // Prepare data for rendering
        init();

        // Task 7: add initUniformBuffers method call
        initUniformBuffers();

        // Task 1: add initPostprocessing method call
        initPostprocessing();

        // Run the rendering loop until the user has attempted to close
        // the window or has pressed the ESCAPE key.
        while (!glfwWindowShouldClose(window)) {
            render();

            glfwSwapBuffers(window); // swap the color buffers

            // Poll for window events. The key callback above will only be
            // invoked during this call.
            glfwPollEvents();
        }
    }
    
    private void loadGhost() throws IOException {
        ObjLoader ghost = new ObjLoader(ApplicationConstants.PATH_TO_MODELS + "ghost.obj");
        ghost.load();
        int ghostBufferLength = 3 * 8 * ghost.getTriangleCount();
        FloatBuffer ghostData = BufferUtils.createFloatBuffer(ghostBufferLength);
        for (int f = 0; f < ghost.getTriangleCount(); f++) {
            int[] pi = ghost.getVertexIndices().get(f);
            int[] ni = ghost.getNormalIndices().get(f);
            int[] ti = ghost.getTexcoordIndices().get(f);
            for (int i = 0; i < 3; i++) {
                float[] position = ghost.getVertices().get(pi[i]);
                float[] normal = ghost.getNormals().get(ni[i]);
                float[] texcoord = ghost.getTexcoords().get(ti[i]);
                ghostData.put(position);
                ghostData.put(normal);
                ghostData.put(texcoord);
            }
        }
        ghostData.rewind();
        glBindBuffer(GL_ARRAY_BUFFER, ghostBuffer);
        glBufferData(GL_ARRAY_BUFFER, ghostData, GL_STATIC_DRAW);
    }

    private void init() {
        // empty scene color
        glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        glLineWidth(3.0f); // makes lines thicker

        glEnable(GL_DEPTH_TEST);

        // load GLSL program (vertex, fragment shaders) and textures
        try {
            axesProgram = loadProgram( ApplicationConstants.PATH_TO_SHADERS + "axes.vs.glsl", ApplicationConstants.PATH_TO_SHADERS + "axes.fs.glsl");
            dotProgram = loadProgram(ApplicationConstants.PATH_TO_SHADERS + "dot.vs.glsl", ApplicationConstants.PATH_TO_SHADERS + "dot.fs.glsl");
            cubeProgram = loadProgram(ApplicationConstants.PATH_TO_SHADERS + "cube.vs.glsl", ApplicationConstants.PATH_TO_SHADERS + "cube.fs.glsl");
            woodTexture = loadTexture(ApplicationConstants.PATH_TO_TEXTURES + "wood.jpg");
        } catch (IOException ex) {
            logger.error("it's broken", ex);
            System.exit(1);
        }

        // get uniform locations
        // axes program uniforms
        axesAspectUniformLoc = glGetUniformLocation(axesProgram, "aspect");
        axesLengthUniformLoc = glGetUniformLocation(axesProgram, "len");
        axesMvpUniformLoc = glGetUniformLocation(axesProgram, "MVP");

        cubeProjectionLoc = glGetUniformLocation(cubeProgram, "projection");
        cubeViewLoc = glGetUniformLocation(cubeProgram, "view");
        cubeEyePositionLoc = glGetUniformLocation(cubeProgram, "eyePosition");
        cubeWoodTexLoc = glGetUniformLocation(cubeProgram, "woodTex");

        dotMvpLoc = glGetUniformLocation(dotProgram, "MVP");
        dotNLoc = glGetUniformLocation(dotProgram, "N");
        dotModelLoc = glGetUniformLocation(dotProgram, "model");

        dotProjectionLoc = glGetUniformLocation(dotProgram, "projection");
        dotViewLoc = glGetUniformLocation(dotProgram, "view");

        modelLightPositionLoc = glGetUniformLocation(dotProgram, "lightPosition");
        modelLightAmbientColorLoc = glGetUniformLocation(dotProgram, "lightAmbientColor");
        modelLightDiffuseColorLoc = glGetUniformLocation(dotProgram, "lightDiffuseColor");
        modelLightSpecularColorLoc = glGetUniformLocation(dotProgram, "lightSpecularColor");

        dotEyePositionLoc = glGetUniformLocation(dotProgram, "eyePosition");

        // Task 5: prepare 100 model matrices so that the objects are in a 10×10 grid, centered around [0,1,0] in world coordinates
        // you can use the prepared one dimensional array modelMatrices
        // protip: there's a constant NUMBER_OF_INSTANCES, you can use it in your for cycle :)
        // Task 6: assign a different color to each dot, use method randomColor()
        // again, use the prepared array, dotColors
        // Task 9: store the model matrix and colors to dotDataBuffer;
        // you will want to you Matrix4f.get(int index, FloatBuffer buffer)
        // and Vector4f.get(int index, FloatBuffer buffer)
        // Task 10: prepare 100 model matrices so that the objects are in a 10×10 grid, centered around [0,0,0] in world coordinates
        // and store them into the cubeDataBuffer
        
        
        int cubeCounter = 0;
        int dotsCounter = 0;
        int x = 0;
        int y = 0;
        for (String row : ApplicationConstants.MAZE_DEFINITION_STRING) {
            x = 0;
            for (char c : row.toCharArray()) {
                logger.info(String.format("_x, y, = %s, %s.", x, y));
                if ('X' == c) {
                    // logger.info(String.format("x, y, = %s, %s. Drawing cube", x, y));
                    // Vector3f cubeLocation = new Vector3f((i % 10 - 4.5f) * 5, 0, (i / 10 - 4.5f) * 5);
                    Vector3f cubeLocation = new Vector3f((x - 4.5f) * 2, (y - 4.5f) * 2, 0);
                    new Matrix4f().translate(cubeLocation).get(cubeCounter * 16, cubeDataBuffer);
                    cubeCounter++;
                } else if ('.' == c) {
                    Vector3f dotLocation = new Vector3f((x - 4.5f) * 2, (y - 4.5f) * 2, 0);
                    new Matrix4f().translate(dotLocation) // creates model matrix at dotLocation
                            .scale(0.25f, 0.25f, 0.25f)
                            .get(dotsCounter * 16, dotDataBuffer); // and stores it into dotBuffer at selected index
                    dotsCounter++;
                }
                x++;
            }
            y++;
        }

        // create buffers with geometry
        int[] buffers = new int[3];
        glGenBuffers(buffers);
        axesBuffer = buffers[0];
        cubeBuffer = buffers[1];
        dotBuffer = buffers[2];

        // fill a buffers with geometry
        glBindBuffer(GL_ARRAY_BUFFER, axesBuffer);
        glBufferData(GL_ARRAY_BUFFER, AXES, GL_STATIC_DRAW);

        // load cube and fill buffer with cube data
        cube = new ObjLoader(ApplicationConstants.PATH_TO_MODELS + "cube.obj");
        try {
            cube.load();
        } catch (IOException ex) {
            logger.error("it's broken", ex);
            System.exit(1);
        }
        int cubeBufferLength = 3 * 8 * cube.getTriangleCount();
        FloatBuffer cubeData = BufferUtils.createFloatBuffer(cubeBufferLength);
        for (int f = 0; f < cube.getTriangleCount(); f++) {
            int[] pi = cube.getVertexIndices().get(f);
            int[] ni = cube.getNormalIndices().get(f);
            int[] ti = cube.getTexcoordIndices().get(f);
            for (int i = 0; i < 3; i++) {
                float[] position = cube.getVertices().get(pi[i]);
                float[] normal = cube.getNormals().get(ni[i]);
                float[] texcoord = cube.getTexcoords().get(ti[i]);
                cubeData.put(position);
                cubeData.put(normal);
                cubeData.put(texcoord);
            }
        }
        cubeData.rewind();
        glBindBuffer(GL_ARRAY_BUFFER, cubeBuffer);
        glBufferData(GL_ARRAY_BUFFER, cubeData, GL_STATIC_DRAW);

        // load dot and fill buffer with dot data
        dot = new ObjLoader(ApplicationConstants.PATH_TO_MODELS + "sphere.obj");
        try {
            dot.load();
        } catch (IOException ex) {
            logger.error("it's broken", ex);
            System.exit(1);
        }
        int dotLength = 3 * 8 * dot.getTriangleCount();
        FloatBuffer dotData = BufferUtils.createFloatBuffer(dotLength);
        for (int f = 0; f < dot.getTriangleCount(); f++) {
            int[] pi = dot.getVertexIndices().get(f);
            int[] ni = dot.getNormalIndices().get(f);
            int[] ti = dot.getTexcoordIndices().get(f);
            for (int i = 0; i < 3; i++) {
                float[] position = dot.getVertices().get(pi[i]);
                float[] normal = dot.getNormals().get(ni[i]);
                float[] texcoord = dot.getTexcoords().get(ti[i]);
                dotData.put(position);
                dotData.put(normal);
                dotData.put(texcoord);
            }
        }
        dotData.rewind();
        glBindBuffer(GL_ARRAY_BUFFER, dotBuffer);
        glBufferData(GL_ARRAY_BUFFER, dotData, GL_STATIC_DRAW);

        // clear buffer binding, so that other code doesn't presume it (easier error detection)
        glBindBuffer(GL_ARRAY_BUFFER, 0);

        // create a vertex array object for the geometry
        int[] arrays = new int[3];
        glGenVertexArrays(arrays);
        axesArray = arrays[0];
        cubeArray = arrays[1];
        dotArray = arrays[2];

        // get axes program attributes
        int positionAttribLoc = glGetAttribLocation(axesProgram, "position");
        int colorAttribLoc = glGetAttribLocation(axesProgram, "color");
        // bind axes buffer
        glBindVertexArray(axesArray);
        glBindBuffer(GL_ARRAY_BUFFER, axesBuffer);
        // stride and offset are employed as both position and color data reside in the same vertex buffer
        glEnableVertexAttribArray(positionAttribLoc);
        glVertexAttribPointer(positionAttribLoc, 3, GL_FLOAT, false, SIZEOF_AXES_VERTEX, 0);
        glEnableVertexAttribArray(colorAttribLoc);
        glVertexAttribPointer(colorAttribLoc, 3, GL_FLOAT, false, SIZEOF_AXES_VERTEX, COLOR_OFFSET);

        // get cube program attributes
        positionAttribLoc = glGetAttribLocation(dotProgram, "position");
        int normalAttribLoc = glGetAttribLocation(dotProgram, "normal");
        int texcoordAttribLoc = glGetAttribLocation(dotProgram, "texcoord");

        // bind cube buffer
        glBindVertexArray(cubeArray);
        glBindBuffer(GL_ARRAY_BUFFER, cubeBuffer);
        glEnableVertexAttribArray(positionAttribLoc);
        glVertexAttribPointer(positionAttribLoc, 3, GL_FLOAT, false, SIZEOF_MODEL_VERTEX, 0);
        glEnableVertexAttribArray(normalAttribLoc);
        glVertexAttribPointer(normalAttribLoc, 3, GL_FLOAT, false, SIZEOF_MODEL_VERTEX, NORMAL_OFFSET);
        glEnableVertexAttribArray(texcoordAttribLoc);
        glVertexAttribPointer(texcoordAttribLoc, 2, GL_FLOAT, false, SIZEOF_MODEL_VERTEX, TEXCOORD_OFFSET);

        // bind dot buffer
        glBindVertexArray(dotArray);
        glBindBuffer(GL_ARRAY_BUFFER, dotBuffer);
        glEnableVertexAttribArray(positionAttribLoc);
        glVertexAttribPointer(positionAttribLoc, 3, GL_FLOAT, false, SIZEOF_MODEL_VERTEX, 0);
        glEnableVertexAttribArray(normalAttribLoc);
        glVertexAttribPointer(normalAttribLoc, 3, GL_FLOAT, false, SIZEOF_MODEL_VERTEX, NORMAL_OFFSET);
        glEnableVertexAttribArray(texcoordAttribLoc);
        glVertexAttribPointer(texcoordAttribLoc, 2, GL_FLOAT, false, SIZEOF_MODEL_VERTEX, TEXCOORD_OFFSET);

        // clear bindings, so that other code doesn't presume it (easier error detection)
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glBindVertexArray(0);
    }

    private void initPostprocessing() {
        // Create post-processing shader program
        // Task 1: examine initialization of all objects needed for off-screen rendering and post-processing
        try {
            postprocessProgram = loadProgram(ApplicationConstants.PATH_TO_SHADERS + "postprocess.vs.glsl", ApplicationConstants.PATH_TO_SHADERS + "postprocess.fs.glsl");
        } catch (IOException ex) {
            logger.error("it's broken", ex);
            System.exit(1);
        }

        // Get attribute and uniform locations
        int position_loc = glGetAttribLocation(postprocessProgram, "position");
        sceneTexLoc = glGetUniformLocation(postprocessProgram, "sceneTex");
        animationTimeLoc = glGetUniformLocation(postprocessProgram, "animation_time");

        // Set the sceneTex texture unit to 0
        glUseProgram(postprocessProgram);
        glUniform1i(sceneTexLoc, SCENE_TEXTURE_UNIT);
        glUseProgram(0);

        // Prepare screen quad geometry and VAO
        screenQuadBuffer = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, screenQuadBuffer);
        glBufferData(GL_ARRAY_BUFFER, SCREEN_QUAD_DATA, GL_STATIC_DRAW);

        screenQuadArray = glGenVertexArrays();
        glBindVertexArray(screenQuadArray);
        glEnableVertexAttribArray(position_loc);
        glVertexAttribPointer(position_loc, 2, GL_FLOAT, false, 0, 0);
        glBindVertexArray(0);
        glBindBuffer(GL_ARRAY_BUFFER, 0);

        // Prepare FBO textures
        fboColorTexture = glGenTextures();
        fboDepthStencilTexture = glGenTextures();
        resizeFboTextures();

        // Set parameters of the color texture. We do not read the depth texture for rendering,
        // so we do not set its parameters.
        glBindTexture(GL_TEXTURE_2D, fboColorTexture);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_EDGE);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
        glBindTexture(GL_TEXTURE_2D, 0);

        // Prepare FBO
        fbo = glGenFramebuffers();
        glBindFramebuffer(GL_FRAMEBUFFER, fbo);
        glFramebufferTexture2D(GL_FRAMEBUFFER, GL_COLOR_ATTACHMENT0, GL_TEXTURE_2D, fboColorTexture, 0);
        glFramebufferTexture2D(GL_FRAMEBUFFER, GL_DEPTH_STENCIL_ATTACHMENT, GL_TEXTURE_2D, fboDepthStencilTexture, 0);
        glDrawBuffers(GL_COLOR_ATTACHMENT0);
        int status = glCheckFramebufferStatus(GL_FRAMEBUFFER);
        if (status != GL_FRAMEBUFFER_COMPLETE) {
            logger.error("it's broken with status: " + status);
            System.exit(1);
        }
        glBindFramebuffer(GL_FRAMEBUFFER, 0);
    }

    private void initUniformBuffers() {
        float[] lightData = new float[] { 0, 1, 0, 0, // position
                0.3f, 0.3f, 0.3f, 0, // ambient color
                1, 1, 1, 0, // diffuse color
                1, 1, 1, 0 // specular color
        };

        // Task 7: store location of LightData uniform block index, using glGetUniformBlockIndex(int program, String uniformBlockName)
        // bind the location for the program using glUniformBlockBinding(int program, int uniformBlockIndex, int uniformBlockBinding)
        // choose the uniformBlockBinding to be the constant LIGHT_DATA_INDEX which is 0
        // Task 9: store location of DotData uniform block index, as in the previous scenario
        // but now use the uniformBlockBinding to be the constant DOT_DATA_INDEX which is 1
        // Task 10: store location of CubeData uniform block index, as in the previous scenario
        // be careful! now you want a different program
        // and again, use a constant 1 again (CUBE_DATA_INDEX)
        int dotLightDataLoc = glGetUniformBlockIndex(dotProgram, "LightData");
        glUniformBlockBinding(dotProgram, dotLightDataLoc, LIGHT_DATA_INDEX);

        int dotDataLoc = glGetUniformBlockIndex(dotProgram, "DotData");
        glUniformBlockBinding(dotProgram, dotDataLoc, DOT_DATA_INDEX);

        int cubeDataLoc = glGetUniformBlockIndex(cubeProgram, "CubeData");
        glUniformBlockBinding(cubeProgram, cubeDataLoc, CUBE_DATA_INDEX);

        // Task 7: generate buffers using glGenBuffers; remember the first seminar and glGenBuffers
        // (protip: you can generate 3 buffers now, we will use them later)
        // afterwards, assign the first generated buffer to lightDataUBO class variable
        // then, bind the buffer using glBindBuffer with target being GL_UNIFORM_BUFFER
        // and upload the data using glBufferData(int target, float[] data, GL_STATIC_DRAW)
        // finally, bind buffer 0 to unbind the previous buffer
        // Task 9: get an unused buffer name from the generated buffer names and store it as dotDataUBO
        // bind it, and upload dotDataBuffer as buffer data
        // Task 10: get an unused buffer name from the generated buffer names and store it as cubeDataUBO
        // bind it, and upload the cubeDataBuffer as buffer data
        int[] buffers = new int[3];
        glGenBuffers(buffers);

        lightDataUBO = buffers[0];
        glBindBuffer(GL_UNIFORM_BUFFER, lightDataUBO);
        glBufferData(GL_UNIFORM_BUFFER, lightData, GL_STATIC_DRAW);
        glBindBuffer(GL_UNIFORM_BUFFER, 0);

        dotDataUBO = buffers[1];
        glBindBuffer(GL_UNIFORM_BUFFER, dotDataUBO);
        glBufferData(GL_UNIFORM_BUFFER, dotDataBuffer, GL_STATIC_DRAW);
        glBindBuffer(GL_UNIFORM_BUFFER, 0);

        cubeDataUBO = buffers[2];
        glBindBuffer(GL_UNIFORM_BUFFER, cubeDataUBO);
        glBufferData(GL_UNIFORM_BUFFER, cubeDataBuffer, GL_STATIC_DRAW);
        glBindBuffer(GL_UNIFORM_BUFFER, 0);

    }

    private void render() {
        // Task 4: fix this method, so that only the original scene is rendered as a wireframe (won't tell you where)
        // Resize OpenGL viewport, i.e., the (bitmap) extents to that is the
        // OpenGL screen space [-1, 1] mapped.
        if (resized) {
            // Task 1: replace glViewport(...) with resizeFboTextures()
            resizeFboTextures();
            resized = false;
        }

        // animate variables
        if (animate) {
            t += 0.02f;
        }

        // Task 1: set rendering to off-screen FBO with glBindFramebuffer(GL_FRAMEBUFFER, fbo)
        // set proper viewport size for rendering to the FBO with glViewport(0, 0, <width>, <height>)
        // see method resizeFboTextures() regarding <width> and <height>
        glBindFramebuffer(GL_FRAMEBUFFER, fbo);
        glViewport(0, 0, fboWidth, fboHeight);

        glPolygonMode(GL_FRONT_AND_BACK, mode);

        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

        // Task 1: set proper width/height ratio for projection, consider FBO size
        Matrix4f projection = new Matrix4f().perspective((float)Math.toRadians(60f), fboWidth / (float)fboHeight, 1, 500);
        Matrix4f view = new Matrix4f().lookAt(camera.getEyePosition(), new Vector3f(0, 0, 0), new Vector3f(0, 1, 0));

        // Task 8: update the light position in the buffer using glBufferSubData(int target, int offset, float[] data)
        // you will have to bind the light data buffer before you upload the data; also, don't forget to unbind it afterwards
        // put the directional light in (30, 10, 0) and rotate it by t around Y axis (you will need to create the light position variable)
        Vector4f lightPosition = new Vector4f(30, 10, 0, 1).mul(new Matrix4f().rotate(t, new Vector3f(0, 1, 0)));
        glBindBuffer(GL_UNIFORM_BUFFER, lightDataUBO);
        glBufferSubData(GL_UNIFORM_BUFFER, 0, new float[] { lightPosition.x, lightPosition.y, lightPosition.z, lightPosition.w });
        glBindBuffer(GL_UNIFORM_BUFFER, 0);

        // Task 7: bind the light uniform buffer using glBindBufferBase(GL_UNIFORM_BUFFER, int index, int buffer)
        // where index is your uniformBlockBinding you used previously
        // Task 9: bind uniform buffer for the dot data, using the same function as previously
        glBindBufferBase(GL_UNIFORM_BUFFER, LIGHT_DATA_INDEX, lightDataUBO);
        glBindBufferBase(GL_UNIFORM_BUFFER, DOT_DATA_INDEX, dotDataUBO);

        drawDot(new Matrix4f(), view, projection, dotArray, dot.getTriangleCount() * 3);

        // Task 10: rebind buffer base on index 1 (DOT_DATA_INDEX) to CUBE_DATA_INDEX to cubeDataUBO
        // and add drawCubes() call to draw the cubes
        glBindBufferBase(GL_UNIFORM_BUFFER, CUBE_DATA_INDEX, cubeDataUBO);
        drawCubes(view, projection);

        // Task 1: setup rendering to window (default/"main") framebuffer:
        // bind window framebuffer as a current target with glBindframeBuffer(GL_FRAMEBUFFER, 0)
        // set proper viewport size for rendering to window
        // clear color information in window framebuffer (we don't care about depth while drawing the fullscreen quad)
        // also disable depth test with glDisable(GL_DEPTH_TEST)
        glBindFramebuffer(GL_FRAMEBUFFER, 0);
        glViewport(0, 0, width, height);
        glClear(GL_COLOR_BUFFER_BIT);
        glDisable(GL_DEPTH_TEST);

        // Task 1: render one big rectangle over the entire screen:
        // use postprocessProgram to use correct shaders with glUseProgram(...)
        // activate GL_TEXTURE0 texturing unit
        // bind fboColorTexture texture into it (we have previously set the sampler in postprocessProgram to use 0)
        // bind screenQuadArray as current VertexArray with glBindVertexArray(...)
        // draw 4 points from the vertex array as a GL_TRIANGLE_STRIP
        // Task 3: set uniform variable "animation_time" to our variable t (think WHEN to assign it, the order of commands matters)
        glPolygonMode(GL_FRONT_AND_BACK, GL_FILL); // Task 4 solution

        glUseProgram(postprocessProgram);
        glUniform1f(animationTimeLoc, t);

        glActiveTexture(GL_TEXTURE0);
        glBindTexture(GL_TEXTURE_2D, fboColorTexture);

        glBindVertexArray(screenQuadArray);
        glDrawArrays(GL_TRIANGLE_STRIP, 0, 4);

        // Task 1: examine clean up:
        // unbind VertexArray so it's easier to detect errors by calling glBindVertexArray(0)
        // unbind shader program so it's easier to detect errors by calling glUseProgram(0)
        // re-enable depth test for subsequent rendering
        glBindVertexArray(0);
        glUseProgram(0);
        glEnable(GL_DEPTH_TEST);
    }

    private void drawDot(Matrix4f model, Matrix4f view, Matrix4f projection, int vao, int count) {
        glUseProgram(dotProgram);
        glBindVertexArray(vao);

        glUniform3f(dotEyePositionLoc, camera.getEyePosition().x, camera.getEyePosition().y, camera.getEyePosition().z);

        // Task 5: remove the current assignments of MVP, normal and model matrices
        // pass the projection and view matrix separately to the shaders
        // - create FloatBuffers for each matrix
        // - set their data using matrix.get(FloatBuffer buffer)
        // - pass the float buffer to shader using glUniformMatrix4fv(int location, false, FloatBuffer buffer)
        // similarly, pass 100 model matrices to the model array in shader
        // hint: use a for cycle and get uniform location of "model[i]"
        // Task 6: as in previous task, pass the 100 dot colors
        // Task 7: remove the glUniform calls for light properties
        // Task 9: remove the assigning of model and materialColor uniform variables
        FloatBuffer projectionData = BufferUtils.createFloatBuffer(16);
        FloatBuffer viewData = BufferUtils.createFloatBuffer(16);
        FloatBuffer modelData = BufferUtils.createFloatBuffer(16);

        projection.get(projectionData);
        view.get(viewData);
        /*
         * for (int i = 0; i < NUMBER_OF_INSTANCES; i++) { modelMatrices[i].get(modelData); int modelLoc =
         * glGetUniformLocation(dotProgram, "model[" + i + "]"); int materialLoc = glGetUniformLocation(dotProgram, "materialColor[" +
         * i + "]"); glUniformMatrix4fv(modelLoc, false, modelData); glUniform4f(materialLoc, dotColors[i].x, dotColors[i].y,
         * dotColors[i].z, dotColors[i].w); }
         */
        glUniformMatrix4fv(dotProjectionLoc, false, projectionData);
        glUniformMatrix4fv(dotViewLoc, false, viewData);


        // Task 5: change the draw call to be an instanced draw call glDrawArraysInstanced(int mode, int first, int count, int primcount)
        // where primcount is the number of instances drawn
        glDrawArraysInstanced(GL_TRIANGLES, 0, count, NUMBER_OF_INSTANCES);

        glBindTexture(GL_TEXTURE_2D, 0);
        glBindVertexArray(0);
        glUseProgram(0);
    }

    // Task 1: examine
    private void resizeFboTextures() {
        // Task 1: later, you can try rendering in different resolutions by changing these
        fboWidth = width;
        fboHeight = height;
        glBindTexture(GL_TEXTURE_2D, fboColorTexture);
        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, fboWidth, fboHeight, 0, GL_RGBA, GL_UNSIGNED_BYTE, (float[])null);
        glBindTexture(GL_TEXTURE_2D, fboDepthStencilTexture);
        glTexImage2D(GL_TEXTURE_2D, 0, GL_DEPTH24_STENCIL8, fboWidth, fboHeight, 0, GL_DEPTH_STENCIL, GL_UNSIGNED_INT_24_8, (float[])null);
        glBindTexture(GL_TEXTURE_2D, 0);
    }

    private Vector4f randomColor() {
        Random random = new Random();

        float hue = random.nextFloat();
        float saturation = random.nextFloat();
        float brightness = 0.7f;

        Color color = Color.getHSBColor(hue, saturation, brightness);

        return new Vector4f(color.getRed() / 255f, color.getGreen() / 255f, color.getBlue() / 255f, 1);
    }

    private void drawAxes(Matrix4f modelViewProjection, float length) {
        glUseProgram(axesProgram);
        glBindVertexArray(axesArray);

        FloatBuffer mvpData = BufferUtils.createFloatBuffer(16);
        modelViewProjection.get(mvpData);
        glUniform1f(axesLengthUniformLoc, length);
        glUniform1f(axesAspectUniformLoc, width / (float)height);
        glUniformMatrix4fv(axesMvpUniformLoc, false, mvpData);

        glDrawArrays(GL_LINES, 0, 6);

        glBindVertexArray(0);
        glUseProgram(0);
    }

    private void drawCubes(Matrix4f view, Matrix4f projection) {
        glUseProgram(cubeProgram);
        glBindVertexArray(cubeArray);

        FloatBuffer projectionData = BufferUtils.createFloatBuffer(16);
        FloatBuffer viewData = BufferUtils.createFloatBuffer(16);
        projection.get(projectionData);
        view.get(viewData);

        glUniform3f(cubeEyePositionLoc, camera.getEyePosition().x, camera.getEyePosition().y, camera.getEyePosition().z);
        glUniformMatrix4fv(cubeProjectionLoc, false, projectionData);
        glUniformMatrix4fv(cubeViewLoc, false, viewData);

        glActiveTexture(GL_TEXTURE0);
        glBindTexture(GL_TEXTURE_2D, woodTexture);
        glUniform1i(cubeWoodTexLoc, 0);

        glDrawArraysInstanced(GL_TRIANGLES, 0, cube.getTriangleCount() * 3, NUMBER_OF_INSTANCES);

        glBindTexture(GL_TEXTURE_2D, 0);
        glBindVertexArray(0);
        glUseProgram(0);
    }

    private int loadTexture(String filepath) throws IOException {
        BufferedImage image = ImageIO.read(new FileInputStream(filepath)); //ImageIO.read(Cv6.class.getResourceAsStream(filename)); 
        byte[] pixels = ((DataBufferByte)image.getRaster().getDataBuffer()).getData();

        int internalFormat;
        int format;
        switch (image.getType()) {
        case BufferedImage.TYPE_3BYTE_BGR:
            internalFormat = GL_RGB;
            format = GL_BGR;
            break;
        case BufferedImage.TYPE_4BYTE_ABGR:
            internalFormat = GL_RGBA;
            format = GL_BGRA;
            pixels = toBGRA(pixels);
            break;
        default:
            throw new IOException("Unknown image type: " + image.getType());
        }

        ByteBuffer textureData = BufferUtils.createByteBuffer(pixels.length);
        textureData.put(pixels);
        textureData.rewind();

        int texture = glGenTextures();
        glBindTexture(GL_TEXTURE_2D, texture);
        glTexImage2D(GL_TEXTURE_2D, 0, internalFormat, image.getWidth(), image.getHeight(), 0, format, GL_UNSIGNED_BYTE, textureData);
        glGenerateMipmap(GL_TEXTURE_2D);

        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR_MIPMAP_LINEAR);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);

        // unbind texture
        glBindTexture(GL_TEXTURE_2D, 0);

        return texture;
    }

    private byte[] toBGRA(byte[] abgr) {
        byte[] bgra = new byte[abgr.length];
        for (int i = 0; i < abgr.length; i += 4) {
            bgra[i] = abgr[i + 1];
            bgra[i + 1] = abgr[i + 2];
            bgra[i + 2] = abgr[i + 3];
            bgra[i + 3] = abgr[i];
        }
        return bgra;
    }

    private int loadShader(String filename, int shaderType) throws IOException {
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

    private int loadProgram(String vertexShaderFile, String fragmentShaderFile) throws IOException {
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

    private String readAllFromResource(String resource) throws IOException {
        return FileUtils.readFileToString(new File(resource));
    }

}
