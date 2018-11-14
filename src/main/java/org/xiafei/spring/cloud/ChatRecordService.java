package org.xiafei.spring.cloud;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 聊天记录service
 */
@Component
public class ChatRecordService {
    private Map<String, LinkedList<String>> chatRecordMap = new HashMap<>();

    public void storeChatRecord(String groupKey, String text) {
        LinkedList<String> chatRecordList = chatRecordMap.get(groupKey);
        if (chatRecordList == null) {
            chatRecordList = new LinkedList<>();
            chatRecordMap.put(groupKey,chatRecordList);
        }
        chatRecordList.addLast(text);
        if (chatRecordList.size() > 20) {
            chatRecordList.remove(0);
        }

    }

    public List<String> getChatRecord(String groupKey) {
        return chatRecordMap.get(groupKey);
    }
}
