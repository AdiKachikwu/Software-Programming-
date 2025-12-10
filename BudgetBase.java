// base code for student budget assessment
// Students do not need to use this code in their assessment, fine to junk it and do something different!

// user can enter in wages and loans and calculate total income
//
// To see GUI, run with java and select Box Url from Codio top line menu
//
// Layout - Uses GridBag layout in a straightforward way, every component has a (column, row) position in the UI grid
// Not the prettiest layout, but relatively straightforward
// Students who use IntelliJ or Eclipse may want to use the UI designers in these IDEs , instead of GridBagLayout

// Ehud Reiter Aug 204

package Budget;

// Swing imports
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.Stack;

// class definition
public class BudgetBase extends JPanel {    // based on Swing JPanel

    // high level UI stuff
    JFrame topLevelFrame;  // top-level JFrame
    GridBagConstraints layoutConstraints = new GridBagConstraints(); // used to control layout

    // widgets which may have listeners and/or values
    private JButton calculateButton;   // Calculate button
    private JButton exitButton;        // Exit button
    private JButton undoButton; //Undo Button

    private JTextField WagesField;     // Wages text field
    private JTextField LoansField;     // Loans text field
    private JTextField totalIncomeField; // Total Income field
    private JTextField OtherFinField; // Other Finance Field
    private JTextField FoodExpenseField; //Food Expense Field
    private JTextField RentExpenseField; //Rent Expense Field
    private JTextField OtherExpenseField; //Other Expense Field
    private JTextField Surplus_Deficit_Field; //Surplus/Deficit Field

    private JComboBox<String> wage_Frequency;
    private JComboBox<String> loan_Frequency;
    private JComboBox<String> otherFinance_Frequency;
    private JComboBox<String> food_Frequency;
    private JComboBox<String> rent_Frequency;
    private JComboBox<String> otherExpenses_Frequency;


    String[] dropdown_options = new String[]{"week", "month", "year"};
    Stack<State_Object> history = new Stack<>();



    // constructor - create UI  (dont need to change this)
    public BudgetBase(JFrame frame) {
        topLevelFrame = frame; // keep track of top-level frame
        setLayout(new GridBagLayout());  // use GridBag layout
        initComponents();  // initalise components
    }

