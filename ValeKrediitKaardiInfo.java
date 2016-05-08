/**
 * Created by Kaspar on 07/05/16.
 */
public class ValeKrediitKaardiInfo extends RuntimeException{
    String e;

    public ValeKrediitKaardiInfo(String e) {
        this.e = e;
        System.out.println(e);
    }
}
