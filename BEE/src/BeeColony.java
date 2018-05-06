import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class BeeColony {

    public List<Forager> foragers;
    public List<Forager> best_foragers;
    public double colony_profitability_rating;
    public int num_best;

    public BeeColony(int num_foragers, JSSP jssp, int num_best){

    }

    public void reset_foragers(){
        return;
    }

    public void update_colony_profitability_rating() {
        double total_profitability_rating = 0.0;
        for (Forager forager : this.best_foragers) {
            total_profitability_rating += forager.profitability_rating;
        }
        this.colony_profitability_rating = total_profitability_rating / this.best_foragers.size();
    }

    public void populate_best_foragers() {
        PriorityQueue<Forager> foragers = new PriorityQueue<Forager>(new Comparator<Forager>() {
            @Override
            public int compare(Forager o1, Forager o2) {
                if (o1.makespan < o2.makespan) {
                    return 1;
                } if (o1.makespan > o2.makespan) {
                    return -1;
                }
                return 0;
            }
        });

        for (Forager forager : this.foragers) {
            foragers.add(forager);
        }

        for (int i = 0 ; i < this.num_best ; i++) {
            this.best_foragers.add(foragers.poll());
        }
    }

    public void assign_follow_probabilities(){

        for (Forager forager : this.foragers){
            double profitability = forager.profitability_rating;
            if (!this.best_foragers.contains(forager){
                if (profitability < 0.9*this.colony_profitability_rating) {
                    forager.r = 0.6;
                }else if (profitability < 0.95*this.colony_profitability_rating){
                    forager.r = 0.2;
                }else if (profitability < 1.15*this.colony_profitability_rating){
                    forager.r = 0.02;
                }else {
                    forager.r = 0.00;
                }
            }
        }
    }

    public void waggle_dance(){
        
    }




}
