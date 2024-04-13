public class Promotion {
    private String discountCode;
    private double discountRate;
    private boolean used;

    // Constructors
    public Promotion(String discountCode, double discountRate) {
        this.discountCode = discountCode;
        this.discountRate = discountRate;
        this.used = false;
    }

    // Getters
    public String getDiscountCode() {
        return discountCode;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public boolean isUsed() {
        return used;
    }

    // Setters
    public void setUsed(boolean used) {
        this.used = used;
    }
    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }
    public void setDiscountRate(double discountRate) {
        this.discountRate = discountRate;
    }

    // Overrides

    @Override
    public String toString() {
        return "Promotion{" +
                "discountCode='" + discountCode + '\'' +
                ", discountRate=" + discountRate +
                ", used=" + used +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Promotion promotion = (Promotion) obj;
        return Double.compare(promotion.discountRate, discountRate) == 0 && used == promotion.used && discountCode.equals(promotion.discountCode);
    }



}
