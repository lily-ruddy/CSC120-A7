/* Resources */
// https://docs.oracle.com/javase/8/docs/api/index.html?java/util/Hashtable.html
// https://www.tutorialspoint.com/how-to-iterate-through-hashtable-in-java

import java.util.Hashtable;
import java.util.Set;

public class Library extends Building{

  // Attribute:
  private Hashtable<String, Boolean> collection;
  private boolean hasElevator; 
  
  // Costructor:
  /**
   * Constructor for Library class.
   * @param name; Name of the library.
   * @param address; Address of the library.
   * @param nFloors; Number of floors of the library. Must be greater than equal to 1.
   * @param hasElevator; True = has an elevator in the library, False = doesn't have an elevator in the library
   */
  public Library(String name, String address, int nFloors, boolean hasElevator) {
    super(name, address, nFloors); // extends Building
    System.out.println("You have built a library: 📖");
    this.collection = new Hashtable<String, Boolean>();
    this.hasElevator = hasElevator; 
  }

  // Getters:
  /**
   * Getter for if the library has an elevator.
   * @return boolean; True = has an elevator in the library, False = doesn't have an elevator in the library
   */
  public boolean getHasElevator(){
    return this.hasElevator;
  }

  // Methods:
  /**
   * Adds the book into the library.
   * @param title; Name of the added book
   */
  public void addTitle(String title){
    this.collection.put(title, true);
    System.out.println(title + " is now in the " + this.name);
  }

  /**
   * Removes the book from the library. 
   * @param title; Name of the book
   * @return title; Name of the removed book
   */
  public String removeTitle(String title){
    /* Check if the title is in the collection */
    if(this.collection.containsKey(title)){
      System.out.println(title + "is no longer in the " + this.name);
      this.collection.remove(title);
      return title;

    } else{
      throw new RuntimeException(title + " isn't in the " + this.name + ". Please give another title.");
    }
  }

  /**
   * Checks out the book from the library. Changes its availability to false.
   * @param title; Name of the checked out book
   */
  public void checkOut(String title){
    /* Check if the title is in the collection */
      if(this.collection.containsKey(title)== false){
        throw new RuntimeException(title + " isn't in the " + this.name + ". Please give another title."); // not in the library
        
        /* Checks to see if the title is already checked out */
      } else{
        if (this.collection.get(title) == false ) {
          throw new RuntimeException("Sorry " + title + " is already checked out."); // already checked out
          
        } else { 
          this.collection.replace(title, true, false);
          System.out.println(title + " is now checked out of the " + this.name);
      }
    }
  }
  
  /**
   * Returns the title to the library. Changes its availabilty to true. 
   * @param title; Name of the book that is being returned.
   */
  public void returnBook(String title){
    /* Check if the title is in the collection */
      if(this.collection.containsKey(title)== false){
        throw new RuntimeException(title + " isn't in the " + this.name + ". Please give another title."); // not in the library
          
        /* Checks to see if the title is already checked out */
      } else{
        if (this.collection.get(title) == true ) {
          throw new RuntimeException(title + " is already in " + this.name + "This title isn't the property of " +this.name); // not from library
            
        } else { 
          this.collection.replace(title, false, true);
          System.out.println(title + " is now checked back in to the " + this.name);
      }
    }
  }
  
  /**
   * Determines if the title is in the library's collection.
   * @param title; Name of the book
   * @return boolean; True = title is in the library, False = title isn't in the library
   */
  public boolean containsTitle(String title){
    if(this.collection.containsKey(title)== true){
      System.out.println(title + " is in the " +this.name);
      return true;

    } else{
      System.out.println(title + " isn't in the " +this.name);
      return false;
    }
  }

  /**
   * Checks to see if a title in the library's collection is available to be checked out.
   * @param title; Name of the book interested in checking out.
   * @return boolean; True = title is available to be checked out, False = title isn't available to be checked out
   */
  public boolean isAvailable(String title){
    if (this.collection.get(title) == null) {
      throw new RuntimeException(title +" isn't in the " + this.name +"'s collection");
    }

    if (this.collection.get(title) == false) {
      System.out.println("Sorry " + title + " is already checked out."); // already checked out
      return false;

    } else{
      System.out.println(title + " is available to be checked out of " + this.name);
      return true;
    }  
  }

