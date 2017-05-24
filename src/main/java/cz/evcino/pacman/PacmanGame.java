package cz.evcino.pacman;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.GLFW_CONTEXT_VERSION_MAJOR;
import static org.lwjgl.glfw.GLFW.GLFW_CONTEXT_VERSION_MINOR;
import static org.lwjgl.glfw.GLFW.GLFW_FALSE;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_A;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_DOWN;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_ESCAPE;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_F;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_L;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_LEFT;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_RIGHT;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_T;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_UP;
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
import static org.lwjgl.opengl.GL11.GL_FILL;
import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL11.GL_FRONT_AND_BACK;
import static org.lwjgl.opengl.GL11.GL_LINE;
import static org.lwjgl.opengl.GL11.GL_LINEAR;
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
import static org.lwjgl.opengl.GL12.GL_CLAMP_TO_EDGE;
import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.glActiveTexture;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.glBufferData;
import static org.lwjgl.opengl.GL15.glBufferSubData;
import static org.lwjgl.opengl.GL15.glGenBuffers;
import static org.lwjgl.opengl.GL20.glDrawBuffers;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glGetAttribLocation;
import static org.lwjgl.opengl.GL20.glGetUniformLocation;
import static org.lwjgl.opengl.GL20.glUniform1f;
import static org.lwjgl.opengl.GL20.glUniform1i;
import static org.lwjgl.opengl.GL20.glUniform3f;
import static org.lwjgl.opengl.GL20.glUniformMatrix3fv;
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
import static org.lwjgl.opengl.GL31.GL_UNIFORM_BUFFER;
import static org.lwjgl.opengl.GL31.glDrawArraysInstanced;
import static org.lwjgl.opengl.GL31.glGetUniformBlockIndex;
import static org.lwjgl.opengl.GL31.glUniformBlockBinding;
import static org.lwjgl.system.MemoryStack.stackPush;
import static org.lwjgl.system.MemoryUtil.NULL;

import java.io.IOException;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.joml.Matrix3f;
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

import cz.evcino.pacman.enums.MazeLocationStatus;
import cz.evcino.pacman.enums.MovementDirection;
import cz.evcino.pacman.objects.Ghost;
import cz.evcino.pacman.objects.Maze;
import cz.evcino.pacman.objects.Pacman;
import cz.evcino.pacman.utils.ModelUtils;
import cz.evcino.pacman.utils.MovementUtils;
import cz.evcino.pacman.utils.ShaderUtils;
import cz.evcino.pacman.utils.TextureUtils;


public class PacmanGame {

    protected static final Logger logger = LoggerFactory.getLogger(PacmanGame.class);

    private static final int NUMBER_OF_INSTANCES_CUBES = StringUtils
            .countMatches(Arrays.toString(ApplicationConstants.MAZE_DEFINITION_STRING), "X");
    private static final int NUMBER_OF_INSTANCES_DOTS = StringUtils
            .countMatches(Arrays.toString(ApplicationConstants.MAZE_DEFINITION_STRING), ".");
    private static final int NUMBER_OF_INSTANCES_SUPERDOTS = StringUtils
            .countMatches(Arrays.toString(ApplicationConstants.MAZE_DEFINITION_STRING), "O");
    private static final int NUMBER_OF_INSTANCES_GHOSTS = StringUtils
            .countMatches(Arrays.toString(ApplicationConstants.MAZE_DEFINITION_STRING), "G");

    private static final int SIZEOF_GHOST_VERTEX = 6 * Float.BYTES;
    private static final int COLOR_OFFSET = 3 * Float.BYTES;

    private static final int SIZEOF_MODEL_VERTEX = 8 * Float.BYTES;
    private static final int NORMAL_OFFSET = 3 * Float.BYTES;
    private static final int TEXCOORD_OFFSET = 6 * Float.BYTES;

    private Camera camera;

    // the window handle
    private long window;

    // window size
    private int width = ApplicationConstants.DEFAULT_WINDOW_WIDTH;
    private int height = ApplicationConstants.DEFAULT_WINDOW_HEIGHT;
    private boolean resized = false;

    // animation
    private boolean animate = false;
    private float t = 0f;

    // rendering mode
    private int mode = GL_FILL;

