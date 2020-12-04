package ist261.user.intent;

import java.util.Hashtable;

public class FetchDocumentation extends AbstractUserIntent{
    public FetchDocumentation(String userMsg) { super(userMsg); }

    @Override
    Hashtable<String, Object> extractSlotValuesFromUserMsg(String userMsg) {
        Hashtable<String, Object> result = new Hashtable<String, Object>();
        System.out.println(userMsg);

        // Borrowed from UseCommand intent (some commands may not have documentation)
        String[] docList = new String[]{"alias", "cat", "cd", "chmod", "curl", "df", "du", "diff", "echo",
                "exit", "find", "grep", "gzip", "head", "history", "kill", "less", "ls", "mkdir", "mv", "ps",
                "pwd", "ssh", "tail", "tar", "top", "which", "wget"};

        // Fail safe to make sure documentation PDF is not duplicated
        result.put("doc_command", "null");

        for (int i = 0; i < docList.length; i++) {
            if(userMsg.toLowerCase().contains(docList[i].toLowerCase())){
                result.put("doc_command", docList[i].toLowerCase());
                break;
            }
        }
        return result;
    }
}
