package MoonBurn.GoL.view.patternSelect;

import MoonBurn.GoL.util.event.EventBus;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PatternSelectorView extends HBox
{
    private EventBus eventBus;

    private ListView patternList;

    private CheckBox stillLifeCB;
    private CheckBox oscillatorsCB;
    private CheckBox spaceshipsCB;
    private CheckBox specialLifeCB;


    public PatternSelectorView(EventBus eventBus)
    {
        super();
        this.eventBus = eventBus;

        this.setPadding(new Insets(0,0,0,20));
        this.setSpacing(10);

        VBox checkBoxes1 = new VBox();
        checkBoxes1.setSpacing(2);
        stillLifeCB = new CheckBox("Still");
        stillLifeCB.setOnMouseClicked(this::ClickedStillLifeCB);
        oscillatorsCB = new CheckBox("Oscillators");
        oscillatorsCB.setOnMouseClicked(this::ClickedOscillatorCB);
        checkBoxes1.getChildren().addAll(stillLifeCB, oscillatorsCB);

        VBox checkBoxes2 = new VBox();
        checkBoxes2.setSpacing(2);
        spaceshipsCB = new CheckBox("Spaceship");
        spaceshipsCB.setOnMouseClicked(this::ClickedSpaceshipCB);
        specialLifeCB = new CheckBox("Special");
        specialLifeCB.setOnMouseClicked(this::ClickedSpecialLifeCB);
        checkBoxes2.getChildren().addAll(spaceshipsCB, specialLifeCB);

        patternList = new ListView();
        patternList.setOnHiding(this::patterListAction);
        this.getChildren().addAll(checkBoxes1, checkBoxes2, patternList );
    }

    private void patterListAction(Event e)
    {
        System.out.println(patternList.getValue());
    }

    private void ClickedStillLifeCB(MouseEvent m)
    {
        if(stillLifeCB.isSelected())
        {
            patternList.addStillNamesToView();
            System.out.println("Still life add to the View");
        }
        else
        {
            patternList.removeStillNamesFromView();
            System.out.println("Still life remove from the View");
        }

    }
    private void ClickedOscillatorCB(MouseEvent m)
    {
        if(oscillatorsCB.isSelected())
        {
            patternList.addOscillatorNamesToView();
            System.out.println("Oscillators add to the View");
        }
        else
        {
            patternList.removeOscillatorNamesFromView();
            System.out.println("Oscillators remove from the View");
        }
    }
    private void ClickedSpaceshipCB(MouseEvent m)
    {
        System.out.println("Spaceship");
    }

    private void ClickedSpecialLifeCB(MouseEvent m)
    {
        System.out.println("Special");
    }

}
