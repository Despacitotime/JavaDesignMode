import javax.swing.*;
import java.awt.*;

public class Calculator {
    public Time time;
    public Calculator(Time time){
        this.time = time;
    }
    public void countsTimes(int counts) {
        int times = this.time.getHour() * 3600 + this.time.getMinute() * 60 + this.time.getSecond();
        int totaltime = 300 - times;
        int hour = totaltime / 3600;
        int minute = totaltime / 60 % 60;
        System.out.println("" + hour + ":" + minute + ":" + totaltime);
        System.out.println(counts);
        String output1 = "您的游戏所花时间为：" + String.valueOf(hour) + ":" + minute + ":" + totaltime + "\nGameOver\n得分为：" + (400 - counts);
        JOptionPane.showMessageDialog((Component)null, output1);
    }
}
