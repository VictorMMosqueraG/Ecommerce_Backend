package v.o.r.ecommerce.common.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Service;

@Service
public class HasMap {

    public String encodePassword(String password) {
        try {
            // Create an instance of the SHA-256 hash algorithm
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // Calculate the hash of the password
            byte[] hashedBytes = digest.digest(password.getBytes());

            // Convert the hashed bytes to hexadecimal representation
            StringBuilder stringBuilder = new StringBuilder();
            for (byte hashedByte : hashedBytes) {
                stringBuilder.append(Integer.toString((hashedByte & 0xff) + 0x100, 16).substring(1));
            }

            // Return the hexadecimal representation of the hash
            return stringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
