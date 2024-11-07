public class Cafe extends Building{

    // Attributes:
    private int nCoffeeOunces; 
    private int nSugarPackets; 
    private int nCreams; 
    private int nCups; 

    // Constructors:

    /* Overloaded */
    /**
     * Generic constructor for Cafe class.
     */
    public Cafe(){
        super("<Cafe Unknown>", "<Address Unknown>", 1);
        System.out.println("You have built a cafe: ☕");
        this.nCoffeeOunces = 50;
        this.nSugarPackets = 25;
        this.nCreams = 25;
        this.nCups = 20;
    }

    /**
     * Creates Cafe class. 
     * @param name; Name of the cafe   
     * @param address; Address of the cafe
     * @param nFloors; Number of floors of the cafe
     * @param nCoffeeOunces; Number of ounces of coffee remaining in inventory
     * @param nSugarPackets; Number of sugar packets remaining in inventory
     * @param nCreams; Number of "splashes" of cream remaining in inventory
     * @param nCups; Number of cups remaining in inventory
     */
    public Cafe(String name, String address, int nFloors, int nCoffeeOunces, int nSugarPackets, int nCreams, int nCups) {
        super(name, address, nFloors);
        System.out.println("You have built a cafe: ☕");
        this.nCoffeeOunces = nCoffeeOunces;
        this.nSugarPackets = nSugarPackets;
        this.nCreams = nCreams;
        this.nCups = nCups;
    }

    // Getters: 
    /**
     * Getter for the ounces of coffee at the cafe.
     * @return int nCoffeeOunces; Number of ounces left in the inventory
     */
    public int getNCoffeeOunces(){
        return this.nCoffeeOunces;
    }

    /**
     * Getter for the number of sugar packets at the cafe.
     * @return int nSugarPackets; Number of sugar packets in inventory
     */
    public int getNSugarPackets(){
        return this.nSugarPackets;
    }

    /**
     * Getter for the number of creams at the cafe.
     * @return int nCreams; Number of creams in the inventory
     */
    public int getNCreams(){
        return this.nCreams;
    }

    /**
     * Getter for the number of cups at the cafe. 
     * @return nCups; Number of cups in the inventory
     */
    public int getNCups(){
        return this.nCups;
    }

    // Methods:
    /**
     * Sells a coffee at the cafe. Subtracts the items from the inventory. 
     * @param int size; Number of ounces the coffee is
     * @param int nSugarPackets; Number of sugar packets in the coffee
     * @param int nCreams; Number of splashes of creams in the coffee
     */
    public void sellCoffee(int size, int nSugarPackets, int nCreams){
        /* Checks to see if there are enough cups */
        if(nCups>0 & size <= this.nCoffeeOunces & nSugarPackets <= this.nSugarPackets & nCreams <= this.nCreams){
            System.out.println("The " + this.name + " brewed a " +size + "-ounce coffee with " + nSugarPackets + " sugar packets and " + nCreams + " creams.");
            this.nCups = this.nCups -1; // subtracts one cup
            this.nCoffeeOunces = this.nCoffeeOunces - size; // subtracts the coffee ounces
            this.nSugarPackets = this.nSugarPackets - nSugarPackets; // subtracts the sugar packets
            this.nCreams = this.nCreams - nCreams; // subtracts the creams 

        } else{
            System.out.println("----RESTOCKING---");
            this.restock(size, nSugarPackets, nCreams, 15); // restocks the amount that we need to make the coffee
            System.out.println("The " + this.name + " brewed a " +size + "-ounce coffee with " + nSugarPackets + " sugar packets and " + nCreams + " creams.");
        } 
    }

    /* Overloaded */
    /** 
     * Sells a standard cup of coffee from the cafe. The regular is a 10-ounce coffee with 2 sugar packets and 3 splashes of cream
     */
    public void sellCoffee(){
        /* Checks to see if there are enough cups */
        if(nCups>0 & 12 <= this.nCoffeeOunces & nSugarPackets >= 2 & nCreams >= 3){
            System.out.println("The " + this.name + " brewed a 10-ounce coffee with 2 sugar packets and 3 creams.");
            this.nCups = this.nCups -1; // subtracts one cup
            this.nCoffeeOunces = this.nCoffeeOunces - 12; // subtracts the coffee ounces
            this.nSugarPackets = this.nSugarPackets - 2; // subtracts the sugar packets
            this.nCreams = this.nCreams - 3; // subtracts the creams 

        } else{
            System.out.println("----RESTOCKING---");
            this.restock(50, 20, 20, 15); // restocks the amount that we need to make the coffee
            System.out.println("The " + this.name + " brewed a 10-ounce coffee with 2 sugar packets and 3 creams.");
        } 
    }


    /**
     * Restocks the inventory of the cafe when there are not enough supplies to make a coffee.
     * @param int nCoffeeOunces; Number of ounces of coffee restocked  
     * @param int nSugarPackets; Number of sugar packets restocked 
     * @param int nCreams; Number of splashes of creams restocked
     * @param int nCups; Number of cups restocked
     */
    private void restock(int nCoffeeOunces, int nSugarPackets, int nCreams, int nCups){
        this.nCups = this.nCups + nCups; // restocks cups
        this.nCoffeeOunces = this.nCoffeeOunces + nCoffeeOunces; // restocks the coffee ounces
        this.nSugarPackets = this.nSugarPackets + nSugarPackets; // restocks the sugar packets
        this.nCreams = this.nCreams + nCreams; // restocks the creams 
    }

    // Overriding Methods:
    /**
    * Displays all the options within the cafe. 
    */
    public void showOptions() {
        System.out.println("Available options at " + this.name + ":\n + enter() \n + exit() \n + goUp() \n + goDown()\n + goToFloor(n)\n\n Specific Options at " + this.name + ":\n + sellCoffee()");
    }

    /**
     * Prevents people from using the elevator.
     * int floorNum; Floor number elevator attempts to go to. 
     */
    public void goToFloor(int floorNum) {
        if (this.activeFloor == -1) {
            throw new RuntimeException("You are not inside this Building. Must call enter() before navigating between floors.");
        }
        if (floorNum < 1 || floorNum > this.nFloors) {
            throw new RuntimeException("Invalid floor number. Valid range for this Building is 1-" + this.nFloors +".");
        }
        System.out.println("Sorry you don't have access to level " + floorNum + " in " + this.name +". You only have access to the ground floor.");
    }

    public static void main(String[] args) {
        /* Creating cafe */
        System.out.println("-----------------------------------------------------");
        Cafe campusCafe = new Cafe("Campus Center Cafe", "Smith College Campus Center 100 Elm St",3, 100, 45 , 10, 15);
        System.out.println(campusCafe);

        /* Selling coffee */
        System.out.println("-----------------------------------------------------");
        campusCafe.sellCoffee(10, 3, 2); // sell
        campusCafe.sellCoffee(15, 1, 11); // restock and sell 

           /* Show Options */
        System.out.println("-----------------------------------------------------");
        campusCafe.showOptions();
  
        /* Navigating Cafe */
        System.out.println("-----------------------------------------------------");
        campusCafe.enter();
        campusCafe.goUp();
        //campusCafe.goDown();
        campusCafe.exit();

        /* Elevator? */
        System.out.println("-----------------------------------------------------");
        campusCafe.enter();
        campusCafe.goToFloor(3);

        /* Overloading */
        System.out.println("-----------------------------------------------------");
        Cafe cafe = new Cafe();
        cafe.sellCoffee(10, 2, 2);
        cafe.sellCoffee();
        System.out.println(cafe.nCoffeeOunces);




    }   
    
}
