package br.com.phricardo.schedulingtechnicians.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class TechnicianRegistrationUtilTest {

    @Test
    public void generate_checkRange() {
        Long generated = TechnicianRegistrationUtil.generate();
        assertTrue(generated >= 10000L && generated <= 99999L);
    }
}