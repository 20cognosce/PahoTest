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
    private Integer motion;
    private Double sound;
    private Integer illuminance;
    private Double temperature;
    private String time;
    private String caseNumber;

    public JsonData(JsonData jsonData) {
        this.motion = jsonData.getMotion();
        this.sound = jsonData.getSound();
        this.illuminance = jsonData.getIlluminance();
        this.temperature = jsonData.getTemperature();
        this.time = jsonData.getTime();
        this.caseNumber = jsonData.getCaseNumber();
    }
}
