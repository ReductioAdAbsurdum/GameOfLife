package view;

import MoonBurn.GoL.model.enums.CellState;
import MoonBurn.GoL.viewmodel.EditorViewModel;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;

public class Shell extends VBox
{
    private EditorViewModel editorViewModel;

    public Shell(EditorViewModel editorViewModel, BoardCanvasView boardCanvas, Toolbar toolbar)
    {
        this.editorViewModel = editorViewModel;

        this.setOnKeyPressed(this::onKeyPressed);


        getChildren().addAll(toolbar,boardCanvas);
    }

    /**
     * Handles keyboard key press event functionalities.
     */
    private void onKeyPressed(KeyEvent keyEvent)
    {
        KeyCode key = keyEvent.getCode();
        switch (key)
        {
            case D:
                editorViewModel.setDrawMode(CellState.ALIVE);
                System.out.println("Draw mode set to ALIVE");
            break;

            case E:
                editorViewModel.setDrawMode(CellState.DEAD);
                System.out.println("Draw mode set to ERASE");
            break;
        }
    }

}
