package br.com.phricardo.schedulingtechnicians.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ServiceOrderUtilTest {

    @Test
    public void generate_checkLength() {
        String generated = ServiceOrderUtil.generate();
        assertEquals(13, generated.length());
    }

    @Test
    public void generate_checkIfContainsOnlyNumbers() {
        String generated = ServiceOrderUtil.generate();
        assertTrue(generated.matches("\\d+"));
    }
}