package cz.evcino.pacman.utils;

import static org.lwjgl.opengl.GL11.GL_LINEAR;
import static org.lwjgl.opengl.GL11.GL_LINEAR_MIPMAP_LINEAR;
import static org.lwjgl.opengl.GL11.GL_RGB;
import static org.lwjgl.opengl.GL11.GL_RGBA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MAG_FILTER;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MIN_FILTER;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_BYTE;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glGenTextures;
import static org.lwjgl.opengl.GL11.glTexImage2D;
import static org.lwjgl.opengl.GL11.glTexParameteri;
import static org.lwjgl.opengl.GL12.GL_BGR;
import static org.lwjgl.opengl.GL12.GL_BGRA;
import static org.lwjgl.opengl.GL30.glGenerateMipmap;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Random;

import javax.imageio.ImageIO;

import org.joml.Vector4f;
import org.lwjgl.BufferUtils;

import cz.evcino.pacman.ApplicationConstants;

public class TextureUtils {

    public static int loadTextureByFilename(String filename) throws IOException {
        return loadTexture(ApplicationConstants.PATH_TO_TEXTURES + filename);
    }

    private static int loadTexture(String filepath) throws IOException {
        BufferedImage image = ImageIO.read(new FileInputStream(filepath)); // ImageIO.read(Cv6.class.getResourceAsStream(filename));
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

    private static byte[] toBGRA(byte[] abgr) {
        byte[] bgra = new byte[abgr.length];
        for (int i = 0; i < abgr.length; i += 4) {
            bgra[i] = abgr[i + 1];
            bgra[i + 1] = abgr[i + 2];
            bgra[i + 2] = abgr[i + 3];
            bgra[i + 3] = abgr[i];
        }
        return bgra;
    }

    private Vector4f randomColor() {
        Random random = new Random();

        float hue = random.nextFloat();
        float saturation = random.nextFloat();
        float brightness = 0.7f;

        Color color = Color.getHSBColor(hue, saturation, brightness);

        return new Vector4f(color.getRed() / 255f, color.getGreen() / 255f, color.getBlue() / 255f, 1);
    }

}