    Maze maze = null;
    Pacman pacmanCharacter = null;
    private List<Ghost> ghosts = new ArrayList<>();


    // model
    private ObjLoader cubeModel;
    private ObjLoader dot;
    private ObjLoader ghost;
    private ObjLoader pacman;

    // our OpenGL resources
    private int cubeBuffer;
    private int dotArray;
    private int ghostBuffer;
    private int pacmanBuffer;

    private int cubeArray;
    private int dotBuffer;
    private int ghostArray;
    private int pacmanArray;


    // our GLSL resources

    private int cubeProgram;
    private int cubeViewLoc;
    private int cubeProjectionLoc;
    private int cubeEyePositionLoc;

    private int woodTexture;
    private int cubeWoodTexLoc;


    private int dotProgram;
    private int dotViewLoc;
    private int dotProjectionLoc;
    private int dotEyePositionLoc;


    private int ghostProgram;
    private int ghostAspectUniformLoc;
    private int ghostMvpUniformLoc;
    private int ghostNUniformLoc;
    private int ghostColorUniformLoc;

    private int pacmanProgram;
    private int pacmanAspectUniformLoc;
    private int pacmanMvpUniformLoc;
    private int pacmanNUniformLoc;
    private int pacmanColorUniformLoc;


    FloatBuffer cubeDataBuffer = BufferUtils.createFloatBuffer(NUMBER_OF_INSTANCES_CUBES * 16);
    FloatBuffer dotDataBuffer = BufferUtils.createFloatBuffer((NUMBER_OF_INSTANCES_DOTS + NUMBER_OF_INSTANCES_SUPERDOTS) * (16 + 4));
    FloatBuffer ghostDataBuffer = BufferUtils.createFloatBuffer(NUMBER_OF_INSTANCES_GHOSTS * (16 + 4));


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
    private final int CUBE_DATA_INDEX = 1;
    private int cubeDataUBO;

    // Light data uniform buffer
    private final int DOT_DATA_INDEX = 1;
    private int dotDataUBO;

    // Light data uniform buffer
    private final int GHOST_DATA_INDEX = 1;
    private int ghostDataUBO;


    public static void main(String[] args) {
        new PacmanGame().run();
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
        // Setup an error callback. The default implementation will print the error message in System.err.
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

        // Create the window
        window = glfwCreateWindow(width, height, "Evčin pacman", NULL, NULL);
        if (window == NULL) {
            throw new RuntimeException("Failed to create the GLFW window");
        }

        // Setup a key callback. It will be called every time a key is pressed, repeated or released.
        glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
            if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE) {
                System.out.println("esc");
                glfwSetWindowShouldClose(window, true); // We will detect this in the rendering loop
            }
            if (action != GLFW_RELEASE) {
                if (GLFW_KEY_UP == key) {
                    System.out.println("up");
                    pacmanCharacter.setDirection(MovementDirection.UP);
                } else if (GLFW_KEY_DOWN == key) {
                    System.out.println("down");
                    pacmanCharacter.setDirection(MovementDirection.DOWN);
                } else if (GLFW_KEY_LEFT == key) {
                    System.out.println("left");
                    pacmanCharacter.setDirection(MovementDirection.LEFT);
                } else if (GLFW_KEY_RIGHT == key) {
                    System.out.println("right");
                    pacmanCharacter.setDirection(MovementDirection.RIGHT);
                } else if (GLFW_KEY_L == key) {
                    logger.info(maze.toLogString());
                }
            }


