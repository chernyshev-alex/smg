package com.acme.smg;

import com.acme.smg.model.Article;
import com.acme.smg.model.IPersistenceStorage;
import com.acme.smg.model.Topic;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;
import javax.ws.rs.NotFoundException;

@Singleton
public class PersistenceStorageTest implements IPersistenceStorage {
    
   @Inject private Logger logger;

    private static final Map<String, Topic> topics = new ConcurrentHashMap<>();

    public Map<String, Topic> getTopics() {
        logger.info("getTopics");
        return topics;
    }

    public void addTopic(Topic topic) {
        topics.put(topic.getId(), topic);
    }

    public Topic getTopic(String id) {
        return topics.get(id);
    }

    public void deleteTopic(String topicId) {
        topics.remove(topicId);
    }

    public void addArticle(String topicId, Article article) {
        Topic topic = topics.get(topicId);
        if (topic != null) {
            topic.getArticles().add(article);
        }
    }

    public Article getArticle(String topicId, String articleId) throws NotFoundException {
        Topic topic = topics.get(topicId);
        if (topic == null) {
                throw new NotFoundException("topic " + topicId);
        }

        Iterator<Article> it = topic.getArticles().iterator();
        while (it.hasNext()) {
            Article article = it.next();
            if (article.getId().equals(articleId)) {
                return article;
            }
        }
        throw new NotFoundException("article " + articleId);
    }

    public void deleteArticle(String topicId, String articleId) {
        Topic topic = topics.get(topicId);
        if (topic != null) {
            topic.getArticles().remove(new Article(articleId));
        }
    }

    public static void buildExamples(int cnt) {
        for (int i = 1000; i < 1000 + cnt; i++) {
            String topicKey = Integer.toHexString(i);
            Topic topic = Topic.createWith(topicKey, "title " + topicKey);
            topic.getArticles().add(new Article("1"));
            topic.getArticles().add(new Article("2"));
            topics.put(topic.getId(), topic);
        }
    }

}

