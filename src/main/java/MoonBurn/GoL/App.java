package MoonBurn.GoL;

import MoonBurn.GoL.model.Simulation;
import MoonBurn.GoL.model.board.FiniteBoard;
import MoonBurn.GoL.model.board.IBoard;
import MoonBurn.GoL.model.enums.ApplicationState;
import MoonBurn.GoL.model.rules.ConwayRules;
import MoonBurn.GoL.viewmodel.ApplicationViewModel;
import MoonBurn.GoL.viewmodel.SimulatorViewModel;
import MoonBurn.GoL.view.BoardCanvasView;
import MoonBurn.GoL.viewmodel.BoardViewModel;
import MoonBurn.GoL.viewmodel.EditorViewModel;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import MoonBurn.GoL.view.Shell;
import MoonBurn.GoL.view.Toolbar;

public class App extends Application
{

    @Override
    public void start(Stage stage)
    {
        IBoard board = new FiniteBoard(20,20);
        Simulation simulation =new Simulation(board,new ConwayRules());

        ApplicationViewModel avm = new ApplicationViewModel(ApplicationState.EDITING);
        BoardViewModel bvm = new BoardViewModel();
        bvm.setBoard(board);
        EditorViewModel evm = new EditorViewModel(bvm,avm);
        SimulatorViewModel svm = new SimulatorViewModel(bvm, simulation);


        BoardCanvasView boardCanvas = new BoardCanvasView(800,800,evm,bvm);
        Toolbar toolbar = new Toolbar(avm, bvm, evm, svm);

        Shell shell = new Shell(evm, boardCanvas, toolbar);
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