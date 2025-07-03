package co.com.bancolombia.model.validators;

import co.com.bancolombia.model.userinteractionstats.userinteractionstats.UserInteractionStats;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.function.Function;

public class HashValidator {

    private static final Function<String, String> md5Hex = input -> {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(input.getBytes(StandardCharsets.UTF_8));

            StringBuilder hexString = new StringBuilder();
            for (byte b : digest) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 algorithm not available", e);
        }
    };

    private static final Function<UserInteractionStats, String> concatenateFields = stats ->
            String.format("%d,%d,%d,%d,%d,%d,%d",
                    stats.getTotalContactoClientes(),
                    stats.getMotivoReclamo(),
                    stats.getMotivoGarantia(),
                    stats.getMotivoDuda(),
                    stats.getMotivoCompra(),
                    stats.getMotivoFelicitaciones(),
                    stats.getMotivoCambio()
            );

    public static final Function<UserInteractionStats, Boolean> isValid = stats -> {
        String concatenated = concatenateFields.apply(stats);
        String expectedHash = md5Hex.apply(concatenated);
        boolean result = expectedHash.equals(stats.getHash());
        return result;
    };
}
