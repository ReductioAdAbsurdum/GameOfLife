package MoonBurn.GoL;

import MoonBurn.GoL.model.board.FiniteBoard;
import MoonBurn.GoL.model.board.IBoard;
import MoonBurn.GoL.model.enums.ApplicationState;
import MoonBurn.GoL.viewmodel.ApplicationViewModel;
import MoonBurn.GoL.viewmodel.BoardView;
import MoonBurn.GoL.viewmodel.BoardViewModel;
import MoonBurn.GoL.viewmodel.EditorViewModel;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;

public class App extends Application
{

    @Override
    public void start(Stage stage)
    {
        IBoard board = new FiniteBoard(20,20);

        ApplicationViewModel avm = new ApplicationViewModel(ApplicationState.EDITING);
        BoardViewModel bvm = new BoardViewModel();
        bvm.setBoard(board);
        EditorViewModel evm = new EditorViewModel(bvm,avm);
        BoardView boardCanvas = new BoardView(800,800,evm,bvm);

        Shell shell = new Shell(800, 800, board, avm, bvm, evm, boardCanvas);
        Scene scene = new Scene(shell);
        stage.setScene(scene);
        stage.show();

        bvm.setBoard(board);
    }

    public static void main(String[] args)
    {
        launch();
    }

}