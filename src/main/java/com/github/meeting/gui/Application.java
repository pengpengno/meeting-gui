package com.github.meeting.gui;

import com.github.meeting.common.connect.connection.client.ClientLifeStyle;
import com.github.meeting.common.connect.connection.client.tcp.ReactorTcpClient;
//import com.github.meeting.gui.app.controller.VideoController;
//import com.github.meeting.gui.util.FxmlLoader;
import com.gluonhq.attach.display.DisplayService;
import com.gluonhq.attach.util.Platform;
import com.gluonhq.charm.glisten.application.AppManager;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.control.FloatingActionButton;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import com.gluonhq.charm.glisten.visual.Swatch;
import javafx.geometry.Dimension2D;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;

import static com.gluonhq.charm.glisten.application.AppManager.HOME_VIEW;

/***
 * {@link Launcher use this start up application}
 */

@Slf4j
public class Application extends javafx.application.Application {


    private final AppManager appManager = AppManager.initialize(this::postInit);


    private void postInit(Scene scene) {

        Swatch.LIGHT_GREEN.assignTo(scene);

        scene.getStylesheets().add(Application.class.getResource("styles.css").toExternalForm());

        if (Platform.isDesktop()) {
            Dimension2D dimension2D = DisplayService.create()
                    .map(DisplayService::getDefaultDimensions)
                    .orElse(new Dimension2D(640, 480));
            scene.getWindow().setWidth(dimension2D.getWidth());
            scene.getWindow().setHeight(dimension2D.getHeight());
        }
    }


    @Override
    public void start(Stage stage) throws IOException {

//        Scene scene = FxmlLoader.applySingleScene(VideoController.class);

        log.debug("connect status  "  );

        try{
            ClientLifeStyle connect =
                    ReactorTcpClient.getInstance().config(new InetSocketAddress("localhost", 8080))
                            .connect();
            log.debug("connect status {}", connect.isAlive());

        }catch (Exception ex){
            log.error("1");
        }


//        ClientToolkit.reactiveClientAction().sendString("connection established").subscribe();


//        stage.setScene(scene);

        stage.show();
    }


    @Override
    public void init() throws Exception {
//
//        appManager.addViewFactory("login", () -> {
//            View view = new View(new LoginController());
//        });


        appManager.addViewFactory(HOME_VIEW, () -> {
            FloatingActionButton fab = new FloatingActionButton(MaterialDesignIcon.SEARCH.text,
                    e -> System.out.println("Search"));

            ImageView imageView = new ImageView(new Image(Application.class.getResourceAsStream("openduke.png")));

            imageView.setFitHeight(200);
            imageView.setPreserveRatio(true);

            Label label = new Label("Hello, Gluon Mobile!");
            VBox root = new VBox(20, imageView, label);
            root.setAlignment(Pos.CENTER);

            View view = new View(root) {
                @Override
                protected void updateAppBar(AppBar appBar) {
                    appBar.setTitleText("Gluon Mobile");
                }
            };

            fab.showOn(view);

            return view;
        });
    }

    public static void main(String[] args) {
        launch();
    }
}