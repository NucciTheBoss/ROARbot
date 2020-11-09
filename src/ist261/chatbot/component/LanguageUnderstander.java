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
			if(latestUserIntent!=null&&latestUserIntent.getIntentName().equals("WeatherForecast")) {//intent continues
				latestUserIntent.updateSlotValues(nowInputText);
			}else if(latestUserIntent!=null&&latestUserIntent.getIntentName().equals("WillRain")) {//intent continues
				latestUserIntent.updateSlotValues(nowInputText);
			}
		}
		
	}

	private boolean isUserIntent(String nowInputText, String userIntentName) {

		if(nowInputText.contains(userIntentName)) {
			return true;
		}
		return false;
	}

}
