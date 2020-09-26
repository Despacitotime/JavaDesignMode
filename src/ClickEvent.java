public class ClickEvent {
    public int[] iNumber = new int[500];
    public int[] jNumber = new int[500];
    public int[][] numbers3 = new int[8][5];
    int m = 0;
    int n = 0;

    public int getXIndex(int x) {
        int i = 0;
        if (x >= 97 && x < 168) {
            i = 0;
        } else if (x >= 168 && x < 239) {
            i = 1;
        } else if (x >= 239 && x < 310) {
            i = 2;
        } else if (x >= 310 && x < 381) {
            i = 3;
        } else if (x >= 381 && x < 452) {
            i = 4;
        } else if (x >= 452 && x < 523) {
            i = 5;
        } else if (x >= 523 && x < 594) {
            i = 6;
        } else if (x >= 594 && x < 665) {
            i = 7;
        } else {
            System.out.println("你没有选中任何图标.");
        }
        return i;
    }

    public int getYIndex(int y) {
        int j = 0;
        if (y > 53 && y < 128) {
            j = 0;
        } else if (y >= 128 && y < 203) {
            j = 1;
        } else if (y >= 203 && y < 278) {
            j = 2;
        } else if (y >= 278 && y < 353) {
            j = 3;
        } else if (y >= 353 && y < 428) {
            j = 4;
        } else {
            System.out.println("你没有选中任何图标.");
        }
        return j;
    }

    public int countNumber(int counts) {
        return ++counts;
    }

    public boolean isCommon() {
        if (this.m > 1) {
            return numbers3[iNumber[m - 2]][jNumber[m - 2]] == numbers3[iNumber[m - 1]][jNumber[m - 1]];
        } else {
            return false;
        }
    }

    public void storeIndex(int x, int y) {
        this.iNumber[this.m] = x;
        this.jNumber[this.n] = y;
        m++;
        n++;
    }
}
