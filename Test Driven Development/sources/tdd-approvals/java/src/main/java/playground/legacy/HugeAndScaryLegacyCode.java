package playground.legacy;

public class HugeAndScaryLegacyCode {

    public static String TheUgliesMethodYouMightEverSeen(String s, int i, char c) {

        if (s.length() > 5) {
            s += "_some_suffix";
        }

        StringBuilder r = new StringBuilder();
        for (int j = 0; j < s.length(); j++) {
            char k = s.charAt(j);
            if ((int) k % i == 0) {
                r.append(c);
            }
            else {
                if (k == c) {
                    if (r.length() <= 2) {
                        r.append('a');
                    }
                    else {
                        r.append('b');
                    }
                }
                if (k == '^') {
                    r.append('c');
                }
                else {
                    r.append(k);
                }
            }
        }

        return r.toString();
    }
}
