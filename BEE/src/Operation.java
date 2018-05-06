import java.util.ArrayList;
import java.util.List;

public class Operation {

    public int operation_number;
    public int job;
    public int machine;
    public double duration;
    public List<Operation> neigbourhood = new ArrayList<Operation>();

    public Operation(int job, int machine, int duration) {
        this.job = job;
        this.machine = machine;
        this.duration = duration;
        // this.neigbourhood = new ArrayList<Operation>();
    }

    public int getOperation_number() {
        return this.operation_number;
    }

    public int getJob() {
        return this.job;
    }

    public int getMachine() {
        return this.machine;
    }

    public double getDuration() {
        return this.duration;
    }

    public List<Operation> getNeigbourhood() {
        return this.neigbourhood;
    }

}
