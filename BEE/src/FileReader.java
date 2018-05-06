import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.util.Scanner;

public class FileReader {

    public String filename;
    public List<List<Operation>> operation_sequence = new ArrayList<List<Operation>>();


    public JSSP read(String filename){
        List<String> input_lines;

        try {
            input_lines = Files.readAllLines(Paths.get(filename));
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        String line = input_lines.get(0);
        input_lines.remove(0);

        Scanner scanner = new Scanner(line);
        int num_jobs = scanner.nextInt();
        int num_machines = scanner.nextInt();

        for (int i = 0 ; i<input_lines.size()-1; i++) { // TBD: CHECK WHETHER SCANNER ACTUALLY INCLUDES ONE LINE TOO MANY AT THE END
            this.operation_sequence.add(new ArrayList<Operation>());
            scanner = new Scanner(input_lines.get(i));
            while (scanner.hasNextInt()) {
                int job_no = i;
                int machine_no = scanner.nextInt();
                int duration = scanner.nextInt();
                Operation operation = new Operation(job_no, machine_no, duration);
                this.operation_sequence.get(i).add(operation);
            }
        }
        JSSP jssp = new JSSP(num_jobs, num_machines, operation_sequence);
        scanner.close();

        return jssp;
    }

    public void make_graph(JSSP jssp) {
        // updates the JSSP object


        Operation source = new Operation(-2, -2, 0);
        Operation sink = new Operation(-1, -1, 0);
        jssp.source = source; // update jssp object
        jssp.sink = sink; // update jssp object

        for (List<Operation> operations : jssp.operations){ // neighbours to source node
            //System.out.println(operations.size());
            source.neigbourhood.add(operations.get(0));
        }

        for (List<Operation> operations : jssp.operations) { // build neigbourhood
            for (int i = 0 ; i<operations.size()-1 ; i++) {
                operations.get(i).getNeigbourhood().add(operations.get(i + 1));
            }
        }

        for (List<Operation> operations : jssp.operations) { // add sink to neighbourhood of final operations for each job
            operations.get(operations.size()-1).getNeigbourhood().add(sink);
        }

    }



}
