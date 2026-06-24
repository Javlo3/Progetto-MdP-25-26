package org.progetto;

import javafx.application.Application;
import org.progetto.GUI.GUI;

public class Main {
    static void main(String[] args) {
        System.out.println("Avvio del sistema...");
        Application.launch(GUI.class, args);
    }
}