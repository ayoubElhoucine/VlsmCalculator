package com.vlsm.vlsmcalculator;

class Converter {
    static String convertIPIntDec2StringBinary(int intIP) {
        String stringIP = Integer.toBinaryString(intIP);
        int length = stringIP.length();
        if (length < 32) {
            int prependZeros = 32 - length;
            for (int i = 0; i < prependZeros; i++) {
                stringIP = "0" + stringIP;
            }
        }
        String octet1 = stringIP.substring(0, 8);
        String octet2 = stringIP.substring(8, 16);
        String octet3 = stringIP.substring(16, 24);
        return octet1 + "." + octet2 + "." + octet3 + "." + stringIP.substring(24, 32);
    }

    static String convertIPIntDec2StringHex(int intIP) {
        String stringIP = Integer.toHexString(intIP);
        int length = stringIP.length();
        if (length < 8) {
            int prependZeros = 8 - length;
            for (int i = 0; i < prependZeros; i++) {
                stringIP = "0" + stringIP;
            }
        }
        String octet1 = stringIP.substring(0, 2);
        String octet2 = stringIP.substring(2, 4);
        String octet3 = stringIP.substring(4, 6);
        return octet1 + "." + octet2 + "." + octet3 + "." + stringIP.substring(6, 8);
    }

    static int stringIPtoInt(String ip) throws Exception {
        String[] quad = ip.split("\\.", 4);
        if (quad.length != 4) {
            throw new Exception();
        }
        int ip32bit = 0;
        int length = quad.length;
        int i = 0;
        while (i < length) {
            String value = quad[i];
            if (value.length() < 1) {
                throw new Exception();
            }
            try {
                int octet = Integer.parseInt(value);
                if (octet > 255) {
                    throw new Exception();
                }
                ip32bit = (ip32bit << 8) | octet;
                i++;
            } catch (NumberFormatException e) {
                throw new Exception();
            }
        }
        return ip32bit;
    }
}
