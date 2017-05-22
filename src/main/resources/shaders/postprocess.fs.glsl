#version 330

uniform float animation_time;
uniform sampler2D sceneTex;

in vec2 vTexCoord;

out vec4 final_color;

void main() {
    vec2 tex_coord = vTexCoord;

    // Task 3: try offsetting the texturing coordinates based on animation time
    //           for example tex_coord.x += sin(animation_time) * 0.1;
    //           what happens when it goes over 1.0? remember how we set the texture WRAP parameters?
    //         try different offset based on texturing coordinates themselves, make different rows offset differently
    //           for example tex_coord.x += sin(animation_time + 15 * vTexCoord.t) * 0.1;
    //         make more smaller waves by increasing the frequency and decreasing the amplitude
    //           tex_coord.x += sin(animation_time + 45 * vTexCoord.t) * 0.03;
    // Task 5: remove the animation
    // tex_coord.x += sin(animation_time + 45 * vTexCoord.t) * 0.03;

    vec4 color = texture(sceneTex, tex_coord);

    // Task 2: try displaying single color channels from the texture, what will it look like?
    //           convert the image to grayscale, display only brightness
    //             brightness of RGB color can be computed for example by averaging (r+g+b)/3
    //             or better by weighted averaging based on human perception of colors, i.e., 0.299*r+0.587*g+0.114*b (weights from NTSC standard)
    // Task 5: remove the grayscale
    // float brightness = 0.299*color.r + 0.587*color.g + 0.114*color.b;
    final_color = vec4(color);
}
