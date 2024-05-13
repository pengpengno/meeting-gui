package com.github.meeting.gui.util;

import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.google.inject.Guice;
import com.google.inject.Injector;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.net.URL;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
/***
 *  所有 fxml 设计为 与controller 同名 且 同路径
 */
public class FxmlLoader {

    private final static ConcurrentHashMap<Class<?>,Stage> stageMap = new ConcurrentHashMap<>();


    private final static ConcurrentHashMap<Class<?>,Scene> sceneMap = new ConcurrentHashMap<>();


    private final static String FXML_SUFFIX = ".fxml";


    /***
     * 获取单例的 Stage
     * @param clazz 传入的类
     * @return 返回加载的 stage
     */
    public static Scene applySingleScene (Class<?> clazz) {
        try{

            Assert.notNull(clazz,"指定路径不可为空！");

            URL resource = clazz.getResource(buildString(clazz));

            FXMLLoader fxmlLoader = new FXMLLoader(resource);

//            Injector injector = Guice.createInjector();
//            fxmlLoader.setControllerFactory(injector::getInstance);
//            fxmlLoader.setControllerFactory(SpringUtil::getBean);

            Parent load = fxmlLoader.load();
            return new Scene(load);

        }
        catch (Exception e){

            log.error("create stage fail {}", ExceptionUtil.stacktraceToString(e));

            return null;

        }
    }

    /***
     * 根据给定路径加载fxml
     * @param clazz 传入的类
     * @return 返回加载的scene
     */
    private static String buildString (Class<?> clazz) {

        return clazz.getSimpleName()+ FXML_SUFFIX;

    }




    /**
     * 获取多例的 Stage
     * @return 返回加载的 stage
     */
    public static Stage applyMultiStage(Class<?> clazz){
        Stage resStage = stageMap.computeIfAbsent(clazz, (path) -> {
            Stage stage = new Stage();
            Scene scene = applySingleScene(clazz);
            stage.setScene(scene);
            return stage;
        });
//        if windows is do close request  handler will close
        resStage.addEventHandler(WindowEvent.WINDOW_CLOSE_REQUEST,(evt)-> {

            log.debug("{} frame  is doing {} event",evt.getSource(),evt.getEventType());

            stageMap.remove(clazz);

        });

        stageMap.put(clazz,resStage);

        return resStage;
    }


    /**
     * 获取单例的 Stage
     * @return 返回加载的 stage
     */
    public static Stage applySingleStage(Class<?> clazz){
        Stage resStage = stageMap.computeIfAbsent(clazz, (path) -> {
            Stage stage = new Stage();
            Scene scene = applySingleScene(clazz);
            stage.setScene(scene);
            return stage;
        });
//        if windows is do close request  handler will close
        resStage.addEventHandler(WindowEvent.WINDOW_CLOSE_REQUEST,(evt)-> {

            log.debug("{} frame  is doing {} event",evt.getSource(),evt.getEventType());

            stageMap.remove(clazz);

        });

        stageMap.put(clazz,resStage);

        return resStage;
    }


}
