package application.utils;

import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class Generator {
    private static final String LOWER = "qwertyuiopasdfghjklzxcvbnm";
    private static final String UPPER = "QWERTYUIOPASDFGHJKLZXCVBNM";
    private static final String NUMBERS = "1234567890";
    private static final short TOKEN_LENGTH = 32;
    private static final Random RANDOM = new Random();

    @NotNull
    public static String generateToken() {
        return generate(TOKEN_LENGTH);
    }

    @NotNull
    private static String generate(short length){
        StringBuilder result = new StringBuilder();
        for (short i = 0; i < length; ++i) {
            switch (RANDOM.nextInt(3)) {
                case 0:
                    result.append(LOWER.charAt(RANDOM.nextInt(LOWER.length())));
                    break;
                case 1:
                    result.append(UPPER.charAt(RANDOM.nextInt(UPPER.length())));
                    break;
                case 2:
                    result.append(NUMBERS.charAt(RANDOM.nextInt(NUMBERS.length())));
                    break;
            }
        }
        return result.toString();
    }

}
