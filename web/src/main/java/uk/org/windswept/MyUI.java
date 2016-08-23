package uk.org.windswept;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
@Widgetset("uk.org.windswept.MyAppWidgetset")
public class MyUI extends UI {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyUI.class);

    private Date startDate = new Date();


    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();
        
        final TextField name = new TextField();
        name.setCaption("Type your name here:");

        Button button = new Button("Click Me");
        button.addClickListener( e -> {
            LOGGER.info("Click me {}", name.getValue());
            layout.addComponent(new Label("Thanks " + name.getValue() 
                    + ", it works!"));
        });


        Button button2 = new Button("And me");
        button2.addClickListener(e -> {
            LOGGER.info ("You called");
            Notification.show("You called", Notification.Type.WARNING_MESSAGE);
        });


        DateField dateField = new DateField("Start Date", startDate);
        dateField.addValueChangeListener(event -> {
            Date date = dateField.getValue();
            LOGGER.info("date:{}", date);
            Notification.show("You selected " + date);
        });

        layout.addComponents(name, button, button2, dateField);


        layout.setMargin(true);
        layout.setSpacing(true);
        
        setContent(layout);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
