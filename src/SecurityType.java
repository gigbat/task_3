public class SecurityType {
    private final String INPUT_LINE;
    private String result = "";

    static boolean checkZero = false;
    static boolean checkOne = false;
    static int stepUp = 1;
    static int stepDown = 0;

    public SecurityType(String line) {
        this.INPUT_LINE = line;
    }

    public void generateSecurityMessage() {
        for (int i = 0; i < INPUT_LINE.length()-1; i++) {
            byte o1 = parseFromCharAt(i);

            checkZero = false;
            checkOne = false;

            if (i + 1 == INPUT_LINE.length()-1) {
                stepUp++;
                calculate(o1);
                boolean lastIndex = true;
                calculateLastIndex(o1, lastIndex);
                return;
            }

            if (parseFromCharAt(i) ==
                    parseFromCharAt(i + 1)) {
                stepUp++;
            } else {
                if (stepUp == stepDown) stepUp++;
                if (parseFromCharAt(i) == parseFromCharAt(i-1)) stepUp = i;
                calculate(o1);
                stepDown = stepUp;
            }
        }
    }

    public void calculate(byte o1) {
        for (int j = stepDown; j <= stepUp; j++) {
            byte o2 = parseFromCharAt(j);
            if (o1 == o2 && o1 == 0 && !checkZero) {
                result += " 00 0";
                checkZero = true;
            } else if (o1 == o2 && o1 == 0 && checkZero) {
                result += "0";
            } else if (o1 == o2 && o1 == 1 && !checkOne) {
                result += " 0 0";
                checkOne = true;
            } else if (o1 == o2 && o1 == 1 && checkOne) {
                result += "0";
            }
        }
    }

    public void calculateLastIndex(byte o, boolean lastIndex) {
        if (lastIndex) {
            stepUp++;
            stepDown = stepUp - 1;
            o = parseFromCharAt(stepUp);
            byte temp = parseFromCharAt(stepDown);
            if (o == temp && o == 1 && temp == 1) {
                checkOne = true;
                stepDown = stepUp;
                calculate(o);
            } else if (o == temp && o == 0 && temp == 0) {
                checkZero = true;
                stepDown = stepUp;
                calculate(o);
            } else {
                calculate(o);
            }
        }
    }

    private byte parseFromCharAt(int i) {
        byte b = Byte.parseByte(String.valueOf(INPUT_LINE.charAt(i)));
        return b;
    }

    public String deleteFirstSpace(String str) {
        String newResult = str.replaceFirst("\\s", "");
        return newResult;
    }

    public void printResult() {
        System.out.println(deleteFirstSpace(result));
    }
}
