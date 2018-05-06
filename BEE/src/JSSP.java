import java.util.List;

public class JSSP {

    int num_jobs;
    int num_machines;
    Operation source;
    Operation sink;
    List<List<Operation>> operations;

    public JSSP(int num_jobs, int num_machines, List<List<Operation>> operations){
        this.num_jobs = num_jobs;
        this.num_machines = num_machines;
        this.operations = operations;
    }

    public int getNum_jobs() {
        return num_jobs;
    }

    public int getNum_machines() {
        return num_machines;
    }

    public List<List<Operation>> getOperations() {
        return operations;
    }

    @Override
    public String toString() {
        return "JSSP with " + this.num_jobs + " jobs and " + this.num_machines + " machines.";
    }
}
