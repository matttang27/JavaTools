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

import org.vaadin.Generate;

import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.component.notification.Notification;
import java.util.ArrayList;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.textfield.TextAreaVariant;

@PageTitle("Java Tools")
@Route("")

public class MainView extends VerticalLayout {

    public MainView() {
        
        H1 title = new H1("Java Scanner Generator");
        //TODO: Center Title
        title.getStyle().set("text-align","center");

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
                    nameField.addFocusListener(listener -> {
                        if (nameField.getStyle().get("color").equals("red")) {
                            nameField.getStyle().set("color", "black");
                        }
                    });
                    typeField.addFocusListener(listener -> {
                        if (typeField.getStyle().get("color").equals("red")) {
                            typeField.getStyle().set("color", "black");
                        }
                    });
                    
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

        Button listGen = new Button("listGen");
        listGen.setText("Generate");
        
        HorizontalLayout manualInput = new HorizontalLayout(typeField, nameField, addButton);
        fieldList.add(manualInput);

        TextField classInput = new TextField();
        classInput.setPlaceholder("Class Name");


        HorizontalLayout genRow = new HorizontalLayout(classInput,listGen);

        VerticalLayout manual = new VerticalLayout();
        manual.add(new H2("Manual Input"),genRow,new H4("Properties"), fieldList);

        // ----------------------------------------------

        VerticalLayout construct = new VerticalLayout();

        TextField cField = new TextField();
        cField.setWidth("100%");
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
        TextArea resultText = new TextArea("Result");
        resultText.setWidth("75%");

        result.add(resultText);
        result.setVisible(false);

        listGen.addClickListener(listener -> {
            
            System.out.println("CLICKED");
            System.out.println(fieldList.getComponentCount());
            
            if (fieldList.getComponentCount() == 1) {
                System.out.println("NOPE");
                Notification notification = Notification.show("Add some fields first!");
            }
            else {
                String[][] fieldArray = new String[fieldList.getComponentCount()][2];
                fieldArray[0][0] = classInput.getValue();
                for (int i=1;i<fieldList.getComponentCount();i++) {
                    Component c = fieldList.getComponentAt(i);
                    System.out.println(c);
                    String a = c.getComponentAt(i).getValue();
                }
                result.setVisible(true);
                //result.setValue(Generate.fromList(fieldArray);
            }
        });

        generate.addClickListener(click -> {
            // pBar.setVisible(true);
            // pBarLabel.setVisible(true);
            result.setVisible(true);
            resultText.setValue(Generate.fromConstruct(cField.getValue()));
        });

        add(
                title,
                new HorizontalLayout(
                        manual,
                        construct),
                result

        );
    }
}