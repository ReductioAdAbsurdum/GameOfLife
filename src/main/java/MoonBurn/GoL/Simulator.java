package MoonBurn.GoL;

import MoonBurn.GoL.viewmodel.BoardViewModel;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class Simulator
{
    private Timeline timeline;
    private Simulation simulation;
    private BoardViewModel boardViewModel;

    public Simulator(BoardViewModel boardViewModel, Simulation simulation)
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
