#version 330

// Task 6: add in variable for color of type vec3
in vec3 vNormal;
in vec3 vPosition;
in vec2 vTexCoord;
in vec3 vColor;

out vec4 fragColor;

// Task 7: put LightData struct around the light variables
//           syntax is layout (std140) uniform LightData { .... };
//           change all variables to vec4
layout (std140) uniform LightData
{
    vec4 lightPosition;
    vec4 lightAmbientColor;
    vec4 lightDiffuseColor;
    vec4 lightSpecularColor;
};

uniform vec3 eyePosition;

vec3 phong(vec3 matAmbientColor, vec3 matDiffuseColor, vec3 matSpecularColor, float matShininess);

void main() {
    vec2 repeat = vec2(4.0);
    float offset = 0.5 * floor(repeat.t * vTexCoord.t);
    vec2 granko_coord = fract(repeat * vTexCoord + vec2(offset, 0));
    float granko = step(0.3, length(granko_coord - 0.5));
    // Task 6: change red to input color, so we have differently dotted dots
    //vec3 color = mix(vColor, vec3(1.0), granko);
    vec3 color = vec3(1,1,0);

    vec3 lighting = phong(color, color, vec3(1), 32);

    fragColor = vec4(lighting, 1.0);
}

/*
 * Computes lighting using Blinn-Phong model.
 *
 * Task 7: reflect the changes in types to vec4 on light properties
 */
vec3 phong(vec3 matAmbientColor, vec3 matDiffuseColor, vec3 matSpecularColor, float matShininess) {
    vec3 N = normalize(vNormal);

    vec3 lightDirection;
    if (lightPosition.w == 0.0) {
        lightDirection = normalize(lightPosition.xyz);
    } else {
        lightDirection = normalize(lightPosition.xyz - vPosition);
    }

    vec3 viewDirection = normalize(eyePosition - vPosition);
    vec3 halfVector = normalize(lightDirection + viewDirection);

    vec3 ambient = lightAmbientColor.xyz * matAmbientColor;

    float diffuseFactor = max(dot(N, lightDirection), 0.0);
    vec3 diffuse = lightDiffuseColor.xyz * matDiffuseColor * diffuseFactor;

    float specularFactor = pow(max(dot(N, halfVector), 0.0), matShininess) * diffuseFactor;
    vec3 specular = lightSpecularColor.xyz * matSpecularColor * specularFactor;

    return ambient + diffuse + specular;
}
