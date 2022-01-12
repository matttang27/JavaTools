package org.vaadin.example;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.progressbar.ProgressBar;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("")
public class MainView extends VerticalLayout {

    public MainView() {

        VerticalLayout manual = new VerticalLayout();
        VerticalLayout construct = new VerticalLayout();

        VerticalLayout fieldList = new VerticalLayout();
        TextField nameField = new TextField();
        nameField.setPlaceholder("Name");
        TextField typeField = new TextField();
        typeField.setPlaceholder("Type");
        Button addButton = new Button("Add");
        addButton.addClickListener(click -> {
            
            Checkbox checkbox = new Checkbox(nameField.getValue());
            fieldList.add(checkbox);
            nameField.setValue("");
        });
        addButton.addClickShortcut(Key.ENTER);
        HorizontalLayout manualInput = new HorizontalLayout(nameField,typeField,addButton);
        manual.add(new H2("Manual Input"),fieldList,manualInput);


        TextField cField = new TextField();
        Button generate = new Button("Generate");

        ProgressBar pBar = new ProgressBar();
        pBar.setIndeterminate(true);

        Div pBarLabel = new Div();
        pBarLabel.setText("Generating report...");
        pBar.setVisible(false);
        pBarLabel.setVisible(false);
        
        generate.addClickListener(click -> {
            pBar.setVisible(true);
            pBarLabel.setVisible(true);
        });
        
        construct.add(new H2("Copy from constructor"),cField,generate,pBarLabel, pBar);
        add(
            
            new H1("Java Scanner Generator"),
            new HorizontalLayout(
                manual,
                construct
            )
            
        );
    }
}