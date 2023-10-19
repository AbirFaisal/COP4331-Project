import org.junit.jupiter.api.Test;

import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.CipherSpi;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Random;

public class AliceAndBob {

    BigInteger[] genPrimesArray(int len) {
        BigInteger[] secretNumbers = new BigInteger[len];

        for (int i = 0; i < len; i++) {
            secretNumbers[i] = BigInteger.probablePrime(16, new Random());
        }
        return secretNumbers;
    }

    BigInteger[] genSecretMods(BigInteger[] commonMods, BigInteger[] commonBases, BigInteger[] secretMods) {
        BigInteger[] secretSauce = new BigInteger[secretMods.length];

        for (int i = 0; i < secretMods.length; i++) {
            BigInteger base = commonBases[i];
            BigInteger mod = secretMods[i]; //exponent

            //= base^mod % common_mod
            secretSauce[i] = base.modPow(mod, commonMods[i]);
        }
        return secretSauce;
    }


    private class TestObject {
        //object for encrypt/decrypt test
        private int i;
        public TestObject(int i){
            this.i = i;
        }
        public int getI() {
            return i;
        }
        public TestObject setI(int i) {
            this.i = i;
            return this;
        }
    }


    @Test
    public void simDiffieHellmanKeyExchange() throws Exception {

        int len = 1;

        BigInteger[] pubMods = genPrimesArray(len);
        BigInteger[] pubBases = genPrimesArray(len);

        System.out.println("pubMods: " + Arrays.toString(pubMods));
        System.out.println("pubBases: " +  Arrays.toString(pubBases));

        //Alice's Secret
        BigInteger[] alicePrivateMods = genPrimesArray(len);
        System.out.println("aliceSecret: " +  Arrays.toString(alicePrivateMods));

        //Bob's Secret
        BigInteger[] bobPrivateMods = genPrimesArray(len);
        System.out.println("bobSecret: " +  Arrays.toString(bobPrivateMods));

        //generate a public key given the public and private mods
        BigInteger[] alicePubMods = genSecretMods(pubMods, pubBases, alicePrivateMods);
        System.out.println("alicePubMods: " +  Arrays.toString(alicePubMods));

        BigInteger[] bobPubMods = genSecretMods(pubMods, pubBases, bobPrivateMods);
        System.out.println("bobPubMods:   " +  Arrays.toString(bobPubMods));

        //Alice x Bob Key Exchange and Mix
        BigInteger[] commonSecret1 = genSecretMods(pubMods, bobPubMods, alicePrivateMods);
        System.out.println("commonSecret1: " +  Arrays.toString(commonSecret1));

        //Bob x Alice Key Exchange and Mix
        BigInteger[] commonSecret2 = genSecretMods(pubMods, alicePubMods, bobPrivateMods);
        System.out.println("commonSecret2: " +  Arrays.toString(commonSecret2));


        //Test Encryption/Decryption
        TestObject testObject = new TestObject(123);


        String cipherMode = "AES/CBC/PKCS5Padding";
        Cipher cipher;
        cipher = Cipher.getInstance(cipherMode);
        //TODO cipher.init(Cipher.ENCRYPT_MODE, secretKey, IvParameterSpec);

        OutputStream outputStream = null; //TODO

        CipherOutputStream cipherOutputStream = new CipherOutputStream(outputStream, cipher);

        cipherOutputStream.write(0);



        for (int i = 0; i < len; i++) {
            assert commonSecret1[i].equals(commonSecret2[i]) : "Secrets are not common";
        }
    }

}
