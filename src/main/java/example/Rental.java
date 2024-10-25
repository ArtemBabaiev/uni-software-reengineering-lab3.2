package example;

import static example.Movie.MovieType.NEW_RELEASE;

class Rental {
    private final Movie movie;
    private final int daysRented;

    public Rental(Movie movie, int daysRented) {
        this.movie = movie;
        this.daysRented = daysRented;
    }

    public int getDaysRented() {
        return daysRented;
    }

    public Movie getMovie() {
        return movie;
    }

    double getCharge() {
        double amount = 0;
        //determine amounts for each line
        switch (getMovie().getPriceCode()) {
            case REGULAR -> {
                amount += 2;
                if (getDaysRented() > 2)
                    amount += (getDaysRented() - 2) * 1.5;
            }
            case NEW_RELEASE -> amount += getDaysRented() * 3;
            case CHILDRENS -> {
                amount += 1.5;
                if (getDaysRented() > 3)
                    amount += (getDaysRented() - 3) * 1.5;
            }
        }
        return amount;
    }

    int getFrequentRenterPoints() {
        int frequentRenterPoints=1;
        // add bonus for a two day new release rental
        if ((getMovie().getPriceCode() == NEW_RELEASE) && getDaysRented() > 1){
            frequentRenterPoints++;
        }
        return frequentRenterPoints;
    }
}
