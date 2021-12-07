package MoonBurn.GoL;

import MoonBurn.GoL.model.Simulation;
import MoonBurn.GoL.model.board.FiniteBoard;
import MoonBurn.GoL.model.board.IBoard;
import MoonBurn.GoL.model.enums.ApplicationState;
import MoonBurn.GoL.model.rules.ConwayRules;
import MoonBurn.GoL.viewmodel.ApplicationVM;
import MoonBurn.GoL.viewmodel.SimulatorVM;
import MoonBurn.GoL.view.Canvas;
import MoonBurn.GoL.viewmodel.BoardVM;
import MoonBurn.GoL.viewmodel.EditorVM;
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
        IBoard board = new FiniteBoard(40,20);
        Simulation simulation =new Simulation(board,new ConwayRules());

        ApplicationVM avm = new ApplicationVM(ApplicationState.EDITING);
        BoardVM bvm = new BoardVM(board);

        EditorVM evm = new EditorVM(bvm,avm);
        SimulatorVM svm = new SimulatorVM(bvm, simulation);


        Canvas boardCanvas = new Canvas(800,400,evm,bvm);
        Toolbar toolbar = new Toolbar(avm, bvm, evm, svm);

        Shell shell = new Shell(evm, boardCanvas, toolbar);
        Scene scene = new Scene(shell);
        stage.setScene(scene);
        stage.show();

        bvm.getBoardProp().notifyOfExternalChange();
    }

    public static void main(String[] args)
    {
        launch();
    }

}