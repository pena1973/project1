package myRider.listeners;

import myRider.View;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Sid927 on 27.09.2016.
 */
public class DoubleClicListener implements MouseListener
{
    private View view;

    public DoubleClicListener(View view)
    {
        this.view = view;
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        if(e.getClickCount()==2){
            view.textPaneMouseClicked(e);
        }
    }

    @Override
    public void mousePressed(MouseEvent e)
    {
 //      System.out.println("mousePressed");
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
  //      System.out.println("mouseReleased");
    }

    @Override
    public void mouseEntered(MouseEvent e)
    {
 //       System.out.println("mouseEntered");
    }

    @Override
    public void mouseExited(MouseEvent e)
    {
  //      System.out.println("mouseExited");
    }
}
