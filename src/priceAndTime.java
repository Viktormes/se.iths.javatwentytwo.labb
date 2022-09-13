public class priceAndTime {


    private int time;
    private int price;

    public priceAndTime(int time, int price) {

        this.time = time;
        this.price = price;
    }
    public int getPrice() {
        return this.price;
    }
    public void setPrice(int price){
        this.price = price;
    }
    public int getTime() {
        return this.time;
    }
    public void setTime(int time){
        this.time = time;
    }
}
