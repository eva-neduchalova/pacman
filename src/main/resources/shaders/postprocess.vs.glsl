#version 330

in vec2 position;

out vec2 vTexCoord;

// Task 1: examine how this works:
//           texture coordinates are generated from vertex position
//           remember the NDC coordinates are [-1,1], but texturing coordinates are [0,1]
void main() {
    vTexCoord = position * 0.5 + 0.5;
    gl_Position = vec4(position, 0.0, 1.0);
}