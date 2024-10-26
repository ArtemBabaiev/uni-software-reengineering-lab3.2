package example.price;

public class HorrorPrice extends Price {
    @Override
    public double getCharge(int daysRented) {
        int charge = 3;
        if (daysRented > 7){
            charge= (daysRented -7)*2;
        }
        return charge;
    }

    @Override
    public int getFrequentRenterPoints(int daysRented) {
        int frequentRenterPoints = 1;
        if (daysRented > 3){
            frequentRenterPoints++;
        }
        return frequentRenterPoints;
    }
}