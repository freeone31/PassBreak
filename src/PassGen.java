public class PassGen {
    private PassGen() {}

    private static class LazyHolder {
        static final PassGen INSTANCE = new PassGen();
    }

    public static PassGen getInstance() {
        return LazyHolder.INSTANCE;
    }

    private final byte passLenght = 20;

    // первый символ строки-пароля соответствует нулевому индексу массива
    private volatile byte[] lastPass = new byte[passLenght];

    // нулевой индекс не учитываем, чтобы считалось с первого
//    private final char[] alphabet = {
//        '0',
//        '1','2','3','4','5','6','7','8','9','0',
//        'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
//        'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z',
//        '-','=','\\','!','@','#','$','%','^','&','*','(',')','_','+','|','~','[',']',';',',','.','/','{','}',':','\'','"','<','>','?'
//    };

    private final char[] alphabet = {
        '0',
        '1','2','3','4','5','6','7','8','9','0'
    };

    private String convertPassToString() {
        StringBuilder sb = new StringBuilder();

        for (byte i = 0; i < passLenght; i++) {
            if (lastPass[i] != 0) {
                sb.append(alphabet[lastPass[i]]);
            }
        }

        return sb.toString();
    }

    private void incDigit(byte digit) {
        for (byte i = digit; i < passLenght; i++) {
            if (lastPass[i] < alphabet.length - 1) {
                lastPass[i]++;
                break;
            }
            else {
                if (i < passLenght - 1) {
                    lastPass[i] = 1;
                    incDigit((byte)(i + 1));
                    break;
                }
            }
        }
    }

    public synchronized String getNextPass() {
        // сначала всегда увеличиваем нулевой разряд, потом рекурсивно все остальные по мере заполненности
        incDigit((byte)0);

        return convertPassToString();
    }
}
