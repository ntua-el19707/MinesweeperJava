import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import java.util.ArrayList;

/**
 * class formControlCreate
 * 
 * @author Victoras Giannaki
 */
public class formControlCreate {
    @FXML
    private TextField filename;
    formCreate listener;
    @FXML
    private ChoiceBox senario;
    @FXML
    private RadioButton SuperBomb;
    @FXML
    private ChoiceBox Bombs;
    @FXML
    private Button confirm;
    @FXML
    private ChoiceBox Time;

    private void ch(int sb, int lb, int st, int lt) {
        confirm.setDisable(true);
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = sb; i < lb; i++) {
            arrayList.add(i);
        }
        ArrayList<Integer> arrayList2 = new ArrayList<>();
        for (int i = st; i < lt; i++) {
            arrayList2.add(i);
        }
        ObservableList<Integer> options = FXCollections.observableArrayList(arrayList);
        ObservableList<Integer> times = FXCollections.observableArrayList(arrayList2);
        Bombs.setValue(null);
        Bombs.setItems(options);
        Bombs.setDisable(false);
        Time.setValue(null);
        Time.setItems(times);
        Time.setDisable(false);
        Bombs.setOnAction(e -> {

            en();
        });
        Time.setOnAction(e -> {
            en();
        });
    }

    int type, sb;

    /**
     * method send() => values back to class
     */
    public void send() {
        type = 0;
        sb = 0;
        if (senario.getValue() == "9x9") {
            type = 0;
        } else if (senario.getValue() == "16x16") {
            type = 1;
            if (SuperBomb.isSelected()) {
                sb = 1;
            }
        }
        Platform.runLater(() -> {
            listener.createsenartio(type, (int) Bombs.getValue(), (int) Time.getValue(), sb, filename.getText());
        });
    }

    private void en() {
        if (Time.getValue() != null && Bombs.getValue() != null && filename.getText() != "") {
            confirm.setDisable(false);
        }
    }

    /**
     * method initForm
     * 
     * @param listener formCreate the class that call
     */
    public void initForm(formCreate listener) {
        this.listener = listener;
        confirm.setDisable(true);
        Bombs.setDisable(true);
        Time.setDisable(true);
        SuperBomb.setDisable(true);
        SuperBomb.setSelected(false);
        filename.setPromptText("Please enter a  name for your senario");
        filename.setOnKeyPressed(e -> {
            en();
        });

        ObservableList<String> options = FXCollections.observableArrayList("9x9", "16x16");
        senario.setItems(options);
        senario.setOnAction(e -> {
            if (senario.getValue() == "9x9") {
                // ini();
                SuperBomb.setDisable(true);
                SuperBomb.setSelected(false);
                ch(9, 12, 120, 181);

            } else if (senario.getValue() == "16x16") {
                // ini();
                SuperBomb.setDisable(false);
                ch(35, 46, 240, 361);
            }
        });
    }
}
