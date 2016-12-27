package com.acme.smg;

import com.acme.smg.model.Article;
import com.acme.smg.model.Topic;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;
import javax.ws.rs.NotFoundException;

@Singleton
public class PersistenceStorage implements IPersistenceStorage {

    private final  Logger logger;

    private static final Map<String, Topic> topics = new ConcurrentHashMap<>();

    @Inject
    public PersistenceStorage(Logger logger) {
       this.logger = logger;
     }

    @Override
    public Map<String, Topic> getTopics() {
        return topics;
    }

    @Override
    public void addTopic(Topic topic) {
        topics.put(topic.getId(), topic);
    }

    @Override
    public Topic getTopic(String id) {
        return topics.get(id);
    }

    @Override
    public void deleteTopic(String topicId) {
        topics.remove(topicId);
    }

    @Override
    public void addArticle(String topicId, Article article) {
        Topic topic = topics.get(topicId);
        if (topic != null) {
            topic.getArticles().add(article);
        }
    }

    @Override
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

    @Override
    public void deleteArticle(String topicId, String articleId) {
        Topic topic = topics.get(topicId);
        if (topic != null) {
            topic.getArticles().remove(new Article(articleId));
        }
    }

}

