
package com.acme.smg.model;

import java.util.Map;

public interface IPersistenceStorage {

    public Map<String, Topic> getTopics();
    
}
