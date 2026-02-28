package com.hongyi.hr.framework.websocket.core.sender.local;

import com.hongyi.hr.framework.websocket.core.sender.AbstractWebSocketMessageSender;
import com.hongyi.hr.framework.websocket.core.sender.WebSocketMessageSender;
import com.hongyi.hr.framework.websocket.core.session.WebSocketSessionManager;

/**
 * 本地的 {@link WebSocketMessageSender} 实现类
 *
 * 注意：仅仅适合单机场景！！！
 *
 * @author 芋道源码
 */
public class LocalWebSocketMessageSender extends AbstractWebSocketMessageSender {

    public LocalWebSocketMessageSender(WebSocketSessionManager sessionManager) {
        super(sessionManager);
    }

}
