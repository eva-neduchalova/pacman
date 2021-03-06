#version 330

in vec3 position;
in vec3 normal;

out vec3 vertColor;
out vec3 vertNormal;

// width/height ratio
uniform float aspect;

// model view projection matrix
uniform mat4 MVP;
uniform mat3 N;
uniform vec3 color;

void main() {
    vertColor = color;
    vertNormal = N * normal;
    // Task 3:  remove division by aspect as it is already present in projection
    gl_Position = MVP * vec4(position.x, position.yz, 1.0);
}
