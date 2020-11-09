package ist261.chatbot.component;

import ist261.chatbot.infra.Chatbot;
import ist261.user.intent.AbstractUserIntent;
import ist261.user.intent.TroubleShoot;
import ist261.user.intent.UseCommand;
import ist261.user.intent.WritePBS;

public class LanguageUnderstander {

	private Chatbot chatbot;

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
		String[] useCommandKeyWord = new String[]{"How do", "How to", "Trying do", "Trying to",
				"Can", "Need help", "Need to"};

		// Words to indicate intent is to draft pbs script
		String[] writePBSKeyWord = new String[]{"Autogenerate", "Write", "Draft", "Job script",
				"Pbs script", "Submission Script", "Writing"};

		// Words to indicate the user needs help troubleshooting
		String[] troubleShootKeyWord = new String[]{"Error", "Received", "Trouble", "Troubleshoot"};

		// Loop through each string array
		for (int i = 0; i < useCommandKeyWord.length; i++){
			if(nowInputText.toLowerCase().contains(useCommandKeyWord[i].toLowerCase())){
				nowInputText = "UseCommand";
				break;
			}

		}

		for (int i = 0; i < writePBSKeyWord.length; i++){
			if(nowInputText.toLowerCase().contains(writePBSKeyWord[i].toLowerCase())){
				nowInputText = "WritePBS";
				break;
			}
		}

		for (int i = 0; i < troubleShootKeyWord.length; i++){
			if(nowInputText.toLowerCase().contains(troubleShootKeyWord[i].toLowerCase())){
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
