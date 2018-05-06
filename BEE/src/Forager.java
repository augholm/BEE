import java.util.*;

public class Forager {

    //bruker priority queue til å sortere ut de x beste biene som skal gjøre waggle dance

    public double profitability_rating;
    public double preferred_tour_indicator;
    public List<Operation> preferred_tour;
    public Operation next_preferred_op;
    public Operation current_op;
    public List<Operation> tour;
    public List<Operation> legal_ops;
    public double num_jobs;
    public double num_machines;
    public JSSP jssp;
    public List<List<Operation>> graph;
    public double makespan;
    public double r;


    public Forager(JSSP jssp){
        this.tour = new ArrayList<Operation>();
        this.jssp = jssp;
        this.num_jobs = jssp.num_jobs;
        this.num_machines = jssp.num_machines;
        this.graph = jssp.getOperations();
        this.current_op = jssp.sink;
    }

    public Operation select_next_op() {
        double total_prob;
        int num_legal = this.legal_ops.size(); // k from paper
        double alpha = 0.9; // alpha from paper

        List<Double> ratings = new ArrayList<Double>();
        if (this.preferred_tour_indicator != num_legal && this.preferred_tour_indicator < num_legal) {
            for (Operation operation : this.legal_ops) { // must improve this...
                if (operation.equals(this.next_preferred_op)) {
                    ratings.add((1 - this.preferred_tour_indicator * 0.9) / (num_legal - this.preferred_tour_indicator));
                } else {
                    ratings.add(1.0/num_legal);
                }
            }
        } else {
            for (Operation operation : this.legal_ops) {
                ratings.add(alpha);
            }
        }

        List<Double> probabilities = new ArrayList<Double>();
        for (int i = 0 ; i < num_legal ; i++){
            probabilities.add(ratings.get(i)*(1.0/legal_ops.get(i).getDuration()));
        }

        // roulette wheel selection for next op
        Operation next_op = null;
        double total = ratings.get(0);
        Random rnd = new Random();

        for( int i = 1; i < num_legal; i++ ) {
            total += probabilities.get(i);
            if(rnd.nextDouble() <= (probabilities.get(i) / total)) {
                next_op = this.legal_ops.get(i);
            }
        }

        if (next_op != this.next_preferred_op) {
            this.preferred_tour_indicator = 0;
        }
        if (this.preferred_tour.size() > 0){
            this.preferred_tour.remove(0);
        }

        return next_op;

    }


    public double calculate_tour_length() {
        double C_max = 0.0;

        List<Double> makespans = new ArrayList<Double>();

        for (int i = 0 ; i < num_jobs - 1 ; i++) {
            continue;
        }
        return Collections.max(makespans);
    }

    public void move(Operation operation_to, Operation operation_from){
        this.tour.add(operation_to); // add operation to tour
        this.legal_ops.remove(operation_to); // add all neigbouring operations to the operation we're moving to
        for (Operation neigbour : operation_to.getNeigbourhood()) {
            this.legal_ops.add(neigbour);
        }
        this.current_op = operation_to;
    }

    public void update_profitability_rating() {
        double C_max = this.calculate_tour_length();
        this.makespan = C_max;
        this.profitability_rating = 1/C_max;
    }


}
