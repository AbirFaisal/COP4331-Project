import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Random;

public class AliceAndBob {

    BigInteger[] genPrimesArray(int len) {
        BigInteger[] secretNumbers = new BigInteger[len];

        for (int i = 0; i < len; i++) {
            secretNumbers[i] = BigInteger.probablePrime(32, new Random());
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


    @Test
    public void simDiffieHellmanKeyExchange() {

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

        //mix privKeyPubKey
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

        
        for (int i = 0; i < len; i++) {
            assert commonSecret1[i].equals(commonSecret2[i]) : "Secrets are not common";
        }
    }

//    BigDecimal bigPow(BigDecimal base, BigDecimal exp) {
//
//        return new BigDecimal.valueOf(0);
//    }


}
