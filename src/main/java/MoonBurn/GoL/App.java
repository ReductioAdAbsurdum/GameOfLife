package MoonBurn.GoL;

import MoonBurn.GoL.model.Simulation;
import MoonBurn.GoL.model.board.FiniteBoard;
import MoonBurn.GoL.model.board.IBoard;
import MoonBurn.GoL.model.enums.ApplicationState;
import MoonBurn.GoL.model.rules.ConwayRules;
import MoonBurn.GoL.util.event.classes.ApplicationStateEvent;
import MoonBurn.GoL.util.event.EventBus;
import MoonBurn.GoL.util.event.classes.BoardPressEvent;
import MoonBurn.GoL.util.event.classes.DrawModeEvent;
import MoonBurn.GoL.util.event.classes.SimulatorEvent;
import MoonBurn.GoL.logic.ApplicationStateManager;
import MoonBurn.GoL.logic.Simulator;
import MoonBurn.GoL.view.BoardView;
import MoonBurn.GoL.viewmodel.BoardVM;
import MoonBurn.GoL.logic.Editor;
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
        EventBus eventBus = new EventBus();

        IBoard board = new FiniteBoard(40,20);
        Simulation simulation =new Simulation(board,new ConwayRules());

        ApplicationStateManager applicationStateManager = new ApplicationStateManager(ApplicationState.EDITING);
        BoardVM boardVM = new BoardVM(board);
        Editor editor = new Editor(boardVM, applicationStateManager);
        Simulator simulator = new Simulator(boardVM, simulation);

        eventBus.addMapping(SimulatorEvent.class, simulator::handleSimulatorEvent);
        eventBus.addMapping(ApplicationStateEvent.class, applicationStateManager::handleApplicationStateEvent);
        eventBus.addMapping(DrawModeEvent.class, editor::handleDrawModeEvent);
        eventBus.addMapping(BoardPressEvent.class, editor::onBoardPressed);

        BoardView boardView = new BoardView(800,400, boardVM, eventBus);
        eventBus.addMapping(DrawModeEvent.class, boardView::handleDrawModeEvent);
        Toolbar toolbar = new Toolbar(eventBus);
        Shell shell = new Shell(eventBus, boardView, toolbar);

        Scene scene = new Scene(shell);
        stage.setScene(scene);
        stage.show();

        boardVM.getBoardProp().notifyOfExternalChange();
    }

    public static void main(String[] args)
    {
        launch();
    }

}