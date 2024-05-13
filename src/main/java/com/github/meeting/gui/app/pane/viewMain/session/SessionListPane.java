package com.github.meeting.gui.app.pane.viewMain.session;

import com.github.meeting.common.model.account.session.SessionInfoVo;
import com.github.meeting.gui.api.ChatApi;
import com.github.meeting.gui.app.event.SessionEvent;
import com.github.meeting.gui.app.event.handler.SwitchSessionPaneHandler;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

@Component
@Slf4j
@RequiredArgsConstructor
public class SessionListPane extends FlowPane implements Initializable {


    private final Map<Long,SessionMessageMinPane> sessionMessageMinPaneMap = new HashMap<>();

    private final ChatApi chatApi;

    private final List<SwitchSessionPaneHandler> switchSessionPaneHandlers;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        initPane();

    }

    public void initPane(){
        this.setHgap(FlowPane.USE_PREF_SIZE);

        this.setVgap(1);
        this.setOrientation(Orientation.VERTICAL);
        this.setAlignment(Pos.TOP_LEFT);
        this.setBackground(new Background(new BackgroundFill(Color.rgb(136,10,160),null,null)));

    }



//    private SessionListPane(){
//        sessionMessageMinPaneMap = new HashMap<>();
//    }




    public void addSession(SessionInfoVo sessionInfoVo){

        if (sessionInfoVo == null){
            throw new IllegalArgumentException("Session is illegal ,Could not create SessionMinPane");
        }

        Long sessionId = sessionInfoVo.getSessionId();




        SessionMessageMinPane sessionMessageMinPane = new SessionMessageMinPane(sessionInfoVo);

        sessionMessageMinPane.prefWidthProperty().bind(this.widthProperty());
        sessionMessageMinPane.prefHeightProperty().set(SessionMessageMinPane.HEIGHT);

        sessionMessageMinPaneMap.putIfAbsent(sessionId,sessionMessageMinPane);

        this.getChildren().add(sessionMessageMinPane);

        clickHandler(sessionMessageMinPane);


    }


    public void clickHandler(SessionMessageMinPane minPane){

        if (minPane != null){
            minPane.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                log.info("click min pane");
                if (minPane.getSessionInfoVo() != null){
                    SessionEvent sessionEvent = new SessionEvent(SessionEvent.SESSION_SWITCH, minPane.getSessionInfoVo());
                    switchSessionPaneHandlers.stream().forEach(e-> e.switchSessionEvent(sessionEvent));
                }
            });
        }
    }





//    private enum SingleInstance{
//        INSTANCE;
//        private final SessionListPane instance;
//        SingleInstance(){
//            instance = new SessionListPane();
//        }
//        private SessionListPane getInstance(){
//            return instance;
//        }
//    }
//    public static SessionListPane getInstance(){
//        return SingleInstance.INSTANCE.getInstance();
//    }


}
