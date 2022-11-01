package ru.mosit.pahotest;

import com.google.gson.Gson;
import org.eclipse.paho.client.mqttv3.*;
import ru.mosit.pahotest.data.JsonData;
import ru.mosit.pahotest.data.Topic;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class PahoTest {

    private static final String host = "192.168.2.25"; // IP-адрес чемодана
    private static final Integer port = 1883; // Порт по которому подключаемся к чемодану
    private static final List<JsonData> jsonDataList = new ArrayList<>(); // Список будущих ответов от сервера
    private static final Gson gson = new Gson(); // Объект класса GSON для парсинга в JSON

    // TODO: Запустить и в папке проекта создастся data.json и (не протестировано) data.xml
    // TODO: В Topic указать другие имена датчиков!!!
    public static void main(String[] args) throws MqttException {

        try (MqttClient client = new MqttClient(
                String.format("tcp://%s:%d", host, port),
                MqttClient.generateClientId())) {

            // Объект для хранения данных
            JsonData jsonData = new JsonData(0, 0.0, 0, 0.0, "", "25");

            // Переопределение методов для обработки событий, полученных от сервера
            client.setCallback(new MqttCallback() {
                @Override
                public void connectionLost(Throwable cause) {
                    cause.printStackTrace();
                }

                @Override
                public void messageArrived(String topic, MqttMessage message) {
                    String payload = new String(message.getPayload());
                    System.out.println(topic + ": " + payload);

                    if (Objects.equals(topic, Topic.MOTION.getValue())) jsonData.setMotion(Integer.valueOf(payload));
                    if (Objects.equals(topic, Topic.SOUND.getValue())) jsonData.setSound(Double.valueOf(payload));
                    if (Objects.equals(topic, Topic.ILLUMINANCE.getValue())) jsonData.setIlluminance(Integer.valueOf(payload));
                    if (Objects.equals(topic, Topic.TEMPERATURE.getValue())) jsonData.setTemperature(Double.valueOf(payload));
                    jsonData.setTime(LocalDateTime.now().toString());

                    jsonDataList.add(new JsonData(jsonData));
                    writeToJsonFile(gson.toJson(jsonDataList));
                    writeToXmlFile(gson.toJson(jsonDataList)); //TODO: XML не протестирован. JSON точно пишет.
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken token) {
                    System.out.println("Delivery completed: " + token);
                }
            });

            client.connect();

            // Подписывание на топики
            for (Topic topic : Topic.values()) {
                client.subscribe(topic.getValue(), 1);
            }
        }
    }

    private static void writeToJsonFile(String data) {
        try {
            Files.write(Paths.get("data.json"), data.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeToXmlFile(String data) {
        try {
            Files.write(Paths.get("data.xml"), data.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
