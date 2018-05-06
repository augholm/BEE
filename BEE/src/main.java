public class main {

    public static void main(String[] args) {
        int num_bees;
        int num_iterations;


        FileReader file_reader = new FileReader();
        JSSP jssp = file_reader.read("data/2.txt");
        //System.out.println(jssp.operations.size());
        file_reader.make_graph(jssp);

    }
}
