package ist261.user.intent;

import java.util.Hashtable;

public class WritePBS extends AbstractUserIntent{
    public  WritePBS(String userMsg) { super(userMsg); }

    @Override
    Hashtable<String, Object> extractSlotValuesFromUserMsg(String userMsg) {
        Hashtable<String, Object> result = new Hashtable<String, Object>();
        System.out.println(userMsg);

        String[] shellOption = new String[]{"bash", "csh"};
        String[] yesNo = new String[]{"yes", "no"};

        if(this.getLastestSlotValue("shell")==null){
            for (int i = 0; i < shellOption.length; i++){
                if(userMsg.toLowerCase().contains(shellOption[i].toLowerCase())){
                    result.put("shell", shellOption[i]);
                    return result;
                }
            }

        } else if(this.getLastestSlotValue("alloc")==null &&
        this.getLastestSlotValue("shell")!=null){
            result.put("alloc", userMsg);
            return result;

        } else if(this.getLastestSlotValue("node")==null &&
                this.getLastestSlotValue("alloc")!=null &&
                this.getLastestSlotValue("shell")!=null){
            result.put("node", userMsg);
            return result;

        } else if(this.getLastestSlotValue("ppn")==null &&
                this.getLastestSlotValue("node")!=null &&
                this.getLastestSlotValue("alloc")!=null &&
                this.getLastestSlotValue("shell")!=null){
            result.put("ppn", userMsg);
            return result;

        } else if(this.getLastestSlotValue("pmem")==null &&
                this.getLastestSlotValue("ppn")!=null &&
                this.getLastestSlotValue("node")!=null &&
                this.getLastestSlotValue("alloc")!=null &&
                this.getLastestSlotValue("shell")!=null){
            result.put("pmem", userMsg);
            return result;

        } else if(this.getLastestSlotValue("walltime")==null &&
                this.getLastestSlotValue("pmem")!=null &&
                this.getLastestSlotValue("ppn")!=null &&
                this.getLastestSlotValue("node")!=null &&
                this.getLastestSlotValue("alloc")!=null &&
                this.getLastestSlotValue("shell")!=null){
            result.put("walltime", userMsg);
            return result;

        } else if(this.getLastestSlotValue("email_yes_no")==null &&
                this.getLastestSlotValue("walltime")!=null &&
                this.getLastestSlotValue("pmem")!=null &&
                this.getLastestSlotValue("ppn")!=null &&
                this.getLastestSlotValue("node")!=null &&
                this.getLastestSlotValue("alloc")!=null &&
                this.getLastestSlotValue("shell")!=null){
            for (int i = 0; i < yesNo.length; i++){
                if(userMsg.toLowerCase().contains(yesNo[i].toLowerCase())){
                    result.put("email_yes_no", yesNo[i]);
                    return result;
                }
            }

        } else if(this.getLastestSlotValue("email_yes_no").equals("yes") &&
                this.getLastestSlotValue("walltime")!=null &&
                this.getLastestSlotValue("pmem")!=null &&
                this.getLastestSlotValue("ppn")!=null &&
                this.getLastestSlotValue("node")!=null &&
                this.getLastestSlotValue("alloc")!=null &&
                this.getLastestSlotValue("shell")!=null){
            result.put("email", userMsg);
            return result;
        } else {
            return result;
        }
        return result;
    }
}
