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
import javax.swing.text.DefaultEditorKit;
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

    private JTextField wagesField;     // Wages text field
    private JTextField loansField;     // Loans text field
    private JTextField totalIncomeField; // Total Income field
    private JTextField otherField; // Other Finance Field
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
    Stack<String> history_stack = new Stack<>();


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
        wagesField = new JTextField("", 10);   // blank initially, with 10 columns
        wagesField.setHorizontalAlignment(JTextField.RIGHT) ;    // number is at right end of field
        addComponent(wagesField, 1, 1);

        //Set up a frequency for the wages
        wage_Frequency = addDropdown(dropdown_options);
        addComponent(wage_Frequency, 1 , 2);


        // Row 2 - Loans label followed by loans textbox
        JLabel loansLabel = new JLabel("Loans");
        addComponent(loansLabel, 2, 0);

        // set up text box for entering loans
        loansField = new JTextField("", 10);   // blank initially, with 10 columns
        loansField.setHorizontalAlignment(JTextField.RIGHT) ;    // number is at right end of field
        addComponent(loansField, 2, 1);

        //Set up a frequency for the loan
        loan_Frequency = addDropdown(dropdown_options);
        addComponent(loan_Frequency, 2 , 2);


        // Row 3 - Total Other label followed by other income field
        JLabel OtherLabel = new JLabel("Other Finance");
        addComponent(OtherLabel, 3, 0);

        //set up textfield for entering other finances
        otherField = new JTextField("", 10);
        otherField.setHorizontalAlignment(JTextField.RIGHT);
        addComponent(otherField, 3, 1);

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
        allkeys(wagesField);
        allkeys(loansField);
        allkeys(otherField);
        allkeys(FoodExpenseField);
        allkeys(RentExpenseField);
        allkeys(OtherExpenseField);

        //For when focus is lost for a text field
        refocus(wagesField);
        refocus(loansField);
        refocus(otherField);
        refocus(FoodExpenseField);
        refocus(RentExpenseField);
        refocus(OtherExpenseField);

        //For when the undo button is pressed









        // exitButton - exit program when pressed
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        //UndoButton
        undoButton.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(ActionEvent e) {

            }
        });

        // calculateButton - call calculateTotalIncome() when pressed
        calculateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calculateTotalIncome(); calculateSurplus_Deficit();
            }
        });
                // wagesField - call calculateTotalIncome() when pressed
        wagesField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calculateTotalIncome();
            }
        });

    }

    public class Pair<A,B>{

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
    //History of uses and also the implimentation of the undo button
    public void history( JTextField field){
        field.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                history_stack.push(field, getTextFieldValue(field));
            }

            @Override
            public void keyPressed(KeyEvent e) {
                history_stack.push(String.valueOf(field));
            }

            @Override
            public void keyReleased(KeyEvent e) {
                history_stack.push(String.valueOf(field));
            }
        });
    }

    //Undo Button Implimentation
    public void setUndoButton(){
        //First - Check remove the first element
        history_stack.pop();
        //Second find the JTEXTFIELD that corresponds to the new one and change it
        history_stack.substring(0,)

    }

    //Method for all focus listeners
    public void refocus(JTextField field){
        field.addFocusListener(new FocusListener() {
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
        double wages = getMultiplier(getTextFieldValue(wagesField), wage_Frequency);
        double loans = getMultiplier(getTextFieldValue(loansField), loan_Frequency);
        double otherFinance = getMultiplier(getTextFieldValue(otherField), otherFinance_Frequency);


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
        double wages = getMultiplier(getTextFieldValue(wagesField), wage_Frequency);
        double loans = getMultiplier(getTextFieldValue(loansField), loan_Frequency);
        double otherFinance = getMultiplier(getTextFieldValue(otherField), otherFinance_Frequency);

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
             } catch (java.lang.NumberFormatException ex) {  // catch invalid number exception
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
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI(); 
            }
        });
    }


}