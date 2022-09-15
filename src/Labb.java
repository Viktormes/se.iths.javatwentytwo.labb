
import java.util.Arrays;
import java.util.Scanner;

public class Labb {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PriceAndTime[] pricesAndTimes = new PriceAndTime[24];

        label:
        while (true) {
            System.out.println();
            menuChoices();
            String choice = sc.next();

            switch (choice) {
                case "1":
                    pricePerHour(sc, pricesAndTimes);
                    break;
                case "2":
                    PriceAndTime maxMax = getMaxPrice(pricesAndTimes);
                    PriceAndTime minMin = getMinPrice(pricesAndTimes);

                    System.out.println("Billigast är det kl:" + minMin.getTime() + "-" + (minMin.getTime() + 1) + ", " + minMin.getPrice() + " öre per kWh");
                    System.out.println("Dyrast är det kl:" + maxMax.getTime() + "-" + (maxMax.getTime() + 1) + ", " + maxMax.getPrice() + " öre per kWh");
                    System.out.format("Medelpriset är %.3f\n", avgPrice(pricesAndTimes));
                    break;
                case "3":
                    PriceAndTime[] sortedDeluxe = sortedPrices(pricesAndTimes);
                    for (PriceAndTime priceAndTime : sortedDeluxe) {
                        System.out.println("Kl:" + priceAndTime.getTime() + "-" + (priceAndTime.getTime() + 1) + ", " + (priceAndTime.getPrice()) + " öre per kWh");
                    }
                    break;
                case "4":
                    bestFourHours(pricesAndTimes);
                    break;
                case "e":
                case "E":
                    System.out.println("Ha det gött!");
                    break label;
                default:
                    System.out.println("Var god välj ett alternativ!");
                    break;
            }
        }
    }

    private static void pricePerHour(Scanner sc, PriceAndTime[] pricePerHourArr) {

        for (int i = 0; i < 24; i++) {
            System.out.println("Skriv in elpriset i hela ören för klockan: " + i + "-" + (i + 1));
            pricePerHourArr[i] = new PriceAndTime(i, sc.nextInt());


        }
    }

    private static PriceAndTime getMaxPrice(PriceAndTime[] pricePerHourArr) {
        PriceAndTime maxPrice = new PriceAndTime(0, 0);
        for (PriceAndTime priceAndTime : pricePerHourArr) {
            if (priceAndTime.getPrice() > maxPrice.getPrice()) {
                maxPrice.setPrice(priceAndTime.getPrice());
                maxPrice.setTime(priceAndTime.getTime());
            }
        }
        return maxPrice;
    }

    private static PriceAndTime getMinPrice(PriceAndTime[] pricePerHourArr) {

        PriceAndTime minPrice = new PriceAndTime(0, 1000);

        for (PriceAndTime priceAndTime : pricePerHourArr) {
            if (priceAndTime.getPrice() < minPrice.getPrice()) {
                minPrice.setPrice(priceAndTime.getPrice());
                minPrice.setTime(priceAndTime.getTime());
            }
        }
        return minPrice;
    }

    private static double avgPrice(PriceAndTime[] pricePerHourArr1) {

        double total = 0;
        for (PriceAndTime priceAndTime : pricePerHourArr1) {
            total = total + priceAndTime.getPrice();
        }
        double avg = total / pricePerHourArr1.length;
        {

        }
        return avg;
    }

    private static PriceAndTime[] sortedPrices(PriceAndTime[] pricePerHourArr) {

        PriceAndTime[] priceClone = Arrays.copyOf(pricePerHourArr, pricePerHourArr.length);

        for (int i = 0; i < priceClone.length; i++) {
            for (int j = 0; j < priceClone.length; j++)
                if (priceClone[i].getPrice() < priceClone[j].getPrice()) {

                    PriceAndTime temp = priceClone[i];
                    priceClone[i] = priceClone[j];
                    priceClone[j] = temp;

                }

        }
        return priceClone;


    }

    private static void menuChoices() {
        System.out.println("""
                Elpriser
                ======
                1. Inmatning
                2. Min, Max och Medel
                3. Sortera
                4. Bästa laddningstid (4h)
                e. Avsluta""");
    }

    private static void bestFourHours(PriceAndTime[] bestPriceArr) {

        int priceOfFour = 0;
        double lowestOfFour = Integer.MAX_VALUE;
        int bestTime = 0;
        for (int i = 0; i < bestPriceArr.length - 3; i++) {
            priceOfFour = bestPriceArr[i].getPrice() + bestPriceArr[i + 1].getPrice() + bestPriceArr[i + 2].getPrice() + bestPriceArr[i + 3].getPrice();
            if (priceOfFour < lowestOfFour) {
                lowestOfFour = priceOfFour;
                bestTime = bestPriceArr[i].getTime();
            }

        }
        System.out.println("De billigaste fyra sammanhängade timmarna är kl: " + bestTime + "-" + (bestTime + 4));
        System.out.println("Medelpriset de billigaste fyra sammanhängade timmarna är: " + lowestOfFour / 4 + " öre per kWh");

    }
}


