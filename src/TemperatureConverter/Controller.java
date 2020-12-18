package TemperatureConverter;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private int textFieldInFocus = 1;
    final private String FAHRENHEIT = "Fahrenheit";
    final private String CELSIUS = "Celsius";
    @FXML
    private TextField textField1;
    @FXML
    private TextField textField2; // Right text field
    @FXML
    private Text formulaUsed;

    @FXML
    private ComboBox<String> comboBox;
    @FXML
    private ComboBox<String> comboBox2;

    private double fromCtoF(String value) {
        DecimalFormat numberFormat = new DecimalFormat("#.00");
        return Double.parseDouble(numberFormat.format(Double.parseDouble(value) * 9/5 + 32));
    }
    private double fromFtoC(String value) {
        DecimalFormat numberFormat = new DecimalFormat("#.00");
        return Double.parseDouble(numberFormat.format((Double.parseDouble(value) - 32) * 5/9));
    }

    private void setFormulaText(String leftNumber, String rightNumber, String formatUnit){
        if(formatUnit == CELSIUS) {
            formulaUsed.setText("(" + leftNumber + "°F − 32) × 5/9 = " + rightNumber + "°C");
        } else {
            formulaUsed.setText("(" + leftNumber + "°C × 9/5) + 32 = " + rightNumber + "°F");
        }
    }


    @FXML
    public void updateTextField(){
        String unit = comboBox.getValue(); // Reading the comboBox value
        String number1 = textField1.getText(); // Reading the textField1 value
        String number2 = textField2.getText(); // Reading the textField2 value
        if (textFieldInFocus == 1 ) {
            if(unit.equals(FAHRENHEIT)) {
                double calculatedValue = fromFtoC(number1);
                textField2.setText(String.valueOf(calculatedValue));
                setFormulaText(number1, String.valueOf(calculatedValue), CELSIUS);
            } else {
                double calculatedValue = fromCtoF(number1);
                textField2.setText(String.valueOf(calculatedValue));
                setFormulaText(number1, String.valueOf(calculatedValue), FAHRENHEIT);
            }
        } else {
            if(unit.equals(FAHRENHEIT)) {
                double calculatedValue = fromCtoF(number2);
                textField1.setText(String.valueOf(calculatedValue));
                setFormulaText(String.valueOf(calculatedValue), number2, CELSIUS);
            } else {

                double calculatedValue = fromFtoC(number2);
                textField1.setText(String.valueOf(calculatedValue));
                setFormulaText(String.valueOf(calculatedValue), number2, FAHRENHEIT);
            }
        }


    }

    private void flipTextFields() {
        String originalText1 = textField1.getText();
        textField1.setText(textField2.getText());
        textField2.setText(originalText1);
    }

    @FXML
    public void updateComboBox() {
        String unit1 = comboBox.getValue();
        String leftText = textField1.getText();
        String rightText = textField2.getText();
        if(!unit1.equals(FAHRENHEIT)) {
            setFormulaText(rightText, leftText, FAHRENHEIT);
            comboBox2.setValue(FAHRENHEIT);

        } else {
            setFormulaText(rightText, leftText, CELSIUS);
            comboBox2.setValue(CELSIUS);
        }
        flipTextFields();
    }

    @FXML
    public void updateComboBox2(){
        String unit2 = comboBox2.getValue();
        String leftText = textField1.getText();
        String rightText = textField2.getText();
        if(!unit2.equals(FAHRENHEIT)) {
            comboBox.setValue(FAHRENHEIT);
            setFormulaText(rightText, leftText, CELSIUS);
        } else {
            comboBox.setValue(CELSIUS);
            setFormulaText(rightText, leftText, FAHRENHEIT);
        }
        // Have no idea why i need these next 3 lines for the flipTextField to work???
        String ogText2 = textField2.getText();
        textField2.setText(textField1.getText());
        textField1.setText(ogText2);
        flipTextFields();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        textField1.focusedProperty().addListener((ov, oldV, newV) -> {
            if (newV) {
                textFieldInFocus = 1;
            }
        });
        textField2.focusedProperty().addListener((ov, oldV, newV) -> {
            if (newV) {
                textFieldInFocus = 2;
            }
        });
    }
}
