package message.Constants;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MessageConstants {
    public enum ActionType{
        CREATE("create"),UPDATE("update"),NONE("none");
        String action;
        static Map<String,ActionType>  actionMap= new HashMap<>();

        ActionType(String action) {
            this.action = action;
        }

        public String getAction() {
            return action;
        }
        static{
            Arrays.stream(ActionType.values()).forEach(e->actionMap.put(e.getAction(),e));
        }
        public ActionType getActionType(String action){
            return actionMap.get(action);
        }
    }

}
