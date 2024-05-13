package com.github.meeting.gui.app.pane.dashbord;

import cn.hutool.core.collection.CollectionUtil;
import com.github.meeting.common.model.AccountContext;
import com.github.meeting.common.model.AccountInfo;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * @author pengpeng
 * @description
 * @date 2023/6/29
 */
@Slf4j
public class DashBoardPane extends FlowPane implements Initializable {


    private AccountInfo accountInfo;

    private Label accountName ;

    @Autowired
    private List<MiniApplication> dashBoardMiniApps;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        this.setBackground(new Background(new BackgroundFill(Color.rgb(1,180,100),null,null)));

        log.info (this.getClass().getName() + "has been build ");

        initPane();

    }


    public void setAccountInfo(AccountInfo accountInfo){
        this.accountInfo = accountInfo ;
    }



    public void initPane(){

        setAccountInfo(AccountContext.getCurAccount());

        accountName = new Label(accountInfo.getAccount());

        this.setOrientation(Orientation.VERTICAL);
        this.setHgap(10);
        this.setVgap(dashBoardMiniApps.size());
        this.getChildren().add( accountName);
        if (CollectionUtil.isNotEmpty(dashBoardMiniApps)){
            log.info("  app size is {}",dashBoardMiniApps.size());
            dashBoardMiniApps.stream().filter(Objects::nonNull).forEach(e-> {

                if (e instanceof Initializable initializable){
                    initializable.initialize(null,null);
                }


                if (e instanceof Region region){
                    region.prefWidthProperty().bind(this.prefWidthProperty());
                }

                if (e instanceof Node node){
                    this.getChildren().add(node);
                }

            });
        }
    }



    private DashBoardPane(){

    }

    private enum SingleInstance{
        INSTANCE;
        private final DashBoardPane instance;
        SingleInstance(){
            instance = new DashBoardPane();
        }
        private DashBoardPane getInstance(){
            return instance;
        }
    }
    public static DashBoardPane getInstance(){
        return SingleInstance.INSTANCE.getInstance();
    }


}
