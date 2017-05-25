package cz.evcino.pacman.loader;


import org.joml.Vector3f;

 public class Faces {
    public Vector3f Vertex = new Vector3f();
    public Vector3f normals = new Vector3f();

    public Faces(Vector3f vertex, Vector3f normal) {
        this.Vertex = vertex;
        this.normals = normal;
    }
}