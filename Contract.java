/**
 * Interface with 10 methods
 */
public interface Contract {
    void grab(String item);
    String drop(String item);
    void examine(String item);
    void use(String item);
    boolean walk(String direction);
    boolean fly(int x, int y);
    Number shrink();
    Number grow();
    void rest();
    void undo();
}

class Agency implements Contract{
    /* Declaring parameters */
    String star;
    protected int rank;
    protected String health;
    String name;
    String movie;

    /* Constructor */
    public Agency(String name_agency){
        this.name = name_agency;
    } 
    
    /**
     * Sign a new star and add them to the agency
     * @param item string value of the star's name
     */
    public void grab(String item){
        this.star = item;
        System.out.println(star + " is our newly scouted talent!");
        this.rank = 100;
    }

    /**
     * Drop star from contract list and remove information
     * @param item name of the star
     * @return statement/denial of the star being dropped by the agency
     */
    public String drop(String item){
        if (star == item){
            String statement = name + " no longer represents " + item;
            undo();
            return statement;
        }
        String denial = item + " is not a signed talent in our agency, " + name;
        return denial;
    }

    /**
     * Examine health and print health condition
     * @param item string value representing condition
     */
    public void examine(String item){
        this.health = item;
        System.out.println(star + " is in a " + health + " condition");
    }

    /**
     * Star in a movie and increase ranking
     * @param item name of the movie
     */
    public void use(String item){
        this.movie = item;
        System.out.println(star + " has been cast in the new movie " + movie);
        grow();
    }

    /**
     * Walk to a place; if rank is high, reserve plane ticket
     * @param direction string value of where to go
     * @return true/false of whether they walked or got a plane ticket
     */
    public boolean walk(String direction){
        if (rank < 100){
            System.out.println(star + "can't walk to " + direction);
            System.out.println("Get a plane ticket");
            fly(3, 500);
            return false;
        }
        System.out.println(star + " can walk to " + direction);
        return true;
    }


    /**
     * Pay for flight ticket depending on class
     * @param class_type int repressing the type of class
     * @param cost int of the amount that was provided to the flight agent
     * @return true/false of whether the flight ticket was purchased
     */
    public boolean fly(int class_type, int cost){
        //higher(1) class type costs more
        for(int i = 1; i < 3; i++){
            int all_cost = 1000;
            if(i == class_type){
                int difference = all_cost - cost;
                //given money is less than the cost of the flight
                if (difference > 0){
                    System.out.println("You are missing " + difference + " dollars");
                }
                else{
                    System.out.println(difference + " is your return");
                }
                return true;
            }
            all_cost -= 250;
        }

        //Entered wrong class type
        System.out.println("There is only 3 different flight classes");
        return false;
    }

    /**
     * Decrease ranking by 10 spots
     * @return int rank (after fall)
     */
    public Number shrink(){
        this.rank += 10;
        System.out.println(star + " fell out of the ranking and is on " + rank);
        return rank;
    }

    /**
     * Overload shrink method; decrease ranking by supplied amount
     * @return int rank (after fall)
     */
    public Number shrink(int fall){
        this.rank += fall;
        System.out.println(star + " fell out of the ranking and is on " + rank);
        return rank;
    }

    /**
     * Increase ranking by 10 spots
     * @return int rank (after rise)
     */
    public Number grow(){
        this.rank -= 10;
        System.out.println(star + " has risen into the ranking and is on " + rank);
        return rank;
    }

    /* By resting, health improves to a great condition */
    public void rest(){
        examine("great");
    }

    /* All fields are annuled */
    public void undo(){
        rank = 0;
        star = "unknown";
    }

    public static void main(String[] args) {
        Agency warnersAgency = new Agency("Warners");
        warnersAgency.grab("John Oliver");
        warnersAgency.use("The Lion King");
        warnersAgency.grow();
        warnersAgency.fly(1, 300);
        warnersAgency.shrink(60);
        warnersAgency.walk("hollywood boulevard");
        warnersAgency.rest();
        warnersAgency.shrink();
        warnersAgency.examine("bad");
        warnersAgency.drop("John Oliver");
    }
}