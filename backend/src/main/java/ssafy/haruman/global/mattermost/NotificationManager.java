package ssafy.haruman.global.mattermost;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationManager {

    private final MattermostSender mmSender;
    private Logger log = LoggerFactory.getLogger(NotificationManager.class);

    public void sendNotification(Exception e, String uri, String params) {
        log.info("#### SEND Notification");
        mmSender.sendMessage(e, uri, params);
    }

}