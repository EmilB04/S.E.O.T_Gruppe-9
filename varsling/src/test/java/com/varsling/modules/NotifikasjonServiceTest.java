package com.varsling.modules;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class NotifikasjonServiceTest {

    private NotifikasjonService notifikasjonService = new NotifikasjonService();

    //Tester for stromprisnotifikasjon
    @Test
    public void testGratisStrom() {
        //Act
        Notifikasjon notifikasjon = notifikasjonService.getStromprisNotifikasjon(true, 0);
        
        //Assert
        assertNotNull(notifikasjon);
        assertEquals("23451", notifikasjon.getToken());
        assertEquals("Strømprisvarsel", notifikasjon.getTitle());
        assertEquals("Strømmen er gratis nå, du burde lade", notifikasjon.getBody());
    }

    @Test
    public void testStromUnder0_5() {
        //Act
        Notifikasjon notifikasjon = notifikasjonService.getStromprisNotifikasjon(true, 0.4);
        
        //Assert
        assertNotNull(notifikasjon);
        assertEquals("12345", notifikasjon.getToken());
        assertEquals("Strømprisvarsel", notifikasjon.getTitle());
        assertEquals("Strømmen er billig nå, du burde lade", notifikasjon.getBody());
    }

    @Test
    public void testStromOver1_5() {
        //Act
        Notifikasjon notifikasjon = notifikasjonService.getStromprisNotifikasjon(true, 2);
        
        //Assert
        assertNotNull(notifikasjon);
        assertEquals("34512", notifikasjon.getToken());
        assertEquals("Strømprisvarsel", notifikasjon.getTitle());
        assertEquals("Strømmen er over 1.5 kr pre Kw, du burde vente med å lade", notifikasjon.getBody());
    }

    @Test
    public void testStromOver5() {
        //Act
        Notifikasjon notifikasjon = notifikasjonService.getStromprisNotifikasjon(true, 6);
        
        //Assert
        assertNotNull(notifikasjon);
        assertEquals("45123", notifikasjon.getToken());
        assertEquals("Strømprisvarsel", notifikasjon.getTitle());
        assertEquals("Strømmen er over 5 kr pre Kw, du burde ikke lade nå hvis du ikke må", notifikasjon.getBody());
    }
    

     // Tester for stromnivånotifikasjon
    @Test
    public void testStromnivaUnder5() {
        //Act
        Notifikasjon notifikasjon = notifikasjonService.getStromnivaNotifikasjon("Home", 4);
        
        //Assert
        assertNotNull(notifikasjon);
        assertEquals("54321", notifikasjon.getToken());
        assertEquals("Batterinotifikasjon", notifikasjon.getTitle());
        assertEquals("Batterinivået er under 5%, du burde lade", notifikasjon.getBody());
    }

    @Test
    public void testStromnivaUnder20() {
        //Act
        Notifikasjon notifikasjon = notifikasjonService.getStromnivaNotifikasjon("Home", 15);
        
        //Assert
        assertNotNull(notifikasjon);
        assertEquals("15432", notifikasjon.getToken());
        assertEquals("Batterinotifikasjon", notifikasjon.getTitle());
        assertEquals("Batterinivået er under 20%, du burde lade", notifikasjon.getBody());
    }


    @Test
    public void testStromnivaOver50() {
        //Act
        Notifikasjon notifikasjon = notifikasjonService.getStromnivaNotifikasjon("Home", 60);
        
        //Assert
        assertNotNull(notifikasjon);
        assertEquals("21543", notifikasjon.getToken());
        assertEquals("Batterinotifikasjon", notifikasjon.getTitle());
        assertEquals("Batterinivået er under 50%", notifikasjon.getBody());
    }

    @Test
    public void testStromnivaOver80() {
        //Act
        Notifikasjon notifikasjon = notifikasjonService.getStromnivaNotifikasjon("Home", 85);
        
        //Assert
        assertNotNull(notifikasjon);
        assertEquals("32154", notifikasjon.getToken());
        assertEquals("Batterinotifikasjon", notifikasjon.getTitle());
        assertEquals("Batteriet er over 80%, det er ikke henisktsmessig å lade nå", notifikasjon.getBody());
    }

}
