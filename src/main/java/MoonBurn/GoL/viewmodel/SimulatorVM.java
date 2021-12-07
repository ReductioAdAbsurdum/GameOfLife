package MoonBurn.GoL.viewmodel;

import MoonBurn.GoL.model.Simulation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class SimulatorVM
{
    private Timeline timeline;
    private Simulation simulation;
    private BoardVM boardVM;

    public SimulatorVM(BoardVM boardVM, Simulation simulation)
    {
        this.boardVM = boardVM;
        this.simulation = simulation;

        timeline = new Timeline(new KeyFrame(Duration.millis(200), actionEvent -> doStep()));
        timeline.setCycleCount(Timeline.INDEFINITE);
    }

    public void doStep()
    {
        simulation.step();
        boardVM.getBoardProp().setValue(simulation.getBoard());

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
