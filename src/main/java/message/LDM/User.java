package message.LDM;

import java.util.List;

public class User extends BaseModel {
    private Long userIdentifier;
    private String username;
    private String password;
    private List<MeesageModel> meesageModelList;
    private List<User> friends;

}
