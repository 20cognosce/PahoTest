package ru.mosit.pahotest.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JsonData {
    private Double power;
    private Integer motion;
    private Double temperature;
    private Double sound;
    private String time;
    private String caseNumber;

    public JsonData(JsonData jsonData) {
        this.power = jsonData.getPower();
        this.motion = jsonData.getMotion();
        this.temperature = jsonData.getTemperature();
        this.sound = jsonData.getSound();
        this.time = jsonData.getTime();
        this.caseNumber = jsonData.getCaseNumber();
    }
}
