public class FirstBreakMethod extends BreakStrategy{
    public int[][] breakArray(int[] numbers1,int length){
        int r;
        for(int i = 0; i < length; ++i) {
            r = (int)(Math.random() * 7.0D);
            numbers1[i] = r;
        }
        int[] numbers2 = new int[length*2];

        for(r = 0; r < length*2; ++r) {
            if (r < length) {
                numbers2[r] = numbers1[r];
            } else {
                numbers2[r] = numbers1[r - length];
            }
        }
        int k;
        int i;
        int j;
        for(r = 0; r < 10; ++r) {
            for(k = 0; k < length*2; ++k) {
                i = (int)(Math.random() * 40.0D);
                j = numbers2[k];
                numbers2[k] = numbers2[i];
                numbers2[i] = j;
            }
        }
        r = 0;
        int[][] numbers3 = new int[8][5];
        for(k = 0; k < 8; ++k) {
            for(i = 0; i < 5; ++i) {
                numbers3[k][i] = numbers2[r];
                ++r;
            }
        }
        return numbers3;
    }
}
