import java.util.Objects;

public class QuantityMeasurementApp {

    public enum LengthUnit {
        FEET(12.0),
        INCH(1.0);

        private final double conversionToInches;

        LengthUnit(double conversionToInches) {
            this.conversionToInches = conversionToInches;
        }

        public double getConversionToInches() {
            return this.conversionToInches;
        }
    }

    public static class QuantityLength {
        private final double value;
        private final LengthUnit unit;

        public QuantityLength(double value, LengthUnit unit) {
            if (unit == null) {
                throw new IllegalArgumentException("Unit cannot be null");
            }
            this.value = value;
            this.unit = unit;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof QuantityLength)) return false;
            QuantityLength that = (QuantityLength) obj;
            double thisInches = this.value * this.unit.getConversionToInches();
            double thatInches = that.value * that.unit.getConversionToInches();
            return Double.compare(thisInches, thatInches) == 0;
        }

        @Override
        public int hashCode() {
            return Objects.hash(value * unit.getConversionToInches());
        }

        @Override
        public String toString() {
            return "Quantity(" + value + ", \"" + unit.name().toLowerCase() + "\")";
        }
    }

    // Kept for backward compatibility with UC1 and UC2 tests
    public static class Feet extends QuantityLength {
        public Feet(double value) {
            super(value, LengthUnit.FEET);
        }
    }

    public static class Inches extends QuantityLength {
        public Inches(double value) {
            super(value, LengthUnit.INCH);
        }
    }

    public static boolean checkFeetEquality(double val1, double val2) {
        return new Feet(val1).equals(new Feet(val2));
    }

    public static boolean checkInchesEquality(double val1, double val2) {
        return new Inches(val1).equals(new Inches(val2));
    }

    public static void main(String[] args) {
        QuantityLength val1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength val2 = new QuantityLength(12.0, LengthUnit.INCH);
        
        System.out.println("Input: " + val1 + " and " + val2);
        System.out.println("Output: Equal (" + val1.equals(val2) + ")");
        
        QuantityLength val3 = new QuantityLength(1.0, LengthUnit.INCH);
        QuantityLength val4 = new QuantityLength(1.0, LengthUnit.INCH);
        
        System.out.println("Input: " + val3 + " and " + val4);
        System.out.println("Output: Equal (" + val3.equals(val4) + ")");
    }
}
