package com.vlsm.vlsmcalculator;

import android.annotation.SuppressLint;

import androidx.core.internal.view.SupportMenu;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.util.Arrays;

class InetAddresses {
    private static final int IPV4_PART_COUNT = 4;
    private static final int IPV6_PART_COUNT = 8;

    static boolean isMappedIPv4Address(String ipString) {
        byte[] bytes = ipStringToBytes(ipString);
        if (bytes == null || bytes.length != 16) {
            return false;
        }
        int i;
        for (i = 0; i < 10; i++) {
            if (bytes[i] != (byte) 0) {
                return false;
            }
        }
        for (i = 10; i < 12; i++) {
            if (bytes[i] != (byte) -1) {
                return false;
            }
        }
        return true;
    }

    static byte[] ipStringToBytes(String ipString) {
        boolean hasColon = false;
        boolean hasDot = false;
        for (int i = 0; i < ipString.length(); i++) {
            char c = ipString.charAt(i);
            if (c == '.') {
                hasDot = true;
            } else if (c == ':') {
                if (hasDot) {
                    return null;
                }
                hasColon = true;
            } else if (Character.digit(c, 16) == -1) {
                return null;
            }
        }
        if (hasColon) {
            if (hasDot) {
                ipString = convertDottedQuadToHex(ipString);
                if (ipString == null) {
                    return null;
                }
            }
            return textToNumericFormatV6(ipString);
        } else if (hasDot) {
            return textToNumericFormatV4(ipString);
        } else {
            return null;
        }
    }

    static byte[] textToNumericFormatV4(String ipString) {
        String[] address = ipString.split("\\.", 5);
        if (address.length != 4) {
            return null;
        }
        byte[] bytes = new byte[4];
        int i = 0;
        while (i < bytes.length) {
            try {
                bytes[i] = parseOctet(address[i]);
                i++;
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return bytes;
    }

    static byte[] textToNumericFormatV6(String ipString) {
        String[] parts = ipString.split(":", 10);
        if (parts.length < 3 || parts.length > 9) {
            return null;
        }
        int i;
        int partsHi;
        int partsLo;
        int skipIndex = -1;
        for (i = 1; i < parts.length - 1; i++) {
            if (parts[i].length() == 0) {
                if (skipIndex >= 0) {
                    return null;
                }
                skipIndex = i;
            }
        }
        if (skipIndex >= 0) {
            partsHi = skipIndex;
            partsLo = (parts.length - skipIndex) - 1;
            if (parts[0].length() == 0) {
                partsHi--;
                if (partsHi != 0) {
                    return null;
                }
            }
            if (parts[parts.length - 1].length() == 0) {
                partsLo--;
                if (partsLo != 0) {
                    return null;
                }
            }
        }
        partsHi = parts.length;
        partsLo = 0;
        int partsSkipped = 8 - (partsHi + partsLo);
        if (skipIndex >= 0) {
            if (partsSkipped < 1) {
                return null;
            }
        } else if (partsSkipped != 0) {
            return null;
        }
        ByteBuffer rawBytes = ByteBuffer.allocate(16);
        i = 0;
        while (i < partsHi) {
            try {
                rawBytes.putShort(parseHextet(parts[i]));
                i++;
            } catch (NumberFormatException e) {
                return null;
            }
        }
        for (i = 0; i < partsSkipped; i++) {
            rawBytes.putShort((short) 0);
        }
        for (i = partsLo; i > 0; i--) {
            rawBytes.putShort(parseHextet(parts[parts.length - i]));
        }
        return rawBytes.array();
    }

    private static String convertDottedQuadToHex(String ipString) {
        int lastColon = ipString.lastIndexOf(58);
        String initialPart = ipString.substring(0, lastColon + 1);
        byte[] quad = textToNumericFormatV4(ipString.substring(lastColon + 1));
        if (quad == null) {
            return null;
        }
        String penultimate = Integer.toHexString(((quad[0] & 255) << 8) | (quad[1] & 255));
        return initialPart + penultimate + ":" + Integer.toHexString(((quad[2] & 255) << 8) | (quad[3] & 255));
    }

    private static byte parseOctet(String ipPart) {
        int octet = Integer.parseInt(ipPart);
        if (octet <= 255 && (!ipPart.startsWith("0") || ipPart.length() <= 1)) {
            return (byte) octet;
        }
        throw new NumberFormatException();
    }

    @SuppressLint("RestrictedApi")
    private static short parseHextet(String ipPart) {
        int hextet = Integer.parseInt(ipPart, 16);
        if (hextet <= SupportMenu.USER_MASK) {
            return (short) hextet;
        }
        throw new NumberFormatException();
    }

    static String toAddrString(InetAddress ip) {
        if (ip == null) {
            return null;
        }
        if (ip instanceof Inet4Address) {
            return ip.getHostAddress();
        }
        if (!(ip instanceof Inet6Address)) {
            return null;
        }
        byte[] bytes = ip.getAddress();
        int[] hextets = new int[8];
        for (int i = 0; i < hextets.length; i++) {
            hextets[i] = fromBytes((byte) 0, (byte) 0, bytes[i * 2], bytes[(i * 2) + 1]);
        }
        compressLongestRunOfZeroes(hextets);
        return hextetsToIPv6String(hextets);
    }

    static int fromBytes(byte b1, byte b2, byte b3, byte b4) {
        return (((b1 << 24) | ((b2 & 255) << 16)) | ((b3 & 255) << 8)) | (b4 & 255);
    }

    static void compressLongestRunOfZeroes(int[] hextets) {
        int bestRunStart = -1;
        int bestRunLength = -1;
        int runStart = -1;
        int i = 0;
        while (i < hextets.length + 1) {
            if (i >= hextets.length || hextets[i] != 0) {
                if (runStart >= 0) {
                    int runLength = i - runStart;
                    if (runLength > bestRunLength) {
                        bestRunStart = runStart;
                        bestRunLength = runLength;
                    }
                    runStart = -1;
                }
            } else if (runStart < 0) {
                runStart = i;
            }
            i++;
        }
        if (bestRunLength >= 2) {
            Arrays.fill(hextets, bestRunStart, bestRunStart + bestRunLength, -1);
        }
    }

    private static String hextetsToIPv6String(int[] hextets) {
        StringBuilder buf = new StringBuilder(39);
        boolean lastWasNumber = false;
        for (int i = 0; i < hextets.length; i++) {
            boolean thisIsNumber = hextets[i] >= 0;
            if (thisIsNumber) {
                if (lastWasNumber) {
                    buf.append(':');
                }
                buf.append(Integer.toHexString(hextets[i]));
            } else if (i == 0 || lastWasNumber) {
                buf.append("::");
            }
            lastWasNumber = thisIsNumber;
        }
        return buf.toString();
    }
}
