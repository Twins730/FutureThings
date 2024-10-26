#version 150

#moj_import <fog.glsl>

uniform sampler2D Sampler0;
        
uniform float Time;
uniform float FogStart;
uniform float FogEnd;
uniform vec4 FogColor;

in float vertexDistance;
in vec4 vertexColor;
in vec4 lightMapColor;
in vec4 overlayColor;
in vec2 texCoord0;

out vec4 fragColor;

void main() {
    vec4 color = texture(Sampler0, texCoord0);
    if (color.a < 0.1) {
        discard;
    }
    float value = (color.r + color.g + color.b) / 3;
    vec4 colorgrayscale = vec4(value / 4, value / 2, value, (pow(sin((texCoord0.y * 3.14159265359) * 50 + Time) / 2, 2) + 0.60));

    fragColor = linear_fog(colorgrayscale, vertexDistance, FogStart, FogEnd, FogColor);
}
