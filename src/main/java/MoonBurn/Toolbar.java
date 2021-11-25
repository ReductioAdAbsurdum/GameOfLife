package MoonBurn;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;

public class Toolbar extends ToolBar
{
    private MainView mainView;

    public Toolbar(MainView mainView)
    {
        this.mainView = mainView;

        Button draw = new Button("Draw");
        draw.setOnAction(this::handleDrawButton);

        Button erase = new Button("Erase");
        erase.setOnAction(this::handleEraseButton);

        Button step = new Button("Step");
        step.setOnAction(this::handleStepButton);


        this.getItems().addAll(draw,erase,step);
    }

    /**
     * Handles step button press event.
     */
    private void handleStepButton(ActionEvent actionEvent)
    {
        mainView.getSimulation().step();
        mainView.draw();
    }

    /**
     * Handles erase button press event.
     * @param actionEvent
     */
    private void handleEraseButton(ActionEvent actionEvent)
    {
        mainView.setDrawMode(Simulation.DEAD);
    }

    /**
     * Handles draw button press event.
     * @param actionEvent
     */
    private void handleDrawButton(ActionEvent actionEvent)
    {
        mainView.setDrawMode(Simulation.ALIVE);
    }
}
