/**
 * Created by Kaspar on 07/05/16.
 */
public class ValeKrediitKaardiInfo extends RuntimeException {
    private String e;

    public ValeKrediitKaardiInfo(String e) {
        this.e = e;
        System.out.println(this.e);
    }

    @Override
    public String toString() {
        return
                "Viga! " + e;
    }
}
