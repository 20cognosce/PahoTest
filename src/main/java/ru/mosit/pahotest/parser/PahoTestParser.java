package ru.mosit.pahotest.parser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PahoTestParser {

    public static void main(String[] args) throws IOException {
        System.out.println(Files.readString(Paths.get("data.json")));
        System.out.println(Files.readString(Paths.get("data.xml")));
    }
}
