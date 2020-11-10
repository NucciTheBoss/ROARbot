package ist261.user.intent;

import java.util.Hashtable;

public class UseCommand extends AbstractUserIntent{
    public UseCommand(String userMsg) { super(userMsg); }

    @Override
    Hashtable<String, Object> extractSlotValuesFromUserMsg(String userMsg) {
        Hashtable<String, Object> result = new Hashtable<String, Object>();
        System.out.println(userMsg);

        // With current method cannot use "command" or "man"
        String[] commandList = new String[]{"alias", "cat", "cd", "chmod", "curl", "df", "du", "diff", "echo",
        "exit", "find", "grep", "gzip", "head", "history", "kill", "less", "ls", "mkdir", "mv", "ps",
        "pwd", "ssh", "tail", "tar", "top", "which", "wget"};

        for (int i = 0; i < commandList.length; i++){
            if(userMsg.toLowerCase().contains(commandList[i].toLowerCase())){
                result.put("command", commandList[i].toLowerCase());
                break;
            }
        }

        return result;
    }
}
