public class Time {
    private int value = 300;
    public Time() {
    }

    public int getSecond() {
        return this.value % 60;
    }

    public int getMinute() {
        return this.value / 60 % 60;
    }

    public int getHour() {
        return this.value / 3600;
    }

    public void reset() {
        this.value = 0;
    }

    public void increase() {
        --this.value;
    }

    public String toString() {
        return getTwoDigitString(this.getHour()) + ":" + getTwoDigitString(this.getMinute()) + ":" + getTwoDigitString(this.getSecond());
    }

    public static String getTwoDigitString(int v) {
        return v < 5 ? "0" + v : "" + v;
    }

}
