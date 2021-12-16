package MoonBurn.GoL;

import MoonBurn.GoL.logic.RuleApplier;
import MoonBurn.GoL.util.Wrapper;
import MoonBurn.GoL.model.board.FiniteBoard;
import MoonBurn.GoL.model.board.IBoard;
import MoonBurn.GoL.model.enums.ApplicationState;
import MoonBurn.GoL.model.rules.ConwayRules;
import MoonBurn.GoL.util.event.classes.*;
import MoonBurn.GoL.util.event.EventBus;
import MoonBurn.GoL.logic.ApplicationStateManager;
import MoonBurn.GoL.logic.Simulator;
import MoonBurn.GoL.view.BoardView;
import MoonBurn.GoL.viewmodel.BoardVM;
import MoonBurn.GoL.logic.Editor;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
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
        Wrapper<IBoard> wrappedBoard = new Wrapper<>(board);
        BoardVM boardVM = new BoardVM(wrappedBoard);

        RuleApplier ruleApplier =new RuleApplier(wrappedBoard,new ConwayRules());
        Simulator simulator = new Simulator(boardVM, ruleApplier);
        eventBus.addMapping(SimulatorEvent.class, simulator::handleSimulatorEvent);

        ApplicationStateManager applicationStateManager = new ApplicationStateManager(ApplicationState.EDITING);
        eventBus.addMapping(ApplicationStateEvent.class, applicationStateManager::handleApplicationStateEvent);

        Editor editor = new Editor(boardVM, applicationStateManager);
        eventBus.addMapping(DrawModeEvent.class, editor::handleDrawModeEvent);
        eventBus.addMapping(BoardPressEvent.class, editor::onBoardPressed);
        eventBus.addMapping(PatternEvent.class, editor::onPatternSelected);

        BoardView boardView = new BoardView(800,400, boardVM, eventBus);
        eventBus.addMapping(DrawModeEvent.class, boardView::onDrawModeEvent);
        Toolbar toolbar = new Toolbar(eventBus);
        Shell shell = new Shell(eventBus, boardView, toolbar);

        Scene scene = new Scene(shell);
        stage.setScene(scene);
        stage.show();

        boardVM.getWrappedBoardProp().notifyOfExternalChange();
    }

    public static void main(String[] args)
    {
        launch();
    }

}