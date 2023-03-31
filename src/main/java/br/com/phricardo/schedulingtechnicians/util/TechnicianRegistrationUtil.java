package br.com.phricardo.schedulingtechnicians.util;

import java.util.Random;

public class TechnicianRegistrationUtil {

    public static Long generate() {
      return 10000L + new Random().nextInt(90000);
    }
}
