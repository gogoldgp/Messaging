package message.jpa.repositories;

import message.jpa.LDM.MessageModel;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageClusterGraphRepo extends Neo4jRepository<MessageModel,Long> {

    @Query("match (m:MessageModel{messageClusterIdentifier:{nodesForMessageModelAndStampedMessages}}),(sender:MessageUser{emailID:{senderId}}), (rec:MessageUser{emailID:{receiverId}})" +
            "CREATE (sender)-[:CLUSTER_OF]->(m),(rec)-[:CLUSTER_OF]->(m)")
    void updateRelationships(@Param("nodesForMessageModelAndStampedMessages")String nodesForMessageModelAndStampedMessages, @Param("senderId")String senderId,@Param("receiverId") String receiverId);
}
