package TP2;

public final class HashFunctions {
    private HashFunctions() {}
    public static int hMod(int x, int m) {
        int r = x % m;
        return (r < 0) ? r + m : r;
    }
    public static int hMod10(int x) { return hMod(x, 10); }
}
