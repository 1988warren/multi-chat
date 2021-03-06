package edu.gwu.cs6431.multichat.demo.client.component.vo;

import edu.gwu.cs6431.multichat.core.protocol.ProtocolProps;
import edu.gwu.cs6431.multichat.core.protocol.client.ClientMessage;
import edu.gwu.cs6431.multichat.core.protocol.server.RelayMessage;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import org.apache.commons.lang3.StringUtils;

/**
 * View object of chat message
 */
public class ChatMessageVO extends HBox {

    private Label label = new Label();

    public ChatMessageVO(ClientMessage message) {
        if(ProtocolProps.TEXT_CONTENT.equals(message.getContentType())) {
            label.setText(org.apache.commons.codec.binary.StringUtils.newStringUtf8(message.getPayload()));
            this.setAlignment(Pos.BASELINE_RIGHT);
        } else {
            label.setText("FILE SHARED");
            this.setAlignment(Pos.BASELINE_CENTER);
        }
        this.getChildren().add(this.label);
    }

    public ChatMessageVO(RelayMessage message) {
        StringBuilder sb = new StringBuilder();
        sb.append("client ");
        sb.append(message.getFrom());
        if(StringUtils.isNotEmpty(message.getNickname())) {
            sb.append(" (");
            sb.append(message.getNickname());
            sb.append(")");
        }

        if(ProtocolProps.TEXT_CONTENT.equals(message.getContentType())) {
            sb.append(" says");
            if(message.getTo() != null) {
                sb.append(" to you");
            }
            sb.append(": ");
            sb.append(org.apache.commons.codec.binary.StringUtils.newStringUtf8(message.getPayload()));
            label.setText(sb.toString());
            this.setAlignment(Pos.BASELINE_LEFT);
        } else {
            sb.append(" shares a file");
            if(message.getTo() != null) {
                sb.append(" with you");
            }
            label.setText(sb.toString());
            this.setAlignment(Pos.BASELINE_CENTER);
        }

        if(message.getTo() != null) {
            // set label background to yellow color
            label.setStyle("-fx-background-color: yellow");
        }

        this.getChildren().add(this.label);
    }
}