  /** 
   * Prints out the entire library's collection of books.
   */
  public void printCollection(){
    System.out.println("Current Collection for " + this.name + ":");

    /* Set & For Loop */
    Set<String> setOfTitles = collection.keySet();
    for(String key : setOfTitles){
      System.out.println("- " + key + "\t\t Availability:" + collection.get(key));
    }
  }

  // Overriding Methods:
  /**
   * Displays all the options within the library. 
   */
  public void showOptions() {
    System.out.println("Available options at " + this.name + ":\n + enter() \n + exit() \n + goUp() \n + goDown()\n + goToFloor(n)\n\n Specific Options at " + this.name + ":\n + addTitle() \n + removeTitle()\n + isResident()\n + checkOut()\n + returnBook()\n + containsTitle()\n + isAvailable()\n + printCollection()");
  }

  /**
   * If the library has an elevator, use the elevator to go directly to any of the floor numbers in the building. 
   * @return int floorNum; The floor number the elevator will take you
   */
  public void goToFloor(int floorNum) {
    if(this.hasElevator == false & Math.abs(floorNum - this.activeFloor) != 1){
      throw new RuntimeException(this.name + " doesn't have an elevator. Please use the stairs to navigate.");
    }
    if (this.activeFloor == -1) {
      throw new RuntimeException("You are not inside this Building. Must call enter() before navigating between floors.");
    }
    if (floorNum < 1 || floorNum > this.nFloors) {
      throw new RuntimeException("Invalid floor number. Valid range for this Building is 1-" + this.nFloors +".");
    }
    System.out.println("You are now on floor #" + floorNum + " of " + this.name);
    this.activeFloor = floorNum;
  }
  
  public static void main(String[] args) {
    /* Creating Library */
    System.out.println("-----------------------------------------------------");
    Library neilson = new Library("Neilson Library", "7 Neilson Drive", 5, true);
    System.out.println(neilson);

    /* Adding & Removing */
    System.out.println("-----------------------------------------------------");
    neilson.addTitle("Green Eggs and Ham by Dr. Seuss"); // adding book
    System.out.println(neilson.collection);
    neilson.removeTitle("Green Eggs and Ham by Dr. Seuss"); // removing book

    /* Check In & Out */
    System.out.println("-----------------------------------------------------");
    neilson.addTitle("Goodnight Moon by Margaret Wise Brown"); // adding book
    neilson.checkOut("Goodnight Moon by Margaret Wise Brown"); // checking out book
    neilson.returnBook("Goodnight Moon by Margaret Wise Brown"); // returning book

    /* In the Library? */
    System.out.println("-----------------------------------------------------");
    neilson.addTitle("Matilda by Roald Dahl"); // adding book
    neilson.containsTitle("Matilda by Roald Dahl"); // checking if it's in library
    neilson.containsTitle("Twilight");

    /* Available? */
    System.out.println("-----------------------------------------------------");
    neilson.addTitle("The Giving Tree by Shel Silverstein"); // adding book
    neilson.isAvailable("The Giving Tree by Shel Silverstein"); // availability
    neilson.checkOut("The Giving Tree by Shel Silverstein"); // check out
    neilson.isAvailable("The Giving Tree by Shel Silverstein"); // no availability

    /* Print */
    System.out.println("-----------------------------------------------------");
    neilson.printCollection(); // prints collection
    
    /* Show Options */
    System.out.println("-----------------------------------------------------");
    neilson.showOptions();
  
    /* Navigating Library */
    System.out.println("-----------------------------------------------------");
    neilson.enter();
    neilson.goUp();
    neilson.goDown();
    neilson.exit();

    /* Elevator? */
    System.out.println("-----------------------------------------------------");
    neilson.enter();
    neilson.goToFloor(4);
    neilson.goToFloor(2);
    
    
  }
}
