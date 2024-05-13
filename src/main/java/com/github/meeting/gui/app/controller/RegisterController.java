package com.github.meeting.gui.app.controller;

import com.github.meeting.common.model.account.AccountVo;
import com.github.meeting.gui.util.FxmlLoader;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

/***
 * 注册界面
 */
@Slf4j
public class RegisterController  {

    @FXML
    private TextField accountField;

    @FXML
    private TextField mailField;

    @FXML
    private TextField psdField;

    @FXML
    private Button cancel;

    @FXML
    private Button register;

    @FXML
    private VBox registerFrame;

    @FXML
    void register(MouseEvent event) {
        AccountVo accountVo = new AccountVo();

        accountVo.setAccount(accountField.getText());

        accountVo.setPassword(psdField.getText());

        accountVo.setEmail(mailField.getText());

    }

    @FXML
    void cancel(MouseEvent event)   {

        Stage stage = FxmlLoader.applySingleStage(RegisterController.class);

        Stage loginStage = FxmlLoader.applySingleStage(LoginController.class);

        stage.hide();

        loginStage.toFront();

    }


    public static void show(){

        Stage stage = FxmlLoader.applySingleStage(RegisterController.class);

        log.info("prepare to show  register");

        stage.show();

        stage.setTitle("注册");

    }



}
