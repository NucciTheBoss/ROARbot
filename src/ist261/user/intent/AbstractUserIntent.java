package ist261.user.intent;

import java.util.Hashtable;

public abstract class AbstractUserIntent {
	
	private Hashtable<String, Object> slotTable;

	public String getIntentName() {
		return this.getClass().getSimpleName();
	}
	
	abstract Hashtable<String, Object> extractSlotValuesFromUserMsg(String userMsg); 
	
	public void updateSlotValues(String newMsg){
		Hashtable<String, Object> localTable = extractSlotValuesFromUserMsg(newMsg);
		//System.out.println(localTable.size());
		if(localTable!=null) {
			for(String nowKey: localTable.keySet()) {
				System.out.println("Update Slot:"+ nowKey+" = "+localTable.get(nowKey));
				if(nowKey!=null&&localTable.get(nowKey)!=null) {
					//System.out.println(nowKey+", "+localTable.get(nowKey));
					slotTable.put(nowKey, localTable.get(nowKey));
				}	
			}
		}
	}
	
	public Object getLastestSlotValue(String slotName){
		//updateSlotValues();
		//System.out.println(slotName);
		return slotTable.get(slotName);
	}
	
	public Hashtable<String, Object> getLatestSlotValues(){
		//updateSlotValues();
		return slotTable;
	}
	
	public AbstractUserIntent(String userMsg) {
		this.slotTable = new Hashtable<String, Object>();
		updateSlotValues(userMsg);
	}
}
