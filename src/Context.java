public class Context {
    private BreakStrategy strategy;

    public void setStrategy(BreakStrategy strategy){
        this.strategy = strategy;
    }

    public int[][] breakArray(int[] numbers1,int length){
        return strategy.breakArray(numbers1,length);
    }
}