            if (action == GLFW_RELEASE) {
                // TODO - remove unused
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
            // Poll for window events. The key callback above will only be invoked during this call.
            glfwPollEvents();

            // TODO - ghosts.move

            for (Ghost ghost : ghosts) {
                boolean ghostCanTurn = MovementUtils.canGhostChangeDirection(ghost, maze);
                if (ghostCanTurn) {
                    MovementUtils.selectGhostDirection(ghost, pacmanCharacter, maze);
                }
                ghost.move();
                MovementUtils.fixGhostPosition(ghost, maze);
            }

            if (MovementUtils.canMove(pacmanCharacter, maze)) {
                boolean dotsUpdateRequired = MovementUtils.evaluateDots(pacmanCharacter, maze);
                if (dotsUpdateRequired) {
                    addDotsAndPowerDotsOnBuffers(maze, dotDataBuffer);
                    glBindBuffer(GL_UNIFORM_BUFFER, dotDataUBO);
                    glBufferData(GL_UNIFORM_BUFFER, dotDataBuffer, GL_STATIC_DRAW);
                    glBindBuffer(GL_UNIFORM_BUFFER, 0);
                }
                pacmanCharacter.move();
            }

            render();

            glfwSwapBuffers(window); // swap the color buffers


        }
    }

    private void init() {
        // empty scene color
        glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        glLineWidth(3.0f); // makes lines thicker

        glEnable(GL_DEPTH_TEST);

        // load GLSL program (vertex, fragment shaders) and textures
        try {
            cubeProgram = ShaderUtils.loadProgramByShaderMainName("cube");
            dotProgram = ShaderUtils.loadProgramByShaderMainName("dot");
            ghostProgram = ShaderUtils.loadProgramByShaderMainName("ghost");
            pacmanProgram = ShaderUtils.loadProgramByShaderMainName("pacman");
            woodTexture = TextureUtils.loadTextureByFilename("wood.jpg");
        } catch (IOException ex) {
            logger.error("it's broken", ex);
            System.exit(1);
        }

        cubeProjectionLoc = glGetUniformLocation(cubeProgram, "projection");
        cubeViewLoc = glGetUniformLocation(cubeProgram, "view");
        cubeEyePositionLoc = glGetUniformLocation(cubeProgram, "eyePosition");
        cubeWoodTexLoc = glGetUniformLocation(cubeProgram, "woodTex");

        dotProjectionLoc = glGetUniformLocation(dotProgram, "projection");
        dotViewLoc = glGetUniformLocation(dotProgram, "view");
        dotEyePositionLoc = glGetUniformLocation(dotProgram, "eyePosition");

        int mazeRows = ApplicationConstants.MAZE_DEFINITION_STRING.length;
        int mazeColumns = ApplicationConstants.MAZE_DEFINITION_STRING[0].length();
        maze = new Maze(mazeRows, mazeColumns);


        float offsetX = -mazeColumns / 2;
        float offsetY = -mazeRows / 2;
        int cubeCounter = 0;
        int x = 0;
        int y = 0;
        for (String row : ApplicationConstants.MAZE_DEFINITION_STRING) {
            x = 0;
            for (char c : row.toCharArray()) {
                Vector3f location = new Vector3f((x + offsetX) * Maze.SQUARE_LENGTH, (y + offsetY) * Maze.SQUARE_LENGTH, 0);
                if ('X' == c) {
                    maze.setValue(x, y, MazeLocationStatus.WALL);
                    new Matrix4f().translate(location).scale(1f,1f,0.25f).get(cubeCounter * 16, cubeDataBuffer);
                    cubeCounter++;
                } else if ('.' == c) {
                    maze.setValue(x, y, MazeLocationStatus.DOT);
                } else if ('O' == c) {
                    maze.setValue(x, y, MazeLocationStatus.POWER_DOT);
                } else if ('G' == c) {
                    Ghost ghostCharacter = new Ghost();
                    ghostCharacter.setDefaultLocation(location);
                    ghostCharacter.setLocation(location);
                    ghostCharacter.setColor(Ghost.COLORS[ghosts.size() % Ghost.COLORS.length]);
                    ghostCharacter.setDirection(MovementDirection.values()[ghosts.size() % MovementDirection.values().length]);
                    ghosts.add(ghostCharacter);
                    maze.setValue(x, y, MazeLocationStatus.EMPTY);
                } else if ('P' == c) {
                    // Pacman!
                    if (pacmanCharacter != null) {
                        throw new IllegalArgumentException("two (or more) Pacmans at one playground!");
                    }
                    pacmanCharacter = new Pacman();
                    pacmanCharacter.setDefaultLocation(location);
                    pacmanCharacter.setLocation(location);
                    maze.setValue(x, y, MazeLocationStatus.EMPTY);
                }
                x++;
            }
            y++;
        }

        addDotsAndPowerDotsOnBuffers(maze, dotDataBuffer);

        // model program uniforms
        ghostAspectUniformLoc = glGetUniformLocation(ghostProgram, "aspect");
        ghostMvpUniformLoc = glGetUniformLocation(ghostProgram, "MVP");
        ghostNUniformLoc = glGetUniformLocation(ghostProgram, "N");
        ghostColorUniformLoc = glGetUniformLocation(ghostProgram, "color");

        // model program uniforms
        pacmanAspectUniformLoc = glGetUniformLocation(pacmanProgram, "aspect");
        pacmanMvpUniformLoc = glGetUniformLocation(pacmanProgram, "MVP");
        pacmanNUniformLoc = glGetUniformLocation(pacmanProgram, "N");
        pacmanColorUniformLoc = glGetUniformLocation(pacmanProgram, "color");


        // create buffers with geometry
        int[] buffers = new int[4];
        glGenBuffers(buffers);
        cubeBuffer = buffers[0];
        dotBuffer = buffers[1];
        ghostBuffer = buffers[2];
        pacmanBuffer = buffers[3];

        // fill a buffers with geometry
        // glBindBuffer(GL_ARRAY_BUFFER, ghostBuffer);
        // TOFIXglBufferData(GL_ARRAY_BUFFER, AXES, GL_STATIC_DRAW);

        // load cube and fill buffer with cube data
        cubeModel = ModelUtils.loadModelData("cube.obj", cubeBuffer);

        // load dot and fill buffer with dot data
        dot = ModelUtils.loadModelData("sphere.obj", dotBuffer);

        // load ghost and fill buffer with ghost data
        ghost = ModelUtils.loadModelData("ghost.obj", ghostBuffer);

        // load pacman and fill buffer with pacman data
        pacman = ModelUtils.loadModelData("pacman.obj", pacmanBuffer);

        // clear buffer binding, so that other code doesn't presume it (easier error detection)
        glBindBuffer(GL_ARRAY_BUFFER, 0);

        // create a vertex array object for the geometry
        int[] arrays = new int[4];
        glGenVertexArrays(arrays);
        cubeArray = arrays[0];
        dotArray = arrays[1];
        ghostArray = arrays[2];
        pacmanArray = arrays[3];

        {
            // get cube program attributes
            int positionAttribLoc = glGetAttribLocation(cubeProgram, "position");
            int normalAttribLoc = glGetAttribLocation(cubeProgram, "normal");
            int texcoordAttribLoc = glGetAttribLocation(cubeProgram, "texcoord");

            // bind cube buffer
            glBindVertexArray(cubeArray);
            glBindBuffer(GL_ARRAY_BUFFER, cubeBuffer);
            glEnableVertexAttribArray(positionAttribLoc);
            glVertexAttribPointer(positionAttribLoc, 3, GL_FLOAT, false, SIZEOF_MODEL_VERTEX, 0);
            glEnableVertexAttribArray(normalAttribLoc);
            glVertexAttribPointer(normalAttribLoc, 3, GL_FLOAT, false, SIZEOF_MODEL_VERTEX, NORMAL_OFFSET);
            glEnableVertexAttribArray(texcoordAttribLoc);
            glVertexAttribPointer(texcoordAttribLoc, 2, GL_FLOAT, false, SIZEOF_MODEL_VERTEX, TEXCOORD_OFFSET);
        }

        {
            updateDotArrayAndBuffer();
        }

        {
            // get ghost program attributes
            int positionAttribLoc = glGetAttribLocation(ghostProgram, "position");
            int normalAttribLoc = glGetAttribLocation(ghostProgram, "normal");
            int texcoordAttribLoc = glGetAttribLocation(ghostProgram, "texcoord");
            // int colorAttribLoc = glGetAttribLocation(ghostProgram, "color");

            // bind ghost buffer
            glBindVertexArray(ghostArray);
            glBindBuffer(GL_ARRAY_BUFFER, ghostBuffer);
            glEnableVertexAttribArray(positionAttribLoc);
            glVertexAttribPointer(positionAttribLoc, 3, GL_FLOAT, false, SIZEOF_MODEL_VERTEX, 0);
            glEnableVertexAttribArray(normalAttribLoc);
            glVertexAttribPointer(normalAttribLoc, 3, GL_FLOAT, false, SIZEOF_MODEL_VERTEX, NORMAL_OFFSET);
            glEnableVertexAttribArray(texcoordAttribLoc);
            glVertexAttribPointer(texcoordAttribLoc, 2, GL_FLOAT, false, SIZEOF_MODEL_VERTEX, TEXCOORD_OFFSET);
            // glEnableVertexAttribArray(colorAttribLoc);
            // glVertexAttribPointer(colorAttribLoc, 3, GL_FLOAT, false, SIZEOF_GHOST_VERTEX, COLOR_OFFSET);
        }

        {
            // get pacman program attributes
            int positionAttribLoc = glGetAttribLocation(pacmanProgram, "position");
            int normalAttribLoc = glGetAttribLocation(pacmanProgram, "normal");

            // bind pacman buffer
            glBindVertexArray(pacmanArray);
            glBindBuffer(GL_ARRAY_BUFFER, pacmanBuffer);
            glEnableVertexAttribArray(positionAttribLoc);
            glVertexAttribPointer(positionAttribLoc, 3, GL_FLOAT, false, SIZEOF_MODEL_VERTEX, 0);
            glEnableVertexAttribArray(normalAttribLoc);
            glVertexAttribPointer(normalAttribLoc, 3, GL_FLOAT, false, SIZEOF_MODEL_VERTEX, NORMAL_OFFSET);

        }

        // clear bindings, so that other code doesn't presume it (easier error detection)
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glBindVertexArray(0);
    }

    private void updateDotArrayAndBuffer() {
        // get dot program attributes
        int positionAttribLoc = glGetAttribLocation(dotProgram, "position");
        int normalAttribLoc = glGetAttribLocation(dotProgram, "normal");
        int texcoordAttribLoc = glGetAttribLocation(dotProgram, "texcoord");

        // bind dot buffer
        glBindVertexArray(dotArray);
        glBindBuffer(GL_ARRAY_BUFFER, dotBuffer);
        glEnableVertexAttribArray(positionAttribLoc);
        glVertexAttribPointer(positionAttribLoc, 3, GL_FLOAT, false, SIZEOF_MODEL_VERTEX, 0);
        glEnableVertexAttribArray(normalAttribLoc);
        glVertexAttribPointer(normalAttribLoc, 3, GL_FLOAT, false, SIZEOF_MODEL_VERTEX, NORMAL_OFFSET);
        glEnableVertexAttribArray(texcoordAttribLoc);
        glVertexAttribPointer(texcoordAttribLoc, 2, GL_FLOAT, false, SIZEOF_MODEL_VERTEX, TEXCOORD_OFFSET);
    }


    private void addDotsAndPowerDotsOnBuffers(Maze maze, FloatBuffer dotDataBuffer) {
        dotDataBuffer.clear();
        int dotsCounter = 0;
        float offsetX = -maze.getColumns() / 2;
        float offsetY = -maze.getRows() / 2;

        for (int x = 0; x < maze.getColumns(); x++) {
            for (int y = 0; y < maze.getRows(); y++) {
                MazeLocationStatus status = maze.getValue(x, y);
                Vector3f location = new Vector3f((x + offsetX) * Maze.SQUARE_LENGTH, (y + offsetY) * Maze.SQUARE_LENGTH, 0);
                if (MazeLocationStatus.DOT.equals(status)) {
                    new Matrix4f().translate(location).scale(MovementUtils.DOT_DIAMETER).get(dotsCounter * 16, dotDataBuffer);
                    dotsCounter++;
                } else if (MazeLocationStatus.POWER_DOT.equals(status)) {
                    new Matrix4f().translate(location).scale(MovementUtils.SUPERDOT_DIAMETER).get(dotsCounter * 16, dotDataBuffer);
                    dotsCounter++;
                }
            }
        }
    }

    private void initPostprocessing() {
        // Create post-processing shader program
        // Task 1: examine initialization of all objects needed for off-screen rendering and post-processing
        try {
            postprocessProgram = ShaderUtils.loadProgramByShaderMainName("postprocess");
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

        int dotLightDataLoc = glGetUniformBlockIndex(dotProgram, "LightData");
        glUniformBlockBinding(dotProgram, dotLightDataLoc, LIGHT_DATA_INDEX);

        int dotDataLoc = glGetUniformBlockIndex(dotProgram, "DotData");
        glUniformBlockBinding(dotProgram, dotDataLoc, DOT_DATA_INDEX);

        int cubeDataLoc = glGetUniformBlockIndex(cubeProgram, "CubeData");
        glUniformBlockBinding(cubeProgram, cubeDataLoc, CUBE_DATA_INDEX);

        int[] buffers = new int[4];
        glGenBuffers(buffers);

        lightDataUBO = buffers[0];
        glBindBuffer(GL_UNIFORM_BUFFER, lightDataUBO);
        glBufferData(GL_UNIFORM_BUFFER, lightData, GL_STATIC_DRAW);
        glBindBuffer(GL_UNIFORM_BUFFER, 0);

        cubeDataUBO = buffers[1];
        glBindBuffer(GL_UNIFORM_BUFFER, cubeDataUBO);
        glBufferData(GL_UNIFORM_BUFFER, cubeDataBuffer, GL_STATIC_DRAW);
        glBindBuffer(GL_UNIFORM_BUFFER, 0);

        dotDataUBO = buffers[2];
        glBindBuffer(GL_UNIFORM_BUFFER, dotDataUBO);
        glBufferData(GL_UNIFORM_BUFFER, dotDataBuffer, GL_STATIC_DRAW);
        glBindBuffer(GL_UNIFORM_BUFFER, 0);

    }

    private void render() {

        if (resized) {
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

        // cubes
        glBindBufferBase(GL_UNIFORM_BUFFER, CUBE_DATA_INDEX, cubeDataUBO);
        drawCubes(view, projection);

        // dots
        glBindBufferBase(GL_UNIFORM_BUFFER, LIGHT_DATA_INDEX, lightDataUBO);
        glBindBufferBase(GL_UNIFORM_BUFFER, DOT_DATA_INDEX, dotDataUBO);
        drawDot(new Matrix4f(), view, projection, dotArray, dot.getTriangleCount() * 3);

        // ghosts
        for (Ghost ghostCharacter : ghosts) {
            Matrix4f ghostCharacterMV = new Matrix4f(view).translate(ghostCharacter.getLocation())
                    .rotate((float)Math.toRadians(90), 1f, 0f, 0f).scale(Ghost.GHOST_DIAMETER);
            if (MovementDirection.LEFT.equals(pacmanCharacter.getDirection())) {
                ghostCharacterMV = ghostCharacterMV.rotate((float)Math.toRadians(-90), 0f, 1f, 0f);
            } else if (MovementDirection.RIGHT.equals(pacmanCharacter.getDirection())) {
                ghostCharacterMV = ghostCharacterMV.rotate((float)Math.toRadians(90), 0f, 1f, 0f);
            } else if (MovementDirection.UP.equals(pacmanCharacter.getDirection())) {
                ghostCharacterMV = ghostCharacterMV.rotate((float)Math.toRadians(180), 0f, 1f, 0f);
            }
            drawGhost(ghostCharacterMV, projection, ghostArray, ghost.getTriangleCount() * 3, ghostCharacter.getColor());
        }


        // pacman
        
        
        Matrix4f pacmanMV = new Matrix4f(view).translate(pacmanCharacter.getLocation()).rotate((float)Math.toRadians(90), 1f, 0f, 0f);
        // after first rotation pacman is facing bottom of screen.scale(Pacman.PACMAN_DIAMETER);
        if (MovementDirection.LEFT.equals(pacmanCharacter.getDirection())) {
            pacmanMV = pacmanMV.rotate((float)Math.toRadians(-90), 0f, 1f, 0f);
        } else if (MovementDirection.RIGHT.equals(pacmanCharacter.getDirection())) {
            pacmanMV = pacmanMV.rotate((float)Math.toRadians(90), 0f, 1f, 0f);
        } else if (MovementDirection.UP.equals(pacmanCharacter.getDirection())) {
            pacmanMV = pacmanMV.rotate((float)Math.toRadians(180), 0f, 1f, 0f);
        }
        pacmanMV = pacmanMV.scale(Pacman.PACMAN_DIAMETER);

        drawPacman(pacmanMV, projection, pacmanArray, pacman.getTriangleCount() * 3, new Vector3f(0.188f, 0.83921f, 0.784f));
        for (int i = 0; i < pacmanCharacter.getExtraLives(); i++) {
            float x = MovementUtils.getAbsoluteXLocation(-2, maze);
            float y = MovementUtils.getAbsoluteYLocation(i, maze);
            Matrix4f pacmanLifeMV = new Matrix4f(view).translate(x, y, 0).scale(Pacman.PACMAN_DIAMETER);
            drawPacman(pacmanLifeMV, projection, pacmanArray, pacman.getTriangleCount() * 3, new Vector3f(0.188f, 0.83921f, 0.784f));
        }


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

        glDrawArraysInstanced(GL_TRIANGLES, 0, cubeModel.getTriangleCount() * 3, NUMBER_OF_INSTANCES_CUBES);

        glBindTexture(GL_TEXTURE_2D, 0);
        glBindVertexArray(0);
        glUseProgram(0);
    }

    private void drawDot(Matrix4f model, Matrix4f view, Matrix4f projection, int vao, int count) {
        glUseProgram(dotProgram);
        glBindVertexArray(vao);

        glUniform3f(dotEyePositionLoc, camera.getEyePosition().x, camera.getEyePosition().y, camera.getEyePosition().z);

        FloatBuffer projectionData = BufferUtils.createFloatBuffer(16);
        FloatBuffer viewData = BufferUtils.createFloatBuffer(16);
        FloatBuffer modelData = BufferUtils.createFloatBuffer(16);

        projection.get(projectionData);
        view.get(viewData);

        glUniformMatrix4fv(dotProjectionLoc, false, projectionData);
        glUniformMatrix4fv(dotViewLoc, false, viewData);


        // Task 5: change the draw call to be an instanced draw call glDrawArraysInstanced(int mode, int first, int count, int primcount)
        // where primcount is the number of instances drawn
        glDrawArraysInstanced(GL_TRIANGLES, 0, count, NUMBER_OF_INSTANCES_DOTS + NUMBER_OF_INSTANCES_SUPERDOTS);

        glBindTexture(GL_TEXTURE_2D, 0);
        glBindVertexArray(0);
        glUseProgram(0);
    }

    private void drawGhost(Matrix4f modelView, Matrix4f projection, int vao, int count, Vector3f color) {
        // compute model-view-projection matrix
        Matrix4f mvp = new Matrix4f().set(projection).mul(modelView);

        // compute normal matrix
        Matrix3f n = modelView.get3x3(new Matrix3f()).invert().transpose();

        glUseProgram(ghostProgram);
        glBindVertexArray(vao); // bind vertex array to draw

        glUniform1f(ghostAspectUniformLoc, width / (float)height); // update aspect uniform

        FloatBuffer mvpData = BufferUtils.createFloatBuffer(16);
        FloatBuffer nData = BufferUtils.createFloatBuffer(9);
        mvp.get(mvpData);
        n.get(nData);
        glUniformMatrix4fv(ghostMvpUniformLoc, false, mvpData); // pass MVP matrix to shader
        glUniformMatrix3fv(ghostNUniformLoc, false, nData); // pass Normal matrix to shader
        glUniform3f(ghostColorUniformLoc, color.x, color.y, color.z); // pass object color to shader

        glDrawArrays(GL_TRIANGLES, 0, count);

        glBindVertexArray(0);
        glUseProgram(0);
    }

    private void drawPacman(Matrix4f modelView, Matrix4f projection, int vao, int count, Vector3f color) {
        // compute model-view-projection matrix
        Matrix4f mvp = new Matrix4f().set(projection).mul(modelView);

        // compute normal matrix
        Matrix3f n = modelView.get3x3(new Matrix3f()).invert().transpose();

        glUseProgram(pacmanProgram);
        glBindVertexArray(vao); // bind vertex array to draw

        glUniform1f(pacmanAspectUniformLoc, width / (float)height); // update aspect uniform

        FloatBuffer mvpData = BufferUtils.createFloatBuffer(16);
        FloatBuffer nData = BufferUtils.createFloatBuffer(9);
        mvp.get(mvpData);
        n.get(nData);
        glUniformMatrix4fv(pacmanMvpUniformLoc, false, mvpData); // pass MVP matrix to shader
        glUniformMatrix3fv(pacmanNUniformLoc, false, nData); // pass Normal matrix to shader
        glUniform3f(pacmanColorUniformLoc, color.x, color.y, color.z); // pass object color to shader

        glDrawArrays(GL_TRIANGLES, 0, count);

        glBindVertexArray(0);
        glUseProgram(0);
    }


}