    // initialise componenents
    // Note that this method is quite long.  Can be shortened by putting Action Listener stuff in a separate method
    // will be generated automatically by IntelliJ, Eclipse, etc
    private void initComponents() { 

        // Top row (0) - "INCOME" label
        JLabel incomeLabel = new JLabel("INCOME");
        addComponent(incomeLabel, 0, 0);

        // Row 1 - Wages label followed by wages textbox
        JLabel wagesLabel = new JLabel("Wages");
        addComponent(wagesLabel, 1, 0);

        // set up text field for entering wages
        // Could create method to do below (since this is done several times)
        WagesField = new JTextField("", 10);   // blank initially, with 10 columns
        WagesField.setHorizontalAlignment(JTextField.RIGHT) ;    // number is at right end of field
        addComponent(WagesField, 1, 1);

        //Set up a frequency for the wages
        wage_Frequency = addDropdown(dropdown_options);
        addComponent(wage_Frequency, 1 , 2);


        // Row 2 - Loans label followed by loans textbox
        JLabel loansLabel = new JLabel("Loans");
        addComponent(loansLabel, 2, 0);

        // set up text box for entering loans
        LoansField = new JTextField("", 10);   // blank initially, with 10 columns
        LoansField.setHorizontalAlignment(JTextField.RIGHT) ;    // number is at right end of field
        addComponent(LoansField, 2, 1);

        //Set up a frequency for the loan
        loan_Frequency = addDropdown(dropdown_options);
        addComponent(loan_Frequency, 2 , 2);


        // Row 3 - Total Other label followed by other income field
        JLabel OtherLabel = new JLabel("Other Finance");
        addComponent(OtherLabel, 3, 0);

        //set up textfield for entering other finances
        OtherFinField = new JTextField("", 10);
        OtherFinField.setHorizontalAlignment(JTextField.RIGHT);
        addComponent(OtherFinField, 3, 1);

        //Set up a frequency for the other finance options
        otherFinance_Frequency = addDropdown(dropdown_options);
        addComponent(otherFinance_Frequency, 3 , 2);

        // Row 4 - Food Expense label followed by food expense textfield
        JLabel FoodExpense = new JLabel("Food Expenses");
        addComponent(FoodExpense, 4, 0);

        //set up textfield for entering other finances
        FoodExpenseField = new JTextField("", 10);
        FoodExpenseField.setHorizontalAlignment(JTextField.RIGHT);
        addComponent(FoodExpenseField, 4, 1);

        //Set up a frequency for the food expenses
        food_Frequency = addDropdown(dropdown_options);
        addComponent(food_Frequency, 4 , 2);

        // Row 5 - Rent Expense label followed by rent expense textfield
        JLabel RentExpense = new JLabel("Rent Expense");
        addComponent(RentExpense, 5, 0);

        //set up textfield for entering other finances
        RentExpenseField = new JTextField("", 10);
        RentExpenseField.setHorizontalAlignment(JTextField.RIGHT);
        addComponent(RentExpenseField, 5, 1);

        //Set up a frequency for the rent expenses
        rent_Frequency = addDropdown(dropdown_options);
        addComponent(rent_Frequency, 5 , 2);

        // Row 6 - Other Expense label followed by Other expense textfield
        JLabel OtherExpense = new JLabel("Other Expense");
        addComponent(OtherExpense, 6, 0);

        //set up textfield for entering other finances
        OtherExpenseField = new JTextField("", 10);
        OtherExpenseField.setHorizontalAlignment(JTextField.RIGHT);
        addComponent(OtherExpenseField, 6, 1);

        //Set up a frequency for the other expenses
        otherExpenses_Frequency = addDropdown(dropdown_options);
        addComponent(otherExpenses_Frequency, 6 , 2);

        // Row 8 - Total Income label followed by total income field
        JLabel totalIncomeLabel = new JLabel("Total Income");
        addComponent(totalIncomeLabel, 8, 0);

        // set up text box for displaying total income.  Users cam view, but cannot directly edit it
        totalIncomeField = new JTextField("0", 10);   // 0 initially, with 10 columns
        totalIncomeField.setHorizontalAlignment(JTextField.RIGHT) ;    // number is at right end of field
        totalIncomeField.setEditable(false);    // user cannot directly edit this field (ie, it is read-only)
        addComponent(totalIncomeField, 8, 1);

        //Row 9 - Surplus/Deficit followed by a read/only text box
        JLabel Surplus_Deficit = new JLabel("Surplus/Deficit");
        addComponent(Surplus_Deficit, 9, 0);

        //Setting up text box for displaying Surplus/Deficit for read only
        Surplus_Deficit_Field = new JTextField("0",10);
        Surplus_Deficit_Field.setHorizontalAlignment(JTextField.RIGHT);
        Surplus_Deficit_Field.setEditable(false);
        addComponent(Surplus_Deficit_Field, 9, 1);

        // Row 7 - Calculate Button
        calculateButton = new JButton("Calculate");
        addComponent(calculateButton, 7, 0);

        // Row 9 - Exit Button
        exitButton = new JButton("Exit");
        addComponent(exitButton, 10, 2);

        // Row 9 - Undo Button
        undoButton = new JButton("Undo");
        addComponent(undoButton, 10, 0);

        // set up  listeners (in a separate method)
        initListeners();
    }

