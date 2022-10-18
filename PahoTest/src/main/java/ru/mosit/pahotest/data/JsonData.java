package ru.mosit.pahotest.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Data-class для хранения информации в JSON
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JsonData {
    private Float power;
    private Integer motion;
    private Float temperature;
    private Float sound;

    public JsonData(JsonData another) {
        this.power = another.getPower();
        this.motion = another.getMotion();
        this.temperature = another.getTemperature();
        this.sound = another.getSound();
    }
}
