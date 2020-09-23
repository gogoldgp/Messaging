package message.Constants;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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
        public static ActionType getActionType(String action){
            if(Objects.isNull(action)){
                return null;
            }
            return actionMap.get(action);
        }
    }

}
