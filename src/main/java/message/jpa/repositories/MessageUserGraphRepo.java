package message.jpa.repositories;

import message.jpa.LDM.MessageModel;
import message.jpa.LDM.MessageUser;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageUserGraphRepo extends Neo4jRepository<MessageUser,Long> {

    @Query("match (U:MessageUser) where U.emailID = {emailID} return U")
    MessageUser findByEmailID(@Param("emailID") String emailID);
    @Query("match (s:MessageUser) where s.emailID = {sender_emailID} match (r:MessageUser) where r.emailID = {receiver_emailID}  create (s)-[:MUTUAL_OF]->(r) set s.username = {username}")
    void createMutualFriendAndUpdateUsername(@Param("sender_emailID") String sender_emailID, @Param("receiver_emailID") String receiver_emailID,@Param("username") String username);
    @Query("match (s:MessageUser) where s.emailID = {sender_emailID} set s.username = {username}")
    void setNewUsername(@Param("sender_emailID") String emailID, @Param("username") String username);
    @Query("match (s:MessageUser) where s.emailID = {sender_emailID} set s.friends ={newFriends} set s.updatedTimestamp={updatedTimeStamp}")
    void setNewFriends(@Param("sender_emailID") String emailID, @Param("newFriends") List<String> newFriends, @Param("updatedTimeStamp") String updatedTimeStamp);
    @Query("match (s:MessageUser) where s.emailID = {sender_emailID} set s.friends ={newFriend} + s.friends set s.updatedTimestamp={updatedTimeStamp}")
    void addNewFriend(@Param("sender_emailID") String sender, @Param("newFriend")List<String> newFriend, @Param("updatedTimeStamp")String updatedTimeStamp);
    @Query("match (s:MessageUser) where s.emailID = {sender_emailID} set s.username = {username},s.updatedTimeStamp={updatedTimeStamp}")
    void modifyUsername(@Param("username") String username, @Param("sender_emailID") String sender,@Param("updatedTimeStamp") String updatedTimeStamp);
    @Query("match (s:MessageModel) where s.senderId = {sender} and s.receiverId = {receiver} return s")
    MessageModel getMessageModel(@Param("sender") String sender, @Param("receiver") String receiver);
    @Query("match (s:MessageModel) where s.senderId = {sender} and s.receiverId = {receiver} set s.isFriend = {isFriend}return s")
    void addFriendMessageModel(@Param("sender") String sender, @Param("receiver") String receiver, @Param("isFriend") boolean isFriend);
}