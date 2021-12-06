package MoonBurn.GoL;

import MoonBurn.GoL.model.board.FiniteBoard;
import MoonBurn.GoL.model.board.IBoard;
import MoonBurn.GoL.model.enums.ApplicationState;
import MoonBurn.GoL.viewmodel.ApplicationViewModel;
import MoonBurn.GoL.viewmodel.BoardViewModel;
import MoonBurn.GoL.viewmodel.EditorViewModel;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application
{

    @Override
    public void start(Stage stage)
    {
        IBoard board = new FiniteBoard(20,20);

        ApplicationViewModel avm = new ApplicationViewModel(ApplicationState.EDITING);
        BoardViewModel bvm = new BoardViewModel();
        EditorViewModel evm = new EditorViewModel(bvm,avm);


        MainView mainView = new MainView(800, 800, board, avm, bvm, evm);
        Scene scene = new Scene(mainView);
        stage.setScene(scene);
        stage.show();

        bvm.setBoard(board);
    }

    public static void main(String[] args)
    {
        launch();
    }

}