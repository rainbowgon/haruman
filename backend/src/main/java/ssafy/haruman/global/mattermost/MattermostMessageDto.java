package ssafy.haruman.global.mattermost;

import com.google.gson.annotations.SerializedName;
import lombok.*;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public class MattermostMessageDto {

    @Getter
    public static class Attachments {
        private Props props;
        private List<Attachment> attachments;

        public Attachments() {
            attachments = new ArrayList<>();
        }

        public Attachments(List<Attachment> attachments) {
            this.attachments = attachments;
        }

        public Attachments(Attachment attachment) {
            this();
            this.attachments.add(attachment);
        }

        public void addProps(Exception e) {
            props = new Props(e);
        }

    }

    @Getter
    @AllArgsConstructor
    @Builder
    @ToString
    public static class Attachment {

        private String channel;
        private String pretext;
        private String color;

        @SerializedName("author_name")
        private String authorName;

        @SerializedName("author_icon")
        private String authorIcon;

        private String title;
        private String text;
        private String footer;

        public Attachment addExceptionInfo(Exception e) {
            this.title = e.getClass().getSimpleName();
            StringBuilder sb = new StringBuilder(text);

            sb.append("**Error Message**")
                    .append("\n\n")
                    .append("```")
                    .append(e.getMessage())
                    .append("```")
                    .append("\n\n");

            this.text = sb.toString();

            return this;
        }

        public Attachment addExceptionInfo(Exception e, String uri) {
            this.addExceptionInfo(e);
            StringBuilder sb = new StringBuilder(text);

            sb.append("**Reqeust URL**").append("\n\n").append(uri).append("\n\n");

            this.text = sb.toString();
            return this;
        }

        public Attachment addExceptionInfo(Exception e, String uri, String params) {
            this.addExceptionInfo(e, uri);
            StringBuilder sb = new StringBuilder(text);

            sb.append("**Parameters**")
                    .append("\n\n")
                    .append(params)
                    .append("\n\n");

            this.text = sb.toString();
            return this;
        }

    }

    @Getter
    @NoArgsConstructor
    public static class Props {
        private String card;

        public Props(Exception e) {
            StringBuilder text = new StringBuilder();

            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            text.append("**Stack Trace**")
                    .append("\n\n")
                    .append("```")
                    .append(sw.toString().substring(0, Math.min(5500, sw.toString().length())) + "\n")
                    .append("```")
                    .append("\n\n");

            this.card = text.toString();
        }
    }
}
