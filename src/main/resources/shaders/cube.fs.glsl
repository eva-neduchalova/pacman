#version 330

in VertexData
{
    vec3 vPosition;
    vec3 vNormal;
    vec2 vTexCoord;
} IN;

out vec4 fragColor;

layout (std140) uniform LightData
{
    vec4 position;
    vec4 ambientColor;
    vec4 diffuseColor;
    vec4 specularColor;
} light;

uniform vec3 eyePosition;

uniform sampler2D woodTex;

vec3 phong(vec3 matAmbientColor, vec3 matDiffuseColor, vec3 matSpecularColor, float matShininess);

void main() {
    vec3 color = texture(woodTex, IN.vTexCoord).rgb;

    vec3 lighting = phong(color, color, vec3(1), 32);

    fragColor = vec4(lighting, 1.0);
}

/*
 * Computes lighting using Blinn-Phong model.
 */
vec3 phong(vec3 matAmbientColor, vec3 matDiffuseColor, vec3 matSpecularColor, float matShininess) {
    vec3 N = normalize(IN.vNormal);

    vec3 lightDirection;
    if (light.position.w == 0.0) {
        lightDirection = normalize(light.position.xyz);
    } else {
        lightDirection = normalize(light.position.xyz - IN.vPosition);
    }

    vec3 viewDirection = normalize(eyePosition - IN.vPosition);
    vec3 halfVector = normalize(lightDirection + viewDirection);

    vec3 ambient = light.ambientColor.rgb * matAmbientColor;

    float diffuseFactor = max(dot(N, lightDirection), 0.0);
    vec3 diffuse = light.diffuseColor.rgb * matDiffuseColor * diffuseFactor;

    float specularFactor = pow(max(dot(N, halfVector), 0.0), matShininess) * diffuseFactor;
    vec3 specular = light.specularColor.rgb * matSpecularColor * specularFactor;

    return ambient + diffuse + specular;
}
