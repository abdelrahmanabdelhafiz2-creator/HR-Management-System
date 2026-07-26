package util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * أداة تشفير الباسورد.
 * بنستخدم SHA-256 + Salt عشوائي بدل ما نخزن الباسورد كـ plain text.
 * الشكل المخزن في الداتا بيز: base64(salt) + ":" + base64(hash)
 */
public final class PasswordUtil {

    private static final String ALGORITHM = "SHA-256";
    private static final int SALT_LENGTH = 16;

    private PasswordUtil() {
    }

    /**
     * بيعمل Hash لباسورد جديد (وقت التسجيل / التعديل).
     */
    public static String hash(String plainPassword) {
        try {
            byte[] salt = generateSalt();
            byte[] hashed = digest(plainPassword, salt);

            return Base64.getEncoder().encodeToString(salt)
                    + ":" + Base64.getEncoder().encodeToString(hashed);

        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("Password hashing algorithm not available", e);
        }
    }

    /**
     * بيتأكد إن الباسورد اللي المستخدم كاتبه بيطابق الـ hash المخزن.
     */
    public static boolean verify(String plainPassword, String storedValue) {
        if (plainPassword == null || storedValue == null || !storedValue.contains(":")) {
            return false;
        }

        try {
            String[] parts = storedValue.split(":", 2);
            byte[] salt = Base64.getDecoder().decode(parts[0]);
            byte[] expectedHash = Base64.getDecoder().decode(parts[1]);

            byte[] actualHash = digest(plainPassword, salt);

            return constantTimeEquals(expectedHash, actualHash);

        } catch (Exception e) {
            return false;
        }
    }

    /**
     * بيتأكد هل القيمة المخزنة بالفعل عبارة عن hash (salt:hash) ولا لسه plain text.
     * مفيدة لدعم بيانات قديمة كانت متخزنة قبل إضافة التشفير.
     */
    public static boolean isHashed(String storedValue) {
        if (storedValue == null || !storedValue.contains(":")) {
            return false;
        }
        String[] parts = storedValue.split(":", 2);
        return parts.length == 2 && isBase64(parts[0]) && isBase64(parts[1]);
    }

    private static boolean isBase64(String value) {
        try {
            Base64.getDecoder().decode(value);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    private static byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_LENGTH];
        random.nextBytes(salt);
        return salt;
    }

    private static byte[] digest(String password, byte[] salt) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance(ALGORITHM);
        digest.update(salt);
        return digest.digest(password.getBytes(java.nio.charset.StandardCharsets.UTF_8));
    }

    private static boolean constantTimeEquals(byte[] a, byte[] b) {
        if (a.length != b.length) {
            return false;
        }
        int result = 0;
        for (int i = 0; i < a.length; i++) {
            result |= a[i] ^ b[i];
        }
        return result == 0;
    }
}
