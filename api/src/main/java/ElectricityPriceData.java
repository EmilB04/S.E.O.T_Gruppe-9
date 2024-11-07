/**
 * Represents electricity price data for a specific time interval.
 * 
 * The class encapsulates data about electricity price like price per kWh, start and end time of a specific time frame.
 */
public class ElectricityPriceData {

    private double price; 
    private String timeStart;
    private String timeStop;

    // Konstruktør 
    public ElectricityPriceData(double price, String timeStart, String timeStop){
        this.price = price; 
        this.timeStart = timeStart;
        this.timeStop = timeStop;
    }

    // Gettere
    public double getPrice(){
        return price;
    }

    public String getTimeStart(){
        return timeStart;
    }

    public String getTimeStop(){
        return timeStop; 
    }
}