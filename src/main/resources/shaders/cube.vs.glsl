#version 330

in vec3 position;
in vec3 normal;
in vec2 texcoord;

out VertexData
{
    vec3 vPosition;
    vec3 vNormal;
    vec2 vTexCoord;
} OUT;

uniform mat4 projection;
uniform mat4 view;

layout (std140) uniform CubeData
{
	mat4 model[100];
};

void main() {
    OUT.vNormal = transpose(inverse(mat3(model[gl_InstanceID]))) * normal;
    OUT.vPosition = vec3(model[gl_InstanceID] * vec4(position, 1.0));
    OUT.vTexCoord = texcoord;
    gl_Position = projection * view * model[gl_InstanceID] * vec4(position, 1.0);
}
