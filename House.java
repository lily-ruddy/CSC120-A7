import java.util.ArrayList;

public class House extends Building{

  // Attributes:
  private ArrayList<String> residents; 
  private boolean hasDiningRoom;
  private boolean hasElevator;

  // Constructors:

  /** 
   * Default constructor for the House class.
   */
  public House(){
    this("<Name Unknown>", "<Address Unknown>", 1, false,false);
  }




  /**
   * Full constructor for the House class.
   * @param name; Name of the house.
   * @param address; Address of the house
   * @param nFloors; The number of floors in the house. Must be greater than or equal to 1.
   * @param hasDiningRoom; True = has a dining room, False = doesn't have a dining room
   * @param has Elevator; True = has an elevator in the house, False = doesn't have an elevator in the house
   */
  public House(String name, String address, int nFloors, boolean hasDiningRoom, boolean hasElevator) {
    super(name, address, nFloors); // extends Building 
    System.out.println("You have built a house: 🏠");
    this.residents = new ArrayList<String>();
    this.hasDiningRoom = hasDiningRoom; 
    this.hasElevator = hasElevator; 
  }

  // Getters:
  /**
   * Getter for if the house has a dining room.
   * @return boolean; True = has a dining room, False = doesn't have a dining room
   */
  public boolean getHasDiningRoom(){
    return this.hasDiningRoom;
  }

  /**
   * Getter for if the house has an elevator.
   * @return boolean; True = has an elevator in the house, False = doesn't have an elevator in the house
   */
  public boolean getHasElevator(){
    return this.hasElevator;
  }

  /**
   * Getter for the number of residents in the house.
   * @return int size, size of the house
   */
  public int getNResidents(){
    return this.residents.size();
  }
  
  // Methods:
  /**
   * Moves a person into a house. Adds them to the residents roster list.
   * @param name; Name of the person moving in
   */
  public void moveIn(String name){
    /* Check if the person isn't already a resident */
    if(!residents.contains(name)){

      residents.add(name); // doesn't have max capacity
      System.out.println(name + " is now a resident of " + this.name);

    } else{
      throw new RuntimeException(name + " is already a resident of " + this.name);
    }
  }

  /**
   * Moves a person out of a house. Removes them from the residents roster list.
   * @param name; Name of the person who moved out
   * @return name; Name of the person who moved out
   */
  public String moveOut(String name){
    /* Checks that the person is a resident */
    if(residents.contains(name)){

      residents.remove(name);
      System.out.println(name + " is no longer a resident of " + this.name);
      return name;

    } else{
      throw new RuntimeException(name + " can't move out of " + this.name +"");
    }
  }

  /**
   * Checks whether a person is a resident of a house.
   * @param person, Name of the person
   * @return boolean, True = person is a resident, False = person isn't a resident.
   */
  public boolean isResident(String person){
    if (residents.contains(person)) {
      System.out.println(person + " is a resident of " + this.name);
      return true;
      
    } else {
      System.out.println(person + " isn't a resident of " + this.name);
      return false;
    }
  }
  
  // Overriding Methods:
  /**
   * Displays all the options within the house. 
   */
  public void showOptions() {
    System.out.println("Available options at " + this.name + ":\n + enter() \n + exit() \n + goUp() \n + goDown()\n + goToFloor(n)\n\n Specific Options at " + this.name + ":\n + hasDiningRoom() \n + nResidents()\n + isResident()\n + moveIn()\n + moveOut()");
  }

  /**
   * If the house has an elevator, use the elevator to go directly to any of the floor numbers in the building. 
   * @return int floorNum; The floor number the elevator will take you
   */
  public void goToFloor(int floorNum) {
    if (this.activeFloor == -1) {
        throw new RuntimeException("You are not inside this Building. Must call enter() before navigating between floors.");
    }
     if(this.hasElevator == false & Math.abs(floorNum - this.activeFloor) != 1){
       throw new RuntimeException(this.name + " doesn't have an elevator. Please use the stairs to navigate.");
    }
    if (floorNum < 1 || floorNum > this.nFloors) {
        throw new RuntimeException("Invalid floor number. Valid range for this Building is 1-" + this.nFloors +".");
    }
    System.out.println("You are now on floor #" + floorNum + " of " + this.name);
    this.activeFloor = floorNum;
  }

  public static void main(String[] args) {
    House baldwin = new House("Baldwin House", "15 Bedford Terrace", 5, false, false);
    //House genHouse = new House();

    /* Move in and out */
    baldwin.moveIn("Lily");
    baldwin.moveOut("Lily");
    System.out.println(baldwin.isResident("Joe"));

    /* Show Options */
    System.out.println("-----------------------------------------------------");
    baldwin.showOptions();

    /* Navigating House */
    System.out.println("-----------------------------------------------------");
    baldwin.enter();
    baldwin.goUp();
    baldwin.goDown();
    baldwin.exit();

    /* Elevator? */
    System.out.println("-----------------------------------------------------");
    baldwin.enter();
    baldwin.goToFloor(4);
    

  }

}