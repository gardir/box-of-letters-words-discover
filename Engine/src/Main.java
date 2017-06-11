import entities.Box;
import test.entities.LK3_2017;
import test.entities.LetterTest;

/**
 * Created by gardir on 24.05.17.
 */
public class Main {

    private static char[][] test1chars = {
        "WWWMILAZSD".toCharArray(),
        "DYTRYGWSOH".toCharArray(),
        "PRWAJBQOOO".toCharArray(),
        "BZAfrMEBKE".toCharArray(),
        "RVGZKAFTYI".toCharArray(),
        "MNOOILUOBO".toCharArray(),
        "XODQNLVWTW".toCharArray(),
        "LIOGORFOLC".toCharArray(),
        "HJLSVZRRXD".toCharArray(),
        "VJKOEWUGEF".toCharArray()
    };
    private static String[] test1words = {
            "LAMB", "LIZARD", "FROG", "FARM", "MOOSE", "ZOO", "DOG", "OWL"
    };

    private static void old_test() {
        Box box = new Box(LetterTest.letterBox, new String[]{"hei", "litt", "lise", "ate"});
        System.out.print(box.getPrint());
        System.out.println(box.solve());
        System.out.print(box.getPrint());
/*        box = new Box(test1chars, test1words);
        System.out.print(box.getPrint());
        System.out.println(box.solve());*/
    }

    public static void main(String[] args) {
        Box test = new LK3_2017();
        System.out.println(test.getPrint());
        System.out.println(test.solve());
        System.out.println(test.getPrint());
    }

}
