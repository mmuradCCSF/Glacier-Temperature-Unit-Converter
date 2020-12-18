package TemperatureConverter;

public interface TemperatureConverter {
    double fromCtoF(String value);
    double fromFtoC(String value);
    void setFormulaText(String leftNumber, String rightNumber, String formatUnit);
    void updateTextField();
    void flipTextFields();
    void updateComboBox();
    void updateComboBox2();
}

