package com.github.meeting.gui.app.controller;


import com.github.meeting.common.connect.connection.client.ReactiveClientAction;
import com.github.meeting.common.model.account.AccountVo;
import com.github.meeting.gui.util.FxmlLoader;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import java.net.URL;
import java.util.ResourceBundle;


@Slf4j
public class LoginController  implements Initializable {

    @FXML
    private Label account;

    @FXML
    private TextField accountField;

    @FXML
    private CheckBox autoLoginCheckBox;

    @FXML
    private Button cancel;

    @FXML
    private ImageView iconView;

    @FXML
    private Button loginBut;

    @FXML
    private VBox loginFrame;

    @FXML
    private AnchorPane loginPane;

    @FXML
    private Label password;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label registerAccount;

    @FXML
    private CheckBox rememberPsdCheckBox;

    ReactiveClientAction reactiveClientAction;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        passwordField.setText("wangpeng");
        accountField.setText("wangpeng");
        log.debug ("initialing login controller ");
    }

    @FXML
    public void login(MouseEvent event) {
        AccountVo accountVo = AccountVo.builder().account(accountField.getText())
                .password(accountField.getText()).build();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("登录状态");

//        accountApi.login(accountVo)
//                .doOnNext(acc -> {
//                    Platform.runLater(()->  {
//                        log.debug("save accountInfo ");
//                        AccountContext.setCurAccount(acc);
//                    });
//                } )
//            .map(acc -> {
//                Account.AccountInfo accountInfo = ProtoBufMapper.INSTANCE.protocolAccMap(acc);
//                return Account.Authenticate
//                        .newBuilder()
//                        .setAccountInfo(accountInfo)
//                        .build();
//            })
//            .subscribe(auth-> {
//                log.info("res {}", auth.toString());
//                Platform.runLater(()->  {
//                    reactiveClientAction.sendMessage(auth).subscribe();
//                    log.debug("start main frame");
//                    hide();
//                    MainController.show();
//                });
//            });

        alert.contentTextProperty().addListener((a1,a2,a3)-> alert.show());

    }


    @FXML
    void cancelLogin(MouseEvent event) {
        log.info("正在关闭客户端程序-----");
        Platform.exit();
        System.exit(0);
    }

    @FXML
    void toRegister(MouseEvent event)   {
        RegisterController.show();
    }


    public static void show(){
        Stage stage = FxmlLoader.applySingleStage(LoginController.class);
        log.debug("prepare to show  register");
        stage.show();
        stage.setTitle("注册");
    }

    public static  void hide(){
        Stage stage = FxmlLoader.applySingleStage(LoginController.class);
        log.debug("Hide LoginFrame ");
        stage.hide();
    }

}
