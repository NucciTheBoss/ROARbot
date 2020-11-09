package ist261.chatbot.infra;

import ist261.chatbot.component.DialogManager;
import ist261.chatbot.component.LanguageUnderstander;
import ist261.chatbot.component.ResponseGenerator;
import ist261.user.intent.AbstractUserIntent;

public class Chatbot {
	
	private String userName = "YOUR NAME HERE";
	private String botName = "BOT NAME HERE";
	
	private LanguageUnderstander lu;
	private DialogManager dm;
	private ResponseGenerator rg;
	
	
	public Chatbot(String userName, String botName) {
		
		this.userName = userName;
		this.botName = botName;
		
		this.lu = new LanguageUnderstander(this);
		this.dm = new DialogManager(this);
		this.rg = new ResponseGenerator(this);
		
	}
	
	public String getResponse(String nowInputText) {
		
		/*
		 * Task 1: Implement the isUserIntent() method in LanguageUnderstander.java
		 * 
		 * == What Does This Method Do ==
		 * This method takes a user's message as input (String nowInputText) and identifies its user intent. If
		 * the identified intent equals to the given intent (String userIntentName), this method returns true.
		 * It returns false otherwise.
		 * 
		 * In this assignment, we have two predefined intent: "WillRain" and "WeatherForecast". "WillRain" refers
		 * to the intent when the user wants to know if it will rain today (e.g., "Will it rain today?"), and 
		 * "WeatherForecast" refers to the intent when the user wants to hear a weather forecast. 
		 * 
		 * Also, when a message does not fit in any of these intents-- for example, "How are you?" or "Hello!"--
		 * your method should always return false.
		 * 
		 * There's no "correct" way yo do intent detection. You can use keywords to judge (e.g., if this message
		 * contains certain keywords); you can use a regular expression to match some common expressions (e.g.,
		 * "Will it rain tomorrow?"); or you can create a machine-learning model to predict the user intent. You
		 * will need to explain how you did it in the video.
		 * 
		 */
		AbstractUserIntent nowUserIntent = lu.getLatestUserIntent(nowInputText);
		if(nowUserIntent!=null) {
			System.out.println("Latest User Intent: "+nowUserIntent.getIntentName());
		}else {
			System.out.println("Latest User Intent: no intent detected yet");
		}
		
		/*
		 * Task 2: Implement the extractSlotValuesFromUserMsg() methods in WillRain.java and WeatherForecast.java
		 * 
		 * == What Does This Method Do ==
		 * "WillRain" and "WeatherForecast" are two intents we defined for this assignment. "WillRain" refers to
		 * the intent when the user wants to know if it will rain today (e.g., "Will it rain today?"); and
		 * "WeatherForecast" refers to the intent when the user wants to hear a weather forecast. Both intents
		 * require a piece of critical information: location.
		 * 
		 * You will implement the extractSlotValuesFromUserMsg() methods to extract the "location" from the user
		 * message (String userMsg). For example, when the message is "I'm in New York!" your code needs to
		 * extract "New York"; when the message says "I live in LA.", your code needs to extract "LA."
		 * 
		 * Finally, you will need to put the extracted location to the HashTable I created for you. For example,
		 * when the extracted location is "State College," you can do the following: 
		 * 		result.put("location", "State College");
		 * 
		 * In this assignment, we assume one user message contains no more than one location. So don't worry about
		 * extracting 2 or more locations. You can just extract the first location occurs in a message.
		 * 
		 * There are different ways to extract locations in a string. You can create a dictionary of city names
		 * and match the city names; you can use regular expressions to match common English expressions for
		 * location; or you can use external packages to identify locations. We don't have a "correct way" here.
		 * You will need to explain how you do it in the video.
		 * 
		 * The extractSlotValuesFromUserMsg() method in WillRain.java and WeatherForecast.java are basically the
		 * same. You can simply copy and paste the code from one to the other, or you can create an
		 * extractLocationFromUserMsg() method in their parent class (AbstractUserIntent.java) and call this
		 * method-- Hey, this is the magic of OOP!
		 * 
		 */
		String nowConversationalAction = dm.getConversationalAction(nowUserIntent);
		System.out.println("nowConversationalAction: "+nowConversationalAction);
		
		
		/*
		 * Task 3: Implement the getResponse() method in ResponseGenerator.java
		 * 
		 * == What Does This Method Do ==
		 * This method takes the latest user intent (AbstractUserIntent nowUserIntent) and conversational action
		 * (String nowConversationalAction) as input, return a message that will be sent back to the user. For
		 * example, when (nowUserIntent = WeatherForecast, nowConversationalAction = answer), it will just return
		 * the weather forecast of the location;  when (nowUserIntent = WeatherForecast, nowConversationalAction 
		 * = ask-location), you can say "Where are you?" or "What's your location?".
		 * 
		 * In this assignment, we do NOT require you to hook your code with a real working weather service API
		 * (e.g., WeatherUnderground). So you can just make up a response that is reasonable for each condition.
		 * 
		 */
		String finalResponse = rg.getResponse(nowUserIntent, nowConversationalAction);
		
		return finalResponse;

	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getBotName() {
		return botName;
	}

	public void setBotName(String botName) {
		this.botName = botName;
	}

	
	
	

}
