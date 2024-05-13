package com.github.meeting.gui.app.controller;


import cn.hutool.core.io.FileUtil;
import com.github.meeting.gui.app.pane.viewMain.MainView;
import com.github.meeting.gui.util.FxmlLoader;
import com.github.meeting.common.model.account.search.AccountSearchVo;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.extern.slf4j.Slf4j;
import java.net.URL;
import java.util.ResourceBundle;

@Slf4j
public class MainController implements Initializable {

    @FXML
    private TextField searchField;

    @FXML
    private FlowPane dashBoardFlowPane;

    @FXML
    private ToolBar toolBar;

    @FXML
    private Button minButton;

    @FXML
    private Button maxButton;

    @FXML
    private Pane  viewMainPane;

    MainView mainView;

//    DashBoardPane dashBoard;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        log.info("Main Controller is init");
//        mainView = new MainView()
        mainView = MainView.getInstance();
        mainView.initialize(null,null);

        mainView.prefWidthProperty().bind(viewMainPane.widthProperty());

        mainView.prefHeightProperty().bind(viewMainPane.heightProperty());

        viewMainPane.getChildren().add(mainView);

    }



    protected void initSearch(){
        searchField.textProperty().addListener((obs-> {
            String text = searchField.getText();
            AccountSearchVo build =
                AccountSearchVo.builder()
                .likeAccount(searchField.getText())
                .build();
        }));
    }

    @FXML
    void searchAcc(InputMethodEvent event) {

        String text = searchField.getText();

        log.info("当前文本为 {} ",text);

        AccountSearchVo build =
                AccountSearchVo.builder()
                .likeAccount(searchField.getText())
                .build();
    }


    public static void show(){
        Stage stage = FxmlLoader.applySingleStage(MainController.class);
        log.info("prepare to show  main");
        Image icon = new Image(FileUtil.getInputStream("icon/title/conversation.png"));
        stage.getIcons().add(icon);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("IFx");
        stage.show();

    }


}
