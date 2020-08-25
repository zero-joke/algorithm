package Exercises.string;

public class UsefulNumber {
    public boolean isNumber(String s) {
        s = s.trim();
        int idxe = s.indexOf("e");
        int idxE = s.indexOf("E");
        if(idxe != -1 && idxE != -1)
            return false;
        idxe = idxe != -1 ? idxe : idxE;
        if(idxe == -1) {
            return isR(s, false);
        } else {
            if(idxe-1 < 0 || idxe+1 >= s.length())
                return false;
            return isR(s.substring(0, idxe), false) 
                        && isR(s.substring(idxe+1), true);
        }
    }

    private boolean isR(String s, boolean mustInt) {
        int len = s.length();
        boolean signed = false;
        boolean pointed = false;
        boolean useful = false;
        for(int i=0; i<len;i++) {
            char c = s.charAt(i);
            if(isSign(c)) {
                if(signed)
                    return false;
                if(i+1 >= len)
                    return false;
                signed = true;
            } else if(c == '.') {
                if(mustInt)
                    return false;
                if(pointed)
                    return false;
                if(!useful && (i+1 >= len || !Character.isDigit(s.charAt(i+1))))
                    return false;   // 前后都没有数字
                pointed = true;
            } else if(Character.isDigit(c)) {
                useful = true;
            } else
                return false;
        }
        return useful;
    }

    private boolean isSign(char c) {
        return c == '+' || c == '-';
    }

    public static void main(String[] args) {
        UsefulNumber Main = new UsefulNumber();
        System.out.println(Main.isNumber("2e0"));
    }
}