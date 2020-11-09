package ist261.user.intent;

import java.util.Hashtable;

public class UseCommand extends AbstractUserIntent{
    public UseCommand(String userMsg) { super(userMsg); }

    @Override
    Hashtable<String, Object> extractSlotValuesFromUserMsg(String userMsg) {
        Hashtable<String, Object> result = new Hashtable<String, Object>();
        System.out.println(userMsg);

        return result;
    }
}
