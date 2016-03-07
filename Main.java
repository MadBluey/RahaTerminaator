/*
 * Created by Kaspar on 07/03/16.
 */


//Töö autorid on Ove Liis Mahhov ja Karl Kaspar Haavel.


import java.util.*;

//Test

public class Main {
    public static void main (String[] args){
        Scanner input1 = new Scanner(System.in);
        System.out.println("Sisestage oma rahasumma mängimiseks: ");  //Küsib rahasummat, millega mängja alustab.
        double raha = input1.nextDouble();

        Scanner input2 = new Scanner(System.in);
        System.out.println("Palun sisestage oma soovitud panus: "); //Küsib kasutajalt soovitud panuse.
        double panus = input2.nextDouble();

        MänguAutomaat a = new MänguAutomaat(raha, panus); //MänguAutomaadis toimub kogu maagia.

        a.setVõimalus(0.9); //Võiduvõimalus on praegu 90%.

        while (true){ //Tsükkel, et mängida saaks ilma koguaeg rahasummat sisestamata.

            a.sümboliGeneratsioon(a.getVõimalus()); //--> Mänguautomaat
            System.out.println("Raha on "+ a.getRaha()); //Tagastab palju raha on.

            if (a.getRaha() == 0){
                System.out.println("TEIL ON RAHA OTSAS, KONTROLLIGE OMA MÄNGUIHA :D!"); //Print käsk ütleb kõik.
                break;}
            Scanner input3 = new Scanner(System.in);
            System.out.println("Soovite edasi mängida y/n ? "); //Kas kasutaja soovib edasi mängida või mitte, kui ei soovi lõpetab programmi.
            String kasutajasisestus = input3.nextLine();

            if (kasutajasisestus.equals("n")){
                System.out.println("Lõpetasite mängu. Väljavõetud summa eurodes on:  " + a.getRaha() +"."); //Programmi lõpetamisel väljastab raha summa.
                break;}

            Scanner input4 = new Scanner(System.in);
            System.out.println("Palun sisestage oma soovitud panus"); //Küsib panuse uuesti.
            double panus2 = input4.nextDouble();
            a.setPanus(panus2);


            //Võiduvõimaluse kontrollimiseks.
            if (panus2==13.37){
                System.out.println("Sisenesite admini paneeli. Sisestage 1 kui soovite kontrollida võiduvõimalust.");
                Scanner input5 = new Scanner(System.in);
                if (input5.nextDouble() == 1){
                    Scanner input6 = new Scanner(System.in);
                    System.out.println("Sisestage palun teie poolt soovitud võiduvõimalus. Default on 90%. ");
                    a.setVõimalus(input6.nextDouble()/100);
                    System.out.println("Teie soovitud võiduvõimalus on: " + a.getVõimalus()*100);
                }
            }




        }

    }
}
