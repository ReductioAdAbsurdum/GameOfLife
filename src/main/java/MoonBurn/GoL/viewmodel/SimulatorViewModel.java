package MoonBurn.GoL.viewmodel;

import MoonBurn.GoL.model.Simulation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class SimulatorViewModel
{
    private Timeline timeline;
    private Simulation simulation;
    private BoardViewModel boardViewModel;

    public SimulatorViewModel(BoardViewModel boardViewModel, Simulation simulation)
    {
        this.boardViewModel = boardViewModel;
        this.simulation = simulation;

        timeline = new Timeline(new KeyFrame(Duration.millis(200), actionEvent -> doStep()));
        timeline.setCycleCount(Timeline.INDEFINITE);
    }

    public void doStep()
    {
        simulation.step();
        boardViewModel.setBoard(simulation.getBoard());
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
