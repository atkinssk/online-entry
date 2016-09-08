package uk.org.windswept;

import com.vaadin.data.validator.EmailValidator;
import com.vaadin.data.validator.IntegerRangeValidator;
import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Field;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

/**
 * Created by 802998369 on 24/08/2016.
 */
public enum DisplayFieldType
{
    TEXT
            {
                @Override
                public Field field(String name, String caption)
                {
                    return new TextField(caption);
                }
            },
    DATE
            {
                @Override
                public Field field(String name, String caption)
                {
                    return new DateField(caption);
                }
            },
    NUMBER
            {
                @Override
                public Field field(String name, String caption)
                {
                    final TextField field = new TextField(caption);
                    field.addValidator(new IntegerRangeValidator("Please enter a valid number", 0, Integer.MAX_VALUE));
                    return field;
                }
            },
    TEXT_AREA
            {
                @Override
                public Field field(String name, String caption)
                {
                    return new TextArea(caption);
                }
            },
    EMAIL
            {
                @Override
                public Field field(String name, String caption)
                {
                    final TextField field = new TextField(caption);
                    field.addValidator(new EmailValidator("Please enter a valid email address"));
                    return field;
                }
            };

    public abstract Field field(String name, String caption);
}
