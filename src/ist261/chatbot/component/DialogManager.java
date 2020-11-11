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
			if(nowUserIntent.getLastestSlotValue("shell")==null){
				return "ask-shell";
			}
			else if(nowUserIntent.getLastestSlotValue("alloc")==null){
				return "ask-alloc";
			}
			else if(nowUserIntent.getLastestSlotValue("node")==null){
				return "ask-node";
			}
			else if(nowUserIntent.getLastestSlotValue("ppn")==null){
				return "ask-ppn";
			}
			else if(nowUserIntent.getLastestSlotValue("pmem")==null){
				return "ask-pmem";
			}
			else if(nowUserIntent.getLastestSlotValue("walltime")==null){
				return "ask-walltime";
			}
			else if(nowUserIntent.getLastestSlotValue("email_yes_no")==null){
				return "ask-if-email";
			}
			else if(nowUserIntent.getLastestSlotValue("email_yes_no")!=null){
				if(nowUserIntent.getLastestSlotValue("email_yes_no")=="no"){
					return "finish-script";
				}
				else{
					if (nowUserIntent.getLastestSlotValue("email")==null){
						return "ask-email";
					}
					else {
						return "finish-script";
					}
				}
			}

		}else if(nowUserIntent.getIntentName().equals("TroubleShoot")){
			if (nowUserIntent.getLastestSlotValue("problem")==null){
				return "ask-problem";

			}else if(nowUserIntent.getLastestSlotValue("problem")!=null){
				return "return-solution";

			}else{
				return "no-solution";
			}

		}
		return "";
	}

}
