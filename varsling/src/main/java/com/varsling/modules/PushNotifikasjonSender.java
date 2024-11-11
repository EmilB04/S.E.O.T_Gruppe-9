package com.varsling.modules;

import com.google.firebase.messaging.Notification;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.FirebaseMessaging;

public class PushNotifikasjonSender {

    private NotifikasjonService notifikasjonService;

    public PushNotifikasjonSender() {
        this.notifikasjonService = new NotifikasjonService();
    }

    /**
     * Sender en notifikasjon basert på strømpris, hvis perameterene er oppfylt riktig.
     */
    public void sendStromprisNotifikasjon(boolean tillatelse, double strompris) {
        Notifikasjon notifikasjon = notifikasjonService.getStromprisNotifikasjon(tillatelse, strompris);
        if (notifikasjon != null) {
            sendNotifikasjonTilFirebase(notifikasjon);
        }
    }

    /**
     * Sender en notifikasjon basert på strømnivået på bilen, 
     * hvis perameterene er oppfylt riktig.
     */
    public void sendStromnivaNotifikasjon(String lokasjon, int stromniva) {
        Notifikasjon notifikasjon = notifikasjonService.getStromnivaNotifikasjon(lokasjon, stromniva);
        if (notifikasjon != null) {
            sendNotifikasjonTilFirebase(notifikasjon);
        }
    }

    /**
     * Privat metode for å sende en notifikasjon til Firebase.
     */
    private void sendNotifikasjonTilFirebase(Notifikasjon notifikasjon) {
        Notification notification = Notification.builder()
                .setTitle(notifikasjon.getTitle())
                .setBody(notifikasjon.getBody())
                .build();

        Message message = Message.builder()
                .setToken(notifikasjon.getToken())
                .setNotification(notification)
                .build();

        try {
            FirebaseMessaging.getInstance().send(message);
            //Utskriften er teknisk sett ikke nødvendig, kan fjernes etter testing
            System.out.println("Notifikasjonen ble sendt");
        } catch (Exception e) {
            System.out.println("Det oppstod en feil under sending av notifikasjonen");
            e.printStackTrace();
        }
    }
}
