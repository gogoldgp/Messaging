package message.repositories;

import message.LDM.MessageUser;
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
    @Query("match (s:MessageUser) where s.emailID = {sender_emailID} set s.friends ={newFriends}")
    void setNewFriends(@Param("sender_emailID") String emailID,@Param("newFriends") List<String> newFriends);
}