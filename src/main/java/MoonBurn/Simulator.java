package MoonBurn;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.util.Duration;

public class Simulator
{
    private Timeline timeline;
    private MainView mainView;
    private Simulation simulation;

    public Simulator(MainView mainView)
    {
        timeline = new Timeline(new KeyFrame(Duration.millis(200), this::doStep));
        timeline.setCycleCount(Timeline.INDEFINITE);
        this.mainView = mainView;
        simulation = mainView.getSimulation();
    }

    private void doStep(ActionEvent actionEvent)
    {
        simulation.step();
        mainView.draw();
    }

    public void start()
    {
        timeline.play();
    }

    public void stop()
    {
        timeline.stop();
    }
}
