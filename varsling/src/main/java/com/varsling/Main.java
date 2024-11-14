package com.varsling;

import com.varsling.modules.FirebaseInitializer;
import com.varsling.modules.PushNotifikasjonSender;

public class Main {
    public static void main(String[] args) {
        // Initialiser Firebase
        FirebaseInitializer.initializeFirebaseApp();

        // Opprett PushNotifikasjonSender
        PushNotifikasjonSender notifikasjonSender = new PushNotifikasjonSender();

        // Send en notifikasjon basert på strømpris (for eksempel)
        double strompris = 0.4; // Eksempelverdi for strømpris
        boolean tillatelse = true; // Anta at tillatelse er gitt

        notifikasjonSender.sendStromprisNotifikasjon(tillatelse, strompris);
    }
}
