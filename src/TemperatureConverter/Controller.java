package TemperatureConverter;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

public class Controller implements Initializable, TemperatureConverter {
    private int textFieldInFocus = 1;
    final private String FAHRENHEIT = "Fahrenheit";
    final private String CELSIUS = "Celsius";
    @FXML
    private TextField textField1; // Left textField
    @FXML
    private TextField textField2; // Right textField
    @FXML
    private Text formulaUsed; // Text showing user the formula

    @FXML
    private ComboBox<String> comboBox; // Allows user to select temperature unit
    @FXML
    private ComboBox<String> comboBox2; // Allows user to select temperature unit

    /**
     * Converts temperature from Celsius to Fahrenheit
     * @param value The value to be converted
     * @return The converted value
     */
    public double fromCtoF(String value) {
        DecimalFormat numberFormat = new DecimalFormat("#.00");
        return Double.parseDouble(numberFormat.format(Double.parseDouble(value) * 9/5 + 32));
    }
    /**
     * Converts temperature from Fahrenheit to Celsius
     * @param value The value to be converted
     * @return The converted value
     */
    public double fromFtoC(String value) {
        DecimalFormat numberFormat = new DecimalFormat("#.00");
        return Double.parseDouble(numberFormat.format((Double.parseDouble(value) - 32) * 5/9));
    }

    /**
     * Sets the formula text shown to the user
     * @param leftNumber The value from the left textField
     * @param rightNumber The value from the right textField
     * @param formatUnit Celsius or Fahrenheit
     */
    public void setFormulaText(String leftNumber, String rightNumber, String formatUnit){
        // Will always show formula from left textField to right
        if(formatUnit == CELSIUS) {
            formulaUsed.setText("(" + leftNumber + "°F − 32) × 5/9 = " + rightNumber + "°C");
        } else {
            formulaUsed.setText("(" + leftNumber + "°C × 9/5) + 32 = " + rightNumber + "°F");
        }
    }

    /**
     * A method that updates the textFields
     */
    @FXML
    public void updateTextField(){
        String unit = comboBox.getValue(); // Reading the comboBox value
        String number1 = textField1.getText(); // Reading the textField1 value
        String number2 = textField2.getText(); // Reading the textField2 value
        // updates opposite value based on which textField is highlighted
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

    /**
     * Method that flips text fields when a user changes the temperature in comboBox
     */
    public void flipTextFields() {
        String originalText1 = textField1.getText();
        textField1.setText(textField2.getText());
        textField2.setText(originalText1);
    }

    /**
     * Updates left comboBox
     */
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
    /**
     * Updates right comboBox
     */
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

    /**
     * Sets a listener on textFields to determine when they are focused
     * @param url
     * @param resourceBundle
     */
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
