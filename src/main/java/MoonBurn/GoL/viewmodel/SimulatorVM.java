package MoonBurn.GoL.viewmodel;

import MoonBurn.GoL.model.Simulation;
import MoonBurn.GoL.model.enums.ApplicationState;
import MoonBurn.GoL.model.enums.CellState;
import MoonBurn.GoL.util.event.classes.SimulatorEvent;
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

    public void handleSimulatorEvent(SimulatorEvent event)
    {
        switch (event.getEventType())
        {
            case STEP:
                doStep();
                break;
            case START:
                start();
                break;
            case STOP:
                stop();
                break;
            case CLEAR:
                clear();
                break;
        }
    }

    private void doStep()
    {
        simulation.step();
        boardVM.getBoardProp().setValue(simulation.getBoard());
    }

    private void start()
    {
        timeline.play();
    }

    public void stop()
    {
        timeline.stop();
    }

    private void clear()
    {
        timeline.stop();

        boardVM.getBoardProp().getValue().clearBoard();
        boardVM.getBoardProp().notifyOfExternalChange();
    }
}
