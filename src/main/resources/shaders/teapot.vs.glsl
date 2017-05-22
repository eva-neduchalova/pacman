#version 330

in vec3 position;
in vec3 normal;
in vec2 texcoord;

// Task 6: add out variable for color of type vec3
out vec3 vNormal;
out vec3 vPosition;
out vec2 vTexCoord;
out vec3 vColor;

// Task 5: remove current normal, MVP and model matrices
//           add projection and view matrix uniforms
//           add array of model matrices with 100 items
// Task 6: add array materialColor of 100 colors (type vec4)
// Task 9: move the model and material color arrays to TeapotData uniform object
uniform mat4 projection;
uniform mat4 view;
// uniform mat4 model[100];
// uniform vec4 materialColor[100];

layout (std140) uniform TeapotData
{
    mat4 model[100];
    vec4 materialColor[100];
};

void main() {
    // Task 5: change the calculations to use the model matrix from array, with current gl_InstanceID as index
    // Task 6: pass the values (first 3 components) of the materialColor to vColor
    vNormal = transpose(inverse(mat3(model[gl_InstanceID]))) * normal;
    vPosition = vec3(model[gl_InstanceID] * vec4(position, 1.0));
    vTexCoord = texcoord;
    vColor = materialColor[gl_InstanceID].rgb;
    gl_Position = projection * view * model[gl_InstanceID] * vec4(position, 1.0);
}
