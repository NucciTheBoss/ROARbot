package ist261.chatbot.component;

import ist261.chatbot.infra.Chatbot;
import ist261.user.intent.AbstractUserIntent;
import java.util.Random;

public class ResponseGenerator {

	private Chatbot chatbot;
	
	public ResponseGenerator(Chatbot chatbot) {
		this.chatbot = chatbot;
	}

	public String getResponse(AbstractUserIntent nowUserIntent, String nowConversationalAction) {
		Random randomInt = new Random();
		if(nowUserIntent!=null&&nowUserIntent.getIntentName().equals("UseCommand")) {
			String nowCommand = (String)nowUserIntent.getLastestSlotValue("command");
			if(nowConversationalAction.equals("ask-command")){
				return "Please tell me the command you wish to use";
			}else if(nowConversationalAction.equals("answer-command")){
				return "Here is your answer";
			}else{
				return "I'm sorry, but I did not understand that :(";
			}

		}else if(nowUserIntent!=null&&nowUserIntent.getIntentName().equals("WritePBS")){

		}else if(nowUserIntent!=null&&nowUserIntent.getIntentName().equals("TroubleShoot")){

		}else{
			int otherResponse = randomInt.nextInt(3);
			switch (otherResponse) {
				case 0:
					return "I wasn't programmed to be a social bot :( " +
							"but I am great at getting the weather forecast! " +
							"Ask me about the weather forecast!";

				case 1:
					return "I'm sorry Dave. I can't do that. But what I can do is " +
							"tell you if it will rain or not. Ask me if it is going " +
							"rain today!";

				case 2:
					return "I'm not much for conversation, but I do love to talk about " +
							"the weather! Ask me if it is going to rain today, or about " +
							"today's weather forecast!";
			}
		}
		return "";
	}
}
