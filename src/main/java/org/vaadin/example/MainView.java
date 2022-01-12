package org.vaadin.example;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.progressbar.ProgressBar;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.component.notification.Notification;
import java.util.ArrayList;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.textfield.TextAreaVariant;

@PageTitle("Java Tools")
@Route("")

public class MainView extends VerticalLayout {

    public MainView() {

        VerticalLayout manual = new VerticalLayout();

        VerticalLayout fieldList = new VerticalLayout();
        ArrayList<TextField> names = new ArrayList<TextField>();
        ArrayList<TextField> types = new ArrayList<TextField>();
        TextField typeField = new TextField();
        typeField.setPlaceholder("Type");
        TextField nameField = new TextField();
        nameField.setPlaceholder("Name");

        Button addButton = new Button("Add");
        addButton.addClickListener(click -> {
            if (nameField.getValue().equals("") || typeField.getValue().equals("")) {
                if (nameField.getValue().equals("") ) {
                    nameField.getStyle().set("color", "red");
                    
                }
                if (typeField.getValue().equals("") ) {
                    typeField.getStyle().set("color", "red");
                }
                
            } 
            else {

                TextField ntext = new TextField();
                ntext.setValue(nameField.getValue());
                ntext.setHeight("100");
                names.add(ntext);
                TextField ttext = new TextField();
                ttext.setValue(typeField.getValue());
                types.add(ttext);
                Button removeButton = new Button("", VaadinIcon.CLOSE.create());
                removeButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY, ButtonVariant.LUMO_ERROR);
                HorizontalLayout row = new HorizontalLayout(ttext, ntext, removeButton);
                removeButton.addClickListener(click2 -> {
                    fieldList.remove(row);
                });
                fieldList.add(row);
                nameField.setValue("");
                typeField.setValue("");
            }
        });
        addButton.addClickShortcut(Key.ENTER);
        HorizontalLayout manualInput = new HorizontalLayout(typeField, nameField, addButton);
        fieldList.add(manualInput);
        manual.add(new H2("Manual Input"), fieldList);

        // ----------------------------------------------

        VerticalLayout construct = new VerticalLayout();

        TextField cField = new TextField();
        Button generate = new Button("Generate");

        ProgressBar pBar = new ProgressBar();
        pBar.setIndeterminate(true);

        Div pBarLabel = new Div();
        pBarLabel.setText("Generating report...");
        pBar.setVisible(false);
        pBarLabel.setVisible(false);

        construct.add(new H2("Copy from constructor"), cField, generate, pBarLabel, pBar);

        // ----------------------------------------------

        VerticalLayout result = new VerticalLayout();
        TextArea textArea = new TextArea("Result");

        result.add(textArea);
        result.setVisible(false);

        generate.addClickListener(click -> {
            // pBar.setVisible(true);
            // pBarLabel.setVisible(true);
            result.setVisible(true);
        });

        add(
                new H1("Java Scanner Generator"),
                new HorizontalLayout(
                        manual,
                        construct),
                result

        );
    }
}