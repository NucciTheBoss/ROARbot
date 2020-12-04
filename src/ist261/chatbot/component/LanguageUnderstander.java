package ist261.chatbot.component;

import ist261.chatbot.infra.Chatbot;
import ist261.user.intent.AbstractUserIntent;
import ist261.user.intent.TroubleShoot;
import ist261.user.intent.UseCommand;
import ist261.user.intent.WritePBS;

// Need to query databases
import java.sql.*;
import java.util.ArrayList;

public class LanguageUnderstander {

	private Chatbot chatbot;

	private Connection conn;

	private AbstractUserIntent latestUserIntent;
	
	public LanguageUnderstander(Chatbot chatbot) {
		this.chatbot = chatbot;
	}
	
	public AbstractUserIntent getLatestUserIntent(String nowInputText) {
		detectUserIntent(nowInputText);
		return latestUserIntent;
	}

	public void detectUserIntent(String nowInputText) {
		
		if(isUserIntent(nowInputText, "UseCommand")) {
			System.out.println("Detected User Intent: UseCommand");
			if(latestUserIntent!=null&&latestUserIntent.getIntentName().equals("UseCommand")) {//intent continues
				latestUserIntent.updateSlotValues(nowInputText);
			}else {//new intent
				System.out.println("Updated User Intent: UseCommand");
				latestUserIntent = new UseCommand(nowInputText);
			}
			
		}else if(isUserIntent(nowInputText, "WritePBS")){
			System.out.println("Detected User Intent: WritePBS");
			if(latestUserIntent!=null&&latestUserIntent.getIntentName().equals("WritePBS")) {//intent continues
				latestUserIntent.updateSlotValues(nowInputText);
			}else {//new intent
				System.out.println("Updated User Intent: WritePBS");
				latestUserIntent = new WritePBS(nowInputText);
			}
		}else if(isUserIntent(nowInputText, "TroubleShoot")){
			System.out.println("Detected User Intent: TroubleShoot");
			if(latestUserIntent!=null&&latestUserIntent.getIntentName().equals("TroubleShoot")) {//intent continues
				latestUserIntent.updateSlotValues(nowInputText);
			}else {//new intent
				System.out.println("Updated User Intent: TroubleShoot");
				latestUserIntent = new TroubleShoot(nowInputText);
			}
		}else {
			System.out.println("Detected User Intent: Else");
			if(latestUserIntent!=null&&latestUserIntent.getIntentName().equals("UseCommand")) {//intent continues
				latestUserIntent.updateSlotValues(nowInputText);
			}else if(latestUserIntent!=null&&latestUserIntent.getIntentName().equals("WritePBS")) {//intent continues
				latestUserIntent.updateSlotValues(nowInputText);
			}else if(latestUserIntent!=null&&latestUserIntent.getIntentName().equals("TroubleShoot")) {//intent continues
				latestUserIntent.updateSlotValues(nowInputText);
				}
			}
		}


	private boolean isUserIntent(String nowInputText, String userIntentName) {
		// Words to indicate intent is to get help using command
		String originalNowInputText = nowInputText;
		String[] useCommandKeyWord = new String[]{"How do", "How to", "Trying do", "Trying to",
				"Can", "Need help", "Need to"};

		// Words to indicate user is trying to retrieve documentation
		String[] fetchDocKeyWord = new String[]{"Documentation", "Doc", "Man Pages", "Manual", "Get me",
				"Retrieve for me"};

		// Words to indicate intent is to draft pbs script
		String[] writePBSKeyWord = new String[]{"Autogenerate", "Write", "Draft", "Job script",
				"Pbs script", "Submission Script", "Writing"};

		// Words to indicate the user needs help troubleshooting
		String[] troubleShootKeyWord = new String[]{"Error", "Received", "Trouble", "Troubleshoot", "Issue"};

		// List of errors stored in troubleshoot.db
		ArrayList<String> troubleShootProb = new ArrayList<>();
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:/home/nucci/Documents/ist261_code/ist261_final_project/data/troubleshoot.db");
			conn.setAutoCommit(false);

			String qsubProbStmt = "SELECT problem FROM qsubproblem";
			PreparedStatement qsubSelect = conn.prepareStatement(qsubProbStmt);
			ResultSet qsubResult = qsubSelect.executeQuery();

			while(qsubResult.next()){
				troubleShootProb.add(qsubResult.getString("problem"));
			}

		} catch (SQLException e) {
			System.out.println(e);
		}

		// Loop through each string array
		for (int i = 0; i < useCommandKeyWord.length; i++){
			if(originalNowInputText.toLowerCase().contains(useCommandKeyWord[i].toLowerCase())){
				nowInputText = "UseCommand";
				break;
			}

		}

		for (int i = 0; i < fetchDocKeyWord.length; i++) {
			if(originalNowInputText.toLowerCase().contains(fetchDocKeyWord[i].toLowerCase())){
				nowInputText = "FetchDocumentation";
				break;
			}
		}

		for (int i = 0; i < writePBSKeyWord.length; i++){
			if(originalNowInputText.toLowerCase().contains(writePBSKeyWord[i].toLowerCase())){
				nowInputText = "WritePBS";
				break;
			}
		}

		for (int i = 0; i < troubleShootKeyWord.length; i++){
			if(originalNowInputText.toLowerCase().contains(troubleShootKeyWord[i].toLowerCase())){
				nowInputText = "TroubleShoot";
				break;
			}
		}

		for (int i = 0; i < troubleShootProb.size(); i++) {
			if(originalNowInputText.toLowerCase().contains(troubleShootProb.get(i).toLowerCase())){
				nowInputText = "TroubleShoot";
				break;
			}
		}

		if(nowInputText.contains(userIntentName)) {
			return true;
		}
		return false;
	}

}