    // set up listeners
    // initially just for buttons, can add listeners for text fields
    private void initListeners() {



        //For when a number is entered or changed
        allkeys(WagesField);
        allkeys(LoansField);
        allkeys(OtherFinField);
        allkeys(FoodExpenseField);
        allkeys(RentExpenseField);
        allkeys(OtherExpenseField);

        //For when focus is lost for a text field
        refocus(WagesField);
        refocus(LoansField);
        refocus(OtherFinField);
        refocus(FoodExpenseField);
        refocus(RentExpenseField);
        refocus(OtherExpenseField);

        //For when the undo button is pressed
        //For the zero value
        State_Object history_State = new State_Object();
        history.push(history_method(history_State));

        //The history of these text fields
        history(WagesField);
        history(LoansField);
        history(OtherFinField);
        history(FoodExpenseField);
        history(RentExpenseField);
        history(OtherExpenseField);

        //The history of frequency
        history_Frequency(wage_Frequency);
        history_Frequency(loan_Frequency);
        history_Frequency(otherFinance_Frequency);
        history_Frequency(food_Frequency);
        history_Frequency(rent_Frequency);
        history_Frequency(otherExpenses_Frequency);












        // exitButton - exit program when pressed
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        //UndoButton
        undoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setUndoButton();
            }
        });

        // calculateButton - call calculateTotalIncome() when pressed
        calculateButton.addActionListener(new java.awt.event.ActionListener()  {
            public void actionPerformed(ActionEvent e) {
                calculateTotalIncome(); calculateSurplus_Deficit();
                State_Object history_State = new State_Object();
                history.push(history_method(history_State));
            }
        });
                // WagesField - call calculateTotalIncome() when pressed
        WagesField.addActionListener(new java.awt.event.ActionListener()  {
            public void actionPerformed(ActionEvent e) {
                calculateTotalIncome();
            }
        });

    }



    // add a component at specified row and column in UI.  (0,0) is top-left corner
    private void addComponent(Component component, int gridrow, int gridcol) {
        layoutConstraints.fill = GridBagConstraints.HORIZONTAL;   // always use horixontsl filll
        layoutConstraints.gridx = gridcol;
        layoutConstraints.gridy = gridrow;
        add(component, layoutConstraints);

    }

    //Add dropdown method
    public static JComboBox<String> addDropdown(String[] items){
        return new JComboBox<>(items);
    }

    private double getMultiplier(double value, JComboBox<String> combo_box) {
        String frequency;
        frequency = (String) combo_box.getSelectedItem();

        return switch (frequency) {
            case "week" -> value * 52;
            case "month" -> value * 12;
            case "year" -> value;
            default -> value;
        };

    }
    //General method for saving to the the stack
    public State_Object history_method(State_Object history_State){
        history_State.Wage_save = String.valueOf(getTextFieldValue(WagesField));
        history_State.Loan_save = String.valueOf(getTextFieldValue(LoansField));
        history_State.OtherFin_save = String.valueOf(getTextFieldValue(OtherFinField));
        history_State.Food_save = String.valueOf(getTextFieldValue(FoodExpenseField));
        history_State.Rent_save = String.valueOf(getTextFieldValue(RentExpenseField));
        history_State.OtherExpense_save = String.valueOf(getTextFieldValue(OtherExpenseField));

        history_State.wageFrequency_save = wage_Frequency.getSelectedItem();
        history_State.loanFrequency_save = loan_Frequency.getSelectedItem();
        history_State.otherFin_Frequency = otherFinance_Frequency.getSelectedItem();
        history_State.Food_Frequency_save = food_Frequency.getSelectedItem();
        history_State.Rent_Frequency_save = rent_Frequency.getSelectedItem();
        history_State.Other_Expense_Frequency_save = otherExpenses_Frequency.getSelectedItem();
        return history_State;
    }

    //History of uses for JTextfields
    public void history( JTextField field){
        field.addFocusListener(new java.awt.event.FocusListener(){

            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                State_Object history_State = new State_Object();
                history.push(history_method(history_State));
            }
        });

        field.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                State_Object history_State = new State_Object();
                history.push(history_method(history_State));
            }
        });

    }

    // History of Dropdown Method
    public void history_Frequency(JComboBox<String> combo_box){
        combo_box.addActionListener(new java.awt.event.ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                State_Object history_State = new State_Object();
                history.push(history_method(history_State));
            }
        });
    }





    public class State_Object {

        String Wage_save;
        String Loan_save;
        String OtherFin_save;


        String Food_save;
        String Rent_save;
        String OtherExpense_save;


        Object wageFrequency_save;
        Object loanFrequency_save;
        Object otherFin_Frequency;
        Object Food_Frequency_save;
        Object Rent_Frequency_save;
        Object Other_Expense_Frequency_save;


    }

    //Undo Button Implimentation
    public void setUndoButton(){
        if (!history.isEmpty()) {
            State_Object previous_entry = history.pop();
            WagesField.setText(previous_entry.Wage_save);
            LoansField.setText(previous_entry.Loan_save);
            OtherFinField.setText(previous_entry.OtherFin_save);
            FoodExpenseField.setText(previous_entry.Food_save);
            RentExpenseField.setText(previous_entry.Rent_save);
            OtherExpenseField.setText(previous_entry.OtherExpense_save);

            wage_Frequency.setSelectedItem(previous_entry.wageFrequency_save);
            loan_Frequency.setSelectedItem(previous_entry.loanFrequency_save);
            otherFinance_Frequency.setSelectedItem(previous_entry.otherFin_Frequency);
            food_Frequency.setSelectedItem(previous_entry.Food_Frequency_save);
            rent_Frequency.setSelectedItem(previous_entry.Rent_Frequency_save);
            otherExpenses_Frequency.setSelectedItem(previous_entry.Other_Expense_Frequency_save);
        }
        //A longer version of this which was I was trying to do at first would be Stack_Pair previous_entry = history_stack.peek(); history_stack.pop();previous_entry.getField() etc, took me a bit to understand this though cause my head was stuck in python and I wanted to loop through it, when all vales are being pushed.



    }


    //Method for all focus listeners
    public void refocus(JTextField field){
        field.addFocusListener(new java.awt.event.FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                //Leaving this field empty as the only uses of this will be when focus is lost
            }

            @Override
            public void focusLost(FocusEvent e) {
                calculateTotalIncome(); calculateSurplus_Deficit();
            }
        });
    }
    //Method for all the key listeners
    public void allkeys(JTextField field){
        field.addKeyListener(new java.awt.event.KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                calculateTotalIncome(); calculateSurplus_Deficit();
            }

            @Override
            public void keyPressed(KeyEvent e) {
                calculateTotalIncome(); calculateSurplus_Deficit();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                calculateTotalIncome(); calculateSurplus_Deficit();
            }


        });
    }

    // update totalIncomeField (eg, when Calculate is pressed)
    // use double to hold numbers, so user can type fractional amounts such as 134.50
    public double calculateTotalIncome() {

        // get values from income text fields.  value is NaN if an error occurs
        double wages = getMultiplier(getTextFieldValue(WagesField), wage_Frequency);
        double loans = getMultiplier(getTextFieldValue(LoansField), loan_Frequency);
        double otherFinance = getMultiplier(getTextFieldValue(OtherFinField), otherFinance_Frequency);


        // clear total field and return if any value is NaN (error)
        if (Double.isNaN(wages) || Double.isNaN(loans) || Double.isNaN(otherFinance)) {
            totalIncomeField.setText("");  // clear total income field
            wages = 0.0;
            return wages;   // exit method and do nothing
        }


        // otherwise calculate total income and update text field
        double totalIncome = wages + loans + otherFinance;
        totalIncomeField.setText(String.format("%.2f",totalIncome));  // format with 2 digits after the .
        return totalIncome;
    }

    //Calculate the surplus/Deficit
    public double calculateSurplus_Deficit() {

        // get values from income text fields.  value is NaN if an error occurs
        double wages = getMultiplier(getTextFieldValue(WagesField), wage_Frequency);
        double loans = getMultiplier(getTextFieldValue(LoansField), loan_Frequency);
        double otherFinance = getMultiplier(getTextFieldValue(OtherFinField), otherFinance_Frequency);

        //Then get the values of the expenses.
        double food = getMultiplier(getTextFieldValue(FoodExpenseField), food_Frequency);
        double rent = getMultiplier(getTextFieldValue(RentExpenseField), rent_Frequency);
        double otherExpense = getMultiplier(getTextFieldValue(OtherExpenseField), otherExpenses_Frequency);

        // clear total field and return if any value is NaN (error)
        if (Double.isNaN(wages) || Double.isNaN(loans) || Double.isNaN(otherFinance)) {
            totalIncomeField.setText("");  // clear total income field
            wages = 0.0;
            return wages;   // exit method and do nothing
        }

        // otherwise calculate total income and update text field
        double totalIncome = wages + loans + otherFinance;
        double totalExpenses = food + rent + otherExpense;
        double Surplus_Deficit = totalIncome - totalExpenses;
        Surplus_Deficit_Field.setText(String.format("%.2f",Surplus_Deficit));  // format with 2 digits after the .
        return Surplus_Deficit;
    }

    // return the value if a text field as a double
    // --return 0 if field is blank
    // --return NaN if field is not a number
    private double getTextFieldValue(JTextField field) {

        // get value as String from field
        String fieldString = field.getText();  // get text from text field

        if (fieldString.isBlank()) {   // if text field is blank, return 0
            return 0;
        }

        else {  // if text field is not blank, parse it into a double
            //return Double.parseDouble(fieldString);
            try {
                return Double.parseDouble(fieldString);  // parse field number into a double
             } catch (NumberFormatException ex) {  // catch invalid number exception
                JOptionPane.showMessageDialog(topLevelFrame, "Please enter a valid number");  // show error message
                return Double.NaN;  // return NaN to show that field is not a number
            }
        }
    }




// below is standard code to set up Swing, which students shouldnt need to edit much
    // standard mathod to show UI
    private static void createAndShowGUI() {
 
        //Create and set up the window.
        JFrame frame = new JFrame("Budget Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        //Create and set up the content pane.
        BudgetBase newContentPane = new BudgetBase(frame);
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);
 
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
 
    // standard main class to set up Swing UI
    static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI(); 
            }
        });
    }


}