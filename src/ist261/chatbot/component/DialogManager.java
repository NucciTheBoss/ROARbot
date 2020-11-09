package ist261.chatbot.component;

import ist261.chatbot.infra.Chatbot;
import ist261.user.intent.AbstractUserIntent;

public class DialogManager {

	private Chatbot chatbot;
	
	public DialogManager(Chatbot chatbot) {
		this.chatbot = chatbot;
	}

	public String getConversationalAction(AbstractUserIntent nowUserIntent) {
		if(nowUserIntent==null) {//no user intent detected yet
			return "ask-intent";

		}else if(nowUserIntent.getIntentName().equals("UseCommand")){
			if(nowUserIntent.getLastestSlotValue("command")!=null){
				return "answer-command";
			}else{
				return "ask-command";
			}

		}else if(nowUserIntent.getIntentName().equals("WritePBS")){
			return "getting-to-it";

		}else if(nowUserIntent.getIntentName().equals("TroubleShoot")){
			return "getting-to-it";

		}else{
			if(nowUserIntent.getLastestSlotValue("location")!=null) {
				return "answer";
			}else {
				return "ask-location";
			}
		}
	}

}
