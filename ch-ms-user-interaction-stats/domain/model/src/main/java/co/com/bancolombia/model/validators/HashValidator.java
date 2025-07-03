package co.com.bancolombia.model.validators;

import co.com.bancolombia.model.userinteractionstats.userinteractionstats.UserInteractionStats;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashValidator implements IHashValidator {

    public boolean isValid(UserInteractionStats stats) {
        String concatenated = concatenateFields(stats);
        String expectedHash = md5Hex(concatenated);
        return expectedHash.equals(stats.getHash());
    }

    public String generateHash(UserInteractionStats stats) {
        return md5Hex(concatenateFields(stats));
    }

    private String md5Hex(String input) {
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
            throw new IllegalStateException("MD5 algorithm not available", e);
        }
    }

    private String concatenateFields(UserInteractionStats stats) {
        return String.format("%d,%d,%d,%d,%d,%d,%d",
                stats.getTotalContactoClientes(),
                stats.getMotivoReclamo(),
                stats.getMotivoGarantia(),
                stats.getMotivoDuda(),
                stats.getMotivoCompra(),
                stats.getMotivoFelicitaciones(),
                stats.getMotivoCambio()
        );
    }
}