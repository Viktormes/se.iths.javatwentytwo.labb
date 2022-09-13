
import java.util.Arrays;
import java.util.Scanner;

public class Labb {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PriceAndTime[]pricesAndTimes = new PriceAndTime[24];

        while (true) {
            System.out.println();
            menyChoices();
            String choice = sc.next();

            if (choice.equals("1")) {
                pricePerHour(sc, pricesAndTimes);
            }
            else if (choice.equals("2")) {
                PriceAndTime maxMax = getMaxPrice(pricesAndTimes);
                PriceAndTime minMin = getMinPrice(pricesAndTimes);

                System.out.println("Billigast är det kl:" + minMin.getTime() + "-" + (minMin.getTime()+1) + ", " + minMin.getPrice() + " öre per kW/h");
                System.out.println("Dyrast är det kl:" + maxMax.getTime() + "-" + (maxMax.getTime()+1) + ", " + maxMax.getPrice() + " öre per kW/h");
                System.out.format("Medelpriset är %.3f\n", avgPrice(pricesAndTimes));

            }
            else if (choice.equals("3")) {




            }

            else if (choice.equals("4")) {
                // Får reda på bästa laddningstid
            }

            else if (choice.equals("e") || choice.equals("E")) {
                System.out.println("Ha det gött!");
                break;
            }
            else
                System.out.println("Var god välj ett alternativ!");
        }
    }

    private static void pricePerHour(Scanner sc, PriceAndTime[] pricePerHourArr) {

        for (int i = 0; i < 24; i++) {
            System.out.println("Skriv in elpriset i hela ören för klockan: " + i + "-" + (i+1) );
            pricePerHourArr[i] = new PriceAndTime(i,sc.nextInt());


        }
    }

    private static PriceAndTime getMaxPrice(PriceAndTime[] pricePerHourArr) {
        PriceAndTime maxPrice = new PriceAndTime(0,0);
        for (int i = 0; i < pricePerHourArr.length ; i++) {
            if (pricePerHourArr[i].getPrice() > maxPrice.getPrice()){
                maxPrice.setPrice(pricePerHourArr[i].getPrice());
                maxPrice.setTime(pricePerHourArr[i].getTime());
            }
        }
        return maxPrice;
    }
    private static PriceAndTime getMinPrice(PriceAndTime[] pricePerHourArr) {

        PriceAndTime minPrice = new PriceAndTime(0,1000);

        for (int i = 0; i < pricePerHourArr.length ; i++) {
            if (pricePerHourArr[i].getPrice() < minPrice.getPrice()) {
                minPrice.setPrice(pricePerHourArr[i].getPrice());
                minPrice.setTime(pricePerHourArr[i].getTime());
            }
        }
        return minPrice;
    }
    private static double avgPrice(PriceAndTime[] pricePerHourArr1) {

        double total = 0;
        for (int i = 0; i < pricePerHourArr1.length ; i++) {
            total = total + pricePerHourArr1[i].getPrice();
        }
        double avg = total / pricePerHourArr1.length; {
        }
        return avg;
    }
    private static void menyChoices() {
        System.out.println("""
                Elpriser
                ======
                1. Inmatning
                2. Min, Max och Medel
                3. Sortera
                4. Bästa laddningstid (4h)
                e. Avsluta""");
    }
}


