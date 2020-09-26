import java.util.Hashtable;

public class IconsFactory {
    private static IconsFactory instance = new IconsFactory();
    private static Hashtable ht;

    private IconsFactory(){
        ht = new Hashtable();
        icons icons1,icons2,icons3,icons4,icons5,icons6,icons7;
        icons1 = new Icons1();
        ht.put("0",icons1);
        icons2 = new Icons2();
        ht.put("1",icons2);
        icons3 = new Icons3();
        ht.put("2",icons3);
        icons4 = new Icons4();
        ht.put("3",icons4);
        icons5 = new Icons5();
        ht.put("4",icons5);
        icons6 = new Icons6();
        ht.put("5",icons6);
        icons7 = new Icons7();
        ht.put("6",icons7);
    }
    public static IconsFactory getInstance(){
        return instance;
    }

    public static icons getIcons(String number){
        return (icons)ht.get(number);
    }
}
