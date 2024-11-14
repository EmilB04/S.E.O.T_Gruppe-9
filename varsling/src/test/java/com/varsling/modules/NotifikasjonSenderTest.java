package com.varsling.modules;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class NotifikasjonSenderTest {

    @Mock
    private NotifikasjonService notifikasjonServiceMock; // Mock NotifikasjonService

    @InjectMocks
    private PushNotifikasjonSender pushNotifikasjonSender; // Inject mock service into PushNotifikasjonSender

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialiserer Mockito-mocks
    }

    // Test for å sende en notifikasjon basert på strømpris
    @Test
    public void testSendStromprisNotifikasjon() {
        // Arrange
        boolean tillatelse = true;
        double strompris = 0.3;
        Notifikasjon notifikasjon = new Notifikasjon("12345", "Gunstig lading", "Strømmen er billig nå, du burde lade");

        // Mocker responsen fra NotifikasjonService
        when(notifikasjonServiceMock.getStromprisNotifikasjon(tillatelse, strompris)).thenReturn(notifikasjon);

        // Act
        pushNotifikasjonSender.sendStromprisNotifikasjon(tillatelse, strompris);

        // Assert
        assertEquals("12345", notifikasjon.getToken());
        assertEquals("Gunstig lading", notifikasjon.getTitle());
        assertEquals("Strømmen er billig nå, du burde lade", notifikasjon.getBody());
    }
}
