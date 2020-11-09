package ist261.chatbot.infra;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

public class ChatbotGUI extends JFrame {

	private Chatbot nowChatbot;

	private JFrame nowGUIFrame;

	private JTextField inputTextBox;
	private JTextPane chatHistoryPane;

	private String initMessage;

	public ChatbotGUI(Chatbot nowChatbot) {

		this.nowChatbot = nowChatbot;

		//create the frame of chatbot
		nowGUIFrame = new JFrame();
		nowGUIFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		nowGUIFrame.setVisible(true);
		nowGUIFrame.setResizable(false);
		nowGUIFrame.setLayout(null);
		nowGUIFrame.setSize(600, 1000);
		nowGUIFrame.setTitle(nowChatbot.getBotName());
		nowGUIFrame.getContentPane().setBackground(new java.awt.Color(43, 43, 43));

		//create JTextPane
		chatHistoryPane = new JTextPane();
		nowGUIFrame.add(chatHistoryPane);
		chatHistoryPane.setSize(550, 900);
		chatHistoryPane.setLocation(25, 2);

		//create JTextField
		inputTextBox = new JTextField();
		nowGUIFrame.add(inputTextBox);
		inputTextBox.setSize(540, 30);
		inputTextBox.setLocation(30, 920);
		inputTextBox.setToolTipText("Ask WeatherBot about the weather!");
		inputTextBox.addActionListener(new InputTextListener(inputTextBox, chatHistoryPane, this));

		// Add default message that indicates to user what they should ask
		initMessage = "Hey there! My name is "+nowChatbot.getBotName()+"! I can help you with the following things:\n" +
				"    1. General Linux usage\n" +
				"    2. Writing PBS scripts\n" +
				"    3. Basic troubleshooting\n" +
				"Feel free to start asking me questions!";

		ChatbotGUI.chatBotappendToPane(chatHistoryPane, nowChatbot.getBotName(), initMessage, Color.BLACK);

	}

	public ChatbotGUI() {
		// TODO Auto-generated constructor stub
	}

	public Chatbot getChatbot() {
		return nowChatbot;
	}

	public static void appendToPane(JTextPane nowPane, String senderName, String message, Color color){

		String nowMsg = senderName+": "+message+"\n";

		StyleContext sc = StyleContext.getDefaultStyleContext();
		AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, color);

		aset = sc.addAttribute(aset, StyleConstants.FontFamily, "Lucida Console");
		aset = sc.addAttribute(aset, StyleConstants.FontSize, 16);
		aset = sc.addAttribute(aset, StyleConstants.Alignment, StyleConstants.ALIGN_RIGHT);
		aset = sc.addAttribute(aset, StyleConstants.Background, Color.WHITE);

		int len = nowPane.getDocument().getLength();
		nowPane.setCaretPosition(len);
		nowPane.setCharacterAttributes(aset, false);
		nowPane.replaceSelection(nowMsg);

	}

	public static void chatBotappendToPane(JTextPane nowPane, String senderName, String message, Color color){

		String nowMsg = senderName+": "+message+"\n";

		StyleContext sc = StyleContext.getDefaultStyleContext();
		AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, color);

		aset = sc.addAttribute(aset, StyleConstants.FontFamily, "Lucida Console");
		aset = sc.addAttribute(aset, StyleConstants.FontSize, 16);
		aset = sc.addAttribute(aset, StyleConstants.Alignment, StyleConstants.ALIGN_JUSTIFIED);
		aset = sc.addAttribute(aset, StyleConstants.Background, new java.awt.Color(246, 239, 239));

		int len = nowPane.getDocument().getLength();
		nowPane.setCaretPosition(len);
		nowPane.setCharacterAttributes(aset, false);
		nowPane.replaceSelection(nowMsg);

	}


}

class InputTextListener implements ActionListener{

	private ChatbotGUI chatbotUtil;

	private JTextField nowInputTextBox;
	private JTextPane nowChatHistoryPane;

	public InputTextListener(JTextField inputTextBox, JTextPane chatHistoryPane, ChatbotGUI chatbotUtil) {
		this.chatbotUtil = chatbotUtil;
		nowInputTextBox = inputTextBox;
		nowChatHistoryPane = chatHistoryPane;
		//nowUserName = userName;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String nowInputText = nowInputTextBox.getText();
		ChatbotGUI.appendToPane(nowChatHistoryPane, chatbotUtil.getChatbot().getUserName(), nowInputText, Color.BLUE);

		String nowChatbotResponse = chatbotUtil.getChatbot().getResponse(nowInputText);
		ChatbotGUI.chatBotappendToPane(nowChatHistoryPane, chatbotUtil.getChatbot().getBotName(), nowChatbotResponse, Color.BLACK);

		//
		//appendToPane(nowChatHistoryPane, nowMsg, Color.BLUE);

		nowInputTextBox.setText("");

	}


}
