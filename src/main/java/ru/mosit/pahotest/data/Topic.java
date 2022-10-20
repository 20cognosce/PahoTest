package ru.mosit.pahotest.data;

import lombok.Getter;

@Getter
public enum Topic {

    /* Вариант №4
    * 1. Датчик движения устройства WB-MSW v.3 (5)
    * 2. Датчик шума устройства WB-MSW v.3 (5)
    * 3. Датчик освещенности устройства WB-MS v.2 (12)
    * 4. Датчик температуры устройства WB-MS v.2 (12)
    * */
    POWER("/devices/wb-map12e_23/controls/Ch 1 P L2"),
    MOTION("/devices/wb-msw-v3_21/controls/Current Motion"),
    TEMPERATURE("/devices/wb-ms_11/controls/Temperature"),
    SOUND("/devices/wb-msw-v3_21/controls/Sound Level");

    private final String value;
    Topic(String value) {
        this.value = value;
    }
}
