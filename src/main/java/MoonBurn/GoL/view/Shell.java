package MoonBurn.GoL.view;

import MoonBurn.GoL.model.enums.CellState;
import MoonBurn.GoL.viewmodel.EditorVM;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;

public class Shell extends VBox
{
    private EditorVM editorVM;

    public Shell(EditorVM editorVM, Canvas boardCanvas, Toolbar toolbar)
    {
        this.editorVM = editorVM;

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
                editorVM.getDrawModeProp().setValue(CellState.ALIVE);
                System.out.println("Draw mode setValue to ALIVE");
                break;

            case E:
                editorVM.getDrawModeProp().setValue(CellState.DEAD);
                System.out.println("Draw mode setValue to ERASE");
                break;
        }
    }

}
