package myRider.listeners;

import myRider.View;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Sid927 on 27.09.2016.
 */
public class PopupClass extends MouseAdapter
{
    private View view;

    public PopupClass(View view)
    {
        this.view = view;
    }

    public void mousePressed(MouseEvent event)
    {
        //if ((event.getButton() == 3) && (event.getClickCount() == 1))
        {
            System.out.println("mousePressed");
            System.out.println(event.getButton());
            System.out.println(event.getClickCount());
        }
    }

    public void mouseReleased(MouseEvent event)
    {
        if (event.isPopupTrigger())
        {
            System.out.println("mousePressed");
            view.openMenu(event);
        }
    }

    @Override
    public void mouseClicked(MouseEvent event)
    {
        //if ((event.getButton() == 1) && (event.getClickCount() == 1))

        System.out.println("mouseClicked");
        System.out.println(event.getButton());
        System.out.println(event.getClickCount());


    }
}
