package ist261.user.intent;

// Use tuples to make exchanging data easier
import org.javatuples.Pair;

// Import sql functionality
import java.sql.*;

import java.util.ArrayList;
import java.util.Hashtable;

public class TroubleShoot extends AbstractUserIntent{
    public TroubleShoot(String userMsg) { super(userMsg); }

    private Connection conn;
    //private ArrayList<Pair<Integer, String>> qsubPairs;

    @Override
    Hashtable<String, Object> extractSlotValuesFromUserMsg(String userMsg) {
        Hashtable<String, Object> result = new Hashtable<String, Object>();
        System.out.println(userMsg);

        // Read troubleshoot.db to build problem arrays
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:/home/nucci/Documents/ist261_code/ist261_final_project/data/troubleshoot.db");
            conn.setAutoCommit(false);

            // Retrieve data from qsubproblem table
            String qsubProbStmt = "SELECT id, problem FROM qsubproblem";
            PreparedStatement qsubSelect = conn.prepareStatement(qsubProbStmt);
            ResultSet qsubResult = qsubSelect.executeQuery();

            // Build qsubproblem array
            ArrayList<Pair<Integer, String>> qsubPairs = new ArrayList<>();

            while(qsubResult.next()){
                Pair<Integer, String> pair = Pair.with(qsubResult.getInt("id"), qsubResult.getString("problem"));
                qsubPairs.add(pair);
            }

            for (int i = 0; i < qsubPairs.size(); i++) {
                Pair<Integer, String> tmp_pair = qsubPairs.get(i);
                System.out.println(tmp_pair.getValue1());
                if(userMsg.toLowerCase().contains(tmp_pair.getValue1().toLowerCase())){
                    // Convert id to a string
                    result.put("problem", tmp_pair.getValue0().toString());
                    result.put("table", "qsubproblem");
                    return result;
                }

            }

        } catch (SQLException e){
            System.out.println(e);
        }

        return result;
    }
}
