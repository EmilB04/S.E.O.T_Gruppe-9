import java.util.List;

/** 
 * A utility class for analyzing electricity prices. 
 * 
 * This class provides three methods for calculating the average, highest and lowest electricity price from objects from the List "ElectricityPriceData"
 * 
 * Example usage:
 * <pre>
 * ElectricityPriceCalculator calculator = new ElectricityPriceCalculator();
 * double averagePrice = calculator.calculateAveragePrice(ElectricityPriceData);
 * <\pre>
*/

public class ElectricityPriceCalculator {

    /**
     * Calculates the average electricity price from a list of prices
     * 
     * This method sums up every electricity price object in a List and divides by the number of objects in the List.
     * 
     * @param prices List of "ElectricityPriceData" objects that represents prices for today/24 hours. 
     * @return The average price as a "double". 
     * @throws IllegalArgumentException if price List is empty.
     */
    public double calculateAveragePrice(List<ElectricityPriceData> prices) {
        if (prices.isEmpty()) {
            throw new IllegalArgumentException("Kunne ikke kalkulere gjennomsnittspris da listen er tom. Undersøk inputlisten");
        }

        double sum = 0; 
        for (ElectricityPriceData data : prices) {
            sum += data.getPrice();
        }

        double average = sum / prices.size();
        return average;
    }

    /**
     * Finds highest price in List.
     * 
     * Iterates through a List and adds highest price to variable; "highest".
     * 
     * @param  prices List of "ElectricityPriceData" objects that represents prices for today/24 hours.  
     * @return The highest price as a "double". 
     * @throws IllegalArgumentException if price List is empty.
     */ 
    public double calculateHighestPrice(List<ElectricityPriceData> prices) {
        if (prices.isEmpty()) {
            throw new IllegalArgumentException("Kunne ikke kalkulere høyest pris da listen er tom. Undersøk inputlisten");
        }

        double highest = Double.MIN_VALUE; 
        for (ElectricityPriceData data : prices) {
            if (data.getPrice() > highest) {
                highest = data.getPrice();
            }
        }

        return highest;
    }

    /**
     * Finds lowest price in List
     * 
     * Iterates through a List and adds lowest price to a variable; "lowest".
     * 
     * @param  prices List of "ElectricityPriceData" objects that represents prices for today/24 hours. 
     * @return The lowest price as a "double". 
     * @throws IllegalArgumentException if price List is empty.
     */
    public double calculateLowestPrice(List <ElectricityPriceData> prices) {
        if (prices.isEmpty()) {
            throw new IllegalArgumentException("Kunne ikke kalkulere laveste pris da listen er tom. Undersøk inputlisten");
        }

        double lowest = Double.MAX_VALUE;
        for (ElectricityPriceData data : prices) {
            if (data.getPrice() < lowest) {
                lowest = data.getPrice();
            }
        }

        return lowest;
    }
}
