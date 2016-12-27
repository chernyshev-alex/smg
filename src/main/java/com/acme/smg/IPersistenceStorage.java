
package com.acme.smg;

import com.acme.smg.model.Article;
import com.acme.smg.model.Topic;
import java.util.Map;
import javax.ws.rs.NotFoundException;

public interface IPersistenceStorage {

    public Map<String, Topic> getTopics();

    public Topic getTopic(String id) throws NotFoundException;
    
    public void addTopic(Topic topic);

    public void deleteTopic(String topicId) throws NotFoundException;
    
    public Article getArticle(String topicId, String articleId) throws NotFoundException;
    
    public void addArticle(String topicId, Article article) throws NotFoundException;
    
    public void deleteArticle(String topicId, String articleId) throws NotFoundException;
 
}
