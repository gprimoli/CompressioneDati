package it.unisa.di.common;

public class Helper {
    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
        return true;
    }

    public static boolean isFloat(String s) {
        try {
            Float.parseFloat(s);
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
        return true;
    }

    public static Type whatIs(String s) {
        if (isInteger(s)) {
            return Type.Integer;
        } else if (isFloat(s)) {
            return Type.Float;
        }
        return Type.String;
    }
}
